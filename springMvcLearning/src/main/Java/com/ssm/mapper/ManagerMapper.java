package com.ssm.mapper;
import com.ssm.pojo.Manager;
import java.util.List;
public interface ManagerMapper {
    Manager login(String name) throws Exception;
    public List<Manager> getManagerList() throws Exception;
    public Manager searchUpdateManagerFromName(String name) throws Exception;
    public void updateManagerFromName(Manager manager) throws Exception;
    public void insertManager(Manager manager) throws Exception;
    public void deleteManagerFromName(Manager manager) throws Exception;
}
