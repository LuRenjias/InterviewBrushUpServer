package edu.hnu.controller;

import com.github.pagehelper.Page;
import edu.hnu.entity.Collection;
import edu.hnu.service.CollectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Collection)表控制层
 *
 * @author lx
 * @since 2024-05-14 10:12:33
 */
@RestController
@RequestMapping("collection")
public class CollectionController {
    /**
     * 服务对象
     */
    @Resource
    private CollectionService collectionService;

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
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Collection> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.collectionService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param collection 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Collection> add(Collection collection) {
        return ResponseEntity.ok(this.collectionService.insert(collection));
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

