package edu.hnu.controller;

import com.github.pagehelper.Page;
import edu.hnu.entity.Follow;
import edu.hnu.service.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Follow)表控制层
 *
 * @author lx
 * @since 2024-05-14 10:13:10
 */
@RestController
@RequestMapping("follow")
public class FollowController {
    /**
     * 服务对象
     */
    @Resource
    private FollowService followService;

    /**
     * 分页查询
     *
     * @param follow 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
   /* @GetMapping
    public ResponseEntity<Page<Follow>> queryByPage(Follow follow, PageRequest pageRequest) {
        return ResponseEntity.ok(this.followService.queryByPage(follow, pageRequest));
    }*/

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Follow> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.followService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param follow 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Follow> add(Follow follow) {
        return ResponseEntity.ok(this.followService.insert(follow));
    }

    /**
     * 编辑数据
     *
     * @param follow 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Follow> edit(Follow follow) {
        return ResponseEntity.ok(this.followService.update(follow));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.followService.deleteById(id));
    }

}

