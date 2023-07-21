package services.CurrencyServices;

import models.Currency;
import services.СonverterServices.ConverterService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyServiceImpl implements CurrencyService {

    private ConverterService converterService;

    public ConverterService getConverterService() {
        return converterService;
    }

    public CurrencyServiceImpl(ConverterService converterService) {
        this.converterService = converterService;
    }

    @Override
    public void addCurrency(int code, String title, List<Currency> currencyList) {
        boolean isPresent = false;
        Currency currency = new Currency(code, title);
        if (currencyList.size() == 0) {
            currencyList.add(currency);
            return;
        }
        for (Currency currency1 : currencyList) {
            if (currency1.equals(currency)) {
                isPresent = true;
                if (currencyList.size() > 0) {
                    System.out.println("Валюта " + currency.getTitle() + " дублируется.");
                }
            }
        }
        if (!isPresent) {
            currencyList.add(currency);
        }
    }

    @Override
    public void exchangeRates(Map<String, ArrayList<Double>> rates, String key,
                              double courseSales, double courseBuying) {
        ArrayList<Double> courses = new ArrayList<>();
        courses.add(courseBuying);
        courses.add(courseSales);
        rates.put(key, courses);
    }

    @Override
    public List<Double> ConverterOfCurrency(Map<String, ArrayList<Double>> rates, Double sum,
                                            String key, int index) {
        return converterService.ConverterOfCurrency(rates, sum, key, index);
    }
}
