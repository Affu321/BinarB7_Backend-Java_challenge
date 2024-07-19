package com.challenge7user.challenge7user.Controller.oauth.tymeleaf;

import com.challenge7user.challenge7user.ConfigOauth.Config;
import com.challenge7user.challenge7user.Repository.oauth.UserOauthRepository;
import com.challenge7user.challenge7user.entity.oauth.UserOauth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/v1/user-register/web/")
public class RegisterConfirm {

    @Autowired
    public UserOauthRepository userRepo;

    Config config = new Config();

    @GetMapping(value = { "/index/{tokenotp}"})
    public String index(Model model, @PathVariable String  tokenotp) {
        UserOauth user = userRepo.findOneByOTP(tokenotp);
        if (null == user) {
            System.out.println("user null: tidak ditemukan");
            model.addAttribute("erordesc", "User not found for code "+tokenotp);
            model.addAttribute("title", "");
            return "register";
        }
        if(user.isEnabled()){
            model.addAttribute("erordesc", "Hai Adeline, yang cantik tapi");
            model.addAttribute("title", "Adeline Faradisia cah semarang");
            return "register";
        }

        String today =  convertDateToString(new Date());

        String dateToken = config.convertDateToString(user.getOtpExpiredDate());
        if(Long.parseLong(today) > Long.parseLong(dateToken)){
            model.addAttribute("erordesc", "Your token is expired. Please Get token again.");
            model.addAttribute("title", "");
            return "register";
        }
        user.setEnabled(true);
        userRepo.save(user);
        model.addAttribute("title", "Congratulations, "+user.getUsername()+", you have successfully registered.");
        model.addAttribute("erordesc", "");
        return "register";
    }

    @GetMapping(value = "/user1")
    public String index() {
        return "user";
    }

    @RequestMapping(value = "/user")
    public Principal user(Principal principal) {

        return  principal;
    }

    public String convertDateToString(Date date) {

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String strDate = dateFormat.format(date);
//        System.out.println("Date: " + strDate);
        return strDate;
    }

}
