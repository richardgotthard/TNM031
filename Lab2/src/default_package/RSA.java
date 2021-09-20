package default_package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.SecureRandom;
//This class provides a cryptographically strong random number generator (RNG).


//Write an implementation of the RSA algorithm in java.
//Your program must have the following functionalities. It must be able to
// generate public and private keys.
// encrypt a plain text message given one key.
// decrypt the ciphertext message given the other key.

public class RSA {
	
	
	//Generating a large prime for a BigInt
	private BigInteger largePrime() {
		return BigInteger.probablePrime(1024, new SecureRandom());
		
	};
	
	private static String input = null;
	private BigInteger p = largePrime();
	private BigInteger q = largePrime();
	private BigInteger n = p.multiply(q);
	
	private BigInteger ONE = new BigInteger("1");
	private BigInteger TWO = new BigInteger("2");
	private BigInteger phi = (p.subtract(ONE)).multiply(q.subtract(ONE));

	private BigInteger e = new BigInteger("1");

	//Function for generating e as the formula gcd(e, phi)
	public void gcd_calc() {
		for (e=TWO; e.compareTo(phi) == -1; e.add(ONE)) {
			if(e.gcd(phi)==ONE)
			{
				break;
			}
		}
	};
	
	//encrypting the messange
	private BigInteger message_l = new BigInteger(input.getBytes());
	private BigInteger c = message_l.modPow(e, n);
	private BigInteger d = e.modInverse(phi);
	
	//decrypting the message
	private BigInteger decryptedBI = c.modPow(d,n);
	private String decryptedMessage = new String(decryptedBI.toByteArray());
	
	
	public static void main(String args[]) throws IOException {
		
		System.out.println("Hello, enter your message: ");
		input = (new BufferedReader(new InputStreamReader(System.in))).readLine();
		
		RSA Encryption = new RSA();
		
		System.out.println("The message to be decrypted is following: " + Encryption.input);
		System.out.println("encryption key c: " + Encryption.c);
		System.out.println("encryption key n: " + Encryption.n);
		System.out.println("Decrypted message: " + Encryption.decryptedMessage);

		
		
	}
};