

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;


public class Spell {

    private static Hashtable<String, Boolean> dictionary;
    Spell(String dict_file, String file_to_check) {
        // Load dictionary words from file into Hashtable
        dictionary = new Hashtable<String, Boolean>();
        try {
            Scanner scanner = new Scanner(new File(dict_file));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                dictionary.put(line, Boolean.TRUE);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // add your code here




        // Load words in fileToCheck.txt

        // add your code here

    }

    public static void main(String[] args) {
        // init an object of type Spell
        Spell spell = new Spell(args[0], args[1]);
        System.out.println(getDictionary());

        // add your code here
    }

    // this function check if the dictionay is loaded or not
    public static Hashtable<String, Boolean> getDictionary(){
        // add your code here
        return dictionary;
    }

    // This function takes a String word as an argument to check if the word exists in the dictionary. 
    // If the word exists, it will print it with a message "Incorrect Spelling:" to the console.
    // Else it will call the suggestCorrections function to provide the correct word from the words given in the dictionary file.
    public static boolean checkSpelling(String word){
       boolean check = dictionary.containsKey(word);
       if(check){System.out.println(word + ": Correct Spelling");}
       else {suggestCorrections(word);}
       return check;
    }

    // This function takes a String input word as argument.
    // It starts by printing the message word: Incorrect Spelling to the console.
    // The function also uses four different methods (correctSpellingWithSubstitution,
    // correctSpellingWithOmission, correctSpellingWithInsertion, correctSpellingWithReversal)
    // to generate possible corrected spellings for the input word.
    // The function then returns the suggestions list which contains the possible corrected spellings.
    public static boolean suggestCorrections(String word) {

        System.out.println(word + ": Incorrect Spelling");

        ArrayList<String> substitution = new ArrayList<>(List.of(correctSpellingSubstitution(word).trim().split(" ")));
        ArrayList<String> omission = new ArrayList<>(List.of(correctSpellingWithOmission(word).trim().split(" ")));
        ArrayList<String> insertion = correctSpellingWithInsertion(word);
        ArrayList<String> reversal = new ArrayList<>(List.of(correctSpellingWithReversal(word).trim().split(" ")));

        ArrayList<String> suggestions = new ArrayList<>();

        for (String i : substitution){
            if (!substitution.contains(i)){
                substitution.add(i);
            }
        }

        for (String i : omission){
            if (!omission.contains(i)){
                omission.add(i);
            }
        }

        for (String i : insertion){
            if (!insertion.contains(i)){
                insertion.add(i);
            }
        }

        for (String i : reversal){
            if (!reversal.contains(i)){
                reversal.add(i);
            }
        }

        return !suggestions.equals(new ArrayList<>());
    }

    // This function takes in a string word and tries to correct the spelling by substituting letters and 
    // check if the resulting new word is in the dictionary.
    static String correctSpellingSubstitution(String word) {
        // add your code here
    }

    // This function tries to omit (in turn, one by one) a single character in the misspelled word 
    // and check if the resulting new word is in the dictionary.
    static String correctSpellingWithOmission(String word) {
        // add your code here
    }

    // This function tries to insert a letter in the misspelled word 
    // and check if the resulting new word is in the dictionary.
    static ArrayList<String> correctSpellingWithInsertion(String word) {
        // add your code here
    }
    
    // This function tries swapping every pair of adjacent characters 
    // and check if the resulting new word is in the dictionary.
    static String correctSpellingWithReversal(String word) {
       // add your code here
    }

}