package io.github.kuyer.util;

import java.util.UUID;

public class SequenceUtil {
	
	public static String getSequence() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static void main(String[] args) {
		System.out.println(getSequence());
	}

}
