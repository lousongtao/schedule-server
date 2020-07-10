package com.jslink.schedule.controller;

import com.jslink.schedule.bean.UserTime;
import com.jslink.schedule.requestbody.RqbUser;
import com.jslink.schedule.responsebody.ResponseBody;
import com.jslink.schedule.requestbody.RqbUserTime;
import com.jslink.schedule.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/user")
@RestController
@Api(value = "user", tags = {"user"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserContoller {

    private final Logger logger = LoggerFactory.getLogger(UserContoller.class);

    @Autowired
    private UserService userService;

    @PostMapping("/")
    @ApiOperation(value = "Load user time", response = List.class, httpMethod = "POST", authorizations = {@Authorization(value = "basicAuth")})
    public ResponseBody saveUser(@ApiParam(name = "user", value = "user body")
                                     @RequestParam(name = "user")RqbUser user){
        return userService.saveUser(user);
    }

    @GetMapping("/usertime")
    @ApiOperation(value = "Load user time", response = List.class, httpMethod = "GET", authorizations = {@Authorization(value = "basicAuth")})
    public List<UserTime> queryStaffTime(@ApiParam(name = "userName", value = "user name")
                                              @RequestParam(name = "userName") String userName,
                                          @ApiParam(name = "date", value = "day time")
                                          @RequestParam(name = "startTime") Date date){
        return userService.queryUserTime(userName, date);
    }

    @PostMapping("/usertime")
    @ApiOperation(value = "Load user time", response = List.class, httpMethod = "POST", authorizations = {@Authorization(value = "basicAuth")})
    public ResponseBody saveStaffTime(@ApiParam(name = "rqbUserTime", value = "body of user name")
                                          @RequestParam(name = "rqbUserTime")RqbUserTime rqbUserTime){
        return userService.saveUserTime(rqbUserTime);
    }
}
