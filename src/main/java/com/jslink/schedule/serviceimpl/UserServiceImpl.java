package com.jslink.schedule.serviceimpl;

import com.jslink.schedule.bean.UserTime;
import com.jslink.schedule.bean.TimeSlot;
import com.jslink.schedule.bean.User;
import com.jslink.schedule.responsebody.ResponseBody;
import com.jslink.schedule.repository.TimeSlotRepository;
import com.jslink.schedule.repository.UserRepository;
import com.jslink.schedule.repository.UserTimeRepository;
import com.jslink.schedule.requestbody.RqbUser;
import com.jslink.schedule.requestbody.RqbUserTime;
import com.jslink.schedule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
    public ResponseBody<List<UserTime>> queryUserTime(int userId, Date date1, Date date2) {
        List<UserTime> userTimes = userTimeRepository.findByUserAndDateRange(userId, date1, date2);
        return new ResponseBody(true, null, userTimes);
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
    public ResponseBody saveUserTime(RqbUserTime rqbUserTime) {
        User user = userRepository.findById(rqbUserTime.getUserId()).get();
        TimeSlot timeSlot = timeSlotRepository.findById(rqbUserTime.getTimeSlotId()).get();
        UserTime ut = null;
        if (rqbUserTime.getId() > 0) {
            ut = userTimeRepository.findById(rqbUserTime.getId()).get();
        } else {
            ut = new UserTime();
            ut.setUser(user);
            ut.setTimeSlot(timeSlot);
            ut.setDate(rqbUserTime.getDate());
        }
        ut.setAvailable(rqbUserTime.isAvailable());
        ut = userTimeRepository.save(ut);
        return new ResponseBody(true);
    }

    @Override
    public ResponseBody saveUser(RqbUser rqbUser) {
        User user = null;
        if (rqbUser.getId() <= 0){
            user = new User();
            user.setName(rqbUser.getName());
            user.setPassword(rqbUser.getPassword());
        } else {
            user = userRepository.findById(rqbUser.getId()).get();
        }
        user = userRepository.save(user);
        return new ResponseBody(true);
    }

    @Override
    public ResponseBody queryUsers() {
        List<User> users = userRepository.findAll();
        return new ResponseBody(true, null, users);
    }
}
