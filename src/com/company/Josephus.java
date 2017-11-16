/*
Array-based Data Structure where N items are created in an array, and every Mth element
is removed until only one element remains.
 */
package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Josephus implements Iterable {
    private int[] arr;
    private int count;
    private int element;

    @SuppressWarnings("unchecked")
    public Josephus(int N, int M ){
        count = N;
        element = M;
        arr = new int[count];
    }

    public boolean isEmpty(){
        return count == 0;
    }

    @SuppressWarnings("unchecked")
    public void eliminate(){
        // method that eliminates every Mth element until there is only one left.
        // create new array that adds deleted items in the order they are deleted.
        if(isEmpty() || element > count) throw new NoSuchElementException("Underflow");
        for(int i = 0; i < count; i++){
            arr[i] = i+1;
        }
        System.out.println(this.toString());

        //array for output
        int[] deletedArray = new int[count];
        for(int j = 0; j < arr.length; j++){
            if ((j) % element == 0){
                deletedArray[j] = arr[j];
                arr[j] = 0;
                count--;
                System.out.print(deletedArray[j] + " ");
            }
        }
        System.out.println(this.toString());

    }
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(int item: arr){
            if (item != 0){
                s.append(item);
                s.append(" ");
            }
        }
        return s.toString();
    }

    @Override
    public Iterator iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator{
        private int i = 0;

        public boolean hasNext(){ return i < count;}
        public void remove(){throw new UnsupportedOperationException();}
        public Object next(){
            if (!hasNext()) throw new NoSuchElementException();
            return arr[i++];
        }
    }

    public static void main(String[] args){
        Josephus arr = new Josephus(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        arr.eliminate();
        System.out.println(arr);
    }
}
