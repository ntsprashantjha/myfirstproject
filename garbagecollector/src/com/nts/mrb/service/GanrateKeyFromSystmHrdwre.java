package com.nts.mrb.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Base64;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nts.grb.validation.ReturnResponse;

public class GanrateKeyFromSystmHrdwre {
	public static JSONArray hardwareKey(String ship_name, String ship_imo, String company_name)
			throws SocketException, UnknownHostException

	{
		String path = "C:\\abc\\";
		String file_name = "mrbkey.txt";
		try {
			FileWriter fw = new FileWriter(path + file_name);
			Base64.Encoder encoder = Base64.getMimeEncoder();
			String eStr = encoder.encodeToString(getSysMac().getBytes());
			fw.write(eStr);
			fw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ReturnResponse().somethingWentWrong();
		}

		return new ReturnResponse().ganrate_key_success_condition("http://192.168.1.101:3030/abc/" + file_name);

	}

	public static String getSysMac() throws SocketException, UnknownHostException {
		JSONArray systeminfo = new JSONArray();
		JSONObject jsonobj = new JSONObject();
		NetworkInterface network = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
		byte[] mac = network.getHardwareAddress();

		System.out.print("Current MAC address : ");

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mac.length; i++) {
			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
		}
		String macid = sb.toString();
		try {
			jsonobj.put("mac_id", macid);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(macid);

		return macid;
	}

}
