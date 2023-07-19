package services.CurrencyServices;

import models.Currency;
import services.СonverterServices.ConverterService;

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
    public List<Currency> addCurrency(String title, int code) {
        return null;
    }

    @Override
    public Map<String, Double> buyingRate(String title, double course) {
        return null;
    }

    @Override
    public Map<String, Double> salesRate(String title, double course) {
        return null;
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
