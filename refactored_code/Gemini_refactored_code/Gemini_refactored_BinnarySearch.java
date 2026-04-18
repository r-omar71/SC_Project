package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.SearchAlgorithm;

/**
 * A clean implementation of the Recursive Binary Search algorithm.
 * * Logic:
 * 1. Divide the search space into two halves based on the median.
 * 2. If the key matches the median, return the index.
 * 3. If the key is smaller, recurse into the left half.
 * 4. If the key is larger, recurse into the right half.
 */
public class BinarySearch implements SearchAlgorithm {

    private static final int ELEMENT_NOT_FOUND = -1;

    /**
     * Searches for a specific key within a sorted array.
     *
     * @param <T>   Type of elements, must implement Comparable.
     * @param array The sorted input array.
     * @param key   The element to locate.
     * @return The index of the key, or -1 if not found or input is invalid.
     */
    @Override
    public <T extends Comparable<T>> int find(T[] array, T key) {
        if (isInvalidInput(array, key)) {
            return ELEMENT_NOT_FOUND;
        }

        return performRecursiveSearch(array, key, 0, array.length - 1);
    }

    private <T extends Comparable<T>> boolean isInvalidInput(T[] array, T key) {
        return array == null || array.length == 0 || key == null;
    }

    /**
     * Internal recursive method that narrows down the search range.
     *
     * @param array The sorted array.
     * @param key   The search target.
     * @param low   The starting index of the current range.
     * @param high  The ending index of the current range.
     * @return Index of the key or -1.
     */
    private <T extends Comparable<T>> int performRecursiveSearch(T[] array, T key, int low, int high) {
        // Base Case: Range is exhausted
        if (low > high) {
            return ELEMENT_NOT_FOUND;
        }

        int midIndex = calculateMidIndex(low, high);
        int comparisonResult = key.compareTo(array[midIndex]);

        if (comparisonResult == 0) {
            return midIndex;
        }

        return (comparisonResult < 0) 
            ? performRecursiveSearch(array, key, low, midIndex - 1)   // Search Left
            : performRecursiveSearch(array, key, midIndex + 1, high); // Search Right
    }

    /**
     * Calculates middle index using unsigned right shift to prevent 
     * integer overflow while maintaining high performance.
     */
    private int calculateMidIndex(int low, int high) {
        return (low + high) >>> 1;
    }
}
