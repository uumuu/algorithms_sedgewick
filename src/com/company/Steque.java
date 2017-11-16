/*
List-based Stack/Queue data structure.
Pushes and pulls like a normal stack, only it is implemented in list form as opposed to array form.
Also includes standard enqueue function of a queue.
 */
package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Steque<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int n;

    private static class Node<Item>{
        private Item item;
        private Node<Item> next;
    }

    public Steque(){
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

    public void push(Item item){
        //if array not empty we need to insert new node before old first and have it pointing to oldfirst
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

    public Item pop(){
        if(isEmpty()) throw new NoSuchElementException("Underflow");

        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public void enqueue(Item item){
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        if (n == 0) first = last;
        else if(oldLast!=null) oldLast.next = last;
        n++;
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
}
