/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc365a1;
import csc365a1.HashTable.Node;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Tok
 */
//C:\Users\Tok\Desktop\review or C:\Users\Tok\Desktop\business
public class JsonParser{    
    static final int cap = 1;
    public HashTable load() throws Exception {
        
        HashTable ht = new HashTable();
        File f = new File("C:\\Users\\Tok\\Desktop\\business.json");
        File k = new File("C:\\Users\\Tok\\Desktop\\review.json"); 
        Scanner sf = new Scanner(f);
        Scanner sk = new Scanner(k);
        sf.useDelimiter(",|:");
        sk.useDelimiter("\",\"|\":\"");
        String id=null,name=null;
        int i = 0;
        while(sf.hasNextLine() && i < cap){//parse business
            String line = sf.next();
            if(line.contains("business_id")){//parse business id
                line = sf.next();
                id=line.substring(2, line.length()-1);
                System.out.println(id);
            }else if(line.contains("\"name\"")){//parse name of business
                line = sf.next();
                name=line.substring(2, line.length()-1);
                System.out.println(name);
                i++;  
            ht.put(id,name);//adds all data to hashtable
            
            }
        }
        int mark=0;
        while(sk.hasNextLine()){  
            String line = sk.next();
            if(line.contains("business_id")){//parse business id
                line = sk.next();
                id=line.substring(0, line.length());
                System.out.println(id);
            }else if(line.contains("text")){//parse all review text
                int ep=0;
                ArrayList<String> text = new ArrayList<>();
               // String text = new String();
               while(sk.hasNext()){
                   String temp = sk.next();
                 if(temp.contains("useful\":")){//checks for end of review
                 
                        temp=temp.substring(1, temp.length()-1);
                        text.add(temp);
                       break;
                   }else if(ep==0){//checks for start of review
                        temp=temp.substring(0, temp.length());
                        text.add(temp);
                       ep++;
                   }else{//everything else in categories
                       temp=temp.substring(0, temp.length()-1);
                       text.add(temp);
                   }
                   System.out.println(temp);
               }
               Node temp3 = ht.get(id);
               if(temp3 != null) {
                   if(temp3.reviews==null){
                    temp3.reviews = text;
                    mark++;
                   }else{
                       temp3.add(text);
                   }
               }
               if(mark==cap){
                   break;
               }
        }
        }
        return ht;
        }
    }
