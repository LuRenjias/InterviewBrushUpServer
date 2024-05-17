package edu.hnu.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.hnu.entity.Admin;
import edu.hnu.entity.PageBean;
import edu.hnu.service.AdminService;
import edu.hnu.utils.Result;
import edu.hnu.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (Admin)表控制层
 *
 * @author lx
 * @since 2024-05-14 10:09:56
 */
@Slf4j
@RestController
@RequestMapping("admin")
public class AdminController {
  /**
   * 服务对象
   */
  @Resource
  private AdminService adminService;

  /**
   * 分页查询文章数据.
   *
   * @param page     当前页码
   * @param pageSize 一页展示的文章数量
   */
  @GetMapping("getArticle")
  public Result queryArticleByPage(Integer page, Integer pageSize) {
    log.info("分页查询文章数据，参数：{},{}", page, pageSize);
    PageBean pageBean = adminService.ListArticle(page, pageSize);
    return Result.success(pageBean);
  }

  /**
   * 分页查询题目数据.
   *
   * @param page     当前页码
   * @param pageSize 一页展示的题目数量
   */
  @GetMapping("getChoiceQuestion")
  public Result queryChoiceQuestionByPage(Integer page, Integer pageSize) {
    log.info("分页查询题目数据，参数：{},{}", page, pageSize);
    PageBean pageBean = adminService.ListChoiceQuestion(page, pageSize);
    return Result.success(pageBean);
  }

  /**
   * 分页查询八股数据.
   *
   * @param page     当前页码
   * @param pageSize 一页展示的八股数量
   */
  @GetMapping("getIntegratedQuestion")
  public Result queryIntegratedQuestionByPage(Integer page, Integer pageSize) {
    log.info("分页查询八股数据，参数：{},{}", page, pageSize);
    PageBean pageBean = adminService.ListIntegratedQuestion(page, pageSize);
    return Result.success(pageBean);
  }

  /**
   * 分页查询用户数据.
   *
   * @param page     当前页码
   * @param pageSize 一页展示的用户数量
   */
  @GetMapping("getUser")
  public Result queryUserByPage(Integer page, Integer pageSize) {
    log.info("分页查询用户数据，参数：{},{}", page, pageSize);
    PageBean pageBean = adminService.ListUser(page, pageSize);
    return Result.success(pageBean);
  }

  /**
   * 通过主键查询单条数据
   *
   * @param id 主键
   * @return 单条数据
   */
  @GetMapping("{id}")
  public ResponseEntity<Admin> queryById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(this.adminService.queryById(id));
  }

  /**
   * 新增数据
   *
   * @param admin 实体
   * @return 新增结果
   */
  @PostMapping
  public ResponseEntity<Admin> add(Admin admin) {
    return ResponseEntity.ok(this.adminService.insert(admin));
  }

  /**
   * 编辑数据
   *
   * @param admin 实体
   * @return 编辑结果
   */
  @PutMapping
  public ResponseEntity<Admin> edit(Admin admin) {
    return ResponseEntity.ok(this.adminService.update(admin));
  }

  /**
   * 删除数据
   *
   * @param id 主键
   * @return 删除是否成功
   */
  @DeleteMapping
  public ResponseEntity<Boolean> deleteById(Integer id) {
    return ResponseEntity.ok(this.adminService.deleteById(id));
  }

  /**
   * 审核文章.
   *
   * @param jsonObject 封装的 idList 集合
   */
  @PostMapping("addArticle")
  public Result addArticle(@RequestBody JSONObject jsonObject) {
    log.info("审核文章");
    JSONArray jsonArray = jsonObject.getJSONArray("idList");
    if (jsonArray == null || jsonArray.isEmpty()) {
      return Result.error(StatusCode.NO_SELECT_ID);
    }
    List<Integer> list = jsonArray.toJavaList(Integer.class);
    this.adminService.updateAStateByIdList(list);
    return Result.success();
  }

  /**
   * 审核题目.
   *
   * @param jsonObject 封装的 idList 集合
   */
  @PostMapping("addQuestion")
  public Result addQuestion(@RequestBody JSONObject jsonObject) {
    log.info("审核题目");
    JSONArray jsonArray = jsonObject.getJSONArray("idList");
    if (jsonArray == null || jsonArray.isEmpty()) {
      return Result.error(StatusCode.NO_SELECT_ID);
    }
    List<Integer> list = jsonArray.toJavaList(Integer.class);
    this.adminService.updateQStateByIdList(list);
    return Result.success();
  }
}

