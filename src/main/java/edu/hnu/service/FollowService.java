package edu.hnu.service;

import com.github.pagehelper.Page;
import edu.hnu.entity.Follow;


/**
 * (Follow)表服务接口
 *
 * @author lx
 * @since 2024-05-14 10:13:10
 */
public interface FollowService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Follow queryById(Integer id);

    /**
     * 分页查询
     *
     * @param follow 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    //Page<Follow> queryByPage(Follow follow, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param follow 实例对象
     * @return 实例对象
     */
    Follow insert(Follow follow);

    /**
     * 修改数据
     *
     * @param follow 实例对象
     * @return 实例对象
     */
    Follow update(Follow follow);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
