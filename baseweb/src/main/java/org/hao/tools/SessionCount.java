package org.hao.tools;

public class SessionCount {

	private static int sessioncount = 0;

	public static int getSessioncount() {
		return sessioncount;
	}

	public static void ADD() {
		SessionCount.sessioncount++;
	}
	public static void MINUS() {
		SessionCount.sessioncount--;
	}
}
