package edu.hnu.service.impl;

import edu.hnu.dao.CollectionDao;
import edu.hnu.dto.CollectionDTO;
import edu.hnu.entity.Collection;
import edu.hnu.service.CollectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public List<CollectionDTO> queryByIdAndModule(Integer user_id,Integer module) {
        return this.collectionDao.queryByIdAndModule(user_id,module);
    }

    /**
     * 分页查询
     *
     * @param collection 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /*@Override
    public Page<Collection> queryByPage(Collection collection, PageRequest pageRequest) {
        long total = this.collectionDao.count(collection);
        return new PageImpl<>(this.collectionDao.queryAllByLimit(collection, pageRequest), pageRequest, total);
    }*/


    @Override
    public int insert(Integer user_id, String collection_name, String create_time, Integer module) {
        return this.collectionDao.insert(user_id,collection_name,create_time,module);
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
}
