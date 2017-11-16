/*
Array-based Queue that removes and returns Kth least recently inserted item
*/
package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class GeneralisedQueue<Item>implements Iterable<Item> {
    private Item[] q;
    private int first;
    private int last;
    private int n;

    @SuppressWarnings("unchecked")
    public GeneralisedQueue(){
        // creates an empty array for the queue
        q = (Item[]) new Object[2];
        first = 0;
        last = 0;
        n = 0;
    }

    public boolean isEmpty(){
        //checks if the queue is empty
        return n == 0;
    }

    @SuppressWarnings("unchecked")
    public void resize(int capacity){
        //resizes the queue when it is full or it is 1/4 of capacity
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++){
            temp[i] = q[(i+first) % q.length];
        }
        q = temp;
        first = 0;
        last = n;
    }

    public void insert(Item item){
        // checks if array is full, doubles capacity if so.
        // then inserts the item at the end of the array.
        if (n == q.length){resize(q.length * 2);}
        q[last++] = item;
        if (last == q.length){last = 0;}
        n++;
    }

    public Item remove(int k){
        //checks if n >= k, if not there is not a kth element to remove
        //removes kth least recently added element from the queue by counting back
        //k times from the last element (n)

        if(q[n-k] == null){throw new NoSuchElementException("item does not exist");}

        Item item = q[n-k];

        q[n-k] = null;
        n--;
        if (n >0 && n == q.length /4){resize(q.length/2);}
        return item;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for(Item item: q){
            if (item != null) {
                s.append(item);
                s.append(" ");
            }
        }
        return s.toString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }
    @SuppressWarnings({"unchecked"})
    private class ArrayIterator<Item> implements Iterator<Item>{
        private int i = 0;

        public boolean hasNext(){return i < n;}
        public void remove(){throw new UnsupportedOperationException();}
        public Item next(){
            if (!hasNext()){throw new NoSuchElementException();}
            Item item = (Item) q[(i + first) % q.length];
            return item;
        }
    }
}
