/*
Program that takes a filepath as command-line argument and recursively prints the contents of the directories
it encounters.
Uses array-based implementation.

1.Evaluates the file system. Passes original directory files to enqueue
  ???????If it finds any directories ??????
2.enqueue the contents, if there is a new directory create a new list within the one we are currently in??
3.Print the contents of the queues?

 */
package com.company;

import java.io.File;
import java.io.IOException;

public class FileList {
    private String[] arr;
    private String dirPath;
    private int first;
    private int last;
    private int n;

    public FileList(String dirPath){
        arr = new String[2];
        dirPath = dirPath;
        first = 0;
        last = 0;
        n = 0;
    }

    public void evaluateDirectory() throws IOException {
        File f = new File(dirPath);
        //check directory is actually valid, if +not, close the program.
        if(!f.isDirectory()){
            throw new IOException("Path does not exist.");
        }else{
            //generate queue for the filepath including queues within queues for new directories found
            String[] fileArr = f.list();

            for(String file : fileArr){
                System.out.println(file);
            }

            //if there is a directory in the filepath, call the printer method on that,too.
        }


    }
    public boolean isEmpty(){
        return n == 0;
    }

    public void push(String item) {
        //FIFO push to first in the queue.
    }

    public String printTree(){
    //prints the output of the recursively added queue file system.
    }

    public static void main(String[] args) throws IOException {
        FileList q = new FileList(args[0]);
        try{
            q.evaluateDirectory();
        }catch(IOException e){
            System.out.println("Path is invalid");}

        System.out.println(q.printTree());
    }

}
