import java.util.Scanner;

public class RailFence {
  public static String encryptMessage(String msg, int key) {
    // Create a 2D list to represent the rail fence
    char[][] fence = new char[key][msg.length()];

    // Fill the fence with the plain text characters
    int direction = -1;
    int row = 0, col = 0;
    for (char ch : msg.toCharArray()) {
      // Change the direction when the top or bottom row is reached
      if (row == 0 || row == key - 1) {
        direction = -direction;
      }

      fence[row][col] = ch;
      col++;
      row += direction;
    }

    // Concatenate the fence rows into the encrypted text
    StringBuilder cipher = new StringBuilder();
    for (int i = 0; i < key; i++) {
      for (int j = 0; j < msg.length(); j++) {
        if (fence[i][j] != '\n') {
          cipher.append(fence[i][j]);
        }
      }
    }

    return cipher.toString();
  }

  public static String decryptMessage(String cipher, int key) {
    // Create a 2D list to represent the rail fence
    char[][] fence = new char[key][cipher.length()];

    // Calculate the fence pattern
    int direction = -1;
    int row = 0, col = 0;
    for (char ch : cipher.toCharArray()) {
      // Change the direction when the top or bottom row is reached
      if (row == 0 || row == key - 1) {
        direction = -direction;
      }

      fence[row][col] = '*';
      col++;
      row += direction;
    }

    // Fill the fence with the cipher text characters
    int index = 0;
    for (int i = 0; i < key; i++) {
      for (int j = 0; j < cipher.length(); j++) {
        if (fence[i][j] == '*' && index < cipher.length()) {
          fence[i][j] = cipher.charAt(index);
          index++;
        }
      }
    }

    // Concatenate the fence columns into the decrypted text
    StringBuilder plainText = new StringBuilder();
    direction = -1;
    row = 0;
    col = 0;
    for (int i = 0; i < cipher.length(); i++) {
      if (row == 0 || row == key - 1) {
        direction = -direction;
      }

      if (fence[row][col] != '*') {
        plainText.append(fence[row][col]);
        col++;
      }

      row += direction;
    }

    return plainText.toString();
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter your message: ");
    String msg = sc.nextLine();
    msg = msg.replaceAll(" ", "");

    System.out.print("Enter your key: ");
    int key = Integer.parseInt(sc.nextLine());

    String cipher = encryptMessage(msg, key);
    System.out.println("Encrypted message: " + cipher);

    String decryptedMsg = decryptMessage(cipher, key);
    System.out.println("Decrypted message: " + decryptedMsg);

    sc.close();
  }
}
