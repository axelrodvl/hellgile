package co.axelrod.hellgile.game.telegram;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.*;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 26.12.2017.
 */
public class UserInteractionBuilder {
    private Long chatId;

    private List<SendMessage> messagesToSend = new ArrayList<>();
    private Map<String, UserInteractionBuilder> nextInteractions = new HashMap<>();

    private String menuItemName = null;
    private ReplyKeyboardMarkup replyKeyboardMarkup = null;
    List<KeyboardRow> keyboard = new ArrayList<>();

    public UserInteractionBuilder(Long chatId) {
        this.chatId = chatId;
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

    public UserInteractionBuilder withMenu(UserInteractionBuilder userInteractionBuilder) {
        this.nextInteractions.put(userInteractionBuilder.menuItemName, userInteractionBuilder);
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(userInteractionBuilder.menuItemName);
        this.keyboard.add(keyboardRow);
        return this;
    }

    public List<SendMessage> build(Update update) {
          if(!nextInteractions.containsKey(update.getMessage().getText())) {

            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            keyboardMarkup.setKeyboard(keyboard);

            if(messagesToSend.isEmpty()) {
                this.messagesToSend.add(createMessage().setText("").setReplyMarkup(keyboardMarkup));
            } else {
                this.messagesToSend.get(messagesToSend.size() - 1).setReplyMarkup(keyboardMarkup);
            }

            return messagesToSend;
        } else {
            UserInteractionBuilder nextInteraction = nextInteractions.get(update.getMessage().getText());
            return nextInteraction.build(null);
        }
    }

    private void addMessage(String text) {
        this.messagesToSend.add(createMessage().setText(text));
    }

    private SendMessage createMessage() {
        return new SendMessage().setChatId(this.chatId);
    }
}
