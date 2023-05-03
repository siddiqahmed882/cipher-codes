ALPHABET_SIZE = 26

def encrypt(plainText, key):
    # Convert to lowercase
    plainText = plainText.lower()
    key = key.lower()

    # Convert to int array
    plainTextInt = text_to_number(plainText)
    keyInt = text_to_number(key)

    # Add key to plain text and mod by alphabet size
    cipherTextInt = [(plainTextInt[i] + keyInt[i]) % ALPHABET_SIZE for i in range(len(plainTextInt))]

    # Convert to string and return
    return number_to_text(cipherTextInt)

def decrypt(cipherText, key):
    # Convert to lowercase
    cipherText = cipherText.lower()
    key = key.lower()

    # Convert to int array
    cipherTextInt = text_to_number(cipherText)
    keyInt = text_to_number(key)

    # Subtract key from cipher text and mod by alphabet size
    plainTextInt = [(cipherTextInt[i] - keyInt[i] + ALPHABET_SIZE) % ALPHABET_SIZE for i in range(len(cipherTextInt))]

    # Convert to string and return
    return number_to_text(plainTextInt)

def text_to_number(text):
    textInt = [ord(c) - ord('a') for c in text]
    return textInt

def number_to_text(number):
    text = "".join(chr(i + ord('a')) for i in number)
    return text

while True:
    # ask the user to enter the text to be encrypted
    plainText = input("Enter plain text: ").replace(" ", "")

    key = ""
    while True:
        # ask the user to enter the key
        key = input("Enter key: ").replace(" ", "")

        # check if the key is valid
        if len(key) == len(plainText):
            break
        else:
            print("Key length must be equal to plain text length")

    cipherText = encrypt(plainText, key)
    print("Cipher text: " + cipherText)

    decryptedText = decrypt(cipherText, key)
    print("Decrypted text: " + decryptedText)

    # ask the user if they want to continue
    choice = input("Do you want to continue? (Y/N) :")

    # if the user enters N or n, exit the program
    if choice.upper() == "N":
        print("Exiting...")
        break
