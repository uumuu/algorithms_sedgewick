/*
Program that takes a filepath as command-line argument and recursively prints the contents of the directories
it encounters.
Uses queue implementation.
 */
package com.company;

import java.io.File;

public class FileList<Item> {
    private Queue<String> q;
    private int depth;
    private static final String TAB = "  ";

    public FileList(String rootDir){
        q = new Queue<>();
        depth = 0;
        evaluateDirectory(new File(rootDir));
    }

    public void add(String fileName){
        //method that adds the correct amount of indentation to the current file/directory
        //and enqueues it to the queue ready for printing.
        String indent = "";
        for(int i = 0; i < depth; i++){
            indent+=TAB;
        }
        q.enqueue(indent + fileName);
    }

    public void evaluateDirectory(File dir){
        //evaluates current directory, adds all directories and files to add() method
        //which enqueues the string name to queue.
        //if directory is found, this method is recalled on that directory.
        if(!dir.isDirectory()){
            throw new IllegalArgumentException("Path does not exist.");
        }
        add(dir.getName() + " -\\");
        depth++;
        File[] fileArr = dir.listFiles();
        for(File file : fileArr){
            if(file.isDirectory()){
                evaluateDirectory(file);
            }else{
                add(file.getName());
            }
        }
        depth--;
    }

    public void print(){
        //prints the output of the queue file system.
        for(String s: q){
            System.out.println(s);
        }
    }

    public static void usage(){
        //prints correct usage if there is an inout error.
        System.out.println("Usage: java FileList <directory path>");
    }

    public static void main(String[] args) {
        if (args.length < 1){ usage(); }

        FileList tree = new FileList(args[0]);
        tree.print();
    }

}
