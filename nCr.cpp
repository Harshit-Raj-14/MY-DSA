# include<bits/stdc++.h>
using namespace std;


int factorial(int n){
    if (n==0) return 1;
    int ans = 1;
    while(n>0){
        ans*=n;
        n--;
    }
    return ans;
}

int nCr(int n, int r){
    int ans = factorial(n) / (factorial(n-r) * factorial(r));
}

int main(){
    int n,r;
    cin>>n>>r;
    int c = nCr(n,r);
    cout<<c<<endl;
}


/*
nCr = (n!) / ((n-r)! * r!)
*/
