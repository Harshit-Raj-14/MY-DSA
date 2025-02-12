# How JavaScript Code is executed?
Whenever a js code is run an "EXECUTION CONTEXT" is created.
Now this execution context has two parts, memory and code.

For the first step when js code is run:
Step I: Memory Allocation 
-> all the variables and function are allocated memory. The variables have undefiend value inside them. And the function will contain the entire function code inside it.

Note : that is why?
CODE:
function getName() {
    console.log("Namaste Javascript");
}
console.log(getName);

OUTPUT:
getName() {
    console.log("Namaste Javascript");
}

Reason: the function in memory allocation is stored with its entire function code.

Step II: Code Execution 
-> all the variables are assigned their respective valeus and for every function to be run is when encountred a new "EXECUTION CONTEXT" is created.

So, you see there is a main or global execution context and for each function we have local execution contet having their very own local memory allocation and code execution step.


Once the function is completed the local execution context is deleted after returning the value. And ocne the entire js code is executed even the global execution context is deleted.

Now how does js manages to do so much creation and deltion of exection step when it cna go so deep with nested functions and all.

JS has its own callstack, with global execution context at bottom and local execution context is pushed and popped accordingly with creation and deletion.


Hence in other words "CALL STACK" maintains the order of execution in execution context.



# Hoisting in JavaScript 
CODE 1:
var x = 7;
function getName() {
    console.log("Namaste Javascript");
}
getName();
console.log(x);

Output 1:
Namaste Javascript
7

CODE 2:
getName();
console.log(x);
console.log(getName);
var x = 7;
function getName() {
    console.log("Namaste Javascript");
}

Output 2:
Namaste Javascript
undefined
getName() {
    console.log("Namaste Javascript");
}


CODE 3:
getName();
console.log(x);
console.log(getName);
function getName() {
    console.log("Namaste Javascript");
}

Output 3:
Namaste Javascript
Uncaught ReferenceError : x is not defined
getName() {
    console.log("Namaste Javascript");
}

Explanation:
Code 1 and output 1 are simple and expected.
In code 2 initialization of function and variable x has been done after the usage. But somehow js got the function running and for x instead of returning error it returned an undefined value.
In code 3 becuase x declaration was removed from the code we get error.

Another concept : "undefined" from code 2 and "not defiend" from code 3 means that they are not the same thing,


In js hoisting allows us to access variables and functions even before you have intitalised them or put some value inside them without any error.

Reason: we know from execution context, that even before the code starts being executed the first step is memory allocation.
Becuase the variable are stored with undefiend value and function is stored with their entire code.
The function gets executed when called even if the function is written afterwards.
As for the variable it is stored with an undefiend value and will only be allocated with 7 after code execution.


# Hoisting and arrow function
CODE 3:
getName();
console.log(x);
console.log(getName);
var x=7;
var getName = () => {
    console.log("Namaste Javascript");
}

Output 3:
Uncaught TypeError: getName is not a function
undefined
undefined

Reason : Arrowfunction when used the getName now behaves like a variable instead.
So, getName() returns an error that it is not a function.
And as for variable we know that during memory allocation undefined is put inside it.
So, for getName and x we get undefined as output.




# How functions work in JS
CODE:
var x = 1
a();
b();
console.log(x);

function a() {
    var x = 10
    console.log(x);
}

function b(){
    var x = 100
    console.log(x);
}

OUTPUT:
10
100
1

Reason:
Lets do the flowchart and try to solve this:
Global Execution Context creation
Memory allocation -> x:undefined, a:{...}, b:{...}
Code execution:
-> x is assigned 1
-> Now a() is run:
    Local Execution Context creation for function a()
    Memory Allocation -> x:undefined
    Code Execution: x=10 assigned, and x is printed as 10
    Local Execution Context is deleted

since local is deleted x is assigned back global value 1

-> Now b() is run:
    Local Execution Context creation for function b()
    Memory Allocation -> x:undefined
    Code Execution: x=100 assigned, and x is printed as 100
    Local Execution Context is deleted

since local is deleted x is assigned back global value 1

-> Now console.log(x) is run which gives back 1




# What is a Higher Order Function?
A higher order function is a function that takes one or more functions as arguments, or returns a function as its result. 

// Callback function, passed as a parameter in the higher order function
function callbackFunction(){
    console.log('I am  a callback function');
}

// higher order function
function higherOrderFunction(func){
    console.log('I am higher order function')
    func()
}

higherOrderFunction(callbackFunction);

In the above code higherOrderFunction() is an HOF because we are passing a callback function as a parameter to it. 




# Shortest JS program
An empty file
JS will execute an empty file and even make a global execution context and setup a memory space.

It also creates a window object in global space and it has many windows functions.

It also creates a "this" keyowrd.
And at global level "this" points to window object. (this === window => true)

So, whenever a js file is run, it creates a global execution context, a global object (in case of browser - window), and a this variable.


# undefined vs not defined in JS 

undefined -> var a is declared but nothing is put inside, so it is undefined (undefiend is like a placeholder during memory allocation)
not defined -> a has not been declared but we might still be using a somewhere


# The Scope Chain - Scope & Lexical Environment
scope -> means where you can access a specific variable or function in a code

Scope of a variable is directly dependent on the lexical environment.

Whenever an execution context is created, a lexical environment is created. 
Lexical environment is the local memory along with the lexical environment of its parent. 
Lexical Environment = local memory(inner/child function/execution context) + lexical environment of parent(outer/parent function)

Lexical as a term means in hierarchy or in sequence.

Having the reference of parent's lexical environment means, the child or the local function can 
access all the variables and functions defined in the memory space of its lexical parent.

The JS engine first searches for a variable in the current local memory space, if its not found there, 
it searches for the variable in the lexical environment of its parent, and if its still not found, 
then it searches that variable in the subsequent lexical environments, 
and the sequence goes on until the variable is found in some lexical environment or 
the lexical environment becomes NULL (reach global execution context).

This process is also called scope chain. Of going bottom down in lexical scope to search for any variable.

The mechanism of searching variables in the subsequent lexical environments is known as Scope Chain. 
If a variable is not found anywhere, then we say that the variable is not present in the scope chain.

var -> gets hoisted up to global scope 