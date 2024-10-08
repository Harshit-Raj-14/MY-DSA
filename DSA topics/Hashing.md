* HashSet is very similar to that of unordered_set in C++. It implements set interface which ensures no duplicate elements are present in hashset. The insertion of an object in hashset is based on its hash value, therefore when you iterate over hashset the order of elements that you'll get will most probably be different than how they were entered in the hashset. Hash value is the unique value of an object generated by a hash function to uniquely identify an object.

* HashSet<Integer> set : Average time complexity for add, remove and look-up operation of HashSet is O(1). Worst case time complexity is O(N) which rarely occurs.

* TreeSet<Integer> set : On an average , each operation like insert, find and delete takes O(logn) time on average. Worst Case will also take O(logN) time for each operation. Thats the advantage of using TreeSet over HashSet. All elements are stored in sorted order.


*  HashMap<Integer, String> map : Average time complexity is O(1) and worst case time complexity is O(n) => happens rarely. Similar to unordered_map in C++.
```HashMap<Integer, Integer> a = new HashMap<>();```


* The TreeMap in Java is a concrete implementation of the java.util.SortedMap interface. It provides an ordered collection of key-value pairs, where the keys are ordered based on their natural order or a custom Comparator passed to the constructor.
  The treemap implementation is not synchronized in the sense that if a map is accessed by multiple threads, concurrently and at least one of the threads modifies the map structurally, it must be synchronized externally.
  A TreeMap is implemented using a Red-Black tree, which is a type of self-balancing binary search tree. This provides efficient performance for common operations such as adding, removing, and retrieving elements, with an average time complexity of O(log n). Hnece it works better than hashmap which might have worst case O(n).

## Functions of hashset
* add()
* remove()

## Functions of HashMap
* put(key, value)
* getOrDefault()
```
for(int i=0;i<nums.length;i++){
    map.put(nums[i],map.getOrDefault(nums[i],0)+1);
}
```

# IMPORTANT ERRORS TO AVOID
error: incompatible types: boolean cannot be converted to List<String> =>>>> map.put(key, list.add(arr[i]));

The error you're encountering is due to the fact that the List.add() method in Java doesn't return the list itself but rather a boolean value indicating whether the addition was successful or not. Therefore, you cannot directly use list.add(arr[i]) as an argument to map.put(key, list.add(arr[i])).

To fix this error, you should first add the element to the list and then put the list into the map. You can do it like this:

list.add(arr[i]);
map.put(key, list);

////////////////////////////
Instead of adding the string to the list if the key exists in the map, you should retrieve the list associated with that key and then add the string to it.
if the key exists in the map, it retrieves the list associated with that key using map.get(key). If the key doesn't exist, it creates a new empty list using new ArrayList<>(). 
Then, it adds the current string to this list and puts it back in the map using map.put(key, list).
So, correct way of writing is:
if(!map.containsKey(key)){
                List<String> list = new ArrayList<>();
                list.add(arr[i]);
                map.put(key, list);
            }
            else{
                List<String> list = map.get(key);
                list.add(arr[i]);
            }

            
# PROBLEMS

## Find whether an array is subset of another array(without duplicates)
LOGIC --- USING HASHSET

Put the first array as hashset

Check whether second elements are present in hashset or not

<br>

## Find whether an array is subset of another array(with duplicates)
LOGIC --- USING HASHMAP

Put first array elemnt sin hashmap along with their frequencies
Now iterate in second array while subtracting the frequencies in hahsmap
If frequency is hit zero; it it is not a subset
```
class Compute {
    public String isSubset( long a1[], long a2[], long n, long m) {
        HashMap<Long, Integer> map = new HashMap<>();
        for(int i=0;i<a1.length;i++){
            if(!map.containsKey(a1[i])) map.put(a1[i],1);
            else map.put(a1[i], map.get(a1[i])+1);
        }
        //subtracting second array elements from map
        for(int i=0;i<a2.length;i++){
            if(map.containsKey(a2[i])){
                if(map.get(a2[i])==0) return "No"; //frequency has become zero, a2 has more of same element than a1
                else map.put(a2[i], map.get(a2[i])-1);
            }
            else return "No";
        }
        return "Yes";
    }
}
```
<br>

## Maximum distance between two occurrences of same element in array
LOGIC ---
Make a hashmap of index of first occurenc eof each elemnt
Next find max distacne by subtracting the index of each element with its first occurence index =>(this will be the max distance)
```
class Solution{
    int maxDistance(int arr[], int n){
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<arr.length;i++){
            if(!map.containsKey(arr[i])) map.put(arr[i],i);
        }
        int maxDistance=0;
        for(int i=0;i<arr.length;i++){
            int distance=Math.abs(i-map.get(arr[i]));
            maxDistance=Math.max(maxDistance, distance);
        }
        return maxDistance;
    }
}
```
<br>

## Minimum operation to make all elements equal in array
LOGIC ---
The minimum number of operations required to make all elements equal is equal to the difference between the total number of elements in the array and the maximum frequency of any element in the hashmap. We will be converting all the elements to the lement with max frequency.
```
class Solution {
    public int minOperations(int arr[]) {
        HashMap<Integer, Integer> map = new Hashmap<>();
        for(int i=0;i<arr.length;i++){
            map.put(arr[i],map.getOrDefault(arr[i],0)+1);
        }
        int maxfreq=0;
        for(int i=0;i<arr.length;i++){
            maxfreq=Math.max(maxfreq,map.get(arr[i]));
        }
        return arr.length-maxfreq;
    }
}
```
<br>

## Check if a given array contains duplicate elements within k distance from each other
LOGIC ---
Put all the first exisitng indexes on map
iterte over array
check the difference between indexes of same element is less than k and also update them
```
public boolean checkDuplicatesWithinK(int arr[]) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<arr.length;i++){
            if(!map.containsKey(arr[i])) map.put(arr[i],i);
        }
        for(int i=0;i<arr.length;i++){
            if(map.containsKey(arr[i])){
                if(i-map.get(arr[i])<=k) return true;
                map.put(arr[i],i);
            }
        }
        return false;
    }
```

