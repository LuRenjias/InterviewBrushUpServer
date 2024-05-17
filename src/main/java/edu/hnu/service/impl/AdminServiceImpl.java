package edu.hnu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import edu.hnu.dao.*;
import edu.hnu.entity.*;
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
  private ChoiceQuestionDao choiceQuestionDao;
  @Resource
  private IntegratedQuestionDao integratedQuestionDao;
  @Resource
  private UserDao userDao;

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
    PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
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
   * @param idList 主键
   */
  @Override
  public void updateAStateByIdList(List<Integer> idList) {
    this.articleDao.updateStateByIdList(idList);
  }

  /**
   * 通过主键更新题目审核状态
   *
   * @param idList 主键
   */
  @Override
  public void updateQStateByIdList(List<Integer> idList) {
    this.integratedQuestionDao.updateStateByIdList(idList);
  }

  @Override
  public PageBean ListChoiceQuestion(Integer page, Integer pageSize) {
    PageHelper.startPage(page, pageSize);
    List<ChoiceQuestion> listChoice = choiceQuestionDao.list();
    Page<ChoiceQuestion> p = (Page<ChoiceQuestion>) listChoice;
    PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
    return pageBean;
  }

  @Override
  public PageBean ListIntegratedQuestion(Integer page, Integer pageSize) {
    PageHelper.startPage(page, pageSize);
    List<IntegratedQuestion> listIntegratedQuestion = integratedQuestionDao.list();
    Page<IntegratedQuestion> p = (Page<IntegratedQuestion>) listIntegratedQuestion;
    PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
    return pageBean;
  }

  @Override
  public PageBean ListUser(Integer page, Integer pageSize) {
    PageHelper.startPage(page, pageSize);
    List<User> listUser = userDao.list();
    Page<User> p = (Page<User>) listUser;
    PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
    return pageBean;
  }

  @Override
  public Integer queryByAccountAndPasswd(String account, String password) {
    return adminDao.queryByAccountAndPasswd(account, password);
  }

  @Override
  public void addChoiceQuestion(Integer type, Integer category, String question, String options, String correctOption, String publishTime) {
    String t = null;
    if (type == 0) {
      t="single";
    } else {
      t="multiple";
    }
    choiceQuestionDao.addQuestion(t, category, question, options, correctOption, publishTime);
  }
}
