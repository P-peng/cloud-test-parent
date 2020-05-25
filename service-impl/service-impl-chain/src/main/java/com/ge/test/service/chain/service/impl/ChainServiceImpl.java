package com.ge.test.service.chain.service.impl;

import com.ge.test.service.chain.client.CAClient;
import com.ge.test.service.chain.client.ChannelClient;
import com.ge.test.service.chain.client.FabricClient;
import com.ge.test.service.chain.config.FabricConfig;
import com.ge.test.service.chain.config.FabricConfigBean;
import com.ge.test.service.chain.enumeration.FabricNodeEnum;
import com.ge.test.service.chain.service.ChainService;
import com.ge.test.service.chain.user.UserContext;
import com.ge.test.service.chain.util.Util;
import org.apache.commons.io.IOUtils;
import org.hyperledger.fabric.sdk.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author dengzhipeng
 * @version 1.0
 * @date 2020/5/25 0025
 */
@Service
public class ChainServiceImpl implements ChainService {

    private static final byte[] EXPECTED_EVENT_DATA = "!".getBytes(UTF_8);
    private static final String EXPECTED_EVENT_NAME = "event";

    private Channel channel;
    private FabricClient fabClient;
    private ChannelClient channelClient ;

    @Autowired
    private FabricConfigBean fabricConfigBean;

    @Value("${fabric.user-path}")
    private String fabricUserPath;

    public String invoke(String key, String temp, String humidity){
        String txId = null;
        try {
            this.initFabricClient();

            TransactionProposalRequest request = fabClient.getInstance().newTransactionProposalRequest();
            ChaincodeID ccid = ChaincodeID.newBuilder().setName("chaincode002").build();
            request.setChaincodeID(ccid);
            request.setFcn("save");
            String[] arguments = {key, temp, humidity};

            request.setArgs(arguments);
//            request.setProposalWaitTime(20000);

            Map<String, byte[]> tm2 = new HashMap<>(4);
//            tm2.put("HyperLedgerFabric", "TransactionProposalRequest:JavaSDK".getBytes(UTF_8));
//            tm2.put("method", "TransactionProposalRequest".getBytes(UTF_8));
//            tm2.put("result", ":)".getBytes(UTF_8));
//            tm2.put(EXPECTED_EVENT_NAME, EXPECTED_EVENT_DATA);
            request.setTransientMap(tm2);
            // 返回的条数的和要发送提案的节点树
            Collection<ProposalResponse> responses = channelClient.sendTransactionProposal(request);
            for (ProposalResponse res: responses) {
                ChaincodeResponse.Status status = res.getStatus();
                txId = res.getTransactionID();
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
        return txId;
    }

    public void initFabricClient() throws Exception {
        if (fabClient == null) {
            UserContext adminUser = new UserContext();
            adminUser.setName(FabricConfig.ADMIN);
            adminUser.setAffiliation(FabricConfig.ORG1);
            adminUser.setMspId(FabricConfig.ORG1_MSP);
            File f = new File(FabricConfig.CA1_TLSFILE);
            String certficate = new String(IOUtils.toByteArray(new FileInputStream(f)), "UTF-8");
            Properties properties = new Properties();
            properties.put("pemBytes", certficate.getBytes());
            properties.setProperty("pemFile", f.getAbsolutePath());
            properties.setProperty("allowAllHostNames", "true");
            CAClient caclient = new CAClient(FabricConfig.CA1NAME, FabricConfig.CA_ORG1_URL, properties);
            caclient.setAdminUserContext(adminUser);
            adminUser = caclient.enrollAdminUserTLS(fabricUserPath, FabricConfig.ADMIN, FabricConfig.ADMIN_PASSWORD);

            FabricClient fabClient = new FabricClient(adminUser);

            ChannelClient channelClient = fabClient.createChannelClient(FabricConfig.CHANNEL_NAME);
            Channel channel = channelClient.getChannel();

            Properties properties1 = Util.gete2ePro(FabricNodeEnum.PEER.getType(), fabricConfigBean.getOrg1Peer0());
            Peer peer = fabClient.getInstance().newPeer(fabricConfigBean.getOrg1Peer0() , fabricConfigBean.getOrg1Peer0Url(),properties1);

            Properties properties2 = Util.gete2ePro(FabricNodeEnum.PEER.getType(), fabricConfigBean.getOrg2Peer0());
            Peer peer2 = fabClient.getInstance().newPeer(fabricConfigBean.getOrg2Peer0(), fabricConfigBean.getOrg2Peer0Url(),properties2);

            Properties orderPro = Util.gete2ePro(FabricNodeEnum.ORDERER.getType(), fabricConfigBean.getOrdererName());
            Orderer orderer = fabClient.getInstance().newOrderer(fabricConfigBean.getOrdererName(), fabricConfigBean.getOrdererUrl(), orderPro);
            channel.addPeer(peer);
            // 背书策略为OR的话不需要添加 peer2
            channel.addPeer(peer2);
            channel.addOrderer(orderer);
            channel.initialize();

            this.fabClient = fabClient;
            this.channel = channel;
            this.channelClient = channelClient;
        }
    }
}
