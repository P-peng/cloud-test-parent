package com.ge.test.service.chain.config;

import java.io.File;

/**
 * @author dengzhipeng
 * @date 2020.5.25
 */

public class FabricConfig {

    private static final String SERVER = "47.102.139.103";
    private static final String D_N = "baolijinglight.com";
    private static final String GRPC ="grpcs://";

    /**
     * 证书所在的文件架
     */
    public static final String BASE_PATH = "D://";

    /**
     * 通道名字
     */
    public static final String CHANNEL_NAME = "mychannel";

    /**
     * 链码名
     */
    public static final String CHANNEL_CODE_NAME = "mycc";

    /**
     * ca 密码
     */
    public static final String ADMIN = "admin";
    public static final String ADMIN_PASSWORD = "adminpw";

    /**
     * 组织1 msp
     */
    public static final String ORG1 = "org1";
    public static final String ORG1_MSP = "Org1MSP";

    /**
     * 组织1 ca
     */
    public static final String CA_ORG1_URL = "https://" + SERVER + ":7054";
    public static final String CA1NAME = "ca-org1";
    public static final String CA1_TLSFILE = BASE_PATH + "data" + File.separator + "crypto-config" + File.separator + "peerOrganizations"
            + File.separator + "org1." + D_N + File.separator+ "ca" + File.separator + "ca.org1." + D_N + "-cert.pem";

    /**
     * 组织1 peer0 节点
     */
    public static final String ORG1_PEER_0 = "peer0.org1." + D_N;
    public static final String ORG1_PEER_0_URL = GRPC + SERVER + ":7051";

    /**
     * 组织2 peer0 节点
     */
    public static final String ORG2_PEER_0 = "peer0.org2." + D_N;
    public static final String ORG2_PEER_0_URL = GRPC + SERVER + ":9051";

    /**
     * 排序 orderer 节点
     */
    public static final String ORDERER_NAME = "orderer." + D_N;
    public static final String ORDERER_URL = GRPC + SERVER + ":7050";


}
