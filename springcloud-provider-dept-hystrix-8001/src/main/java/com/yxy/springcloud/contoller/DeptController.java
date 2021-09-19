package com.yxy.springcloud.contoller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.yxy.springcloud.pojo.Dept;
import com.yxy.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//提供restful服务
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @PostMapping("dept/add")
    public boolean addDept(Dept dept){
        return deptService.addDept(dept);
    }

    @GetMapping("/dept/get/{id}")
    @HystrixCommand(fallbackMethod = "hystrixGet")
    public Dept get(@PathVariable("id") Long id){
        Dept dept = deptService.queryById(id);
        if(dept==null){
            throw new RuntimeException("id= "+id+", 用户信息未找到");
        }

        return dept;
    }

    public Dept hystrixGet(@PathVariable("id") Long id){
        Dept dept = new Dept();
        dept.setDeptno(id);
        dept.setDname(id + "没有对应信息");
        dept.setDb_source(id + "没有对应的数据库");
        return dept;
    }

    @GetMapping("/dept/list")
    public List<Dept> queryAll(){
        return deptService.queryAll();
    }
}
