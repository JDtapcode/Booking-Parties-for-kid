package com.swp.birthdaybooking.repositories;

import com.swp.birthdaybooking.entities.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokensRepository extends JpaRepository<Tokens, Integer> {
    @Query("""
            select t from Tokens t inner join Account u on t.account.userId = u.userId 
            where u.userId = :studentID and (t.expired = false or t.revoke = false)
            """)
    List<Tokens> findAllValidTokenByUser(@Param(value = "studentID") Integer userid);

    Optional<Tokens> findByToken(String token);
}
