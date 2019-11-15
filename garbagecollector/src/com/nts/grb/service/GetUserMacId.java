package com.nts.grb.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

public class GetUserMacId {

	//getClientIPAddress
	public static String getClientMACAddress(HttpServletRequest request) {
		String iP_add = null;
		if (request.getHeader("x-forwarded-for") == null) {
			iP_add = request.getRemoteAddr();
//			System.out.println("ip address:----"+iP_add);
			return iP_add;
		}

		iP_add = request.getHeader("x-forwarded-for");
//		System.out.println("ip address:----"+iP_add);
		return iP_add;
	}
	
	public static String getClientMACAddress_1(HttpServletRequest request) throws SocketException, UnknownHostException {
		String clientIp = null;
		if (request.getHeader("x-forwarded-for") == null) {
			clientIp = request.getRemoteAddr();
			System.out.println("alalala:---------"+clientIp);

		}
		String str = "";
		String macAddress = "dev";
		try {
			Process p = Runtime.getRuntime().exec("nbtstat -A " + clientIp);
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC Address") > 1) {
						macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length());
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		// adc();
		System.out.println("macaddress:----" + macAddress);
		macAddress = "dev";
		return "dev";
	}

	public static void adc() throws SocketException, UnknownHostException {
		NetworkInterface network = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
		byte[] mac = network.getHardwareAddress();

		System.out.print("Current MAC address : ");

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mac.length; i++) {
			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
		}
		String macid = sb.toString();
		System.out.println("user mac id:--" + macid);
	}

	public static void main(String arg[]) throws SocketException, UnknownHostException {
		GetUserMacId.adc();
	}

}
