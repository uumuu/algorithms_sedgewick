package com.company;

/*
Array-based data structure used for text edit buffer
Allows user to move cursor, insert and delete characters at the current cursor position.
Stack-based.
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TextEditBuffer<Item> implements Iterable<Item> {

    private Item[] stack;
    private int n;
    private int cursorPos;

    @SuppressWarnings("unchecked")
    public TextEditBuffer() {
        stack = (Item[]) new Object[2];
        n = 0;
        cursorPos = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    @SuppressWarnings("unchecked")
    public void resize(int capacity) {
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = stack[i];
        }
        stack = temp;
    }

    @SuppressWarnings("unchecked")
    public void insert(Item item) {
        //insert item into stack.
        if(n == stack.length){resize(stack.length * 2);}

        if (stack[cursorPos] != null){
            //if current position we want to insert to is not empty.
            //we need to take the current position and everything after it and shift by one element.
            //to do this we will use another array to copy everything into and then we
            //will put everything back into the original stack, +1 position from original.
            //We will do this after inserting the new item in the correct position.
            Item[] temp = (Item[]) new Object[n+1];
            for(int i = cursorPos; i< n; i++){
                temp[i] = stack[i];
            }
            //insert item
            stack[cursorPos] = item;
            n++;

            //now re-insert everything +1 places
            for(int j = cursorPos+1; j < n; j++){
                stack[j] = temp[j-1];
            }
            cursorPos++;
        }
        else{
            stack[cursorPos++] = item;
            n++;
        }
    }

    public Item delete() {
        if (isEmpty()) {
            throw new NoSuchElementException("Underflow");
        }
        Item c = stack[cursorPos - 1];
        stack[cursorPos-1] = null;
        cursorPos--;
        return c;
    }

    public void left(int k) {
        try {
            cursorPos -= k;

        } catch (IndexOutOfBoundsException e) {
            System.out.println("No such position, cannot move cursor.");
        }
    }

    public void right(int k) {
        try {
            cursorPos += k;

        } catch (IndexOutOfBoundsException e) {
            System.out.println("No such position, cannot move cursor.");
        }
    }

    public int size() {
        return n;
    }


    public String toString(){
        StringBuilder s = new StringBuilder();
        for(Item item: stack){
            if(item != null) {
            s.append(item);
            s.append(" ");
            }
        }
        return s.toString();
    }

    @SuppressWarnings("unchecked")
    public Iterator<Item> iterator() {
        return new reverseArrayIterator();
    }

    @SuppressWarnings("unchecked")
    private class reverseArrayIterator<Item> implements Iterator<Item> {
        private int i;

        public reverseArrayIterator() {
            i = n - 1;
        }

        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (Item) stack[i--];
        }
    }
}
