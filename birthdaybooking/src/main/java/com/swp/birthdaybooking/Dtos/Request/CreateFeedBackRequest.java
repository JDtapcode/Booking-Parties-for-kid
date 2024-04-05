package com.swp.birthdaybooking.Dtos.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateFeedBackRequest {
    private Integer serviceId;
    private Integer guestId;
    private String description;
}
