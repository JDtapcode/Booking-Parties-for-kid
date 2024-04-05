package com.swp.birthdaybooking.Dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFeedBackRequest {
    private Integer feedbackId;
    private String description;
}
