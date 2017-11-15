package com.company;

//1.3.15 Write a Queue client that takes a command-line argument k and prints the kth
// from the last string found on standard input (assuming that standard input has k or more strings).

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputFinderQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int n;
    private int first;
    private int last;

    @SuppressWarnings("unchecked")
    public InputFinderQueue(){
        q  = (Item[]) new Object[2];
        n = 0;
        first = 0;
        last = 0;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }

    @SuppressWarnings("unchecked")
    public void resize(int capacity){
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];

        for (int i = 0; i < n; i++){
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last = n;
    }

    public void enqueue(Item item){

        if (n == q.length) {resize(q.length * 2);}
        q[last++] = item;
        if(last == q.length) { last = 0;}
        n++;
    }

    public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item oldFirst = q[first];
        q[first] = null;
        n--;
        first++;
        if(first == q.length) { first = 0;}
        if (n > 0 && n == q.length/4) {resize(q.length/2);}
        return oldFirst;
    }
    public Item peek(){
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return q[first];
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for(Item item: q){
            s.append(item);
            s.append(" ");
        }
        return s.toString();
    }
    public Iterator<Item> iterator() { return new ArrayIterator();}

    private class ArrayIterator implements Iterator<Item>{
        private int i = 0;
        public boolean hasNext() { return n > i;}

        public void remove() { throw new UnsupportedOperationException();}

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[(first + i) % q.length];
            return item;
        }
    }

    public static void main(String args[]){
        InputFinderQueue<String> inputQueue = new InputFinderQueue<>();

        String regex = "[0-9]+";
        if (!args[0].matches(regex)){
            System.out.println("Invalid number.");
            System.exit(0);
        }
        int elementNum = Integer.parseInt(args[0]);

        System.out.println("Please enter a string.");
        Scanner input = new Scanner((System.in));
        String str = input.nextLine();
        input.close();

        String[] words = str.split(" ");

        for (String word: words) {
            System.out.println(word);
            inputQueue.enqueue(word);
        }

        for (int j = words.length; j> elementNum; j--){
            inputQueue.dequeue();
        }
        System.out.println(inputQueue);
        String kString = inputQueue.peek();

        System.out.println("Kth String is: "+kString);
    }

}

