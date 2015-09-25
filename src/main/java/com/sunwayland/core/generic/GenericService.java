package com.sunwayland.core.generic;

import java.util.List;

import com.sunwayland.core.vo.WebPage;

/**
 * 所有自定义Service的顶级接口,封装常用的增删查改操作
 * <p/>
 * Model : 代表数据库中的表 映射的Java对象类型
 * PK :代表对象的主键类型
 * 
 */

public interface GenericService<T, PK ,  E  > {
	
	/// GenericServiceImpl.java  

    /**
     * 插入对象 , 并封入 id; 
     *   insertSelective();
     * @param model 对象
     */
    long insert(T model);

    /**
     * 更新对象
     * 调用 dao 的:  updateByPrimaryKeySelective( model); 
     * @param model 对象 
     */
    int updateById(T model);
    
    int updateByExample(T model , E example);

    /**
     * 通过主键, 删除对象
     *
     * @param id 主键
     */
    int deleteById(PK id);
    
    int  deleteByExample(E e);

    /**
     * 通过主键, 查询对象
     *selectByPrimaryKey () ; 
     *
     */
    T selectById(PK id); 
    
    /**
     * 通过模版查询; 
     * selectByExample() ; 返回所有字段; 
     * @param e
     * @return
     */
    List<T>  selectByExample(E e) ;
    
    WebPage  selectByExampleWithpage (E e , WebPage page );
    
 
    /**
     * 查询单个对象
     *  调用   selectByExample() ;  默认 返回 list 中的第一个 ; 
     *                       不存在 返回null ; 
     * @return 对象
     */
    T selectOneByExample( E e);
    
    /**
     *  返回制定字段; 
     * @param e
     * @return
     */
    List<T>  selectByExamSelective(E e) ; 
    

    T selectOneByExamSelective(E e) ; 
    

	int countByExample(E e);
     
    
    
    
    /**
     * 查询多个对象
     *
     * @return 对象集合
     */
    List<T> selectList();


}
