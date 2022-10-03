package SubstitutionCipher;
import java.util.*;
import java.io.*;

public class SubstitutionCipher {
    private HashMap<Character, Character> key;
    private String name;
    private String decryptedText = "";

    // Character array to validate key.
    private char[] validKeyValues = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
        'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
        'W', 'X', 'Y', 'Z'
    };

    // Validate the argument you've provided. if the argument is null, returns true if argument is null.
    private boolean validateNullArguments(Object arg) {
        if (arg == null) {
            System.out.println("Provided argument is not valid.");
            return true;
        }
        return false;
    }

    // Constructor
    public SubstitutionCipher(String name, HashMap<Character, Character>key) {
        if (validateNullArguments(name) || validateNullArguments(key)) return;
        validateNullArguments(name);
        validateNullArguments(key);
        this.key = key;
        this.name = name;
    }

    // The method verifies whether the key is valid or not.
    // The key should be of the map type, and the key value pair needs to have all 26 distinctive characters to be valid.
    public boolean keyIsValid() {
        if (key.isEmpty()) return false;
        else {
            Object[] keyArray = key.keySet().toArray();
            Object[] valueArray = key.values().toArray();
            if (validKeyValues.length != keyArray.length || validKeyValues.length != valueArray.length) return false;
            for (int i = 0; i < validKeyValues.length; i++) {
                if (!key.containsKey(validKeyValues[i]) || !key.containsValue(validKeyValues[i])) return false;
            }
        }
        return true;
    }

    // The encrypted text is decrypted and stored in a variable if the key is valid.
    // The method returns true if the cypher text can be successfully decrypted and false otherwise.
    public boolean cipherText(String encryptedText) {
        if (validateNullArguments(encryptedText) || !keyIsValid()) return false;
        for (int i = 0; i < encryptedText.length(); i++) {
            char encryptedLetter = Character.toUpperCase((encryptedText.charAt(i)));
            if (key.get(encryptedLetter) != null) {
                char decryptedLetter = key.get(encryptedLetter);
                decryptedText += decryptedLetter;
            } else {
                decryptedText += encryptedLetter;
            }
        }
        return true;
    }

    // Returns the decrypted cipher text
    public String decodeText() {
        if (!keyIsValid()) return "Please provide valid key.";
        if (decryptedText == "") return "Please provide cipher text.";
        return decryptedText;
    }
}
