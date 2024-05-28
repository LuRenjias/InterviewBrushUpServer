package edu.hnu.service;

import edu.hnu.dto.ChoiceQuestionErrorSetDTO;
import edu.hnu.entity.ChoiceQuestionRecord;

import java.util.List;


/**
 * (ChoiceQuestionRecord)表服务接口
 *
 * @author lx
 * @since 2024-05-14 10:12:18
 */
public interface ChoiceQuestionRecordService {

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  ChoiceQuestionRecord queryById(Integer id);

  /**
   * 分页查询
   *
   * @param choiceQuestionRecord 筛选条件
   * @param pageRequest      分页对象
   * @return 查询结果
   */
  //Page<ChoiceQuestionRecord> queryByPage(ChoiceQuestionRecord choiceQuestionRecord, PageRequest pageRequest);

  /**
   * 新增数据
   *
   * @param choiceQuestionRecord 实例对象
   * @return 实例对象
   */
  ChoiceQuestionRecord insert(ChoiceQuestionRecord choiceQuestionRecord);

  /**
   * 修改数据
   *
   * @param choiceQuestionRecord 实例对象
   * @return 实例对象
   */
  ChoiceQuestionRecord update(ChoiceQuestionRecord choiceQuestionRecord);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  boolean deleteById(Integer id);

  Boolean queryByIdAndAnswer(Integer id, Integer userId, String answer, String choiceTime);

  String queryAnswer(Integer id);

  List<ChoiceQuestionErrorSetDTO> queryErrorSet(Integer userId);
}
