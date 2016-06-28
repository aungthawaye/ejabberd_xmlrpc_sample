package com.gnomesys.poc.ejabberd;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class TestXMLRPC {
	public static void main(String args[]) throws MalformedURLException, XmlRpcException {
		register("test01", "password");
		register("test02", "password");
		addRosterTo("test02", "test01");
		addRosterTo("test01", "test02");
	}

	public static XmlRpcClient getClient() throws MalformedURLException {
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL("http://localhost:4560/RPC2"));
		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);
		return client;
	}

	public static Map<String, Object> getAdminCredential() {
		Map<String, Object> admin = new HashMap<String, Object>();
		
		// Your admin account information
		admin.put("user", "admin");
		admin.put("server", "im.gnomesys.com");
		admin.put("password", "password");
		return admin;
	}

	public static void printResponse(Map<Object, Object> map) {
		for (Object key : map.keySet()) {
			System.out.println(key.toString() + " : " + map.get(key).toString());
		}
	}

	@SuppressWarnings("unchecked")
	public static void register(String newUsername, String password) throws MalformedURLException, XmlRpcException {

		XmlRpcClient xmlRpcClient = getClient();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user", newUsername);
		params.put("host", "im.gnomesys.com");
		params.put("password", password);

		HashMap<Object, Object> hashMap = (HashMap<Object, Object>) xmlRpcClient.execute("register",
				new Object[] { getAdminCredential(), params });

		printResponse(hashMap);
	}

	@SuppressWarnings("unchecked")
	public static void addRosterTo(String roster, String localUser) throws MalformedURLException, XmlRpcException {

		XmlRpcClient xmlRpcClient = getClient();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("localuser", localUser);
		params.put("localserver", "im.gnomesys.com");
		params.put("user", roster);
		params.put("server", "im.gnomesys.com");
		params.put("nick", roster);
		params.put("group", "buddy");
		params.put("subs", "both");

		HashMap<Object, Object> hashMap = (HashMap<Object, Object>) xmlRpcClient.execute("add_rosteritem",
				new Object[] { getAdminCredential(), params });

		printResponse(hashMap);
	}
}
