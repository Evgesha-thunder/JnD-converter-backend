package com.bulish.melnikov.converter.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;

@Getter
@Setter
@RedisHash("Customer")
public class ConvertRequest {

    @Id
    private String id;

    private State state;

    private String filePath;

    public ConvertRequest(String filePath) {
        this.filePath = filePath;
        this.state = State.INIT;
        this.id = UUID.randomUUID().toString();
    }
}
