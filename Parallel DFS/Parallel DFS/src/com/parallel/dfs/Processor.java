package com.parallel.dfs;

import java.util.Stack;

public class Processor extends Thread  {
	
	private int num;
	private Graph graph;
	public Processor(Graph g,int num){
		this.num = num;
		setName("Processor "+num);
		graph = g;
	}
	
	
	@Override
	public long getId() {
		return num;
	}

	@Override
	public void run() {
		while(!graph.done()){
			graph.dfs();
			
			sub_Dfs(graph.getlstcks().get(num));
		}
	}
	
	public void sub_Dfs(Stack<Integer> lstck){
		Stack<Integer> tmpstck = new Stack<Integer>();
		while(!lstck.isEmpty()){
			int node = lstck.pop();
			if(!graph.get_visited(node)){
				graph.set_visited(node,true);
				graph.inc_count();
				boolean flg = true;
				for(int i = 0; i<graph.get_size(); i++){
					if(node==i)continue;
					if(graph.isneigh(node, i) && flg && !graph.get_visited(i)){
						lstck.push(i);
						flg = false;
					}
				}
			}
		}
		graph.push_stck(tmpstck);
	}
}
