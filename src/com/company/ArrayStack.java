/*
Array-based Stack FILO data structure.
Also includes copy constructor that can be used to copy a stack and create a completely independent object
leaving the original and copy free from any connections.
 */
package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<Item> implements Iterable<Item> {
    private Item[] stack;
    private int n;

    @SuppressWarnings("unchecked")
    public ArrayStack(){
        stack = (Item[]) new Object[2];
    }
    @SuppressWarnings("unchecked")
    public ArrayStack(ArrayStack<Item> original){
        //copy constructor used to copy the contents of one ArrayStack object to another
        //results in two completely independent and seperate stacks that can be modified
        //independently
        this.stack = (Item[]) new Object[2];
        if (original.isEmpty()){throw new NoSuchElementException("Original stack is empty, nothing to copy.");}
        for(int i = 0; i < original.n; i++){
            this.push(original.stack[i]);
        }
    }

    public boolean isEmpty(){ return n == 0;}

    public int size(){return n;}

    @SuppressWarnings("unchecked")
    public void resize(int capacity){
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        for(int i=0; i <n; i++){
            temp[i] = stack[i];
        }
        stack = temp;
    }

    public void push(Item item){
        if(n == stack.length){resize(stack.length * 2);}
        stack[n++] = item;
    }

    public Item pop(){
        if(isEmpty()) throw new NoSuchElementException("Underflow");
        Item item = stack[n-1];
        stack[n-1] = null;
        n--;
        if(n >0 && n == stack.length/4){resize(stack.length/2);}
        return item;
    }

    public Item peek(){
        if(isEmpty()) throw new NoSuchElementException("Underflow");
        return stack[n-1];
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(Item item: stack){
            if(item !=null) {
                s.append(item);
                s.append(" ");
            }
        }
        return s.toString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterator<Item> iterator() {
        return new reverseArrayIterator();
    }

    @SuppressWarnings("unchecked")
    private class reverseArrayIterator<Item> implements Iterator<Item>{
        private int i;

        public reverseArrayIterator(){
            i = n-1;
        }
        public boolean hasNext(){return i < n;}
        public void remove(){throw new UnsupportedOperationException();}
        public Item next(){
            if(!hasNext()) throw new NoSuchElementException();
            return (Item) stack[i--];
        }
    }
}

