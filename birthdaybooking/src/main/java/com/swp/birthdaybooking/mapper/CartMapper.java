package com.swp.birthdaybooking.mapper;

import com.swp.birthdaybooking.Dtos.Request.CreateCartRq;
import com.swp.birthdaybooking.entities.Cart;
import com.swp.birthdaybooking.entities.Guest;
import com.swp.birthdaybooking.entities.Package;
import com.swp.birthdaybooking.exception.NotFoundException;
import com.swp.birthdaybooking.repositories.PackageRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CartMapper {
    @Autowired
    private PackageRepository packageRepository;

    @Mapping(source = "guestId", target = "guest", qualifiedByName = "idToGuest")
    @Mapping(source = "packageId", target = "cartPackage", qualifiedByName = "idToPackage")
    public abstract Cart mapToCart(CreateCartRq cartRq);

    @Named("idToPackage")
    public Package idToPackage(Integer id) {
        if (id == null) {
            return null;
        }
        if (!packageRepository.existsById(id)) {
            throw new NotFoundException("Package with id " + id + " not found");
        }
        return new Package(id);
    }

    @Named("idToGuest")
    public Guest idToGuest(Integer id) {
        if (id == null) {
            return null;
        }
        if (!packageRepository.existsById(id)) {
            throw new NotFoundException("Guest with id " + id + " not found");
        }
        return new Guest(id);
    }
}
