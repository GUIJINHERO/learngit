package com.test.platform.controller;

import com.test.platform.dto.LoginDTO;
import com.test.platform.dto.LoginResultDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class LoginInfoController {

    public LoginResultDTO login(LoginDTO dto){
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(dto.getUserName(),dto.getPassword().toCharArray());

        Subject subject = SecurityUtils.getSubject();

        subject.login(usernamePasswordToken);

        subject.getSession().setTimeout(3*60*1000);


        LoginResultDTO resultDTO = new LoginResultDTO();
        resultDTO.setToken(subject.getSession().getId());

        return resultDTO;



    }



}
