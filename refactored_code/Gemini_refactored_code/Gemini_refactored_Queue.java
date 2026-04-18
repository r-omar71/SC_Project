package com.thealgorithms.datastructures.queues;

/**
 * A circular-array implementation of a Queue.
 * Follows First-In-First-Out (FIFO) principles.
 * * @param <T> the type of elements held in this queue
 */
public final class Queue<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private final Object[] elements;
    private final int capacity;
    
    private int headIndex;
    private int tailIndex;
    private int currentSize;

    /**
     * Initializes a queue with the default capacity of 10.
     */
    public Queue() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Initializes a queue with a specified capacity.
     *
     * @param capacity The maximum number of elements the queue can hold.
     * @throws IllegalArgumentException if capacity is non-positive.
     */
    public Queue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Queue capacity must be positive. Provided: " + capacity);
        }
        this.capacity = capacity;
        this.elements = new Object[capacity];
        this.headIndex = 0;
        this.tailIndex = -1;
        this.currentSize = 0;
    }

    /**
     * Adds an element to the rear of the queue.
     *
     * @param element The item to add.
     * @return true if added, false if the queue is at capacity.
     */
    public boolean enqueue(T element) {
        if (isFull()) {
            return false;
        }

        // Circular increment of tail pointer
        tailIndex = (tailIndex + 1) % capacity;
        elements[tailIndex] = element;
        currentSize++;
        return true;
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return The front element.
     * @throws IllegalStateException if the queue is empty.
     */
    public T dequeue() {
        ensureNotEmpty("remove element");

        T removedElement = getElementAt(headIndex);
        elements[headIndex] = null; // Prevent memory leak / facilitate GC

        // Circular increment of head pointer
        headIndex = (headIndex + 1) % capacity;
        currentSize--;

        return removedElement;
    }

    /**
     * Returns the front element without removing it.
     */
    public T peekFront() {
        ensureNotEmpty("peek front");
        return getElementAt(headIndex);
    }

    /**
     * Returns the rear element without removing it.
     */
    public T peekRear() {
        ensureNotEmpty("peek rear");
        return getElementAt(tailIndex);
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == capacity;
    }

    public int size() {
        return currentSize;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder visualQueue = new StringBuilder("[");
        for (int i = 0; i < currentSize; i++) {
            int actualIndex = (headIndex + i) % capacity;
            visualQueue.append(elements[actualIndex]);
            
            if (i < currentSize - 1) {
                visualQueue.append(", ");
            }
        }
        return visualQueue.append("]").toString();
    }

    /**
     * Helper to handle unchecked casting in one place.
     */
    @SuppressWarnings("unchecked")
    private T getElementAt(int index) {
        return (T) elements[index];
    }

    /**
     * Centralized check for empty state to reduce code duplication.
     */
    private void ensureNotEmpty(String action) {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty, cannot " + action);
        }
    }
}
