package com.swp.birthdaybooking.mapper;

import com.swp.birthdaybooking.Dtos.Request.CreateScheduleDetailRq;
import com.swp.birthdaybooking.Dtos.Request.CreateScheduleRq;
import com.swp.birthdaybooking.entities.*;
import com.swp.birthdaybooking.exception.NotFoundException;
import com.swp.birthdaybooking.repositories.HostRepository;
import com.swp.birthdaybooking.repositories.ScheduleRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ScheduleMapper {

    @Autowired
    protected ScheduleRepository scheduleRepository;
    @Autowired
    protected HostRepository hostRepository;

    @Mapping(source = "hostId", target = "host", qualifiedByName = "idToHost")
    @Mapping(source = "scheduleId", target = "schedule", qualifiedByName = "idToSchedule")
    public abstract ScheduleDetail mapToScheduleDetail(CreateScheduleDetailRq createScheduleDetail);

    public abstract Schedule mapToSchedule(CreateScheduleRq createScheduleRq);

    @Named("idToSchedule")
    public Schedule idToSchedule(Integer id) {
        if (id == null) {
            return null;
        }
        if(!scheduleRepository.existsById(id)){
            throw new NotFoundException("Schedule with id " + id + " not found");
        }
        return new Schedule(id);
    }

    @Named("idToHost")
    public Host idToHost(Integer id) {
        if (id == null) {
            return null;
        }
        if(!hostRepository.existsById(id)){
            throw new NotFoundException("Host with id " + id + " not found");
        }
        return new Host(id);
    }
}