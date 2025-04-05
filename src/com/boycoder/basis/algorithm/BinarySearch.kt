package com.boycoder.basis.algorithm

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/4
 * @desc: Search in a sorted array.
 */
object BinarySearch {

    /**
     * find target index, return -1 if there is no target in sorted array
     */
    fun search(array: IntArray, target: Int): Int {
        var begin = 0
        var end = array.size - 1
        var mid = 0

        // when end = array.size - 1, begin = end = array -1, is a valid case;
        while (begin <= end) {
            mid = begin + ((end - begin) /2)

            // array[mid] < target, means target is in the right half of array;
            if (array[mid] < target) {
                // use mid + 1, because we already know array[mid] is not the target;
                begin = mid + 1
                // array[mid] > target, means target is in the left half of the array;
            } else if (array[mid] > target) {
                // use mid - 1, because we already know array[mid] is not the target;
                end = mid - 1
            } else {
                // find it!
                return mid
            }
        }
        // not found
        return -1
    }

    /**
     * Another binary search;
     */
    fun search2(array: IntArray, target: Int): Int {
        var start = 0
        var end = array.size
        var mid = 0

        // because end = array.size, array[array.size] is not valid;
        // so we use < instead of <=;
        while(start < end) {
            // avoid overflow
            mid = start + ((end - start) /2)

            // target is in the right half of the array;
            if (array[mid] < target) {
                start = mid + 1
            } else if (array[mid] > target) { // target is in the left half of the array;
                // because we use < as while loop condition, we need to use mid instead of (mid - 1);
                end = mid
            } else {
                return mid
            }
        }
        return -1
    }

    /**
     * find the last matching index in a sorted array, return -1, if there is no target
     */
    fun searchLastIndex(array: IntArray, target: Int): Int {
        var begin = 0
        var end = array.size - 1
        var mid = 0

        // we use <= because begin = end = array.size -1 is a valid case;
        while (begin <= end) {
            mid = begin + ((end - begin) /2)

            // target is in the right half of the array;
            if (array[mid] < target) {
                // we already know array[mid] is not the target;
                begin = mid + 1
            } else if (array[mid] > target) { // target is in the left half of the array;
                // we already know array[mid] is not the target;
                end = mid - 1
            } else {
                // here is the key, when we find the target, we need to make begin = targetIndex + 1;
                // and then let end slowly move to begin;
                // when begin = end = targetIndex + 1, array[mid] > target;
                // so end = mid - 1 = targetIndex;
                begin = mid + 1
            }
        }

        // There is no target in the array;
        if (end < 0 || array[end] != target) {
            return -1
        }
        return end
        // or we can change to:
//        if (end >= 0 && end < array.size && array[end] == target) {
//            return end
//        }
//        return -1
    }

    /**
     * find the first matching index in a sorted array, return -1, if there is no target
     */
    fun searchFirstIndex(array: IntArray, target: Int): Int {
        var begin = 0
        var end = array.size - 1
        var mid = 0

        while (begin <= end) {
            mid = begin + ((end - begin) /2)

            if (array[mid] < target) {
                begin = mid + 1
            } else if (array[mid] > target) {
                end = mid - 1
            } else {
                // same here. let end = targetIndex - 1;
                // then array[mid] < target always true, until begin = end = targetIndex - 1
                // this time, begin = mid + 1 = targetIndex;
                end = mid - 1
            }
        }

        // check the index is valid.
        if (begin >= array.size || array[begin] != target) {
            return -1
        }

        return begin
    }

    fun searchInsertIndex(array: IntArray, target: Int): Int {
        var begin = 0
        var end = array.size - 1
        var mid = 0

        while (begin <= end) {
            mid = begin + ((end - begin) /2)

            if (array[mid] < target) {
                begin = mid + 1
            } else if (array[mid] > target) {
                end = mid - 1
            } else {
                begin = mid + 1
            }
        }

        return end + 1
    }
}

fun main() {
    val res = BinarySearch.search(intArrayOf(1,2,3,4,5,6,7,8), 3)
    asserts(res, 2)
    val res1 = BinarySearch.searchLastIndex(intArrayOf(1,2,3,3,3,4,5,6), 3)
    asserts(res1, 4)
    val res2 = BinarySearch.searchLastIndex(intArrayOf(1,2,3,3,3,4,5,6), 0)
    asserts(res2, -1)
    val res3 = BinarySearch.searchFirstIndex(intArrayOf(1,2,3,3,3,4,5,6), 3)
    asserts(res3, 2)
    val res4 = BinarySearch.searchFirstIndex(intArrayOf(1,2,3,3,3,4,5,6), 0)
    asserts(res4, -1)
    val res5 = BinarySearch.searchFirstIndex(intArrayOf(1,2,3,3,3,4,5,6), 7)
    asserts(res5, -1)
    val res6 = BinarySearch.searchInsertIndex(intArrayOf(1,2,3,3,3,4,5,6), 1)
    asserts(res6, 1)
    val res7 = BinarySearch.searchInsertIndex(intArrayOf(1,2,3,3,3,4,5,6), 7)
    asserts(res7, 8)
    val res8 = BinarySearch.searchInsertIndex(intArrayOf(1,2,3,3,3,4,5,6), 0)
    asserts(res8, 0)
    val res9 = BinarySearch.searchInsertIndex(intArrayOf(1,2,3,3,3,4,5,6), 3)
    asserts(res9, 5)
    val res10 = BinarySearch.searchLastIndex(intArrayOf(1,2,3,4,5), 3)
    asserts(res10, 2)
}