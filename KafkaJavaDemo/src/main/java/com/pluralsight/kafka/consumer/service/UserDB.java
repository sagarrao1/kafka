package com.pluralsight.kafka.consumer.service;

import com.pluralsight.kafka.consumer.enums.UserId;
import com.pluralsight.kafka.consumer.model.ConsumerUser;

import java.util.HashMap;
import java.util.Map;

public class UserDB {

    private static Map<String, ConsumerUser> users = new HashMap<>();

    static {
        users.put(UserId.ABC123.toString(), new ConsumerUser(UserId.ABC123));
        users.put(UserId.ABC321.toString(), new ConsumerUser(UserId.ABC321));
        users.put(UserId.CBA123.toString(), new ConsumerUser(UserId.CBA123));
        users.put(UserId.CBA321.toString(), new ConsumerUser(UserId.CBA321));
        users.put(UserId.A1B2C3.toString(), new ConsumerUser(UserId.A1B2C3));
    }

    public ConsumerUser findByID(String id) {
        return users.get(id);
    }

    public void save(ConsumerUser consumerUser) {
        users.put(consumerUser.getConsumerUserId().toString(), consumerUser);
    }
}
