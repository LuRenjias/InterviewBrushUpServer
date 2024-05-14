package edu.hnu.service;

import com.github.pagehelper.Page;
import edu.hnu.entity.Collection;


/**
 * (Collection)表服务接口
 *
 * @author lx
 * @since 2024-05-14 10:12:33
 */
public interface CollectionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Collection queryById(Integer id);

    /**
     * 分页查询
     *
     * @param collection 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    //Page<Collection> queryByPage(Collection collection, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param collection 实例对象
     * @return 实例对象
     */
    Collection insert(Collection collection);

    /**
     * 修改数据
     *
     * @param collection 实例对象
     * @return 实例对象
     */
    Collection update(Collection collection);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
