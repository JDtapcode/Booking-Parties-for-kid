package com.swp.birthdaybooking.services;

import com.swp.birthdaybooking.Dtos.Request.CreateScheduleDetailRq;
import com.swp.birthdaybooking.entities.ScheduleDetail;
import com.swp.birthdaybooking.mapper.ScheduleMapper;
import com.swp.birthdaybooking.repositories.ScheduleDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleDetailService extends BaseService<ScheduleDetail,Integer>{

    private final ScheduleMapper scheduleMapper;
    private final ScheduleDetailRepository scheduleDetailRepository;

    public ScheduleDetailService(ScheduleMapper scheduleMapper, ScheduleDetailRepository scheduleDetailRepository) {
        super(scheduleDetailRepository);
        this.scheduleMapper = scheduleMapper;
        this.scheduleDetailRepository = scheduleDetailRepository;
    }

    public List<ScheduleDetail> getAllScheduleDetails() {
        return scheduleDetailRepository.findAll();
    }

    public ScheduleDetail getScheduleDetailById(Integer id) {
        return getById(id);
    }

    public ScheduleDetail createScheduleDetail(CreateScheduleDetailRq scheduleDetail) {
        return scheduleDetailRepository.save(scheduleMapper.mapToScheduleDetail(scheduleDetail));
    }

    public ScheduleDetail updateScheduleDetail(ScheduleDetail scheduleDetail) {
        return isIdExist(scheduleDetail.getScheduleDetailId())
                .save(scheduleDetail);
    }
}