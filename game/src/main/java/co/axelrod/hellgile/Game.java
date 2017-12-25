package co.axelrod.hellgile;

import co.axelrod.hellgile.game.telegram.UserInteractionBuilder;
import co.axelrod.hellgile.management.Project;
import co.axelrod.hellgile.people.external.Customer;
import co.axelrod.hellgile.people.internal.worker.AbstractWorker;
import co.axelrod.hellgile.people.internal.worker.Developer;
import co.axelrod.hellgile.people.internal.worker.params.Temperament;
import co.axelrod.hellgile.sprint.Sprint;
import lombok.Getter;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 11.12.2017.
 */
public class Game {
    private Long id;

    public UserInteractionBuilder lastInteraction;

    public Game(Long id) {
        this.id = id;
        configure();
    }

    @Getter
    Project project = new Project();

    private void configure() {
        Customer customer = new Customer();
        customer.setName("ПАО Германбанк");

        project.setName("Блокчейн");
        project.setMoney(1000000);
        project.setDuration(10);
        project.setDaysLeft(10);
        project.setCustomer(customer);
        project.setFuckedUpFee(project.getMoney() * 2);

        Developer developer = new Developer();
        developer.setName("Вася");
        developer.setSkills(null);
        developer.setTemperament(Temperament.CHOLERIC);
        developer.setSalary(100000);
        developer.setMood(100);
        developer.setLevel(35);

        project.getWorkers().add(developer);
    }

    public SendMessage action(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(id);
        //message.setText()

        Integer sprintCount = 1;

        System.out.println("\n" +
                "Добро пожаловать в Hellgile!\n" +
                "\n" +
                "Внимание! \uD83D\uDD1E! ИГРА СОДЕРЖИТ НЕЦЕНЗУРНУЮ ЛЕКСИКУ.\n" +
                "\n" +
                "Твой новый проект: " + project.getName() + "\n" +
                "Денег: " + project.getMoney() + " рублей\n" +
                "Длительность: " + project.getDuration() + " дней\n" +
                "Объём работ: 50 SP\n"); // TODO

        System.out.println("В твоём распоряжении:");
        for(AbstractWorker worker : project.getWorkers()) {
            System.out.println(worker.toString());
        }

        System.out.println("Проект будет поделён на 10 спринтов по неделе. \n" +
                "\n" +
                "Начинаем!\n" +
                "\n" +
                "Сейчас мы запланируем твой первый спринт. Наливай \uD83C\uDF78, \uD83D\uDCBA поудобнее. \n");

        System.out.println("Чтобы успевать, тебе нужно выполнять по Х SP в день. Пока с одним Васей шансов мало, но ты можешь его обучать, а также нанимать команду в процессе.");

        while(!project.getIsDone() && project.getMoney() >= 0 && (project.getDaysLeft() > 0)) {
            Sprint sprint = new Sprint(project);
            sprint.setName("Спринт: " + sprintCount++);
            sprint.planSprint();
            sprint.runSprint();
            sprint.endSprint();
        }

        if(project.getIsDone()) {
            System.out.println("Проект успешно завершен! В сроки и в плюс! Красавчик!");
            System.out.println("Профит: " + project.getMoney());
            System.out.println("Дней запланировано: " + project.getDuration());
            System.out.println("Дней осталось: " + project.getDaysLeft());
        }

        if(project.getMoney() < 0) {
            project.setMoney(project.getMoney() - project.getFuckedUpFee());

            System.out.println("\uD83E\uDD26 Просрали все полимеры!");
            System.out.println("\uD83D\uDCB8 Ты бабки вообще считаешь?");
            System.out.println("\uD83D\uDCB0 Убыток: " + project.getMoney());
            System.out.println("\uD83D\uDD70 Дней запланировано: " + project.getDuration());
            System.out.println("\uD83D\uDD52 Дней осталось: " + project.getDaysLeft());
        }

        if(project.getDaysLeft() == 0) {
            project.setMoney(project.getMoney() - project.getFuckedUpFee());

            System.out.println("\uD83E\uDD26 Проект проебан!");
            System.out.println("⏰ Сроки, блять!");
            System.out.println("\uD83D\uDCB0 Убыток: " + project.getMoney());
            System.out.println("\uD83D\uDD70 Дней запланировано: " + project.getDuration());
            System.out.println("\uD83D\uDD52 Дней осталось: " + project.getDaysLeft());
        }

        return null;
    }

    public List<SendMessage> startGame(Update update) {
        ArrayList<SendMessage> messages = new ArrayList<>();

        Integer sprintCount = 1;

        messages.add(prepareMessage("Твой ID: " + id));

        messages.add(prepareMessage(
                "Добро пожаловать в Hellgile!\n" +
                "\n" +
                "Внимание! \uD83D\uDD1E! ИГРА СОДЕРЖИТ НЕЦЕНЗУРНУЮ ЛЕКСИКУ.\n" +
                "\n" +
                "Твой новый проект: " + project.getName() + "\n" +
                "Денег: " + project.getMoney() + " рублей\n" +
                "Длительность: " + project.getDuration() + " дней\n" +
                "Объём работ: 50 SP\n"));

        StringBuilder workers = new StringBuilder("В твоём распоряжении:");
        for(AbstractWorker worker : project.getWorkers()) {
            workers.append(worker.toString());
        }
        messages.add(prepareMessage(workers.toString()));

        messages.add(prepareMessage("Проект будет поделён на 10 спринтов по неделе. \n" +
                "\n" +
                "Начинаем!\n" +
                "\n" +
                "Сейчас мы запланируем твой первый спринт. Наливай \uD83C\uDF78, \uD83D\uDCBA поудобнее. \n"));

        messages.add(prepareMessage("Чтобы успевать, тебе нужно выполнять по Х SP в день. Пока с одним Васей шансов мало, но ты можешь его обучать, а также нанимать команду в процессе."));

        /*
        while(!project.getIsDone() && project.getMoney() >= 0 && (project.getDaysLeft() > 0)) {
            Sprint sprint = new Sprint(project);
            sprint.setName("Спринт: " + sprintCount++);
            sprint.planSprint();
            sprint.runSprint();
            sprint.endSprint();
        }

        if(project.getIsDone()) {
            helloText.append("Проект успешно завершен! В сроки и в плюс! Красавчик!");
            helloText.append("Профит: " + project.getMoney());
            helloText.append("Дней запланировано: " + project.getDuration());
            helloText.append("Дней осталось: " + project.getDaysLeft());
        }

        if(project.getMoney() < 0) {
            project.setMoney(project.getMoney() - project.getFuckedUpFee());

            helloText.append("\uD83E\uDD26 Просрали все полимеры!");
            helloText.append("\uD83D\uDCB8 Ты бабки вообще считаешь?");
            helloText.append("\uD83D\uDCB0 Убыток: " + project.getMoney());
            helloText.append("\uD83D\uDD70 Дней запланировано: " + project.getDuration());
            helloText.append("\uD83D\uDD52 Дней осталось: " + project.getDaysLeft());
        }

        if(project.getDaysLeft() == 0) {
            project.setMoney(project.getMoney() - project.getFuckedUpFee());

            helloText.append("\uD83E\uDD26 Проект проебан!");
            helloText.append("⏰ Сроки, блять!");
            helloText.append("\uD83D\uDCB0 Убыток: " + project.getMoney());
            helloText.append("\uD83D\uDD70 Дней запланировано: " + project.getDuration());
            helloText.append("\uD83D\uDD52 Дней осталось: " + project.getDaysLeft());
        }
        */

        return messages;
    }

    /*
    public void sendCustomKeyboard(Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Добро пожаловать в Hellgile!\n" +
                "\n" +
                "Внимание! \uD83D\uDD1E! ИГРА СОДЕРЖИТ НЕЦЕНЗУРНУЮ ЛЕКСИКУ.\n" +
                "\n" +
                "Объём работ: 50 SP\n" +

                "Проект будет поделён на 10 спринтов по неделе. \n" +
                "\n" +
                "Начинаем!\n" +
                "\n" +
                "Сейчас мы запланируем твой первый спринт. Наливай \uD83C\uDF78, \uD83D\uDCBA поудобнее. \n"

                +

                "Чтобы успевать, тебе нужно выполнять по Х SP в день. Пока с одним Васей шансов мало, но ты можешь его обучать, а также нанимать команду в процессе.");

        // Create ReplyKeyboardMarkup object
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        // Set each button, you can also use KeyboardButton objects if you need something else than text
        row.add("Row 1 Button 1");
        row.add("Row 1 Button 2");
        row.add("Row 1 Button 3");
        // Add the first row to the keyboard
        keyboard.add(row);
        // Create another keyboard row
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Row 2 Button 1");
        row.add("Row 2 Button 2");
        row.add("Row 2 Button 3");
        // Add the second row to the keyboard
        keyboard.add(row);
        // Set the keyboard to the markup
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        message.setReplyMarkup(keyboardMarkup);

        try {
            // Send the message
            //execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    */

    public void saveGame() {
        // TODO
    }

    public void loadGame() {
        // TODO
    }

    private SendMessage prepareMessage(String text) {
        SendMessage message = new SendMessage();
        message.setChatId(id);
        message.setText(text);
        return message;
    }
}
