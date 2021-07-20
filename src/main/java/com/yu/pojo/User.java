package com.yu.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: ycm
 * @Date: 2021/7/20 11:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     *主键生成策略：
     *雪花算法:
     * public enmu IdType:
     * AUTO(0): id自增
     * NONE(1)：未设置主键
     * INPUT(2):手动输入
     * ID_WORKER(3): 默认的全局唯一id
     * UUID(4):全局唯一id
     * ID_WORKER_STR(5): 字符串表示
     *
     */
    @TableId(type = IdType.UUID)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    //乐观锁注解
    @Version
    private Integer version;

    //字段添加填充内容
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic//逻辑删除
    private Integer deleted;

}