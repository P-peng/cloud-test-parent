package com.ge.test.api.chain.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author dengzhipeng
 * @version 1.0
 * @date 2020/5/22 0022
 */
@FeignClient(value = "my-chain")
@RequestMapping("/chain/chain")
public interface ChainFeign{

    @GetMapping("/getChain")
    String getChain(@RequestParam(value = "userId", required = false) Integer userId);

    @GetMapping("/getByKey")
    String getByKey(@RequestParam(value = "key", required = false) String key);
}
