import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SortingNetwork {
    int[] arr;

    private void compare(int i, int j) {
        if (i < arr.length && j < arr.length){ //npot compliance
            if (arr[i] > arr[j]) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    }

    public void runAll(int[][] pairs){
        Arrays.stream(pairs).parallel().forEach((pair) -> compare(pair[0], pair[1]));
    }

    ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void runAll2(int[][] pairs){
        for(int[] pair : pairs){
            exec.submit(() -> compare(pair[0], pair[1]));
        }
    }

    public void setArray(int[] a){
        arr = a;
    }
}
