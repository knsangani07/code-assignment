package com.epam.error.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class OrderStatusValidator implements ConstraintValidator<OrderStatus, String> {

    List<String> orderStatus = Arrays.asList("Pending", "Payment Complete", "Complete", "Shipping", "Complete", "Unfulfillable");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return orderStatus.contains(value);

    }
}
