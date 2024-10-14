PS- Strobogrammatic Number II [GOOGLE]
For the given length n, find all n-length Strobogrammatic numbers.

/* CODE */
```
public class Solution {
    public static ArrayList<String> findStrobogrammatic(int n){
        return solve(n,n);
    }

    public static ArrayList<String> solve(int n, int len){
        ArrayList<String> ans = new ArrayList<>();
    	if(n==0){ ans.add(""); return ans;}
        if(n==1){ ans.add("0"); ans.add("1"); ans.add("8"); return ans;}
        List<String> middleElements = solve(n-2, len); //it calls the previous length by difference of 2 strobo list around which we have to add new elements, becuase we are growing number by 2 elements
        for (String me : middleElements){
            if(n!=len) ans.add("0" + me + "0");
            ans.add("1" + me + "1");
            ans.add("8" + me + "8");
            ans.add("6" + me + "9");
            ans.add("9" + me + "6");
        }
        return ans;
    }
}

/*
Strobogrammatic Number--- 0,1,8,6,9
After 180 rotation 0,1,8 remains same, but 6 becomes 9 and 9 becomes 6

The first few strobogrammatic numbers are:
0, 1, 8, 11, 69, 88, 96, 101, 111, 181, 609, 619, 689, 808, 818, 888, 
906, 916, 986, 1001, 1111, 1691, 1881, 1961, 6009, 6119,...

That means we can have 6 and 9 as pairs. and 0,1,8 can be anywhere

The basic idea is that we will reduce the problem into small problems. 
We will recursively solve the problem for length = length - 2. 
And then add digits out of (0,1,6,8,9) at the starting and the corresponding digits (0,1,9,8,6) 
at the end.

Pay attention to the observation of n=0 and n=2, you can find that the latter is based on the former, and the left and right sides of each number are increased by [1 1], [6 9], [8 8], [9 6].

Look at n=1 and n=3, it’s more obvious, increase [1 1] around 0, become 101, increase around 0 [6 9], become 609, increase around 0 [8 8] , Becomes 808, increases [9 6] to the left and right of 0, becomes 906, and then adds the four sets of numbers to the left and right sides of 1 and 8 respectively.

In fact, it starts from the m=0 layer and adds layer by layer. It should be noted that when the n layer is added.

[0 0] cannot be added to the left and right sides, because 0 cannot appear at the beginning of two or more digits. In the process of recursive in the middle, it is necessary to add 0 to the left and right sides of the number.

Time Complexity: O(5^n)
Since for every pair of digits in the strobogrammatic number, we have 5 choices(0, 1, 6, 8, 9), 
and we are iterating over each of them. Therefore, the time complexity is O(5^N).

Auxiliary Space: O(5^n)
Since for every pair of digits in the strobogrammatic number, we have 5 choices(0, 1, 6, 8, 9), 
and there would be ‘N/2’ pairs. So there will be approximately  5^(N) strobogrammatic numbers of length ‘N’. 
*/
```

/****************************************************************************************************/
PS- Strobogrammatic Number 
Given a string ‘N’ that represents a number, you need to check if the given number is a strobogrammatic number or not.

/* CODE */
```
public class Solution {
   	public static boolean isStrobogrammatic(String n){
    	if (n==null || n.length()==0) return true;
		// Create a HashMap to store Strobogrammatic pairs
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
        map.put('6', '9');
        map.put('9', '6');
        // Use two pointers to traverse the string from both ends
        int l = 0;
        int r = n.length()-1;
        while (l<=r) {
            // Check if the characters at the current positions are valid Strobogrammatic pairs
            if(!map.containsKey(n.charAt(r)) || n.charAt(l)!=map.get(n.charAt(r))) return false;
            // Move the pointers towards the center
            l++;
            r--;
        }
        return true; // If the loop completes, the string is Strobogrammatic
    }
}
```


/*****************************************************************************************************/
PS- 241. Different Ways to Add Parentheses
Given a string expression of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. You may return the answer in any order.

Input: expression = "2-1-1"
Output: [0,2]
Explanation:
((2-1)-1) = 0 
(2-(1-1)) = 2

/* CODE */
class Solution {
    public List<Integer> diffWaysToCompute(String s){
        return solve(s);
    }

    public static List<Integer> solve(String s){
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0; i<s.length();i++){
            if(s.charAt(i)=='+' || s.charAt(i)=='-' ||s.charAt(i)=='*'){
                // Recursively solve left and right substrings
                List<Integer> left = solve(s.substring(0,i));  //store the left side combinations
                List<Integer> right = solve(s.substring(i+1)); //store the right side combinations
                // Combine results from left and right parts
                for(int l:left){
                    for(int r:right){
                        if(s.charAt(i)=='+') ans.add(l+r);
                        else if(s.charAt(i)=='-') ans.add(l-r);
                        else if(s.charAt(i)=='*') ans.add(l*r);
                    }
                }
            }
        }
        if(ans.size()==0) ans.add(Integer.parseInt(s)); //scenario like 1,2,22 etc. where no operators -> base case
        return ans;
    }
}

/*
LOGIC---
Objective - find the distinct evaluated value of the expression, after placing brackets at differnet places. 

What does bracket helps us to do? 
=> It helps us to split an expression.

RECURSION
Split the string into two parts based on the operator, then solve each substring recursively.
Whenver you see a operator split the string at that point, solve the split parts independently. And apply the same operator where we splitted.

eg: 2-1-1
split at first '-' => 2 and 1-1 => 2 and 0 => 2-0 => 2
split at second '-' => 2-1 and 1 => 1 and 1 => 1-1 = 0

eg: 2*2-1-1
split at first * => 2 and 2-1-1
Now in right part the expression in itslef can evaluate to multiple answers as seen above.
Same can go for left side. So, we need to further evaluate left and right and store their combination in list.
these two list can be later combined to get final answer.

TC-O(n*2^n)
For each sub-expression, we iterate through the string to identify the operators, which takes O(n) time. At every step from there we again explore two possibilty to sl=plit or not. 2^n

SC-O(2^n)

Note memoisation or tabulation will make SC-O(n^2*2^n) and no change in TC. 
Despite the efficiency gains from memoization, the time complexity is still dominated by the recursive nature of the algorithm. So, don't use them.
*/

/*********************************************************************************************************/
PS- JOSEPHUS PROBLEM
Given the total number of persons N and a number k which indicates that k-1 persons are skipped and the kth person is killed in a circle counting the man holding the gun himself. The task is to choose the person in the initial circle that survives.
Input: N = 5 and k = 2
Output: 3
Explanation: Firstly, the person at position 2 is killed, then the person at position 4 is killed, then the person at position 1 is killed. Finally, the person at position 5 is killed. So the person at position 3 survives. 

/* CODE */
/* ITERATIVE SOLUTION O(n), O(1) */
class Solution{
   public int josephus(int n, int k){
        int i=1;
        int ans=0;
        while(i<=n){
            ans=(ans+k)%i;
            i++;
        }
        return ans+1;
    }
}

/* RECURSIVE SOLUTION O(n), O(n) */
class Solution{
   public int josephus(int n, int k){
        if(n==1) return 1;
        else return (josephus(n-1,k) + k-1)% n+1;
    }
}

/*
LOGIC---
Becuase we are trying to repeat something again an again -> recursion we cna use

*/

/* BRUTE FORCE O(n^2) */
Josephus( list , start , k){
   if (list.size = 1) return list[0];
   start = (start + k) % list.size
   list.remove(start)
   return Josephus( list, start, k)
}

/**********************************************************************************************************/
PS- Josephus Problem when k is 2 || Lucky alive person in a circle
You kill the next person and hand over gun to next person. Find the survivor.

Why a special case?
If you write down all answers startign from n=1.
You iwll realsie the last person starts from 1 then it becoems 3 then becomes 5, then becomes 7 and so on.
So, there's a jump of 2 each time.
But it between it keeps on resetting back to 1.

n survivor
1 1
2 1
3 3
4 1
5 3
6 5
6 7
8 1
9 3
10 5
11 7
12 9

INTERESTING OBSERVATION:
for every number of power of two answer always resets back to 1.
So, when n is power of 2 the survivor is 1.

Reason : in first round you killed all even's.
Now sword is back at one. And again all evens are killed. Sword back to 1.

Now how to determine for n values between the pure power of 2?
Any number = 2^a + something
Every number can be written in powers of 2/
eg: 77 = 64 + 8 + 4 + 1
77 = 2^6 + 2^3 + 2^2 +2^0
To fit in abaove rep : n = 2^a + l
So, 77 = 2^6 + 13

Now in a 2^a the survivor is the first person.
So, after l steps whoever turn it is is the final survivor.
So, its like saying let l steps happen and then the 2^a game happens.

So, after l stpes the gun will be with person 2l+1.
So, the winner will be at seat 2l+1.

/* CODE */
/* SOLUTION O(logn) */
class Solution{
    static int find(int n){
        if((n&(n-1))==0) return 1;
        else{
            int rightmostset=0;
            int temp=n;
            while(temp!=0){
                temp=temp>>1;
                rightmostset++;
            }
            int l=n-(int)Math.pow(2,rightmostset-1);
            return 2*l+1;
        }
    }
}

/*
LOGIC---
For power of 2 answer will be 1.
Find n=2^a + l
Then answer will be 2l+1
We can determine a by getting the rightmost set bit.
*/


/*******************************************************************************************************************/
PS- 60. Permutation Sequence
Given n and k, return the kth permutation sequence.
Input: n = 3, k = 3
Output: "213"

/* CODE */
/* OPTIMAL SOLUTION O(n), O(n) */
class Solution {
    public String getPermutation(int n, int k){
        //Step I: create an array of factorial lookup
        int factorial[] = new int[n+1]; //need factorial values till n
        factorial[0]=1;
        for(int i=1;i<=n;i++) factorial[i] = factorial[i-1]*i;
        //Step II: create a list of numbers to get indices
        List<Integer> numbers = new ArrayList<>();
        for(int i=1; i<=n; i++){
            numbers.add(i);
        }
        k--;    //make k 0 indexed
        StringBuilder sb = new StringBuilder();
        for(int i=n-1;i>=0;i--){    //number of palces left to fill on the right
            int index = k/factorial[i]; //choosing the bucket 
            sb.append(Integer.toString(numbers.get(index)));
            numbers.remove(index);
            k -= index*factorial[i];
        }
        return sb.toString();
    }
}

/*
APPROACH---
WHY int index = k / factorial[i] ?
factorial[i] gives the number of permutations that can be formed with the remaining i elements. 
By dividing k by this value, the code calculates which "bucket" or group of permutations the kth permutation falls into.
For example, if factorial[i] = 6 (i.e., 6 permutations can be made for the next 3 positions), and k = 14, then dividing 14 by 6 gives 2. 
This tells you that the desired permutation is in the 3rd "bucket" (since counting starts from 0).

DRY RUN---
Initial Values:numbers = {1, 2, 3, 4}, k = 14 - 1 = 13 (since k is zero-indexed in this code logic)
factorial = {1, 1, 2, 6} (for n = 4)

Now, let's step through the iterations.
Iteration 1 (i = 3):
index = k / factorial[3] = 13 / 6 = 2
We're selecting the 3rd element (0-indexed) from the numbers list.
sb.append(numbers.get(2)) = sb.append(3)
sb becomes "3".
numbers.remove(2), so numbers becomes {1, 2, 4}.
k -= index * factorial[3] = 13 - 2 * 6 = 1
New k = 1.


LOGIC---
think dictionary.
The idea is to keep selecting a digit and eliminating it from further selection based on value of K.
For example:
Given, N = 4, K = 9
There are 6 numbers starting with 1: 1234, 1243, 1324, 1342, 1423, 1432
There are 6 numbers starting with 2: 2134, 2143, 2314, 2341, 2413, 2431
Similarly, there are 6 numbers starting with 3 and 6 numbers starting with 4. We willbe calling these distributions as buckets, so here we have in start 4(n) buckets.

This is because when we have chosen one place out of 4 places(as N=4), there are 3 places remaining to be filled and those 3 places can be filled in 6 ways or (N-1)! ways.

So, we have to keep identifying which digit to choose.

Initially, we have to choose a digit from {1,2,3,4}.
Since K = 9, meaning it belongs to second set of six numbers and hence, would begin with 2.

Now, first place is chosen as 2 and output string becomes “2”.
This means we have eliminated 6 choices starting with 1 (1234, 1243, 1324, 1342, 1423, 1432).

Now, K would be updated as K = 9-6 = 3.
We now have to identify remaining 3 places with the digits {1,3,4} and with K = 3.

There are 2 numbers starting with 1: 134, 143
There are 2 numbers starting with 3: 314, 341
There are 2 numbers starting with 4: 413, 431

This is because when we have chosen one place out of 3 available places, there are 2 places remaining to be filled and those 2 places can be filled in 2 ways.

Since, K = 3, meaning it belongs to second set of two numbers and hence, answer would be appended with “3” and output string becomes “23”.
This means we have eliminated 2 choices starting with 1 (134, 143).

Now, K would be updated as K = 3-2 = 1.

We now have to identify remaining 2 places with the digits {1,4} with K = 1.

There is 1 number starting with 1: 14
There is 1 number starting with 4: 41

This is because when we have chosen one place out of 2 available places, there is only 1 place remaining to be filled and that 1 place can be only be filled in 1 ways.

Since, K = 1, meaning it belongs to first set of one number and hence, answer would be appended with “14” and output string becomes “2314”.

Therefore, final answer becomes “2314”
*/


/* BRUTE FORCE - GENERATE ALL PERMUTATIONS + SORT */
class Solution{
    static String getPermutation(int n, int k){
        String s = "";
        ArrayList<String> list = new ArrayList <>();
        for (int i=1; i<=n; i++) {
            s += i;
        }
        solve(s.toCharArray(), 0, list);
        Collections.sort(list);
        return list.get(k-1);
    }
    
    static void solve(char s[], int idx, ArrayList <String> list) {
        if (idx == s.length){
            String str = new String(s);
            list.add(str);
            return;
        }
        for (int i=idx; i<s.length; i++){
            swap(s, i, idx);
            solve(s, idx + 1, list);
            swap(s, i, idx);    //backtrack
        }
    }
    
    static void swap(char s[], int i, int j) {
        char ch = s[i];
        s[i] = s[j];
        s[j] = ch;
    }
}

/*
LOGIC---
Time complexity: O(N! * N) +O(N! Log N!)
Reason:  The recursion takes O(N!)  time because we generate every possible permutation and another O(N)  time is required to make a deep copy and store every sequence in the data structure. Also, O(N! Log N!)  time required to sort the data structure

Space complexity: O(N) 
*/