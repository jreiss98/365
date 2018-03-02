/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc365a1;

import csc365a1.HashTable.Node;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 * @author Tok
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        JsonParser jp = new JsonParser();

        HashTable ht = jp.load();
        ArrayList<Node> temp = ht.getAll();
        for(int i=0;i<temp.size();i++){
            temp.get(i).compression();
        }
        HashTable.Node temp4 = ht.get("He-G7vWjzVUysIKrfNbPUQ");
        System.out.println("id in main " + temp4.bname);
        //***************************************************
        //saves data to disk
        OutputStream op;
        ObjectOutputStream oos;
        op = new FileOutputStream("C:\\Users\\tok\\Documents\\NetBeansProjects\\csc365a1\\src\\csc365a1\\Data", false);
        oos = new ObjectOutputStream(op);
        oos.writeObject(ht);
        oos.close();
        //*****************************************************
        //calls data back
        File f = new File("C:\\Users\\tok\\Documents\\NetBeansProjects\\csc365a1\\src\\csc365a1\\Data");
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ht = (HashTable) ois.readObject();        
        ois.close();
        //*****************************************************
    }
}
