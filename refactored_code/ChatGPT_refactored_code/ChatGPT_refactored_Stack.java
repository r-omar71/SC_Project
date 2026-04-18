package com.thealgorithms.datastructures.stacks;

/**
 * A generic interface representing a stack (LIFO - Last In, First Out).
 *
 * @param <T> the type of elements stored in the stack
 */
public interface Stack<T> {

    /**
     * Pushes an element onto the top of the stack.
     *
     * @param element the element to be added
     */
    void push(T element);

    /**
     * Removes and returns the top element of the stack.
     *
     * @return the removed top element
     * @throws IllegalStateException if the stack is empty
     */
    T pop();

    /**
     * Returns the top element without removing it.
     *
     * @return the top element
     * @throws IllegalStateException if the stack is empty
     */
    T peek();

    /**
     * Checks whether the stack is empty.
     *
     * @return true if the stack contains no elements, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the stack.
     *
     * @return the current size of the stack
     */
    int size();

    /**
     * Removes all elements from the stack.
     */
    void makeEmpty();
}
