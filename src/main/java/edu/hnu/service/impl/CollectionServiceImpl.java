package edu.hnu.service.impl;

import com.github.pagehelper.Page;
import edu.hnu.entity.Collection;
import edu.hnu.dao.CollectionDao;
import edu.hnu.service.CollectionService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;

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

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Collection queryById(Integer id) {
        return this.collectionDao.queryById(id);
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

    /**
     * 新增数据
     *
     * @param collection 实例对象
     * @return 实例对象
     */
    @Override
    public Collection insert(Collection collection) {
        this.collectionDao.insert(collection);
        return collection;
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
        return this.queryById(collection.getId());
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
}
