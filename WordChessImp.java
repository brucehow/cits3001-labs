import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * A class that implements the fractional and discrete Knapsack algorithms
 * by implementing the WordChess interface.
 * @author Bruce How (22242664)
 */

public class WordChessImp implements WordChess {

    /**
     * Private subclass which stores each word and the parent Node 
     * leading to the word.
     */
    private class Node {
        String word;
        Node parent;
        int depth;

        public Node(String word, Node parent, int depth) {
            this.word = word;
            this.parent = parent;
            this.depth = depth;
        }
    }

    /**
     * Private subclass to represent a TrieNode used in the Trie
     * data structure.
     */
    private class TrieNode {
        char ch;
        ArrayList<TrieNode> children;
        boolean end;

        public TrieNode(char ch){
            this.ch = ch;
            this.children = new ArrayList<TrieNode>();
            this.end = false;
        }
    }

    /**
     * Private subclass to represent the Trie data structure.
     * The Trie data structure is a efficient data stucture
     * that can be used to search and insert Strings. It is
     * used to contain the valid words from a dictionary in
     * this case. Inserting and searching is done in O(n)
     * where n is the length of the word to search.
     */
    private class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode('*');
        }

        /**
         * Performs a search to identify all words that differ
         * by only one character at most. The complexity of this
         * method is O(n * alphabet_size) where n is the length
         * of the word to search.
         */
        public ArrayList<String> getNeighbours(String word) {
            ArrayList<String> res = new ArrayList<String>();

            for (int i = 0; i < word.length(); i++) {
                String prepend = word.substring(0, i);
                TrieNode node = root;
                int j = 0;
                boolean exit = false;
                while (j < i) {
                    boolean found = false;
                    for (TrieNode child : node.children) {
                        if (child.ch == word.charAt(j)) {
                            found = true;
                            node = child;
                        }
                    }
                    if (!found) {
                        exit = true;
                    }
                    j++;
                }
                if (exit) {
                    continue;
                }
                for (TrieNode varChild : node.children) {
                    if (varChild.ch == word.charAt(i)) {
                        continue;
                    }
                    j = i + 1;
                    TrieNode nestedNode = varChild;
                    boolean exitEarly = false;
                    while (j < word.length()) {
                        boolean found = false;
                        for (TrieNode child : nestedNode.children) {
                            if (child.ch == word.charAt(j)) {
                                found = true;
                                nestedNode = child;
                            }
                        }
                        if (!found) {
                            exitEarly = true;
                            break;
                        }
                        j++;
                    }
                    if (exitEarly) {
                        continue;
                    }
                    if (nestedNode.end) {
                        if (i == word.length() -1) {
                            res.add(prepend + varChild.ch);
                        } else {
                            res.add(prepend + varChild.ch + word.substring(i+1));
                        }
                    }
                }
            }
            return res;
        }

        /**
         * Adds a word to the Trie. Used to store all
         * valid words from a dictionary. A valid word
         * is a word that has the same length as the
         * starting and ending word.
         * @param word The word to add to the Trie
         */
        public void addWord(String word) {
            TrieNode node = root;

            for (int i = 0; i < word.length(); i++) {
                boolean found = false;
                for (TrieNode child : node.children) {
                    if (child.ch == word.charAt(i)) {
                        found = true;
                        node = child;
                        break;
                    }
                }
                if (!found) {
                    TrieNode newNode = new TrieNode(word.charAt(i));
                    node.children.add(newNode);
                    node = newNode;
                }
            }
            node.end = true;
        }
    }

    @Override
    public String[] findPath(String[] dictionary, String startWord, String endWord) {
        int length = startWord.length();
        Trie trie = new Trie();
        for (String word : dictionary) {
            if (word.length() == length) {
                trie.addWord(word);
            }
        }

        Deque<Node> queue = new LinkedList<>();
        HashSet<String> explored = new HashSet<>();
        Node current = null;
        Boolean foundWord = false;

        queue.addLast(new Node(startWord, null, 1));
        explored.add(startWord);

        while(!foundWord) {
            current = queue.removeFirst();

            ArrayList<String> words = trie.getNeighbours(current.word);
            for (String word : words) {
                if (!explored.contains(word)) {
                    if (word.equals(endWord)) {
                        foundWord = true;
                        break;
                    }
                    queue.addLast(new Node(word, current, current.depth+1));
                    explored.add(word);
                }    
            }
        }

        String[] result = new String[current.depth+1];
        result[result.length-1] = endWord;
        for (int i = result.length-1; i > 0; i--) {
            result[i-1] = current.word;
            current = current.parent;
        }
        return result;
    }

}