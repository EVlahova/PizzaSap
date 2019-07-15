package com.sap.pizza.converters;

import com.sap.pizza.enums.OrderStatus;

import javax.persistence.AttributeConverter;

public class OrderStatusConverter implements AttributeConverter<OrderStatus, String>  {
    @Override
    public String convertToDatabaseColumn(OrderStatus status) {
        return status.getKey();
    }

    @Override
    public OrderStatus convertToEntityAttribute(String s) {
        return OrderStatus.fromKey(s);
    }
}
