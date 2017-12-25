package org.maji.microservice.feign;

import org.maji.microservice.model.Item;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Item的feign客户端
 *
 * @author maji
 * @create 2017-12-25
 */
@FeignClient(value = "maji-microservice-item") //指明服务id
public interface ItemFeignClient {

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public Item queryByItemId(@PathVariable("id") Long id);
}
