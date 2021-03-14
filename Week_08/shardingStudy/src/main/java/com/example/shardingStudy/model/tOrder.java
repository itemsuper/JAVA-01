package com.example.shardingStudy.model;

import lombok.Builder;
import lombok.Data;
import java.math.BigInteger;

@Data
@Builder
public class tOrder {
    private Long orderId;
    private Integer userId;
    private String STATUS;
}
