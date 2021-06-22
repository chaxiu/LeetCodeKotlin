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

fun <T>assertCollection(res: Collection<T>, expect: Collection<T>) {
    if (res.size != res.size) {
        throw WrongResultException("\n" +
                "=================\n" +
                "res: $res \nexpect: $expect")
    }

    val iterator1 = res.iterator()
    val iterator2 = expect.iterator()
    while (iterator1.hasNext() && iterator2.hasNext()) {
        val v1 = iterator1.next()
        val v2 = iterator2.next()

        if (v1 != v2) {
            throw WrongResultException("\n" +
                    "=================\n" +
                    "res: $res \nexpect: $expect")
        }
    }

    println("success")
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