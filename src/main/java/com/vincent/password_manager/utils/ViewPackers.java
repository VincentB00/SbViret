package com.vincent.password_manager.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class ViewPackers
{
    public static final String BEARER_TOKEN = "Authorization";
    public static final String DOMAIN_NAME = "Domain-name";

    public static final Gson gson = new Gson();

    private ViewPackers() {};

    public static String customToJson(Object object)
    {
        return customToJson(object, null);
    }

    public static String customToJson(Object object, ArrayList<String> classes)
    {
        List<String[]> list = new ArrayList<>();

        Method[] methods = object.getClass().getMethods();

        for (Method method : methods) 
        {
            String methodName = method.getName();
            String get = methodName.substring(0, 3);

            if(!(get.contains("get") || get.contains("is")))
                continue;

            if(classes != null)
            {
                if(!classes.parallelStream().anyMatch(c -> c.equals(method.getReturnType().getClass().getName())))
                    continue;
            }
            // else
            // {
            //     if(!(method.getClass().isPrimitive() || method.getReturnType().toString().equals(String.class.toString()) || method.getReturnType().toString().equals(String[].class.toString())))
            //     {
            //         continue;
            //     }
            // }

            methodName = (methodName.charAt(3) + "").toLowerCase() + methodName.substring(4);
            StringBuilder stringBuilder = new StringBuilder();
            for (char c : methodName.toCharArray()) 
            {
                if(Character.isUpperCase(c))
                    stringBuilder.append("_" + Character.toLowerCase(c));
                else
                    stringBuilder.append(c);
            }
            String variableName = stringBuilder.toString();

            try
            {
                String key = variableName.toString();
                String value = (String) method.invoke(object);
                list.add(new String[] {key, value});
            }
            catch(Exception ex)
            {
                continue;
            }
        }

        return toJson(list);
    }

    public static String toJson(Object object)
    {
        return gson.toJson(object);
    }

    public static String toJson(Object object, Gson gson)
    {
        return gson.toJson(object);
    }

    public static String toJson(Object key, Object value)
    {
        if(isNumber(value))
            return String.format("{\"%s\":%s}", key, value);
        else
            return String.format("{\"%s\":\"%s\"}", key, value);
    }

    public static String toJson(List<String[]> list)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for(int count = 0; count < list.size(); count++)
        {	
            String key = list.get(count)[0];
            String value = list.get(count)[1];

            if(count >= (list.size() - 1))
            {
                if(isNumber(value) || value == null)
                    stringBuilder.append(String.format("\"%s\":%s", key, value));
                else
                    stringBuilder.append(String.format("\"%s\":\"%s\"", key, value));
            }
            else
            {
                if(isNumber(value) || value == null)
                    stringBuilder.append(String.format("\"%s\":%s,", key, value));
                else
                    stringBuilder.append(String.format("\"%s\":\"%s\",", key, value));
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    // public static String toJson(List<Pair<Object, Object>> list)
    // {
    //     StringBuilder stringBuilder = new StringBuilder();
    //     stringBuilder.append("{");
    //     for(int count = 0; count < list.size(); count++)
    //     {	
    //         String key = list.get(count).getKey().toString();

    //         String value = null;

    //         if(list.get(count).getValue() != null)
    //             value = list.get(count).getValue().toString();

    //         if(count >= (list.size() - 1))
    //         {
    //             if(isNumber(value) || value == null)
    //                 stringBuilder.append(String.format("\"%s\":%s", key, value));
    //             else
    //                 stringBuilder.append(String.format("\"%s\":\"%s\"", key, value));
    //         }
    //         else
    //         {
    //             if(isNumber(value) || value == null)
    //                 stringBuilder.append(String.format("\"%s\":%s,", key, value));
    //             else
    //                 stringBuilder.append(String.format("\"%s\":\"%s\",", key, value));
    //         }
    //     }
    //     stringBuilder.append("}");
    //     return stringBuilder.toString();
    // }

    public static boolean isNumber(Object object)
    {
        try
        {
            Double.parseDouble((String) object);
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
}
