package com.example.lib;

import java.util.ArrayDeque;
import java.util.Queue;

class QueueTest {
    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        queue.offer(2);

        System.out.println(queue.poll()); // 1
        System.out.println(queue.poll()); // 2
    }
}