package edu.hnu.controller;

import com.alibaba.fastjson.JSONObject;
import edu.hnu.dto.CollectionDTO;
import edu.hnu.entity.Collection;
import edu.hnu.service.CollectionService;
import edu.hnu.utils.JwtUtils;
import edu.hnu.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
   * 分页查询
   *
   * @param collection 筛选条件
   * @param pageRequest      分页对象
   * @return 查询结果
   */
    /*@GetMapping
    public ResponseEntity<Page<Collection>> queryByPage(Collection collection, PageRequest pageRequest) {
        return ResponseEntity.ok(this.collectionService.queryByPage(collection, pageRequest));
    }*/

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
    int line = collectionService.insert(user_id, collection_name, create_time, module);
    if (line != 1) {
      return Result.error();
    }
    return Result.success();
  }

  /**
   * 删除收藏分组
   *
   * @param jsonObject 封装的数据实体
   */
  @DeleteMapping("deleteCollection")
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
  }

  /**
   * 编辑数据
   *
   * @param collection 实体
   * @return 编辑结果
   */
  @PutMapping
  public ResponseEntity<Collection> edit(Collection collection) {
    return ResponseEntity.ok(this.collectionService.update(collection));
  }

  /**
   * 删除数据
   *
   * @param id 主键
   * @return 删除是否成功
   */
  @DeleteMapping
  public ResponseEntity<Boolean> deleteById(Integer id) {
    return ResponseEntity.ok(this.collectionService.deleteById(id));
  }

}

