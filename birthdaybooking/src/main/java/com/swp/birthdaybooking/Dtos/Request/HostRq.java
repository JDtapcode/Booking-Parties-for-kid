package com.swp.birthdaybooking.Dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HostRq {
    private Integer hostId;
    private String phone;
    private String name;
}
