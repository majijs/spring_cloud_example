package org.maji.microservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.maji.microservice.feign.ItemFeignClient;
import org.maji.microservice.model.Item;
import org.maji.microservice.properties.MsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author maji
 * @create 2017-12-21
 */
@Service
public class ItemService {
    // Spring框架对RESTful方式的http请求做了封装，来简化操作
    @Autowired
    private RestTemplate restTemplate;

    //通过它来获取服务提供者的信息
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private MsProperties msProperties;

    @Autowired
    private ItemFeignClient itemFeignClient;

    @HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod") //进行容错处理
    public Item queryItemById(Long id) {
        //第五种，使用feign
        return this.itemFeignClient.queryByItemId(id);
    }

    /*@HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod") //进行容错处理
    public Item queryItemById(Long id) {
        //第四种，使用hystrix容错保护
        String serviceId = "maji-microservice-item";
        return this.restTemplate.getForObject("http://" + serviceId + "/item/" + id, Item.class);
    }*/

    public Item queryItemByIdFallbackMethod(Long id){ // 请求失败执行的方法
        return new Item(id, "查询商品信息出错!", null, null, null);
    }


    /*public Item queryItemById(Long id) {
        //第三种，使用Ribbon负载均衡
        String serviceId = "maji-microservice-item";
        return this.restTemplate.getForObject("http://" + serviceId + "/item/" + id, Item.class);
    }*/

    /* public Item queryItemById(Long id){
        // 第二种，走eureka的逻辑，从服务注册中心获取ip
        String serviceId = "maji-microservice-item"; //服务提供者的name
        List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);
        if(instances.isEmpty()){
            return null;
        }
        ServiceInstance serviceInstance = instances.get(0);
        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
        System.out.println("url-->"+url);
        return this.restTemplate.getForObject("http://" + url + "/item/" + id, Item.class);
    }*/

   /* public Item queryItemById(Long id){
        // 第一种，没有走eureka的逻辑，直接通过外部配置的ip地址发送http请求
        return this.restTemplate.getForObject(msProperties.getItem().getUrl() + id, Item.class);
    }*/

}
