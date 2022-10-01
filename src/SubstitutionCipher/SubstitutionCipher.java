package SubstitutionCipher;
import java.util.*;
import java.io.*;

public class SubstitutionCipher {
    private HashMap<Character, Character> key;
    private String name;
    private String decryptedText;

    private char[] validKeyValues = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
        'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
        'W', 'X', 'Y', 'Z'
    };

    public SubstitutionCipher(String name, HashMap<Character, Character>key) {
        this.key = key;
        this.name = name;
    }

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

    public boolean cipherText(String encryptedText) {

    }
}
