/*
List-based queue that removes and returns Kth least-least recently added item.
 */

package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class GeneralisedQueueList<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int N;

    private static class Node<Item>{
        private Item item;
        private Node<Item> next;
    }

    public GeneralisedQueueList(){
        first = null;
        last = null;
        N = 0;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public void insert(Item item){
        //inserts item at the beginning of the queue by creating a new first node with that item
        //that points to the old first node
        if (isEmpty()) {
            Node<Item> oldFirst = first;
            first = new Node<>();
            first.item = item;
            first.next = oldFirst;
            last = first;
            N++;
        }else{
            Node<Item> oldFirst = first;
            first = new Node<>();
            first.item = item;
            first.next = oldFirst;
            N++;
        }
    }

    public Item remove(int k){
        // removes kth least recently added item
        // if k is equal to the size of the list, it will be removing the last item
        // otherwise it will traverse through list (N-k)-1 times so that we can remove
        // kth from list by setting previous node to point to one after kth
        if (isEmpty() || N < k){throw new NoSuchElementException("Item does not exist");}
        Item item = null;
        Node<Item> previous = first;
        Node<Item> current = first;
        int count = 0;
        if(k == N){
            item = first.item;
            first = first.next;
        }
        else {
            while (current.next != null) {
                previous = current;
                current = current.next;
                if (count == (N - k) - 1) {
                    item = current.item;
                    System.out.println("item: " + item);
                    if (k == N) {
                        previous.next = null;
                    } else {
                        previous.next = current.next;
                    }
                    break;
                }
                count++;
            }
        }
        N--;
        return item;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for (Item item: this){
            s.append(item);
            s.append(" ");
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
        public void remove(){throw new UnsupportedOperationException();}
        public Item next(){
            if(!hasNext()){throw new NoSuchElementException();}
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
