package com.swp.birthdaybooking.services;

import com.swp.birthdaybooking.repositories.PackageRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceOfPackageService {
    private final PackageRepository repository;

    public ServiceOfPackageService(PackageRepository repository) {
        this.repository = repository;
    }

    public void resetPriceForPackage() {

    }
}