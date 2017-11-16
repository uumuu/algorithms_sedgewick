/*
Array-based Data structure that is first-in-first-out.
Ring-architecture (last -> first)
Fixed size of N.
Elements wrap around with the usage of modulus increment (last = (last+1) % max)
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

    public boolean isFull(){
        //is full if first is one element higher than last after being modded by max
        return first == (last+1) % max;
    }

    public void add(Item item){
        //last increments, but wraps around to be zero if it is equal to max
        //if the array is empty, we need to set first to be last as it will be
        //the first element in the array.
        if(isFull()){throw new NoSuchElementException("Queue is full.");}
        last = (last+1) % max;
        ring[last] = item;
        if(isEmpty()){first = last;}
        N++;
    }

    public Item remove(){
        //gets item at ring[first], sets that position to be null
        //then increments first with the wraparound feature.
        if(isEmpty()){throw new NoSuchElementException("Queue is empty");}
        Item item = ring[first];
        ring[first] = null;
        first = (first+1) % max;
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
}
