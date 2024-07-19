package com.challenge7user.challenge7user.ConfigOauth;


import lombok.Data;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

@Data
public class Config {
    private String code = "status";
    private String message = "message";
    public String code_notFound = "404";
    public String codeRequired = "403";
    public String isRequired = "is Required";
    public String code_sukses = "200";
    public String code_server = "500";
    public String code_null = "1";
    public String message_sukses = "sukses";



    public String convertDateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(date);
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }
}


