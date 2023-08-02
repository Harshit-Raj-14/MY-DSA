## Right Rotation of Array:
```
int last=arr.get(arr.size()-1);
  for(int j=arr.size()-1;j>0;j--){  //we traverse backwards
    arr.set(j,arr.get(j-1));  //arr[j]=arr[j-1];
  }
  arr.set(0,last);
```

LOGIC---
1 2 3 4 5
  1 2 3 4  //shifted to right
5 4 3 2 1  //pasted right in front


## Right Rotate Array k times
Brute Force - O(n*k)   => run the above rotation program k times 
Approach : Idea is to right-rotate all array elements by one position k times, where k is the given rotation count.

#### Optimised Approach - TC-O(n) , SC-O(k)
Approach - Using Auxiliary Array
We can reduce the time complexity of the above solution to linear using some extra space. 
The idea is to store the last k elements of the input array in an auxiliary array of size k. 
Then shift the first n-k elements of the input array at the end. 
Finally, put elements of the auxiliary array at their correct positions in the input array.
```
// construct an auxiliary array of size `k` and
// fill it with the last `k` elements of the input array
int aux[] = new int[k];
for(in t=0;i<aux.length;i++){
  aux[i]=arr[arr.length-k+i];
}
// shift the first `n-k` elements of the input array at the end
for(int i=arr.length-k-1;i>=0;i--){
  arr[i+k]=arr[i];
}
// put the elements of the auxiliary array at their correct positions in the input array
for(int i=0;i<k;i++){
  arr[i]=aux[i];
}
//OUPUT - arr is right rotated k times
```

#### USING MODULUS OPERATOR

## Left Rotation of Array:
```
int first = arr[0];
for(int i=1;i<arr.length;i++){
  arr[i-1]=arr[i];
}
arr[arr.length-1]=first;
```
