package com.ge.test.service.api.member.biz.impl;

import com.ge.test.service.api.member.biz.MemberBiz;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dengzhipeng
 * @version 1.0
 * @date 2020/5/20 0020
 */
@Service
public class MemberBziImpl implements MemberBiz {

    @Transactional
    public String get() {
        return null;
    }
}
