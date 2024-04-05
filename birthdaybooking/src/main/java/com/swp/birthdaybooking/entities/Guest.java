package com.swp.birthdaybooking.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity()
@Table(name = "Guest")
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "guest_id")
    private Integer guestId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private Account account;

    @OneToMany(mappedBy = "guest", fetch = FetchType.LAZY)
    @JsonManagedReference
    @ToString.Exclude
    private List<Cart> cartList;

    @OneToMany(mappedBy = "guest", fetch = FetchType.LAZY)
    @JsonManagedReference
    @ToString.Exclude
    private List<Feedback> feedbackList;

    @Column(name = "phone", length = 250)
    private String phone;

    @Column(name = "name", length = 250)
    private String name;

    @Column(name = "gender", length = 250)
    private String gender;

    public Guest(Integer guestId) {
        this.guestId = guestId;
    }
}

