package com.example.lib.leetcode;


public class LeetCode148 {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        // 通过快慢指针找到中心点（基数是中心，偶数是中间左侧节点）
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 使用递归 将链表分为两个部分，直到为单个节点
        ListNode temp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(temp);

        // 然后创建带空节点，插入时，不用对空链表单独处理
        // 排序
        ListNode p = new ListNode(0);
        ListNode res = p;
        while (left != null && right != null) {
            if (left.val < right.val) {
                p.next=left;
                left=left.next;
            }else{
                p.next=right;
                right=right.next;
            }
            p = p.next;
        }
        p.next= left==null?right:left;
        return res.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
