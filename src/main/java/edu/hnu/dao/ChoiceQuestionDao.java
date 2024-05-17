package edu.hnu.dao;

import edu.hnu.entity.ChoiceQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (ChoiceQuestion)表数据库访问层
 *
 * @author lx
 * @since 2024-05-14 10:10:54
 */
@Mapper
public interface ChoiceQuestionDao {

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  ChoiceQuestion queryById(Integer id);

  /**
   * 查询指定行数据
   *
   * @param choiceQuestion 查询条件
   * @param pageable         分页对象
   * @return 对象列表
   */
  //List<ChoiceQuestion> queryAllByLimit(ChoiceQuestion choiceQuestion, @Param("pageable") Pageable pageable);

  /**
   * 统计总行数
   *
   * @param choiceQuestion 查询条件
   * @return 总行数
   */
  long count(ChoiceQuestion choiceQuestion);

  /**
   * 新增数据
   *
   * @param choiceQuestion 实例对象
   * @return 影响行数
   */
  int insert(ChoiceQuestion choiceQuestion);

  /**
   * 批量新增数据（MyBatis原生foreach方法）
   *
   * @param entities List<ChoiceQuestion> 实例对象列表
   * @return 影响行数
   */
  int insertBatch(@Param("entities") List<ChoiceQuestion> entities);

  /**
   * 批量新增或按主键更新数据（MyBatis原生foreach方法）
   *
   * @param entities List<ChoiceQuestion> 实例对象列表
   * @return 影响行数
   * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
   */
  int insertOrUpdateBatch(@Param("entities") List<ChoiceQuestion> entities);

  /**
   * 修改数据
   *
   * @param choiceQuestion 实例对象
   * @return 影响行数
   */
  int update(ChoiceQuestion choiceQuestion);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 影响行数
   */
  int deleteById(Integer id);

  /**
   * 查询所有题目数据.
   *
   * @return 所有题目数据
   */
  @Select("select * from choice_question")
  List<ChoiceQuestion> list();

  void addQuestion(String type, Integer category, String question, String options, String correctOption, String publishTime);
}

