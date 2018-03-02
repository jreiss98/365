/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc365a1;

import java.util.ArrayList;


/**
 *
 * @author Tok
 */
public class Similarity {
    

    private JsonParser parser = new JsonParser();
    private HashTable similarBusissnes;
    private double similarmagit;
    private HashTable userEntry;
    private double usermagit;

    double cosine(HashTable s, HashTable b) throws Exception {
        similarBusissnes=s;
        userEntry=b;
        double ab = top();
        similarmagit = magmake(similarBusissnes);
        usermagit = magmake(userEntry);
        double value = ab / (usermagit * similarmagit);
        System.out.println("Cosine done");
        return value;
    }

    private double magmake(HashTable v) {
        ArrayList<Integer> fre = new ArrayList<>();
        ArrayList<Word> words = v.getAll();
        for (int k = 0; k < words.size(); k++) {
            fre.add(words.get(k).getCount());
        }
        double b = magitude(fre);
        return b;
    }

    private double magitude(ArrayList<Integer> a) {
        double count = 0;
        double finalcount;
        for (int i = 0; i < a.size(); i++) {
            double k = a.get(i);
            count += Math.pow(k, 2);
        }

        finalcount = Math.sqrt(count);
        return finalcount;

    }

    private double top() {//computates the A*B
        ArrayList<String> setA = new ArrayList();
        ArrayList<String> setB = new ArrayList();
        ArrayList<String> union = new ArrayList();
        ArrayList<Word> similar;
        ArrayList<Word> user;
        HashTable a = similarBusissnes;
        HashTable b = userEntry;
        double sum = 0;
        similar = a.getAll();
        user = b.getAll();
        for (int i = 0; i < similar.size(); i++) {
            String bet = similar.get(i).word;
            union.add(bet);
            setA.add(bet);
        }
        for (int i = 0; i < user.size(); i++) {
            String bet = user.get(i).word;
            union.add(bet);
            setB.add(bet);
        }
        union.retainAll(setA);
        union.retainAll(setB);//gets all words in a that are in b
        if (!union.isEmpty()) {
            for (int i = 0; i < union.size(); i++) {
                Word temp = a.get(union.get(i));
                Word tem = b.get(union.get(i));
                sum += ((temp.getCount()) * (tem.getCount()));
            }//mult count of word in both a and b together and adding to sum
        } else {

        }
        return sum;
    }
      int numberWebsites = 0;

}
