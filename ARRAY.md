PS - 121. Best Time to Buy and Sell Stock
You are given an array prices where prices[i] is the price of a given stock on the ith day.
You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
Return the maximum profit you can achieve from this transaction.

/* CODE */
```
class Solution {
    public int maxProfit(int[] prices) {
        int ans=0;
        int currmin=prices[0];  //cur buy price
        for(int i=1;i<prices.length;i++){
            ans=Math.max(ans, prices[i]-currmin);   //can we make profit with curr buy price
            currmin=Math.min(currmin, prices[i]);   //we would only like to shift to a new buy price when it is less than my prev buy_price
        }
        return ans;
    }
}
```
Note - similar problem - 2016. Maximum Difference Between Increasing Elements


/**********************************************************************************************************/
PS- Equal(Hackerrank)
You have an array of chocolates present with employees. 
You are allowed to make an operation in which you give chocolate to all employees except for one.
Also, in a operation you can only give out either 1,2 or 5 chocolates.
Tell,the minimum no of operations to apply to make all employees have same chocolates.

/* CODE */
```
public static int equal(List<Integer> arr) {
    int min=Integer.MAX_VALUE;
    for(int i=0;i<arr.size();i++){
        min=Math.min(min, arr.get(i));
    }
    int ans=Integer.MAX_VALUE;
    for(int i=0;i<5;i++){   //min to min-4
    int ops=0;
        for(int j=0;j<arr.size();j++){
            int dif=arr.get(j)-min;
            ops+= dif/5 + (dif%5)/2 + ((dif%5)%2)/1;    //once divided by 5, let's divide the remainder after division by 2 and so on
        }
        ans=Math.min(ans, ops);
        min--;  //move to next possiblity of min
    }
    return ans;
}

/*
LOGIC---
Giving chocolates to everyone except one is the same as taking chocolate from that one employee.

It is difficult to think who to give chocolate when so many employees.
rather its easy to just decide who not to give cohocolate since we focus on one prson.

It will be easier to tweak one value in array rather than changing all the values.

eg: [4,4,4,6,4] => rather than thinking how to make each of them equal.
Why not just bring 6 to the same level of 4 by taking two choclolates away from him

eg: [2,2,3,7]
ops1: give 5 to everyone except 4 => [7,7,8,7]
ops2: give 1 to everyone except 3 => [8.8.8.8]
ans => minimum 2 steps

Now think of this problem in above explaiend terms:
Reverse the operations---
take 5 chocolates from 4 => [2,2,3,2]
take 1 chocolate from 3 => [2,2,2,2]

Infact, we can say that we are trying to attain a minimum level to bring the chocolate distribution equal.

But note that this minimal value, is not necessary the minimum of our array.
eg: [2,6,6,6]
ops1: take 2 chocolates from 2 => [2,4,6,6]
if you keep this up : [2,2,2,2]
but total operation made = 2*3=6

But there is a more optimal solution
ops1: take 5 chocolates from 2 => [2,1,6,6]
ops2: take 5  chocolates from 3 => [2,1,1,6]
ops3: take 5  chocolates from 4 => [2,1,1,1]
ops4: take 1  chocolates from 1 => [1,1,1,1]
total operations made = 4 

Infact the minimal state we want to reach becuase of the operations will vary from (minimum of aray)min to min-4.
Reason: The range comes from the fact that you can either give 1, 2 or 5 chocolates. 
That's why you have to check all the possibilities in that range. 
We do not go past "min-4" because that will take 5 operations which is same as a single operation of giving 5 chocolates at once.
If you reach ‘min-5’, then you will reach the same scenario you started with.
Try to do a dry run and take away 5 chocolates, you will find yourself in a recurrence.

Algorithm---
Find the minimum in array. 
Explore every minimum possiblity to reach min, min-1, min-2, min-3, min-4.
Use 1,2,5 removal ops at each index to reach the minimal level.

Note : if she could have distributed 8 chocolate we would have checked till min-7.
*/
```

/********************************************************************************************************/
PS-Nth Natural Number [GOOGLE]
Given a positive integer n. You have to find n-th natural number after removing all the numbers containing the digit 9.

/* CODE */
```
/* OPTIMISED APPROACH NUMBER SYSTEM - CHANGING TO BASE 9 O(logn) */
class Solution {
    long findNth(long n) {
        long bs9n=0;
        int pos=1;
        while(n!=0){
            long r=n%9;
            bs9n += pos * r;    //constructing the number in base 9 with remaninder in reverse order
            pos*=10;    //Move to the next decimal place
            n/=9;
        }
        return bs9n;
    }
}

/*
LOGIC---
NUMBER SYSTEM
base 2 => (0,1) => 2 possibilities => all numbers are made of combination 0f 0 and 1
0,1 => all numbers exhausted repeat again
00,01,10,11 => all numbers exhausted repeat again

base 10 => (0-9) => 10 possibilities => all numbers are made from 0 to 9
1,2,3,4,5,6,7,8,9 => all numbers exhausted, we start repeating again
10,12,13,14,15,16,17,18,19 => all numbers exhausted then repeat again
=> numbers end at 9

think about possibility of number system of base 9

base 9 => (0,8) => 9 possilbities => number system won't contain digit 9
=> that's exactly what we want
0,1,2,3,4,5,6,7,8
10,11,12,13,14,15,16,17,18
20,21,22,.....,28
you see number 9 has disappeared.
In fact the 9th number of base 10 when excluding digits containing 9 = 9th number of base 9 =10 {tc 2)

GOAL--- We need to return the nth number in base 9
n is itself in base 10
=> In other words we need to convert n into base 9 and that will be our answer

How to ocnvert a number from one base to another?
Remember how you converted any decimal number into binary by dividing it by 2 repeatedly.

So, to convert n to base 9:(we are decreasing base so we divide)
Step I: divide it by 9 until n is 0
=> construct the number in reverse order of remainders


BRUTE FORCE--- O(nlogn)
Run the code till you find the nth number after checking and skipping any digit that contains 9

*/
```
```
/* Time - O(log(n)), Space - O(1) */
public static long method4(long N) {
    long remainder;
    StringBuilder builder = new StringBuilder();
    while (N != 0) {
        remainder = N % 9;
        builder.append(remainder);
        N = N / 9;
    }
    return Long.parseLong(builder.reverse().toString());
}
```
```
/* ONE LINER O(logn) */
class Solution {
    long findNth(long n) {
        return Long.parseLong(Long.toString(n, 9));
    }
}
/*
LOGIC---
Long.toString(n, 9) => converts the number n from base 10 (decimal) to a string representing the number in base 9.
Long.parseLong(...) => converts the base-9 string back into a long integer.
*/
```