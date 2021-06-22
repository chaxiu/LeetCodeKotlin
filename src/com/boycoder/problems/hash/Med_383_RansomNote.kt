package com.boycoder.problems.hash

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc: https://leetcode-cn.com/problems/ransom-note/
 */
object Med_383_RansomNote {
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val array1 = IntArray(26)
        val array2 = IntArray(26)

        for (char in magazine.toCharArray()) {
            array1[char - 'a']++
        }

        for (char in ransomNote.toCharArray()) {
            array1[char - 'a']--
        }

        return !array1.any { it < 0 }

    }
}