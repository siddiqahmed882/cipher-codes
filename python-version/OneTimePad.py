import random

ALPHABET = 'abcdefghijklmnopqrstuvwxyz'

def generate_key(length):
    """Generate a random key of specified length."""
    key = ''
    for i in range(length):
        key += random.choice(ALPHABET)
    return key

def encrypt(plain_text, key):
    """Encrypt the plain text using the specified key."""
    cipher_text = ''
    for i in range(len(plain_text)):
        p = ALPHABET.index(plain_text[i])
        k = ALPHABET.index(key[i])
        c = (p + k) % 26
        cipher_text += ALPHABET[c]
    return cipher_text

def decrypt(cipher_text, key):
    """Decrypt the cipher text using the specified key."""
    plain_text = ''
    for i in range(len(cipher_text)):
        c = ALPHABET.index(cipher_text[i])
        k = ALPHABET.index(key[i])
        p = (c - k) % 26
        plain_text += ALPHABET[p]
    return plain_text


while True:
    # ask the user to enter the text to be encrypted
    plainText = input("Enter plain text: ").replace(" ", "")

    # generate a key of the same length as plain text
    key = generate_key(len(plainText))

    # encrypt the plain text using the key
    cipherText = encrypt(plainText, key)
    print("Cipher text: " + cipherText)

    # decrypt the cipher text using the key
    decryptedText = decrypt(cipherText, key)
    print("Decrypted text: " + decryptedText)

    # ask the user if they want to continue
    choice = input("Do you want to continue? (Y/N) :")

    # if the user enters N or n, exit the program
    if choice.upper() == "N":
        print("Exiting...")
        break
