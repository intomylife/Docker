package com.zwc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloDockerController
 * @Desc TODO   say hello - 前端控制器
 * @Date 2019/5/3 17:18
 * @Version 1.0
 */
@RestController
public class HelloDockerController {

    /*
     * @ClassName HelloDockerController
     * @Desc TODO   say hello
     * @Date 2019/5/3 17:21
     * @Version 1.0
     */
    @RequestMapping("/")
    public String say(){
        return "Hello Docker ！！！";
    }

}
