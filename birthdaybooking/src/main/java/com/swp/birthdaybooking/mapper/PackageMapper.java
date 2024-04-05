package com.swp.birthdaybooking.mapper;

import com.swp.birthdaybooking.Dtos.Request.PackageRequest;
import com.swp.birthdaybooking.entities.Location;
import com.swp.birthdaybooking.entities.Package;
import com.swp.birthdaybooking.exception.NotFoundException;
import com.swp.birthdaybooking.repositories.LocationRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class PackageMapper {

    @Autowired
    protected LocationRepository locationRepository;

    @Mapping(source = "locationId", target = "location", qualifiedByName = "idToLocation")
    public abstract Package mapToPackage(PackageRequest packageRequest);

    @Mapping(source = "locationId", target = "location", qualifiedByName = "idToLocation")
    @Mapping(target = "packageId", ignore = true)
    public abstract void mapToPackage(PackageRequest packageRequest,@MappingTarget Package pack);

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
