package com.company;

public class List<T> {
    private Node head;
    private Node tail;

    public void add(T data) {
        Node<T> newNode = new Node<>();
        newNode.inform = data;
        if (head == null) {
            head = newNode;
            tail = head;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void add(int index, T data) {
        Node newNode = new Node();
        newNode.inform = data;
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else if (index == 0){
            newNode.next = head;
            head = newNode;
        }
        else if (index >= size()) {
            tail.next = newNode;
            tail = newNode;
        } else if (index < size()) {
            var current = head;
            while (current != tail) {
                if (current.next.index == index) {
                    var temp = current;
                    var temp2 = current.next;
                    temp.next = newNode;
                    newNode.next = temp2;
                }
                current = current.next;
            }
        }
        indexOfList();
    }


    private void indexOfList() {
        int currentIndex = 0;
        var current = head;
        while (current != null) {
            current.index = currentIndex;
            current = current.next;
            currentIndex++;
        }
    }

    public int size() {
        int size = 0;
        var current = head;
        if (current == null)
            return size;
        else {
            while (current != null) {
                size++;
                current = current.next;
            }
        }
        return size;
    }

    public void remove(int index) {
        if (head == null)
            return;
        if (head == tail) {
            head = tail = null;
        } else if (head.index == index) {
            head = head.next;
        } else {
            var current = head;
            while (current != null) {
                if (current.next.index == index) {
                    if (tail == current.next) {
                        tail = current;
                    }
                    current.next = current.next.next;
                    return;
                }
                current = current.next;
            }
        }
        indexOfList();
    }

    public T get(int index) {
        indexOfList();
        T student;
        var current = head;
        while (current != null) {
            if (current.index == index) {
                student = (T) current.inform;
                return student;
            }
            current = current.next;
        }
        return null;
    }

    public void addAll(List<T> list) {
        if (head == null) {
            head = list.head;
            tail = head;
            var current = list.head.next;
            while (current != null) {
                tail.next = current;
                tail = current;
                current = current.next;
            }
        } else {
            var current = list.head;
            if (current == null)
                return;
            while (current != null) {
                tail.next = current;
                tail = current;
                current = current.next;
            }
        }
        indexOfList();
    }
}
