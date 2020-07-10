package com.jslink.schedule.service;

import com.jslink.schedule.bean.UserTime;
import com.jslink.schedule.responsebody.ResponseBody;
import com.jslink.schedule.requestbody.RqbUser;
import com.jslink.schedule.requestbody.RqbUserTime;

import java.util.Date;
import java.util.List;

public interface UserService {
    List<UserTime> queryUserTime(String userName, Date date);

    ResponseBody saveUserTime(RqbUserTime rqbUserTime);

    ResponseBody saveUser(RqbUser user);
}
