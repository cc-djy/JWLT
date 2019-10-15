package com.cc.mapper;

import com.cc.pojo.Manager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ManagerMapper {
    List<Manager> queryManagers();
}
