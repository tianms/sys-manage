package com.sys.manage.modules.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sys.manage.modules.base.dao.BaseDao;
import com.sys.manage.modules.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 *
 * 公共
 *
 * @Auther: tianms
 * @Date: 2019/12/10 20:41
 * @Description:
 */
public class BaseServiceImpl<M extends BaseDao<T>, T> implements BaseService<T> {

    @Autowired
    protected M baseDao;

    /**
     *
     * 根据条件查询对应实体信息
     *
     * @Description:
     *
     * @author tianms
     * @date 2019/12/10 20:57
     * @param  map
     * @return T
    */
    @Override
    public T queryByMap(Map<String, Object> map) {
        return baseDao.queryByMap(map);
    }

    /**
     *
     * 获取用户列表
     *
     * @Description:
     *
     * @author tianms
     * @date 2019/12/15 16:47
     * @param  params
     * @return java.util.List<com.sys.manage.modules.sys.entity.SysUserEntity>
     */
    @Override
    public List<T> queryList(Map<String, Object> params) {
        return baseDao.queryList(params);
    }

    /**
     *
     * 获取数据分页列表
     *
     * @Description:
     *
     * @author tianms
     * @date 2019/12/15 21:49
     * @param  params
     * @return com.github.pagehelper.PageInfo<T>
    */
    @Override
    public PageInfo<T> queryPage(Map<String, Object> params) {

        PageHelper.startPage(
                params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString()),
                params.get("limit") == null ? Integer.MAX_VALUE : Integer.parseInt(params.get("limit").toString()));

        List<T> tList = this.queryList(params);

        PageInfo<T> pageInfo = new PageInfo<T>(tList);

        return pageInfo;
    }

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
    @Override
    public T queryById (String id) {
        return baseDao.queryById(id);
    }


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
    @Override
    public void insert(T t) {
        baseDao.insert(t);
    }

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
    @Override
    public void insertAll (T t) {
        baseDao.insertAll(t);
    }

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
    @Override
    public void update(T t) {
        baseDao.update(t);
    }


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
    @Override
    public void delete(String id) {
        baseDao.delete(id);
    }

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
    @Override
    public void delete(Map<String, Object> params) {
        baseDao.delete(params);
    }

    /**
     *
     * 批量删除用户
     *
     * @Description:
     *
     * @author tianms
     * @date 2020/01/16 22:02
     * @param  ids
     * @return void
    */
    @Override
    public void deleteBatch (List<String> ids) {
        baseDao.deleteBatch(ids);
    }
}
