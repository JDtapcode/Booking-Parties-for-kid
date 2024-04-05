package com.swp.birthdaybooking.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Schedule_id")
    private Integer scheduleId;

    @Column(name = "date")
    private Date date;

    @Column(name = "busy")
    private Boolean busy;

    @OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ScheduleDetail> scheduleDetailList;

    public Schedule(int scheduleId) {
        this.scheduleId = scheduleId;
    }
}
