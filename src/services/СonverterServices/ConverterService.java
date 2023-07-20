package services.СonverterServices;

import java.util.ArrayList;
import java.util.Map;

public interface ConverterService {
    double ConverterOfCurrency(Double sum, Map<String, ArrayList<Double>> rates, String key, int index);


}
