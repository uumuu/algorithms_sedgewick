package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<Item> implements Iterable<Item> {

    public Node<Item> first;
    public Node<Item> last;
    public int n;

    private static class Node<Item>{
        private Item item;
        private Node<Item> prev;
        private Node<Item> next;
    }

    public DoublyLinkedList(){
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty(){return first == last;}


    public int size(){return n;}

    public void prepend(Item item){
        Node<Item> x = new Node<>();
        x.item = item;
        if (isEmpty()){first = x; last = x;}
        else{x.prev = last; last.next = x; last = x;}
        n++;
    }
    public void insertFirst(Node node, Item item){
        if (node == first){prepend(item);}

        else{
            // Node is the node we are adding a NEW NODE before.
            // e.g x -> node
            Node previous = new Node();
            previous = node.prev;
            Node x =  new Node();
            x.item = item;
            x.prev = previous;
            x.next = node;
            previous.next = x;
            node.prev = x;
            n++;
        }
    }

    public void insertBeginning(Item item){
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        // if list is empty, initialise everything to be the same, bar first and last
        if(n==0){
            last = first;
            first.prev = null;
            last.next = null;
        // else if list is populated, the only thing we need to assign is last.prev
        // if list only has three or less items, last.prev is first.next
        // we do this by traversing to the node before last and setting it to be last.prev.
        }else if(oldFirst != null ){
            if (n == 1){
                last.prev = first;
            }
            if (n == 2){
                last.prev = first.next;
            }else{
                for (int i = 0  ; i <n-2; i++) {
                    last.prev = oldFirst.next;
                    oldFirst = oldFirst.next;
                }
            }
        }
        n++;
    }
    // DEBUGGING METHODS
    public Node returnFirst(){
        return first;
    }
    public Item returnFirstNext(){
        return first.next.item;
    }
    public Item returnLast(){
        return last.item;
    }
    public Item returnLastNext(){
        return last.next.item;
    }

    public void insertEnd( Item item){
        //NEED TO HANDLE .PREVIOUS ON THIS METHOD, LOOP THROUGH???????????????
        //THINK WE NEED TO HANDLE THIS IN THE ENQUEUE METHOD SO THAT WHATEVER WE ENQUEUE ALWAYS HAS A PREVIOUS
        //NEED TO SET LAST.PREVIOUS IN ENQUEUE METHODS
        Node<Item > oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        if (n==0){
            first = last;
            first.prev = null;
        }
        else if(oldLast!= null){oldLast.next =last; }
    }

    public Item removeBeginning(){
        //removes first node by first = first.next
        if(isEmpty()) throw new NoSuchElementException("List empty");
        Item item = first.item;
        first.prev.next = first.next;
        first.next.prev = first.prev;
        first = first.prev;
        n--;
        return item;
    }

    public  Item removeEnd(){
        //last.previous = last.next
        if(isEmpty()) throw new NoSuchElementException("List empty");
        Item item = last.item;
        last.prev.next = last.next;
        last.next.prev = last.prev;
        last = last.prev;
        n--;
        return item;
    }

    public void insertBeforeNode(Node<Item> node, Item item){
        //take in a node, create a new node that sandwiches in between
        // the node and node.previous.
        //previousNode -> newNode -> node
        Node<Item> previous = node.prev;
        Node<Item> x = new Node<>();
        x.item = item;
        x.prev = previous;
        x.next = node;
        previous.next = x;
        node.prev = x;
        n++;
    }

    public void insertAfterNode(Node<Item> node, Item item){
        //take in a node, create a new node that sandwiches between node and node.next
        //node -> newNode -> nextNode
        Node<Item> next = node.next;
        Node<Item> x = new Node<>();
        x.item = item;
        x.prev = node;
        x.next = node.next;
        next.prev = x;
        node.next = x;
        n++;
    }

    public Item removeNode(Node<Item> node){
        //node.previous.next = node.next;
        // node.previous = node;
        if(isEmpty()) throw new NoSuchElementException("List empty");
        Item item = node.item;
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node = node.prev;
        n--;
        return item;
    }


    public String toString(){
        StringBuilder s = new StringBuilder();
        for (Item item : this){
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

        public boolean hasNext(){return current != null;}
        public void remove(){throw new UnsupportedOperationException();}
        public Item next(){
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args){
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        Node<Integer> first = list.returnFirst();
        System.out.println(list);
        for(int i = 1; i < 26; i++){
            list.insertFirst(first, i);
            System.out.println(list);
        }
        System.out.println(list);
    }


}
