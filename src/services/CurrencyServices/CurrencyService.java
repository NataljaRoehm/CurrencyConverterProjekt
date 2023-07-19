package services.CurrencyServices;

import models.Currency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CurrencyService {
    void addCurrency(int code, String title, List<Currency> currencyList);

     void exchangeRates(HashMap<String, ArrayList<Double>> rates, String title,
                        double courseSales, double courseBuying);

    double converterBuyingRate(String currency, Map<String, Double> buyingRate);

    double converterSalesRate(String currency, Map<String, Double> salesRate);
}
