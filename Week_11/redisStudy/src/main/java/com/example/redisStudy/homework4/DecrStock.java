package com.example.redisStudy.homework4;

import com.example.redisStudy.util.JedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DecrStock {

    @ResponseBody
    @GetMapping("/decrStock")
    public String decrStock(){
        long count= JedisUtil.setdecr("myKey", 30000);
        if(count<=0){
            return "扣减库存失败！";
        }
        return "扣减库存成功！";
    }
}
