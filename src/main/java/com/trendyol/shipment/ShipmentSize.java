package com.trendyol.shipment;

public enum ShipmentSize {

    SMALL,
    MEDIUM,
    LARGE,
    X_LARGE;

    public ShipmentSize incrementAndGet() {
        if (this == X_LARGE) {
            return X_LARGE;
        }
        return ShipmentSize.values()[this.ordinal() + 1];
    }
}