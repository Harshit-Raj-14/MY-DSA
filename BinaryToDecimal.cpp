# include <bits/stdc++.h>
using namespace std;

int binaryToDecimal(int binary){
    int i=0;
    int num=0;
    while(binary!=0){
        int digit = binary%10;
        if(digit==1) num=pow(2,i) + num;
        //else if (bit==0) Do nothing
        i++;    
        binary/=10;
    }
    return num;
}

int main(){
    int n=110;
    int ans=binaryToDecimal(n);
    cout<<ans<<endl;  
}



/*
MAGIC of SHIFT OPERATORS
>>1 Right shift operator divides by 2
<<1 left shift operator multiplies by 2
eg n1=2; b1=10

n2=n1<<1;

n2=4; b2=100
In n2 bits are shifted to left for once

Algorithm
Find the last digit of binary using %10;
if last digit is 1, 1*2^i+ans where i is palce and keep on adding to ans
if last digit is 0 its just 0*2^i=0 so no need to od anything for it
keep on dividing the binary to keep on proceeding
*/
