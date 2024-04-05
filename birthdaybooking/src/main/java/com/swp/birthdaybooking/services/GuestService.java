package com.swp.birthdaybooking.services;

import com.swp.birthdaybooking.Dtos.Request.GuestRq;
import com.swp.birthdaybooking.entities.Guest;
import com.swp.birthdaybooking.mapper.GuestMapper;
import com.swp.birthdaybooking.repositories.GuestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GuestService {
    private final GuestRepository guestRepository;
    private final GuestMapper guestMapper;

    public GuestService(GuestRepository guestRepository, GuestMapper guestMapper) {
        this.guestRepository = guestRepository;
        this.guestMapper = guestMapper;
    }

    public Guest editProfile(GuestRq guestRq) {
        var guestOpl = guestRepository.findById(guestRq.getGuestId());
        if (guestOpl.isEmpty()) {
            throw new RuntimeException("Guest not found");
        }
        var guest =  guestOpl.get();
        guestMapper.updateFromRq(guestRq, guest);

        log.info("GuestRq: {}", guestRq);
        log.info("Guest: {}", guest);
        return guestRepository.save(guest);
    }
}