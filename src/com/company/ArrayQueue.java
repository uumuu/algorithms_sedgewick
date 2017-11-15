/*
Array-based FIFO queue that allows for inserting at the end of the queue and removing from the beginning.
Peek returns least-recently (first) added item.
 */
package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int n;
    private int first;
    private int last;

    @SuppressWarnings("unchecked")
    public ArrayQueue(){

        q = (Item[]) new Object[2];
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
        for(int i = 0; i < n; i++){
            temp[i] = q[(first+i) % q.length];
        }
        q = temp;
        first = 0;
        last = n;
    }

    public void enqueue(Item item){
        if(n == q.length) resize(2*q.length);
        q[last++] = item;
        if (last == q.length) last = 0;
        n++;
    }

    public Item dequeue(){
        // dequeue if it is empty
        // if it has items in it
        // modify first variable (increment)
        // event if first == length, we need to wrap around
        // return item we remove

        if(isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = q[first];
        q[first] = null;
        n--;
        first++;
        if (first == q.length) first = 0;
        if (n > 0 && n == q.length/4) resize(q.length/2);
        return item;

    }

    public Item peek(){
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return q[first];
    }

    public Iterator<Item> iterator(){
        return new ArrayIterator();
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for (Item item : q) {
            System.out.println(item);
            s.append(item);
            s.append(" ");
        }
        return s.toString();
    }

    private class ArrayIterator implements Iterator<Item>{
        private int i = 0;
        public boolean hasNext() { return i < n; }
        public void remove() { throw new UnsupportedOperationException();}

        public Item next(){
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[(i + first) % q.length];
            return item;
        }
    }
}
