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
        List<List<String>> res = new LeetCode().partition("aab");
        System.out.println(res);

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

    private List<List<String>> res = new ArrayList<>();
    private Stack<String> path = new Stack<>();

    public List<List<String>> partition(String s) {
        dfs(s, 0);
        return res;
    }

    /**
     * @param s 字符串
     * @param cur 当前字符在字符串的下标
     */
    private void dfs(String s, int cur) {
        if(cur == s.length() - 1) {
            res.add(new ArrayList(path));
            return;
        }

        for(int i = cur; i < s.length(); i++) {
            if(isPalindrome(s, cur, i)) {
                String str = s.substring(cur, i - cur + 1);
                path.push(str);
            } else {
                continue;
            }
            dfs(s, i + 1);
            path.pop();
        }
    }

    // 判断子串是否是回文串
    private boolean isPalindrome(String s, int l, int r) {

        while(l < r) {
            if(s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                return false;
            }
        }

        return true;

    }
}
