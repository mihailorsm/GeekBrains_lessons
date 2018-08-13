package com.company;

public class Stack {
    private int maxSize;
    private char[] stack;
    private int top;

    public static void main(String[] args) {
        String str = "qwerty";
        Stack stack = new Stack(str.length());
        for (char ch : str.toCharArray())
            stack.push(ch);
        while (!stack.isEmpty())
            System.out.print((char)stack.pop());


    }
    public Stack(int size){
        this.maxSize = size;
        this.stack = new char[this.maxSize];
        this.top = -1;
    }

    public boolean isEmpty(){
        return (this.top == -1);
    }
    public boolean isFull(){
        return (this.top == this.maxSize-1);
    }

    public void push(char i){
        this.stack[++this.top] = i;
    }

    public int pop(){
        return this.stack[this.top--];
    }

    public int peek(){
        return this.stack[this.top];
    }

}
