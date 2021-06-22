package com.boycoder.problems.linkedList

import com.boycoder.basis.datastructure.ListNode

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc: https://leetcode-cn.com/problems/reverse-linked-list/
 */
object Easy_206_ReverseLinkedList {
    fun reverseList(head: ListNode?): ListNode? {
        if (head == null) return head
        return reverseRecur(head)
    }

    fun reverseRecur(head: ListNode): ListNode? {
        if (head.next == null) return head

        var result = reverseRecur(head.next!!)

        var next = head.next!!
        next.next = head
        head.next = null
        return result
    }

    /**
     *  Ideal
     *1. Two pointer
     *
     * 1 -> 2 -> 3 -> 4 -> 5 -> null
     *
     * null <- 1 -> 2 -> 3 -> 4 -> 5 -> null
     */
    fun reverseList1(head: ListNode?): ListNode? {
        if (head == null) return head

        var pre: ListNode? = null
        var cur = head
        var next: ListNode? = null

        while (cur != null) {
            next = cur.next
            cur.next = pre
            pre = cur
            cur = next
        }

        return pre
    }
}
