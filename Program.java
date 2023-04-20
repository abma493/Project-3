package Project3;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Program {


    public static void main(String[] args) {
        String src = "aliceInWonderlandDictionary.txt";
        Map<String, String> words = new HashMap<>();
        Set<String> dictionary = getDictionary(src);
        String[] str = {"tiredtinytoes", 
        				"freewilliam", 
        				"freeinjure", 
        				"wholesomething", 
        				"tonguetoes", 
        				"williamwillwritewonderfulwalrus",
        				"aliceinwonderland", "alicee", "handsomean"};
        
        for(int i = 0; i < str.length; i++) {
        	words = new HashMap<>();
        	String res = findSplits(str[i], dictionary, words);
        	System.out.println(res);
        }
    }


    public static Set<String> getDictionary(String source) {
        
        Set<String> dictionary = new HashSet<>();
        Scanner reader;
		try {
			reader = new Scanner(new File(source));
			
	        while(reader.hasNextLine()) {
	            dictionary.add(reader.nextLine());
	        }
	        reader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        return dictionary;
    }

	/*
	 * Let a Map of words consist of
	 * a key String representing the current substring being analyzed for further substrings,
	 * and a value String representing the substrings of that substring.
	 * 
	 * i.e. <freewilliam, "free william">, <iam, "i am">, ...
	 */
    public static String findSplits(String text, Set<String> dictionary, Map<String, String> words) {
    	
		// is the passed text associated with a map key?
    	if(words.keySet().contains(text))
    		return words.get(text); 
    	
		// is the passed text in the dictionary?
    	if(dictionary.contains(text))
    		return text;
    	
    	String result = null;
    	for(int i = 0; i < text.length(); i++) {
    		
    		String pfx = text.substring(0, i+1);
			
			// is the substring (pfx) of text in the dictionary?
    		if(dictionary.contains(pfx)) {   			
    			
    			String sfxRes = null;
    			sfxRes = findSplits(text.substring(i+1, text.length()), dictionary, words);
      			
      			
      			if(sfxRes != null) { // was there more valid words in the suffix of the text?
      				
      				String splitRes = pfx + " " + sfxRes; // concat prefix w/ suffix valid words
      				
					// if there hasn't been a result recorded yet, or existing is contested by smaller amount 
					// of words found in string total, then replace it.
      				if(result == null || splitRes.split(" ").length < result.split(" ").length)
      					result = splitRes;
      			}
    		}
    	}

		// <K,V> as <text passed in, smallest number of words found in THIS text>
		words.put(text, result);
		return result;
    	
    }


}