package CaesarCipher;

import java.util.Scanner;

class CaesarCipher {
	public static StringBuffer encrypt(String text, int shift) {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < text.length(); i++) {
			// replace spaces with |
			if (text.charAt(i) == ' ') {
				result.append('|');
				continue;
			}
			if (Character.isUpperCase(text.charAt(i))) {
				char ch = (char) (((int) text.charAt(i) + shift - 65) % 26 + 65);
				result.append(ch);
			} else {
				char ch = (char) (((int) text.charAt(i) + shift - 97) % 26 + 97);
				result.append(ch);
			}
		}
		return result;
	}

	public static StringBuffer decrypt(String text, int shift) {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < text.length(); i++) {
			// replace | with spaces
			if (text.charAt(i) == '|') {
				result.append(' ');
				continue;
			}
			if (Character.isUpperCase(text.charAt(i))) {
				char ch = (char) (((int) text.charAt(i) - shift - 65 + 26) % 26 + 65);
				result.append(ch);
			} else {

				char ch = (char) (((int) text.charAt(i) - shift - 97 + 26) % 26 + 97);
				result.append(ch);
			}
		}
		return result;

	}

	// Driver code
	public static void main(String[] args) {
		// create a scanner object to read the user input
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter the text to be encrypted: ");
		String text = sc.nextLine();

		// ask the user to enter the shift value
		System.out.print("Enter the shift value: ");
		int shift = sc.nextInt();

		// encrypt the text
		String encryptedText = encrypt(text, shift).toString();

		// decrypt the text
		String decryptedText = decrypt(encryptedText, shift).toString();

		// print the encrypted text
		System.out.println("Encrypted text: " + encryptedText);

		// print the decrypted text
		System.out.println("Decrypted text: " + decryptedText);

		sc.close();
	}
}
