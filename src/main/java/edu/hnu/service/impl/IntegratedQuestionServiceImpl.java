package edu.hnu.service.impl;

import com.github.pagehelper.Page;
import edu.hnu.entity.IntegratedQuestion;
import edu.hnu.dao.IntegratedQuestionDao;
import edu.hnu.service.IntegratedQuestionService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;

/**
 * (IntegratedQuestion)表服务实现类
 *
 * @author lx
 * @since 2024-05-14 10:13:20
 */
@Service("integratedQuestionService")
public class IntegratedQuestionServiceImpl implements IntegratedQuestionService {
    @Resource
    private IntegratedQuestionDao integratedQuestionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public IntegratedQuestion queryById(Integer id) {
        return this.integratedQuestionDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param integratedQuestion 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /*@Override
    public Page<IntegratedQuestion> queryByPage(IntegratedQuestion integratedQuestion, PageRequest pageRequest) {
        long total = this.integratedQuestionDao.count(integratedQuestion);
        return new PageImpl<>(this.integratedQuestionDao.queryAllByLimit(integratedQuestion, pageRequest), pageRequest, total);
    }*/

    /**
     * 新增数据
     *
     * @param integratedQuestion 实例对象
     * @return 实例对象
     */
    @Override
    public IntegratedQuestion insert(IntegratedQuestion integratedQuestion) {
        this.integratedQuestionDao.insert(integratedQuestion);
        return integratedQuestion;
    }

    /**
     * 修改数据
     *
     * @param integratedQuestion 实例对象
     * @return 实例对象
     */
    @Override
    public IntegratedQuestion update(IntegratedQuestion integratedQuestion) {
        this.integratedQuestionDao.update(integratedQuestion);
        return this.queryById(integratedQuestion.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.integratedQuestionDao.deleteById(id) > 0;
    }
}
