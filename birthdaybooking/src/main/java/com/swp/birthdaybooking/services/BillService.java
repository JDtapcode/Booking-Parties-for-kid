package com.swp.birthdaybooking.services;

import com.swp.birthdaybooking.Dtos.Request.CreateBillRq;
import com.swp.birthdaybooking.entities.Bill;
import com.swp.birthdaybooking.entities.BillDetail;
import com.swp.birthdaybooking.entities.Payment;
import com.swp.birthdaybooking.repositories.BillDetailRepository;
import com.swp.birthdaybooking.repositories.BillRepository;
import com.swp.birthdaybooking.repositories.PackageRepository;
import com.swp.birthdaybooking.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BillService {
    private final BillRepository billRepository;
    private final BillDetailRepository billDetailRepository;
    private final CartService cartService;
    private final PackageRepository packageRepository;
    private final PaymentRepository paymentRepository;

    public BillService(BillRepository billRepository, BillDetailRepository billDetailRepository, CartService cartService, PackageRepository packageRepository, PaymentRepository paymentRepository) {
        this.billRepository = billRepository;
        this.billDetailRepository = billDetailRepository;
        this.cartService = cartService;
        this.packageRepository = packageRepository;
        this.paymentRepository = paymentRepository;
    }

    public Bill createBill(CreateBillRq billRq) {
        // get packages from cart
        var cart = cartService.getCartById(billRq.getCartId());
        var prices = packageRepository.getTotalPricesServiceByPackageId(cart.getCartId());
        var billSaved = Bill.builder().cart(cart).build();
        billRepository.save(billSaved);

        var payment = Payment.builder()
                .paymentDate(new Date())
                .paymentTime(new Date())
                .bill(billSaved)
                .method(billRq.getPaymentMethod())
                .build();
        paymentRepository.save(payment);

        var billDetail = BillDetail.builder()
                .totalPrice(prices.floatValue())
                .bill(billSaved)
                .guest(cart.getGuest())
                .build();

        billDetailRepository.save(billDetail);
        return billSaved;
    }
}
