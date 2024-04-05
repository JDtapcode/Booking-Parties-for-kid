package com.swp.birthdaybooking.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Service_Detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "service_detail_id")
    private int serviceDetailId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private Cart cart;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "service_id", referencedColumnName = "service_id")
    private ServiceBirthday serviceBirthday;
}
