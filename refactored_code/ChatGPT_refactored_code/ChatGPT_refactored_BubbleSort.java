package com.thealgorithms.sorts;

/**
 * Bubble Sort implementation using a generic comparable type.
 *
 * Time Complexity:
 * - Best case: O(n) when already sorted
 * - Average case: O(n^2)
 * - Worst case: O(n^2)
 *
 * Space Complexity: O(1) (in-place sorting)
 */
class BubbleSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {

        int arrayLength = array.length;

        for (int passIndex = 1; passIndex < arrayLength; passIndex++) {

            boolean hasSwapped = false;

            for (int currentIndex = 0; currentIndex < arrayLength - passIndex; currentIndex++) {

                if (SortUtils.greater(array[currentIndex], array[currentIndex + 1])) {
                    SortUtils.swap(array, currentIndex, currentIndex + 1);
                    hasSwapped = true;
                }
            }

            // Early exit if already sorted
            if (!hasSwapped) {
                break;
            }
        }

        return array;
    }
}
