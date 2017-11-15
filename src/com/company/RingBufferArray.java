/*
Array-based Data structure that is first-in-first-out.
Ring-architecture (last -> first)
Fixed size of N.
Wraparound needs to be implemented when adding/removing and also when inserting when the buffer is full,
instead of throwing an error, it should maybe replace the first inserted[?]
 */

package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RingBufferArray<Item> implements Iterable<Item> {
    private Item[] ring;
    private int first;
    private int last;
    private int N;
    private int max;

    @SuppressWarnings("unchecked")
    public RingBufferArray(int limit){
        ring = (Item[]) new Object[limit];
        first = 0;
        last = 0;
        N = 0;
        max = limit;
    }

    public boolean isEmpty(){return N == 0;}

    public boolean isFull(){return N == max;}

    public void add(Item item){
        if(isFull()){throw new NoSuchElementException("Queue is full.");}
        ring[last++] = item;
        if(last == ring.length) last = 0;
        N++;
    }

    public Item remove(){
        if(isEmpty()){throw new NoSuchElementException("Queue is empty");}
            Item item = ring[first];
            ring[first] = null;
            first++;
            N--;
            return item;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for(Item item: ring){
            if(item != null) {
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
    @SuppressWarnings("unchecked")
    private class ArrayIterator<Item> implements Iterator<Item>{
        private int i=0;

        public boolean hasNext(){return i < N;}
        public void remove(){throw new UnsupportedOperationException();}
        public Item next(){
            Item item = (Item) ring[(i + first) % ring.length];
            i++;
            return item;

        }
    }

    public static void main(String[] args){
        RingBufferArray<Integer> ring = new RingBufferArray<>(5);
        for(int i = 1; i < 6; i++){
            ring.add(i);
        }
        System.out.println(ring);
        ring.remove();
        System.out.println(ring);
        Iterator iter = ring.iterator();

        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());

    }
}
