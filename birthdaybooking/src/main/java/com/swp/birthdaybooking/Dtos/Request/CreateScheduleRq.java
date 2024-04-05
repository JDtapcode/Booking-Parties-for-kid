package com.swp.birthdaybooking.Dtos.Request;

import java.util.Date;

public record CreateScheduleRq(Date date, Boolean busy){
}
