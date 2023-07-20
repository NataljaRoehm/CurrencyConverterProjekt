package services.СonverterServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConverterServiceImpl implements ConverterService {

    @Override
    public List<Double> ConverterOfCurrency(Map<String, ArrayList<Double>> rates, Double sum,
                                            String key, int index) {
        List<Double> results = new ArrayList<>();

        results.add(sum / rates.get(key).get(index));
        results.add(rates.get(key).get(index));
        return results;
    }
}
