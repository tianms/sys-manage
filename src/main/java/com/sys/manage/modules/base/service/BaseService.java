package com.sys.manage.modules.base.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 *
 * 基础业务查询
 *
 * @Auther: tianms
 * @Date: 2019/12/10 20:41
 * @Description:
 */
public interface BaseService<T> {


    /**
     *
     * 根据条件查询对应实体信息
     *
     * @Description:
     *
     * @author tianms
     * @date 2019/12/10 20:20
     * @param  map
     * @return T
     */
    T queryByMap(Map<String, Object> map);


    /**
     *
     * 根据条件获取列表
     *
     * @Description:
     *
     * @author tianms
     * @date 2019/12/15 21:50
     * @param  params
     * @return java.util.List<T>
    */
    List<T> queryList(Map<String, Object> params);

    /**
     *
     * 根据条件获取分页列表
     *
     * @Description:
     *
     * @author tianms
     * @date 2019/12/15 21:50
     * @param  params
     * @return com.github.pagehelper.PageInfo<T>
    */
    PageInfo<T> queryPage(Map<String, Object> params);

    /**
     *
     * 根据id查询详细信息
     *
     * @Description:
     *
     * @author tianms
     * @date 2019/12/16 20:49
     * @param  id
     * @return T
     */
    T queryById (String id);


    /**
     *
     * 新增
     *
     * @Description:
     *
     * @author tianms
     * @date 2019/12/16 20:38
     * @param  t
     * @return void
     */
    void insert(T t);

    /**
     *
     * 插入全部的列
     *
     * @Description:
     *
     * @author tianms
     * @date 2019/12/16 20:39
     * @param  t
     * @return void
     */
    void insertAll (T t);

    /**
     *
     * 更新列
     *
     * @Description:
     *
     * @author tianms
     * @date 2019/12/16 20:39
     * @param  t
     * @return void
     */
    void update(T t);

    /**
     *
     * 根据id删除
     *
     * @Description:
     *
     * @author tianms
     * @date 2019/12/16 20:40
     * @param  id
     * @return void
     */
    void delete(String id);

    /**
     *
     * 根据条件删除
     *
     * @Description:
     *
     * @author tianms
     * @date 2019/12/16 20:40
     * @param  params
     * @return void
     */
    void delete(Map<String, Object> params);

}
