package com.swp.birthdaybooking.services;

import com.swp.birthdaybooking.Dtos.Request.CreateFeedBackRequest;
import com.swp.birthdaybooking.Dtos.Request.UpdateFeedBackRequest;
import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.entities.Feedback;
import com.swp.birthdaybooking.repositories.FeedbackRepository;
import com.swp.birthdaybooking.repositories.GuestRepository;
import com.swp.birthdaybooking.repositories.ServiceBirthdayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.swp.birthdaybooking.entities.Feedback;
import com.swp.birthdaybooking.exception.NotFoundException;
import com.swp.birthdaybooking.repositories.FeedbackRepository;
import com.swp.birthdaybooking.repositories.ServiceBirthdayRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final ServiceBirthdayRepository serviceBirthdayRepository;
    private final GuestRepository guestRepository;

    public ResponseEntity<ResponseObject> createFeedback(CreateFeedBackRequest obj) {
        try {
            Feedback feedback = Feedback.builder()
                    .guest(guestRepository.findById(obj.getGuestId()).orElse(null))
                    .serviceBirthday(serviceBirthdayRepository.findByServiceId(obj.getServiceId()).orElse(null))
                    .description(obj.getDescription())
                    .feedbackDate(new Date())
                    .updatedDate(new Date())
                    .build();

            Feedback savedFeedback = feedbackRepository.save(feedback);
            if (savedFeedback == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Feedback haven't been created", null));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject("Success", "Feedback have been created", savedFeedback));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Feedback haven't been created", null));
        }
    }

    public ResponseEntity<ResponseObject> updateFeedback(UpdateFeedBackRequest obj) {
        try {
            Feedback feedback = feedbackRepository.findById(obj.getFeedbackId()).orElse(null);
            if (feedback == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("Failed", "Couldn't find feedback", null));
            }
            feedback.setDescription(obj.getDescription());
            feedback.setUpdatedDate(new Date());
            Feedback savedFeedback = feedbackRepository.save(feedback);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Success", "Feedback have been updated", savedFeedback));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Feedback haven't been updated", null));
        }
    }
    /**
     * View feedback
     */
    public List<Feedback> getByServiceId(int serviceId) {
        var isServiceExist = serviceBirthdayRepository.existsById(serviceId);
        if(!isServiceExist) throw new NotFoundException("Can not found service with id: " + serviceId + "!");

        var feedbacks = feedbackRepository
                .findAllByServiceId(serviceId);
        return feedbacks.orElseGet(List::of);
    }
}
