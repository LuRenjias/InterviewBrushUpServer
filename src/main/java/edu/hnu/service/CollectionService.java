package edu.hnu.service;

import edu.hnu.dto.*;
import edu.hnu.entity.Collection;

import java.util.List;


/**
 * (Collection)表服务接口
 *
 * @author lx
 * @since 2024-05-14 10:12:33
 */
public interface CollectionService {

  /**
   * 通过 user_id 和 module 查询单条数据
   *
   * @param user_id 用户 id
   * @param module  所属模块
   * @return 数据列表
   */
  List<CollectionDTO> queryByIdAndModule(Integer user_id, Integer module);

  /**
   * 分页查询
   *
   * @param collection 筛选条件
   * @param pageRequest      分页对象
   * @return 查询结果
   */
  //Page<Collection> queryByPage(Collection collection, PageRequest pageRequest);


  /**
   * 新增收藏分组
   *
   * @param user_id         用户 id
   * @param collection_name 分组名
   * @param create_time     创建时间
   * @param module          所属模块
   * @return 影响行数
   */
  CollectionDTO insert(Integer user_id, String collection_name, String create_time, Integer module);

  /**
   * 修改数据
   *
   * @param collection 实例对象
   * @return 实例对象
   */
  Collection update(Collection collection);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  boolean deleteById(Integer id);

  boolean deleteByIdAndNameAndModule(Integer userId, String collectionName, Integer module);

  boolean updateNameById(Integer id, String name);

  Collection queryById(Integer id);

  List<ArticleAbbreviationsDTO> queryArticle(Integer id, Integer userId);

  List<IntegratedQuestionListDTO> queryIntegratedQuestion(Integer id, Integer userId);

  List<SingleChoiceQuestionDTO> queryChoiceQuestion(Integer id, Integer userId);
}
