package com.thealgorithms.datastructures.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

/**
 * A standard implementation of a Singly Linked List.
 * Includes common algorithms like Floyd's Cycle Detection and multiple reversal strategies.
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

    // --- Core Queries ---

    /**
     * Detects if the list contains a cycle using Floyd's Tortoise and Hare algorithm.
     */
    public boolean hasCycle() {
        SinglyLinkedListNode slow = head;
        SinglyLinkedListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the middle node. For even lengths, returns the lower middle (size/2).
     */
    public SinglyLinkedListNode findMiddle() {
        if (head == null) return null;

        SinglyLinkedListNode slow = head;
        SinglyLinkedListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // --- Transformation Methods ---

    /**
     * Reverses the list starting from the given node using an iterative approach.
     */
    public SinglyLinkedListNode reverseIteratively(SinglyLinkedListNode node) {
        SinglyLinkedListNode previous = null;
        SinglyLinkedListNode current = node;

        while (current != null) {
            SinglyLinkedListNode nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }
        return previous;
    }

    /**
     * Reverses the list starting from the given node using a recursive approach.
     */
    public SinglyLinkedListNode reverseRecursively(SinglyLinkedListNode node) {
        if (node == null || node.next == null) {
            return node;
        }

        SinglyLinkedListNode newHead = reverseRecursively(node.next);
        node.next.next = node;
        node.next = null; // Original head becomes the new tail

        return newHead;
    }

    /**
     * Swaps the positions of two nodes based on their values.
     */
    public void swapNodes(int valueA, int valueB) {
        if (valueA == valueB) return;

        // Locate Node A and its predecessor
        SinglyLinkedListNode prevA = null, currA = head;
        while (currA != null && currA.value != valueA) {
            prevA = currA;
            currA = currA.next;
        }

        // Locate Node B and its predecessor
        SinglyLinkedListNode prevB = null, currB = head;
        while (currB != null && currB.value != valueB) {
            prevB = currB;
            currB = currB.next;
        }

        if (currA == null || currB == null) return;

        // Update predecessors
        if (prevA != null) prevA.next = currB; else head = currB;
        if (prevB != null) prevB.next = currA; else head = currA;

        // Swap next pointers
        SinglyLinkedListNode tempNext = currA.next;
        currA.next = currB.next;
        currB.next = tempNext;
    }

    public void deleteDuplicates() {
        SinglyLinkedListNode current = head;
        while (current != null && current.next != null) {
            if (current.value == current.next.value) {
                current.next = current.next.next;
                size--;
            } else {
                current = current.next;
            }
        }
    }

    // --- Insertion & Deletion ---

    public void insertHead(int data) {
        insertAt(0, data);
    }

    public void insertTail(int data) {
        insertAt(size, data);
    }

    public void insertAt(int index, int data) {
        validatePosition(index, 0, size);
        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);

        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            SinglyLinkedListNode prev = getNodeAt(index - 1);
            newNode.next = prev.next;
            prev.next = newNode;
        }
        size++;
    }

    public void deleteHead() {
        deleteAt(0);
    }

    public void deleteTail() {
        deleteAt(size - 1);
    }

    public void deleteAt(int index) {
        validatePosition(index, 0, size - 1);

        if (index == 0) {
            head = head.next;
        } else {
            SinglyLinkedListNode prev = getNodeAt(index - 1);
            prev.next = prev.next.next;
        }
        size--;
    }

    // --- Helper Methods ---

    private SinglyLinkedListNode getNodeAt(int index) {
        validatePosition(index, 0, size - 1);
        SinglyLinkedListNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private void validatePosition(int index, int low, int high) {
        if (index < low || index > high) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

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

    @Override
    public String toString() {
        if (isEmpty()) return "";
        StringJoiner joiner = new StringJoiner("->");
        for (Integer value : this) {
            joiner.add(String.valueOf(value));
        }
        return joiner.toString();
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            private SinglyLinkedListNode current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Integer next() {
                if (!hasNext()) throw new NoSuchElementException();
                int value = current.value;
                current = current.next;
                return value;
            }
        };
    }
}
