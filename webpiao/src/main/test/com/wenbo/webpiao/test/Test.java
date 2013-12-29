package com.wenbo.webpiao.test;

import java.net.URI;
import java.net.URISyntaxException;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			URI uri  = new URI("http://127.0.0.1:6379/wenbo");
			System.out.println(uri.getUserInfo());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
