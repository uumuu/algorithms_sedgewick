/*
Linked-list-based Data structure that is first-in-first-out.
Ring-architecture (last.next = first)
Fixed size of N.
 */

package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RingBuffer<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int max;
    private int N;

    private static class Node<Item>{
        Item item;
        Node<Item> next;
    }

    public RingBuffer(int limit){
        first = null;
        last = null;
        N = 0;
        max = limit;
    }

    public boolean isEmpty(){return N == 0;}

    public boolean isFull(){return N == max;}

    public void add(Item item){
        if(isFull()){throw new NoSuchElementException("Queue is full");}
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = first;

        if (N == 0){first = last;}
        else if(oldLast !=null){oldLast.next = last;}
        N++;
    }

    public Item remove(){
        if(isEmpty()){throw new NoSuchElementException("Queue is empty");}
        if(N ==1 ){
            Item item = first.item;
            first = null;
            last = null;
            N--;
            return item;
        }
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for (Item item: this){
            s.append(item);
            s.append(" ");
            if(item == last.item){break;}
        }
        return s.toString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterator<Item> iterator() {
        return new ListBasedIterator(first);
    }

    private class ListBasedIterator<Item> implements Iterator<Item>{
        private Node<Item> current;

        public ListBasedIterator(Node<Item> first){
            current = first;
        }

        public boolean hasNext(){return current != null;}
        public void remove(){throw new NoSuchElementException();}
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args){
        RingBuffer<Integer> ring = new RingBuffer<>(5);

        ring.add(5);
        ring.add(27);
        ring.add(98);
        ring.add(3);
        ring.add(13);
        System.out.println(ring);
        ring.remove();
        ring.remove();
        ring.remove();
        ring.remove();
        ring.remove();
        ring.remove();
        System.out.println(ring);
    }
}
