package com.devopsbuddy.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    /** The login view name */
    private static final String LOGIN_VIEW_NAME = "user/login" ;

    @GetMapping(value = "/login")
    public String login() {
        return LOGIN_VIEW_NAME;
    }
}
