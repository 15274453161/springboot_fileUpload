package com.qgh.spring_mvc.moduels.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: BaseDao
 * @Description: 基础dao接口
 * @author liuwenfeng
 * @date 2020年2月28日 上午9:07:38
 * 
 * @param <T>
 */
public interface BaseDao<T> {

	/**
	 * <p>Description:新增</p>
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	int save(T t) throws Exception;
	
	/**
	 * <p>Description:批量新增</p>
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	int batchSave(List<T> list) throws Exception;
	
	/**
	 * <p>Description:修改</p>
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	int update(T t) throws Exception;
	
	/**
	 * <p>Description:删除</p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int delete(@Param("id") Long id) throws Exception;
	
	/**
	 * <p>Description:查询</p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	T get(@Param("id") Long id) throws Exception;
	
	/**
	 * <p>Description:列表查询</p>
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	List<T> list(T t) throws Exception;
	
	/**
	 * <p>Description:删除，id为字符串</p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int deleteById(@Param("id") String id) throws Exception;
	
	/**
	 * <p>Description:查询，id为字符串</p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	T getById(@Param("id") String id) throws Exception;
	
}
