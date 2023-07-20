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
                    System.out.println(currencies);
                    break;
                case UPDATE_EXCHANGE_RATES:
                    updateExchangeRates(exchangeRates,
                            currencyService,
                            currencies,
                            scanner);
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

    public static void addCurrency(List<Currency> currencyList, CurrencyService currencyService, Scanner scanner) {
        System.out.println("Добавление новой валюты:");
        System.out.println("Введите название валюты:");
        String title = scanner.nextLine();
        while (title.isEmpty()) {
            System.out.print("Название валюты не может быть пустым, введите название: ");
            title = scanner.nextLine();
        }
        System.out.println("Введите код валюты:");
        int code = INCORRECT;
        while (code <= 0) {
            while (!scanner.hasNextInt()) {
                System.out.println("Некорректный ввод, введите число:");
                System.out.println("Введите код валюты:");
            }
            code = scanner.nextInt();
            scanner.nextLine();
            if (code <= 0) {
                System.out.println("Некорректный ввод, введите код валюты:");
            }
        }
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
                    System.out.println("Введите курс покупки  " + currency.getTitle() +
                            " за " + originalCurrency.getTitle());
                    double buyingRate = INCORRECT;
                    while (buyingRate <= 0) {
                        while (!scanner.hasNextDouble()) {
                            System.out.println("Некорректный ввод, введите число:");
                            System.out.println("Введите курс покупки:");
                        }
                        buyingRate = scanner.nextDouble();
                        scanner.nextLine();
                        if (buyingRate <= 0) {
                            System.out.println("Некорректный ввод, введите курс покупки:");
                        }
                    }
                    System.out.println("Введите курс продажи  " + currency.getTitle() +
                            " за " + originalCurrency.getTitle());

                    double salesRate = INCORRECT;
                    while (salesRate <= 0) {
                        while (!scanner.hasNextDouble()) {
                            System.out.println("Некорректный ввод, введите число:");
                            System.out.println("Введите курс продажи:");
                        }
                        salesRate = scanner.nextDouble();
                        scanner.nextLine();
                        if (salesRate <= 0) {
                            System.out.println("Некорректный ввод, введите курс продажи:");
                        }
                    }
                    String key = originalCurrency.getTitle() + currency.getTitle();
                    currencyService.exchangeRates(exchangeRates, key, salesRate, buyingRate);
                }
            }
        }
    }

    public static void CurrencyConversion(Map<String, ArrayList<Double>> exchangeRates,
                                          CurrencyService currencyService,
                                          List<Currency> currencyList,
                                          Scanner scanner) {
        final int BUY_RATE = 1;
        final int SALE_RATE = 2;
        System.out.println("Валюты:");
        for (Currency currency : currencyList) {
            System.out.print(currency.getTitle() + " ");
        }
        System.out.println();
        System.out.println("Выберите исходную валюту");
        String originalCurrency = currencySelection(currencyList, scanner);
        String finalCurrency = originalCurrency;
        String key = null;
        while (finalCurrency.equals(originalCurrency)) {
            System.out.println("Конечная валюта не должна быть равна исходной валюте");
            System.out.println("Выберите конечную валюту");
            finalCurrency = currencySelection(currencyList, scanner);
            if (finalCurrency.equals(originalCurrency)) {
                System.out.println("Конечная валюта не должна быть равна исходной валюте");
                System.out.println("Выберите конечную валюту");
                finalCurrency = currencySelection(currencyList, scanner);
            }
            key = originalCurrency + finalCurrency;
        }
        int exchangeRate = 0;
        System.out.println("Выберите курс для конвертации:");
        System.out.println(BUY_RATE + ". Курс покупки");
        System.out.println(SALE_RATE + ". Курс продажи");
        int command = INCORRECT;
        while (command != 1 && command != 2) {
            while (!scanner.hasNextInt()) {
                System.out.println("Некорректный ввод, введите число: ");
                System.out.println("Выберите курс для конвертации:");
            }
            command = scanner.nextInt();
            scanner.nextLine();
            if (command != 1 && command != 2) {
                System.out.println("Некорректный ввод, выберите курс для конвертации:");
                continue;
            }
            switch (command) {
                case BUY_RATE:
                    break;
                case SALE_RATE:
                    exchangeRate = 1;
                    break;
                default:
                    System.out.println("Некорректная команда: " + command);
                    break;
            }
        }
        double initialAmount = INCORRECT;
        System.out.println("Введите начальную сумму:");
        while (initialAmount <= 0) {
            while (!scanner.hasNextInt()) {
                System.out.println("Некорректный ввод, введите число:");
                System.out.println("Введите начальную сумму:");
            }
            initialAmount = scanner.nextInt();
            scanner.nextLine();
            if (initialAmount <= 0) {
                System.out.println("Некорректный ввод, введите начальную сумму:");
            }
        }
        List<Double> results = currencyService.ConverterOfCurrency(exchangeRates, initialAmount,
                key, exchangeRate);
        System.out.println("Результат:");
        System.out.println("Начальная валюта: " + originalCurrency);
        System.out.println("Начальная сумма: " + initialAmount + " " + originalCurrency);
        System.out.println("Конечная валюта: " + finalCurrency);
        if (exchangeRate == 0) {
            System.out.println("Курс покупки: " + results.get(1) + " " +
                    originalCurrency + " за один " + finalCurrency);
            System.out.println("Конечная сумма: " + results.get(0) + " " + finalCurrency);
        } else {
            System.out.println("Курс продажи: " + results.get(1) + " " +
                    originalCurrency + " за один " + finalCurrency);
            System.out.println("Конечная сумма: " + results.get(0) + " " + finalCurrency);
        }


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

    public static String currencySelection(List<Currency> currencyList, Scanner scanner) {
        int count = 0;
        String originalCurrency = scanner.nextLine();
        while (count == 0) {
            for (Currency currency : currencyList) {
                if (originalCurrency.equals(currency.getTitle())) {
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("Валюта " + originalCurrency + " не корректна.");
                System.out.println("Введите валюту:");
                originalCurrency = scanner.nextLine();
            }
        }
        return originalCurrency;
    }
}
