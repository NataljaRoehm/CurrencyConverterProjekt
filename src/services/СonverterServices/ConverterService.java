package services.СonverterServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ConverterService {

    List<Double> ConverterOfCurrency(Map<String, ArrayList<Double>> rates, Double sum, String key, int index);
}
