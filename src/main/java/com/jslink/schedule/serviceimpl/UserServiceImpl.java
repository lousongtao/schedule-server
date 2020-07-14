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
        List<RbUserTime> rbUserTimes = userTimes.stream().map(ut -> new RbUserTime(
                Utils.DF_YYYYMMDD.format(ut.getDate()), ut.getTimeSlot().getId(),
                ut.getUser().getId(), ut.getUser().getName(), ut.isAvailable()))
                .collect(Collectors.toList());
        return new ResponseBody(true, null, rbUserTimes);
    }

//    private List<TimeSlot> getTimeSlots(String startTime, String endTime) {
//        List<TimeSlot> allts = timeSlotRepository.findAll();
//        List<TimeSlot> tss = allts.stream().filter(ts ->{
//            ts.get
//        })
//    }


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
        RbUserTime rut = new RbUserTime(Utils.DF_YYYYMMDD.format(ut.getDate()), ut.getTimeSlot().getId(), rqbUserTime.getUserId(), null, ut.isAvailable());
        return new ResponseBody(true, null, rut);
    }

    @Override
    public ResponseBody saveUser(RqbUser rqbUser) {
        User user = null;
        if (rqbUser.getId() <= 0){
            user = new User();
            user.setName(rqbUser.getName());
            user.setPassword(rqbUser.getPassword());
            user.setAvailable(true);
        } else {
            user = userRepository.findById(rqbUser.getId()).get();
            user.setName(rqbUser.getName());
            user.setAvailable(rqbUser.isAvailable());
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
}
