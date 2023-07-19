package services.СonverterServices;

import java.util.Map;

public interface ConverterService {
    double converterBuyingRate(String currency, Map<String, Double> buyingRate);
    double converterSalesRate(String currency, Map<String, Double> salesRate);

}
