package com.jslink.schedule.controller;

import com.jslink.schedule.responsebody.RbSchedule;
import com.jslink.schedule.responsebody.ResponseBody;
import com.jslink.schedule.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RequestMapping("/schedule")
@RestController
@Api(value = "schedule", tags = {"schedule"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class ScheduleController {
    private final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/byday")
    @ApiOperation(value = "Load schedule", response = List.class, httpMethod = "GET", authorizations = {@Authorization(value = "basicAuth")})
    public ResponseBody<List<RbSchedule>> queryScheduleByDayRange(@ApiParam(name = "startDate", value = "start date")
                                          @RequestParam(name = "startDate") Date startDate,
                                          @ApiParam(name = "endDate", value = "end date")
                                          @RequestParam(name = "endDate") Date endDate){
        return scheduleService.queryScheduleByDayRange(startDate, endDate);
    }

    @GetMapping("/bymonth/{month}")
    @ApiOperation(value = "Load schedule", response = List.class, httpMethod = "GET", authorizations = {@Authorization(value = "basicAuth")})
    public List<RbSchedule> queryScheduleByMonth(@ApiParam(name = "month", value = "month(value from 1 to 12)")
                                                    @RequestParam(name = "month") int month){
        return scheduleService.queryScheduleByMonth(month);
    }

    @PostMapping("/arrangeschedule")
    @ApiOperation(value = "Arrange schedule", response = List.class, httpMethod = "POST", authorizations = {@Authorization(value = "basicAuth")})
    public ResponseBody arrangeSchedule(
            @ApiParam(name = "date", value = "date")
            @RequestParam(name = "date") Date date,
            @ApiParam(name = "timeSlotId", value = "time slot id")
            @RequestParam(name = "timeSlotId") int timeSlotId,
            @ApiParam(name = "userId", value = "user id")
            @RequestParam(name = "userId") int userId){
        return scheduleService.arrangeSchedule(date, timeSlotId, userId);
    }

    @DeleteMapping("/arrangeschedule")
    @ApiOperation(value = "Arrange schedule", response = List.class, httpMethod = "DELETE", authorizations = {@Authorization(value = "basicAuth")})
    public ResponseBody deleteSchedule(
            @ApiParam(name = "date", value = "date")
            @RequestParam(name = "date") Date date,
            @ApiParam(name = "timeSlotId", value = "time slot id")
            @RequestParam(name = "timeSlotId") int timeSlotId,
            @ApiParam(name = "userId", value = "user id")
            @RequestParam(name = "userId") int userId){
        return scheduleService.deleteSchedule(date, timeSlotId, userId);
    }
}
