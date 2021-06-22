package com.boycoder.problems.bst

import com.boycoder.basis.datastructure.TreeNode
import java.util.*

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Easy_530_AbsDifferenceInBST {
    // we can store the value only to get more performance
    private var pre:TreeNode? = null
    private var min = Int.MAX_VALUE
    fun getMinimumDifference(root: TreeNode?): Int {
        getMin(root)
        return min
    }

    private fun getMin(root: TreeNode?) {
        if (root == null) return

        val stk = LinkedList<TreeNode?>()
        stk.addLast(root)
        var node: TreeNode? = null

        while (!stk.isEmpty()) {
            node = stk.getLast() // peek

            node?.let {
                stk.removeLast()

                if (it.right != null) stk.addLast(it.right)

                stk.addLast(it)
                stk.addLast(null)

                if (it.left != null) stk.addLast(it.left)
            }?: run {
                stk.removeLast() // remove null
                val cur = stk.removeLast()?: return // element itself

                pre?.let {
                    min = Math.min(min, cur.value - it.value)
                }
                pre = cur
            }
        }
    }

    private fun getMin1(root: TreeNode?) {
        if (root == null) return

        getMin(root.left)

        pre?.let {
            min = Math.min(min, root.value - it.value)
        }
        pre = root

        getMin(root.right)
    }
}