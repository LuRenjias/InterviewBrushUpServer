package edu.hnu.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.hnu.entity.Admin;
import edu.hnu.entity.PageBean;
import edu.hnu.service.AdminService;
import edu.hnu.utils.JwtUtils;
import edu.hnu.utils.Result;
import edu.hnu.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
   * 管理员登录.
   *
   * @param jsonObject 账号密码信息
   */
  @PostMapping("login")
  public Result login(@RequestBody JSONObject jsonObject) {
    String account = jsonObject.getString("account");
    String password = jsonObject.getString("password");
    log.info("管理员登录，账号密码：{},{}", account, password);
    Admin admin = adminService.queryByAccountAndPasswd(account, password);
    if (admin == null) {
      return Result.error(StatusCode.LOGIN_ERROR);
    }
    return Result.success(JwtUtils.getAdminToken(admin));
  }

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
   * 添加选择题.
   *
   * @param jsonObject 选择题内容
   */
  @PostMapping("addChoiceQuestion")
  public Result addChoiceQuestion(@RequestBody JSONObject jsonObject) {
    log.info("新增选择题");
    Integer type = jsonObject.getInteger("type");
    Integer category = jsonObject.getInteger("category");
    String question = jsonObject.getString("question");
    String options = jsonObject.getString("options");
    String correct_option = jsonObject.getString("correct_option");
    String publish_time = jsonObject.getString("publish_time");
    adminService.addChoiceQuestion(type,category,question,options,correct_option,publish_time);
    return Result.success();
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

  @PostMapping("disableUser")
  public Result disableUser(@RequestBody JSONObject jsonObject) {
    log.info("禁用用户");
    //adminService.updateUserState
    return Result.success();
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
   * 删除文章数据
   *
   * @param jsonObject 包含所有要删除的文章 id
   * @return 删除是否成功
   */
  @DeleteMapping("deleteArticle")
  public Result deleteArticleByIdList(@RequestBody JSONObject jsonObject) {
    log.info("删除文章");
    JSONArray jsonArray = jsonObject.getJSONArray("idList");
    if (jsonArray == null || jsonArray.isEmpty()) {
      return Result.error(StatusCode.NO_SELECT_ID);
    }
    List<Integer> list = jsonArray.toJavaList(Integer.class);
    if(this.adminService.deleteArticle(list)){
      return Result.success();
    } else{
      return Result.error(StatusCode.MISSING_SELECT_ID);
    }
  }

  /**
   * 删除题目数据
   *
   * @param jsonObject 包含所有要删除的题目 id
   * @return 删除是否成功
   */
  @DeleteMapping("deleteChoiceQuestion")
  public Result deleteChoiceQuestion(@RequestBody JSONObject jsonObject) {
    log.info("删除题目");
    JSONArray jsonArray = jsonObject.getJSONArray("idList");
    if (jsonArray == null || jsonArray.isEmpty()) {
      return Result.error(StatusCode.NO_SELECT_ID);
    }
    List<Integer> list = jsonArray.toJavaList(Integer.class);
    if(this.adminService.deleteChoiceQuestion(list)){
      return Result.success();
    } else{
      return Result.error(StatusCode.MISSING_SELECT_ID);
    }
  }

  /**
   * 审核文章通过.
   *
   * @param jsonObject 封装的 idList 集合
   */
  @PostMapping("passArticle")
  public Result passArticle(@RequestBody JSONObject jsonObject) {
    log.info("审核文章通过");
    JSONArray jsonArray = jsonObject.getJSONArray("idList");
    if (jsonArray == null || jsonArray.isEmpty()) {
      return Result.error(StatusCode.NO_SELECT_ID);
    }
    List<Integer> list = jsonArray.toJavaList(Integer.class);
    this.adminService.updateAStateByIdList1(list);
    return Result.success();
  }

  /**
   * 审核题目通过.
   *
   * @param jsonObject 封装的 idList 集合
   */
  @PostMapping("passQuestion")
  public Result passQuestion(@RequestBody JSONObject jsonObject) {
    log.info("审核题目通过");
    JSONArray jsonArray = jsonObject.getJSONArray("idList");
    if (jsonArray == null || jsonArray.isEmpty()) {
      return Result.error(StatusCode.NO_SELECT_ID);
    }
    List<Integer> list = jsonArray.toJavaList(Integer.class);
    this.adminService.updateQStateByIdList1(list);
    return Result.success();
  }

  /**
   * 审核文章不通过.
   *
   * @param jsonObject 封装的 idList 集合
   */
  @PostMapping("rejectArticle")
  public Result rejectArticle(@RequestBody JSONObject jsonObject) {
    log.info("审核文章不通过");
    JSONArray jsonArray = jsonObject.getJSONArray("idList");
    if (jsonArray == null || jsonArray.isEmpty()) {
      return Result.error(StatusCode.NO_SELECT_ID);
    }
    List<Integer> list = jsonArray.toJavaList(Integer.class);
    this.adminService.updateAStateByIdList2(list);
    return Result.success();
  }

  /**
   * 审核题目不通过.
   *
   * @param jsonObject 封装的 idList 集合
   */
  @PostMapping("rejectQuestion")
  public Result examiningQuestion(@RequestBody JSONObject jsonObject) {
    log.info("审核题目不通过");
    JSONArray jsonArray = jsonObject.getJSONArray("idList");
    if (jsonArray == null || jsonArray.isEmpty()) {
      return Result.error(StatusCode.NO_SELECT_ID);
    }
    List<Integer> list = jsonArray.toJavaList(Integer.class);
    this.adminService.updateQStateByIdList2(list);
    return Result.success();
  }
}

