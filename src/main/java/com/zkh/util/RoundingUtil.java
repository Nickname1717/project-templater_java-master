package com.zkh.util;



import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class RoundingUtil {
    public static String roundingTwo(float number) throws ParseException {
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        numberInstance.setMaximumFractionDigits(2);
        numberInstance.setRoundingMode(RoundingMode.HALF_UP);
        String num = numberInstance.format(number);
        double d = new DecimalFormat().parse(num).doubleValue();
        return String.valueOf(d);
    }

}
