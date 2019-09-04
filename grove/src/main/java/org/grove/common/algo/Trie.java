package org.grove.common.algo;

import java.util.HashMap;
import java.util.HashSet;

class Trie {

    HashMap<Character,Node> root ;
    /** Initialize your data structure here. */
    public Trie() {
        root = new HashMap(32);
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] ca = word.toCharArray();
        HashMap<Character,Node> curNode = root;
        for(int i=0;i<ca.length;i++){
            Node nNode;
            if(curNode.containsKey(ca[i])){
                nNode = curNode.get(ca[i]);
                curNode = nNode.getChild();
                if(i==ca.length-1){
                    curNode.get(ca[i]).setEnd(true);
                }
            }else{
                nNode = new Node();
                nNode.setData(ca[i]);
                nNode.setEnd(i==ca.length-1);
                curNode.put(ca[i],nNode);
                curNode = nNode.getChild();
            }
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        char[] ca = word.toCharArray();
        HashMap<Character,Node> curNode = root;
        boolean result = false;
        for(int i=0;i<ca.length;i++){
            if(curNode.containsKey(ca[i])){
                Node node = curNode.get(ca[i]);
                if(i==ca.length-1 && node.isEnd()){
                    result = true;
                }else{
                    curNode = node.getChild();
                }
            }else{
                break;
            }
        }
        return result;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        char[] ca = prefix.toCharArray();
        HashMap<Character,Node> curNode = root;
        boolean result = false;
        for(int i=0;i<ca.length;i++){
            if(curNode.containsKey(ca[i])){
                Node node = curNode.get(ca[i]);
                if(i==ca.length-1){
                    result = true;
                }else{
                    curNode = node.getChild();
                }
            }else{
                break;
            }
        }
        return result;
    }







    public static class Node{
        char data;
        boolean isEnd = false;
        HashMap<Character,Node> child = new HashMap<>(32);

        public char getData() {
            return data;
        }

        public void setData(char data) {
            this.data = data;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd(boolean end) {
            isEnd = end;
        }

        public HashMap<Character, Node> getChild() {
            return child;
        }

        public void setChild(HashMap<Character, Node> child) {
            this.child = child;
        }

        @Override
        public int hashCode() {
            return data;
        }

    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // returns true
        System.out.println(trie.search("app"));     // returns false
        System.out.println(trie.startsWith("app")); // returns true
        trie.insert("app");
        System.out.println(trie.search("app"));
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
