
public interface SpellCheckTrieListener {
	void insertIntoTree(TrieNode root,String word);
	TrieNode createTree();
	void printTree(TrieNode root,int level,char[] branch);
	String findWord(TrieNode root,String word);
}
