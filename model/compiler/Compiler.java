package model.compiler;

import java.util.HashSet;
import java.util.Hashtable;

import model.execGraph.ExecGraph;
import model.execGraph.Node;

public class Compiler
{
	private HashSet<String> segma;
	HashSet<String> states;
	HashSet<String> endStates;
	String startState;
	Hashtable<String, String> function;
	ExecGraph execGraph = new ExecGraph();

	public Compiler(HashSet<String> segma, HashSet<String> states, HashSet<String> endStates,
			String startState, Hashtable<String, String> function)
	{
		super();
		this.setSegma(segma);
		this.states = states;
		this.endStates = endStates;
		this.startState = startState;
		this.function = function;
	}

	public ExecGraph compile()
	{
		for(String state: states)
		{
			Node stateNode = new Node(state);
			execGraph.addNode(stateNode);
		}
		
		execGraph.setStart(new Node(startState));
		
		for(String key: function.keySet())
		{
			String[] k = key.split(",");
			String start = k[0];
			String input = k[1];
			execGraph.addEdge(new Node(start),new Node(function.get(key)),input);
		}
		
		execGraph.setEnds(endStates);
		
		return execGraph;
	}

	public HashSet<String> getSegma()
	{
		return segma;
	}

	public void setSegma(HashSet<String> segma)
	{
		this.segma = segma;
	}
	
}
