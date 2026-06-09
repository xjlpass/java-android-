package com.example.lib.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Leetcode207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int []inDegree = new int[numCourses];

        for(int i=0;i<numCourses;i++){
            graph.add(new ArrayList<>());
        }
        for(int[] prerequisite: prerequisites){
            int a= prerequisite[0];
            int b =prerequisite[1];
            inDegree[a]++;
            graph.get(b).add(a);
        }

        Deque<Integer> dq= new ArrayDeque<>();
        // 将入度为0的顶点放到队列中，为了保证该顶点前置顶点都执行完
        for(int i=0;i<numCourses;i++){
            if(inDegree[i]==0){
                dq.add(i);
            }
        }

        int curCourseNum =0;
        while(!dq.isEmpty()){
            Integer cur =dq.poll();
            curCourseNum++;
            for(int next:graph.get(cur)){
                inDegree[next]--;
                if(inDegree[next]==0){
                    dq.add(next);
                }
            }

        }
        return curCourseNum ==numCourses;
    }
}
