package com.swp.birthdaybooking.Controllers;

import com.swp.birthdaybooking.Dtos.Request.LoginRequest;
import com.swp.birthdaybooking.Dtos.Request.RegisterRequest;
import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/authenticate")
public class AuthenticationController {
    private final AuthenticationService services;

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(
            @RequestBody RegisterRequest request
    ) {
        return services.register(request);
    }

    @PostMapping("/register/host")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<ResponseObject> registerForHost(
            @RequestBody RegisterRequest request
    ) {
        return services.registerForHost(request);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(
            @RequestBody LoginRequest request
    ) {
        return services.login(request);
    }

}
