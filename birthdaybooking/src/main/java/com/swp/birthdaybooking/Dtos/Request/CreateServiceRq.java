package com.swp.birthdaybooking.Dtos.Request;

public record CreateServiceRq(String name, boolean status, float price, String description, Integer locationId,String imageUrl) {

}
