package com.swp.birthdaybooking.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Table(name = "Feedback")
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "feedback_id")
    private int feedbackId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "service_id", referencedColumnName = "service_id")
    private ServiceBirthday serviceBirthday;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "guest_id", referencedColumnName = "guest_id")
    private Guest guest;

    @Column(name = "description", length = 250)
    private String description;

    @Column(name = "feedback_date")
    private Date feedbackDate;

    @Column(name = "edit_feedback_date")
    private Date updatedDate;
}
