/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author baraa
 */
import java.util.*;

public class ExecGraph
{
	HashSet<Node> nodes = new HashSet<>();
	HashSet<Edge> edges = new HashSet<>();
	Node start;
	Node pointer;
	
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
		
	}
	
	public void reset()
	{
		pointer = start;
	}
}

class Node
{
	String content;
	boolean isEnd = false;
	
	public Node(String content)
	{
		this.content = content;
	}
	
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (content != null?content.hashCode():0);
		return result;
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null || !(obj instanceof Node))
		{
			return false;
		}
		Node nodeObj = (Node) obj;
		if(!this.content.equals(nodeObj.content))
		{
			return false;
		}
		return true;
	}
	
	public void setEnd(boolean isEnd)
	{
		this.isEnd = isEnd;
	}
	
	public boolean isEnd()
	{
		return isEnd;
	}
	
	public String toString()
	{
		return content ;
	}
}

class Edge
{
	Node start, end;
	String name;
	
	public Edge(Node start, Node end,String name)
	{
		this.start = start;
		this.end = end;
		this.name = name;
	}
	
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + name.hashCode();
		return result;
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null || !(obj instanceof Edge))
		{
			return false;
		}
		Edge edgeObj = (Edge) obj;
		if(!this.start.equals(edgeObj.start) || !this.end.equals(edgeObj.end) || !this.name.equals(edgeObj.name))
		{
			return false;
		}
		return true;
	}
}
