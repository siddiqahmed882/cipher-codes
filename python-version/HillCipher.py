def encrypt(plain_text, key):
    # Generate key matrix
    key_matrix = [[ord(c) - ord('A') for c in row] for row in key]
    
    # Determine key matrix dimensions
    rows, cols = len(key_matrix), len(key_matrix[0])
    
    # Pad plain text if necessary
    if len(plain_text) % cols != 0:
        plain_text += 'X' * (cols - (len(plain_text) % cols))
    
    # Convert plain text to matrix
    plain_text_matrix = [[ord(c) - ord('A') for c in plain_text[i:i+cols]] for i in range(0, len(plain_text), cols)]
    
    # Perform encryption
    cipher_text = ''
    for block in plain_text_matrix:
        # Multiply key matrix with plain text matrix
        result = [sum([block[j] * key_matrix[i][j] for j in range(cols)]) % 26 for i in range(rows)]
        
        # Convert result back to characters
        cipher_text += ''.join([chr(c + ord('A')) for c in result])
    
    return cipher_text


def decrypt(cipher_text, key):
    # Generate key matrix
    key_matrix = [[ord(c) - ord('A') for c in row] for row in key]
    
    # Calculate inverse of the key matrix
    inverse_matrix = get_inverse_matrix(key_matrix)
    
    # Determine key matrix dimensions
    rows, cols = len(key_matrix), len(key_matrix[0])
    
    # Convert cipher text to matrix
    cipher_text_matrix = [[ord(c) - ord('A') for c in cipher_text[i:i+cols]] for i in range(0, len(cipher_text), cols)]
    
    # Perform decryption
    plain_text = ''
    for block in cipher_text_matrix:
        # Multiply inverse of key matrix with cipher text matrix
        result = [sum([block[j] * inverse_matrix[i][j] for j in range(cols)]) % 26 for i in range(rows)]
        
        # Convert result back to characters
        plain_text += ''.join([chr(c + ord('A')) for c in result])
    
    return plain_text


def get_inverse_matrix(matrix):
    determinant = (
        matrix[0][0] * (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1]) -
        matrix[0][1] * (matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0]) +
        matrix[0][2] * (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0])
    )
    determinant = (determinant % 26 + 26) % 26  # Handle negative determinants
    
    multiplicative_inverse = -1
    for i in range(26):
        if (determinant * i) % 26 == 1:
            multiplicative_inverse = i
            break
    
    inverse_matrix = [[0, 0, 0] for _ in range(3)]
    inverse_matrix[0][0] = ((matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1]) * multiplicative_inverse) % 26
    inverse_matrix[0][1] = ((-matrix[0][1] * matrix[2][2] + matrix[0][2] * matrix[2][1]) * multiplicative_inverse) % 26
    inverse_matrix[0][2] = ((matrix[0][1] * matrix[1][2] - matrix[0][2] * matrix[1][1]) * multiplicative_inverse) % 26
    inverse_matrix[1][0] = ((-matrix[1][0] * matrix[2][2] + matrix[1][2] * matrix[2][0]) * multiplicative_inverse) % 26
    inverse_matrix[1][1] = ((matrix[0][0] * matrix[2][2] - matrix[0][2] * matrix[2][0]) * multiplicative_inverse) % 26
    inverse_matrix[1][2] = ((-matrix[0][0] * matrix[1][2] + matrix[0][2] * matrix[1][0]) * multiplicative_inverse) % 26
    inverse_matrix[2][0] = ((matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]) * multiplicative_inverse) % 26
    inverse_matrix[2][1] = ((-matrix[0][0] * matrix[2][1] + matrix[0][1] * matrix[2][0]) * multiplicative_inverse) % 26
    inverse_matrix[2][2] = ((matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]) * multiplicative_inverse) % 26
    
    return inverse_matrix


# Example usage
key = ['GYB', 'NPF', 'EDC']
plain_text = 'PAYMOREMONE'
cipher_text = encrypt(plain_text, key)
decrypted_text = decrypt(cipher_text, key)

print('Plain Text:', plain_text)
print('Cipher Text:', cipher_text)
print('Decrypted Text:', decrypted_text)
