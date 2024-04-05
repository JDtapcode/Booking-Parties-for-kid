package com.swp.birthdaybooking.Controllers;

import com.swp.birthdaybooking.Dtos.Request.CreateCartRq;
import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<ResponseObject> getAllUser() {
        return cartService.getAll();
    }

    @PostMapping("/add-package-to-cart")
    public ResponseEntity<ResponseObject> addPackageToCart(@RequestBody CreateCartRq createCartRq) {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Cart retrieved",
                        cartService.addPackageToCart(createCartRq)));
    }


}
