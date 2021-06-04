package com.boycoder.utils

fun asserts(res: Any?, expect: Any?) {
    if (res != expect) {
        throw WrongResultException("\n" +
                "=================\n" +
                "res: $res \nexpect: $expect")
    } else {
        println("success")
    }
}

/**
 * increase
 */
fun isSorted(array: IntArray): Boolean {
    for (i in 1 until array.size) {
        if (array[i - 1] > array[i]) {
            return false
        }
    }

    return true
}