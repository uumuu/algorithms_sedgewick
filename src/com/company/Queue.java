/*
List-based FIFO queue that allows for inserting at the end, removing from the beginning.
Also allows for removing last item in the queue, finding specific item with a key,
getting a specific node from the list, removing a specific item after a given node,
calculating a max value from the list.
 */
package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int n;

    private static class Node<Item>{
        private Item item;
        private Node<Item> next;
    }

    public Queue() {
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty(){
        return first == last;
    }

    public int size() {
        return n;
    }

    public Item peek(){
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    public void enqueue(Item item){
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        if (n==0) {first = last;}
        else if(oldLast != null) {oldLast.next =last;}
        n++;
    }

    public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if(isEmpty()) last = null;
        return item;
    }

    public Item removeLast(){
        if (isEmpty() || n <= 0) throw new NoSuchElementException("Queue underflow");
        Item oldLast = last.item;
        Node<Item> previous = first;
        if(first != null){
            Node<Item> current = first;

            while(current.next!=null){
                previous  = current;
                current = current.next;
            }
            previous.next.item = oldLast;
            previous.next = null;
        }
    return oldLast;
    }

    public void delete(int k){
        if (isEmpty() || n <= 0 || n<k) throw new NoSuchElementException("Queue underflow");
        Node<Item> current = first;

        for (int i = 1; i < k-1; i++){
            current = current.next;
            if(i == k-2){
                current.next = current.next.next;
                break;
            }
        }
    }

    public boolean find(String key){
        if (isEmpty() || n <= 0) throw new NoSuchElementException("Queue underflow");

        Node<Item> current = first;

        while(current.next != null){
            if (current.item.equals(key)){
                return true;
            }
            else{
                current = current.next;
                if (current.item.equals(key)){
                    return true;
                }
            }
        }
        return false;
    }

    public void removeAfter(Node<Item> node){
        // removes the node after the given node.

        if (isEmpty() || n <= 0) throw new NoSuchElementException("Queue underflow");
        Node<Item> current = first;

        if (first.item == node.item){
            first.next = first.next.next;
            return;
        }
        // if node is null, do nothing.
        if (node.item == null){
            System.out.println("Node is null.");
            return;
        }else{
            // traverse list
            while(current.next!=null){
                // if current node is given node, delete next node
                if(current.item ==  node.item){
                    current.next = current.next.next;
                    return;
                }
            }
        }
    }

    public Node<Item> getNode(){
        return first;
    }

    public void insertAfter(Node<Item> firstNode, Node<Item> secondNode){
        if (isEmpty() || n <= 0) throw new NoSuchElementException("Queue underflow");
        else if(firstNode == null | secondNode == null) {
            System.out.println("Node is null.");
            return;
        }else{
            secondNode.next = firstNode.next;
            firstNode.next = secondNode;
        }
    }

    public void remove(Queue<Item> list, String str){
        if (list.isEmpty() || list.n <= 0) throw new NoSuchElementException("Queue underflow");
        first.next = list.first.next;
        first = list.first;
        Node<Item> current = first;
        Node<Item> previous = first;
        if (first.item.equals(str)){
            first = first.next;
        }
        while(current.next !=null){
            previous = current;
            current= current.next;
            if(current.item.equals(str)){
                System.out.println(previous.next.item);
                System.out.println(previous.next.next.item);
                previous.next = previous.next.next;
            }
        }
    }

    public int max(Node<Integer> nodeHead){
        if (isEmpty() || n <= 0) return 0;

        Node<Item> current = first;

        int max = 0;
        if ((int) first.item > max){ max = (int) first.item;}

        while(current.next !=null){
            current = current.next;
            if ((int) current.item > max) {
                max = (int) current.item;
            }

        }
        return max;
    }

    public Node<Item> reverseList(Node<Item> firstNode){
        if(isEmpty()){ throw new NoSuchElementException("Queue underflow");}
        firstNode = first;
        Node<Item> reverse = first;



        while(first.next != null){
            Node<Item> current = first.next;
            reverse = first;
            first = current;
        }
        return reverse;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(" ");
        }
        return s.toString();
    }
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first){
            current = first;
        }

        public boolean hasNext() {return current != null;}
        public void remove() {throw new UnsupportedOperationException();}

        public Item next(){
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
