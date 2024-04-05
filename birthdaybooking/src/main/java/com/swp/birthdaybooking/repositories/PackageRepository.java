package com.swp.birthdaybooking.repositories;

import com.swp.birthdaybooking.entities.Package;
import com.swp.birthdaybooking.entities.ServiceOfPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PackageRepository extends JpaRepository<Package, Integer> {

    @Query("""
                select sop from
                Package pack 
                left join ServiceOfPackage sop on pack.packageId = sop.servicePackage.packageId
                left join ServiceBirthday sb on sb.serviceId = sop.serviceBirthday.serviceId
                where pack.status = true and sb.status = true
            """)
    List<ServiceOfPackage> getPartiesOption();

    @Query("""
                select p from Package p
                left join ServiceOfPackage sop on p.packageId = sop.servicePackage.packageId
                left join ServiceBirthday sb on sb.serviceId = sop.serviceBirthday.serviceId
                where sb.serviceId = :serviceId
            """)
    Optional<List<Package>> findAllByServiceId(int serviceId);

    @Query("""
                select sum(sb.price) from Package p
                left join ServiceOfPackage sop on p.packageId = sop.servicePackage.packageId
                left join ServiceBirthday sb on sb.serviceId = sop.serviceBirthday.serviceId
                where p.packageId = :packageId
            """)
    Long getTotalPricesServiceByPackageId(int packageId);
}
