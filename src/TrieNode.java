/**
 * Custom Trie Object
 * 
 * Letter - the Letter which the trie node will contain
 * Links - the Link to the next Trie Node , basically each node will hold the next link to the Trie Node and we assume that there will be a lowercase letter so the size is fixed to 26.
 * validWord - Boolean flag which checks if a valid word ends at that particular node or not . This helps us to decide if there is a word at that node or do we have to continue further.
 * 
 * */
public class TrieNode {

	char letter;
	TrieNode[] links;
	boolean validWord;

	TrieNode(char letter,boolean validWord)
	{
		this.letter = letter;
		links =  new TrieNode[26];
		this.validWord = validWord;
	}
}
