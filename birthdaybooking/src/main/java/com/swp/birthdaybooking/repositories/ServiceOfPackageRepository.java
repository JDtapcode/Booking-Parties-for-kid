package com.swp.birthdaybooking.repositories;

import com.swp.birthdaybooking.entities.ServiceOfPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceOfPackageRepository extends JpaRepository<ServiceOfPackage, Integer> {

}
