package com.model.modelsForCreationCommands.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.modelsForCreationCommands.AseLoadThresholdUiSpeech;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FieldExtractor {

    public static int getFieldIntPrimitive(String[] source, String fieldname) {
        final String field = Stream.of(source).filter(e -> e.contains(fieldname)).findFirst().get();
        String string = field.split("\\s").length != 0 ? field.split("\\s")[1] : null;

        if(null == string) {
            return -1;
        }
        return Integer.parseInt(string);
    }

    public static String getFieldString(String[] source, String fieldname) {
        final String field = Stream.of(source).filter(e -> e.contains(fieldname)).findFirst().get();
        String result = field.split("\\s").length > 1 ? field.split("\\s")[1] : null;
        return result;
    }

    public static List<Integer> getFieldList(String[] source, String fieldname) {
        List<Integer> result = new ArrayList<>();
        try {
            final String field = Stream.of(source).filter(e -> e.contains(fieldname)).findFirst().get();

            String string = field.split("\\s").length != 0 ? field.split("\\s")[1] : null;

            result = new ObjectMapper().readValue("[" + string + "]", List.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Object getFieldObject(Class<?> clazz, String[] source, String fieldname) {
        Object result = null;
        try {
            final String field = Stream.of(source).filter(e -> e.contains(fieldname)).findFirst().get();
            final String constructorArgs = field.split("\\s")[1];
            final Constructor<?> constructor = clazz.getConstructor(String.class);
            result = constructor.newInstance(constructorArgs);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Object getSpecificFieldObject(Class<?> clazz, String[] source, String fieldname) {
        Object result = null;
        try {
            final String field = Stream.of(source).filter(e -> e.contains(fieldname)).findFirst().get();
            final String[] constructorArgs = field.substring(source[104].indexOf(" ")).split(",");
            final Constructor<?> constructor = clazz.getConstructor(String[].class);
            result = constructor.newInstance(new Object[]{constructorArgs});

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
