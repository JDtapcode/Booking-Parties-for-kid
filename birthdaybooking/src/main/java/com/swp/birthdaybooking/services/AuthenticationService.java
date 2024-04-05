package com.swp.birthdaybooking.services;

import com.swp.birthdaybooking.Dtos.Request.LoginRequest;
import com.swp.birthdaybooking.Dtos.Request.RegisterRequest;
import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.entities.*;
import com.swp.birthdaybooking.enums.Role;
import com.swp.birthdaybooking.enums.TokenType;
import com.swp.birthdaybooking.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokensRepository tokensRepo;
    private final GuestRepository guestRepository;
    private final HostRepository hostRepository;
    private final LocationRepository locationRepository;
    private final AccountRepository accountRepository;

    public ResponseEntity<ResponseObject> register(RegisterRequest dto) {
        try {
            Account emailChecker = accountRepository.findByEmail(dto.getEmail()).orElse(null);
            if(!isValidEmail(dto.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Email is invalid", null));
            }
            if(emailChecker != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Email is already exist", null));
            }

            Account account = Account.builder()
                    .email(dto.getEmail())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .role(Role.USER)
                    .status(true)
                    .build();
            var savedUser = accountRepository.save(account);

            Guest guest = Guest.builder()
                    .phone(dto.getPhone())
                    .gender(dto.getGender())
                    .name(dto.getName())
                    .account(savedUser)
                    .build();

            var savedGuest = guestRepository.save(guest);
            var jwtToken = jwtService.generateToken(account);
            SavedUserToken(jwtToken, savedUser);
            return ResponseEntity.ok().body(new ResponseObject("Successful", "Created",jwtToken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Register failed", null));
        }

    }

    public ResponseEntity<ResponseObject> registerForHost(RegisterRequest dto) {
        try {
            Account emailChecker = accountRepository.findByEmail(dto.getEmail()).orElse(null);
            if(!isValidEmail(dto.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Email is invalid", null));
            }
            if(emailChecker != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Email is already exist", null));
            }

            Account account = Account.builder()
                    .email(dto.getEmail())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .role(Role.HOST)
                    .status(true)
                    .build();
            var savedUser = accountRepository.save(account);

            Host host = Host.builder()
                    .phone(dto.getPhone())
                    .gender(dto.getGender())
                    .name(dto.getName())
                    .account(account)
                    .build();

            hostRepository.save(host);
            var jwtToken = jwtService.generateToken(account);
            SavedUserToken(jwtToken, savedUser);
            return ResponseEntity.ok().body(new ResponseObject("Successful", "Created", host));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Register failed", null));
        }

    }

    public ResponseEntity<ResponseObject> login(LoginRequest request) {
        try {
            var authenticatedUser = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            var user = accountRepository.findByEmail(request.getEmail()).orElseThrow();
            if(!user.getStatus()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Account has been blocked", null));
            }
            var jwtToken = jwtService.generateToken(user);
            revokeAllStudentToken(user);
            SavedUserToken(jwtToken, user);
            return ResponseEntity.ok().body(new ResponseObject("Successful", "Jwt token: " + jwtToken, user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Login failed", null));

        }

    }

    private boolean isValidEmail(String email) {
        // Regular expression for Gmail email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@gmail\\.com$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void revokeAllStudentToken(Account account) {
        var validToken = tokensRepo.findAllValidTokenByUser(account.getUserId());
        if(validToken.isEmpty()) return;
        validToken.forEach(t -> {
            t.setExpired(true);
            t.setRevoke(true);
        });
        tokensRepo.saveAll(validToken);
    }

    private void SavedUserToken(String jwtToken, Account savedUser) {
        Tokens token = Tokens.builder()
                .token(jwtToken)
                .account(savedUser)
                .tokenType(TokenType.BEARER)
                .revoke(false)
                .expired(false)
                .build();
        tokensRepo.save(token);
    }
}
