package com.swp.birthdaybooking.Controllers;

import com.swp.birthdaybooking.Dtos.Request.CreateServiceRq;
import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.services.ServiceBirthdayService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/service")
public class ServiceController {
    private final ServiceBirthdayService serviceBirthdayService;

    public ServiceController(ServiceBirthdayService serviceBirthdayService) {
        this.serviceBirthdayService = serviceBirthdayService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('host:create')")
    public ResponseEntity<ResponseObject> createService(@RequestBody CreateServiceRq createServiceRq) {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Service created",
                        serviceBirthdayService.createService(createServiceRq)));
    }

    @PostMapping(
            value = "/uploads",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    @PreAuthorize("hasAuthority('host:create')")
    public ResponseEntity<ResponseObject> uploadImage(@RequestParam("file") MultipartFile file) {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Image uploaded",
                        serviceBirthdayService.upload(file).get("secure_url")));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('host:read')")
    public ResponseEntity<ResponseObject> getById(@PathVariable Integer id) {
        return serviceBirthdayService.getServiceDetails(id);
    }
}
