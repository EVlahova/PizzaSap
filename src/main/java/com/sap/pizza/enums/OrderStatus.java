package com.sap.pizza.enums;

public enum  OrderStatus {

    CREATED("created"),
    IN_PROGRESS("in_progress"),
    DONE("done");

    private String key;

    OrderStatus(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static OrderStatus fromKey(String key) {
        switch (key) {
            case "created":
                return OrderStatus.CREATED;

            case "in_progress":
                return OrderStatus.IN_PROGRESS;

            case "done":
                return OrderStatus.DONE;

            default:
                throw new IllegalArgumentException("Key [" + key + "] not supported.");
        }
    }

}
