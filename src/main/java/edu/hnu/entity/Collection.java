package edu.hnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * (Collection)实体类
 *
 * @author lx
 * @since 2024-05-14 10:12:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collection {
    /**
     * 分组_id
     */
    private Integer id;
    /**
     * 分组_所属用户的_id
     */
    private Integer userId;
    /**
     * 分组_名称
     */
    private String collectionName;
    /**
     * 分组_创建时间
     */
    private Date createTime;
    /**
     * 分组内_内容的数量
     */
    private Integer contentCount;
    /**
     * 分组_所属模块
     */
    private Integer module;


}

