package com.thealgorithms.datastructures.stacks;

/**
 * A generic contract for a Last-In-First-Out (LIFO) Stack data structure.
 *
 * @param <E> the type of elements held in this stack
 */
public interface Stack<E> {

    /**
     * Pushes an item onto the top of this stack.
     *
     * @param element the item to be pushed onto this stack.
     */
    void push(E element);

    /**
     * Removes the object at the top of this stack and returns that object as the value of this function.
     *
     * @return The object at the top of this stack.
     * @throws java.util.EmptyStackException if this stack is empty.
     * (Note: Implementation may also throw IllegalStateException based on specific requirements).
     */
    E pop();

    /**
     * Looks at the object at the top of this stack without removing it from the stack.
     *
     * @return the object at the top of this stack.
     * @throws java.util.EmptyStackException if this stack is empty.
     */
    E peek();

    /**
     * Tests if this stack is empty.
     *
     * @return {@code true} if and only if this stack contains no items; {@code false} otherwise.
     */
    boolean isEmpty();

    /**
     * Returns the number of components in this stack.
     *
     * @return the number of components in this stack.
     */
    int size();

    /**
     * Removes all of the elements from this stack. 
     * The stack will be empty after this call returns.
     */
    void clear();
}
