

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;



public class Spell {

    public Hashtable<String, Boolean> dictionary;
    Spell(String dict_file, String file_to_check) {
        // Load dictionary words from file into Hashtable
        this.dictionary = new Hashtable<>();
        try {
            Scanner scanner1 = new Scanner(new File(dict_file));
            while (scanner1.hasNextLine()) {
                this.dictionary.put(scanner1.nextLine().trim().toLowerCase(), Boolean.TRUE);
            }
            scanner1.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }





        // Load words in fileToCheck.txt
        try {
            Scanner scanner2 = new Scanner(new File(file_to_check));
            while (scanner2.hasNextLine()) {
                checkSpelling(scanner2.nextLine().trim().toLowerCase());
            }
            scanner2.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        // init an object of type Spell
        Spell spell = new Spell(args[0], args[1]);
        spell.checkSpelling("hlelo");

        // add your code here
    }

    // this function check if the dictionary is loaded or not
    public Hashtable<String, Boolean> getDictionary(){
        // add your code here
        return this.dictionary;
    }

    // This function takes a String word as an argument to check if the word exists in the dictionary. 
    // If the word exists, it will print it with a message "Incorrect Spelling:" to the console.
    // Else it will call the suggestCorrections function to provide the correct word from the words given in the dictionary file.
    public boolean checkSpelling(String word){
       boolean check = this.dictionary.containsKey(word);
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
    public boolean suggestCorrections(String word) {

        System.out.println(word + ": Incorrect Spelling");
        // Creates an ArrayList for each suggestion type
        ArrayList<String> substitution = new ArrayList<>(List.of(this.correctSpellingSubstitution(word).trim().split(" ")));
        ArrayList<String> omission = new ArrayList<>(List.of(this.correctSpellingWithOmission(word).trim().split(" ")));
        ArrayList<String> insertion = correctSpellingWithInsertion(word);
        ArrayList<String> reversal = new ArrayList<>(List.of(correctSpellingWithReversal(word).trim().split(" ")));

        ArrayList<String> suggestions = new ArrayList<>();

        // Adds all suggestions from substitution to the general suggestions ArrayList
        for (String i : substitution){
            if (!suggestions.contains(i.toLowerCase())){
                suggestions.add(i);
            }
        }

        // Adds all suggestions from omission to the general suggestions ArrayList
        for (String i : omission){
            if (!suggestions.contains(i.toLowerCase())){
                suggestions.add(i);
            }
        }

        // Adds all suggestions from insertion to the general suggestions ArrayList
        for (String i : insertion){
            if (!suggestions.contains(i.toLowerCase())){
                suggestions.add(i);
            }
        }

        // Adds all suggestions from reversal to the general suggestions ArrayList
        for (String i : reversal){
            if (!suggestions.contains(i.toLowerCase())){
                suggestions.add(i);
            }
        }

        // Prints/formats all suggestions for word
        System.out.print(word + " => ");
        suggestions.removeAll(List.of(new String[]{"", word, word.toLowerCase()}));
        if(suggestions.size() > 0){
            for (String suggestion : suggestions.subList(0, suggestions.size()-1)) {
                if(!suggestion.equals("")) {
                    System.out.print(suggestion + ", ");
                }
            }
            System.out.print(suggestions.get(suggestions.size()-1) + "\n");
        } else {
            System.out.print("No suggestions\n");
        }



        return !(suggestions.size() == 0);
    }

    // This function takes in a string word and tries to correct the spelling by substituting letters and 
    // check if the resulting new word is in the dictionary.
    String correctSpellingSubstitution(String word) {
        StringBuilder sug = new StringBuilder();
        for (int i = 1; i <= word.length(); i++){
            for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
                String search_word = word.substring(0, i-1) + alphabet + word.substring(i);
                if (this.dictionary.containsKey(search_word.toLowerCase())&& !search_word.equals("")){
                    sug.append(search_word).append(" ");
                }
            }
        }
        return sug.toString();
    }

    // This function tries to omit (in turn, one by one) a single character in the misspelled word 
    // and check if the resulting new word is in the dictionary.
    String correctSpellingWithOmission(String word) {
        StringBuilder sug = new StringBuilder();
        for (int i = 1; i <= word.length(); i++){

            String search_word = word.substring(0, i-1) + word.substring(i);
            if (this.dictionary.containsKey(search_word) && !search_word.equals("")){
                sug.append(search_word).append(" ");
            }

        }
        return sug.toString();
    }

    // This function tries to insert a letter in the misspelled word 
    // and check if the resulting new word is in the dictionary.
    ArrayList<String> correctSpellingWithInsertion(String word) {
        // add your code here
        ArrayList<String> sug = new ArrayList<>();
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            char[] search_word = (alphabet + word).toCharArray();

            for (int i = 1; i <= word.length()+1; i++){

                if (this.dictionary.containsKey(new String(search_word)) && !(new String(search_word).equals(""))){
                    sug.add(new String(search_word));
                }
                if (i != word.length()+1) {
                    search_word[i - 1] = search_word[i];
                    search_word[i] = alphabet;
                }


            }
        }
        return sug;
    }
    
    // This function tries swapping every pair of adjacent characters 
    // and check if the resulting new word is in the dictionary.
    String correctSpellingWithReversal(String word) {
        StringBuilder sug = new StringBuilder();

        char[] search_word = word.toCharArray();

        for (int i = 1; i < word.length(); i++){
            char tmp = search_word[i-1];
            search_word[i-1] = search_word[i];
            search_word[i] = tmp;

            if (this.dictionary.containsKey(new String(search_word)) && !(new String(search_word).equals(""))){
                sug.append(new String(search_word));
            }
            search_word = word.toCharArray();
        }

        return sug.toString();
    }

}