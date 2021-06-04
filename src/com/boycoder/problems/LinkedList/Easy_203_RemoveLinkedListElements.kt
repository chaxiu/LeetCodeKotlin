package com.boycoder.problems.LinkedList

import com.boycoder.basis.datastructure.ListNode

/**
 * @Author: zhutao
 * @datetime: 2021/6/4
 * @desc:
 */
object Easy_203_RemoveLinkedListElements {

    fun removeElements(head: ListNode?, target: Int): ListNode? {
        if (head == null) return head
        val dummy = ListNode(-1, head)

        var slow: ListNode? = dummy
        var fast: ListNode? = head
        while (fast != null && slow != null) {
            if (fast.value == target) {
                slow.next = fast.next
                fast = fast.next
            } else {
                slow = fast
                fast = fast.next
            }
        }

        return dummy.next
    }
}