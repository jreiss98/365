/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc365a1;

import java.util.ArrayList;
import java.util.Collections;

public class HashTable implements java.io.Serializable {

    public class Node implements java.io.Serializable {

        ArrayList<String> reviews;
        ArrayList<Word> words;
        String bname;
        double cosine;
        Node next;
        String id;

        Node(String key) {
            this.bname = key;
            next = null;
            reviews = new ArrayList<>();
            words = new ArrayList<>();
        }

        public void add(ArrayList<String> t) {
            reviews.addAll(t);
        }

        public void compression() {//compresses and slims information to conserve space on disk
            ArrayList<String> temp = new ArrayList<>();
            for (int i = 0; i < reviews.size(); i++) {//runs through all chunks of texts
                String[] tempp = reviews.get(i).split(" |\\n\\n|.|-|!|,|\\/|%");//breaks chunks up into individual words
                for (int y = 0; y < tempp.length; y++) {//runs through all the words
                    if (tempp[y] != null || tempp[y].equals(" ")) {//make sure no spaces are present
                        temp.add(tempp[y]);//add the word to arraylist
                    }
                }
            }
            reviews=null;//all chunks of reviews have been converted into individual words so reviews is null now as it's not needed
            ArrayList<String> check = new ArrayList<>();//this will store all words that we run through to prevent repeats
            for(int i=0;i<temp.size();i++){
                if(check.indexOf(temp.get(i))==-1){//checks if word has already occured
                    check.add(temp.get(i));//if it has then it is added to check
                    int freq = Collections.frequency(temp, temp.get(i));//the frequency of the word within the temp array is found
                    Word w = new Word(temp.get(i),freq);//the word with its frequency is then used to make an object
                    words.add(w);//this object is stored within an arraylist called words
                }
            }
        }
        
    }
    Node[] table;
    int count;

    public HashTable() {
        table = new Node[32];
        for (int i = 0; i < 32; i++) {
            table[i] = null;
        }
    }

    void put(String k, String n) {
        count++;
        int h = Math.abs(k.hashCode());
        int i = h & (table.length - 1);
        Node p = new Node(n);
        p.id = k;
        if (table[i] == null) {
            table[i] = p;

        } else {
            Node temp = table[i];
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = p;

        }
        double cap = .75 * table.length;
        if (count >= cap) {
            resize();//does not work
        }

    }

    public Node get(String l) {
        if (l != null) {
            int h2 = Math.abs(l.hashCode());
            int i = h2 & (table.length - 1);
            Node temp = table[i];
            while (temp != null) {
                if (temp.id.equals(l)) {
                    System.out.println("they are equal");
                    return temp;
                } else {
                    temp = temp.next;
                }
            }
        }
        return null;
    }

    private void resize() {
        ArrayList<Node> temp = getAll();
        Node[] tableTemp = new Node[table.length * 2];
        for (int i = 0; i < tableTemp.length; i++) {
            tableTemp[i] = null;
        }
        for (int i = 0; i < temp.size(); i++) {
            int h = Math.abs(temp.get(i).id.hashCode());
            int l = h & (tableTemp.length - 1);

            if (tableTemp[l] == null) {
                tableTemp[l] = temp.get(i);
            } else {
                Node crawler = tableTemp[l];
                while (crawler.next != null) {
                    crawler = crawler.next;
                }
                crawler.next = temp.get(i);
            }
        }
        table = tableTemp;
    }

    public ArrayList<Node> getAll() {
        ArrayList<Node> temp = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            Node x = table[i];
            if (x != null) {
                while (x.next != null) {
                    x = x.next;
                    temp.add(x);
                }
            }
        }
        for (int i = 0; i < temp.size(); i++) {
            temp.get(i).next = null;
        }
        return temp;
    }

}
//cos = ab/mag(a)*mag(b0
//a is the inputted
//a nd b a dif business
//2 find the magnitiude, find a word and its freq in a review
//magn = finding all word freq and adding them up
//top ab= find union of words between business a and business b
