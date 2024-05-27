package edu.hnu.dao;

import edu.hnu.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author lx
 * @since 2024-05-14 10:13:32
 */
@Mapper
public interface UserDao {

    User queryByOpenId(@Param("openId") String openId);

    int insert(User user);

    int update(User user);

    User queryById(@Param("id") int id);

    @Select("select * from user")
    List<User> list();

    int countLessThan(@Param("id") int id);

    List<User> listIn(@Param("ids") String ids);
}

