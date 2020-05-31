package com.ge.test.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 过滤器
 */
@Component
public class TokenGlobalFilter implements GlobalFilter {
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取参数
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if (token == null || token.equals("")){
            ServerHttpResponse response = exchange.getResponse();
            response.setRawStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            String msg = "token not is null";
            DataBuffer buffer = response.bufferFactory().wrap(msg.getBytes());
            // 返回错误信息
            return response.writeWith(Mono.just(buffer));
        }
        // 放行
        return chain.filter(exchange);
    }
}
