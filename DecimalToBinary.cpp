#include <bits/stdc++.h>
using namespace std;

void decimalToBinarySimple(int n){      //this method does not ahdnle overlow
    float binary=0;     //we need to keep binary float becuase: pow(10, 2) give 99.99999 if it int it convert to 99
    int i=0;
    while(n!=0){
        int bit=n&1; 
        binary=(bit*pow(10,i)) + binary;
        i++;
        n=n>>1;     //same as n/=2; right shifitng n to ge thte next int
    }
    cout<<binary<<endl;
}

void decimalToBinarySimple2(int n){ 
}

void decimalToBinaryOnlyNegative(int n){
    n=abs(n);       //n = n*(-1);
    int binary=0;    //we will have to use int and will give correct ans in online compilers only same reason becuas eof pow giving off ans in offline compilers
    int i=0;
    while(n!=0){
        int bit=n&1; 
        binary=(bit*pow(10,i)) + binary;
        i++;
        n=n>>1;     
    }
    if(n<0){
        binary=~binary;
        binary=binary & 1;
        cout<<binary<<endl;
    }
    else cout<<binary<<endl;
}

void decimalToBinary(int n){    //handles all cases
}

int main(){
    int n=-6;
    //cin>>n;
    decimalToBinaryOnlyNegative(n);
}



===  LOGIC  ===
/*
MAGIC of SHIFT OPERATORS
>>1 Right shift operator divides by 2
<<1 left shift operator multiplies by 2
eg n1=2; b1=10

n2=n1<<1;

n2=4; b2=100
in n2 bits are shifted to left for once

***************

M I: Keep on dividing n/2 and store there remainder and reverse it and print it.

**********

M II: & of a number with 1 if is 0 then n is even and if it is 1 then odd.

eg: n=5=101
    n=10=1010
So,
XXXX
&__1_
== if last bit is 1 then last X ==1
______
else it is 0
Algorithm:
while(n!=0)
bit=n&1 (rightmost bit)
n=n>>1 (right shift a bit to know the second next)
ans = (bit*10^i) + ans;

********

To convert a negative number to binary we find its positive 2's compliment
First find its 1's compliment by finding its binary then inverse it
Now & 1 to it

*********

Not when we want to store a number in same order as digits use:
ans=(ans*10) + digit;

If you want to store the digits inr reverse for the number:
ans=(digit*10^i)+ans;


*********
Then again storing bits and converting into number should not be our perfect way.
Since, int has its range and it will eventually lead to overlfow.
That's why we shoudl store bits in an array for better results.
Or, we can 

*/
