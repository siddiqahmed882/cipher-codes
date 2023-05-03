package PlayFairCipher;

import java.util.*;

class PlayFairCipher {

  public char[][] keyMatrix;

  public PlayFairCipher(String key) {
    this.keyMatrix = new char[5][5];
    String keyWithoutDuplicates = removeDuplicateChars(key.replace(" ", "").toUpperCase());
    String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
    // remove chars from alphabet that are in key
    for (int i = 0; i < keyWithoutDuplicates.length(); i++) {
      alphabet = alphabet.replace(String.valueOf(keyWithoutDuplicates.charAt(i)), "");
    }

    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (i * 5 + j < keyWithoutDuplicates.length()) {
          keyMatrix[i][j] = keyWithoutDuplicates.charAt(i * 5 + j);
        } else {
          keyMatrix[i][j] = alphabet.charAt((i * 5 + j) - keyWithoutDuplicates.length());
        }
      }
    }
  }

  public String encrypt(String plaintext) {
    StringBuilder ciphertext = new StringBuilder();
    plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "");
    plaintext = plaintext.replace("J", "I");

    // increamently replace duplicate chars with X
    for (int i = 0; i < plaintext.length() - 1; i += 2) {
      if (plaintext.charAt(i) == plaintext.charAt(i + 1)) {
        plaintext = plaintext.substring(0, i + 1) + "X" + plaintext.substring(i + 1);
      }
    }

    // if the plaintext length is odd, append X to the end
    if (plaintext.length() % 2 == 1) {
      plaintext += "X";
    }

    // print the plaintext
    System.out.println("Plaintext: " + plaintext);

    for (int i = 0; i < plaintext.length(); i += 2) {
      char c1 = plaintext.charAt(i);
      char c2 = plaintext.charAt(i + 1);
      int[] pos1 = this.findPosition(c1);
      int[] pos2 = this.findPosition(c2);
      char e1, e2;
      if (pos1[0] == pos2[0]) {
        // Same row
        e1 = keyMatrix[pos1[0]][(pos1[1] + 1) % 5];
        e2 = keyMatrix[pos2[0]][(pos2[1] + 1) % 5];
      } else if (pos1[1] == pos2[1]) {
        // Same column
        e1 = keyMatrix[(pos1[0] + 1) % 5][pos1[1]];
        e2 = keyMatrix[(pos2[0] + 1) % 5][pos2[1]];
      } else {
        // Rectangle
        e1 = keyMatrix[pos1[0]][pos2[1]];
        e2 = keyMatrix[pos2[0]][pos1[1]];
      }
      ciphertext.append(e1);
      ciphertext.append(e2);
    }
    return ciphertext.toString();
  }

  public String decrypt(String ciphertext) {
    StringBuilder plaintext = new StringBuilder();
    for (int i = 0; i < ciphertext.length(); i += 2) {
      char c1 = ciphertext.charAt(i);
      char c2 = ciphertext.charAt(i + 1);
      int[] pos1 = findPosition(c1);
      int[] pos2 = findPosition(c2);
      if (pos1[0] == pos2[0]) { // same row
        plaintext.append(keyMatrix[pos1[0]][(pos1[1] + 4) % 5]);
        plaintext.append(keyMatrix[pos2[0]][(pos2[1] + 4) % 5]);
      } else if (pos1[1] == pos2[1]) { // same column
        plaintext.append(keyMatrix[(pos1[0] + 4) % 5][pos1[1]]);
        plaintext.append(keyMatrix[(pos2[0] + 4) % 5][pos2[1]]);
      } else { // different row and column
        plaintext.append(keyMatrix[pos1[0]][pos2[1]]);
        plaintext.append(keyMatrix[pos2[0]][pos1[1]]);
      }
    }

    // print the plaintext
    System.out.println("Plaintext before removing x: " + plaintext.toString());

    return removeDummyX(plaintext.toString());
  }

  private int[] findPosition(char c) {
    int[] pos = new int[2];
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (this.keyMatrix[i][j] == c) {
          pos[0] = i;
          pos[1] = j;
          return pos;
        }
      }
    }
    return pos;
  }

  private static String removeDuplicateChars(String str) {
    StringBuilder result = new StringBuilder();
    HashSet<Character> seen = new HashSet<>();

    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (!seen.contains(c)) {
        result.append(c);
        seen.add(c);
      }
    }

    return result.toString();
  }

  public static String removeDummyX(String encryptedText) {
    // Remove any extra 'X' characters that were added during encryption
    StringBuilder decryptedText = new StringBuilder();
    for (int i = 0; i < encryptedText.length(); i++) {
      char currentChar = encryptedText.charAt(i);
      if (currentChar == 'X' && i > 0 && i < encryptedText.length() - 1) {
        char prevChar = encryptedText.charAt(i - 1);
        char nextChar = encryptedText.charAt(i + 1);
        if (prevChar != 'X' && nextChar != 'X') {
          continue; // skip this 'X' character
        }
      }
      decryptedText.append(currentChar);
    }

    return decryptedText.toString();
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    while (true) {
      // ask the user for the key
      System.out.print("Enter key: ");
      String key = input.nextLine();

      PlayFairCipher cipher = new PlayFairCipher(key);

      // ask the user for the plaintext
      System.out.print("Enter plaintext: ");
      String plaintext = input.nextLine();

      // encrypt the plaintext
      String ciphertext = cipher.encrypt(plaintext);
      System.out.println("Ciphertext: " + ciphertext);

      // decrypt the ciphertext for verification
      String decrypted = cipher.decrypt(ciphertext);
      System.out.println("Decrypted: " + decrypted);

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
