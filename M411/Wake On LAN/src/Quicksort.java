
public class Quicksort {

    static void quickSort(String[] a, int start, int end) {

        int i = start;
        int j = end;

        if (j - i >= 1) {

            String pivot = a[i];
            while (j > i) {
                while (a[i].compareTo(pivot) <= 0 && i < end && j > i) {
                    i++;
                }
                while (a[j].compareTo(pivot) >= 0 && j > start && j >= i){
                    j--;
                }
                if (j > i){
                    swap(a, i, j);
                }

                swap(a, start, j);
                quickSort(a, start, j - 1);
                quickSort(a, j + 1, end);
            }
        }
    }

    private static void swap(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
