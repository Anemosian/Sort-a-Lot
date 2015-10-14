import java.io.*;
import java.util.*;

class sortMatic
{
    int a[];
    int n;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) throws IOException
    {
        System.out.println("Introducing, the All-In-One Sortmatic Machine!");
        System.out.println("It bubble-sorts!"); 
        System.out.println("It merge-sorts!");
        System.out.println("It even bucket-sorts!");
        //System.out.println("It even shows you all the results in a step-by-step manner with a handy,dandy user interface!");
        System.out.println("All for one amazing price of 74.95!");

        System.out.println("Enter the size of the array : ");
        int size = Integer.parseInt(br.readLine());
        while(size > 16 || size < 1)
        {
            System.out.println("Invalid input. Please enter a value from 1 to 16.");
            System.out.println("Enter the size of the array : ");
            size = Integer.parseInt(br.readLine());
        }
        int[] call = new int[size];

        System.out.println("\nEnter " +size +" integers with values within the range 0 to 1000:");
        readArray(call);
        System.out.println("Choose Sorting Technique :\n");
        System.out.println("1 : Selection Sort");
        System.out.println("2 : Bubble Sort");
        System.out.println("3 : Insertion Sort");
        System.out.println("4 : Merge Sort");
        System.out.println("5 : Quick Sort");
        System.out.println("6 : Bucket Sort");
        System.out.println("7 : Radix Sort");
        System.out.println("8 : Exit");
        System.out.print("\nYour Choice : ");

        int method = Integer.parseInt(br.readLine());
        while(method > 7 || method < 1)
        {
            System.out.println("\nInvalid Choice ! Please choose again.");
            System.out.print("\nYour Choice : ");
            method = Integer.parseInt(br.readLine());
        }
        switch(method)
        {
            case 1:
            selectionSort(call);
            break;
            case 2:
            bubbleSort(call);
            break;
            case 3:
            insertionSort(call);
            break;
            case 4:
            mergeSort(call);
            break;
            case 5:
            quickSort(call, 0, size-1);
            break;
            case 6:
            bucketSort(call);
            break;
            case 7:
            radixSort(call);
            break;
            case 8:
            System.out.println("\nThank you for using the All-In-One Sortmatic Machine.");
            System.exit(1);
            break;
            default :
            System.out.println("\nInvalid Choice !");
            System.exit(1);
            break;
        }
        display(call);
    }

    public static void readArray(int[] a) throws IOException
    {
        int input;
        int n = a.length;
        for(int i = 0; i < n; i++)
        {
            input = Integer.parseInt(br.readLine());
            while( input < 0 || input > 1000 )
            {
                System.out.println("Invalid input.");
                System.out.println("Please enter a value from 0 to 1000.");
                System.out.println("\nPlease enter " + (n-i) + " more numbers");
                input = Integer.parseInt(br.readLine());
            } 
            a[i] = input;
        }
    }

    public static void selectionSort(int[] a)
    {
        int temp; 
        int min;
        int n = a.length;
        for(int i=0;i<n-1;i++)
        {
            min = i;
            for(int j=i+1;j<n;j++)
            {
                if(a[min]>a[j])
                {
                    min = j;
                }
            }
            if(min!=i)
            {
                temp = a[min];
                a[min] = a[i];
                a[i] = temp;
            }
        }
    }

    public static void bubbleSort(int[] a)
    {
        int temp;
        int n = a.length;
        for(int i = 0; i < n-1; i++)
        {
            for(int j = 0; j < n-1-i ; j++)
            {
                if(a[j] > a[j+1])
                {
                    temp = a[j+1];
                    a[j+1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    public static void insertionSort(int[] a)
    {
        int temp;
        int i;
        int n = a.length;
        for(int j = 1; j < n ;j++)
        {
            temp = a[j];
            i = j;
            while(i > 0 && a[i-1]>temp)
            {
                a[i] = a[i-1];
                i-=1;
            }
            a[i]=temp;
        }
    }

    public static void mergeSort(int[] a) 
    {
        int n = a.length;
        if (n >= 2) {
            // split array in half
            int[] left  = Arrays.copyOfRange(a, 0, a.length / 2);
            int[] right = Arrays.copyOfRange(a, a.length / 2, a.length);

            // sort the halves
            mergeSort(left);
            mergeSort(right);

            // merge them back together
            int i1 = 0;
            int i2 = 0;
            for (int i = 0; i < n; i++) {
                if (i2 >= right.length ||
                (i1 < left.length && left[i1] < right[i2])) {
                    a[i] = left[i1];
                    i1++;
                } else {
                    a[i] = right[i2];
                    i2++;
                }
            }
        }
    }

    public static void quickSort(int[] a, int low, int high)
    {
        int i = low; 
        int j = high;
        int n = a.length;
        if(high - low >= 1)
        {
            int pivot = a[low];
            while(j > i)
            {
                while(a[i]<=pivot && i<=high && j>i)
                {
                    i++;              
                }
                while(a[j]>pivot && j>= low && j>= i)
                {
                    j--;
                }
                if(j>i)
                {
                    exchangeNumbers(a, i, j);
                }
            }
            exchangeNumbers(a, low, j);
            quickSort(a, low, j-1);
            quickSort(a, j+1, high);
        }
        else
        {
            return;
        } 
    }

    private static void exchangeNumbers(int a[], int i, int j) 
    {
        int temp ;
        temp = a[i]; 
        a[i] = a[j]; 
        a[j] = temp; 
    }

    public static void bucketSort(int[] a)
    {
        int[] bins = new int[1001];
        int n = a.length;
        for (int i = 0; i < n ; i++)
        {
            bins[a[i]]++;
        }
        int outPos = 0;
        for (int i = 0; i < bins.length; i++)
        {
            for (int j = 0; j < bins[i]; j++)
            {
                a[outPos++] = i;
            }
        }
        return;
    }

    public static void radixSort(int[] a)
    {
        int n = a.length;
        Queue<Integer>[] bins = new Queue[10];
        for (int i = 0; i < 10; i++)
        {
            bins[i] = new LinkedList<Integer>();
        }
        boolean sorted = false;
        int deciPlace = 1;

        while ( ! sorted) 
        {
            sorted = true;
            for (int item : a) 
            {
                int bucket = (item / deciPlace) % 10;
                if (bucket > 0) sorted = false;
                bins[bucket].add(item);
            }
            deciPlace *= 10;
            int index = 0;
            for (Queue<Integer> bucket : bins)
            {
                while ( ! bucket.isEmpty())
                {
                    a[index++] = bucket.remove();
                }
            }
        }
        assert isSorted(a);
    }

    public static boolean isSorted(int[] a)
    {
        int n = a.length;
        for (int i = 1; i < n; i++)
        {
            if (a[i - 1] > a[i])
            {
                return false;
            }
        }
        return true;
    }

    public static void display(int[] a)
    {
        int n = a.length;
        System.out.println("\nSorted Array :");
        if(n==1)
        {
            System.out.print("["+ a[0] + "]");
            System.out.println("\nThank you for using the All-In-One Sortmatic Machine.");
        }
        else
        {
            System.out.print("["+ a[0] + ", ");
            for(int i=1;i<n-1;i++)
            {    
                System.out.print(a[i] + ", ");
            }
            System.out.print(a[n-1]+"]");
            System.out.println("\nThank you for using the All-In-One Sortmatic Machine.");
        }
    }
}
//When I wrote this, only God and I understood what I was doing.
//Now, God only know.
