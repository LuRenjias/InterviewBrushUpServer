package edu.hnu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import edu.hnu.dao.ArticleDao;
import edu.hnu.dao.IntegratedQuestionDao;
import edu.hnu.entity.Admin;
import edu.hnu.dao.AdminDao;
import edu.hnu.entity.Article;
import edu.hnu.entity.IntegratedQuestion;
import edu.hnu.entity.PageBean;
import edu.hnu.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Admin)表服务实现类
 *
 * @author lx
 * @since 2024-05-14 10:10:11
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
  @Resource
  private AdminDao adminDao;
  @Resource
  private ArticleDao articleDao;
  @Resource
  private IntegratedQuestionDao integratedQuestionDao;

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  @Override
  public Admin queryById(Integer id) {
    return this.adminDao.queryById(id);
  }

  @Override
  public PageBean ListArticle(Integer page, Integer pageSize) {
    PageHelper.startPage(page, pageSize);
    List<Article> listArticle = articleDao.list();
    Page<Article> p = (Page<Article>) listArticle;
    System.out.println(listArticle);
    System.out.println(p);
    PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
    System.out.println(pageBean);
    return pageBean;
  }

  /**
   * 分页查询
   *
   * @param admin 筛选条件
   * @param pageRequest      分页对象
   * @return 查询结果
   */
    /*@Override
    public Page<Admin> queryByPage(Admin admin, PageRequest pageRequest) {
        long total = this.adminDao.count(admin);
        return new PageImpl<>(this.adminDao.queryAllByLimit(admin, pageRequest), pageRequest, total);
    }*/

  /**
   * 新增数据
   *
   * @param admin 实例对象
   * @return 实例对象
   */
  @Override
  public Admin insert(Admin admin) {
    this.adminDao.insert(admin);
    return admin;
  }

  /**
   * 修改数据
   *
   * @param admin 实例对象
   * @return 实例对象
   */
  @Override
  public Admin update(Admin admin) {
    this.adminDao.update(admin);
    return this.queryById(admin.getId());
  }

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  @Override
  public boolean deleteById(Integer id) {
    return this.adminDao.deleteById(id) > 0;
  }

  /**
   * 通过主键更新文章审核状态
   *
   * @param id 主键
   */
  @Override
  public void updateAStateById(Integer id) {
    this.articleDao.updateStateById(id);
  }

  /**
   * 通过主键更新题目审核状态
   *
   * @param id 主键
   */
  @Override
  public void updateQStateById(Integer id) {
    this.integratedQuestionDao.updateStateById(id);
  }
}
