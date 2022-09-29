package com.vincent.password_manager.test;

import java.util.Arrays;

import com.vincent.password_manager.service.SecretService;

public class Test 
{
    public static void main(String[] args) 
    {
        // String fileName = "getSomethingThatCool";
        // String get = fileName.substring(0, 3);
        // fileName = (fileName.charAt(3) + "").toLowerCase() + fileName.substring(4);
        // StringBuilder stringBuilder = new StringBuilder();
        // for (char c : fileName.toCharArray()) 
        // {
        //     if(Character.isUpperCase(c))
        //         stringBuilder.append("_" + Character.toLowerCase(c));
        //     else
        //         stringBuilder.append(c);
        // }
        // fileName = stringBuilder.toString();
        // System.out.println(fileName.getClass().getName());

        // String test = "some";
        // System.out.println(test.indexOf("some/that"));
        // String[] t =  test.split("/");
        // System.out.println(t[t.length - 1]);

        SecretService ss = new SecretService();

        String s = ss.generateSecret(3, 1, 1, 1, 1);
        System.out.println(s);
    }    
}
