package edu.hnu.controller;

import com.github.pagehelper.Page;
import edu.hnu.entity.Favorite;
import edu.hnu.service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Favorite)表控制层
 *
 * @author lx
 * @since 2024-05-14 10:12:58
 */
@RestController
@RequestMapping("favorite")
public class FavoriteController {
    /**
     * 服务对象
     */
    @Resource
    private FavoriteService favoriteService;

    /**
     * 分页查询
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
     * 新增数据
     *
     * @param favorite 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Favorite> add(Favorite favorite) {
        return ResponseEntity.ok(this.favoriteService.insert(favorite));
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
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.favoriteService.deleteById(id));
    }

}

