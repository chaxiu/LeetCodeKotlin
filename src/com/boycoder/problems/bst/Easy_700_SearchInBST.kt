package com.boycoder.problems.bst

import com.boycoder.basis.datastructure.TreeNode

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Easy_700_SearchInBST {
    fun searchBST(root: TreeNode?, target: Int): TreeNode? {
        if (root == null) return null
        if (root.value == target) return root
        if (root.value > target) return searchBST(root.left, target)
        if (root.value < target) return searchBST(root.right, target)

        return null
    }
}