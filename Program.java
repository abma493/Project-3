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


    public static int findSplits(int[] c, String text, Set<String> dictionary, Set<String> words, int i) {
    	
    	if(c.length <= 0 || i >= c.length)
    		return 0;
    	
    	if(i == c.length - 1 && dictionary.contains(text.substring(0, i) + "" + text.charAt(i))) { // i - 1 implied in substring method docs
    		words.add(text);
    		return 1;
    	}
    	
    	System.out.println("len/i " + c.length + "-" + i + "   " + text.charAt(i)); // debug

    	if(dictionary.contains(text.substring(0, i) + "" + text.charAt(i))) {
    		c[i] = 1;
    	} else if (dictionary.contains("" + text.charAt(i))) {
    		
    		if(c[i - 1] > 0) { // add prev. substring split count + 1 (from this existing word)
    			c[i] = c[i - 1] + 1;
    			
    			if(i < c.length) {
    				return findSplits(c, text, dictionary, words, ++i);
    			}
    			
    		}
    		
    	}
    	
    	if( (i-1 >= 0) && c[i-1] == 1) { // letter here may not be a valid word or concatenation
    		words.add(text.substring(0, i));
    		return 1 + findSplits(Arrays.copyOfRange(c, i, c.length), text.substring(i, c.length), dictionary, words, 0);
    	} else {
    		return findSplits(c, text, dictionary, words, ++i);
    	}

    }


}