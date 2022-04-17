package net.accessory.paragram.entities;

public class ConvertCodeName {
    public static String convert (String name){
        String code_name = name.toLowerCase();
        code_name = code_name.trim();
        code_name = code_name.replace(" ","-");
        return code_name;
    }
}
