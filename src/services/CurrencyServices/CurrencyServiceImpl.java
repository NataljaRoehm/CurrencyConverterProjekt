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
    public void exchangeRates(HashMap<String, ArrayList<Double>> rates, String title,
                              double courseSales, double courseBuying) {

    }

    @Override
    public double converterBuyingRate(String currency, Map<String, Double> buyingRate) {
        return 0;
    }

    @Override
    public double converterSalesRate(String currency, Map<String, Double> salesRate) {
        return 0;
    }
}
