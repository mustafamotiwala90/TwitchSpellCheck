

/**
 * The main Trie class
 * insertIntoTree - inserts a word into the trie
 * createTree - initiazlize the Trie with a initial root element of "\0"
 * printTree - Helper method to print the Trie traversing each node.
 * findWord - Method which checks if a word is present in the trie , if it is then it will return the valid word.
 * checkForRepeatedLetter,checkIfVowel,nextLetterVowel : Helper methods used by the findWord method to check for mistakes in the given word.
 * */





import java.util.Set;
import java.util.TreeSet;

public class SpellCheckTrie implements SpellCheckTrieListener{
	static Set<Character> vowelSet = new TreeSet<>();
	static{
		vowelSet.add('a');
		vowelSet.add('e');
		vowelSet.add('i');
		vowelSet.add('o');
		vowelSet.add('u');
	}


	public SpellCheckTrie() {
	}

	@Override
	public void insertIntoTree(TrieNode root,String word)
	{
		if(word.length()==0)
			return;

		char[] letters = word.toCharArray();
		TrieNode currentNode = root;
		for(int i =0;i<word.length();i++)
		{
			if(currentNode.links[letters[i] - 'a']==null)
			{
				boolean lastLetter = i==word.length()-1 ? true : false;
				currentNode.links[letters[i]-'a'] = new TrieNode(letters[i],lastLetter);
			}
			currentNode = currentNode.links[letters[i]-'a'];
		}
	}

	@Override
	public TrieNode createTree()
	{
		return(new TrieNode('\0',false));
	}

	@Override
	public void printTree(TrieNode root,int level,char[] branch)
	{
		if(root==null)
			return;

		for(int i = 0;i<root.links.length;i++)
		{
			branch[level] = root.letter;
			printTree(root.links[i],level + 1,branch);
		}

		if(root.validWord)
		{
			for(int j = 1;j<=level;j++)
				System.out.print(branch[j] + "->");
			System.out.println();
		}
	}

	@Override
	public String findWord(TrieNode root,String s) {

		if(s.length()==0)
			return null;

		String word = s.toLowerCase();
		TrieNode currentNode = root;
		TrieNode prevNode = null;
		String emptySTr = "";
		StringBuilder wordStr = new StringBuilder();
		char[] letters = word.toCharArray();
		int index;
		for(index= 0;index<letters.length;index++) {
			prevNode = currentNode;
			currentNode = currentNode.links[letters[index]-'a'];
			if(currentNode == null)
			{

				TrieNode vowelNode = checkIfVowel(prevNode,letters[index]);
				if(vowelNode!=null && nextLetterVowel(prevNode.letter,letters[index+1])) {
					//					System.out.println("Vowel node : "+vowelNode.letter);
					currentNode = vowelNode;
					wordStr.append(currentNode.letter);
					continue;
				}

				TrieNode repeatedNode = checkForRepeatedLetter(prevNode,letters[index]);
				if(repeatedNode!=null) {
					//					System.out.println("Repeated letter : "+repeatedNode.letter);
					currentNode = repeatedNode;
					continue;
				}


				return emptySTr;
			}
			wordStr.append(currentNode.letter);
		}

		if(index == letters.length && currentNode==null)
			return emptySTr;

		if(currentNode!=null && !currentNode.validWord)
			return emptySTr;

		return wordStr.toString();
	}

	boolean nextLetterVowel(char prevLetter,char letter)
	{
		if(prevLetter==letter)
			return false;
		return true;
	}

	public TrieNode checkForRepeatedLetter(TrieNode node,char prevLetter)
	{
		TrieNode repeatedNode = null;
		if(node.letter==prevLetter)
			repeatedNode=node;
		return repeatedNode;
	}


	public TrieNode checkIfVowel(TrieNode node,char prevLetter)
	{
		TrieNode validNode = null;
		if(vowelSet.contains(prevLetter)) {
			for(char vowel : vowelSet)
			{
				validNode = node.links[vowel - 'a'];
				if(validNode!=null)
				{
					return validNode;
				}
			}
			return validNode;
		}
		return validNode;
	}

}
