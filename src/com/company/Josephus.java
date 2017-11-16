/*
Array-based Data Structure where N items are created in an array, and every Mth element
is removed until only one element remains.
It does this by keeping an element count over how many elements we have looped over.
It will delete the element in the array if the elementCount is a multiple of M.
When array length is less than M, it will sequentially loop over each item in the array as normal without
deleting, until element count reaches a multiple of , when it will then delete the element.
Therefore, the loop always results in one integer left over at the end.
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
        if (isEmpty()) throw new NoSuchElementException("Underflow");

        //create our initial array.
        for(int i = 0; i < count; i++){
            arr[i] = i+1;
        }
        //tracker for how many elements we have looped over.
        int elementCount = 1;

        while(count != 1){
            for(int j = 0; j < arr.length; j++){
                //if elementCount is a multiple of M, delete the element in the array
                //we are currently looping over.
                if((elementCount) % element ==0){
                    arr[j] = 0;
                    count--;
                }
                elementCount++;
            }

            //updates the array, removing eliminates integers so it is ready
            //for the next loop over.
            int insertPos = 0;
            int[] temp = new int[count];
            for(int k = 0; k < arr.length; k++){
                if(arr[k] != 0){
                    temp[insertPos++] = arr[k];
                }
            }
            arr = temp;
        }
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
}