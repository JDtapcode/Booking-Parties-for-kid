package com.swp.birthdaybooking.Controllers;

import com.swp.birthdaybooking.Dtos.Request.GuestRq;
import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.services.GuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/guest")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:update')")
    public ResponseEntity<ResponseObject> editProfile(@RequestBody GuestRq guestRq) {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Guest profile updated",
                        guestService.editProfile(guestRq)
                ));

    }
}
