package com.thealgorithms.sorts;

/**
 * BubbleSort implementation that sorts an array by repeatedly swapping 
 * adjacent elements if they are in the wrong order.
 * * Performance:
 * - Worst/Average Case: O(n²)
 * - Best Case: O(n) (when already sorted)
 * - Space: O(1)
 */
public class BubbleSort implements SortAlgorithm {

    /**
     * Sorts the provided array using the Bubble Sort algorithm.
     *
     * @param <T>   The type of elements (must be Comparable).
     * @param array The array to be sorted.
     * @return The sorted array.
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (isEmptyOrSingleElement(array)) {
            return array;
        }

        bubbleElements(array);
        return array;
    }

    private <T extends Comparable<T>> void bubbleElements(T[] array) {
        int lastIndex = array.length - 1;

        // Outer loop tracks the number of passes
        for (int pass = 0; pass < lastIndex; pass++) {
            boolean wasSwapped = performPass(array, lastIndex - pass);

            // Optimization: If no elements were swapped in a pass, the array is sorted.
            if (!wasSwapped) {
                break;
            }
        }
    }

    /**
     * Performs a single pass of bubble sort, comparing adjacent elements.
     * * @param upperBound The index up to which we need to compare.
     * @return true if any elements were swapped, false otherwise.
     */
    private <T extends Comparable<T>> boolean performPass(T[] array, int upperBound) {
        boolean swapped = false;

        for (int j = 0; j < upperBound; j++) {
            if (isLeftGreaterThanRight(array[j], array[j + 1])) {
                SortUtils.swap(array, j, j + 1);
                swapped = true;
            }
        }

        return swapped;
    }

    private <T extends Comparable<T>> boolean isLeftGreaterThanRight(T left, T right) {
        return SortUtils.greater(left, right);
    }

    private <T> boolean isEmptyOrSingleElement(T[] array) {
        return array == null || array.length <= 1;
    }
}
