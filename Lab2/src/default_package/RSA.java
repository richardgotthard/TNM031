package default_package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.SecureRandom;
//This class provides a cryptographically strong random number generator (RNG).


public class RSA {
	
	//Declaring all the variables
	private BigInteger p, q, n, ONE, phi, e, c, d, message_l, decryptedBI;
	private static String input;
	private String decryptedMessage;
	
	public RSA() {
		initialize();
	}
	
	//Generating a large prime for a BigInt
	private BigInteger largePrime() {
		return BigInteger.probablePrime(1024, new SecureRandom());
		
	};
	
	//Class function for assigning values to all variables
	public void initialize() {
	
	p = largePrime(); //prime
	q = largePrime(); //prime
	n = p.multiply(q); //prime
	ONE = new BigInteger("1");
	phi = (p.subtract(ONE)).multiply(q.subtract(ONE)); //(p-1)(q-)
	
	//Function for generating e (encryption exponent)
		do {
			e = BigInteger.probablePrime(1024/2, new SecureRandom()); //prime 
			
		} while(e.compareTo(BigInteger.ONE) <= 0 //e>1
			    || e.compareTo(phi) >= 0 //e<phi
			    || !e.gcd(phi).equals(BigInteger.ONE)); //gcd(e,phi)==1

	//Encrypting the message
	message_l = new BigInteger(input.getBytes());
	d = e.modInverse(phi); //private key, mod(phi)
	c = message_l.modPow(e, n); //public key, (message_l^e)
	
	//Decrypting the message
	decryptedBI = c.modPow(d,n); //(c^d)
	decryptedMessage = new String(decryptedBI.toByteArray());
	
	}
	
	public static void main(String args[]) throws IOException {
		
		System.out.println("Hello, enter your message: ");
		input = (new BufferedReader(new InputStreamReader(System.in))).readLine();
		
		RSA Encryption = new RSA();
		
		System.out.println("The message to be decrypted is following: " + Encryption.input);
		System.out.println("public encryption key c (e,n): " + Encryption.c);
		System.out.println("n: " + Encryption.n);
		System.out.println("e: " + Encryption.e);
		System.out.println("Decrypted message: " + Encryption.decryptedMessage);
		
	}
};

