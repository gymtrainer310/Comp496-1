import java.lang.Math;

public class Sorts
{
    public static void main(String[] args)
    {
        int number_size = 1000000;
        int cardinality = 10000000;
        int[] nums = new int[cardinality];
        for (int i = 0; i < cardinality; i++)
        {
            nums[i] = (int)(Math.random() * number_size);
        }
        mergesort(nums);
        System.out.println("Sorted!");
        System.out.print(isSorted(nums));
    }
    /*--------------Insertion Sort -----------------------*/
    public static long insertionsort( int[] a)
    {
        return (long)0;
    }



/* --------------------Merge Sort --------------------*/
    //merges sorted slices a[i.. j] and a[j + 1 …  k] for 0<=  i <=j < k < a.length

    public static long merge ( int[] a,  int i, int j , int k)
    {
        int size = k-i+1;
        int[] temp = new int[size];
        int temp_i = i;
        int temp_j = j+1;
        int temp_k = k;
        int max = a.length;

        for (int l = 0; l < size ; l++) {
            if(temp_i == j+1)
            {
                temp[l] = a[temp_j++];
            }else if(temp_j == k+1)
            {
                temp[l] = a[temp_i++];
            }else if(a[temp_i] <= a[temp_j])
            {
                temp[l] = a[temp_i++];
            }else
            {
                temp[l] = a[temp_j++];
            }
        }
        for (int l = 0; l < size; l++) {
            a[i++] = temp[l];
        }
        return (long)1;
    }


    //sorts  a[ i .. k]  for 0<=i <= k < a.length
    private  static  long mergesort(int[] a,  int i ,  int k)
    {
        if((k-i)<=0)
        {
            return (long)0;
        }else
        {
            int middle = (i + k) / 2;
            mergesort(a, i, middle);
            mergesort(a, middle + 1, k);
            merge(a, i, middle, k);
        }
        return (long)1;
    }

    //Sorts the array using mergesort
    public static long mergesort(int[] a )
    {
        mergesort(a,0, a.length-1);
        return (long)1;
    }


    public static boolean isSorted( int[] a)
    {
        for (int i = 1; i < a.length; i++) {
            if(a[i]<a[i-1])
                return false;
        }
        return true;
    }

}