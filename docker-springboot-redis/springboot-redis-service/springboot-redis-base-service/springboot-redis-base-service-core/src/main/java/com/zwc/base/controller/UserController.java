package com.zwc.base.controller;

import com.zwc.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*
 * @ClassName UserController
 * @Desc TODO   来访计数 前端控制器
 * @Date 2019/5/10 16:29
 * @Version 1.0
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /*
     * @ClassName UserController
     * @Desc TODO   来访计数
     * @Date 2019/5/10 16:21
     * @Version 1.0
     */
    @RequestMapping("/")
    @ResponseBody
    public String comeCounts(){
        return userService.comeCounts();
    }

}
