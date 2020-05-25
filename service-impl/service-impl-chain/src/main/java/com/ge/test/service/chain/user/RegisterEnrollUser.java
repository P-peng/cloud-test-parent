/****************************************************** 
 *  Copyright 2018 IBM Corporation 
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 *  you may not use this file except in compliance with the License. 
 *  You may obtain a copy of the License at 
 *  http://www.apache.org/licenses/LICENSE-2.0 
 *  Unless required by applicable law or agreed to in writing, software 
 *  distributed under the License is distributed on an "AS IS" BASIS, 
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 *  See the License for the specific language governing permissions and 
 *  limitations under the License.
 */
package com.ge.test.service.chain.user;

import com.ge.test.service.chain.client.CAClient;
import com.ge.test.service.chain.config.FabricConfig;
import com.ge.test.service.chain.util.Util;

/**
 * 
 * @author Balaji Kadambi
 *
 */

public class RegisterEnrollUser {

//	public static void main(String args[]) {
//		try {
//			Util.cleanUp();
//			String caUrl = FabricConfig.CA_ORG1_URL;
//			CAClient caClient = new CAClient(caUrl, null);
//			// Enroll Admin to Org1MSP
//			UserContext adminUserContext = new UserContext();
//			adminUserContext.setName("admin");
//			adminUserContext.setAffiliation(FabricConfig.ORG1);
//			adminUserContext.setMspId(FabricConfig.ORG1_MSP);
//			caClient.setAdminUserContext(adminUserContext);
//			adminUserContext = caClient.enrollAdminUser(FabricConfig.ADMIN, FabricConfig.ADMIN_PASSWORD);
//
//			// Register and Enroll user to Org1MSP
//			UserContext userContext = new UserContext();
//			String name = "user" + System.currentTimeMillis();
//			userContext.setName(name);
//			userContext.setAffiliation(FabricConfig.ORG1);
//			userContext.setMspId(FabricConfig.ORG1_MSP);
//
//			String eSecret = caClient.registerUser(name, FabricConfig.ORG1);
//
//			userContext = caClient.enrollUser(userContext, eSecret);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
