package com.parallel.dfs;

import java.util.Calendar;

public class Main_pdfs {

	public static void main(String[] args) {
		long st, fin;
		int k = 0;
		final int  nodes = 5000, cores = 4;
		boolean[] node_visited = new boolean[nodes];
		while(k<nodes){
			node_visited[k] = false;
			k++;
		}
		Graph g = new Graph(nodes,node_visited,cores);
		st = Calendar.getInstance().getTimeInMillis();
		k=0;
		Thread[] proc = new Processor[cores];
		while(k<cores)
		{
			proc[k] = new Processor(g,k);
			proc[k].start();
			k++;
		}k=0;
		while(k<cores){
			try {
				proc[k].join();
			} catch (InterruptedException obj) {
				obj.printStackTrace();
			}
			k++;
		}
		fin = Calendar.getInstance().getTimeInMillis();
		System.out.println("Parallel Time:"+(fin-st)); 
		k=0;
		boolean flag = true;
		while(k<nodes){
			if(!node_visited[k]){
				flag = false;
				System.out.println("Failed");
				break;
			}
			k++;
		}
		k=0;
		if(flag)
			System.out.println("Successful ");

		while(k<nodes){
			node_visited[k] = false;
			k++;
		}
		st = Calendar.getInstance().getTimeInMillis();
		SerialDFS serialDFS = new SerialDFS(nodes, node_visited, nodes-1);
		Thread s = new Thread(serialDFS);
		s.start();
		try {
			s.join();
		} catch (InterruptedException obj) {
			obj.printStackTrace();
		}
		fin = Calendar.getInstance().getTimeInMillis();
		k=0;
		System.out.println("Serial Time:"+(fin-st)); 
		flag = true;
		while(k<nodes){
			if(!node_visited[k]){
				flag = false;
				System.out.println("Failed");
				break;
			}
			k++;
		}
		if(flag)
			System.out.println("Successful ");
	}

}
