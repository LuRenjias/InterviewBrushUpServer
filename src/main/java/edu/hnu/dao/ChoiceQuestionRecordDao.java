package edu.hnu.dao;

import edu.hnu.entity.ChoiceQuestionRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (ChoiceQuestionRecord)表数据库访问层
 *
 * @author lx
 * @since 2024-05-14 10:12:18
 */
@Mapper
public interface ChoiceQuestionRecordDao {

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  ChoiceQuestionRecord queryById(Integer id);

  /**
   * 查询指定行数据
   *
   * @param choiceQuestionRecord 查询条件
   * @param pageable         分页对象
   * @return 对象列表
   */
  //List<ChoiceQuestionRecord> queryAllByLimit(ChoiceQuestionRecord choiceQuestionRecord, @Param("pageable") Pageable pageable);

  /**
   * 统计总行数
   *
   * @param choiceQuestionRecord 查询条件
   * @return 总行数
   */
  long count(ChoiceQuestionRecord choiceQuestionRecord);

  /**
   * 新增数据
   *
   * @param choiceQuestionRecord 实例对象
   * @return 影响行数
   */
  int insert(ChoiceQuestionRecord choiceQuestionRecord);

  /**
   * 批量新增数据（MyBatis原生foreach方法）
   *
   * @param entities List<ChoiceQuestionRecord> 实例对象列表
   * @return 影响行数
   */
  int insertBatch(@Param("entities") List<ChoiceQuestionRecord> entities);

  /**
   * 批量新增或按主键更新数据（MyBatis原生foreach方法）
   *
   * @param entities List<ChoiceQuestionRecord> 实例对象列表
   * @return 影响行数
   * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
   */
  int insertOrUpdateBatch(@Param("entities") List<ChoiceQuestionRecord> entities);

  /**
   * 修改数据
   *
   * @param choiceQuestionRecord 实例对象
   * @return 影响行数
   */
  int update(ChoiceQuestionRecord choiceQuestionRecord);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 影响行数
   */
  int deleteById(Integer id);

  //boolean queryByIdAndAnswer(Integer id,Integer userId, String answer);
}

