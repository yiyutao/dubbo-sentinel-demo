package net.yiyutao.dubbo.sentinel;

import net.yiyutao.dubbo.sentinel.api.IHelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HelloServiceTest {

    @Reference(version = "1.0.0")
    private IHelloService helloService;

    @Test
    public void hello() throws InterruptedException {
        int count = 0;
        while (true){
            String sayHello = helloService.sayHello("masteryi");
            System.out.println(sayHello+System.currentTimeMillis());
            if(count++%10==0){
                Thread.sleep(30000);
            }
        }
    }
}
