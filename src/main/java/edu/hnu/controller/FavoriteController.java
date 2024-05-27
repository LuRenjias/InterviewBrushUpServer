package edu.hnu.controller;

import com.alibaba.fastjson.JSONObject;
import edu.hnu.entity.Favorite;
import edu.hnu.service.FavoriteService;
import edu.hnu.utils.JwtUtils;
import edu.hnu.utils.Result;
import edu.hnu.utils.StatusCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Favorite)表控制层
 *
 * @author lx
 * @since 2024-05-14 10:12:58
 */
@Slf4j
@RestController
@RequestMapping("favorite")
public class FavoriteController {
  /**
   * 服务对象
   */
  @Resource
  private FavoriteService favoriteService;
  @Resource
  private HttpServletRequest request;

  /**
   * 分页查询.
   *
   * @param favorite 筛选条件
   * @param pageRequest      分页对象
   * @return 查询结果
   */
    /*@GetMapping
    public ResponseEntity<Page<Favorite>> queryByPage(Favorite favorite, PageRequest pageRequest) {
        return ResponseEntity.ok(this.favoriteService.queryByPage(favorite, pageRequest));
    }*/

  /**
   * 通过主键查询单条数据
   *
   * @param id 主键
   * @return 单条数据
   */
  @GetMapping("{id}")
  public ResponseEntity<Favorite> queryById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(this.favoriteService.queryById(id));
  }

  /**
   * 新增收藏.
   *
   * @param jsonObject 封装的数据
   * @return 新增是否成功
   */
  @PostMapping("addFavorite")
  public Result add(@RequestBody JSONObject jsonObject) {
    String token = request.getHeader("token");
    Integer user_id = JwtUtils.getUserId(token);
    Integer content_id = jsonObject.getInteger("content_id");
    String collection_name = jsonObject.getString("collection_name");
    String collect_time = jsonObject.getString("collect_time");
    Integer module = jsonObject.getInteger("module");
    log.info("新增收藏，user_id:{},content_id:{},collection_name:{},module:{}", user_id, content_id, collection_name, module);
    int line = favoriteService.insert(user_id, content_id, collection_name, collect_time, module);
    if (line != 1) {
      return Result.error(StatusCode.DATABASE_ERROR);
    }
    line = favoriteService.addCollection(user_id, collection_name, module);
    if (line != 1) {
      return Result.error(StatusCode.DATABASE_ERROR);
    }
    return Result.success();
  }

  /**
   * 编辑数据
   *
   * @param favorite 实体
   * @return 编辑结果
   */
  @PutMapping
  public ResponseEntity<Favorite> edit(Favorite favorite) {
    return ResponseEntity.ok(this.favoriteService.update(favorite));
  }

  /**
   * 删除收藏.
   *
   * @param jsonObject 封装的数据
   * @return 删除是否成功
   */
  @DeleteMapping("deleteFavorite")
  public Result deleteById(@RequestBody JSONObject jsonObject) {
    String token = request.getHeader("token");
    Integer user_id = JwtUtils.getUserId(token);
    Integer content_id = jsonObject.getInteger("content_id");
    Integer module = jsonObject.getInteger("module");
    log.info("删除收藏，user_id:{},content_id:{},module:{}", user_id, content_id, module);
    Favorite favorite = favoriteService.queryByUIdAndContentIdAndModule(user_id,content_id,module);
    if(favorite == null || !favoriteService.deleteById(favorite.getId())){
      return Result.error(StatusCode.ILLEGAL_DELETION);
    }
    favoriteService.reduceCollection(favorite.getCollectionId());
    return Result.success();
  }

}

