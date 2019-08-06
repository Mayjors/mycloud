package com.eu.util.test.sort;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuanjie
 * @date 2019/7/8 16:51
 */
public class Test {

    public static void main(String[] args) {

        List<String> fpxTrackingNoList = JSON.parseArray("[\"1\",\"2\",\"3\"]", String.class);

        int[] nums = {3, 2, 4};
        int[] ints = twoSum(nums, 9);
        System.out.println(ints);

        int s = lengthOfLongestSubstring2("asssdbjs");
        System.out.println(s);

        int i = removeDuplicates(new int[]{1, 1, 2});
        System.out.println(i);

        String s1 = countAndSay(6);
        System.out.println(s1);
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] a = new int[10];
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            while (j < nums.length) {
                if (nums[i] + nums[j] == target) {
                    a[0] = nums[i];
                    a[1] = nums[j];
                    return a;
                }
                j++;
            }
        }
        return a;
    }

    public static int[] twoSum2(int[] nums, int target) {
        int i, j;
        int[] result = new int[2];
        for (i = 0; i < nums.length; i++) {
            for (j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    if (i == j) {
                        break;
                    }
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        String str = "";
        int x = 0;
        ArrayList list = new ArrayList();
        for (int i = 0; i < s.length(); i++) {
            if (str.indexOf(s.charAt(i)) == -1) {
                str = str + s.charAt(i);
                x = str.length();
                list.add(x);
            } else {
                int z = str.indexOf(s.charAt(i)) + 1;
                str = str.substring(z) + s.charAt(i);
            }
        }
        int max = 0;
        for (int i = 0; i < list.size(); i++) {
            if (max < (int) list.get(i)) {
                max = (int) list.get(i);
            }
        } //int max = (int) Collections.max(list);
        return max;
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        int longest = 0;
        int pre = -1;
        for (int i = 0; i < s.length(); i++) {
            for (int j = pre + 1; j < i; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    pre = j;
                    break;
                }
            }
            longest = Math.max(i - pre, longest);
        }
        return longest;
    }

    /**
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
     * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     * nums1 = [1, 3]
     * nums2 = [2]
     * 则中位数是 2.0
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums=new int[nums1.length+nums2.length];
        System.arraycopy(nums1, 0, nums, 0, nums1.length);
        System.arraycopy(nums2, 0, nums, nums1.length, nums2.length);
        Arrays.sort(nums);
        int a=nums.length%2,b=nums.length/2;
        double num=0;
        if(a!=0){
            num= nums[b];
        }else {
            num= (nums[b]+ nums[b-1])*0.5;
        }
        return num;
    }

    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int lenAll = nums1.length + nums2.length;
        int i1=0,i2=0;
        int[] alls = new int[lenAll];
        for(int n = 0; n < lenAll ; n++ ) {
            if(i1 == nums1.length && i2 < nums2.length) {
                alls[n] = nums2[i2];
                i2++;
                continue;
            }
            if(i2 == nums2.length && i1 < nums1.length) {
                alls[n] = nums1[i1];
                i1++;
                continue;
            }
            if(nums1[i1] > nums2[i2]) {
                alls[n] = nums2[i2];
                i2++;
            }else {
                alls[n] = nums1[i1];
                i1++;
            }
        }
        //System.out.println(Arrays.toString(alls));
        if(lenAll % 2 == 0) {
            return (alls[lenAll/2-1] + alls[lenAll/2])*1.0/2;
        }else {
            return alls[lenAll/2];
        }
    }

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len <=1) {
            return s;
        }
        String longest = s.substring(0, 1);
        // 定义窗口，i为窗口长度，初始为2
        for (int i=2; i<len+1; i++) {
            for (int j=0; j< len -i + 1; j++) {
                if(isPalindromic(s.substring(j, j+i))) {
                    longest = s.substring(j, j+i);
                    break;
                }
            }
        }
        return longest;
    }

    private boolean isPalindromic(String substr) {
        int len = substr.length();
        for(int i = 0; i < len/2; i++) {
            if(substr.charAt(i) != substr.charAt(len - i -1))
                return false;
        }
        return true;
    }

    /**
     * 删除排序数组中的重复项
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        int len = nums.length;
        if(len <2) {
            return len;
        }
        int k = 1;
        for (int i=1; i<len; i++) {
            if (nums[i] != nums[i-1]) {
                nums[k++] = nums[i];
            }
        }
        return k;
    }

    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        int k = 0;
        int count = 0;
        if (nums[0] == val) {
//            nums[0] =;
        }
        for (int i=0; i<len; i++) {
            if (nums[i] == val) {
                k = i;
                nums[k] = nums[len-count-1];
                count++;
            }
        }
        return len - count;
    }

    /**
     * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * 1 被读作  "one 1"  ("一个一") , 即 11。
     * 11 被读作 "two 1s" ("两个一"）, 即 21。
     * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211
     * @param n
     * @return
     */
    public static String countAndSay(int n) {
        if(n == 1)return "1";
        String temp = countAndSay(n-1);
        StringBuilder ans = new StringBuilder("");
        for(int i = 0;i < temp.length();)
        {
            int j = i;
            while(j < temp.length() && temp.charAt(j) == temp.charAt(i))j++;
            int k = j-i;
            ans.append(k+""+temp.charAt(i));
            i = j;
        }
        return ans.toString();
    }
}
