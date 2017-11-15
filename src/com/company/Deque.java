/*
List-based double-ended queue structure.
Pushes and pops to/from both front and beginning of the list.
 */

package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int n;

    private static class Node<Item>{
        private Item item;
        private Node<Item> next;
    }

    public Deque(){
        first= null;
        last = null;
        n = 0;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }

    public void pushLeft(Item item){
        //pushes item to the beginning of the list
        if (isEmpty()){
            Node<Item> oldFirst = first;
            first = new Node<>();
            first.item = item;
            first.next = oldFirst;
            last = first;
            n++;
        }else{
            Node<Item> oldFirst = first;
            first = new Node<>();
            first.item = item;
            first.next = oldFirst;
            n++;
        }
    }

    public void pushRight(Item item){
        // pushes an item to the end of the list
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;

        if (n == 0) first = last;
        else if(oldLast!=null) oldLast.next = last;
    }

    public Item popLeft(){
        // removes an item from the beginning of the list
        if(isEmpty()) throw new NoSuchElementException("Underflow");

        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public Item popRight(){
        // removes an item from the end of the list
        if(isEmpty()) throw new NoSuchElementException("Underflow");

        Item oldLast = last.item;
        Node<Item> previous = first;
        if (first != null) {
            Node<Item> current = first;
            while (current.next != null) {
                previous = current;
                current = current.next;
            }
            last = previous;
            last.next = null;
        }
        return oldLast;
    }


    public String toString(){
        StringBuilder s = new StringBuilder();

        for(Item item : this){
            s.append(item);
            s.append(" ");
        }
        return s.toString();
    }

    public Iterator<Item> iterator() {return new ListIterator<Item>(first);}

    private class ListIterator<Item> implements Iterator<Item>{
        private Node<Item> current;

        public ListIterator(Node<Item> first){
            current = first;
        }
        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {throw new NoSuchElementException();}
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    public static void main(String[] args){
        Deque<Integer> deque = new Deque<>();

        for (int i = 1; i < 11; i++){
            deque.pushLeft(i);
        }
        System.out.println(deque);

        for (int j = 1; j < 6; j++)
            deque.popLeft();
        System.out.println(deque);

        for (int k = 1; k < 6; k++)
            deque.pushRight(k);
        System.out.println(deque);

        for (int l = 1; l < 4; l++)
            deque.popRight();
        System.out.println(deque);

        deque.popLeft();
        System.out.println(deque);


    }

}
