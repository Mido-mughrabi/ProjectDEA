/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dea;

/**
 *
 * @author baraa
 */
import java.util.*;
import java.io.*;
import java.util.regex.*;
public class Main
{
	
	static HashSet<String> segma, states, endStates;
	static String startState;
	static Hashtable<String, String> function;
	public ExecGraph g = new ExecGraph();

	public String readFile(String path) throws FileNotFoundException
	{
		Scanner s = new Scanner(new File(path));
		String dea = "";
		while(s.hasNextLine())
		{
			dea = dea + s.nextLine() + "\n";
		}
		return dea ;
	}
	
	public void initSymbolTable(String dea)
	{
		Tokeniser t = new Tokeniser(dea);
		segma = t.getSet("segma");
		states = t.getSet("states");
		endStates = t.getSet("endStates");
		startState = t.getVariable("start");
		function = t.getFunction();
	}
	
	public void compile()
	{
		for(String state: states)
		{
			Node stateNode = new Node(state);
			g.addNode(stateNode);
		}
		
		g.setStart(new Node(startState));
		
		for(String key: function.keySet())
		{
			String[] k = key.split(",");
			String start = k[0];
			String input = k[1];
			g.addEdge(new Node(start),new Node(function.get(key)),input);
		}
		
		g.setEnds(endStates);
	}
	
	public String execute(String input){
            g.reset();
            if(!segma.contains(input))return "f";
            g.makeMove(input);
            startState=g.pointer.content;
            g.setStart(new Node(startState));
            return g.pointer.content;
	}
}

class Tokeniser
{
	String dea;
	
	public Tokeniser(String dea)
	{
		this.dea = dea;
	}
	
	public HashSet<String> getSet(String setName)
	{
		Pattern p = Pattern.compile("\\s*(\\w*)\\s*=\\s*\\{(.*)\\}");
		Matcher m = p.matcher(dea);
		HashSet<String> set = new HashSet<>();
		while(m.find())
		{
			if(m.group(1).equals(setName))
			{
				String result = m.group(2);
				for(String s: result.replaceAll("\\s+", "").split(","))
				{
					set.add(s);
				}
			}
		}
		return set;
	}
	
	public String getVariable(String varName)
	{
		Pattern p = Pattern.compile("\\s*(\\w*)\\s*=\\s*(.*)");
		Matcher m = p.matcher(dea);
		while(m.find())
		{
			if(m.group(1).equals(varName))
			{
				return m.group(2);
			}
		}
		return "";
	}
	
	public Hashtable<String, String> getFunction()
	{
		Pattern p = Pattern.compile("\\s*function\\s*=\\s*\\{(.*)\\}");
		Matcher m = p.matcher(dea);
		Hashtable<String, String> function = new Hashtable<>();
		if(m.find())
		{
			String functionTxt = m.group(1);
			Pattern p2 = Pattern.compile("\\((\\w*,\\w*),(\\w*)\\)(\\s*,\\s*)*");
			Matcher m2 = p2.matcher(functionTxt);
			while(m2.find())
			{
				function.put(m2.group(1),m2.group(2));
			}
		}
		return function;
	}
}
