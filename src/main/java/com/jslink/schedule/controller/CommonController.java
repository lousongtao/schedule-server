package com.jslink.schedule.controller;

import com.jslink.schedule.bean.TimeSlot;
import com.jslink.schedule.responsebody.RbSchedule;
import com.jslink.schedule.responsebody.ResponseBody;
import com.jslink.schedule.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/common")
@Api(value = "common", tags = {"common"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class CommonController {
    @Autowired
    private CommonService commonService;

    @GetMapping("/timeslot")
    @ApiOperation(value = "Load time slot", response = List.class, httpMethod = "GET", authorizations = {@Authorization(value = "basicAuth")})
    public ResponseBody<List<TimeSlot>> queryTimeSlot(){
        return commonService.getTimeSlots();
    }
}
