package org.maji.microservice.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 把配置的参数封装成一个对象
 * @author maji
 * @create 2017-12-21
 */
@Component
@ConfigurationProperties(prefix = "microservice")
public class MsProperties {
    private ItemProperties item = new ItemProperties();

    public ItemProperties getItem() {
        return item;
    }

    public void setItem(ItemProperties item) {
        this.item = item;
    }

}
