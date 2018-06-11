package io.github.symonk.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.symonk.common.enums.OrderOptions;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Data
public class Order {

    private String adopterName;
    private String adopterAddress;
    private String adopterEmail;
    private String dogName;
    private List<OrderOptions> listOfOrderItems = new ArrayList<>();

    public Order(String dogName, OrderOptions... options) {
        this.dogName = dogName;
        Collections.addAll(this.listOfOrderItems, options);
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

}
