package util;

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
}
