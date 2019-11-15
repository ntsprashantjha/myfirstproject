package com.nts.mrb.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import javax.servlet.http.HttpServletRequest;

public class GetUserMacId {
	/*
	 * public static String getClientIPAddress(HttpServletRequest request) { if
	 * (request.getHeader("x-forwarded-for") == null) { return
	 * request.getRemoteAddr(); } return request.getHeader("x-forwarded-for"); }
	 */

	public static String getClientMACAddress(HttpServletRequest request) {
		String clientIp = null;
		if (request.getHeader("x-forwarded-for") == null) {
			clientIp = request.getRemoteAddr();

		}
		String str = "";
		String macAddress = "";
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
		return macAddress;
	}

}
