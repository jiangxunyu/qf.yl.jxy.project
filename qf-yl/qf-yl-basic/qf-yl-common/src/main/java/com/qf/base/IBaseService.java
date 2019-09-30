package com.qf.base;

public interface IBaseService<T> {

    //设置和IBaseDao相同的方法以便分辨调用的dao层的方法

    int deleteByPrimaryKey(Long id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

}
