package com.swp.birthdaybooking.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Schedule_Detail")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ScheduleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "schedule_Detail_id")
    private Integer scheduleDetailId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "schedule_id", referencedColumnName = "Schedule_id")
    private Schedule schedule;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "host_id", referencedColumnName = "host_id")
    private Host host;
}
