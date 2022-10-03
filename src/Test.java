import SubstitutionCipher.SubstitutionCipher;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        HashMap<Character, Character>key = new HashMap<>();
        key.put('A', 'B');
        key.put('B', 'C');
        key.put('C', 'D');
        key.put('D', 'E');
        key.put('E', 'F');
        key.put('F', 'G');
        key.put('G', 'H');
        key.put('H', 'I');
        key.put('I', 'J');
        key.put('J', 'K');
        key.put('K', 'L');
        key.put('L', 'M');
        key.put('M', 'N');
        key.put('N', 'O');
        key.put('O', 'P');
        key.put('P', 'Q');
        key.put('Q', 'R');
        key.put('R', 'S');
        key.put('S', 'T');
        key.put('T', 'U');
        key.put('U', 'V');
        key.put('V', 'W');
        key.put('W', 'X');
        key.put('X', 'Y');
        key.put('Y', 'Z');
        key.put('Z', 'A');

        // Valid test object
        SubstitutionCipher testObj = new SubstitutionCipher("Test Object", key);

        // Input Constructor validation
        // Invalid
        SubstitutionCipher testObj1 = new SubstitutionCipher(null, null);
        SubstitutionCipher testObj2 = new SubstitutionCipher("Test Object", null);
        SubstitutionCipher testObj3 = new SubstitutionCipher(null, key);

        // keyIsValid method tests

        // Calling when key is valid
        if (testObj.keyIsValid() == true) System.out.println("Calling when key is valid - Passed");
        else System.out.println("Calling when key is valid - Failed");

        // Calling when key has no character in it
        SubstitutionCipher testObj5 = new SubstitutionCipher("Test Object", new HashMap<>());
        if (testObj5.keyIsValid() == false) System.out.println("Calling when key has no character in it - Passed");
        else System.out.println("Calling when key has no character in it - Failed");

        // call when some letter of the ciphertext does not appear as a ciphertext letter in the key
        HashMap<Character, Character>key1 = new HashMap<>();
        key1.put('A', 'N');
        SubstitutionCipher testObj6 = new SubstitutionCipher("Test Object", key1);
        if (testObj6.keyIsValid() == false) System.out.println("call when some letter of the ciphertext does not appear as a ciphertext letter in the key - Passed");
        else System.out.println("call when some letter of the ciphertext does not appear as a ciphertext letter in the key - Failed");

        // cipherText method tests

        // Input validation
        if (testObj.cipherText(null) == false) System.out.println("Argument validation for method cipherText - Passed");
        else System.out.println("Argument validation for method cipherText - Failed");

        
    }
}