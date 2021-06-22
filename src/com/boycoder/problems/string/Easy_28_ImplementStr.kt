package com.boycoder.problems.string

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Easy_28_ImplementStr {

    /**
     * Implementation for the Rabin Karp in Kotlin
     *
     * "hello"
     * "ll"    -> 'l' * 31 + 'l' * 31^0
     */
    fun strStr(s: String, pat: String): Int {

        // get the power of last digits
        // calculate the hash of pat
        // compare the hash
        val sLength = s.length
        val pLength = pat.length
        if (pLength == 0) {
            return 0
        }
        if (sLength < pLength) {
            return -1
        }

        var power = 1
        val mod = Math.pow(2.0, 20.0).toInt()

        for (i in 0 until pLength) {
            power = power * 31 % mod
        }

        var pHash = 0
        for (i in 0 until pLength) {
            pHash = (pHash * 31 + pat.get(i).toInt()) % mod
        }

        var sHash = 0
        for (j in 0 until sLength) {

            sHash = (sHash * 31 + s.get(j).toInt()) % mod

            if (j < pLength - 1) {
                continue
            }

            if (j >= pLength) {
                sHash = sHash - ((power * s.get(j - pLength).toInt()) % mod)

                if (sHash < 0) {
                    sHash = sHash + mod
                }
            }

            if (sHash == pHash) {
                // double check
                if (s.substring(j - pLength + 1, j + 1).equals(pat)) {
                    return j - pLength + 1
                }
            }
        }
        return -1
    }
}