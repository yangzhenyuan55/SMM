package com.example.leetcode;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yzy
 * @Date: 2022/10/7-22:14
 * @Description:
 */
public class LeetCode {
    public static void main(String[] args) {
        List<List<Integer>> combine = new LeetCode().combine(4, 2);
        System.out.println(combine);


    }


    /**
     * 第三题
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        // key --- 字符
        // value --- 字符的下标
        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 0;


        while (right < s.length()) {
            if (map.isEmpty() || !map.containsKey(s.charAt(right))) {
                map.put(s.charAt(right), right++);
            } else {
                int index = map.get(s.charAt(right));
                for (; left <= index; left++) {
                    map.remove(s.charAt(left));
                }
            }
            maxLen = Math.max(maxLen, map.size());
        }

        return maxLen;
    }

    /**
     * 剑指offer
     *
     * @param n
     * @return
     */
    public static int numWays(int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 1;
        } else {
            return numWays(n - 1) + numWays(n - 2);
        }
    }

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {

        backtracking(n, k, 1);
        return res;
    }

    void backtracking(int n, int k, int startIndex) {
        if (path.size() + (n - startIndex + 1) < k) {
            return;
        }

        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 考虑选择当前位置
        path.add(startIndex);
        backtracking(n, k, startIndex + 1);
        path.remove(path.size() - 1);
        // 考虑不选择当前位置
        backtracking(n, k, startIndex + 1);


    }
}
