package model.execGraph;
import java.util.*;

public class ExecGraph
{
	HashSet<Node> nodes = new HashSet<>();
	HashSet<Edge> edges = new HashSet<>();
	Node start;
	public Node pointer;	//to do: need to adjust visibility 
	
	public void addEdge(Node n1, Node n2, String name)
	{
		nodes.add(n1);
		nodes.add(n2);
		edges.add(new Edge(n1,n2,name));
	}
	
	public void addNode(Node n)
	{
		nodes.add(n);
	}
	
	public void setStart(Node n)
	{
		if(!nodes.contains(n))
		{
			nodes.add(n);
		}
		
		start = n;
	}
	
	public void setEnds(HashSet<String> ends)
	{
		for(Node n: nodes)
		{
			for(String s: ends)
			{
				if(n.content.equals(s))
				{
					n.setEnd(true);
				}
			}
		}
		
		//toDo: not an optimal solution 
		for(Edge e: edges)
		{
			for(String s: ends)
			{
				if(e.end.content.equals(s))
				{
					e.end.setEnd(true);
				}
			}
		}
	}
	
	public Node nextMove(Node start, String input)
	{
		for(Edge e: edges)
		{
			if(e.start.equals(start) && e.name.equals(input))
			{
				return e.end;
			}
		}
		return null;
	}
	
	public void makeMove(String input)
	{
		Node next = nextMove(pointer,input);
		if (next != null)
		{
			pointer = next;
		}
		else
		{
			System.out.println("can not make this move !");
			System.exit(0);
		}
	}
	
	public void reset()
	{
		pointer = start;
	}
	
	public String toString()
	{
		String s = "" ;
		for(Node n : nodes)
		{
			s = s + " " + n.content + " " + n.isEnd + "\n" ;
		}
		return s ;
	}
}

