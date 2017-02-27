import javax.sound.midi.Soundbank;
import java.lang.Math;

public class Sorts
{
    public static void main(String[] args) {
        System.out.println("MergeSort and Selection analysis are now running. Results will print out all at once.");
        System.out.println("Please be patient...");

        int[] mergeSizes = new int[]{10, 100, 1000, 10000, 100000, 1000000, 5000000};
        int[] insertionSizes= new int[]{10, 100, 1000, 10000, 100000, 200000, 300000};

        String[] mergeInfo = new String[7];
        String[] insertionInfo = new String[7];

        for (int i = 0; i < 7; i++) {
           long mergeComparisons = 0;
           long insertionComparisons = 0;
           long mergeTime = 0;
           long insertionTime = 0;

           for (int j = 0; j < 5; j++) {
                long mergeStart = 0;
                long mergeEnd = 0;
                long insertionStart = 0;
                long insertionEnd = 0;

                int[] randomMergeArray = new int[mergeSizes[i]];
                int[] randomInsertionArray = new int[insertionSizes[i]];

                //populate arrays with random integers
                for (int k = 0; k < mergeSizes[i]; k++) {
                   randomMergeArray[k] = (int)(Math.random() * 1000000);
                }
                for (int k = 0; k < insertionSizes[i]; k++) {
                    randomInsertionArray[k] = (int)(Math.random() * 1000000);
                }

                mergeStart = System.nanoTime();
                mergeComparisons += mergesort(randomMergeArray);
                mergeEnd = System.nanoTime();
                if(j == 4)
                    System.out.format("%-15s %4s %7d %15s", "Merge Sort", "n = ", mergeSizes[i], " has completed\n");

                insertionStart = System.nanoTime();
                insertionComparisons += insertionsort(randomInsertionArray);
                insertionEnd = System.nanoTime();
                if(j == 4)
                    System.out.format("%-15s %4s %7d %15s", "Insertion Sort", "n = ", insertionSizes[i], " has completed\n");

                if(!(isSorted(randomInsertionArray) && isSorted(randomMergeArray)))
                    System.out.println("One of the arrays isn't sorted");

                mergeTime += mergeEnd - mergeStart;
                insertionTime += insertionEnd - insertionStart;
            }

            double avgMergeComparisons = (double)(mergeComparisons/5);
            double avgInsertionComparisons = (double)(insertionComparisons/5);
            double avgMergeTime = (double)(mergeTime/5);
            double avgInsertionTime = (double)(insertionTime/5);
            double mergeCompCalc = avgMergeComparisons / (mergeSizes[i]*(Math.log(mergeSizes[i])/(Math.log(2))));
            double mergeTimeCalc = avgMergeTime / (mergeSizes[i]*(Math.log(mergeSizes[i])/(Math.log(2))));
            double insertionCompCalc = avgInsertionComparisons / (Math.pow(insertionSizes[i],2));
            double insertionTimeCalc = avgInsertionTime / (Math.pow(insertionSizes[i],2));

           mergeInfo[i] = String.format("%7d %17.2f %17f %17.2f %17f",
                    mergeSizes[i], avgMergeComparisons, mergeCompCalc, avgMergeTime, mergeTimeCalc);
           insertionInfo[i] = String.format("%7d %17.2f %17f %17.2f %17f",
                   insertionSizes[i], avgInsertionComparisons, insertionCompCalc, avgInsertionTime, insertionTimeCalc);
        }
        System.out.println("\n\nMerge Sort:");
        System.out.format("%7s %17s %17s %17s %17s", "n", "C(n)", "C(n)/O(n)", "T(n)", "T(n)/O(n)\n");
        System.out.println("-------------------------------------------------------------------------------");
        for (int i = 0; i < mergeInfo.length; i++) {
            System.out.println(mergeInfo[i]);
        }
        System.out.println("\n\nInsertion Sort:");
        System.out.format("%7s %17s %17s %17s %17s","n", "C(n)", "C(n)/O(n)", "T(n)", "T(n)/O(n)\n");
        System.out.println("-------------------------------------------------------------------------------");
        for (int i = 0; i < insertionInfo.length; i++) {
            System.out.println(insertionInfo[i]);
        }
    }
    /*--------------Insertion Sort -----------------------*/
    public static long insertionsort( int[] a)
    {
        int temp;
        long comparisons = 0;
        for (int i = 1; i < a.length; i++) {
            for(int j = i ; j > 0 ; j--){
                comparisons++;
                if(a[j] < a[j-1]){
                    temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }
            }
        }
        return comparisons;
    }
/* --------------------Merge Sort --------------------*/
    //merges sorted slices a[i.. j] and a[j + 1 …  k] for 0<=  i <=j < k < a.length

    public static long merge ( int[] a,  int i, int j , int k)
    {
        int length = 1 + (k-i);
        int[] sorted = new int[length];
        int leftIndex = i;
        int rightIndex = j+1;
        long comparisons = 0;

        for (int m = 0; m < length ; m++) {
            if(rightIndex == k+1) {
                sorted[m] = a[leftIndex];
                leftIndex += 1;
            }
            else if(leftIndex == j+1) {
                sorted[m] = a[rightIndex];
                rightIndex += 1;
            }
            else if(a[rightIndex] <= a[leftIndex]) {
                sorted[m] = a[rightIndex];
                    rightIndex +=1;
                    comparisons++;
            }
            else {
                sorted[m] = a[leftIndex];
                leftIndex += 1;
                comparisons++;
            }
        }
        for (int m = 0; m < length; m++) {
            a[i++] = sorted[m];
        }
        return comparisons;
    }
    //sorts  a[ i .. k]  for 0<=i <= k < a.length
    private  static  long mergesort(int[] a,  int i ,  int k)
    {
        long comparisons = 0;
        if((k-i)<=0)
        {
            return comparisons;
        }else
        {
            int middle = (i + k) / 2;
            comparisons += mergesort(a, i, middle);
            comparisons += mergesort(a, middle + 1, k);
            comparisons += merge(a, i, middle, k);
        }
        return comparisons;
    }
    //Sorts the array using mergesort
    public static long mergesort(int[] a )
    {
        int len = a.length - 1;
        long comparisons = mergesort(a,0, len);
        return comparisons;
    }
    public static boolean isSorted( int[] a)
    {
        for (int i = 0; i < a.length - 1; i++) {
            if(a[i] > a[i+1])
                return false;
        }
        return true;
    }
}
