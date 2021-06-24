package com.example.retry.controller;

import com.example.retry.common.util.Result;
import com.example.retry.response.DemoResponseDTO;
import com.example.retry.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李勇勇
 * @version 1.0
 * @date 2021-06-22 14:45
 */
@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;


    @GetMapping(value = "/demo/retry")
    public Result<DemoResponseDTO> demoRetry(@RequestParam(value = "id") String id){
       return Result.success(demoService.demoTry(id)) ;
    }


}
