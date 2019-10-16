package com.zwc.base.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwc.base.constant.BaseServiceConstant;
import com.zwc.base.domain.BaseTable;
import com.zwc.base.mapper.BaseTableMapper;
import com.zwc.core.utils.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName DockerComposeService
 * @Desc TODO   业务逻辑类
 * @Date 2019/9/16 14:35
 * @Version 1.0
 */
@Service
public class BaseTableService extends ServiceImpl<BaseTableMapper, BaseTable> {

    @Autowired
    private RedisClient redisClient;

    // 时间格式化
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /*
     * @ClassName BaseTableService
     * @Desc TODO   统计访问次数
     * @Date 2019/9/16 14:42
     * @Version 1.0
     */
    @Transactional
    public String comeCounts(String sessionId) {
        // 返回数量
        Integer resultCount = 1;

        // 先从缓存中取访问次数
        Object redisComeCounts = redisClient.get(BaseServiceConstant.COME_COUNTS);
        // 取出所有 sessionId
        List<BaseTable> sessionIdList = new ArrayList<>();
        // 访问次数 - 非空判断
        if(redisComeCounts != null) {
            // 取出所有 sessionId
            sessionIdList = JSON.parseObject(redisComeCounts.toString(), new TypeReference<List<BaseTable>>() {});

            // 数据库中的数量
            Integer mysqlCount = super.count(new QueryWrapper<BaseTable>());
            // 计算出返回数量：mysql + redis
            resultCount = mysqlCount + sessionIdList.size();

            // 判断是否该同步到数据库
            if(sessionIdList.size() >= BaseServiceConstant.MAX_COUNTS) {
                // 同步到数据库中
                if(save(sessionIdList)) {
                    // 同步成功，清空缓存
                    sessionIdList = new ArrayList<>();
                    redisClient.delete(BaseServiceConstant.COME_COUNTS);
                }
            }
        }
        // 存入对象
        BaseTable baseTable = new BaseTable();
        // sessionId
        baseTable.setSessionId(sessionId);
        // 添加时间
        baseTable.setCreateDatetime(new Date());
        // 放入集合中
        sessionIdList.add(baseTable);
        // 存入缓存中
        redisClient.set(BaseServiceConstant.COME_COUNTS, JSON.toJSONString(sessionIdList));

        try {
            // 返回数量
            return "ip：" + InetAddress.getLocalHost().getHostAddress() + " / count：" + String.valueOf(resultCount) + " / time：" + sdf.format(new Date());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            // 返回
            return "获取地址失败";
        }
    }

    /*
     * @ClassName BaseTableService
     * @Desc TODO   批量存入数据到数据库
     * @Date 2019/9/16 14:49
     * @Version 1.0
     */
    public boolean save(List<BaseTable> baseTables) {
        // 调用批量新增方法
        return super.saveBatch(baseTables);
    }

}
