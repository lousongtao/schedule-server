package com.jslink.schedule.service;

import com.jslink.schedule.bean.UserTime;
import com.jslink.schedule.responsebody.ResponseBody;
import com.jslink.schedule.requestbody.RqbUser;
import com.jslink.schedule.requestbody.RqbUserTime;

import java.util.Date;
import java.util.List;

public interface UserService {
    ResponseBody<List<UserTime>> queryUserTime(int userId, Date date1, Date date2);

    ResponseBody saveUserTime(RqbUserTime rqbUserTime);

    ResponseBody saveUser(RqbUser user);

    ResponseBody queryUsers();
}
