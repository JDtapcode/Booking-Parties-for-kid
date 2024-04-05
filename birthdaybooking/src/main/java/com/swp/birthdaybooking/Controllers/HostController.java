package com.swp.birthdaybooking.Controllers;

import com.swp.birthdaybooking.Dtos.Request.HostRq;
import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.services.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/host")
public class HostController {

    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('host:update')")
    public ResponseEntity<ResponseObject> editProfile(@RequestBody HostRq hostRq) {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Host profile updated",
                        hostService.editProfile(hostRq)));
    }

}
