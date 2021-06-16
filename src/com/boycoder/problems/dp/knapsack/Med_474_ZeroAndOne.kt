package com.boycoder.problems.dp.knapsack

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/14
 * @desc: https://leetcode-cn.com/problems/ones-and-zeroes/
 */
object Med_474_ZeroAndOne {
    private var used = booleanArrayOf()
    var array: Array<String> = emptyArray()
    var max = 0
    var m = 0
    var n = 0

    var memo = hashMapOf<String, Int>()

    // 3 dimension array
    lateinit var cache: Array<Array<IntArray>>

    fun findMaxForm(strs: Array<String>, zCount: Int, oCount: Int): Int {
        this.array = strs
        used = BooleanArray(array.size)
        cache = Array(strs.size) { Array(zCount + 1) { IntArray(oCount + 1) { -1 } } }
        max = 0
        memo.clear()
        m = zCount
        n = oCount
        find(0, 0, 0, 0)
        return max
    }

    private fun find7(zCount: Int, oCount: Int): Int {
        val dp = Array(zCount + 1) { IntArray(oCount + 1) { 0 } }

        for (i in 1..array.size) {
            var zero = 0
            var one = 0
            for (char in array[i - 1]) {
                when (char) {
                    '0' -> {
                        zero++
                    }
                    '1' -> {
                        one++
                    }
                    else -> {
                        throw IllegalArgumentException()
                    }
                }
            }

            for (j in zCount downTo 0) {
                for (k in oCount downTo 0) {
                    // copy the result of i - 1
                    if (j >= zero && k >= one) {
                        dp[j][k] = Math.max(dp[j][k], dp[j - zero][k - one] + 1)
                    }
                }
            }
        }

        return dp[zCount][oCount]
    }

    /**
     * dp + no more initial
     */
    private fun find6(zCount: Int, oCount: Int): Int {
        val dp = Array(array.size + 1) { Array(zCount + 1) { IntArray(oCount + 1) { 0 } } }

        for (i in 1..array.size) {
            var zero = 0
            var one = 0
            for (char in array[i - 1]) {
                when (char) {
                    '0' -> {
                        zero++
                    }
                    '1' -> {
                        one++
                    }
                    else -> {
                        throw IllegalArgumentException()
                    }
                }
            }

            for (j in 0..zCount) {
                for (k in 0..oCount) {
                    // copy the result of i - 1
                    dp[i][j][k] = dp[i - 1][j][k]
                    if (j >= zero && k >= one) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zero][k - one] + 1)
                    }
                }
            }
        }

        return dp[array.size][zCount][oCount]
    }

    /**
     * dp with 3 dimension array
     * dp[i][j][k] means: For the [0..i] elements, zero capacity j, and one capacity k
     * we can find dp[i][j][k] combinations.
     * The initial state is dp[0][j][k] = 0
     */
    private fun find5(zCount: Int, oCount: Int): Int {
        val dp = Array(array.size) { Array(zCount + 1) { IntArray(oCount + 1) { 0 } } }

        // init when i = 0
        initDP(dp, zCount, oCount)

        for (i in 1 until array.size) {
            var zero = 0
            var one = 0
            for (char in array[i]) {
                when (char) {
                    '0' -> {
                        zero++
                    }
                    '1' -> {
                        one++
                    }
                    else -> {
                        throw IllegalArgumentException()
                    }
                }
            }

            for (j in 0..zCount) {
                for (k in 0..oCount) {
                    if (j >= zero && k >= one) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zero][k - one] + 1)
                    } else {
                        dp[i][j][k] = dp[i - 1][j][k]
                    }
                }
            }
        }

        return dp[array.size - 1][zCount][oCount]
    }

    private fun initDP(dp: Array<Array<IntArray>>, zCount: Int, oCount: Int) {
        var zero = 0
        var one = 0

        for (char in array[0]) {
            when (char) {
                '0' -> {
                    zero++
                }
                '1' -> {
                    one++
                }
                else -> {
                    throw IllegalArgumentException()
                }
            }
        }

        for (j in 0..zCount) {
            for (k in 0..oCount) {
                if (j >= zero && k >= one) {
                    dp[0][j][k] = 1
                }
            }
        }
    }

    /**
     * back track + 3 dimension array cache
     */
    private fun find4(index: Int, remainZ: Int, remainO: Int): Int {
        if (index < 0) {
            return 0
        }

        if (cache[index][remainZ][remainO] != -1) {
            return cache[index][remainZ][remainO]
        }

        var zero = 0
        var one = 0
        for (char in array[index]) {
            when (char) {
                '0' -> {
                    zero++
                }
                '1' -> {
                    one++
                }
                else -> {
                    throw IllegalArgumentException()
                }
            }
        }

        // solve the problem by divide problem into smaller problems
        var res = 0
        if (remainZ >= zero && remainO >= one) {
            res = Math.max(find4(index - 1, remainZ, remainO), find4(index - 1, remainZ - zero, remainO - one) + 1)
            cache[index][remainZ][remainO] = res
        } else {
            res = find4(index - 1, remainZ, remainO)
            cache[index][remainZ][remainO] = res
        }
        return res
    }

    /**
     * back track + cache
     */
    private fun find3(index: Int, remainZ: Int, remainO: Int): Int {
        if (index < 0) {
            return 0
        }

        val key = "${index}.${remainZ}.${remainO}"
        if (memo.containsKey(key)) {
            return memo.getOrDefault(key, 0)
        }

        var zero = 0
        var one = 0
        for (char in array[index]) {
            when (char) {
                '0' -> {
                    zero++
                }
                '1' -> {
                    one++
                }
                else -> {
                    throw IllegalArgumentException()
                }
            }
        }

        // solve the problem by divide problem into smaller problems
        var res = 0
        if (remainZ >= zero && remainO >= one) {
            res = Math.max(find3(index - 1, remainZ, remainO), find3(index - 1, remainZ - zero, remainO - one) + 1)
            memo[key] = res
        } else {
            res = find3(index - 1, remainZ, remainO)
            memo[key] = res
        }
        return res
    }

    /**
     * back track with the ideal of divide and conquer
     */
    private fun find2(index: Int, remainZ: Int, remainO: Int): Int {
        if (index < 0) {
            return 0
        }

        var zero = 0
        var one = 0
        for (char in array[index]) {
            when (char) {
                '0' -> {
                    zero++
                }
                '1' -> {
                    one++
                }
                else -> {
                    throw IllegalArgumentException()
                }
            }
        }

        // solve the problem by divide problem into smaller problems
        if (remainZ >= zero && remainO >= one) {
            return Math.max(find2(index - 1, remainZ, remainO), find2(index - 1, remainZ - zero, remainO - one) + 1)
        } else {
            return find2(index - 1, remainZ, remainO)
        }
    }

    /**
     * back track using ideal of combination
     */
    private fun find1(array: Array<String>, start: Int, size: Int, curZ: Int, curO: Int) {
        if (curZ > m || curO > n) {
            return
        }

        if (size > max) {
            max = size
        }

        if (start >= array.size) {
            return
        }

        var zero = 0
        var one = 0
        for (char in array[start]) {
            when (char) {
                '0' -> {
                    zero++
                }
                '1' -> {
                    one++
                }
                else -> {
                    throw IllegalArgumentException()
                }
            }
        }

        find1(array, start + 1, size + 1, curZ + zero, curO + one)
        find1(array, start + 1, size, curZ, curO)
    }

    /**
     * normal combination
     */
    private fun find(start: Int, size: Int, curZ: Int, curO: Int) {
        if (curZ > m || curO > n) {
            return
        }

        if (start > array.size) {
            return
        }

        if (size > max) {
            max = size
        }

        if (start >= array.size) {
            return
        }

        for (i in start until array.size) {
            var zero = 0
            var one = 0
            for (char in array[i]) {
                when (char) {
                    '0' -> {
                        zero++
                    }
                    '1' -> {
                        one++
                    }
                    else -> {
                        throw IllegalArgumentException()
                    }
                }
            }
            if (used[i]) {
                continue
            }
            used[i] = true
            find(start + 1, size + 1, curZ + zero, curO + one)
            used[i] = false
        }

    }
}

fun main() {
    val array = listOf("10", "0001", "111001", "1", "0").toTypedArray()
    val res = Med_474_ZeroAndOne.findMaxForm(array, 5, 4)
    asserts(res, 4)
    val array1 = listOf("10", "0", "1").toTypedArray()
    val res1 = Med_474_ZeroAndOne.findMaxForm(array1, 1, 1)
    asserts(res1, 2)
    val array2 = listOf("10", "00", "11").toTypedArray()
    val res2 = Med_474_ZeroAndOne.findMaxForm(array2, 1, 1)
    asserts(res2, 1)
    val array3 = listOf("100", "00", "11").toTypedArray()
    val res3 = Med_474_ZeroAndOne.findMaxForm(array3, 1, 1)
    asserts(res3, 0)
}