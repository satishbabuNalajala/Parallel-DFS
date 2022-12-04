package com.parallel.dfs;

import java.util.Stack;

public class SerialDFS implements Runnable{
	private boolean[] node_visit;
	private Graph gph;
	private Stack<Integer> stck;
	private int bgn_node;
	public SerialDFS(int size,boolean[] node_visit,int bgn_point){
		gph = new Graph(size,node_visit,1);
		this.node_visit = node_visit;
		stck = new Stack<>();
		bgn_node = bgn_point;
	}
	@Override
	public void run() {
		for(int i = 0;i<gph.get_size();i++){
			node_visit[i] = false;
		}
		stck.push(bgn_node);
		while(!stck.isEmpty()){
			int node = stck.pop();
			if(node_visit[node]==false){
				node_visit[node] = true;
				for(int i = 0; i<gph.get_size(); i++){
					if(node==i)continue;
					if(gph.isneigh(node, i) && node_visit[i]==false){
						stck.push(i);
					}
				}
			}
		} 
	}
}
