package com.trendyol.shipment;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Basket {
    private static final int THRESHOLD = 3;

    private List<Product> products;

    public ShipmentSize getShipmentSize() {
        if (isProductsEmpty()) {
            return null;
        }

        if (getProducts().size() < THRESHOLD) {
            return getLargestSize();
        }

        Map.Entry<ShipmentSize, Long> thresholdExceededEntry = sizeCountMap().entrySet()
                .stream()
                .filter(shipmentSizeLongEntry -> shipmentSizeLongEntry.getValue() >= THRESHOLD)
                .findFirst().orElse(null);

        return Objects.isNull(thresholdExceededEntry)
                ? getLargestSize() : thresholdExceededEntry.getKey().incrementAndGet();

    }

    public Map<ShipmentSize, Long> sizeCountMap() {
        return getShipmentSizeStream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public ShipmentSize getLargestSize() {
        return getShipmentSizeStream().max(Comparator.comparingInt(Enum::ordinal)).orElse(null);
    }

    private Stream<ShipmentSize> getShipmentSizeStream() {
        return getProducts().stream().map(Product::getSize);
    }

    public boolean isProductsEmpty() {
        return getProducts() == null || getProducts().isEmpty();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
