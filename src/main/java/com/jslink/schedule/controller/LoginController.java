package com.jslink.schedule.controller;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Api(value = "login", tags = {"login"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {
}
