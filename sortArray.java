import java.io.*;
import java.util.*;

class sortArray
{
    int a[];
    int n;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public sortArray(int size) // Constructor
    {
        a = new int[size];
        n = size;
    }

    public static void main(String args[]) throws IOException
    {
        System.out.println("Introducing, the All-In-One Sortmatic Machine!");
        System.out.println("It bubble-sorts!"); 
        System.out.println("It merge-sorts!");
        System.out.println("It even bucket-sorts!");
        //System.out.println("It even shows you all the results in a step-by-step manner with a handy,dandy user interface!");
        System.out.println("All for one amazing price of 80.95!");

        System.out.println("Enter the size of the array : ");
        int size = Integer.parseInt(br.readLine());
        while(size > 16 || size < 1)
        {
            System.out.println("Invalid input. Please enter a value from 1 to 16.");
            System.out.println("Enter the size of the array : ");
            size = Integer.parseInt(br.readLine());
        }
        sortArray call = new sortArray(size);

        System.out.println("\nEnter " +size +" integers with values within the range 0 to 1000:");
        call.readArray();
        System.out.println("Choose Sorting Technique :\n");
        System.out.println("1 : Selection Sort");
        System.out.println("2 : Bubble Sort");
        System.out.println("3 : Insertion Sort");
        System.out.println("4 : Merge Sort");
        System.out.println("5 : Quick Sort");
        System.out.println("6 : Bucket Sort");
        System.out.println("7 : Radix Sort");
        System.out.print("\nYour Choice : ");
        int method = Integer.parseInt(br.readLine());

        switch(method)
        {
            case 1:
            call.selectionSort();
            break;
            case 2:
            call.bubbleSort();
            break;
            case 3:
            call.insertionSort2();
            break;
            case 4:
            call.mergeSort();
            break;
            case 5:
            call.quickSort(0, size-1);
            break;
            case 6:
            call.bucketSort();
            break;
            case 7:
            call.radixSort();
            break;
            default :
            System.out.println("\nInvalid Choice !");
            System.out.print("\nYour Choice : ");
            method = Integer.parseInt(br.readLine());
            break;
        }
        call.display();
    }

    public void readArray() throws IOException
    {
        int input;
        for(int i = 0;i < n; i++)
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

    public void selectionSort()
    {
        int temp; 
        int min;
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

    public void bubbleSort()
    {
        int temp;
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

    public void insertionSort()
    {
        int temp;
        int i;
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
    
    

    public void mergeSort()
    {
    }

    public void quickSort(int low, int high)
    {
        int i = low; 
        int j = high;

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
            quickSort(low, j-1);
            quickSort(j+1, high);
        }
        else
        {
            return;
        } 
    }

    private void exchangeNumbers(int a[], int i, int j) 
    {
        int temp ;
        temp = a[i]; 
        a[i] = a[j]; 
        a[j] = temp; 
    }

    public void bucketSort()
    {
        int[] bins = new int[1001];
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

    public void radixSort()
    {
        Queue<Integer>[] bins = new Queue[10];
        for (int i = 0; i < 10; i++)
        {
            bins[i] = new LinkedList<Integer>();
        }
        boolean sorted = false;
        int expo = 1;

        while ( ! sorted) 
        {
            sorted = true;
            for (int item : a) 
            {
                int bucket = (item / expo) % 10;
                if (bucket > 0) sorted = false;
                bins[bucket].add(item);
            }
            expo *= 10;
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
    public boolean isSorted(int[] a)
    {
        for (int i = 1; i < a.length; i++)
        {
            if (a[i - 1] > a[i])
            {
                return false;
            }
        }
        return true;
    }
    
    public void display()
    {
        System.out.println("\nSorted Array :");
        if(n==1)
        {
            System.out.print("["+ a[0] + "]");
        }
        else
        {
            System.out.print("["+ a[0] + ", ");
            for(int i=1;i<n-1;i++)
            {    
                System.out.print(a[i] + ", ");
            }
            System.out.print(a[n-1]+"]");
        }
    }
}
//When I wrote this, only God and I understood what I was doing.
    //Now, God only know.
