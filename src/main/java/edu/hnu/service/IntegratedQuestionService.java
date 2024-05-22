package edu.hnu.service;

import com.github.pagehelper.Page;
import edu.hnu.entity.IntegratedQuestion;


/**
 * (IntegratedQuestion)表服务接口
 *
 * @author lx
 * @since 2024-05-14 10:13:20
 */
public interface IntegratedQuestionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    IntegratedQuestion queryById(Integer id);

    /**
     * 分页查询
     *
     * @param integratedQuestion 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    //Page<IntegratedQuestion> queryByPage(IntegratedQuestion integratedQuestion, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param integratedQuestion 实例对象
     * @return 实例对象
     */
    IntegratedQuestion insert(IntegratedQuestion integratedQuestion);

    /**
     * 修改数据
     *
     * @param integratedQuestion 实例对象
     * @return 实例对象
     */
    IntegratedQuestion update(IntegratedQuestion integratedQuestion);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过主键查询答案
     *
     * @param id 主键
     * @return 答案字符串
     */
    String queryAnswerById(Integer id);
}
