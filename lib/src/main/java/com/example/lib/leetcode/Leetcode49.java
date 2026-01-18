package com.example.lib.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode49 {
    // 初始版本太罗嗦了
    // key是每个单词的字母个数，value是 List<String> 然后将map中的value 存到结果中
//    public List<List<String>> groupAnagrams(String[] strs) {
//        if (strs.length == 0) return new ArrayList<>();
//        Map<String, List<String>> map = new HashMap<>();
//        List<List<String>> ans = new ArrayList<>();
//        Arrays.sort(strs);
//        for (int i = 0; i < strs.length; i++) {
//            String temp = ToCharArray(strs[i]);
//            if (map.containsKey(temp)) {
//                map.get(temp).add(strs[i]);
//            } else {
//                List tempArray = new ArrayList<>();
//                tempArray.add(strs[i]);
//                map.put(temp, tempArray);
//            }
//        }
//
//        for (List<String> value : map.values()) {
//            ans.add(value); // 把每个分组的集合，添加到ans中
//        }
//        return ans;
//    }
//
//    private String ToCharArray(String str) {
//        char[] c = new char[26];
//        for (int i = 0; i < str.length(); i++) {
//            c[str.charAt(i) - 'a']++;
//        }
//        return new String(c);
//    }
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] temp = strs[i].toCharArray();
            Arrays.sort(temp);
            // 注意: 不能写成  map.computeIfAbsent(new String(temp), k -> new ArrayList<>().add(strs[i]));
            // 注意computeIfAbsent 方法是线程不安全的，建议使用

            List<String> list=map.getOrDefault(temp,new ArrayList<>());
            list.add(strs[i]);
            map.put(new String(temp),list);
        }
        return new ArrayList<>(map.values());
    }
}
