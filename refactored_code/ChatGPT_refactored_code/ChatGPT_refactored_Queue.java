package com.thealgorithms.datastructures.queues;

/**
 * Array-based circular queue implementation (FIFO).
 * Not thread-safe.
 */
public final class Queue<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private final int capacity;
    private final Object[] elements;

    private int front;
    private int rear;
    private int size;

    public Queue() {
        this(DEFAULT_CAPACITY);
    }

    public Queue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Queue capacity must be greater than 0");
        }

        this.capacity = capacity;
        this.elements = new Object[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public boolean insert(T element) {
        if (isFull()) {
            return false;
        }

        rear = (rear + 1) % capacity;
        elements[rear] = element;
        size++;

        return true;
    }

    @SuppressWarnings("unchecked")
    public T remove() {
        ensureNotEmpty("remove");

        T value = (T) elements[front];
        elements[front] = null;

        front = (front + 1) % capacity;
        size--;

        return value;
    }

    @SuppressWarnings("unchecked")
    public T peekFront() {
        ensureNotEmpty("peekFront");
        return (T) elements[front];
    }

    @SuppressWarnings("unchecked")
    public T peekRear() {
        ensureNotEmpty("peekRear");
        return (T) elements[rear];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            builder.append(elements[index]);

            if (i < size - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");

        return builder.toString();
    }

    private void ensureNotEmpty(String operation) {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty, cannot " + operation);
        }
    }
}
