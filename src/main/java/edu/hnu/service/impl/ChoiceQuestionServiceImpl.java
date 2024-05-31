package edu.hnu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import edu.hnu.dao.ChoiceQuestionDao;
import edu.hnu.dao.ChoiceQuestionRecordDao;
import edu.hnu.dao.FavoriteDao;
import edu.hnu.dto.ChoiceQuestionListDTO;
import edu.hnu.dto.ChoiceQuestionListOrderByTimeDTO;
import edu.hnu.entity.ChoiceQuestion;
import edu.hnu.entity.ChoiceQuestionRecord;
import edu.hnu.service.ChoiceQuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ChoiceQuestion)表服务实现类
 *
 * @author lx
 * @since 2024-05-14 10:10:54
 */
@Service("choiceQuestionService")
public class ChoiceQuestionServiceImpl implements ChoiceQuestionService {
  @Resource
  private ChoiceQuestionDao choiceQuestionDao;
  @Resource
  private ChoiceQuestionRecordDao choiceQuestionRecordDao;
  @Resource
  private FavoriteDao favoriteDao;

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  @Override
  public ChoiceQuestion queryById(Integer id) {
    return this.choiceQuestionDao.queryById(id);
  }

  /**
   * 分页查询
   *
   * @param choiceQuestion 筛选条件
   * @param pageRequest      分页对象
   * @return 查询结果
   */
    /*@Override
    public Page<ChoiceQuestion> queryByPage(ChoiceQuestion choiceQuestion, PageRequest pageRequest) {
        long total = this.choiceQuestionDao.count(choiceQuestion);
        return new PageImpl<>(this.choiceQuestionDao.queryAllByLimit(choiceQuestion, pageRequest), pageRequest, total);
    }*/

  /**
   * 新增数据
   *
   * @param choiceQuestion 实例对象
   * @return 实例对象
   */
  @Override
  public ChoiceQuestion insert(ChoiceQuestion choiceQuestion) {
    this.choiceQuestionDao.insert(choiceQuestion);
    return choiceQuestion;
  }

  /**
   * 修改数据
   *
   * @param choiceQuestion 实例对象
   * @return 实例对象
   */
  @Override
  public ChoiceQuestion update(ChoiceQuestion choiceQuestion) {
    this.choiceQuestionDao.update(choiceQuestion);
    return this.queryById(choiceQuestion.getId());
  }

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  @Override
  public boolean deleteById(Integer id) {
    return this.choiceQuestionDao.deleteById(id) > 0;
  }

  @Override
  public List<ChoiceQuestion> queryByCategory(Integer category) {
    return choiceQuestionDao.queryByCategory(category);
  }

  @Override
  public List<ChoiceQuestion> ListChoiceQuestion(Integer page, int i, Integer category) {
    PageHelper.startPage(page, i);
    List<ChoiceQuestion> listChoiceQuestion = choiceQuestionDao.listByCategory(category);
    Page<ChoiceQuestion> p = (Page<ChoiceQuestion>) listChoiceQuestion;
    return p.getResult();
  }

  @Override
  public Long countByCategory(Integer category) {
    return choiceQuestionDao.countByCategory(category);
  }

  @Override
  public Long countCompletedQuestions(Integer category, Integer user_id) {
    return choiceQuestionRecordDao.countByUidAndCategory(user_id, category);
  }

  @Override
  public List<ChoiceQuestionListDTO> listAll(Integer user_id) {
    return choiceQuestionDao.listAll(user_id);
  }

  @Override
  public ChoiceQuestionRecord queryRecordById(Integer userId, Integer questionId) {
    return choiceQuestionRecordDao.queryByUIdAndQId(userId, questionId);
  }

  @Override
  public List<ChoiceQuestionListOrderByTimeDTO> queryByUId(Integer userId) {
    return choiceQuestionDao.queryByUId(userId);
  }

  @Override
  public List<ChoiceQuestion> queryByQuestion(String keyword, Integer orderType) {
    return switch (orderType) {
      case 0 -> // 0表示按时间降序
          choiceQuestionDao.queryByQuestion(keyword, "id");
      case 1 -> // 1表示按浏览量降序
          choiceQuestionDao.queryByQuestion(keyword, "views_count");
      default -> // 默认按照浏览量降序
          choiceQuestionDao.queryByQuestion(keyword, "views_count");
    };
  }

  @Override
  public Boolean queryIsCollectByUIdAndCId(Integer id, Integer userId) {
    List<Integer> list = favoriteDao.queryContentIdByUIdAndModule(userId,  2);
    return list.contains(id);
  }

  @Override
  public void addViewCount(Integer id) {
    choiceQuestionDao.addViewCount(id);
  }

  @Override
  public List<ChoiceQuestionListDTO> queryListOrderByViewCount(Integer userId) {
    return choiceQuestionDao.queryListOrderByViewCount(userId);
  }
}
