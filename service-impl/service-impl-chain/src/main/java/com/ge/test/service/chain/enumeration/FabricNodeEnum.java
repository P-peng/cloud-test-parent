package com.ge.test.service.chain.enumeration;

/**
 * 节点类型
 */
public enum FabricNodeEnum {
    PEER("peer"),
    ORDERER("orderer"),
    CA("ca")
    ;

    private String type;

    FabricNodeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
