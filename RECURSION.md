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




