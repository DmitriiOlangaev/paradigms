package search;

import java.util.Random;

public class BinarySearchMin {
    //Pre a - good array
    //Post R - a[i] from pre
    private static int iterativeBinarySearchMin(final int[] a) {
        //Pre a - good array
        int l = -1, r = a.length - 1;
        //Post a - good array l < ans <= r
        //Pre a - good array l < ans <= r
        while (r - l > 1) {
            //Pre r - l > 1 && a - good array && l < ans <= r
            int m = (l + r) / 2;
            //Post l < m < r && a - good array && l < ans <= r
            if (a[m] > a[m + 1]) {
                //Pre a[m] > a[m + 1] && l < m < r && a - good array && l < ans <= r
                l = m;
                //Post a - good array && m < ans <= r
            } else {
                //Pre a[m] < a[m + 1] && l < m < r && a - good array && l < ans <= r
                r = m;
                //Post a - good array && l < ans <= m
            }
            //Post a - good array && l < ans <= r
        }
        //Post a[r] - answer
        return a.length == 0 ? 1337 : a[r];
    }

    //Pre a.subarray from l + 1 to r - good array
    //Post R - a[i] from pre
    private static int recursiveBinarySearchMin(int[] a, int l, int r) {
        //Pre a.subarray from l + 1 to r - good array && l < ans <= r
        if (r - l <= 1) {
            //Pre r - l <= 1 && a.subarray from l + 1 to r - good array && l < ans <= r
            return (r - l == 0 ? 1337 : a[r]);
            //Post 1) r - l == 0 -> min = 1337
            //Post 2) r = min
        }
        //Pre r - l > 1 && a.subarray from l + 1 to r - good array && l < ans <= r
        int m = (l + r) / 2;
        //Post l < m < r && a.subarray from l + 1 to r - good array && l < ans <= r
        if (a[m] > a[m + 1]) {
            //Pre a[m] > a[m + 1] && l < m < r && a.subarray from l + 1 to r - good array && l < ans <= r
            //Post R - a[i] from pre
            return recursiveBinarySearchMin(a, m, r);
        }
        //Pre a[m] < a[m + 1] && l < m < r && a.subarray from l + 1 to r - good array && l < ans <= r
        //Post R - a[i] from pre
        return recursiveBinarySearchMin(a, l, m);
    }

    // [0, 0]
    // Definition good array(size n) - ∃ i ∈ [0 : n - 1] : ∀ j, k ∈ [0:i] j < k -> a[j] > a[k] && ∀ j, k ∈ [i : n - 1] j < k -> a[j] < a[k]
    // Pre args - array of strings && each element can be parsed as Int && array of parsed elements is good array
    // Post Stdout -> a[i], i from good array definition
    public static void main(String[] args) {
        //Pre args - args - array of strings && each element can be parsed as Int && array of parsed elements is good array
        int[] a = new int[args.length];
        //Post a.length = args.length && args - array of strings && each element can be parsed as Int && array of parsed elements is good array
        //Pre a.length = args.length && args - array of strings && each element can be parsed as Int && array of parsed elements is good array
        for (int i = 0; i < args.length; ++i) {
            a[i] = Integer.parseInt(args[i]);
        }
        //Post a - good array
        Random rnd = new Random();
        //Pre a - good array
        System.out.println(rnd.nextBoolean() ? recursiveBinarySearchMin(a, -1, a.length - 1) : iterativeBinarySearchMin(a));
        //Post Stdout -> a[i], i from good array definition
    }

}
