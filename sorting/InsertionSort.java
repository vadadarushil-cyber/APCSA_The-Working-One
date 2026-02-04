package sorting;
public class InsertionSort implements Sorter {
    public int steps = 0;
    public void sort(int[] input) {
        steps = 0;
        for (int i = 1; i < input.length; i++) {
            int key = input[i];
            int j = i - 1;
            while (j >= 0 && input[j] > key) {
                steps++;            
                input[j + 1] = input[j];
                steps++;            
                j--;
            }
            input[j + 1] = key;
            steps++;                 
        }
        System.out.println("Insertion Sort steps: " + steps);
    }
}