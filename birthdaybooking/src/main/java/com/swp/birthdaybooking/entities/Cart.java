package com.swp.birthdaybooking.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Cart")
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_id")
    private int cartId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "guest_id", referencedColumnName = "guest_id")
    private Guest guest;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "package_id", referencedColumnName = "package_id")
    private Package cartPackage;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ServiceDetail> serviceDetailList;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Bill> billList;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total_price")
    private float totalPrice;
}
