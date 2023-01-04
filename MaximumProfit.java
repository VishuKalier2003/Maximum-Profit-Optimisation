/* You have n jobs and m workers... You are given three arrays: difficulty, profit, and worker where:
* difficulty[i] and profit[i] are the difficulty and the profit of the ith job, and worker[j] is the ability of jth worker (i.e., the jth worker can only complete a job with difficulty at most worker[j])...Every worker can be assigned at most one job, but one job can be completed multiple times...
* For example, if three workers attempt the same job that pays $1, then the total profit will be $3... If a worker cannot complete any job, their profit is $0...
Return the maximum profit we can achieve after assigning the workers to the jobs...
 * Eg 1: Difficulty = [2, 4, 6, 8, 10]   Profit = [10, 20, 30, 40, 50]   Worker = [4, 5, 6, 7]   Output = 100    Reason : Workers are assigned jobs of difficulty [4,4,6,6] and they get a profit of [20,20,30,30] separately...
 * Eg 2: Difficulty = [85, 47, 57]       Profit = [24, 66, 99]           Worker = [40, 25, 25]   Output = 0 */
import java.util.*;
public class MaximumProfit
{
    public int[][] PossibleWork(int[] difficulty, int[] profit, int m)  // Concept of DP...
    {    // Evaluating Work possibility...
        int[][] possible = new int[2][profit.length];
        for(int i = 0, j = 0; i < possible[0].length; i++)
        {
            if(difficulty[i] <= m)    // If difficulty is more than the maximum of the worker...
            {
                possible[0][j] = difficulty[i];
                possible[1][j] = profit[i];
                j++;
            }
        }
        return possible;
    }
    public int GetLastValueIndex(int[][] possible)
    {   // The difficulty higher than possible are rendered zero...
        int index = 0;
        for(int i = 0; i < possible[0].length; i++)
        {    // We find the index of last profit work which is not zero...
            if(possible[0][i] == 0)
                break;
            index++;
        }
        if(index == 0)     // If no work is possible...
            return index;
        return index-1;
    }
    public int MaximumProfitAssignment(int difficulty[], int profit[], int worker[])
    {   // Sorting the worker array...
        Arrays.sort(worker);
        int max_profit = 0;
        int ability = worker[worker.length-1];   // Maximum possible ability...
        int work[][] = PossibleWork(difficulty, profit, ability);  // Evaluating the DP array...
        int j = worker.length-1;
        int k = GetLastValueIndex(work);    // Getting the required index...
        while(j >= 0)
        {
            if(k < 0)    // If no work is possible, render the loop...
                break;
            if(work[0][k] <= worker[j])
            {   // If the task comes within the ability of the current worker...
                max_profit = max_profit + work[1][k];
                System.out.println("Worker Ability : "+worker[j]+", Difficulty : "+work[0][k]+", Profit : "+work[1][k]);
                j--;
            }
            else
                k--;
        }
        return max_profit;
    }
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int m, n;
        System.out.print("Enter the size of Difficulty Array : ");
        n = sc.nextInt();
        int difficulty[] = new int[n];
        int profit[] = new int[n];
        System.out.print("Enter the size of Worker array : ");
        m = sc.nextInt();
        int worker[] = new int[m];
        for(int i = 0; i < n; i++)
        {
            System.out.print("Enter the Difficulty of "+(i+1)+" th Job : ");
            difficulty[i] = sc.nextInt();
        }
        for(int i = 0; i < n; i++)
        {
            System.out.print("Enter the Profit of "+(i+1)+" th Job : ");
            profit[i] = sc.nextInt();
        }
        for(int i = 0; i < m; i++)
        {
            System.out.print("Enter the Ability of "+(i+1)+" th Worker : ");
            worker[i] = sc.nextInt();
        }
        MaximumProfit maximumprofit = new MaximumProfit();    // Object creation...
        m = maximumprofit.MaximumProfitAssignment(difficulty, profit, worker);
        System.out.println("The Maximum Profit possible is : "+m);
        sc.close();
    }
}

// Time Complexity  - O(nlogn)
// Space Complexity - 0(n)

/* DEDUCTIONS :-
 * 1. The Arrays are unsorted so we first have to sort the arrays...
 * 2. The limiting factor here is worker ability so any task larger than maximum worker ability is insignificant...
 * 3. We maximize the profit by making the workers do the task in decreasing ability fashion... This maintains the maximum profit work at the highest priority...
 */