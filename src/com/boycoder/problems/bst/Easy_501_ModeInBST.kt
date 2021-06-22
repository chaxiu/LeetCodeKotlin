package com.boycoder.problems.bst

import com.boycoder.basis.datastructure.TreeNode

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Easy_501_ModeInBST {
    var pre: TreeNode? = null
    var max: Int = 0
    var count: Int = 0
    val list = ArrayList<Int>()

    fun findMode(root: TreeNode?): IntArray {
        find(root)
        return list.toIntArray()
    }

    // recursion with pointer
    private fun find(root: TreeNode?) {
        if (root == null) return

        find(root.left)

        if (pre == null) {
            count = 1
            list.add(root.value)
        } else if (pre!!.value == root.value) {
            count++
        } else {
            count = 1
        }

        if (count == max) {
            list.add(root.value)
        }

        if (count > max) {
            list.clear()

            list.add(root.value)
        }

        max = Math.max(count, max)
        pre = root

        find(root.right)
    }
}