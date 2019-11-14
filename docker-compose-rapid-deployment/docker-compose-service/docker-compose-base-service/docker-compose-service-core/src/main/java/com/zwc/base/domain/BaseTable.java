package com.zwc.base.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName BaseTable
 * @Desc TODO   测试表
 * @Date 2019/9/16 14:31
 * @Version 1.0
 */
@Data
public class BaseTable extends Model<BaseTable> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增 ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 会话 ID
     */
    private String sessionId;

    /**
     * 添加时间
     */
    private Date createDatetime;

    public static final String ID = "id";

    public static final String SESSION_ID = "sessionId";

    public static final String CREATE_DATETIME = "createDatetime";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
