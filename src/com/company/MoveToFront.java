/*
	Data Structure that reads in a sequence of characters from input, stores them in a linked list
No Duplicates.
When you read in a previously unseen character it will insert it to the beginning of the list
When a duplicate is found, delete the original that is stored in the list and reinsert to the front
of the list.
 */
package com.company;

import java.util.Iterator;
import java.util.Scanner;

public class MoveToFront<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int n;
    private boolean CHAR_IN_LIST;

    private static class Node<Item>{
        Item item;
        Node<Item> next;
    }

    public MoveToFront(){
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty(){return n == 0;}

    public int size(){return n;}

    public void checkChar(Item item){
        //inputs character and checks if it is already in the list or not
        //if it is, delete it and reinsert
        //else, insert into the list.
        if(isEmpty()){
            insert(item);
        }else {
            Node<Item> previousNode = first;
            Node<Item> currentNode = first;
            Node<Item> deleteNode = null;
            Node<Item> deleteNodePrevious = null;

            CHAR_IN_LIST = false;
            if (first.item == item) {
                CHAR_IN_LIST = true;
            } else {
                while (currentNode.next != null) {
                    previousNode = currentNode;
                    if (currentNode.item.equals(item)) {
                        CHAR_IN_LIST = true;
                        deleteNode = currentNode.next;
                        deleteNodePrevious = previousNode;
                        break;
                    }
                    else{
                        currentNode = currentNode.next;
                        if(currentNode.item.equals(item)){
                            CHAR_IN_LIST = true;
                            deleteNode = currentNode;
                            deleteNodePrevious = previousNode;
                            break;
                        }

                    }
                }
            }
            if (!CHAR_IN_LIST && !first.item.equals(item)) {
                insert(item);
            } else if(CHAR_IN_LIST && !first.item.equals(item)) {
                remove(deleteNodePrevious, deleteNode);
                insert(item);
            }
        }
    }

    private void insert(Item item){
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        if(isEmpty()){last = first;}
        n++;
    }

    private Item remove(Node<Item> previousNode, Node<Item> deleteNode){
        //remove specific item from the list, sets previous node to point to node after
        Item item = deleteNode.item;
        previousNode.next = deleteNode.next;
        n--;
        return item;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();

        for(Item item: this){
            s.append(item);
            s.append(" ");
        }
        return s.toString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterator<Item> iterator() {
        return new ArrayIterator(first);
    }

    private class ArrayIterator<Item> implements Iterator<Item>{
        private Node<Item> current;

        public ArrayIterator(Node<Item> first){
            current = first;
        }

        public boolean hasNext(){return current != null;}
        public void remove(){throw new UnsupportedOperationException();}
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args){
        MoveToFront<Character> list = new MoveToFront<>();
        System.out.println("Enter some characters, press '/' when finished.");
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        input.close();
        for (int i = 0; i<str.length(); i++){
            list.checkChar(str.charAt(i));
        }
           System.out.println(list);
    }
}