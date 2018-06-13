package io.github.anthony.cucumber;

import io.github.anthony.domain.Order;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TestWorld {

    @Getter private Order currentOrder;
    @Getter private List<Order> scenarioOrders = new ArrayList<>();


    public void cleanUpWorld() {
        scenarioOrders.clear();
    }


}
