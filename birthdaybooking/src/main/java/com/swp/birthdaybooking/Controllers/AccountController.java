package com.swp.birthdaybooking.Controllers;

import com.swp.birthdaybooking.Dtos.Request.DeleteUserRequest;
import com.swp.birthdaybooking.Dtos.Request.UpdateUserRequest;
import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/account")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/getAllUser")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<ResponseObject> getAllUser() {
        return accountService.getAllUser();
    }

    @GetMapping("/getAllHost")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<ResponseObject> getAllHost() {
        return accountService.getAllHost();
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<ResponseObject> updateUser(@RequestBody UpdateUserRequest obj) {
        return accountService.updateUser(obj);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<ResponseObject> deleteUser(@RequestBody DeleteUserRequest obj) {
        return accountService.deleteAccount(obj);
    }
}
