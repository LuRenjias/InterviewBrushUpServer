package edu.hnu.service.impl;

import edu.hnu.entity.ChoiceQuestionRecord;
import edu.hnu.dao.ChoiceQuestionRecordDao;
import edu.hnu.service.ChoiceQuestionRecordService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;

/**
 * (ChoiceQuestionRecord)表服务实现类
 *
 * @author lx
 * @since 2024-05-14 10:12:18
 */
@Service("choiceQuestionRecordService")
public class ChoiceQuestionRecordServiceImpl implements ChoiceQuestionRecordService {
    @Resource
    private ChoiceQuestionRecordDao choiceQuestionRecordDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ChoiceQuestionRecord queryById(Integer id) {
        return this.choiceQuestionRecordDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param choiceQuestionRecord 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /*@Override
    public Page<ChoiceQuestionRecord> queryByPage(ChoiceQuestionRecord choiceQuestionRecord, PageRequest pageRequest) {
        long total = this.choiceQuestionRecordDao.count(choiceQuestionRecord);
        return new PageImpl<>(this.choiceQuestionRecordDao.queryAllByLimit(choiceQuestionRecord, pageRequest), pageRequest, total);
    }*/

    /**
     * 新增数据
     *
     * @param choiceQuestionRecord 实例对象
     * @return 实例对象
     */
    @Override
    public ChoiceQuestionRecord insert(ChoiceQuestionRecord choiceQuestionRecord) {
        this.choiceQuestionRecordDao.insert(choiceQuestionRecord);
        return choiceQuestionRecord;
    }

    /**
     * 修改数据
     *
     * @param choiceQuestionRecord 实例对象
     * @return 实例对象
     */
    @Override
    public ChoiceQuestionRecord update(ChoiceQuestionRecord choiceQuestionRecord) {
        this.choiceQuestionRecordDao.update(choiceQuestionRecord);
        return this.queryById(choiceQuestionRecord.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.choiceQuestionRecordDao.deleteById(id) > 0;
    }
}
