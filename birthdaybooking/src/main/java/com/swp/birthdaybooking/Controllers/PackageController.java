package com.swp.birthdaybooking.Controllers;

import com.swp.birthdaybooking.Dtos.Request.PackageRequest;
import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.services.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/package")
public class PackageController {
    private final PackageService packageService;

    @GetMapping("/parties")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ResponseObject> getParties() {
        return packageService.getParitiesOption();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ResponseObject> getPackages() {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Found packages",
                        packageService.getPackages()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ResponseObject> getPackageById(@PathVariable int id) {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Found packages",
                        packageService.getPackageById(id)));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('host:create')")
    public ResponseEntity<ResponseObject> createPackage(@RequestBody PackageRequest packageRequest) {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Found packages",
                        packageService.createPackage(packageRequest)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('host:update')")
    public ResponseEntity<ResponseObject> updatePackage(@PathVariable int id, @RequestBody PackageRequest packageRequest) {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Found packages",
                        packageService.updatePackage(id, packageRequest)));
    }

}
