package SubstitutionCipher;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SubstitutionCipher {
    private HashMap<Character, Character> encryptionKey;
    private HashMap<Character, Character> decryptionKey;
    private String name;
    private String decryptedText = "";
    private HashMap<String, HashMap<Character, Double>> languageFreqTable = new HashMap<>();

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

    // Generate decryption key from provided encryption key
    private HashMap<Character, Character> generateDecryptionKey(HashMap<Character, Character>key) {
        HashMap<Character, Character>resKey = new HashMap<>();
        Object[] keyArray = key.keySet().toArray();
        for (int i = 0; i < keyArray.length; i++) {
            Character letter = key.get(keyArray[i]);
            resKey.put(letter, keyArray[i].toString().charAt(0));
        }
        return resKey;
    }

    private HashMap<Character, Double> getDefaultLoadedFreqHashTable() {
        HashMap<Character, Double> hashMap = new HashMap<>();
        for (int i = 0; i < validKeyValues.length; i++) {
            hashMap.put(validKeyValues[i], 0.0);
        }
        return hashMap;
    }

    private boolean validateLetter(Character letter) {
        for (int i = 0; i < validKeyValues.length; i++) {
            if (letter.equals(validKeyValues[i])) return true;
        }
        return false;
    }

    // Read the text file
    private String readFile(String filename) {
        String fileText = "";
        try {
            File cipherTextFile = new File(filename);
            Scanner sc = new Scanner(cipherTextFile);
            while (sc.hasNextLine()) {
                fileText += sc.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return fileText;
    }

    private HashMap<Character, Double> generateLetterFreq(String text) {
        HashMap<Character, Integer> letterFreq = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            char letter = Character.toUpperCase(text.charAt(i));
            if (validateLetter(letter)) {
                if (letterFreq.containsKey(letter)) {
                    Integer freq = letterFreq.get(letter) + 1;
                    letterFreq.put(letter, freq);
                } else {
                    letterFreq.put(letter, 1);
                }
            }
        }
        HashMap<Character, Double>freqTableForLanguage = getDefaultLoadedFreqHashTable();
        for (int i = 0; i < validKeyValues.length; i++) {
            if (letterFreq.containsKey(validKeyValues[i])) {
                char letter = validKeyValues[i];
                Double freqValue = Double.valueOf(letterFreq.get(letter)) / Double.valueOf(text.length());
                freqTableForLanguage.put(letter, freqValue);
            }
        }
        return freqTableForLanguage;
    }

    // Constructor
    public SubstitutionCipher(String name, HashMap<Character, Character>key) {
        if (validateNullArguments(name) || validateNullArguments(key)) return;
        this.encryptionKey = key;
        this.decryptionKey = generateDecryptionKey(key);
        this.name = name;
        System.out.println(encryptionKey);
        System.out.println(decryptionKey);
    }

    // The method verifies whether the key is valid or not.
    // The key should be of the map type, and the key value pair needs to have all 26 distinctive characters to be valid.
    public boolean keyIsValid() {
        if (encryptionKey.isEmpty()) return false;
        else {
            Object[] keyArray = encryptionKey.keySet().toArray();
            Object[] valueArray = encryptionKey.values().toArray();
            if (validKeyValues.length != keyArray.length || validKeyValues.length != valueArray.length) return false;
            for (int i = 0; i < validKeyValues.length; i++) {
                if (!encryptionKey.containsKey(validKeyValues[i]) || !encryptionKey.containsValue(validKeyValues[i])) return false;
            }
        }
        return true;
    }

    // The encrypted text is decrypted and stored in a variable if the key is valid.
    // The method returns true if the cypher text can be successfully decrypted and false otherwise.
    public boolean cipherText(String fileName) {
        String encryptedText = readFile(fileName);
        if (validateNullArguments(encryptedText) || !keyIsValid()) return false;
        for (int i = 0; i < encryptedText.length(); i++) {
            char encryptedLetter = Character.toUpperCase((encryptedText.charAt(i)));
            if (decryptionKey.get(encryptedLetter) != null) {
                char decryptedLetter = decryptionKey.get(encryptedLetter);
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

    public Boolean originalLanguage(String name, String fileName) {
        String languageText = readFile(fileName);
        HashMap<Character, Double> letterFreq = generateLetterFreq(languageText);
        languageFreqTable.put(name, letterFreq);
        return true;
    }

    // Returns the encryption key being used
    public HashMap<Character, Character> getKey() {
        return encryptionKey;
    }

    // Return the response language from the language frequency table
    public String matchLanguage(String filenname) {
        String fileText = readFile(filenname);
        HashMap<Character, Double>letterFreq = generateLetterFreq(fileText);
        HashMap<String, Double>languageDifference = new HashMap<>();
        System.out.println(letterFreq);
        Object[] keyArray = languageFreqTable.keySet().toArray();
        String resposeLanguage = "";
        Double closestDiff = 10.0;
        for (int i = 0; i < keyArray.length; i++) {
            HashMap<Character, Double>languageLetterFreq = languageFreqTable.get(keyArray[i]);
            Double diffrenceCount = 0.0;
            for (int j = 0; j < validKeyValues.length; j++) {
                Double diffrence = languageLetterFreq.get(validKeyValues[j]) - letterFreq.get(validKeyValues[j]);
                diffrenceCount += Math.abs(diffrence);
            }
            if (diffrenceCount <= closestDiff) resposeLanguage = keyArray[i].toString();
        }
        return resposeLanguage;
    }

    // This method is yet to be implemented
    public Boolean guessKeyFromFrequencies(String language) { return false; }

    // This method is yet to be implemented
    public Boolean setDecodeLetter(Character plainLetter, Character cipherLetter) { return false; }
}
