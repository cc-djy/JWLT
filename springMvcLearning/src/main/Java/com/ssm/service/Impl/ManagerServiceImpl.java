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
    public Manager loginService(Integer id) throws Exception {
        System.out.println("come here");
        return managerMapper.login(id);
    }

    @Override
    public List<Manager> getManagerList() throws Exception {

        return managerMapper.getManagerList();
    }

    @Override
    public Manager searchUpdateManagerFromNameService(Integer id) throws Exception {
        return null;
    }

    @Override
    public void updateManagerFromNameService(Manager manager) throws Exception {

    }

    @Override
    public void insertManagerService(Manager manager) throws Exception {

    }

    @Override
    public void deleteManagerFromNameService(Integer id) throws Exception {

    }
}
