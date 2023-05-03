import java.util.Scanner;

class OneTimePadCipher {
  public static int ALPHABET_SIZE = 26;

  public static String encrypt(String plainText, String key) {
    // Convert to lowercase
    plainText = plainText.toLowerCase();
    key = key.toLowerCase();

    // Convert to int array
    int[] plainTextInt = textToNumber(plainText);
    int[] keyInt = textToNumber(key);

    // Add key to plain text and mod by alphabet size
    int[] cipherTextInt = new int[plainTextInt.length];
    for (int i = 0; i < plainTextInt.length; i++) {
      cipherTextInt[i] = (plainTextInt[i] + keyInt[i]) % ALPHABET_SIZE;
    }

    // Convert to string
    String cipherText = numberToText(cipherTextInt);

    return cipherText;
  }

  public static String decrypt(String cipherText, String key) {
    // Convert to lowercase
    cipherText = cipherText.toLowerCase();
    key = key.toLowerCase();

    // Convert to int array
    int[] cipherTextInt = textToNumber(cipherText);
    int[] keyInt = textToNumber(key);

    // Subtract key from cipher text and mod by alphabet size
    int[] plainTextInt = new int[cipherTextInt.length];
    for (int i = 0; i < plainTextInt.length; i++) {
      plainTextInt[i] = (cipherTextInt[i] - keyInt[i] + ALPHABET_SIZE) % ALPHABET_SIZE;
    }

    // Convert to string
    String plainText = numberToText(plainTextInt);

    return plainText;
  }

  public static int[] textToNumber(String text) {
    int[] textInt = new int[text.length()];

    for (int i = 0; i < text.length(); i++) {
      textInt[i] = text.charAt(i) - 'a';
    }

    return textInt;
  }

  public static String numberToText(int[] number) {
    StringBuilder text = new StringBuilder();

    for (int i = 0; i < number.length; i++) {
      text.append((char) (number[i] + 'a'));
    }

    return text.toString();
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    while (true) {
      // ask the user to enter the text to be encrypted
      System.out.print("Enter plain text: ");
      String plainText = input.nextLine();
      plainText = plainText.replaceAll("\\s+", "");

      String key;

      while (true) {
        // ask the user to enter the key
        System.out.print("Enter key: ");
        key = input.nextLine();

        key = key.replaceAll("\\s+", "");

        // check if the key is valid
        if (key.length() == plainText.length()) {
          break;
        } else {
          System.out.println("Key length must be equal to plain text length");
        }
      }

      String cipherText = encrypt(plainText, key);
      System.out.println("Cipher text: " + cipherText);

      String decryptedText = decrypt(cipherText, key);
      System.out.println("Decrypted text: " + decryptedText);

      // ask the user if they want to continue
      System.out.print("Do you want to continue? (Y/N) :");
      String choice = input.nextLine();

      // if the user enters N or n, exit the program
      if (choice.equals("N") || choice.equals("n")) {
        System.out.println("Exiting...");
        break;
      }
    }

    input.close();
  }
}
