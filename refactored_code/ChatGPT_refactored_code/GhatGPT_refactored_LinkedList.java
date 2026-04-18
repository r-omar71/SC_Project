package com.thealgorithms.datastructures.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

/**
 * Singly Linked List implementation.
 */
public class SinglyLinkedList implements Iterable<Integer> {

    private SinglyLinkedListNode head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public SinglyLinkedList(SinglyLinkedListNode head, int size) {
        this.head = head;
        this.size = size;
    }

    /**
     * Detect cycle using Floyd’s Tortoise and Hare algorithm.
     */
    public boolean detectLoop() {
        SinglyLinkedListNode slow = head;
        SinglyLinkedListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns middle node (second middle for even-sized list).
     */
    public SinglyLinkedListNode middle() {
        if (head == null) {
            return null;
        }

        SinglyLinkedListNode slow = head;
        SinglyLinkedListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /**
     * Swap nodes by value (not by data swap).
     */
    public void swapNodes(int firstValue, int secondValue) {
        if (firstValue == secondValue) {
            return;
        }

        SinglyLinkedListNode prevA = null;
        SinglyLinkedListNode nodeA = head;

        while (nodeA != null && nodeA.value != firstValue) {
            prevA = nodeA;
            nodeA = nodeA.next;
        }

        SinglyLinkedListNode prevB = null;
        SinglyLinkedListNode nodeB = head;

        while (nodeB != null && nodeB.value != secondValue) {
            prevB = nodeB;
            nodeB = nodeB.next;
        }

        if (nodeA == null || nodeB == null) {
            return;
        }

        if (prevA != null) {
            prevA.next = nodeB;
        } else {
            head = nodeB;
        }

        if (prevB != null) {
            prevB.next = nodeA;
        } else {
            head = nodeA;
        }

        SinglyLinkedListNode tempNext = nodeA.next;
        nodeA.next = nodeB.next;
        nodeB.next = tempNext;
    }

    /**
     * Iterative reverse from given node.
     */
    public SinglyLinkedListNode reverseListIter(SinglyLinkedListNode startNode) {
        SinglyLinkedListNode prev = null;
        SinglyLinkedListNode current = startNode;

        while (current != null) {
            SinglyLinkedListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }

        return prev;
    }

    /**
     * Recursive reverse.
     */
    public SinglyLinkedListNode reverseListRec(SinglyLinkedListNode node) {
        if (node == null || node.next == null) {
            return node;
        }

        SinglyLinkedListNode newHead = reverseListRec(node.next);

        node.next.next = node;
        node.next = null;

        return newHead;
    }

    /**
     * Clears list.
     */
    public void clear() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public SinglyLinkedListNode getHead() {
        return head;
    }

    public void setHead(SinglyLinkedListNode head) {
        this.head = head;
    }

    public int count() {
        int count = 0;
        for (Integer ignored : this) {
            count++;
        }
        return count;
    }

    public boolean search(int key) {
        for (Integer value : this) {
            if (value == key) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("->");

        for (Integer value : this) {
            joiner.add(String.valueOf(value));
        }

        return joiner.toString();
    }

    /**
     * Removes duplicates in sorted list.
     */
    public void deleteDuplicates() {
        SinglyLinkedListNode prev = head;
        SinglyLinkedListNode current = head;

        while (current != null) {

            if (current.next != null && current.value == current.next.value) {

                while (current.next != null && current.value == current.next.value) {
                    current = current.next;
                }

                prev.next = current.next;
            }

            prev = prev.next;
            current = prev;
        }
    }

    public void print() {
        SinglyLinkedListNode current = head;

        while (current != null) {
            System.out.print(current.value);

            if (current.next != null) {
                System.out.print("->");
            }

            current = current.next;
        }

        System.out.println();
    }

    public void insertHead(int value) {
        insertNth(value, 0);
    }

    public void insert(int value) {
        insertNth(value, size);
    }

    public void insertNth(int value, int position) {
        checkBounds(position, 0, size);

        SinglyLinkedListNode newNode = new SinglyLinkedListNode(value);

        if (position == 0) {
            newNode.next = head;
            head = newNode;
            size++;
            return;
        }

        SinglyLinkedListNode current = head;

        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    public void deleteHead() {
        deleteNth(0);
    }

    public void delete() {
        deleteNth(size - 1);
    }

    public void deleteNth(int position) {
        checkBounds(position, 0, size - 1);

        if (position == 0) {
            head = head.next;
            size--;
            return;
        }

        SinglyLinkedListNode current = head;

        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }

        current.next = current.next.next;
        size--;
    }

    public int getNth(int index) {
        checkBounds(index, 0, size - 1);

        SinglyLinkedListNode current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.value;
    }

    public void checkBounds(int position, int low, int high) {
        if (position < low || position > high) {
            throw new IndexOutOfBoundsException(String.valueOf(position));
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new SinglyLinkedListIterator();
    }

    private class SinglyLinkedListIterator implements Iterator<Integer> {

        private SinglyLinkedListNode current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            int value = current.value;
            current = current.next;
            return value;
        }
    }
}
