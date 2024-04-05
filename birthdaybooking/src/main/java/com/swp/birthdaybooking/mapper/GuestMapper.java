package com.swp.birthdaybooking.mapper;

import com.swp.birthdaybooking.Dtos.Request.GuestRq;
import com.swp.birthdaybooking.entities.Guest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface GuestMapper {

    @Mapping(target = "guestId", ignore = true)
    void updateFromRq(GuestRq guestRq, @MappingTarget Guest guest);
}
