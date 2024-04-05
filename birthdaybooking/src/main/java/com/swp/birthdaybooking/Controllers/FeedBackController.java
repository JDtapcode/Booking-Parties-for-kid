package com.swp.birthdaybooking.Controllers;

import com.swp.birthdaybooking.Dtos.Request.CreateFeedBackRequest;
import com.swp.birthdaybooking.Dtos.Request.UpdateFeedBackRequest;
import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.services.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/feedback")
public class FeedBackController {
    private final FeedbackService feedbackService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ResponseObject> createFeature(@RequestBody CreateFeedBackRequest obj) {
        return feedbackService.createFeedback(obj);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ResponseObject> updateFeature(@RequestBody UpdateFeedBackRequest obj) {
        return feedbackService.updateFeedback(obj);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('host:read')")
    public ResponseEntity<ResponseObject> getByServiceId(int serviceId) {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Feedback retrieved",
                        feedbackService.getByServiceId(serviceId)));
    }
}
