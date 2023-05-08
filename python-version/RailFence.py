class RailFence:
  @staticmethod
  def encryptMessage(msg, key):
    # Create a 2D list to represent the rail fence
    fence = [['\n' for i in range(len(msg))] for j in range(key)]

    # Fill the fence with the plain text characters
    direction = -1
    row, col = 0, 0
    for char in msg:
      # Change the direction when the top or bottom row is reached
      if row == 0 or row == key - 1:
        direction = -direction

      fence[row][col] = char
      col += 1
      row += direction
    
    # Concatenate the fence rows into the encrypted text
    cipher = ''
    for i in range(key):
      for j in range(len(msg)):
        if fence[i][j] != '\n':
          cipher += fence[i][j]
    
    return cipher
  
  @staticmethod
  def decryptMessage(cipher, key):
    # Create a 2D list to represent the rail fence
    fence = [['\n' for i in range(len(cipher))] for j in range(key)]

    # Calculate the fence pattern
    direction = -1
    row, col = 0, 0

    for char in cipher:
      # Change the direction when the top or bottom row is reached
      if row == 0 or row == key - 1:
        direction = -direction
      
      fence[row][col] = '*'
      col += 1
      row += direction
    
    # Fill the fence with the cipher text characters
    index = 0
    for i in range(key):
      for j in range(len(cipher)):
        if fence[i][j] == '*' and index < len(cipher):
          fence[i][j] = cipher[index]
          index += 1
    
    # Concatenate the fence columns into the decrypted text
    plainText = ''
    direction = -1
    row, col = 0, 0
    for i in range(len(cipher)):
      if row == 0 or row == key - 1:
        direction = -direction
      
      if fence[row][col] != '*':
        plainText += fence[row][col]
        col += 1
      
      row += direction
    
    return plainText




while True:
  plain_text = input("Enter the text to encrypt: ")
  key = int(input("Enter the key: "))
  Encrypted_text = RailFence.encryptMessage(plain_text, key)
  decrypt_text = RailFence.decryptMessage(Encrypted_text, key)

  print("Encrypted Text:" + Encrypted_text)
  print("Decrypted Text:" + decrypt_text)

  options = input("Do want to continue? (y/n): ")

  if options.lower() == "n":
    print("Quiting!")
    break
