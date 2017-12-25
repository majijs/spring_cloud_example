# Spring Cloud 示例

- 请求统一通过API网关（Zuul）来访问内部服务.
- 网关接收到请求后，从注册中心（Eureka）获取可用服务
- 由Ribbon进行均衡负载后，分发到后端具体实例
- 微服务之间通过Feign进行通信处理业务
- Hystrix负责处理服务超时熔断