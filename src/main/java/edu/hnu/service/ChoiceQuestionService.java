package edu.hnu.service;

import com.github.pagehelper.Page;
import edu.hnu.entity.ChoiceQuestion;


/**
 * (ChoiceQuestion)表服务接口
 *
 * @author lx
 * @since 2024-05-14 10:10:54
 */
public interface ChoiceQuestionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ChoiceQuestion queryById(Integer id);

    /**
     * 分页查询
     *
     * @param choiceQuestion 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    //Page<ChoiceQuestion> queryByPage(ChoiceQuestion choiceQuestion, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param choiceQuestion 实例对象
     * @return 实例对象
     */
    ChoiceQuestion insert(ChoiceQuestion choiceQuestion);

    /**
     * 修改数据
     *
     * @param choiceQuestion 实例对象
     * @return 实例对象
     */
    ChoiceQuestion update(ChoiceQuestion choiceQuestion);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
