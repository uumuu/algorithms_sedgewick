/*
Double-ended array-based queue. Used to add/remove from both ends of the queue.
Less efficient than linked-list implementation as you need to re-order
every element in the array every time you add something to it. Also results
in a lot of empty (null) array elements.

 */

package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayDeque<Item> implements Iterable<Item> {
    private Item[] q;
    private int n;
    private int first;
    private int last;

    @SuppressWarnings("unchecked")
    public ResizingArrayDeque(){
        q = (Item[]) new Object[2];
        n = 0;
        first = 0;
        last = 0;
    }

    public boolean isEmpty(){
        return n==0;
    }

    public int size() {
        return n;
    }

    //DEBUGGING METHOD DELETE AFTER
    public int returnLast(){
        return last;
    }
    @SuppressWarnings("unchecked")
    public void resize(int capacity){
        //resizes array to capacity size by copying queue's elements to a temporary array
        //after modulus'ing it so it wraps around the length of the array.
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i<n;i++){
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last = n;
    }
    @SuppressWarnings("unchecked")
    public void pushLeft(Item item){
        //pushes to the beginning of the array by shifting every element forward by one
        //then inserting the new element at first
        if(n == 0){
            q[first] = item;
            n++;
        }
        else {
            if (n == q.length) {
                resize(2 * q.length);
            }
            Item[] temp = (Item[]) new Object[n+1];
            for(int i = 0; i < n; i++){
                    temp[i+1] = q[i];
            }
            q = temp;
            temp[first] = item;
            n++;
        }
    }

    public void pushRight(Item item){
        //adds to the end of the list by advancing last element.
        if (n == q.length){resize(2*q.length);}
        q[last++] = item;
        if(last == q.length){
            last = 0;}
        n++;
    }

    public Item popLeft(){
        //pops from the beginning of the list, does this by setting first to null and returning its previous
        //contents, then advances first past the null element
        if (isEmpty()) throw new NoSuchElementException("Underflow");
        Item item = q[first];
        q[first] = null;
        n--;
        first++;
        if(first == q.length){ first = 0;}
        if(n > 0 && n == q.length /4){resize(q.length / 2);}
        return item;
    }

    public Item popRight(){
        //pops from the end of the list, does this by setting last element to null and decrementing last
        //to the element behind it.
        if (isEmpty()) throw new NoSuchElementException("Underflow");
        Item item = q[last];
        //if q[last] is null, it's not really the last element so we need to return the previous
        //this happens as a result of the resizing function of the structure, where last can constantly
        //be changing in position, occasionally it is set to zero.
        if (item == null){
            item = q[last-=1];
        }
        q[last] = null;
        n--;
        last--;
        if(n>0 && n == q.length /4){resize(q.length/2);}
        return item;
    }

    public String toString(){
        //prints out every non-null item in deque.
        StringBuilder s = new StringBuilder();
        for(Item item : q){
            if(item !=null) {
                s.append(item);
                s.append(" ");
            }
        }
        return s.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayQueueIterator();
    }

    private class ArrayQueueIterator implements Iterator<Item>{
        private int i = 0;
        public boolean hasNext(){return i < n;}
        public void remove(){throw new UnsupportedOperationException();}

        public Item next(){
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[(i + first) % q.length];/*
Double-ended array-based queue. Used to add/remove from both ends of the queue.
Less efficient than linked-list implementation as you need to re-order
every element in the array every time you add something to it. Also results
in a lot of empty (null) array elements.

 */

package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

            public class ResizingArrayDeque<Item> implements Iterable<Item> {
                private Item[] q;
                private int n;
                private int first;
                private int last;

                @SuppressWarnings("unchecked")
                public ResizingArrayDeque(){
                    q = (Item[]) new Object[2];
                    n = 0;
                    first = 0;
                    last = 0;
                }

                public boolean isEmpty(){
                    return n==0;
                }

                public int size() {
                    return n;
                }

                //DEBUGGING METHOD DELETE AFTER
                public int returnLast(){
                    return last;
                }
                @SuppressWarnings("unchecked")
                public void resize(int capacity){
                    //resizes array to capacity size by copying queue's elements to a temporary array
                    //after modulus'ing it so it wraps around the length of the array.
                    assert capacity >= n;
                    Item[] temp = (Item[]) new Object[capacity];
                    for (int i = 0; i<n;i++){
                        temp[i] = q[(first + i) % q.length];
                    }
                    q = temp;
                    first = 0;
                    last = n;
                }
                @SuppressWarnings("unchecked")
                public void pushLeft(Item item){
                    //pushes to the beginning of the array by shifting every element forward by one
                    //then inserting the new element at first
                    if(n == 0){
                        q[first] = item;
                        n++;
                    }
                    else {
                        if (n == q.length) {
                            resize(2 * q.length);
                        }
                        Item[] temp = (Item[]) new Object[n+1];
                        for(int i = 0; i < n; i++){
                            temp[i+1] = q[i];
                        }
                        q = temp;
                        temp[first] = item;
                        n++;
                    }
                }

                public void pushRight(Item item){
                    //adds to the end of the list by advancing last element.
                    if (n == q.length){resize(2*q.length);}
                    q[last++] = item;
                    if(last == q.length){
                        last = 0;}
                    n++;
                }

                public Item popLeft(){
                    //pops from the beginning of the list, does this by setting first to null and returning its previous
                    //contents, then advances first past the null element
                    if (isEmpty()) throw new NoSuchElementException("Underflow");
                    Item item = q[first];
                    q[first] = null;
                    n--;
                    first++;
                    if(first == q.length){ first = 0;}
                    if(n > 0 && n == q.length /4){resize(q.length / 2);}
                    return item;
                }

                public Item popRight(){
                    //pops from the end of the list, does this by setting last element to null and decrementing last
                    //to the element behind it.
                    if (isEmpty()) throw new NoSuchElementException("Underflow");
                    Item item = q[last];
                    //if q[last] is null, it's not really the last element so we need to return the previous
                    //this happens as a result of the resizing function of the structure, where last can constantly
                    //be changing in position, occasionally it is set to zero.
                    if (item == null){
                        item = q[last-=1];
                    }
                    q[last] = null;
                    n--;
                    last--;
                    if(n>0 && n == q.length /4){resize(q.length/2);}
                    return item;
                }

                public String toString(){
                    //prints out every non-null item in deque.
                    StringBuilder s = new StringBuilder();
                    for(Item item : q){
                        if(item !=null) {
                            s.append(item);
                            s.append(" ");
                        }
                    }
                    return s.toString();
                }

                @Override
                public Iterator<Item> iterator() {
                    return new ArrayQueueIterator();
                }

                private class ArrayQueueIterator implements Iterator<Item>{
                    private int i = 0;
                    public boolean hasNext(){return i < n;}
                    public void remove(){throw new UnsupportedOperationException();}

                    public Item next(){
                        if (!hasNext()) throw new NoSuchElementException();
                        Item item = q[(i + first) % q.length];
                        return item;
                    }
                }
            }

            return item;
        }
    }
}
