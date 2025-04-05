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

        // begin = end is a valid case;
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
     * find the last matching index, return -1, if there is no target
     */
    fun searchLastIndex(array: IntArray, target: Int): Int {
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

        if (end < 0 || array[end] != target) {
            return -1
        }

        return end
    }

    /**
     * find the first matching index, return -1, if there is no target
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
                end = mid - 1
            }
        }

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
}