package com.swp.birthdaybooking.mapper;

import com.swp.birthdaybooking.Dtos.Request.CreateServiceRq;
import com.swp.birthdaybooking.entities.Location;
import com.swp.birthdaybooking.entities.ServiceBirthday;
import com.swp.birthdaybooking.exception.NotFoundException;
import com.swp.birthdaybooking.repositories.LocationRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ServiceMapper {

    @Autowired
    protected LocationRepository locationRepository;

    @Mapping(source = "locationId", target = "location", qualifiedByName = "idToLocation")
    public abstract ServiceBirthday mapToService(CreateServiceRq createServiceRq);

    @Named("idToLocation")
    public Location idToLocation(Integer id) {
        if (id == null) {
            return null;
        }
        if(!locationRepository.existsById(id)){
            throw new NotFoundException("Location with id " + id + " not found");
        }
        return new Location(id);
    }
}