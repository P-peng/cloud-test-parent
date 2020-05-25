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

package com.ge.test.service.chain.util;

import com.ge.test.service.chain.config.FabricConfig;
import com.ge.test.service.chain.user.CAEnrollment;
import com.ge.test.service.chain.user.UserContext;
import org.hyperledger.fabric.sdk.exception.CryptoException;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class Util {

	/**
	 * Serialize user
	 * 
	 * @param userContext
	 * @throws Exception
	 */
	public static void writeUserContext(String fabricUserPath,UserContext userContext) throws Exception {
		String directoryPath = fabricUserPath + "/users/" + userContext.getAffiliation();
		String filePath = directoryPath + "/" + userContext.getName() + ".ser";
		File directory = new File(directoryPath);
		if (!directory.exists())
			directory.mkdirs();

		FileOutputStream file = new FileOutputStream(filePath);
		ObjectOutputStream out = new ObjectOutputStream(file);

		// Method for serialization of object
		out.writeObject(userContext);

		out.close();
		file.close();
	}

	/**
	 * Deserialize user
	 * 
	 * @param affiliation
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public static UserContext readUserContext(String fabricUserPath, String affiliation, String username) throws Exception {
		String filePath = fabricUserPath + "/data/users/" + affiliation + "/" + username + ".ser";
		File file = new File(filePath);
		if (file.exists()) {
			// 读文件
			FileInputStream fileStream = new FileInputStream(filePath);
			ObjectInputStream in = new ObjectInputStream(fileStream);

			// 以流的方式反序列化
			UserContext uContext = (UserContext) in.readObject();

			in.close();
			fileStream.close();
			return uContext;
		}

		return null;
	}

	public static UserContext readAdminUserContext(String fabricUserPath, String affiliation) throws Exception {
		String filePath = fabricUserPath + "/users/" + affiliation + "/admin.ser";
		File file = new File(filePath);
		if (file.exists()) {
			// 读文件
			FileInputStream fileStream = new FileInputStream(filePath);
			ObjectInputStream in = new ObjectInputStream(fileStream);

			// 以流的方式反序列化
			UserContext uContext = (UserContext) in.readObject();

			in.close();
			fileStream.close();
			return uContext;
		}

		return null;
	}

	/**
	 * Create enrollment from key and certificate files.
	 * 
	 * @param keyFileName
	 * @param certFileName
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws CryptoException
	 */
	public static CAEnrollment getEnrollment(String keyFolderPath, String keyFileName, String certFolderPath,
											 String certFileName)
			throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, CryptoException {
		PrivateKey key = null;
		String certificate = null;
		InputStream isKey = null;
		BufferedReader brKey = null;

		try {

			isKey = new FileInputStream(keyFolderPath + File.separator + keyFileName);
			brKey = new BufferedReader(new InputStreamReader(isKey));
			StringBuilder keyBuilder = new StringBuilder();

			for (String line = brKey.readLine(); line != null; line = brKey.readLine()) {
				if (line.indexOf("PRIVATE") == -1) {
					keyBuilder.append(line);
				}
			}

			certificate = new String(Files.readAllBytes(Paths.get(certFolderPath, certFileName)));

			byte[] encoded = DatatypeConverter.parseBase64Binary(keyBuilder.toString());
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
			KeyFactory kf = KeyFactory.getInstance("ECDSA");
			key = kf.generatePrivate(keySpec);
		} finally {
			isKey.close();
			brKey.close();
		}

		CAEnrollment enrollment = new CAEnrollment(key, certificate);
		return enrollment;
	}

	public static void cleanUp() {
		String directoryPath = "users";
		File directory = new File(directoryPath);
		deleteDirectory(directory);
	}

	public static boolean deleteDirectory(File dir) {
		if (dir.isDirectory()) {
			File[] children = dir.listFiles();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDirectory(children[i]);
				if (!success) {
					return false;
				}
			}
		}

		// either file or an empty directory
		Logger.getLogger(Util.class.getName()).log(Level.INFO, "Deleting - " + dir.getName());
		return dir.delete();
	}

	/**
	 * 读取 TLS 证书，并装配配置对象
	 * @param type
	 * @param name
	 * @return
	 */
	static public Properties gete2ePro(String type, String name) {
		String orgName = name.substring(name.indexOf(".")+1);
		Properties pro = new Properties();

		File cert = Paths.get(FabricConfig.BASE_PATH +"data" , "crypto-config",
				type + "Organizations", orgName, type + "s", name, "tls", "server.crt").toFile();
		if (!cert.exists()) {
			throw new RuntimeException(String.format("Missing cert file for : %s.Could not find at location : %s", name,
					cert.getAbsolutePath()));
		}
		pro.setProperty("pemFile", cert.getAbsolutePath());
		pro.setProperty("hostnameOverride", name);
		pro.setProperty("sslProvider", "openSSL");
		pro.setProperty("negotiationType", "TLS");
		return pro;

	}
//	public static void main(String[] args) {
//		Properties pro = gete2ePro("orderer",Config.ORDERER_NAME);
//	}

}
