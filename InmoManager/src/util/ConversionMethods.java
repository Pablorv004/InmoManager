package util;

import java.time.Month;
import java.util.List;

import models.Property;

public class ConversionMethods {

    public static String[] stringListToArray(List<String> list) {
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static Property[] propertyListToArray(List<Property> list){
        Property[] array = new Property[list.size()];
        for(int i = 0; i < list.size(); i++){
            array[i] = list.get(i);
            }
            return array;
    }

    public static String getAbreviation(Month month) {
        return switch (month) {
            case JANUARY -> "JAN";
            case FEBRUARY -> "FEB";
            case MARCH -> "MAR";
            case APRIL -> "APR";
            case MAY -> "MAY";
            case JUNE -> "JUN";
            case JULY -> "JUL";
            case AUGUST -> "AUG";
            case SEPTEMBER -> "SEP";
            case OCTOBER -> "OCT";
            case NOVEMBER -> "NOV";
            case DECEMBER -> "DEC";
            default -> "NaN";
        };
    }
}
