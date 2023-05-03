import java.util.Scanner;

class VigenereCipher {
	public static String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static String key = "BELIEVE";
	public static int keyLength = key.length();

	public static String encrypt(String text) {
		text = text.toUpperCase();
		String cipherText = "";
		char ch, k;
		int index;
		for (int i = 0; i < text.length(); i++) {
			ch = text.charAt(i);
			k = key.charAt(i % keyLength);
			if (ch >= 'A' && ch <= 'Z') {
				index = (letters.indexOf(ch) + letters.indexOf(k)) % 26;
				cipherText += letters.charAt(index);
			} else {
				cipherText += ch;
			}
		}

		return cipherText;
	}

	public static String decrypt(String text) {
		text = text.toUpperCase();
		String plainText = "";
		char ch, k;
		int index;
		for (int i = 0; i < text.length(); i++) {
			ch = text.charAt(i);
			k = key.charAt(i % keyLength);
			if (ch >= 'A' && ch <= 'Z') {
				index = (letters.indexOf(ch) - letters.indexOf(k) + 26) % 26;
				plainText += letters.charAt(index);
			} else {
				plainText += ch;
			}
		}

		return plainText;
	}

	public static void main(String[] args) {
		// create a scanner object to read the user input
		Scanner sc = new Scanner(System.in);

		// write infinite loop to keep the program running until the user exits
		while (true) {

			// first ask the user if they want to encrypt or decrypt
			System.out.print("Do you want to encrypt or decrypt? (E/D) :");
			String choice = sc.nextLine();

			if (choice.equals("E") || choice.equals("e")) {
				// ask the user to enter the text to be encrypted
				System.out.print("Enter the text to be encrypted: ");
				String text = sc.nextLine();

				// encrypt the text and print the result
				String cipherText = encrypt(text);

				// encrypt the text and print the result
				System.out.println("Encrypted text: " + cipherText);

				// verification
				String decryptedText = decrypt(cipherText);
				System.out.println("Decrypted text: " + decryptedText);
			} else if (choice.equals("D") || choice.equals("d")) {
				// ask the user to enter the text to be decrypted
				System.out.print("Enter the text to be decrypted: ");
				String text = sc.nextLine();

				// decrypt the text and print the result
				String plainText = decrypt(text);

				// encrypt the text and print the result
				System.out.println("Decrypted text: " + plainText);

				// verification
				String encryptedText = encrypt(plainText);
				System.out.println("Encrypted text: " + encryptedText);
			}

			// ask the user if they want to continue
			System.out.print("Do you want to continue? (Y/N) :");
			choice = sc.nextLine();

			// if the user enters N or n, exit the program
			if (choice.equals("N") || choice.equals("n")) {
				System.out.println("Exiting...");
				break;
			}
		}
		sc.close();
	}
}