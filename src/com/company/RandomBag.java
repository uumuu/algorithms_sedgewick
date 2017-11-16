/*
Random bag data structure.
Adds things to the array in random order.
 */

package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomBag<Item> implements Iterable<Item> {
    private Item[] bag;
    private int n;

    @SuppressWarnings("unchecked")
    public RandomBag(){
        bag = (Item[]) new Object[2];
        n = 0;
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

        for(int i = 0; i < n; i++){
            temp[i] = bag[i];
        }
        bag = temp;
    }

    public void add(Item item){
        if (n == bag.length){resize(n *2);}
        bag[n++] = item;
    }

    public String toString(){
        //prints out every non-null item in the bag.
        StringBuilder s = new StringBuilder();
        Random rand = new Random();

        int r;
        Item temp;

        for (int i = 0; i < n; i++) {
            r = rand.nextInt(n);
            temp = bag[r];
            bag[r] = bag[i];
            bag[i] = temp;
        }
        for(Item item : bag){
            if(item != null) {
                s.append(item);
                s.append(" ");
            }
        }
        return s.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        public ArrayIterator() {
            Random rand = new Random();

            int r;
            Item temp;

            for (int i = 0; i < n; i++) {
                r = rand.nextInt(n);
                temp = bag[r];
                bag[r] = bag[i];
                bag[i] = temp;
            }
        }
        private int i = 0;
        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return bag[i++];
        }
    }
}
