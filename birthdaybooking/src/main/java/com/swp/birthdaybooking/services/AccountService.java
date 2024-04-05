package com.swp.birthdaybooking.services;

import com.swp.birthdaybooking.Dtos.Request.DeleteUserRequest;
import com.swp.birthdaybooking.Dtos.Request.UpdateUserRequest;
import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.enums.Role;
import com.swp.birthdaybooking.repositories.AccountRepository;
import com.swp.birthdaybooking.repositories.GuestRepository;
import com.swp.birthdaybooking.repositories.HostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final GuestRepository guestRepository;
    private final HostRepository hostRepository;

    public ResponseEntity<ResponseObject> getAllUser() {
        try {
            var list = accountRepository.findAllByStatusAndRole(true, Role.USER);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Found users", list));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Couldn't find users", null));
        }
    }


    public ResponseEntity<ResponseObject> getAllHost() {
        try {
            var list = accountRepository.findAllByStatusAndRole(true, Role.HOST);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Found users", list));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Couldn't find users", null));
        }
    }

    public ResponseEntity<ResponseObject> updateUser(UpdateUserRequest obj) {
        try {
            var guest =  guestRepository.findById(obj.getPersonId()).orElse(null);;
            var host  =  hostRepository.findById(obj.getPersonId()).orElse(null);
            if(guest != null) {
                guest.setName(obj.getName());
                guest.setGender(obj.getGender());
                guest.setPhone(obj.getPhone());
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Updated user", guestRepository.save(guest)));
            }
            if(host != null) {
                host.setName(obj.getName());
                host.setGender(obj.getGender());
                host.setPhone(obj.getPhone());
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Updated user", hostRepository.save(host)));
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Failed", "Couldn't find users", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Couldn't find users", null));
        }
    }

    public ResponseEntity<ResponseObject> deleteAccount(DeleteUserRequest obj) {
        try {
            var account = accountRepository.findById(obj.getAccountId()).orElse(null);
            if(account != null) {
                account.setStatus(obj.getStatus());
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Delete user", accountRepository.save(account)));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Couldn't find user", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Couldn't find user", null));
        }
    }

//    public ResponseEntity<ResponseObject> updateUser(UpdateUserRequest obj) {
//
//    }
}
