package sorting;

public class MergeSort implements Sorter {

    public int steps = 0;

    public void sort(int[] input) {
        steps = 0;
        mergeSort(input, input.length);
        System.out.println("Merge Sort steps: " + steps);
    }

    /**
     *  Description of the Method
     *
     * @param  list  reference to an array of integers to be sorted
     */
    public void mergeSort(int[] list, int n) {
        if (n < 2) {
            return;
        }

        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = list[i];
            steps++;
        }

        for (int i = mid; i < n; i++) {
            r[i - mid] = list[i];
            steps++;
        }

        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(list, l, r, mid, n - mid);
    }

    public void merge(int[] a, int[] l, int[] r, int left, int right) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left && j < right) {
            steps++;
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
            steps++;
        }

        while (i < left) {
            a[k++] = l[i++];
            steps++;
        }

        while (j < right) {
            a[k++] = r[j++];
            steps++;
        }
    }
}
