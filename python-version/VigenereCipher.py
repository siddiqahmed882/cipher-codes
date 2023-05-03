class VigenereCipher:
	letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	key = "BELIEVE"
	keyLength = len(key)

	@staticmethod
	def encrypt(text):
		text = text.upper()
		cipherText = ""
		for i in range(len(text)):
			ch = text[i]
			k = VigenereCipher.key[i % VigenereCipher.keyLength]
			if ch.isalpha():
				index = (VigenereCipher.letters.index(ch) + VigenereCipher.letters.index(k)) % 26
				cipherText += VigenereCipher.letters[index]
			else:
				cipherText += ch
		return cipherText

	@staticmethod
	def decrypt(text):
		text = text.upper()
		plainText = ""
		for i in range(len(text)):
			ch = text[i]
			k = VigenereCipher.key[i % VigenereCipher.keyLength]
			if ch.isalpha():
				index = (VigenereCipher.letters.index(ch) - VigenereCipher.letters.index(k) + 26) % 26
				plainText += VigenereCipher.letters[index]
			else:
				plainText += ch
		return plainText

if __name__ == '__main__':
	# write infinite loop to keep the program running until the user exits
	while True:
		# first ask the user if they want to encrypt or decrypt
		choice = input("Do you want to encrypt or decrypt? (E/D) :")

		if choice.lower() == "e":
			# ask the user to enter the text to be encrypted
			text = input("Enter the text to be encrypted: ")

			# encrypt the text and print the result
			cipherText = VigenereCipher.encrypt(text)
			print("Encrypted text: " + cipherText)

			# verification
			decryptedText = VigenereCipher.decrypt(cipherText)
			print("Decrypted text: " + decryptedText)

		elif choice.lower() == "d":
			# ask the user to enter the text to be decrypted
			text = input("Enter the text to be decrypted: ")

			# decrypt the text and print the result
			plainText = VigenereCipher.decrypt(text)
			print("Decrypted text: " + plainText)

			# verification
			encryptedText = VigenereCipher.encrypt(plainText)
			print("Encrypted text: " + encryptedText)

		# ask the user if they want to continue
		choice = input("Do you want to continue? (Y/N) :")

		# if the user enters N or n, exit the program
		if choice.lower() == "n":
			print("Exiting...")
			break
