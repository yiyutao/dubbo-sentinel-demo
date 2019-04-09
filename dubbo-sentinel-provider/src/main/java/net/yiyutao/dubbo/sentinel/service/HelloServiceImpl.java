package net.yiyutao.dubbo.sentinel.service;

import net.yiyutao.dubbo.sentinel.api.IHelloService;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
public class HelloServiceImpl implements IHelloService {

    public String sayHello(String name) {
        System.out.println("name");
        return "hello:"+name;
    }
}
