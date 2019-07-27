package com.ssm.service.Impl;

import com.ssm.mapper.ManagerMapper;
import com.ssm.pojo.Manager;
import com.ssm.service.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements IManagerService {
    @Autowired
    private ManagerMapper managerMapper;
    @Override
    public Manager loginService(String name) throws Exception {
        System.out.println("come here");
        return managerMapper.login(name);
    }

    @Override
    public List<Manager> getManagerList() throws Exception {

        return managerMapper.getManagerList();
    }

    @Override
    public Manager searchUpdateManagerFromNameService(String name) throws Exception {
        return null;
    }

    @Override
    public void updateManagerFromNameService(Manager manager) throws Exception {

    }

    @Override
    public void insertManagerService(Manager manager) throws Exception {

    }

    @Override
    public void deleteManagerFromNameService(Manager manager) throws Exception {

    }
}
