package edu.hnu.service.impl;

import edu.hnu.dao.CollectionDao;
import edu.hnu.dao.FavoriteDao;
import edu.hnu.entity.Favorite;
import edu.hnu.service.FavoriteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Favorite)表服务实现类
 *
 * @author lx
 * @since 2024-05-14 10:12:58
 */
@Service("favoriteService")
public class FavoriteServiceImpl implements FavoriteService {
    @Resource
    private FavoriteDao favoriteDao;
    @Resource
    private CollectionDao collectionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Favorite queryById(Integer id) {
        return this.favoriteDao.queryById(id);
    }

    @Override
    public int insert(Integer user_id, Integer content_id, String collection_name, String collect_time, Integer module) {
        return favoriteDao.insert(user_id,content_id,collection_name,collect_time,module);
    }

    /**
     * 分页查询
     *
     * @param favorite 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /*@Override
    public Page<Favorite> queryByPage(Favorite favorite, PageRequest pageRequest) {
        long total = this.favoriteDao.count(favorite);
        return new PageImpl<>(this.favoriteDao.queryAllByLimit(favorite, pageRequest), pageRequest, total);
    }*/

    /**
     * 修改数据
     *
     * @param favorite 实例对象
     * @return 实例对象
     */
    @Override
    public Favorite update(Favorite favorite) {
        this.favoriteDao.update(favorite);
        return this.queryById(favorite.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.favoriteDao.deleteById(id) > 0;
    }

    @Override
    public int addCollection(Integer userId, String collectionName, Integer module) {
        return collectionDao.addCount(userId,collectionName,module);
    }

    @Override
    public Favorite queryByUIdAndContentIdAndModule(Integer userId, Integer contentId, Integer module) {
        return favoriteDao.queryByUIdAndContentIdAndModule(userId,contentId,module);
    }

    @Override
    public void reduceCollection(Integer collectionId) {
        collectionDao.reduceCount(collectionId);
    }
}
