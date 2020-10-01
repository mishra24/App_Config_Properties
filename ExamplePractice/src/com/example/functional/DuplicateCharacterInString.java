package com.example.functional;

public class DuplicateCharacterInString {

	public static void main(String arg[]) {
		String str = "Sakket";
		String str2 = "";
		int len = str.length();
		int count = 0;
		System.out.println(len);
		char[] c = str.toCharArray();
		for (int i = 0; i <= len; i++) {
			for (int j = i + 1; j < len; j++) {
				if (c[i] == c[j]) {
					str2 += c[j];
					count++;
					break;
				}
			}

		}
		System.out.println(count + "and with dup character........" + str2);
	}
}
