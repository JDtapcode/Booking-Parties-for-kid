package com.swp.birthdaybooking.repositories;

import com.swp.birthdaybooking.entities.Account;
import com.swp.birthdaybooking.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByEmail(String email);

    List<Account> findAllByStatusAndRole(Boolean status, Role role);
}
