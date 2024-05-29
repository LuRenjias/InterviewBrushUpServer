package edu.hnu.controller;

import com.alibaba.fastjson.JSONObject;
import edu.hnu.dto.*;
import edu.hnu.entity.Collection;
import edu.hnu.service.CollectionService;
import edu.hnu.utils.JwtUtils;
import edu.hnu.utils.Result;
import edu.hnu.utils.StatusCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (Collection)表控制层
 *
 * @author lx
 * @since 2024-05-14 10:12:33
 */
@Slf4j
@RestController
@RequestMapping("collection")
public class CollectionController {
  /**
   * 服务对象
   */
  @Resource
  private CollectionService collectionService;
  @Resource
  private HttpServletRequest request;


  /**
   * 通过 user_id 和 module 查询多条数据
   *
   * @param module 所属模块
   * @return 数据列表
   */
  @GetMapping("getCollection")
  public Result queryById(Integer module) {
    String token = request.getHeader("token");
    Integer user_id = JwtUtils.getUserId(token);
    log.info("查询收藏对应模块下分组，user_id：{}，module：{}",user_id,module);
    List<CollectionDTO> collectionDTOList = new ArrayList<>(collectionService.queryByIdAndModule(user_id, module));
    return Result.success(collectionDTOList);
  }

  /**
   * 新增收藏分组
   *
   * @param jsonObject 封装的数据实体
   */
  @PostMapping("addCollection")
  public Result add(@RequestBody JSONObject jsonObject) {
    String token = request.getHeader("token");
    Integer user_id = JwtUtils.getUserId(token);
    String collection_name = jsonObject.getString("collection_name");
    String create_time = jsonObject.getString("create_time");
    Integer module = jsonObject.getInteger("module");
    log.info("新增分组，user_id:{},collection_name:{},module:{}",user_id,collection_name,module);
    CollectionDTO collectionDTO = collectionService.insert(user_id, collection_name, create_time, module);
    if (collectionDTO == null) {
      return Result.error();
    }
    return Result.success(collectionDTO);
  }

  /**
   * 删除收藏分组
   *
   * @param jsonObject 封装的数据实体
   */
  /*@DeleteMapping("deleteCollection")
  public Result delete(@RequestBody JSONObject jsonObject) {
    String token = request.getHeader("token");
    Integer user_id = JwtUtils.getUserId(token);
    String collection_name = jsonObject.getString("collection_name");
    Integer module = jsonObject.getInteger("module");
    log.info("删除分组，user_id:{},collection_name:{},module:{}",user_id,collection_name,module);
    boolean flag = collectionService.deleteByIdAndNameAndModule(user_id, collection_name, module);
    if (!flag) {
      return Result.error();
    }
    return Result.success();
  }*/

  /**
   * 修改收藏夹名称.
   *
   * @param name 新名字
   * @param id 收藏夹id
   */
  @PostMapping("editName")
  public Result editName(String name,Integer id){
    log.info("修改收藏夹名称，name:{},id:{}",name,id);
    boolean flag = collectionService.updateNameById(id,name);
    if(!flag){
      return Result.error();
    }
    return Result.success();
  }

  /**
   * 删除收藏分组.
   *
   * @param id 主键
   * @return 删除是否成功
   */
  @DeleteMapping("deleteCollection")
  public Result deleteById(Integer id) {
    if(!collectionService.deleteById(id)){
      return Result.error();
    }
    return Result.success();
  }

  /**
   * 查询分组内容列表.
   *
   * @param id 主键
   * @return 删除是否成功
   */
  @GetMapping("getCollectionList")
  public Result queryCollectionList(Integer id){
    String token = request.getHeader("token");
    Integer user_id = JwtUtils.getUserId(token);
    Collection collection = collectionService.queryById(id);
    if(collection == null){
      return Result.error(StatusCode.NO_SELECT_ID);
    }
    int module = collection.getModule();
    if(module == 0){
      List<ArticleAbbreviationsDTO> list = collectionService.queryArticle(id,user_id);
      return Result.success(list);
    } else if (module == 1) {
      List<IntegratedQuestionListDTO> list = collectionService.queryIntegratedQuestion(id,user_id);
      return Result.success(list);
    } else {
      List<SingleChoiceQuestionDTO> list = collectionService.queryChoiceQuestion(id,user_id);
      return Result.success(list);
    }
  }

}

