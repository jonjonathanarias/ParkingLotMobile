package com.example.parkinglotmovile;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Encrypter {

	public String encrypt(String key, String string) {

		String result = null;
		char chaR = 0;
		String keychar = null;

		result = "";

		for (int i = 0; i < string.length(); i++) {

			chaR = string.substring(i, i + 1).charAt(0);

			keychar = (((i % key.length()) - 1) < 0
					? key.substring((key.length() + ((i % key.length()) - 1)),
							(key.length() + (((i % key.length()) - 1) + 1)))
					: key.substring(((i % key.length()) - 1), (i % key.length())));

			chaR = this.chr(this.ord(String.valueOf(chaR)) + this.ord(keychar));

			result += chaR;
		}

		result = new String(Base64.getEncoder().encode(result.getBytes(this.getSelectedCharset())));

		return result;
	}

	public String decrypt(String string, String key) {

		String result = null;
		char chaR = 0;
		String keychar = null;

		result = "";
		string = new String(Base64.getDecoder().decode(string), this.getSelectedCharset());

		for (int i = 0; i < string.length(); i++) {

			chaR = string.substring(i, (i + 1)).charAt(0);

			keychar = (((i % key.length()) - 1) < 0
					? key.substring((key.length() + ((i % key.length()) - 1)),
							(key.length() + (((i % key.length()) - 1) + 1)))
					: key.substring(((i % key.length()) - 1), (i % key.length())));

			chaR = this.chr(this.ord(String.valueOf(chaR)) - this.ord(keychar));

			result += chaR;

		}

		return result;
	}

	private Charset getSelectedCharset() {
		return StandardCharsets.ISO_8859_1;
	}

	private int ord(String s) {
		return (int) s.charAt(0);
	}

	private char chr(int asciiValue) {
		return (char) asciiValue;
	}

}
