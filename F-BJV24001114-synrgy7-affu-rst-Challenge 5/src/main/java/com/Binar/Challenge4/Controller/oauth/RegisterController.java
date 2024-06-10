package com.Binar.Challenge4.Controller.oauth;

import com.Binar.Challenge4.Entity.RegisterModel.RegisterModel;

import com.Binar.Challenge4.Entity.oauth.UserOauth;
import com.Binar.Challenge4.Repository.oauth.UserOauthRepository;
import com.Binar.Challenge4.Service.Email.EmailSender;

import com.Binar.Challenge4.Service.oauth.UserOauthService;
import com.Binar.Challenge4.config.Config;
import com.Binar.Challenge4.utils.EmailTemplate;
import com.Binar.Challenge4.utils.Response;
import com.Binar.Challenge4.utils.SimpleStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/user-register/")
public class RegisterController {
    @Autowired
    private UserOauthRepository userRepository;

    Config config = new Config();

    @Autowired
    public UserOauthService serviceReq;

    @Autowired
    public Response templateEror;
    @Autowired
    public EmailSender emailSender;
    @Autowired
    public EmailTemplate emailTemplate;

    @Value("${expired.token.password.minute:}")//FILE_SHOW_RUL
    private int expiredToken;

    @PostMapping("/register")
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map> saveRegisterManual(@Valid @RequestBody RegisterModel objModel) throws RuntimeException {
        Map map = new HashMap();

        UserOauth user = userRepository.checkExistingEmail(objModel.getUsername());
        if (null != user) {
            return new ResponseEntity<Map>(templateEror.templateEror("Username sudah ada"), HttpStatus.OK);

        }
        map = serviceReq.registerManual(objModel);
        Map sendOTP = sendEmailegister(objModel);
        Map sendOTPUri = sendEmailegisterTymeleaf(objModel);

        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }


    @PostMapping("/register-google")
    public ResponseEntity<Map> saveRegisterByGoogle(@Valid @RequestBody RegisterModel objModel) throws RuntimeException {
        Map map = new HashMap();

        UserOauth user = userRepository.checkExistingEmail(objModel.getUsername());
        if (null != user) {
            return new ResponseEntity<Map>(templateEror.Error("Username sudah ada"), HttpStatus.OK);

        }
        map = serviceReq.registerByGoogle(objModel);
        //gunanya send email
        Map mapRegister = sendEmailegister(objModel);
        return new ResponseEntity<Map>(mapRegister, HttpStatus.OK);

    }


    // Step 2: sendp OTP berupa URL: guna updeta enable agar bisa login:
    @PostMapping("/send-otp")//send OTP
    public Map sendEmailegister(
            @RequestBody RegisterModel user) {
        String message = "Thanks, please check your email for activation.";

        if (user.getUsername() == null) return templateEror.sucsess("No email provided");
        UserOauth found = userRepository.findOneByUsername(user.getUsername());
        if (found == null) return templateEror.Error("Email not found"); //throw new BadRequest("Email not found");

        String template = emailTemplate.getRegisterTemplate();
        if (StringUtils.isEmpty(found.getOtp())) {
            UserOauth search;
            String otp;
            do {
                otp = SimpleStringUtils.randomString(6, true);
                search = userRepository.findOneByOTP(otp);
            } while (search != null);
            Date dateNow = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateNow);
            calendar.add(Calendar.MINUTE, expiredToken);
            Date expirationDate = calendar.getTime();

            found.setOtp(otp);
            found.setOtpExpiredDate(expirationDate);
            template = template.replaceAll("\\{\\{USERNAME}}", (found.getFullname() == null ? found.getUsername() : found.getFullname()));
            template = template.replaceAll("\\{\\{VERIFY_TOKEN}}", otp);
            userRepository.save(found);
        } else {
            template = template.replaceAll("\\{\\{USERNAME}}", (found.getFullname() == null ? found.getUsername() : found.getFullname()));
            template = template.replaceAll("\\{\\{VERIFY_TOKEN}}", found.getOtp());
        }
        emailSender.sendAsync(found.getUsername(), "Register", template);
        return templateEror.sucsess(message);
    }
    @GetMapping("/register-confirm-otp/{token}")
    public ResponseEntity<Map> saveRegisterManual(@PathVariable(value = "token") String tokenOtp) throws RuntimeException {



        UserOauth user = userRepository.findOneByOTP(tokenOtp);
        if (null == user) {
            return new ResponseEntity<Map>(templateEror.templateEror("OTP tidak ditemukan"), HttpStatus.OK);
        }
//validasi jika sebelumnya sudah melakukan aktifasi

        if(user.isEnabled()){
            return new ResponseEntity<Map>(templateEror.templateSuksess("Akun Anda sudah aktif, Silahkan melakukan login"), HttpStatus.OK);
        }
        String today = config.convertDateToString(new Date());

        String dateToken = config.convertDateToString(user.getOtpExpiredDate());
        if(Long.parseLong(today) > Long.parseLong(dateToken)){
            return new ResponseEntity<Map>(templateEror.templateEror("Your token is expired. Please Get token again."), HttpStatus.OK);
        }
        //update user
        user.setEnabled(true);
        userRepository.save(user);

        return new ResponseEntity<Map>(templateEror.templateSuksess("Sukses, Silahkan Melakukan Login"), HttpStatus.OK);
    }
    @Value("${BASEURL:}")//FILE_SHOW_RUL
    private String BASEURL;

    @PostMapping("/send-otp-tymeleaf")//send OTP
    public Map sendEmailegisterTymeleaf(@RequestBody RegisterModel user) {
        String message = "Thanks, please check your email for activation.";

        if (user.getUsername() == null) return templateEror.Error("No email provided");
        UserOauth found = userRepository.findOneByUsername(user.getUsername());
        if (found == null) return templateEror.Error("Email not found"); //throw new BadRequest("Email not found");

        String template = emailTemplate.getRegisterTemplate();
        if (StringUtils.isEmpty(found.getOtp())) {
            UserOauth search;
            String otp;
            do {
                otp = SimpleStringUtils.randomString(6, true);
                search = userRepository.findOneByOTP(otp);
            } while (search != null);
            Date dateNow = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateNow);
            calendar.add(Calendar.MINUTE, expiredToken);
            Date expirationDate = calendar.getTime();

            found.setOtp(otp);
            found.setOtpExpiredDate(expirationDate);
            template = template.replaceAll("\\{\\{USERNAME}}", (found.getFullname() == null ? found.getUsername() : found.getFullname()));
            template = template.replaceAll("\\{\\{VERIFY_TOKEN}}", BASEURL + "/v1/user-register/web/index/"+ otp);
            userRepository.save(found);
        } else {
            template = template.replaceAll("\\{\\{USERNAME}}", (found.getFullname() == null ? found.getUsername() : found.getFullname()));
            template = template.replaceAll("\\{\\{VERIFY_TOKEN}}",  BASEURL + "/v1/user-register/web/index/"+  found.getOtp());
        }
        emailSender.sendAsync(found.getUsername(), "Register", template);
        return templateEror.sucsess(message);
    }

    @PostMapping("/register-google-tymeleaf")
    public ResponseEntity<Map> saveRegisterByGoogleTyemeleaf(@Valid @RequestBody RegisterModel objModel) throws RuntimeException {
        Map map = new HashMap();

        UserOauth user = userRepository.checkExistingEmail(objModel.getUsername());
        if (null != user) {
            return new ResponseEntity<Map>(templateEror.Error("Username sudah ada"), HttpStatus.OK);

        }
        map = serviceReq.registerByGoogle(objModel);
        //gunanya send email
        Map mapRegister =  sendEmailegisterTymeleaf(objModel);
        return new ResponseEntity<Map>(mapRegister, HttpStatus.OK);
    }

}