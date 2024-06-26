package edu.hnu.service.impl;

import edu.hnu.dao.FavoriteDao;
import edu.hnu.dao.IntegratedQuestionDao;
import edu.hnu.dto.IntegratedQuestionDTO;
import edu.hnu.dto.IntegratedQuestionListDTO;
import edu.hnu.entity.IntegratedQuestion;
import edu.hnu.service.IntegratedQuestionService;
import edu.hnu.utils.StatusCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
    @Resource
    private FavoriteDao favoriteDao;
    @Value("${info.recommend-count}")
    private Long recommendCount;

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

    @Override
    public String queryAnswerById(Integer id) {
        return integratedQuestionDao.queryAnswerById(id);
    }

    @Override
    public List<IntegratedQuestionListDTO> queryByCategory(Integer category) {
        IntegratedQuestion integratedQuestion = new IntegratedQuestion();
        integratedQuestion.setCategory(category);
        long count = integratedQuestionDao.count(integratedQuestion);
        if (count < recommendCount) {
            List<IntegratedQuestionListDTO> list = integratedQuestionDao.list1(category);
            Collections.shuffle(list);
            return list;
        } else {
            Random random = new Random(); // 5-3=2 [0,2] (0,3) (1,3) (2,3)
            long diff = count - recommendCount;
            int skipCount = random.nextInt((int) (diff + 1));
            System.out.println(recommendCount);
            List<IntegratedQuestionListDTO> list = integratedQuestionDao.listLimit(skipCount, recommendCount, category);
            Collections.shuffle(list);
            return list;
        }
    }

    @Override
    public List<IntegratedQuestionListDTO> queryByQuestion(String keyword, Integer orderType) {
        return switch (orderType) {
            case 0 -> // 0表示按时间降序
                    integratedQuestionDao.queryByQuestion(keyword, "id", StatusCode.APPROVED.getCode());
            case 1 -> // 1表示按浏览量降序
                    integratedQuestionDao.queryByQuestion(keyword, "views_count", StatusCode.APPROVED.getCode());
            case 2 -> // 2表示按重要等级降序
                    integratedQuestionDao.queryByQuestion(keyword, "importance_level", StatusCode.APPROVED.getCode());
            default -> // 默认按照浏览量降序
                    integratedQuestionDao.queryByQuestion(keyword, "views_count", StatusCode.APPROVED.getCode());
        };
    }

    @Override
    public IntegratedQuestionDTO queryDtoById(Integer id, Integer user_id) {
        IntegratedQuestionDTO integratedQuestionDTO = integratedQuestionDao.queryDtoById(id);
        if (integratedQuestionDTO == null) {
            return null;
        }
        List<Integer> listId = favoriteDao.queryContentIdByUIdAndModule(user_id, 1);
        integratedQuestionDTO.setIsCollect(listId.contains(id));
        integratedQuestionDao.addViewCount(id);
        return integratedQuestionDTO;
    }
}
