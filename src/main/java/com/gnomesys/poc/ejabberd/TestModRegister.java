package com.gnomesys.poc.ejabberd;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.iqregister.AccountManager;

public class TestModRegister {
	public static void main(String args[]) throws SmackException, IOException, XMPPException, InterruptedException,
			KeyManagementException, NoSuchAlgorithmException {
		// Create a connection to the jabber.org server on a specific port.
		XMPPTCPConnectionConfiguration.Builder config = XMPPTCPConnectionConfiguration.builder()
				.setServiceName("im.gnomesys.com").setHost("localhost").setResource("admin@im.gnomesys.com/smack")
				.setPort(5222).setSecurityMode(SecurityMode.disabled).setDebuggerEnabled(true);

		XMPPTCPConnection.setUseStreamManagementDefault(true);

		AbstractXMPPConnection xmppConnection = new XMPPTCPConnection(config.build());
		xmppConnection.connect();
		// Please see ejabberd.yml in ejabberd_config folder
		// I allow only admin to be able to create new user account.
		xmppConnection.login("admin", "password");

		AccountManager accountManager = AccountManager.getInstance(xmppConnection);
		accountManager.sensitiveOperationOverInsecureConnection(true);
		accountManager.createAccount("test01_mod_register", "password");
	}
}
