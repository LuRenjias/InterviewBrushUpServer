package edu.hnu.service.impl;

import edu.hnu.entity.Favorite;
import edu.hnu.dao.FavoriteDao;
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
     * 新增数据
     *
     * @param favorite 实例对象
     * @return 实例对象
     */
    @Override
    public Favorite insert(Favorite favorite) {
        this.favoriteDao.insert(favorite);
        return favorite;
    }

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
}
