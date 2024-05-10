package com.algorithm.string;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/5/10 8:58 下午
 */
public class AddLargeNumbers {


    /**
     * 题目描述：给定两个字符串，表示两个非常大的整数，请计算两个大整数的和，结果也用字符串表示。
     * @param a
     * @param b
     * @return
     */
    public String plus(String a, String b) {
        char[] arr_a = a.toCharArray();
        char[] arr_b = b.toCharArray();
        reverse(arr_a, 0, a.length() - 1);
        reverse(arr_b, 0, b.length() - 1);

        StringBuilder sb = new StringBuilder();

        int i = 0, j = 0, up = 0;
        while(i < arr_a.length || j < arr_b.length || up > 0) {

            int cur = up;
            up = 0;

            if(i == arr_a.length && j < arr_b.length) {
                cur += Integer.valueOf(arr_b[j++] - '0');
            } else if(j == arr_b.length && i < arr_a.length) {
                cur += Integer.valueOf(arr_a[i++] - '0');
            } else if(i < arr_a.length && j < arr_b.length) {
                cur += Integer.valueOf(arr_b[j++] - '0');
                cur += Integer.valueOf(arr_a[i++] - '0');
            }

            if(cur > 10) {
                up = cur / 10;
                cur = cur % 10;
            }
            sb.append(cur);
        }

        char[] ans_arr = sb.toString().toCharArray();
        reverse(ans_arr, 0, ans_arr.length - 1);
        return new String(ans_arr);
    }

    public static void main(String[] args) {
        AddLargeNumbers add = new AddLargeNumbers();
        String a = "1242143214123412222421432";
        String b = "5342524524354133433323433";
        System.out.println(add.plus(a, b));
    }


    void reverse(char[] arr, int lo, int hi) {
        while(lo < hi) {
            swap(arr, lo++, hi--);
        }
    }

    void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
