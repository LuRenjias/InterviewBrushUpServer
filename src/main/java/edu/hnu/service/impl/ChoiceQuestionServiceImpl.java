package edu.hnu.service.impl;

import edu.hnu.dao.ChoiceQuestionDao;
import edu.hnu.entity.ChoiceQuestion;
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
}
