import java.util.Arrays;
import java.util.Random;

public class Tester {

    static SortingNetwork net;
    public static void sort(int arr[], int[][][][] pairsss) {
        net.setArray(arr);
        int l1 = pairsss.length;
        for(int i = 0; i < l1; i++) {
            int l2 = pairsss[i].length;
            for(int j = 0; j < l2; j++){
                net.runAll(pairsss[i][j]);
            }
        }
    }

    public static void sort2(int arr[], int[][][][] pairsss) {
        net.setArray(arr);
        int l1 = pairsss.length;
        for(int i = 0; i < l1; i++) {
            int l2 = pairsss[i].length;
            for(int j = 0; j < l2; j++){
                net.runAll2(pairsss[i][j]);
            }
        }
    }

    private static boolean isOrdered(int[] arr) {
        int acc = arr[0];
        for(int i = 1; i < arr.length; i++) {
            if(acc > arr[i])
                return false;
            acc = arr[i];
        }
        return true;
    }

    public static void main(String[] args){

        Random rand = new Random();
        net = new SortingNetwork();

        for(int i = 2; i <= 1200; i+=rand.nextInt(100)) {
            int[][][][] pairs = Main.networkSort(i);
            int nt = 50;
            for (int j = 0; j < nt; j++) {
                int[] arr = rand.ints(i).toArray();
                sort(arr, pairs);
                if (!isOrdered(arr)) {
                    System.out.printf("Failed test %d of size %d\n", j, i);
                    return;
                }
            }
        }


        System.out.println("All tests passed!");


    }

}
