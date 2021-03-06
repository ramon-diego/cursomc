package com.ramonrocha.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	public static List<Integer> decodeIntList(String arg) {
		String[] vet = arg.split(",");

		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < vet.length; i++) {
			list.add(Integer.parseInt(vet[i]));
		}
		return list;
	}

	public static String decodeParam(String param) {
		try {
			return URLDecoder.decode(param, "UTF-8").toLowerCase();
		} catch (UnsupportedEncodingException e) {
			return "";
		}

	}
}
