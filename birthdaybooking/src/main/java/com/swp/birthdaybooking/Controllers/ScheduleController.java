package com.swp.birthdaybooking.Controllers;

import com.swp.birthdaybooking.Dtos.Request.CreateScheduleRq;
import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.entities.Schedule;
import com.swp.birthdaybooking.services.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/schedule")
@Slf4j
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('host:read')")
    public ResponseEntity<ResponseObject> getSchedule() {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Schedules retrieved",
                        scheduleService.getAllSchedules()));

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('host:read')")
    public ResponseEntity<ResponseObject> getScheduleById(@PathVariable Integer id) {
        log.info("Schedule id: " + id);
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Schedule retrieved",
                        scheduleService.getScheduleById(id)));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('host:create')")
    public ResponseEntity<ResponseObject> createSchedule(@RequestBody CreateScheduleRq schedule) {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Schedule created",
                        scheduleService.createSchedule(schedule)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('host:update')")
    public ResponseEntity<ResponseObject> updateSchedule(@RequestBody CreateScheduleRq schedule, @PathVariable Integer id) {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Schedule updated",
                        scheduleService.updateSchedule(schedule,id)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('host:delete')")
    public ResponseEntity<ResponseObject> deleteSchedule(@PathVariable Integer id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Schedule deleted",
                        null));
    }
}
