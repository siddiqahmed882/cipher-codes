package RowColumnCipher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.lang.Math;
import java.util.Scanner;

public class RowColumnCipher {

  public static String encryptMessage(String msg, String key) {
    String cipher = "";

    // track key indices
    int k_indx = 0;

    double msg_len = (double) msg.length();
    char[] msg_lst = msg.toCharArray();
    List<Character> key_lst = new ArrayList<Character>();
    for (char c : key.toCharArray()) {
      key_lst.add(c);
    }
    Collections.sort(key_lst);

    // calculate column of the matrix
    int col = key.length();

    // calculate maximum row of the matrix
    int row = (int) Math.ceil(msg_len / col);

    // add the padding character '_' in empty the empty cell of the matix
    int fill_null = (row * col) - msg.length();
    for (int i = 0; i < fill_null; i++) {
      msg += "_";
    }

    // create Matrix and insert message and padding characters row-wise
    char[][] matrix = new char[row][col];
    for (int i = 0, k = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        matrix[i][j] = msg_lst[k++];
      }
    }

    // read matrix column-wise using key
    for (int i = 0; i < col; i++) {
      int curr_idx = key.indexOf(key_lst.get(k_indx));
      for (int j = 0; j < row; j++) {
        cipher += matrix[j][curr_idx];
      }
      k_indx++;
    }
    return cipher;
  }

  public static String decryptMessage(String cipher, String key) {
    String msg = "";

    // track key indices
    int k_indx = 0;

    // track msg indices
    int msg_indx = 0;

    double msg_len = (double) cipher.length();
    char[] msg_lst = cipher.toCharArray();

    // calculate column of the matrix
    int col = key.length();

    // calculate maximum row of the matrix
    int row = (int) Math.ceil(msg_len / col);

    // convert key into list and sort alphabetically so we can access each character
    // by its alphabetical position.
    List<Character> key_lst = new ArrayList<Character>();
    for (char c : key.toCharArray()) {
      key_lst.add(c);
    }
    Collections.sort(key_lst);

    // create an empty matrix to store deciphered message
    char[][] dec_cipher = new char[row][col];

    // Arrange the matrix column wise according to permutation order by adding into
    // new matrix
    for (int i = 0; i < col; i++) {
      int curr_idx = key.indexOf(key_lst.get(k_indx));
      for (int j = 0; j < row; j++) {
        dec_cipher[j][curr_idx] = msg_lst[msg_indx++];
      }
      k_indx++;
    }

    // convert decrypted msg matrix into a string
    try {
      for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
          msg += dec_cipher[i][j];
        }
      }
    } catch (Exception e) {
      throw new RuntimeException("This program cannot handle repeating words.");
    }
    int null_count = msg.length() - msg.replace("_", "").length();
    if (null_count > 0) {
      msg = msg.substring(0, msg.length() - null_count);
    }
    return msg;
  }

  public static void main(String[] args) {
    // infinte loop to keep program running
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter your message: ");
    String msg = sc.nextLine();
    msg = msg.replace(" ", "");
    System.out.print("Enter your key: ");
    String key = sc.nextLine();
    String cipher = encryptMessage(msg, key);
    System.out.println("Encrypted message: " + cipher);
    String decryptedMsg = decryptMessage(cipher, key);
    System.out.println("Decrypted message: " + decryptedMsg);

    sc.close();
  }
}
