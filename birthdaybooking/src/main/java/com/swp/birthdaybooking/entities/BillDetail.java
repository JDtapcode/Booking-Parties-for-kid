package com.swp.birthdaybooking.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Bill_Detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bill_detail_id")
    private Integer billDetailId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "bill_id", referencedColumnName = "bill_id")
    private Bill bill;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "guest_id", referencedColumnName = "guest_id")
    private Guest guest;

    @Column(name = "total_price")
    private Float totalPrice;
}
