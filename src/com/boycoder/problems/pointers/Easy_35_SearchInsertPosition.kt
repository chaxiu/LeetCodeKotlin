package com.boycoder.problems.pointers

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/21
 * @desc:
 */
object Easy_35_SearchInsertPosition {

    /**
     * 1, 3, 5, 6
     * |        |
     *
     * 1, 3, 5, 6
     *    |     |
     *
     * 1, 3, 5, 6
     *    |     |
     * 1, 3, 5, 6
     *    |  |
     */
    fun searchInsert(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size - 1

        while (left <= right) {
            val mid = (right + left) /2
            if (target > nums[mid]) {
                left = mid + 1
            } else if (target < nums[mid]) {
                right = mid - 1
            } else {
                return mid
            }
        }

        return left
    }
}

fun main() {
    val res = Easy_35_SearchInsertPosition.searchInsert(intArrayOf(1,3,5,6), 5)
    asserts(res, 2)
    val res1 = Easy_35_SearchInsertPosition.searchInsert(intArrayOf(1,3,5,6), 2)
    asserts(res1, 1)
}