package Project3;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Program {


    public static void main(String[] args) {
        String src = "aliceInWonderlandDictionary.txt";
        Set<String> words = new HashSet<>();
        Set<String> dictionary = getDictionary(src);
        int splits = findSplits(new int[11], "freewilliam", dictionary, words, 0);
        System.out.println("\nsplits found in str: " + splits);
        System.out.println(words);
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


    public static String findSplits(String text, Set<String> dictionary, Map<String, String> words) {
    	
    	if(words.keySet().contains(text))
    		return words.get(text); // with the text passed
    	
    	if(dictionary.contains(text))
    		return text;
    	
    	String result = null;
    	for(int i = 0; i < text.length(); i++) {
    		
    		String pfx = text.substring(0, i+1);
				
    		if(dictionary.contains(pfx)) {   			
    			
    			String sfxRes = null;
    			sfxRes = findSplits(text.substring(i+1, text.length()), dictionary, words);
      			
      			
      			if(sfxRes != null) {
      				
      				String splitRes = pfx + " " + sfxRes;
      				
      				if(result == null || splitRes.split(" ").length < result.split(" ").length) {
      					result = splitRes;
      				}
      			}
    		}
    	}
		words.put(text, result);
		return result;
    	
    }


}