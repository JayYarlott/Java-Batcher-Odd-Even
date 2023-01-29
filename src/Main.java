import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

/**
 5.10 Quick Sort
 @author Jay Y
 @since date
 */

public class Main {
    public static int[][][][] networkSort(int size) {
        return indices(size);
    }

    private static int log2(int num){
        if(num == 0)
            return 0;
        return 31 - Integer.numberOfLeadingZeros(num);
    }

    private static int nextpot(int v) {
        int i = Integer.highestOneBit(v);
        return v > i ? i << 1 : i;
    }

    public static void sort(SortingNetwork net, int arr[], int[][][][] pairsss) {
        net.setArray(arr);
        int l1 = pairsss.length;
        for(int i = 0; i < l1; i++) {
            int l2 = pairsss[i].length;
            for(int j = 0; j < l2; j++){
                net.runAll(pairsss[i][j]);
            }
        }
    }

    private static int[][][][] indices(int n){

        n = nextpot(n); // npot compliance

        int[][][][] ret = new int[log2(n)][][][]; //number of times stride will run

        for(int stride = 1; stride < n; stride*=2) { //log2 (n) times
            ret[log2(stride)] = new int[log2(stride)+1][][];
            for (int interstride = stride; interstride >= 1; interstride /= 2) { //log2 (stride)+1 times
                ArrayList<int[]> list = new ArrayList<int[]>(interstride%stride);
                for(int j = interstride%stride; j < n-interstride; j+=2*interstride){
                    for(int i = 0; i < interstride; i++){
                        if((i+j) / (stride*2) == (i+j+interstride) / (stride*2)) {
                            list.add(new int[]{j + i, i + j + interstride});
                        }
                    }
                }
                ret[log2(stride)][(log2(stride)-log2(interstride))] = list.toArray(new int[list.size()][2]);
            }
        }
        return ret;
    }


    // no touchy
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);
        Random robot = new Random();

        int count;
        int[] nums;

        System.out.print("Sort how many values? > ");
        count = input.nextInt();

        int[][][][] pairs = networkSort(count);
        SortingNetwork network = new SortingNetwork();

        // fill array with random numbers
        nums = robot.ints(count).toArray();

        // output unsorted array
        System.out.println("\nUNSORTED");
        for (int num : nums) {
            System.out.print(num + " ");
        }

        // sort
        sort(network, nums, pairs);

        // output sorted array
        System.out.println("\n\nSORTED");
        for (int num : nums) {
            System.out.print(num + " ");
        }

    }

};