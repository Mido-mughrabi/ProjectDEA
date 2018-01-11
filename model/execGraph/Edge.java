package model.execGraph;

public class Edge
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