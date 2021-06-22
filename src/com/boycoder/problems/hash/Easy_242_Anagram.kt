package com.boycoder.problems.hash

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc: https://leetcode-cn.com/problems/valid-anagram/
 */
object Easy_242_Anagram {
    fun isAnagram(s: String, t: String): Boolean {
        val array1 = s.toCharArray()
        val array2 = t.toCharArray()

        val result = IntArray(26)

        for (char in array1) {
            result[char - 'a']++
        }

        for (char in array2) {
            result[char - 'a']--
        }

        return !result.any { it != 0 }
    }

    fun isAnagram1(s: String, t: String): Boolean {
        val map: MutableMap<Char, Int> = mutableMapOf()

        s.toList().forEach{
            map.put(it, map.getOrDefault(it, 0) + 1)
        }
        t.toList().forEach {
            map.put(it, map.getOrDefault(it, 0) - 1)
        }

        return !map.values.any { it != 0 }
    }
}