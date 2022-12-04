package com.parallel.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Calendar;
import java.util.Random;

public class Graph {
	private List<Stack<Integer>> lstcks;
	private Stack<Integer> global_stck;
	private int no_of_nodes;//No.of Nodes
	private int[][] vertices;//adjacent Matrix
	
	
	public List<Stack<Integer>> getlstcks() {
		return lstcks;
	}
	public void setlstcks(List<Stack<Integer>> lstcks) {
		this.lstcks = lstcks;
	}

	private boolean[] node_visit;
	private boolean done;
	private int Value_count,t1;
	public Graph(int no_of_nodes,boolean[] node_visit,int processor){
		int i,j;
		i=0;
		this.no_of_nodes = no_of_nodes;
		lstcks = new ArrayList<Stack<Integer>>(processor);
		while(i<processor){
			lstcks.add(new Stack<Integer>());
			i++;
		}
		vertices = new int[no_of_nodes][no_of_nodes];
		this.node_visit = node_visit;
		done = false;
		i=0;
		j = 0;
		global_stck = new Stack<Integer>();
		t1=no_of_nodes-1;
		global_stck.push(t1);
		Value_count = 0;
		while(i<this.no_of_nodes)
		{
			while(j<this.no_of_nodes){
				Random boolnum = new Random();
                boolean edg = boolnum.nextBoolean();
                if(i==j)
                	vertices[i][j]=1;
                else	
                	vertices[i][j]=edg ? 1 : 1-1;
                j++;
			}
			i++;
			j = 0;
		}
	}
	public int get_size(){
		return no_of_nodes;
	}
	
	
	
	public synchronized void push_stck(Stack<Integer> tmp){
		while(!tmp.isEmpty()){
			global_stck.push(tmp.pop());
		}
	}
	public synchronized boolean get_visited(int idx){
		return node_visit[idx];
	}
	
	public boolean isneigh(int node, int neighbour){
		return vertices[node][neighbour]==1 ? true : false;
	}
	
	public synchronized void inc_count(){
		Value_count++;
	}
	
	public boolean done() {
		return done;
	}
	public synchronized void set_visited(int idx, boolean value){
		node_visit[idx] = value;
	}
	
	public synchronized void dfs(){
		while(!done && global_stck.empty()){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int idx = (int)(Thread.currentThread().getId());
		if(!global_stck.isEmpty()){
			boolean popped = false;
			int node = global_stck.pop();
			popped = true;
			while(node_visit[node]){
				if(global_stck.empty()){
					done = true;
					popped = false;
					break;
				}else{
					node = global_stck.pop();
					popped = true;
				}
			}
			if(popped){
				node_visit[node] = true;
				Value_count++;
				boolean flag = false;
				for(int i = 0;i<no_of_nodes;i++){
					if(node==i)continue;
					if(isneigh(node, i) && !node_visit[i] && !flag){
						lstcks.get(idx).push(i);
						flag = true;
					}
					if(isneigh(node, i) && !node_visit[i] && flag){
						global_stck.push(i);
					}
				}
			}
		}
		int i=0;
		if(global_stck.empty())
			done = true;
		if(done && Value_count<no_of_nodes){
			done = false;
			while(i<=no_of_nodes-1){
				if(!node_visit[i])
					global_stck.push(i);
				i++;
			}
		}
		notifyAll();
	}
	
}
