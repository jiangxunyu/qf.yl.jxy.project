package com.qf.base;

//类中有抽象方法，类必须是抽象类
public abstract class BaseServiceImpl<T> implements IBaseService<T> {

    //提供一个Dao调用dao层（mapper.xml）的方法
    public abstract IBaseDao<T> getBaseDao();

    public int deleteByPrimaryKey(Long id){
        return getBaseDao().deleteByPrimaryKey(id);
    };

    public int insert(T record){
        return getBaseDao().insert(record);
    };

    public int insertSelective(T record){
        return getBaseDao().insertSelective(record);
    };

    public T selectByPrimaryKey(Long id){
        return getBaseDao().selectByPrimaryKey(id);
    };

    public int updateByPrimaryKeySelective(T record){
        return getBaseDao().updateByPrimaryKeySelective(record);
    };

    public int updateByPrimaryKey(T record){
        return getBaseDao().updateByPrimaryKey(record);
    };

}
