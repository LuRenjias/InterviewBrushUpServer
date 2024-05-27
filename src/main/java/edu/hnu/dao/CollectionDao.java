package edu.hnu.dao;

import edu.hnu.dto.CollectionDTO;
import edu.hnu.entity.Collection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Collection)表数据库访问层
 *
 * @author lx
 * @since 2024-05-14 10:12:33
 */
@Mapper
public interface CollectionDao {

  /**
   * 通过ID查询单条数据
   *
   * @param user_id 主键
   * @param module  所属模块
   * @return 实例对象
   */
  List<CollectionDTO> queryByIdAndModule(Integer user_id, Integer module);

  /**
   * 查询指定行数据
   *
   * @param collection 查询条件
   * @param pageable         分页对象
   * @return 对象列表
   */
  //List<Collection> queryAllByLimit(Collection collection, @Param("pageable") Pageable pageable);

  /**
   * 统计总行数
   *
   * @param collection 查询条件
   * @return 总行数
   */
  long count(Collection collection);

  /**
   * 新增数据
   *
   * @param userId         用户id
   * @param collectionName 分组名
   * @param createTime     创建时间
   * @param module         所属模块
   * @return 影响行数
   */
  int insert(Integer userId, String collectionName, String createTime, Integer module);

  /**
   * 批量新增数据（MyBatis原生foreach方法）
   *
   * @param entities List<Collection> 实例对象列表
   * @return 影响行数
   */
  int insertBatch(@Param("entities") List<Collection> entities);

  /**
   * 批量新增或按主键更新数据（MyBatis原生foreach方法）
   *
   * @param entities List<Collection> 实例对象列表
   * @return 影响行数
   * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
   */
  int insertOrUpdateBatch(@Param("entities") List<Collection> entities);

  /**
   * 修改数据
   *
   * @param collection 实例对象
   * @return 影响行数
   */
  int update(Collection collection);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 影响行数
   */
  int deleteById(Integer id);

  int deleteByIdAndNameAndModule(Integer userId, String collectionName, Integer module);

  int addCount(Integer userId, String collectionName, Integer module);

  void reduceCount(Integer collectionId);
}

