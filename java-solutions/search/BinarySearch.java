package search;

public class BinarySearch {
    //исходный массив нигде не меняется, это очевидно
    //Pred (a.length > 0 && ∃ i ∈ [0:a.length - 1] : a[i] <= x && ∀ i, j ∈ [0:a.length - 1], i < j : a[i] >= a[j] || a.length == 0) && (l == -1 && r == a.length)
    //Post (a.length > 0 ? (R ∈ [0:a.length - 1] && a[R] <= x && ∀ i ∈ [0:a.length - 1], i < R : a[i] > x) : (R == 0))
    static int recursiveBinarySearch(int[] a, int x, int l, int r) {
        //Pred (a.length > 0 && ∃ i ∈ [0:a.length - 1] : a[i] <= x && ∀ i, j ∈ [0:a.length - 1], i < j : a[i] >= a[j] || a.length == 0) && ∀ i ∈ [0, l'] : a[i] > x && ∀ i ∈ [r', r] : a[i] <= x
        if (r - l == 1) {
            //Pred (a.length > 0 && ∃ i ∈ [0:a.length - 1] : a[i] <= x && ∀ i, j ∈ [0:a.length - 1], i < j : a[i] >= a[j] || a.length == 0) && ∀ i ∈ [0, l'] : a[i] > x && ∀ i ∈ [r', r] : a[i] <= x
            return r;
            //Post r = ans на [l, r]
        }
        //Post (a.length > 0 && ∃ i ∈ [0:a.length - 1] : a[i] <= x && ∀ i, j ∈ [0:a.length - 1], i < j : a[i] >= a[j] || a.length == 0) && ∀ i ∈ [0, l'] : a[i] > x && ∀ i ∈ [r', r] : a[i] <= x && r - l > 1
        //Prev true
        int m = (l + r) / 2;
        //Post l <= m <= r
        //Prev хороший интервал в терминах задачи
        if (a[m] > x) {
            //слева нет ответа -> ответ справа
            return recursiveBinarySearch(a, x, m, r);
        }
        //Pred справа нет ответа
        return recursiveBinarySearch(a, x, l, m);
        //Post ответ слева
    }

    //Pred a.length > 0 && ∃ i ∈ [0:a.length - 1] : a[i] <= x && ∀ i, j ∈ [0:a.length - 1], i < j : a[i] >= a[j] || a.length == 0
    //Post (a.length > 0 ? (R ∈ [0:a.length - 1] && a[R] <= x && ∀ i ∈ [0:a.length - 1], i < R : a[i] > x) : (R == 0))
    static int iterativeBinarySearch(int[] a, int x) {
        //Pred a.length > 0 && ∃ i ∈ [0:a.length - 1] : a[i] <= x && ∀ i, j ∈ [0:a.length - 1], i < j : a[i] >= a[j] || a.length == 0
        int l = -1, r = a.length;
        //Post (a.length > 0 && ∃ i ∈ [0:a.length - 1] : a[i] <= x && ∀ i, j ∈ [0:a.length - 1], i < j : a[i] >= a[j] || a.length == 0) && l == -1 && r == a.length
        //Pred (a.length > 0 && ∃ i ∈ [0:a.length - 1] : a[i] <= x && ∀ i, j ∈ [0:a.length - 1], i < j : a[i] >= a[j] || a.length == 0) && l == -1 && r == a.length
        while (r - l > 1) {
            //Pred (a.length > 0 && ∃ i ∈ [0:a.length - 1] : a[i] <= x && ∀ i, j ∈ [0:a.length - 1], i < j : a[i] >= a[j]) && r' - l' > 1 && l', r' ∈ [0:a.length - 1]
            int m = (l + r) / 2;
            //Post l' <= m <= r'
            //Pred (a.length > 0 && ∃ i ∈ [0:a.length - 1] : a[i] <= x && ∀ i, j ∈ [0:a.length - 1], i < j : a[i] >= a[j]) && r' - l' > 1 && l', r' ∈ [0:a.length(у l' правая на один меньше, тоже самое для следующих строчек)] && l' <= m <= r'
            if (a[m] > x) {
                //Pred (a.length > 0 && ∃ i ∈ [0:a.length - 1] : a[i] <= x && ∀ i, j ∈ [0:a.length - 1], i < j : a[i] >= a[j]) && r' - l' > 1 && l', r' ∈ [0:a.length] && l' <= m <= r' && a[m] > x
                l = m;
                //Post (a.length > 0 && ∃ i ∈ [0:a.length - 1] : a[i] <= x && ∀ i, j ∈ [0:a.length - 1], i < j : a[i] >= a[j]) && r' - l' > 1 && l', r' ∈ [0:a.length] && l' <= m <= r' && ∀ i ∈ [l':m) : a[i] > x
            } else {
                //Pred (a.length > 0 && ∃ i ∈ [0:a.length - 1] : a[i] <= x && ∀ i, j ∈ [0:a.length - 1], i < j : a[i] >= a[j]) && r' - l' > 1 && l', r' ∈ [0:a.length] && l' <= m <= r' && a[m] <= x
                r = m;
                //Post (a.length > 0 && ∃ i ∈ [0:a.length - 1] : a[i] <= x && ∀ i, j ∈ [0:a.length - 1], i < j : a[i] >= a[j]) && r' - l' > 1 && l', r' ∈ [0:a.length] && l' <= m <= r' && ∀ i ∈ (m:r'] : a[i] <= x
            }
            //Post (r' - l' + 1 > 0 && ∃ i ∈ [l':r'] : a[i] <= x && ∀ i, j ∈ [l':r'], i < j : a[i] >= a[j]) && l', r' ∈ [0:a.length - 1] && l' <= m <= r' && ∀ i ∈ (m:r'] : a[i] <= x
        }
        //Post 0 <= r - l <= 1 && (a.length == 0 ? (r == 0) : (r ∈ [0:a.length - 1] && a[r] <= x && ∀ j ∈ [0:r) : a[j] > x))
        return r;
    }

    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int[] a = new int[args.length - 1];
        for (int i = 1; i < args.length; ++i) {
            a[i - 1] = Integer.parseInt(args[i]);
        }
        System.out.println(iterativeBinarySearch(a, x));
    }
}
