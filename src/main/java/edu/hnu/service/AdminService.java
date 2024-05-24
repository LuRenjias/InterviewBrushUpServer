package edu.hnu.service;

import edu.hnu.entity.Admin;
import edu.hnu.entity.PageBean;

import java.util.List;


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

  /**
   * 通过主键更新文章审核通过
   *
   * @param idList 主键
   */
  void updateAStateByIdList1(List<Integer> idList);

  /**
   * 通过主键更新题目审核通过
   *
   * @param idList 主键
   */
  void updateQStateByIdList1(List<Integer> idList);

  /**
   * 查询当前页的题目数据.
   *
   * @param page 页码
   * @param pageSize 一页展示的文章数量
   * @return 文章数据的封装类
   */
  PageBean ListChoiceQuestion(Integer page, Integer pageSize);

  /**
   * 查询当前页的八股数据.
   *
   * @param page 页码
   * @param pageSize 一页展示的八股数量
   * @return 八股数据的封装类
   */
  PageBean ListIntegratedQuestion(Integer page, Integer pageSize);

  /**
   * 查询当前页的用户数据.
   *
   * @param page 页码
   * @param pageSize 一页展示的用户数量
   * @return 用户数据的封装类
   */
  PageBean ListUser(Integer page, Integer pageSize);

  /**
   * 管理员通过账号密码登录.
   *
   * @param account 账号
   * @param password 密码
   * @return 对应 id
   */
  Admin queryByAccountAndPasswd(String account, String password);

  /**
   * 新增单选题.
   *
   * @param type 类型
   * @param category 类别
   * @param question 问题
   * @param options 选项
   * @param correctOption 答案
   * @param publishTime 发布时间
   */
  void addChoiceQuestion(Integer type, Integer category, String question, String options, String correctOption, String publishTime);

  boolean deleteArticle(List<Integer> list);

  boolean deleteChoiceQuestion(List<Integer> list);

  /**
   * 通过主键更新文章审核不通过
   *
   * @param idList 主键
   */
  void updateAStateByIdList2(List<Integer> idList);

  /**
   * 通过主键更新文章审核不通过
   *
   * @param idList 主键
   */
  void updateQStateByIdList2(List<Integer> idList);
}
