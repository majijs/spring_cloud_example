package org.maji.microservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author maji
 * @create 2017-12-22
 */
@SpringBootTest(classes = OrderApplication.class)
@RunWith(SpringRunner.class)
public class OrderTest {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Test
    public void test(){
        String serviceId = "maji-microservice-item";
        for (int i = 0; i < 10; i++) {
            ServiceInstance serviceInstance = this.loadBalancerClient.choose(serviceId);
            System.out.println("第"+(i+1)+"次：" + serviceInstance.getHost() + ": " + serviceInstance.getPort());
        }
    }

}
