# String Builder

## Declaration:
```
StringBuilder sb = new StringBuilder("hello");

String s="hello";
StringBuilder sb1 = new StringBuilder(s);
```

## Constructors
StringBuilder()	=> It creates an empty String Builder with the initial capacity of 16.

StringBuilder(String str)	=> It creates a String Builder with the specified string.

StringBuilder(int length)	=> It creates an empty String Builder with the specified capacity as length.

## Adding a character to stirng: StringBuilder append() method
```
char c;
String s;
sb.append(c);
sb.append(s);

StringBuilder sb=new StringBuilder("Hello ");  
sb.append("Java");  //now original string is changed  
System.out.println(sb);  //prints Hello Java  
```
It is used to append the specified string with this string. 
The append() method is overloaded like append(char), append(boolean), append(int), append(float), append(double) etc.


## Useful Functions in String Builder
```
sb.charAt(1);
//charAt(int index)

sb.length();


sb.reverse();


sb.toString();  //convert Strignbuilder class to string


StringBuilder sb=new StringBuilder("Hello ");  
sb.insert(1,"Java");  //now original string is changed  
System.out.println(sb);  //prints HJavaello
//The StringBuilder insert() method inserts the given string with this string at the given position.
//StringBuilder insert (int offset, String s)
//The insert() method is overloaded like insert(int, char), insert(int, boolean), insert(int, int), insert(int, float), insert(int, double) etc.


StringBuilder sb=new StringBuilder("Hello");  
sb.replace(1,3,"Java");  
System.out.println(sb);//prints HJavalo  
//StringBuilder replace(int startIndex, int endIndex, String str)


StringBuilder sb=new StringBuilder("Hello");  
sb.delete(1,3);  
System.out.println(sb);//prints Hlo
```

## Substring method
```
// String substring(int start) => Starting from the specified index till the end, this method will return the substring.
//String substring(int start, int end) => It will return the substring from the start index till the end index.
sb.subst
```

## replace() method
```
//StringBuilder replace(int start, int end, String str)
https://www.simplilearn.com/tutorials/java-tutorial/stringbuilder-in-java

## StringBuilder capacity() method
The capacity() method of StringBuilder class returns the current capacity of the Builder. 
The default capacity of the Builder is 16. If the number of character increases from its current capacity, it increases the capacity by (oldcapacityx2)+2. 
For example if your current capacity is 16, it will be (16x2)+2=34. 
```
StringBuilder sb=new StringBuilder();    
System.out.println(sb.capacity());//default 16    
sb.append("Hello");    
System.out.println(sb.capacity());//now 16    
sb.append("Java is my favourite language");    
System.out.println(sb.capacity());//now (16*2)+2=34 i.e (oldcapacity*2)+2
```

The ensureCapacity() method of StringBuilder class ensures that the given capacity is the minimum to the current capacity. 
If it is greater than the current capacity, it increases the capacity by (oldcapacityx2)+2. For example if your current capacity is 16, it will be (16x2)+2=34. 
