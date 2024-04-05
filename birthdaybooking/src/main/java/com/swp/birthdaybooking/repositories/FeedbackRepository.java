package com.swp.birthdaybooking.repositories;

import com.swp.birthdaybooking.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    @Query("""
                select f from Feedback f
                where f.serviceBirthday.serviceId = :serviceId
            """)
    Optional<List<Feedback>> findAllByServiceId(int serviceId);
}
