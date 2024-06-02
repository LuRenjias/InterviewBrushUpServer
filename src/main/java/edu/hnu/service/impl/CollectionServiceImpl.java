package edu.hnu.service.impl;

import edu.hnu.dao.*;
import edu.hnu.dto.*;
import edu.hnu.entity.Collection;
import edu.hnu.entity.LikeRecord;
import edu.hnu.service.CollectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Collection)表服务实现类
 *
 * @author lx
 * @since 2024-05-14 10:12:33
 */
@Service("collectionService")
public class CollectionServiceImpl implements CollectionService {
    @Resource
    private CollectionDao collectionDao;
    @Resource
    private ArticleDao articleDao;
    @Resource
    private IntegratedQuestionDao integratedQuestionDao;
    @Resource
    private ChoiceQuestionDao choiceQuestionDao;
    @Resource
    private LikeRecordDao likeRecordDao;

    @Override
    public List<CollectionDTO> queryByIdAndModule(Integer user_id,Integer module) {
        return this.collectionDao.queryByIdAndModule(user_id,module);
    }

    /**
     * 分页查询
     *
     * @param collection  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    /*@Override
    public Page<Collection> queryByPage(Collection collection, PageRequest pageRequest) {
        long total = this.collectionDao.count(collection);
        return new PageImpl<>(this.collectionDao.queryAllByLimit(collection, pageRequest), pageRequest, total);
    }*/


    @Override
    public CollectionDTO insert(Integer user_id, String collection_name, String create_time, Integer module) {
        int line=this.collectionDao.insert(user_id,collection_name,create_time,module);
        if(line!=1){
            return null;
        }
        return collectionDao.queryLastInsert();
    }

    /**
     * 修改数据
     *
     * @param collection 实例对象
     * @return 实例对象
     */
    @Override
    public Collection update(Collection collection) {
        this.collectionDao.update(collection);
        //return (Collection) this.queryById(collection.getId());
        return new Collection();
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.collectionDao.deleteById(id) > 0;
    }

    @Override
    public boolean deleteByIdAndNameAndModule(Integer userId, String collectionName, Integer module) {
        return this.collectionDao.deleteByIdAndNameAndModule(userId,collectionName,module) > 0;
    }

    @Override
    public boolean updateNameById(Integer id, String name) {
        return collectionDao.updateNameById(id,name) > 0;
    }

    @Override
    public Collection queryById(Integer id) {
        return collectionDao.queryById(id);
    }

    @Override
    public List<ArticleAbbreviationsDTO> queryArticle(Integer id, Integer userId) {
        List<ArticleAbbreviationsDTO> articleAbbreviationsDTOS = articleDao.queryByCollectionIdAndUId(id,userId);
        List<LikeRecord> likeRecordList = likeRecordDao.queryByUserId(userId);
        Map<Integer, Integer> likeRecordMap = new HashMap<>();
        for (LikeRecord likeRecord : likeRecordList) {
            likeRecordMap.put(likeRecord.getArticleId(), 1);
        }
        if (!articleAbbreviationsDTOS.isEmpty()) {
            articleAbbreviationsDTOS.forEach(v -> {
                Integer like = likeRecordMap.get(v.getId());
                v.setLike(like != null);
            });
        }
        return articleAbbreviationsDTOS;
    }

    @Override
    public List<IntegratedQuestionListDTO> queryIntegratedQuestion(Integer id, Integer userId) {
        return integratedQuestionDao.queryByCollectionIdAndUId(id,userId);
    }

    @Override
    public List<SingleChoiceQuestionDTO> queryChoiceQuestion(Integer id, Integer userId) {
        return choiceQuestionDao.queryByCollectionIdAndUId(id,userId);
    }
}
