package app;

import models.Currency;
import services.CurrencyServices.CurrencyService;
import services.CurrencyServices.CurrencyServiceImpl;
import services.СonverterServices.ConverterService;
import services.СonverterServices.ConverterServiceImpl;

import java.util.*;

public class Main {

    private static final int ADD_CURRENCY = 1;
    private static final int UPDATE_EXCHANGE_RATES = 2;
    private static final int CURRENCY_CONVERSION = 3;
    private static final int EXIT = 0;
    private static final int INCORRECT = -1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Currency> currencies = new ArrayList<>();
        Map<String, ArrayList<Double>> exchangeRates = new HashMap<>();
        ConverterService converterService = new ConverterServiceImpl();
        CurrencyService currencyService = new CurrencyServiceImpl(converterService);
        System.out.println("=== Конвертор валют v. 0.0.1 ===");
        int command;
        do {
            command = readCommand(scanner);
            switch (command) {
                case ADD_CURRENCY:
                    addCurrency(currencies, currencyService, scanner);
                    break;
                case UPDATE_EXCHANGE_RATES:
                    updateExchangeRates(exchangeRates,
                            currencyService,
                            currencies,
                            scanner);
                    System.out.println(exchangeRates);
                    break;
                case CURRENCY_CONVERSION:
                    CurrencyConversion(exchangeRates,
                            currencyService,
                            currencies,
                            scanner);
                    break;
                case EXIT:
                    break;
                default:
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
                command = scanner.nextInt();
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

    public static void addCurrency(List<Currency> currencyList, CurrencyService currencyService, Scanner scanner) {
        System.out.println("Добавление новой валюты:");
        System.out.println("Введите название валюты:");
        String title = scanner.nextLine();
        while (title.isEmpty()) {
            System.out.print("Название валюты не может быть пустым, введите название: ");
            title = scanner.nextLine();
        }
        System.out.println("Введите код валюты:");
        while (!scanner.hasNextInt()) {
            String error = scanner.nextLine();
            System.out.println("Некорректный ввод, введите число: '" + error + "'");
            System.out.println("Введите код валюты:");
        }
        int code = scanner.nextInt();
        scanner.nextLine();
        currencyService.addCurrency(code, title, currencyList);
    }

    public static void updateExchangeRates(Map<String, ArrayList<Double>> exchangeRates,
                                           CurrencyService currencyService,
                                           List<Currency> currencyList,
                                           Scanner scanner) {
        System.out.println("Загрузка курсов валюты:");
        for (Currency originalCurrency : currencyList) {
            System.out.println("Курсы для исходной валюты " + originalCurrency.getTitle() + " :");
            for (Currency currency : currencyList) {
                if (!currency.getTitle().equals(originalCurrency.getTitle())) {
                    System.out.println("Введите курс покупки  " +  currency.getTitle() +
                            " за " + originalCurrency.getTitle());
                    while (!scanner.hasNextDouble()) {
                        String error = scanner.nextLine();
                        System.out.println("Некорректный ввод, введите число: '" + error + "'");
                        System.out.println("Введите курс покупки:");
                    }
                    double buyingRate = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Введите курс продажи  " +  currency.getTitle() +
                            " за " + originalCurrency.getTitle());
                    while (!scanner.hasNextDouble()) {
                        String error = scanner.nextLine();
                        System.out.println("Некорректный ввод, введите число: '" + error + "'");
                        System.out.println("Введите курс покупки:");
                    }
                    double salesRate = scanner.nextDouble();
                    scanner.nextLine();
                    String key = originalCurrency.getTitle() + currency.getTitle();
                    System.out.println(key);
                    currencyService.exchangeRates(exchangeRates, key, salesRate, buyingRate);
                }
            }
        }
    }

    public static void CurrencyConversion(Map<String, ArrayList<Double>> exchangeRates,
                                          CurrencyService currencyService,
                                          List<Currency> currencyList,
                                          Scanner scanner) {
        System.out.println("Валюты:");
        for (Currency currency : currencyList) {
            System.out.print(currency.getTitle() + " ");
        }
        System.out.println();
        System.out.println("Введите исходную валюту");
        String val = scanner.nextLine();

        double sum = 10;

        String key ="";

        int index = 0;


        double sumConv =  currencyService.ConverterOfCurrency(sum, exchangeRates, key, index);
        System.out.println(sumConv);

    }
}