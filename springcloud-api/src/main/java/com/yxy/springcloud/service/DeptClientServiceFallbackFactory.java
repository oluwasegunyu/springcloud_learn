package com.yxy.springcloud.service;

import com.yxy.springcloud.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory {
    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public boolean addDept(Dept dept) {
                return false;
            }

            @Override
            public Dept queryById(Long id) {
                Dept dept = new Dept();
                dept.setDeptno(id);
                dept.setDname(id + "服务降级此服务关闭");
                dept.setDb_source(id + "服务降级此服务关闭");
                return dept;
            }

            @Override
            public List<Dept> queryAll() {
                return null;
            }
        };
    }
}
