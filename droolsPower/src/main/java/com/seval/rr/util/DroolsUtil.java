package com.seval.rr.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;

public class DroolsUtil {

    public static boolean isValidDecimal(String input, int whole, int decimal) {
        StringBuilder sbRegex = new StringBuilder("\\d{1," + whole + "}");
        if (decimal > 0) {
            sbRegex.append("(\\.\\d{" + decimal + "})?");
        }
        return input.trim().matches(sbRegex.toString());
    }

    public static boolean isAlphaNumeric(String input) {
        return input.matches("[a-zA-Z0-9]+");
    }

    public static boolean isAlphaNumeric(String input, int min, int max) {
        return input.trim().matches("[a-zA-Z0-9]{" + min + "," + max + "}");
    }

    public static boolean isAlpha(String input) {
        return input.trim().matches("[a-zA-Z]+");
    }

    public static boolean isAlpha(String input, int min, int max) {
        return input.trim().matches("[a-zA-Z]{" + min + "," + max + "}");
    }

    public static boolean isNumeric(String input) {
        try {
            Double.parseDouble(input.trim());
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean productOf(String result, String input1, String input2) {
        if (StringUtils.isEmpty(result) || StringUtils.isEmpty(input1) || StringUtils.isEmpty(input2)) {
            return false;
        }
        BigDecimal bdResult = new BigDecimal(result.trim()).setScale(4, RoundingMode.HALF_UP);

        BigDecimal calc = BigDecimal.ONE;
        BigDecimal bdInput1 = new BigDecimal(input1.trim());
        calc = calc.multiply(bdInput1).setScale(4, RoundingMode.HALF_UP);
        BigDecimal bdInput2 = new BigDecimal(input2.trim());
        calc = calc.multiply(bdInput2).setScale(4, RoundingMode.HALF_UP);
        return bdResult.compareTo(calc) == 0 ? true : false;
    }

    public static boolean isNumeric(String input, int length) {
        return isNumeric(input) && input.matches("[0-9]{1," + length + "}");
    }

    public static boolean isValidDateTimeFormat(String input, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        try {
            sdf.parse(input.trim());
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
