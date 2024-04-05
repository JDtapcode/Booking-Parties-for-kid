package com.swp.birthdaybooking.repositories;

import com.swp.birthdaybooking.entities.ServiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDetailRepository extends JpaRepository<ServiceDetail, Integer> {

}
