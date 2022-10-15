package com.example.leetcode;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @Author: yzy
 * @Date: 2022/10/7-22:14
 * @Description:
 */
public class LeetCode {
    public static void main(String[] args) {
        List<List<Integer>> combine = new LeetCode().combinationSum3(3, 7);
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

    private List<List<Integer>> res = new ArrayList<>();
    private Stack<Integer> path = new Stack<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        dfs(k, n, 1, 0);
        return res;
    }


    private void dfs(int k, int n, int cur, int sum) {
        if(path.size() > k) {
            return;
        }

        if(sum == n && path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i = cur; i < 10; i++) {
            if(sum + i > n) {
                break;
            }

            path.push(i);


            dfs(k, n, i + 1, sum + i);

            // 回溯
            path.pop();

        }
    }
}
