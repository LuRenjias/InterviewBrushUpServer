package edu.hnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页查询结果封装类.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {
  private long total; // 数据列表
  private List data; // 总记录数
}
