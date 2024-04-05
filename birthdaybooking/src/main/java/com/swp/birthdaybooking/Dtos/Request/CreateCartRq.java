package com.swp.birthdaybooking.Dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCartRq {
    private Integer guestId;
    private Integer packageId;
    private Integer quantity;
    private Integer price;
}


