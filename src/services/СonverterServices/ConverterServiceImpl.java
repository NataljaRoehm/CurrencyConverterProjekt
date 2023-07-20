package services.СonverterServices;

import java.util.ArrayList;
import java.util.Map;

public class ConverterServiceImpl implements ConverterService{


    @Override
    public double ConverterOfCurrency(Double sum, Map<String, ArrayList<Double>> rates, String key, int index) {


        //System.out.println(rates.get(key));

        return rates.get(key).get(0);
    }
}
