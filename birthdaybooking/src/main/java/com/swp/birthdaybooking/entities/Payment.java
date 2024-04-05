package com.swp.birthdaybooking.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private Integer paymentId;

    @ManyToOne
    @JoinColumn(name = "bill_id", referencedColumnName = "bill_id")
    private Bill bill;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "payment_time")
    private Date paymentTime;

    @Column(name = "method", length = 250)
    private String method;
}
