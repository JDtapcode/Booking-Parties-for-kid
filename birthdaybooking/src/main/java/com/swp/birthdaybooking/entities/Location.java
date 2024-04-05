package com.swp.birthdaybooking.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Location")
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "location_id")
    private Integer locationId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "host_id", referencedColumnName = "host_id")
    private Host host;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Package> packageList;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<ServiceBirthday> serviceBirthdays;

    @Column(name = "address", length = 250)
    private String address;

    @Column(name = "name", length = 250)
    private String name;

    public Location(Integer locationId) {
        this.locationId = locationId;
    }
}
