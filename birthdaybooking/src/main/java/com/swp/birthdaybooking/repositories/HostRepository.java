package com.swp.birthdaybooking.repositories;

import com.swp.birthdaybooking.entities.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host, Integer> {

}
