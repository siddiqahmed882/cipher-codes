class CaesarCipher:
    @staticmethod
    def encrypt(text, shift):
        result = []
        for i in range(len(text)):
            # replace spaces with |
            if text[i] == ' ':
                result.append('|')
                continue
            if text[i].isupper():
                ch = chr((ord(text[i]) + shift - 65) % 26 + 65)
                result.append(ch)
            else:
                ch = chr((ord(text[i]) + shift - 97) % 26 + 97)
                result.append(ch)
        return ''.join(result)

    @staticmethod
    def decrypt(text, shift):
        result = []
        for i in range(len(text)):
            # replace | with spaces
            if text[i] == '|':
                result.append(' ')
                continue
            if text[i].isupper():
                ch = chr((ord(text[i]) - shift - 65 + 26) % 26 + 65)
                result.append(ch)
            else:
                ch = chr((ord(text[i]) - shift - 97 + 26) % 26 + 97)
                result.append(ch)
        return ''.join(result)

    @staticmethod
    def main():
        while True:
            # ask the user to enter the text to be encrypted
            text = input("Enter the text to be encrypted: ")

            # ask the user to enter the shift value
            shift = int(input("Enter the shift value: "))

            # encrypt the text
            encryptedText = CaesarCipher.encrypt(text, shift)

            # decrypt the text
            decryptedText = CaesarCipher.decrypt(encryptedText, shift)

            # print the encrypted text
            print("Encrypted text: " + encryptedText)

            # print the decrypted text
            print("Decrypted text: " + decryptedText)

            # ask the user if they want to continue
            choice = input("Do you want to continue? (Y/N) :")

            # if the user enters N or n, exit the program
            if choice.lower() == "n":
                print("Exiting...")
                break

if __name__ == '__main__':
    CaesarCipher.main()
