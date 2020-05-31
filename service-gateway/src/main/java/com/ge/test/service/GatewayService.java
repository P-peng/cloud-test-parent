package com.ge.test.service;

import com.ge.test.entity.GatewayEntity;
import com.ge.test.mapper.GatewayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GatewayService implements ApplicationEventPublisherAware {
    ApplicationEventPublisher publisher;


    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;
    @Autowired
    GatewayMapper gatewayMapper;

    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public String loadAllRoute(){
        List<GatewayEntity> list = gatewayMapper.select();
        for (GatewayEntity gatewayEntity : list) {
            loadRoute(gatewayEntity);
        }
        return "success";
    }

    public String loadRoute(GatewayEntity gatewayEntity){
        RouteDefinition definition = new RouteDefinition();

        Map<String, String> predicateParams = new HashMap<String, String>(8);
        PredicateDefinition predicate = new PredicateDefinition();

        FilterDefinition filterDefinition = new FilterDefinition();
        Map<String, String> filterParams = new HashMap<String, String>();
        URI uri = null;
        if ("0".equals(gatewayEntity.getRouteType())){
            // 配置为0，从注册中心获取服务地址
            uri = UriComponentsBuilder.fromUriString("lb://" + gatewayEntity.getRouteUrl() + "/").build().toUri();
        }else {
            // 直接
            uri = UriComponentsBuilder.fromUriString(gatewayEntity.getRouteUrl()).build().toUri();
        }

        // 定义的路由唯一id
        definition.setId(gatewayEntity.getRouteId());
        predicate.setName("Path");
        // 路由转发地址
        predicateParams.put("pattern", gatewayEntity.getRoutePattern());
        predicate.setArgs(predicateParams);

        // 名称是固定
        filterDefinition.setName("StripPrefix");
        filterParams.put("_genkey_0", "1");
        filterDefinition.setArgs(filterParams);
        definition.setPredicates(Arrays.asList(predicate));
        definition.setFilters(Arrays.asList(filterDefinition));
        definition.setUri(uri);
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        return "success";
    }
}
