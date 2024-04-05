package com.swp.birthdaybooking.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ServiceOfPackage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceOfPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "service_of_package_id")
    private int serviceOfPackageId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "service_id", referencedColumnName = "service_id")
    private ServiceBirthday serviceBirthday;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "package_id", referencedColumnName = "package_id")
    private Package servicePackage;
}
