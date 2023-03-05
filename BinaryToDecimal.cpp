# include <bits/stdc++.h>
using namespace std;

int binaryToDecimal(int binary){    //Efficient O(logn)
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

int binaryToDecimalString(string n){    //O(n) loops n times as length of string
    int num=0;
    int j=0;
    for(int i=n.length()-1;i>=0;i--){
        if(n[i]=='1') num=pow(2,j)+num;
        j++;
    }
    return num;
    }

int main(){
    int n=110;
    string s="110";
    int ans=binaryToDecimal(n);
    int ans2=binaryToDecimalString(s);
    cout<<ans<<endl;  
    cout<<ans2<<endl;  
}



/*
Algorithm
Find the last digit of binary using %10;
if last digit is 1, 1*2^i+ans where i is palce and keep on adding to ans
if last digit is 0 its just 0*2^i=0 so no need to od anything for it
keep on dividing the binary to keep on proceeding
*/
