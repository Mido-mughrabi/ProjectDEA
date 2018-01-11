package model.execGraph;

public class Node
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
