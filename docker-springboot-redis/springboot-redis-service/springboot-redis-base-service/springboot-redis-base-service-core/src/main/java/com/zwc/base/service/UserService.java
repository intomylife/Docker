package com.zwc.base.service;

import com.zwc.base.constant.BaseServiceConstant;
import com.zwc.utils.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @ClassName UserService
 * @Desc TODO   来访计数 实现服务类
 * @Date 2019/5/10 16:31
 * @Version 1.0
 */
@Service
public class UserService {

    @Autowired
    private RedisClient redisClient;

    /*
     * @ClassName UserService
     * @Desc TODO   来访计数
     * @Date 2019/5/10 16:22
     * @Version 1.0
     */
    public String comeCounts(){
        // 判断 key 是否存在
        // 为空，第一次访问
        if(redisClient.get(BaseServiceConstant.USER_REDIS_KEY) == null){
            // 存入访问记录
            redisClient.set(BaseServiceConstant.USER_REDIS_KEY,1);
        // 不为空，访问加一
        }else{
            // 取出当前数量
            Integer counts = Integer.valueOf(redisClient.get(BaseServiceConstant.USER_REDIS_KEY)+"");
            // 存入访问记录
            redisClient.set(BaseServiceConstant.USER_REDIS_KEY,counts+1);
        }
        // 返回结果
        return "来访计数：" + redisClient.get(BaseServiceConstant.USER_REDIS_KEY);
    }

}
