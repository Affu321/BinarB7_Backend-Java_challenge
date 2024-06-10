package com.Binar.Challenge4.config;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Config {

    public  static String APP_NAME ="name";

    public static  String yourName(){
        return "affu";
    }
    public String convertDateToString(Date date) {

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String strDate = dateFormat.format(date);
        return strDate;
    }
    public  String code ="status";

    public  String message= "message";

}
