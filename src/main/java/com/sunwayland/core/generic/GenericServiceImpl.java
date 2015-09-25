package com.sunwayland.core.generic;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import com.sunwayland.core.vo.WebPage;

/**
 * GenericService的实现类, 其他的自定义 ServiceImpl, 继承自它,可以获得常用的增删查改操作, 未实现的方法有 子类各自实现
 * <p/>
 * Model : 代表数据库中的表 映射的Java对象类型 PK :代表对象的主键类型
 * 
 */
public abstract class GenericServiceImpl<Model, PK, E extends GenericModelExample>
	implements GenericService<Model, PK, E> {
    /**
     * 定义成抽象方法,由子类实现,完成dao的注入
     *
     * @return GenericDao实现类
     */

    public abstract GenericMybatisDao<Model, PK, E> getDao();

    @Override
    public List<Model> selectByExamSelective(E e) {

	List<Model> list = getDao().selectByExampleSelective(e);
	return list;
    }

    /**
     * 得到分页数据; 返回page 引用
     */
    @Override
    public WebPage selectByExampleWithpage(E e, WebPage page) {

	Integer total = page.getTotal();

	int totalNum = getDao().countByExample(e);
	page.setTotal(totalNum);

	e.setLimitStart(page.getOffset());
	e.setLimitEnd(page.getLimit());

	List<Model> list = this.selectByExamSelective(e);

	page.setData(list);
	return page;
    }

    @Override
    public int countByExample(E e) {
	int count = getDao().countByExample(e);
	return count;
    }

    @Override
    public Model selectOneByExamSelective(E e) {
	List<Model> list = getDao().selectByExampleSelective(e);
	if (null != list && 0 != list.size()) {
	    return list.get(0);
	}

	return null;
    }

    /**
     * 插入对象
     *
     * @param model
     *            对象
     */
    @Override
    public long insert(Model model) {
	return getDao().insertSelective(model);
    }

    /**
     * 更新对象 updateByPrimaryKeySelective(model);
     * 
     * @param model
     *            对象
     */
    @Override
    public int updateById(Model model) {
	return getDao().updateByPrimaryKeySelective(model);
    }

    /**
     * 更新对象; updateByExampleSelective(model, e)
     * 
     * @param model
     * @param e
     * @return
     */
    @Override
    public int updateByExample(Model model, E e) {

	return getDao().updateByExampleSelective(model, e);
    }

    /**
     * 通过主键, 删除对象
     *
     * @param id
     *            主键
     */
    @Override
    public int deleteById(PK id) {
	return getDao().deleteByPrimaryKey(id);
    }

    /**
     * 
     * @param e
     * @return
     */
    @Override
    public int deleteByExample(E e) {

	return getDao().deleteByExample(e);
    }

    /**
     * 通过主键, 查询对象
     *
     * @param id
     *            主键
     * @return
     */
    @Override
    public Model selectById(PK id) {
	return getDao().selectByPrimaryKey(id);
    }

    /**
     * selectByExample
     */
    @Override
    public List<Model> selectByExample(E e) {
	List<Model> list = getDao().selectByExample(e);
	return list;
    }

    @Override
    public Model selectOneByExample(E e) {
	List<Model> list = getDao().selectByExample(e);
	if (list.size() != 0) {
	    return list.get(0);
	} else {
	    return null;
	}
    }

    /**
     * xxxx 为实现
     */
    @Override
    public List<Model> selectList() {
	return null;
    }
}
