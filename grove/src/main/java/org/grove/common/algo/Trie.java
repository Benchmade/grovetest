package org.grove.common.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class Trie {

   Node root ;
    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] ca = word.toCharArray();
        Node curNode = root;
        for(int i=0;i<ca.length;i++){
            Node nn = curNode.getChild()[ca[i]-'a'];
            if(nn==null){
                Node insertNode = new Node();
                insertNode.setData(ca[i]);
                if(i==ca.length-1){
                    insertNode.setEnd(true);
                }
                curNode.getChild()[ca[i]-'a']= insertNode;
                curNode = insertNode;
            }else{
                if(i==ca.length-1){
                    nn.setEnd(true);
                }
                curNode = nn;
            }
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        char[] ca = word.toCharArray();
        Node curNode = root;
        boolean result = false;
        for(int i=0;i<ca.length;i++){
            Node nn = curNode.getChild()[ca[i]-'a'];
            if(nn!=null){
                if(i==ca.length-1 && nn.isEnd()){
                    result = true;
                }else{
                    curNode = nn;
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
        Node curNode = root;
        boolean result = false;
        for(int i=0;i<ca.length;i++){
            Node nn = curNode.getChild()[ca[i]-'a'];
            if(nn!=null){
                if(i==ca.length-1){
                    result = true;
                }else{
                    curNode = nn;
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
        Node[] child = new Node[26];

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

        public Node[] getChild() {
            return child;
        }

        public void setChild(Node[] child) {
            this.child = child;
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
