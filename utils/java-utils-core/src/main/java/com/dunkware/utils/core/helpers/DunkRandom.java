package com.dunkware.utils.core.helpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class DunkRandom {

	private static final List<String> WORDS = List.of(
	        "apple", "banana", "cherry", "date", "elderberry", "fig", "grape", "honeydew",
	        "kiwi", "lemon", "mango", "nectarine", "orange", "papaya", "quince", "raspberry",
	        "strawberry", "tangerine", "ugli", "vanilla", "watermelon", "xigua", "yellowfruit", "zucchini",
	        "apricot", "blackberry", "blueberry", "cantaloupe", "coconut", "cranberry", "currant", "dragonfruit",
	        "gooseberry", "grapefruit", "guava", "jackfruit", "kumquat", "lime", "lychee", "mandarin",
	        "mulberry", "olive", "passionfruit", "peach", "pear", "persimmon", "pineapple", "plum",
	        "pomegranate", "pomelo", "rambutan", "redcurrant", "salak", "satsuma", "soursop", "starfruit",
	        "tamarind", "tomato", "boysenberry", "cloudberry", "dewberry", "feijoa", "jabuticaba", "jostaberry",
	        "kiwifruit", "longan", "loquat", "marionberry", "medlar", "miraclefruit", "nance", "pequi",
	        "pitaya", "pricklypear", "safou", "salmonberry", "saskatoonberry", "serviceberry", "sharonfruit", "sugarapple",
	        "surinamcherry", "tayberry", "whitecurrant", "whitefig", "whitemulberry", "yellowpassionfruit", "yumberry", "yunnanhackberry",
	        "zinfandelgrape", "zapote", "zigzagvine", "ziziphus", "zabergauapfel", "zlotyapirog"
	    );

	    private static final Random RANDOM = new Random();

	    public static List<String> generateWords(int count) {
	        List<String> randomWords = new ArrayList<>();
	        for (int i = 0; i < count; i++) {
	            randomWords.add(generateRandomWord());
	        }
	        return randomWords;
	    }
	    
	    public static List<String> generateUniqueWords(int count) {
	        if (count > WORDS.size()) {
	            throw new IllegalArgumentException("Requested word count exceeds available unique words");
	        }

	        Set<String> uniqueWords = new HashSet<>();
	        while (uniqueWords.size() < count) {
	            uniqueWords.add(generateRandomWord());
	        }

	        return new ArrayList<>(uniqueWords);
	    }

	    

	    public static String generateRandomWord() {
	        int index = RANDOM.nextInt(WORDS.size());
	        return WORDS.get(index);
	    }

	    public static void main(String[] args) {
	        // Example usage
	        List<String> randomWords = DunkRandom.generateWords(10);
	        System.out.println("Random Words: " + randomWords);

	        String randomWord = DunkRandom.generateRandomWord();
	        System.out.println("Random Word: " + randomWord);
	    }
	
	public static int getRandom(int min, int max) {
	    if (min > max) {
	        throw new IllegalArgumentException("Min " + min + " greater than max " + max);
	    }      
	    return (int) ( (long) min + Math.random() * ((long)max - min + 1));
	}
}
