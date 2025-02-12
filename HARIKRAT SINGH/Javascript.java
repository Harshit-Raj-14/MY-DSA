Why we need language? Why we need more than one language, why not unify everythign with one language?


Compiled language / compiler -> conver whole code into 0/1(temp file). then run.

Interpreted Language/ Intrpreter/ Javascript -> is interpreted line by line and then run. line by line. one line converted run it, then convert other line run it. other eg: python 


C++/Java: Compilation → Creates intermediate files (.class or .o) as part of the execution pipeline.
JavaScript: No temp files are created during execution unless you explicitly code for it or use a build tool. Everything typically happens in memory, leveraging JIT compilation for performance.

Intermediate Caching in Engines:
JIT Compilation: Modern JavaScript engines (e.g., V8) may compile hot code paths into native machine code for performance. However, this happens entirely in-memory, without creating intermediate files on disk.


static language : you need to declare type of varaible and cant change it. java, c++

dynamic language (loosely typed): no need to declare type of variable or you can even later change it. Python and js


Single Threaded nature of JS
No of cores in cmputer = no of processes you can run
one core can only one thing ( context switiching is allowed) but at one time it will only run one process at one time.

JS runs line by line and only one line run at a time.

In JavaScript, the code in the main thread runs one line after the other and will not split across multiple cores. 
JavaScript is single-threaded by default, meaning it can only execute one task at a time on the main thread. 
However, JavaScript can achieve concurrency using the event loop and can utilize multiple cores via Web Workers or worker threads for parallel tasks.

Hence becuase of its single threaded nature, its considered a bad language for scalable systems.

there's always a way thoguh to run it on more than one cores/machines


LET, VAR & CONST
var came first
var is hoisted, meaning it is moved to the top of its scope during the compile phase. However, the variable is initialized as undefined until its value is assigned later in the code.

Redeclaration: You can redeclare a var variable in the same scope without any errors.

Use Case:It is generally discouraged in modern JavaScript because let and const provide better scoping rules and reduce bugs.

Scope: var is function-scoped, meaning it is only accessible within the function in which it is declared. If declared outside a function, it becomes a global variable.
It does not respect block scope (e.g., inside if, for, or while blocks).

```
console.log(x); // undefined (hoisted)
var x = 5;
console.log(x); // 5

if (true) {
  var y = 10; // Function-scoped
}
console.log(y); // 10 (accessible outside the block)
```

LET
Scope: let is block-scoped, meaning it is only accessible within the block ({}) where it is declared, such as inside an if, for, or while block.

Hoisting: let is also hoisted, but it is not initialized. Accessing it before its declaration results in a ReferenceError (known as the temporal dead zone).

Redeclaration: You cannot redeclare a let variable in the same scope, but you can reassign its value.

Use Case: Use let when the value of a variable needs to change during the program's execution (e.g., loop counters).

```
if (true) {
  let a = 10; // Block-scoped
  console.log(a); // 10
}
console.log(a); // ReferenceError: a is not defined

let b = 5;
b = 20; // Reassignment is allowed
console.log(b); // 20
```

CONST
Scope: Like let, const is block-scoped and only accessible within the block where it is declared.

Hoisting: const is hoisted but remains uninitialized in the temporal dead zone until its declaration is encountered.

Redeclaration and Reassignment: You cannot redeclare or reassign a const variable. However, if the const variable holds an object or array, its properties or elements can be modified (the reference itself cannot be changed).

Use Case: Use const when the value of a variable should never change during the program's execution (e.g., configuration values, constants).


| Feature          | var                         | let                         | const                             |
|------------------|-----------------------------|-----------------------------|-----------------------------------|
| **Scope**        | Function-scoped             | Block-scoped                | Block-scoped                     |
| **Hoisting**     | Yes (initialized as `undefined`) | Yes (but in Temporal Dead Zone) | Yes (but in Temporal Dead Zone)  |
| **Redeclaration**| Allowed                     | Not allowed                 | Not allowed                      |
| **Reassignment** | Allowed                     | Allowed                     | Not allowed (except for object/array properties) |
| **Use Case**     | Legacy code                 | Variables that change value | Constants or immutable variables |

Arrays declared using [] : eg : const arr = [1,2,3,4]

Objects are declared inside {} : eg : const user = {name:"harshit", age:12} and to get it user["name"] = harshit 
Note : user.name = harshit , also works

array of objects: [{}]
const user = [
  {
    name:"harshit", 
    age:12
  }
]

get : user[0]["age"] = 12




FUNCTIONS IN JS

=> Function Declaration (Function Statement)
function sum(a,b){
  return a+b;
}
const value = sum(1,2)

=> Anonymous Function Expression
const greet = function(name) {
  return `Hello, ${name}!`;
};

=> Named Function Expression
const greet = function greet(name) {
  return `Hello, ${name}!`;
};

=> Arrow Function (ES6)
Arrow functions provide a more concise syntax, especially around "this".

const greet = (name) => {
  return `Hello, ${name}!`;
};

If the function has a single expression, you can omit the {} and return statement.
const greet = (name) => `Hello, ${name}!`;

const sayHello = () => 'Hello!'; //no parameters

const add = (a, b) => a + b;

=> Anonymous Arrow Function in Event Listeners
button.addEventListener('click', (event) => {
  console.log('Button clicked');
});


=> Async Function (ES7/ES8)
An async function is a function that always returns a promise. 
Inside an async function, you can use await to wait for promises to resolve.

async function fetchData(){
  const response = await fetch('https://api.example.com');
  const data = await response.json();
  return data;
}

=> Arrow Function with async
const fetchData = async () => {
  const response = await fetch('https://api.example.com');
  const data = await response.json();
  return data;
};


CALLBACK
CALLBACK => Is passing another function as an argument in a function.

In given code write such that you use only one function to print data

```
function sum(num1, num2) {
let result = num1 num2;
  return result;
}
function displayResult(data) {
  console.log("Result of the sum is : + data);
}
```

We can easily use two function to print result
const res = sum(1,2);
displayResult(res)

But now to use one function:
In the main code we can update:

function sum(n1, n2, anotherfunc){
  let res = n1+n2;
  return anotherfunc(res);
}

sum(1,2,displayResult); //this is callback

CALLBACK ===> passing another functiona as an argument in a function. 
A function is going to callback a different function => inside sum function I called a displayResult function



SETTIMEOUT
setTimeout(sum, 1*1000);
It means after 1000s the sum function will be called.

So, setTimout is also a callback becuase it passes a function as an argument.


SETINTERVAL
setInterval(sum, 1*1000);
This will call the sum function after every intervl of 1000s.
This also a callback function.






ASYNC FUNCTION


SYNCHRONOUS -> together, one after the other, sequential, only one thing is happening at a time
ASYNCHRONOUS -> opposite of synchronous, happens in parts, happens in parallel, multiple things are context switching with each other

In synchronous -> first boil milk, once boiled,  then put sugar, then put tea leaves -> doing one after another -> one thing at a time

In asynchronous -> boil milk and inside put sugar and tea leaves