package edu.hnu.controller;

import edu.hnu.entity.Admin;
import edu.hnu.entity.PageBean;
import edu.hnu.service.AdminService;
import edu.hnu.utils.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Admin)表控制层
 *
 * @author lx
 * @since 2024-05-14 10:09:56
 */
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
     * @param page 当前页码
     * @param pageSize 一页展示的文章数量
     */
    @GetMapping("getArticle")
    public Result queryByPage(Integer page,Integer pageSize) {
        PageBean pageBean=adminService.ListArticle(page,pageSize);
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
     * 审核文章
     */
    @PostMapping("addArticle")
    public Result addArticle(Integer id){
        this.adminService.updateAStateById(id);
        return Result.success();
    }

    /**
     * 审核题目
     */
    @PostMapping("addQuestion")
    public Result addQuestion(Integer id){
        this.adminService.updateQStateById(id);
        return Result.success();
    }
}

