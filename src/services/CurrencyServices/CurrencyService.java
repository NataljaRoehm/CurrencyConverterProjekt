package services.CurrencyServices;

import models.Currency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CurrencyService {
    void addCurrency(int code, String title, List<Currency> currencyList);

     void exchangeRates(Map<String, ArrayList<Double>> rates, String key,
                        double courseSales, double courseBuying);

    double ConverterOfCurrency(Double sum, Map<String, ArrayList<Double>> rates, String key, int index);
}
