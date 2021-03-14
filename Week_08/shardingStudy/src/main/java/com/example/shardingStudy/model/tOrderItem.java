package com.example.shardingStudy.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class tOrderItem {
    private Long orderItemId;
    private Long orderId;
    private Integer userId;
    private String STATUS;
}
