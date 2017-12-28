package co.axelrod.hellgile.game.telegram;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.lang.reflect.Constructor;
import java.util.*;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 26.12.2017.
 */
public abstract class UserInteractionBuilder {
    protected Long chatId;
    protected Class parentInteraction;

    private List<SendMessage> messagesToSend = new ArrayList<>();
    private Map<String, Class> nextInteractions = new HashMap<>();

    private String menuItemName = null;
    private ReplyKeyboardMarkup replyKeyboardMarkup = null;
    List<KeyboardRow> keyboard = new ArrayList<>();

    public UserInteractionBuilder(Long chatId, Class parentInteraction) {
        this.chatId = chatId;
        this.parentInteraction = parentInteraction;
    }

    public UserInteractionBuilder withName(String name) {
        this.menuItemName = name;
        return this;
    }

    public UserInteractionBuilder withRequest(String requestString) {
        addMessage(requestString);
        return this;
    }

    public UserInteractionBuilder withRequest(List<String> requestStringList) {
        StringBuilder stringBuilder = new StringBuilder();
        for(String requestString : requestStringList) {
            stringBuilder.append(requestString).append("\n");
        }
        addMessage(stringBuilder.toString());
        return this;
    }

    public UserInteractionBuilder withMenu(Class userInteractionBuilderClass) {
        try {
            Constructor<UserInteractionBuilder> constructor = userInteractionBuilderClass.getConstructor(Long.class);
            UserInteractionBuilder userInteractionBuilder = constructor.newInstance(chatId);

            this.nextInteractions.put(userInteractionBuilder.menuItemName, userInteractionBuilderClass);
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(userInteractionBuilder.menuItemName);
            this.keyboard.add(keyboardRow);
            return this;
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    protected List<SendMessage> build(Update update) {
        if(parentInteraction != null) {
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add("Назад");
            this.keyboard.add(keyboardRow);
        }

          if(update == null || !nextInteractions.containsKey(update.getMessage().getText())) {
            if(keyboard != null) {
                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                keyboardMarkup.setKeyboard(keyboard);

                if(messagesToSend.isEmpty()) {
                    this.messagesToSend.add(createMessage().setText("Действия:").setReplyMarkup(keyboardMarkup));
                } else {
                    this.messagesToSend.get(messagesToSend.size() - 1).setReplyMarkup(keyboardMarkup);
                }
            }

            return messagesToSend;
        } else {
            Class interaction;
              if(update.getMessage().getText().equals("Назад")) {
                  interaction = parentInteraction;
              } else {
                  interaction = nextInteractions.get(update.getMessage().getText());
              }

              try {
                  Constructor<UserInteractionBuilder> constructor = interaction.getConstructor(Long.class);
                  UserInteractionBuilder userInteractionBuilder = constructor.newInstance(chatId);
                  return userInteractionBuilder.build(null);
              } catch (Exception ex) {
                  throw new RuntimeException();
              }
        }
    }

    private void addMessage(String text) {
        this.messagesToSend.add(createMessage().setText(text));
    }

    private SendMessage createMessage() {
        return new SendMessage().setChatId(this.chatId);
    }
}
