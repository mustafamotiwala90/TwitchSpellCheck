import java.util.Random;

/*
 * @autor : Mustafa Motiwala
 * Main SpellCheck class
 * Creates a Trie out of Dictionary words
 * Inserts each word into the trie
 * For finding a word it tries to follow the optimal path for the trie and sees if the word can be generated or not
 * dictionaryWords - dictionary of words to be inserted into the Trie
 * wordsSpellingMistake - For creating the spelling Mistake
 * */

public class SpellCheck {


	static String[] dictionaryWords = {"an", "ant", "allot", "alloi", "are", "sheep", "people","wake","job","conspiracy"};
	static String[] wordsSpellingMistake = {"job","sheep","people"};

	//Main class which first creates a trie,adds elements into the Trie,searches for a word and then finally generates the spelling mistakes
	public static void main(String[] args) {


		SpellCheckTrie tree = new SpellCheckTrie();
		TrieNode root = tree.createTree();

		// Check mistakes here before adding it into the tree
		//first mistake : convert to lowercase before adding

		for(String word : dictionaryWords){
			word.toLowerCase();
			tree.insertIntoTree(root,word);
		}

		char[] branch = new char[50];
		tree.printTree(root,0,branch);

		String wordNode = tree.findWord(root,"ppppppeeeeeeoooooppppppllllleeee");

		if(wordNode.length()>0)
			System.out.println(wordNode);
		else
			System.out.println("NO SUGGESTION");

		generateSpellingMistakes(tree,root,wordsSpellingMistake);
	}

	// FINAL STEP PROGRAM : this method basically generates spelling mistakes and other mistakes and then checks the word against the Trie to see if the word exists or not
	static void generateSpellingMistakes(SpellCheckTrie tree,TrieNode root,String[] wordsToSearch) {

		int max = 3;
		int min = 1;
		for (int i = 0; i < wordsToSearch.length; i++) {

			String word = "";
			switch (returnRandomNumber(min,max)) {
			case 1:
				String temp = wordsToSearch[i].toUpperCase();
				word = tree.findWord(root,temp);
				break;
			case 2:
				String replaceVowelStr = replaceVowel(wordsToSearch[i]);
				//				System.out.println("Replace vowel String :"+replaceVowelStr);
				word = tree.findWord(root,replaceVowelStr);
				break;
			case 3:
				String duplicateCharsStr = addDuplicateChars(wordsToSearch[i]);
				//				System.out.println("Duplicate chars String :"+duplicateCharsStr);
				word = tree.findWord(root,duplicateCharsStr);
				break;

			default:
				break;
			}
			if(word.length()>0)
				System.out.println("Word found : " +word);
			else
				System.out.println("NO SUGGESTION");

		}
	}

	// helper class to generate a random integer between a given min and max
	private static int returnRandomNumber(int min,int max) {
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}

	// Second step : adding Duplicate chars in a string to check if the findWord method actually runs
	private static String addDuplicateChars(String source) {
		String result = "";
		int numberPerLetter = returnRandomNumber(1, 10);
		for (int i = 0; i < source.length(); i++) {
			char c = source.charAt(i);
			for (int j = 0; j < numberPerLetter; j++) {
				result += c;
			}
		}
		return result;
	}


	//replacing the vowel with a vowel 'a' to generate mistakes in a valid word
	private static String replaceVowel(String original) {
		String returnStr = null;
		for(char ch:original.toCharArray()) {
			if(SpellCheckTrie.vowelSet.contains(ch))
			{
				returnStr = original.replace(ch, 'a');
			}
		}
		return returnStr;
	}
}
