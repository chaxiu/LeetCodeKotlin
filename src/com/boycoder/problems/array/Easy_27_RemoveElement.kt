package com.boycoder.problems.array

/**
 * @Author: zhutao
 * @datetime: 2021/6/4
 * @desc:
 */
object Easy_27_RemoveElement {

    /**
     * Two pointer start from two side of array
     */
    fun remove(array: IntArray, target: Int): Int {
        var left = 0
        var right = array.size - 1

        while (left <= right) {
            if (array[right] == target) {
                right--
                continue
            }
            if (array[left] == target) {
                array[left] = array[right]
                right--
                left++
            } else {
                left++
            }
        }

        return right + 1
    }

    /**
     * Two point: slow and fast, start from beginning
     */
    fun remove1(array: IntArray, target: Int): Int {
        var slow: Int = 0
        var fast: Int = 0

        val size = array.size

        while (fast < size && slow <= fast) {
            if (array[fast] == target) {
                fast++
            } else {
                if (slow != fast) {
                    array[slow] = array[fast]
                }

                fast++
                slow++
            }
        }

        return slow
    }

}