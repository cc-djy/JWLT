package com.ssm.service;

import com.ssm.pojo.Manager;

import java.util.List;

public interface IManagerService {
    public Manager loginService(String name) throws Exception;
    public List<Manager> getManagerList() throws Exception;
    public Manager searchUpdateManagerFromNameService(String name) throws Exception;
    public void updateManagerFromNameService(Manager manager) throws Exception;
    public void insertManagerService(Manager manager) throws Exception;
    public void deleteManagerFromNameService(Manager manager) throws Exception;

}
