# Parallel-DFS

Introduction
In general Depth-First-Search is an popular Artificial Intelligence related algorithm used for solve wide range of problems in decision-making and intelligent systems. Backtracking refers to the process of resolving various constraint and computational complexity problems. program execution may be compared to a proof tree's depth-first search. DFS methods with iterative-deepening are used to prove theorems and solve discrete optimization issues. The depth-first search method's low memory requirements are a significant benefit. Since many of the problems that DFS addresses need a lot of compute, developing parallel depth-first search algorithms has garnered a lot of interest.

Here in our algorithm, we have used the threading concept instead of cores and we have analyzed the situation of time comparison of normal DFS and PDFS using Java.
Execution:
	We have to run our program in Java Environment
Steps:
Open the project repository in eclipse or in any java environment.
Open Main_pdfs.java file
Change the node, number of processors as our wish in code.
We also can enter source in our code.
Run the Main_pdfs.java file

Input:
A 5000 node random graph is produced, which passes variables for the number of cores, for the number of simultaneous parallel tasks to be performed and number of nodes, Source node and destination are input parameters are hardcoded.





Output:
The result shows the path from the source node to the destination node and also shows time taken by both Parallel and Sequential DFS to process the given Input.



![image](https://user-images.githubusercontent.com/101539639/205505363-ed13e32b-748a-4c6d-83db-c37bd1f69ef4.png)
