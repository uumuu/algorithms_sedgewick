/*
Client that takes two stacks and implements them into one deque with constant-time implementation.
 */
package com.company;

import java.util.Stack;

public class DoubleStackDeque {

    public static void main(String[] args){
        Stack<Integer> stack1 = new Stack<>();
        for (int i = 1; i < 6; i++) {stack1.push(i);}

        Stack<Integer> stack2 = new Stack<>();
        for (int i = 6; i < 11; i++) {stack2.push(i);}

        int[]popArr = new int[stack1.size() + stack2.size()];

        int stack1Size = stack1.size();
        int stack2Size = stack2.size();

        for(int j = 0; j < stack2Size; j++){ popArr[j] = stack2.pop();}
        for(int k = stack2Size; k < stack1Size + stack2Size; k++){popArr[k] = stack1.pop();}

        Deque<Integer> deque = new Deque<>();
        for(int i = 0; i< popArr.length; i++){
            deque.pushLeft(popArr[i]);
        }
    }

    /*
    e.g two stacks = [1, 2, 3, 4, 5] and [6, 7, 8, 9, 10]
                    deque = [1 , 2 , 3 , 4 , 5 , 6, 7, 8, 9, 10 ]
     */
}
