package com.swp.birthdaybooking.services;

import com.swp.birthdaybooking.Dtos.Request.CreateScheduleRq;
import com.swp.birthdaybooking.entities.Schedule;
import com.swp.birthdaybooking.mapper.ScheduleMapper;
import com.swp.birthdaybooking.repositories.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService extends BaseService<Schedule, Integer> {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    public ScheduleService(ScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper) {
        super(scheduleRepository);
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Schedule getScheduleById(Integer id) {
        return getById(id);
    }

    public Schedule createSchedule(CreateScheduleRq schedule) {
        return scheduleRepository.save(scheduleMapper.mapToSchedule(schedule));
    }

    public Schedule updateSchedule(CreateScheduleRq scheduleRq, Integer id) {
        var schedule = scheduleMapper.mapToSchedule(scheduleRq);
        schedule.setScheduleId(id);
        return isIdExist(id).save(schedule);
    }

    public void deleteSchedule(Integer id) {
        scheduleRepository.deleteById(id);
    }
}