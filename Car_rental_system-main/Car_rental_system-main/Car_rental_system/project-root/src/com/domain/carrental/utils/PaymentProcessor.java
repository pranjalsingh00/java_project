package com.domain.carrental.utils;

public class PaymentProcessor {

    public static boolean processPayment(String cardNumber, String expiry, String cvv) {
        // Simulate payment validation
        return isValidCardNumber(cardNumber) && isValidExpiry(expiry) && isValidCVV(cvv);
    }

    private static boolean isValidCardNumber(String number) {
        return number.matches("^\\d{16}$"); // 16-digit card number
    }

    private static boolean isValidExpiry(String expiry) {
        return expiry.matches("^\\d{2}/\\d{2}$"); // Format MM/YY
    }

    private static boolean isValidCVV(String cvv) {
        return cvv.matches("^\\d{3}$"); // 3-digit CVV
    }
}
