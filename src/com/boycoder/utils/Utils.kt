package com.boycoder.utils

fun asserts(res: Any?, expect: Any?) {
    if (res != expect) {
        throw WrongResultException()
    }
}