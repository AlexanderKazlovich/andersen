package com.kazlovich;

public class CustomLinkedList<T>{
    public int size;
    private Node first;
    private Node last;

    public CustomLinkedList() {}

    public void add(T aElement) {
        Node<T> newNode = new Node<>(aElement);
        if (first == null) {
            newNode.setNext(null);
            newNode.setPrev(null);
            first = newNode;
            last = newNode;
        } else {
            last.setNext(newNode);
            newNode.setPrev(last);
            last = newNode;
        }
        size++;
    }
    public void addByIndex(T element, int index) {
        if (index == size) {
            add(element);
            return;
        }
        Node<T> newNode = new Node<>(element);
        if (index == 0) {
            newNode.setNext(first);
            first.setPrev(newNode);
            first = newNode;
        } else {
            Node<T> currentNode = first;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNext();
            }

            Node<T> prevNode = currentNode.getPrev();
            prevNode.setNext(newNode);
            currentNode.setPrev(newNode);

            newNode.setPrev(prevNode);
            newNode.setNext(currentNode);
        }
        size++;
    }

    public void clear(){
        first = last = null;
        size = 0;
    }

    public T get(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException("Illegal index");
        }
        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node.getElement();
    }

    public T remove(int index) {
        Node<T> nodeToRemove;
        if (index == 0) {
            nodeToRemove = last;
            Node<T> nextNode = first.getNext();
            nextNode.setPrev(null);
            first = nextNode;
        } else if (index == (size - 1)) {
            nodeToRemove = first;
            Node<T> prevNode = last.getPrev();
            prevNode.setNext(null);
            last = prevNode;
        } else {
            nodeToRemove = first;
            for (int i = 0; i < index; i++) {
                nodeToRemove = nodeToRemove.getNext();
            }
            Node<T> prevNode = nodeToRemove.getPrev();
            Node<T> nextNode = nodeToRemove.getNext();
            prevNode.setNext(nextNode);
            nextNode.setPrev(prevNode);
        }
        size--;
        return nodeToRemove.getElement();
    }

    public void reverse(){
        Node<T> currentNode = first;
        Node<T> currentNextNode = first.getNext();
        last = first;
        last.setPrev(first.getNext());
        last.setNext(null);

        while (currentNextNode != null) {
            Node<T> tempNode = currentNextNode;
            currentNextNode = currentNextNode.getNext();
            tempNode.setNext(currentNode);
            currentNode = tempNode;
        }
        first = currentNode;
        first.setPrev(null);
    }

    @Override
    public String toString() {
        return "CustomLinkedList{" +
                "size=" + size +
                ", first=" + first +
                ", last=" + last +
                '}';
    }

    private class Node<T> {
        private T element;
        private Node next;
        private Node prev;
        private int index;

        public Node() {}
        public Node(T element) {
            this.element = element;
            this.next = null;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    '}';
        }
    }
}
