import SubstitutionCipher.SubstitutionCipher;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class A1 {
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
        SubstitutionCipher obj = new SubstitutionCipher("Random Name", key);
        System.out.println(obj.keyIsValid());

        String cipherText = "";
        try {
            File cipherTextFile = new File("cipherText.txt");
            Scanner sc = new Scanner(cipherTextFile);
            while (sc.hasNextLine()) {
                cipherText += sc.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        System.out.println(obj.cipherText(cipherText));
        System.out.println(obj.decodeText());
    }
}