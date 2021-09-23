package com.kevinyin.lio;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
@Slf4j
public class ServiceAgentToolkit {
	// Serialize an object to byte[]. Used for TCPIP message serialization
	public static byte[] serialize(Serializable obj) throws IOException {
		ObjectOutputStream os = null;
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			os = new ObjectOutputStream(out);
			os.writeObject(obj);
			return out.toByteArray();
		}finally {
			if (os != null){
				os.close();
			}
		}
	}
	// read in an object from a byte[]. Used for TCPIP message serialization and POSService/Hash of POSServices 
	// deserialization (from the TCPIPMessage:messageData, for example).
	public static Serializable deserialize(byte[] data) throws IOException, ClassNotFoundException {
		ObjectInputStream is = null;
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(data);
			is = new ObjectInputStream(in);
			return (Serializable) is.readObject();
		}finally {
			is.close();
		}
	}

	/**
	 * getSubnetMask(InetAddress ip) returns the subnet mask of this ip on the CURRENT system
	 * 
	 * @param ip
	 *            : InetAddress IP address
	 * @return : the subnet mask for this ip address on this system which this code is running on
	 */
	public static String getSubnetMask(InetAddress ip) throws IOException, InterruptedException {
		String subnetMask = "";
		String os = System.getProperty("os.name");
		String ipV4 = null;
		if (os.indexOf("Windows 7") >= 0) {
			ipV4 = ip.getHostAddress();
			Process process = Runtime.getRuntime().exec("ipconfig");
			process.waitFor();
			InputStream commandOut = process.getInputStream();

			BufferedReader in= null;
			try {
				in = new BufferedReader(new InputStreamReader(commandOut));
				String line;
				while ((line = in.readLine()) != null) {
					if (line.indexOf(ipV4) >= 0) {
						line = in.readLine();
						int colon = line.indexOf(":");
						subnetMask = line.substring(colon + 2).trim();
						break;
					}
				}
			}catch (Exception e){
				log.error("getSubnetMask....",e);
			}finally {
				if(in != null){
					try {
						in.close();
					}catch (Exception e){}

				}
			}

		} else if (os.indexOf("Windows XP") >= 0) {
			ipV4 = ip.getHostAddress().replace("/", "");
			Process process = Runtime.getRuntime().exec("ipconfig");
			process.waitFor();
			InputStream commandOut = process.getInputStream();

			BufferedReader in = new BufferedReader(new InputStreamReader(commandOut));
			String line;
			while ((line = in.readLine()) != null) {
				if (line.indexOf(ipV4) >= 0) {
					line = in.readLine();
					line = in.readLine();
					int colon = line.indexOf(":");
					subnetMask = line.substring(colon + 2).trim();
					break;
				}
			}
		}
		return subnetMask;
	}

	/**
	 * getBroadcastIP(InetAddress ip) returns the broadcast ip on the CURRENT system (based on the given ip)
	 * The broadcast address has a 1 bit where the subnet mask has a 0 bit.
	 * Broadcast = ip-addr OR the inverted subnet-mask (broadcast = ip | ( ~ subnet ))
	 * 
	 * @param ip
	 *            : InetAddress IP address
	 * @return : the broadcast ip or "" if fails
	 */
	public static String getBroadcastIP(InetAddress ip) throws IOException, InterruptedException{
		if (ip == null) {
			return "";
		}
		String subnetIP = getSubnetMask(ip);
		String localIP = ip.getHostAddress();
		if(subnetIP.equals("") || localIP.equals("")){
			return "";
		}
		int subnetIntIP = ipv4String2int(subnetIP);
		int localIntIP = ipv4String2int(localIP);
		int invertedSubnetIntIP = ~subnetIntIP;
		return int2IPv4(localIntIP | invertedSubnetIntIP);
	}

	/**
	 * broadcastMessage(InetAddress ip, int port, byte[] message): broadcasts the given message on the subnet which the
	 * IP is on by using given port
	 * 
	 * @param ip
	 *            : local IP, not subnet IP. This will be used to find the subnet ip on the current system
	 * @param port
	 *            : broadcast port number
	 * @param message
	 *            : the byte[] info to be sent out
	 * @return : successfully ? true: false;
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static boolean broadcastMessage(InetAddress ip, int port, byte[] message) throws IOException,
			InterruptedException {
		String bIP = getBroadcastIP(ip);
		if(bIP != null && !bIP.equals("")){
	        InetAddress host = InetAddress.getByName(bIP);
	        DatagramSocket socket = new DatagramSocket (null);
	        DatagramPacket packet=new DatagramPacket (message, message.length, host, port);
	        socket.send (packet);	
	        //log.info("Message <" + new String(message) + "> send to " + host.getHostAddress() + " on port " + port);
	        log.info("Message <" + new String(packet.getData()) + "> send to " + host.getHostAddress() + " on port " + port);
	        socket.close();
			return true;
		}
		return false;
	}
	
	public static int bytes2Int(byte[] bytes) {
	    return   bytes[3] & 0xFF |
	            (bytes[2] & 0xFF) << 8 |
	            (bytes[1] & 0xFF) << 16 |
	            (bytes[0] & 0xFF) << 24;
	}
	public static byte[] int2Bytes(int i) {
		return new byte[] {
				(byte) ((i >> 24) & 0xFF),
				(byte) ((i >> 16) & 0xFF),
				(byte) ((i >> 8) & 0xFF),
				(byte) (i & 0xFF) };
	}

	public static String int2IPv4(int ip) {
		byte[] ipByte = int2Bytes(ip);
		return (256 + ipByte[0]) % 256 + "." + (256 + ipByte[1]) % 256 + "." + (256 + ipByte[2]) % 256 + "."
				+ (256 + ipByte[3]) % 256;
	}

	public static int ipv4String2int(String ipv4String) {
		String[] parts = ipv4String.split("\\.");
		return bytes2Int( new byte[] {
				(byte) Integer.parseInt(parts[0]),
				(byte) Integer.parseInt(parts[1]),
				(byte) Integer.parseInt(parts[2]),
				(byte) Integer.parseInt(parts[3])
				});
	}

	/**
	 * ?????????????????????????????????????????
	 *
	 * ???:????????????????zip???,?????????????????????.
	 */
	public static String getPassword(String filename) {
		String password = "";
		String temp = filename.toUpperCase();
		try {
			/**
			 * TSPOS1234_20140306_V1.0.0.5_full.zip, TSPOS1234_20140601_V1.0.0.2.zip,
			 * TSCONFIG_STO1234_20140228_0003_Full.zip, TSCONFIG_STO1234_20140401_0002.zip ,
			 */

			if (temp.toUpperCase().endsWith("FULL")) {// 10404102STO12340002
				password = "1";
			} else {
				password = "0";
			}

			if (temp.toUpperCase().startsWith("TSPOS")) {
				temp = temp.substring(temp.indexOf("_") + 1, temp.length());
				String version = "";
				if (temp.toUpperCase().endsWith("FULL")) {
					version = temp.substring(temp.indexOf("_") + 1, temp.lastIndexOf("_"));
				} else {
					version = temp.substring(temp.indexOf("_") + 1, temp.length());
				}

				temp = temp.substring(0, temp.indexOf("_"));
				String transfer = transferString(temp);

				password = transfer + version + password;

			} else if (temp.toUpperCase().startsWith("TSCONFIG")) {

				temp = temp.substring(temp.indexOf("_") + 1, temp.length());// STO1234_20140401_0002
				String storename = temp.substring(0, temp.indexOf("_"));
				temp = temp.substring(temp.indexOf("_") + 1, temp.length());
				String temp1 = temp.substring(0, temp.indexOf("_"));
				String transfer = transferString(temp1);
				String version = "";
				if (temp.toUpperCase().endsWith("FULL")) {
					version = temp.substring(temp.indexOf("_") + 1, temp.lastIndexOf("_"));
				} else {
					version = temp.substring(temp.indexOf("_") + 1, temp.length());
				}
				password = transfer + storename + version + password;
			}

		} catch (Exception ee) {
			log.error("Exception in ServiceAgentToolkit:getPassword(" +  filename  + ") - ", ee);
			return null;
		}
		// System.out.println("filename ===" + filename + "       password==" + password);
		return password;
	}

	/**
	 * ??????20140701???01072014,??????
	 *
	 * @param oldString
	 * @return
	 */
	private static String transferString(String oldString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = null;
		java.util.Date dt = null;
		try {
			dt = sdf.parse(oldString);
			sdf2 = new SimpleDateFormat("ddMMyyyy");

		} catch (ParseException e) {
			log.error("Exception in ServiceAgentToolkit:transferString(" + oldString + ") - ", e);
		}
		return sdf2.format(dt);
	}

	public static void writeData2File(String fileName, byte[] data) { // throws IOException
		File f = new File(fileName);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f);
			fos.write(data);
			fos.close();
		} catch (IOException ioe) {
			soutMonitorInfo();
			log.error("ServiceAgentToolkit:writeData2File - When write file " + fileName + " got exception", ioe);
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static Object recoverObjectFromFile(String fileName){ // throws IOException, ClassNotFoundException
		Object obj = null;
		File file  = new File(fileName);
		FileInputStream fis = null;
		int bytes = 0;
		if(file.exists()){
			byte[] content = new byte[(int) file.length()];
			try {
				fis = new FileInputStream(file);
				bytes = fis.read(content);
				fis.close();
			} catch (IOException e) {
				soutMonitorInfo();
				log.error("ServiceAgentToolkit:recoverObjectFromFile - When read file " + file + " got exception", e);
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						log.error("ServiceAgentToolkit:recoverObjectFromFile - When close file " + fileName + " got exception", e);
					}
				}
			}
			if (bytes > 0) {
				try {
					obj = deserialize(content);
				} catch (Exception e) {
					log.error("ServiceAgentToolkit:recoverObjectFromFile - When deserialize file " + file + " got exception", e);
				}
			}
		}else{
			try {
				file.createNewFile();
			} catch (IOException e) {
				log.error("ServiceAgentToolkit:recoverObjectFromFile - When create file " + file + " got exception", e);
			}
		}
		return obj;
	}

	public static void saveObjectToFile(Serializable obj, String fileName) {  // throws IOException
		File file  = new File(fileName);
		FileOutputStream fos = null;
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				soutMonitorInfo();
				log.error("ServiceAgentToolkit:saveObjectToFile - When create file " + fileName + " got exception", e);
			}
		}
		try {
			fos = new FileOutputStream(file);
			fos.write(serialize(obj));
			fos.close();
		} catch (IOException e) {
			soutMonitorInfo();
			log.error("ServiceAgentToolkit:saveObjectToFile - When creat/write file " + fileName + " got exception", e);
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					log.error("ServiceAgentToolkit:saveObjectToFile - When close file " + fileName + " got exception", e);
				}
			}
		}
	}

	public static void appendInfo(String infoName,String text){
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(infoName, true)));
			out.write(text);
		} catch (Exception e) {
			log.error("Exception in ServiceAgentToolkit:appendInfo(" +  infoName + "," +  text +") when write - ", e);
		} finally {
			try {
				if(out != null) {
					out.close();
				}
			} catch (IOException e) {
				log.error("Exception in ServiceAgentToolkit:appendInfo(" +  infoName + "," +  text +") when close - ", e);
			}
		}
	}

	public static byte[] readFileContent2Bytes ( String fileName) {
		byte[] content = null;
		File file  = new File(fileName);
		FileInputStream fis = null;
		int bytes = 0;
		if(file.exists()){
			content = new byte[(int) file.length()];
			try {
				fis = new FileInputStream(file);
				bytes = fis.read(content);
				fis.close();
			} catch (IOException e) {
				log.error("ServiceAgentToolkit:readFileContent2Bytes when recoverObjectFromFile - When read file " + file + " got exception", e);
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						log.error("ServiceAgentToolkit:readFileContent2Bytes when recoverObjectFromFile - When close file " + fileName + " got exception", e);
					}
				}
			}
		}
		return content;
	}

	public static void deleteFile(String filePath){
		File path=new File(filePath);
		if(path.exists()){
			path.delete();
		}
	}

	public static boolean fileExists(String fileName) {
		return (new File(fileName)).exists();
	}
	/**
	 * @return
	 */
	public static String sendPlateNumberPosition(String ip, int port, int timeout,String msg) throws Exception {
		log.info("sendPlateNumberPosition...ip:" + ip + ",port:" + port + ",msg:" + msg);
		String ackmsg = null;
		Socket socket = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			socket = new Socket();
			socket.setSoTimeout(timeout);
			socket.connect(new InetSocketAddress(ip, port), timeout);

			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(msg);

			ois = new ObjectInputStream(socket.getInputStream());
			ackmsg = (String) ois.readObject();
		} catch (Exception e) {
			throw e;
		} finally {
			if(ois != null){
				ois.close();
			}
			if(oos != null){
				oos.close();
			}
			if(socket != null){
				socket.close();
			}
		}
		return ackmsg;
	}
//	/**
//	 * @return
//	 */
//	public static boolean sendPlateNumberPosition(String ip, int port, int timeout,String msg){
//		log.info("sendPlateNumberPosition...ip:" + ip + ",port:" + port + ",msg:" + msg);
//		boolean res = false;
//		int retry = ConfigurationManager.getConfigurationManager().localTableServiceRetryConnection;//??????????????
//		int connectionQty = 0;
//		Socket socket = null;
//		ObjectOutputStream oos = null;
//		ObjectInputStream ois = null;
//		for(int i = 0;i < retry;i++){
//			try{
//				socket = new Socket();
//				socket.setSoTimeout(timeout);
//				socket.connect(new InetSocketAddress(ip, port), timeout);
//
//				oos = new ObjectOutputStream(socket.getOutputStream());
//				oos.writeObject(msg);
//
//				ois = new ObjectInputStream(socket.getInputStream());
//				String ackmsg = (String) ois.readObject();
//				if(ackmsg.equals(msg)){
//					//?????????????????????
//					res = true;
//					break;
//				}else {
//					//????????????????????????????,??????1s?????????
//					Thread.sleep(1000);
//				}
//
//			}catch(SocketTimeoutException e){
//				log.error("Exception!!", e);
//				if(connectionQty == 0){
//					connectionQty++;
//					continue;
//				}else{
//					res = false;
//					break;
//				}
//			} catch (Exception e) {
//				log.error("Exception in sendPlateNumberPosition !!",e);
//				res = false;
//			} finally {
//				try {
//					if(ois != null){
//						ois.close();
//					}
//					if(oos != null){
//						oos.close();
//					}
//					if(socket != null){
//						socket.close();
//					}
//				} catch (IOException e) {
//					log.error("IOException in sendPlateNumberPosition !!" , e);
//				}
//			}
//
//		}
//		return res;
//	}

	public static boolean findProcess(String processName) {
		log.info("POSControllerToolKit -- findProcess  processName=" + processName);
		BufferedReader input = null;
		try {
	        String line = null;
	        Process p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
	        input = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        int count = 0;
	        while ((line = input.readLine()) != null) {
	            if(line.contains(processName)){
	            	count ++;
	            }
	            if(count >= 2){
	            	return true;
	            }
	        }
	        return false;
	    } catch (Exception e) {
			log.error("POSControllerToolKit -- findProcess  error...");
			return false;
	    }finally{
	    	if(input != null){
	    		try {
					input.close();
				} catch (IOException e) {
					log.error("POSControllerToolKit -- findProcess -- input.close error...");
				}
	    	}
	    }
	}


	public static void soutMonitorInfo() {
		/* Total number of processors or cores available to the JVM */
		log.info("Available processors (cores): " +
				Runtime.getRuntime().availableProcessors());
//		System.out.println("Available processors (cores): " +
//				Runtime.getRuntime().availableProcessors());

		/* Total amount of free memory available to the JVM */
		log.info("Free memory (bytes): " +
				Runtime.getRuntime().freeMemory()/ 1024 / 1024  +"M");

		/* This will return Long.MAX_VALUE if there is no preset limit */
		long maxMemory = Runtime.getRuntime().maxMemory();
		/* Maximum amount of memory the JVM will attempt to use */
		log.info("Maximum memory (bytes): " +
				(maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory / 1024 / 1024 +"M"));

		/* Total memory currently available to the JVM */
		log.info("Total memory available to JVM (bytes): " +
				Runtime.getRuntime().totalMemory() / 1024 / 1024 +"M");

		/* Get a list of all filesystem roots on this system */
		File[] roots = File.listRoots();

		/* For each filesystem root, print some info */
		for (File root : roots) {
			log.info("File system root: " + root.getAbsolutePath());
			log.info("Total space (bytes): " + root.getTotalSpace() / 1024 / 1024/1024 + "G");
			log.info("Free space (bytes): " +  root.getFreeSpace() / 1024 / 1024/1024 + "G");
			log.info("Usable space (bytes): " + root.getUsableSpace() / 1024 / 1024 /1024 + "G");
		}
	}
}
