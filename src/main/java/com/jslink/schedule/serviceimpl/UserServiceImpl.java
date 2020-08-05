package com.jslink.schedule.serviceimpl;

import com.jslink.schedule.bean.UserTime;
import com.jslink.schedule.bean.TimeSlot;
import com.jslink.schedule.bean.User;
import com.jslink.schedule.responsebody.RbUserTime;
import com.jslink.schedule.responsebody.ResponseBody;
import com.jslink.schedule.repository.TimeSlotRepository;
import com.jslink.schedule.repository.UserRepository;
import com.jslink.schedule.repository.UserTimeRepository;
import com.jslink.schedule.requestbody.RqbUser;
import com.jslink.schedule.requestbody.RqbUserTime;
import com.jslink.schedule.service.UserService;
import com.jslink.schedule.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TimeSlotRepository timeSlotRepository;
    @Autowired
    private UserTimeRepository userTimeRepository;

    @Override
    public ResponseBody<List<RbUserTime>> queryUserTime(int userId, Date date1, Date date2) {
        List<UserTime> userTimes = userTimeRepository.findByUserAndDateRange(userId, date1, date2);
        List<RbUserTime> rbUserTimes = userTimes.stream().map(ut -> getRbUserTime(ut)).collect(Collectors.toList());
        return new ResponseBody(true, null, rbUserTimes);
    }

    /**
     * 根据user和timeslot和date确定记录, 如果存在, 就修改, 否则, 就新增一条
     * @return
     */
    @Override
    public ResponseBody<RbUserTime> saveUserTime(RqbUserTime rqbUserTime) {
        Date date = null;
        try {
            date = Utils.DF_YYYYMMDD.parse(rqbUserTime.getDate());
        } catch (ParseException e) {
            return new ResponseBody(false, "parse date exception for " + rqbUserTime.getDate());
        }
        UserTime ut = userTimeRepository.findByDateAndUserIdAndTimeSlotId(date, rqbUserTime.getUserId(), rqbUserTime.getTimeSlotId());
        if (ut == null) {
            User user = userRepository.findById(rqbUserTime.getUserId()).get();
            TimeSlot timeSlot = timeSlotRepository.findById(rqbUserTime.getTimeSlotId()).get();
            ut = new UserTime();
            ut.setUser(user);
            ut.setTimeSlot(timeSlot);
            ut.setDate(date);
        }
        ut.setAvailable(rqbUserTime.isAvailable());
        ut = userTimeRepository.save(ut);
        return new ResponseBody(true, null, getRbUserTime(ut));
    }

    private RbUserTime getRbUserTime(UserTime ut){
        return new RbUserTime(Utils.DF_YYYYMMDD.format(ut.getDate()), ut.getTimeSlot().getId(), ut.getUser().getId(), null, ut.isAvailable());
    }

    @Override
    public ResponseBody saveUser(RqbUser rqbUser) {
        User user = null;
        if (rqbUser.getId() <= 0){
            user = new User();
            user.setName(rqbUser.getName());
            user.setPassword(rqbUser.getPassword());
            user.setShiftTimes(rqbUser.getShiftTimes());
            user.setAvailable(true);
        } else {
            user = userRepository.findById(rqbUser.getId()).get();
            user.setName(rqbUser.getName());
            user.setAvailable(rqbUser.isAvailable());
            user.setShiftTimes(rqbUser.getShiftTimes());
            user.setPassword(rqbUser.getPassword());
        }
        user = userRepository.save(user);
        return new ResponseBody(true, null, user);
    }

    @Override
    public ResponseBody queryUsers() {
        List<User> users = userRepository.findAll();
        return new ResponseBody(true, null, users);
    }

    @Override
    public ResponseBody<List<RbUserTime>> copyUserTime(int userId, Date monday) {
        Calendar c = Calendar.getInstance();
        c.setTime(monday);
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 6);
        Date sunday = c.getTime();
        c.setTime(monday);
        userTimeRepository.deleteByUserAndDateRange(userId, monday, sunday);
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 7);
        Date lastMonday = c.getTime();
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 6);
        Date lastSunday = c.getTime();
        List<UserTime> userTimes = userTimeRepository.findByUserAndDateRange(userId, lastMonday, lastSunday);
        User user = userRepository.findById(userId).get();
        List<RbUserTime> uts = new ArrayList<>();
        for (UserTime ut: userTimes) {
            UserTime ut1 = new UserTime();
            ut1.setAvailable(ut.isAvailable());
            c.setTime(ut.getDate());
            c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 7);
            ut1.setDate(c.getTime());
            ut1.setUser(user);
            ut1.setTimeSlot(ut.getTimeSlot());
            ut1 = userTimeRepository.save(ut1);
            uts.add(getRbUserTime(ut1));
        }
        return new ResponseBody<>(true, null, uts);
    }

    /**
     * set all time true or false
     * if chooseAll = false, delete all time in this week;
     * if chooseAll = true, delete all time in this week and then rebuild this week's time
     * @param userId
     * @param monday
     * @param chooseAll
     * @return
     */
    @Override
    public ResponseBody<List<RbUserTime>> chooseAll(int userId, Date monday, boolean chooseAll) {
        Calendar c = Calendar.getInstance();
        c.setTime(monday);
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 6);
        Date sunday = c.getTime();
        userTimeRepository.deleteByUserAndDateRange(userId, monday, sunday);
        List<TimeSlot> tss = timeSlotRepository.findAll();

        List<RbUserTime> result = new ArrayList<>();
        if (chooseAll){
            User user = userRepository.findById(userId).get();
            for (int i = 0; i < 7; i++) {
                c.setTime(monday);
                c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + i);
                Date oneday = c.getTime();
                for(TimeSlot ts: tss) {
                    UserTime ut = new UserTime();
                    ut.setTimeSlot(ts);
                    ut.setUser(user);
                    ut.setAvailable(true);
                    ut.setDate(oneday);
                    userTimeRepository.save(ut);
                    result.add(getRbUserTime(ut));
                }
            }
        }

        return new ResponseBody<>(true, null, result);
    }
}
