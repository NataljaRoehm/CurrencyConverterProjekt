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
        Currency currency = new Currency(code, title);
        currencyList.add(currency);
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
    public double ConverterOfCurrency(Double sum, Map<String, ArrayList<Double>> rates, String key, int index) {
        index = 0;
        key = "UsdEur";

        return converterService.ConverterOfCurrency(sum, rates, key, index);
    }


}
