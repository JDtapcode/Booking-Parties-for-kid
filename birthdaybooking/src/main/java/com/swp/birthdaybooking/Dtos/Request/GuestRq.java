package com.swp.birthdaybooking.Dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestRq {
    private Integer guestId;
    private String phone;
    private String name;
    private String gender;
}
