# Compiler-project

![image](https://user-images.githubusercontent.com/66208099/228321510-fe7c31ba-4457-492e-903c-8d9eac02c895.png)
<img src="https://user-images.githubusercontent.com/66208099/228321606-a3da4b58-9947-433f-93f2-0b979ea62a56.png" width="100" height="100" />

Build a dynamic code analyzer for Java Language to generate statement and branchescode coverage reports.

## First Task

Write a Java program based on Antlr that takes a java file as an input and outputs amodified intermediate java file (injected code) as demonstrated in the sectionwhere:you add comment in each block of this code indicates the number of this block , itshould look like this :// block number 3

## Our solution 

We defined a class named "TempJavaListener" that extends the "JavaParserBaseListener" class. The purpose of this class is to listen for events in a Java program being parsed by the ANTLR parser, and modify the token stream as necessary.

The class has two instance variables: "rewriter", which is an instance of the ANTLR TokenStreamRewriter class, and "blockNumber", which is an integer used to keep track of the current block number.

Then we used the enterBlock method. This method is called by ANTLR when it encounters a new block in the Java program being parsed. In this method, the blockNumber variable is incremented, and we made modifications to the token stream using the rewriter object by inserting comments indicating the current block number.

## Tree parse 

![image (3)](https://user-images.githubusercontent.com/66208099/228323203-44f13b38-afde-40eb-8ec5-204739290e19.png)
![image (4)](https://user-images.githubusercontent.com/66208099/228323221-1c8d9471-7482-4da9-92c1-1892009dd7c1.png)



## Second Task

Each team has to write a Java program based on Antlr that takes a java file as aninput and outputs a modified intermediate java file (injected code) as demonstratedin the section where :when you run The modified intermediate java file (generated from the previous step)to know which blocks of the code are visited (A file has to be generated after theprogram run that shows which blocks are visited).

the file should look like this:

*block number 1 is visited*

*block number 3 is visited*

## Our solution 

The same as the first task solution but we added more modifications to the token stream using the rewriter object. These modifications include creating a new FileWriter object to write output to a file, writing a message indicating that the current block has been visited, and closing the FileWriter object.


## Tree parse 

![image (5)](https://user-images.githubusercontent.com/66208099/228323419-c1b28be1-5bd9-441a-9cf3-1f1d422028e7.png)
![image (6)](https://user-images.githubusercontent.com/66208099/228323422-031635e6-8ef4-45e1-b207-9622eb998f84.png)


