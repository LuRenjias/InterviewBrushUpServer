package edu.hnu.dao;

import edu.hnu.entity.Image;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImageDao {

    int insert(Image image);

    int update(Image image);

    List<Image> queryByCategoryAndCategoryId(@Param("category") Integer category,
                                             @Param("categoryId") Integer categoryId);

    int deleteByCategoryAndCategoryId(@Param("category") Integer category,
                                      @Param("categoryId") Integer categoryId);

    @Select("select * from image")
    List<Image> listAll();
}
