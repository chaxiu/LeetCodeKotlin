package com.boycoder.problems.hash

/**
 * @Author: zhutao
 * @datetime: 2021/6/4
 * @desc:
 */
object Easy_001_TwoSum {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = mutableMapOf<Int, Int>()

        nums.forEachIndexed { index, value ->
            if (map.contains(target - value)) {
                return intArrayOf(map[target - value]!!, index)
            }
            map.put(value, index)
        }
        return intArrayOf()
    }
}