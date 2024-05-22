package edu.hnu.dao;

import edu.hnu.entity.Follow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (Follow)表数据库访问层
 *
 * @author lx
 * @since 2024-05-14 10:13:10
 */
@Mapper
public interface FollowDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Follow queryById(Integer id);

    /**
     * 统计总行数
     *
     * @param follow 查询条件
     * @return 总行数
     */
    long count(Follow follow);

    /**
     * 新增数据
     *
     * @param follow 实例对象
     * @return 影响行数
     */
    int insert(Follow follow);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Follow> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Follow> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Follow> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Follow> entities);

    /**
     * 修改数据
     *
     * @param follow 实例对象
     * @return 影响行数
     */
    int update(Follow follow);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    Follow queryStatus(@Param("userId") Integer userId,
                       @Param("followerUserId") Integer followerUserId);

    List<Follow> queryByFollowerUserId(@Param("followerUserId") Integer followerUserId);

    List<Follow> queryByUserId(@Param("userId") Integer userId);

    @Select("select * from follow")
    List<Follow> listAll();
}

