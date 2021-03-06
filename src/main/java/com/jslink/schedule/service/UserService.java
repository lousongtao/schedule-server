package com.jslink.schedule.service;

import com.jslink.schedule.responsebody.RbUserTime;
import com.jslink.schedule.responsebody.ResponseBody;
import com.jslink.schedule.requestbody.RqbUser;
import com.jslink.schedule.requestbody.RqbUserTime;

import java.util.Date;
import java.util.List;

public interface UserService {
    ResponseBody<List<RbUserTime>> queryUserTime(int userId, Date date1, Date date2);

    ResponseBody<RbUserTime> saveUserTime(RqbUserTime rqbUserTime);

    ResponseBody saveUser(RqbUser user);

    ResponseBody queryUsers();

    ResponseBody<List<RbUserTime>> copyUserTime(int userId, Date monday);

    ResponseBody<List<RbUserTime>> chooseAll(int userId, Date monday, boolean chooseAll);
}
