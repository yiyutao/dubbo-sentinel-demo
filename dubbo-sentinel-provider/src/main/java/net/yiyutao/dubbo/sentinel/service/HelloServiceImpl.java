package net.yiyutao.dubbo.sentinel.service;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import net.yiyutao.dubbo.sentinel.api.IHelloService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author masterYI
 */
@Service
public class HelloServiceImpl implements IHelloService {

    private AtomicInteger atomicInteger=new AtomicInteger(0);

    @Override
    public String sayHello(String name) {
//        initFlowRules();
        Entry entry = null;
        try {
            entry = SphU.entry("HelloWorld");
            System.out.println("业务开始======================================");
            atomicInteger.addAndGet(1);
            System.out.println(atomicInteger);
            System.out.println(name);
            System.out.println("业务结束======================================");
        } catch (BlockException e) {
            /*流控逻辑处理 - 开始*/
            System.out.println("block!");
            e.printStackTrace();
            /*流控逻辑处理 - 结束*/
        }finally {
            if (entry != null) {
                entry.exit();
            }
        }

        return "hello:" + name;
    }

    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(20);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
