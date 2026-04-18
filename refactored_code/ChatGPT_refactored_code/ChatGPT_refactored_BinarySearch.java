package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.SearchAlgorithm;

/**
 * Binary Search Algorithm Implementation.
 *
 * Works only on a sorted array (ascending order).
 * Time complexity: O(log n)
 * Space complexity: O(log n) due to recursion
 */
class BinarySearch implements SearchAlgorithm {

    @Override
    public <T extends Comparable<T>> int find(T[] array, T key) {

        if (array == null || array.length == 0 || key == null) {
            return -1;
        }

        return binarySearchRecursive(array, key, 0, array.length - 1);
    }

    private <T extends Comparable<T>> int binarySearchRecursive(
            T[] array, T key, int leftIndex, int rightIndex) {

        if (rightIndex < leftIndex) {
            return -1;
        }

        int midIndex = (leftIndex + rightIndex) >>> 1;

        int comparisonResult = key.compareTo(array[midIndex]);

        if (comparisonResult == 0) {
            return midIndex;
        }

        if (comparisonResult < 0) {
            return binarySearchRecursive(array, key, leftIndex, midIndex - 1);
        }

        return binarySearchRecursive(array, key, midIndex + 1, rightIndex);
    }
}
