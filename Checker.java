package checksum;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

public class Checker {

	public static void main(String[] args) throws Exception {
		Checker instance = new Checker();
		if (args.length >= 1) {
			instance.calculate(args[0]);
		} else {
			instance.usage();
		}
	}

	private void usage() {
		System.out.println("Usage: filename");
	}

	public void calculate(String filename) throws Exception {
		MessageDigest mdMD5 = MessageDigest.getInstance("MD5");
		MessageDigest mdSHA1 = MessageDigest.getInstance("SHA-1");
		MessageDigest mdSHA256 = MessageDigest.getInstance("SHA-256");
		MessageDigest mdSHA512 = MessageDigest.getInstance("SHA-512");

		byte[] dataBytes = new byte[5120];
		FileInputStream fis = new FileInputStream(filename);
		int n = 0;
		while ((n = fis.read(dataBytes)) != -1) {
			mdMD5.update(dataBytes, 0, n);
			mdSHA1.update(dataBytes, 0, n);
			mdSHA256.update(dataBytes, 0, n);
			mdSHA512.update(dataBytes, 0, n);
		}
		fis.close();

		String checksumMD5 = String.format("%032x", new BigInteger(1, mdMD5.digest()));
		String checksumSHA1 = String.format("%040x", new BigInteger(1, mdSHA1.digest()));
		String checksumSHA256 = String.format("%064x", new BigInteger(1, mdSHA256.digest()));
		String checksumSHA512 = String.format("%0128x", new BigInteger(1, mdSHA512.digest()));

		System.out.println(filename + "\tMD5\t" + checksumMD5);
		System.out.println(filename + "\tMD5\t" + checksumMD5.toUpperCase());
		System.out.println(filename + "\tSHA1\t" + checksumSHA1);
		System.out.println(filename + "\tSHA1\t" + checksumSHA1.toUpperCase());
		System.out.println(filename + "\tSHA256\t" + checksumSHA256);
		System.out.println(filename + "\tSHA256\t" + checksumSHA256.toUpperCase());
		System.out.println(filename + "\tSHA512\t" + checksumSHA512);
		System.out.println(filename + "\tSHA512\t" + checksumSHA512.toUpperCase());
	}
}