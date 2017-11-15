/*
Array-based Data structure that enqueues items to a queue like normal but removes each item randomly
Does this by removing from a random element in the array.
Sample method used to view a random item in the queue, it does not remove the item from the queue.
 */
package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int first;
    private int last;
    private int n;

    @SuppressWarnings("unchecked")
    public RandomQueue(){
        q = (Item[]) new Object[2];
        first = 0;
        last = 0;
        n = 0;
    }

    public boolean isEmpty(){
        //checks if the queue is empty or not
        return n == 0;
    }

    public int size() {
        return n;
    }
    @SuppressWarnings("unchecked")
    public void resize(int capacity){
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];

        for(int i = 0; i< n; i++){
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last = n;
    }

    public void enqueue(Item item){
        //enqueues an item to the queue by adding it to the end
        if(n == q.length){resize(n*2);}
        q[last++] = item;
        if (last == q.length){last = 0;}
        n++;
    }

    public Item dequeue(){
        // removes and returns a random item from the queue
        // does this by choosing a random item in array then swaps with the last item.
        // we then remove and return that item.
        if (isEmpty()){throw new NoSuchElementException("Underflow");}
        Random rand = new Random();
        int r = rand.nextInt(n);
        Item tempItem = q[last];
        q[last] = q[r];
        q[r] = tempItem;
        q[last] = null;
        n--;
        return tempItem;
    }

    public Item sample(){
        // returns a random item from the queue without removing it
        if (isEmpty()) throw new NoSuchElementException("Underflow");
        Random rand = new Random();
        int r = rand.nextInt(n);
        return q[r];
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        Random rand = new Random();

        int r;
        Item temp;

        for(int i = 0; i < n; i++) {
            r = rand.nextInt(n);
            temp = q[r];
            q[r] = q[i];
            q[i] = temp;
        }

        for (Item item: q){
            s.append(item);
            s.append(" ");
        }
        return s.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new  ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item>{

        public ArrayIterator(){
            Random rand = new Random();

            int r;
            Item temp;

            for(int i = 0; i < n; i++){
                r = rand.nextInt(n);
                temp = q[r];
                q[r] = q[i];
                q[i] = temp;
            }
        }

        private int i = 0;

        public boolean hasNext(){
            return i < n;
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
        public Item next(){
            if (!hasNext()) throw new NoSuchElementException();
            return q[i++];
        }
    }

    public static void main(String[] args){
        RandomQueue<String> q = new RandomQueue<>();

        q.enqueue("Hello");
        q.enqueue("World");
        q.enqueue("kaw4");
        q.enqueue("is");
        q.enqueue("mary");
        q.enqueue("jack");
        q.enqueue("a");
        q.enqueue("nice");
        q.enqueue("person");
        q.enqueue("they");

        System.out.println(q);
        q.dequeue();
        q.dequeue();
        System.out.println(q);
//        for(int i = 0; i <6; i++){
//            System.out.println(q.sample());
//        }
    }
}
