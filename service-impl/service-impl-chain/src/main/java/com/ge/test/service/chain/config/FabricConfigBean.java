package com.ge.test.service.chain.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * @author dengzhipeng
 * @date 2020.5.25
 */
@Component
@Getter
public class FabricConfigBean {

    @Value("${fabric.server}")
    private String server;
    @Value("${fabric.domain-name}")
    private String domainName;

    /**
     * 证书所在的文件架
     */
    @Value("${fabric.crypto-config-path}")
    private String basePath;

    private String GRPC = "grpcs://";

    /**
     * 通道名字
     */
    private String channelName = "mychannel";

    /**
     * 链码名
     */
    private String channelCodeName = "mycc";

    /**
     * ca 密码
     */
    private String admin = "admin";
    private String adminPassword = "adminpw";

    /**
     * 组织1 msp
     */
    private String org1 = "org1";
    private String org1Msp = "Org1MSP";

    /**
     * 组织1 ca
     */
    private String caOrg1Url ;
    private String ca1Name = "ca-org1";
    private String ca1TlsFile ;

    /**
     * 组织1 peer0 节点
     */
    private String org1Peer0;
    private String org1Peer0Url;

    /**
     * 组织2 peer0 节点
     */
    private String org2Peer0;
    private String org2Peer0Url;

    /**
     * 排序 orderer 节点
     */
    private String ordererName;
    private String ordererUrl;

    @PostConstruct
    public void init(){
        /**
         * 组织1 ca
         */
        caOrg1Url = "https://" + server + ":7054";
        ca1TlsFile = basePath + "data" + File.separator + "crypto-config" + File.separator + "peerOrganizations"
                + File.separator + "org1." + domainName + File.separator+ "ca" + File.separator + "ca.org1." + domainName + "-cert.pem";

        /**
         * 组织1 peer0 节点
         */
        org1Peer0 = "peer0.org1." + domainName;
        org1Peer0Url = GRPC + server + ":7051";

        /**
         * 组织2 peer0 节点
         */
        org2Peer0 = "peer0.org2." + domainName;
        org2Peer0Url = GRPC + server + ":9051";

        /**
         * 排序 orderer 节点
         */
        ordererName = "orderer." + domainName;
        ordererUrl = GRPC + server + ":7050";
    }


}
