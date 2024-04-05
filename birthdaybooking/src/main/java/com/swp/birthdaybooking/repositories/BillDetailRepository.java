package com.swp.birthdaybooking.repositories;

import com.swp.birthdaybooking.entities.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Integer> {

}
