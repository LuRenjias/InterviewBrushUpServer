package edu.hnu.service.impl;

import com.github.pagehelper.Page;
import edu.hnu.entity.Follow;
import edu.hnu.dao.FollowDao;
import edu.hnu.service.FollowService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;

/**
 * (Follow)表服务实现类
 *
 * @author lx
 * @since 2024-05-14 10:13:10
 */
@Service("followService")
public class FollowServiceImpl implements FollowService {
    @Resource
    private FollowDao followDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Follow queryById(Integer id) {
        return this.followDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param follow 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /*@Override
    public Page<Follow> queryByPage(Follow follow, PageRequest pageRequest) {
        long total = this.followDao.count(follow);
        return new PageImpl<>(this.followDao.queryAllByLimit(follow, pageRequest), pageRequest, total);
    }*/

    /**
     * 新增数据
     *
     * @param follow 实例对象
     * @return 实例对象
     */
    @Override
    public Follow insert(Follow follow) {
        this.followDao.insert(follow);
        return follow;
    }

    /**
     * 修改数据
     *
     * @param follow 实例对象
     * @return 实例对象
     */
    @Override
    public Follow update(Follow follow) {
        this.followDao.update(follow);
        return this.queryById(follow.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.followDao.deleteById(id) > 0;
    }
}
