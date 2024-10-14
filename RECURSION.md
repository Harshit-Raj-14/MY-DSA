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