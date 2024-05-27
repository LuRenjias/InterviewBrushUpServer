package edu.hnu.service;

import edu.hnu.entity.Favorite;


/**
 * (Favorite)表服务接口
 *
 * @author lx
 * @since 2024-05-14 10:12:58
 */
public interface FavoriteService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Favorite queryById(Integer id);

    /**
     * 分页查询
     *
     * @param favorite 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    //Page<Favorite> queryByPage(Favorite favorite, PageRequest pageRequest);

    /**
     * 新增收藏.
     *
     * @param user_id 用户 id
     * @param content_id 收藏内容 id
     * @param collection_name 分组名
     * @param collect_time 收藏时间
     * @param module 分组所属模块
     * @return 影响行数
     */
    int insert(Integer user_id,Integer content_id,String collection_name,String collect_time,Integer module);

    /**
     * 修改数据
     *
     * @param favorite 实例对象
     * @return 实例对象
     */
    Favorite update(Favorite favorite);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 收藏夹对应分组收藏数量+1.
     *
     * @param userId 用户 id
     * @param collectionName 分组名
     * @param module 所属模块
     * @return 影响行数
     */
    int addCollection(Integer userId, String collectionName, Integer module);

    /**
     * 查询收藏对象.
     *
     * @param userId 用户 id
     * @param contentId 收藏内容 id
     * @param module 所属模块
     * @return 收藏对象
     */
    Favorite queryByUIdAndContentIdAndModule(Integer userId, Integer contentId, Integer module);

    /**
     * 收藏夹对应分组收藏数量-1.
     *
     * @param collectionId 分组 id
     */
    void reduceCollection(Integer collectionId);
}
