#include <bits/stdc++.h>
using namespace std;

void decimalToBinarySimple(int n){      //O(n) //this method does not handle overlow
    float binary=0;     //we need to keep binary float becuase: pow(10, 2) give 99.99999 if it int it convert to 99
    int i=0;
    while(n!=0){
        int bit=n&1;    //to find the last digit // int r=n%2;
        binary=(bit*pow(10,i)) + binary;
        i++;
        n=n>>1;     //same as n/=2; right shifitng n to ge thte next int
    }
    cout<<binary<<endl;
}

void decimalToBinaryAll(int n){     //O(1) Most Efficient
    for(int i=31;i>=0;i--){     //assuming int is of 32 bits    //Also we have to do it in reverse becuase we want to print in reverse the bits for binary
        int bit=n>>i;
        if(bit & 1 == 1) cout<<"1";
        else cout<<"0";     //keep this in string otherwise 0 will be neglected
    }
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


void decimalToBinaryArray(int n){    //O(logn)
    int binary[32];
    int i=0;
    while(n>0){
        binary[i]=n%2;
        i++;
        n=n>>1;     //n/=2;
    }
    // printing binary array in reverse order
    for (int j=i-1; j>=0; j--) cout << binary[j];
    }


void decimalToBinary(int n){    //handles all cases
}

int main(){
    int n=17;
    //cin>>n;
    decimalToBinaryArray(n);
}


/*
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
That's why we should store bits in an array or string for better results.
*/
