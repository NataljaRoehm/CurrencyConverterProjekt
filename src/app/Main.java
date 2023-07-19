package app;

import models.Currency;
import services.CurrencyServices.CurrencyService;
import services.CurrencyServices.CurrencyServiceImpl;
import services.СonverterServices.ConverterService;
import services.СonverterServices.ConverterServiceImpl;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

public class Main {

    private static final int ADD_CURRENCY = 1;
    private static final int UPDATE_EXCHANGE_RATES = 2;
    private static final int CURRENCY_CONVERSION = 3;
    private static final int EXIT = 0;
    private static final int INCORRECT = -1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Currency> currencies = new ArrayList<>();
        ConverterService converterService = new ConverterServiceImpl();
        CurrencyService currencyService = new CurrencyServiceImpl(converterService);

        System.out.println("=== Конвертор валют v. 0.0.1 ===");
        int command;
        do {
            command = readCommand(scanner);
            switch (command) {
                case ADD_CURRENCY:
                    System.out.println("ADD_CURRENCY");
                    break;
                case UPDATE_EXCHANGE_RATES:
                    System.out.println("UPDATE_EXCHANGE_RATES");
                    break;
                case CURRENCY_CONVERSION:
                    System.out.println("CURRENCY_CONVERSION");
                    break;
                case EXIT:
                    break;
                default:
                    // Мы не должны здесь оказаться, `readCommand()` уже всё проверил
                    System.out.println("Некорректная команда: " + command);
                    break;
            }
        } while (command != EXIT);
        System.out.println("До свидания!");
    }

    private static int readCommand(Scanner scanner) {
        int command = INCORRECT;
        while (!isCommand(command)) {
            printMenu();
            System.out.print("Выберите команду: ");
            try {
                command = scanner.nextInt(); // здесь может быть InputMismatchException
                if (!isCommand(command)) {
                    System.out.println("Некорректный номер команды: " + command);
                }
            } catch (InputMismatchException e) {
                System.out.println("Некорректный ввод, введите номер команды!");
            } finally {
                scanner.nextLine();
            }
        }
        return command;
    }

    private static boolean isCommand(int command) {
        switch (command) {
            case ADD_CURRENCY:
            case UPDATE_EXCHANGE_RATES:
            case CURRENCY_CONVERSION:
            case EXIT:
                return true;
            default:
                return false;
        }
    }

    private static void printMenu() {
        System.out.println("Команды:");
        System.out.println(ADD_CURRENCY + ". Добавить валюту");
        System.out.println(UPDATE_EXCHANGE_RATES + ". Обновить курсы валюты");
        System.out.println(CURRENCY_CONVERSION + ". Конвертировать валюту");
        System.out.println(EXIT + ". Выход");
    }
}