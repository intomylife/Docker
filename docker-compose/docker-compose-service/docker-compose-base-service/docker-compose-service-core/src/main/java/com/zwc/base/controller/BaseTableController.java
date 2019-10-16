package com.zwc.base.controller;

import com.zwc.base.service.BaseTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName BaseTableController
 * @Desc TODO   前端控制器
 * @Date 2019/9/16 15:01
 * @Version 1.0
 */
@RestController
public class BaseTableController {

    @Autowired
    private BaseTableService baseTableService;

    /*
     * @ClassName BaseTableController
     * @Desc TODO   统计访问次数
     * @Date 2019/9/16 15:02
     * @Version 1.0
     */
    @RequestMapping("/")
    @ResponseBody
    public String comeCounts(HttpServletRequest request) {
        // 调用统计访问次数逻辑
        return  baseTableService.comeCounts(request.getSession().toString());
    }

}
