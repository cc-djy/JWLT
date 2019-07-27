package com.ssm.pojo;

import org.springframework.stereotype.Component;

@Component
public class Manager {
    private String name;

    public Manager() {
    }

    public Manager(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
