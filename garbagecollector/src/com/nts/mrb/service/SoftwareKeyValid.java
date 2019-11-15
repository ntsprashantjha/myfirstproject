package com.nts.mrb.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Base64;
import java.util.Scanner;

import org.json.JSONArray;

import com.nts.grb.validation.ReturnResponse;
import com.nts.mrb.dao.SoftwareLicenceTime;

public class SoftwareKeyValid {
	public JSONArray keyDecode(String key1) {
		try {
			String key = GanrateKeyFromSystmHrdwre.getSysMac() + key1;
			StringBuffer bc = new StringBuffer();
			Scanner sc = new Scanner(new File("C:\\abc\\mrbkey.txt"));
			sc.useDelimiter("");
			while (sc.hasNext()) {
				String s = sc.next();

				bc.append(s);

			}
			System.out.println(bc);
			Base64.Decoder decoder = Base64.getMimeDecoder();
			// Decoding MIME encoded message
			String dStr = new String(decoder.decode(bc.toString()));
			String[] softwareValidationDate=dStr.split("-");
			System.out.println("ajn" + dStr + "**both**" + key);
			if (dStr.contains(key1)) {
				if (dStr.contains(key)) {
					new SoftwareLicenceTime().insertDate(softwareValidationDate[1]);
					return new ReturnResponse().success_condition();
				} else {
					return new ReturnResponse().keyOrMachineNotMatch();

				}
			} else {
				return new ReturnResponse().keyOrMachineNotMatch();

			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ReturnResponse().somethingWentWrong();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ReturnResponse().somethingWentWrong();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ReturnResponse().keyfilenotfound();
		}
	}

}
