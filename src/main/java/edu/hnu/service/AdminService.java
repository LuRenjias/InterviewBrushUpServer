package edu.hnu.service;

import com.github.pagehelper.Page;
import edu.hnu.entity.Admin;
import edu.hnu.entity.PageBean;

import java.awt.print.Pageable;


/**
 * (Admin)表服务接口
 *
 * @author lx
 * @since 2024-05-14 10:10:10
 */
public interface AdminService {

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  Admin queryById(Integer id);

  /**
   * 查询当前页的文章数据.
   *
   * @param page 页码
   * @param pageSize 一页展示的文章数量
   * @return 文章数据的封装类
   */
  PageBean ListArticle(Integer page,Integer pageSize);

  /**
   * 新增数据
   *
   * @param admin 实例对象
   * @return 实例对象
   */
  Admin insert(Admin admin);

  /**
   * 修改数据
   *
   * @param admin 实例对象
   * @return 实例对象
   */
  Admin update(Admin admin);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  boolean deleteById(Integer id);

  void updateAStateById(Integer id);

  void updateQStateById(Integer id);
}
