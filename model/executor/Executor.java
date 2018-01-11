package model.executor;

import java.util.HashSet;
import java.util.Scanner;

import model.execGraph.ExecGraph;

public class Executor
{
	public ExecGraph g;
	public HashSet<String> inputs;
	
	public Executor(ExecGraph g, HashSet<String> inputs)
	{
		super();
		this.g = g;
		this.inputs = inputs;
	}

	public void execute()
	{
		g.reset();
		Scanner s = new Scanner(System.in);
		System.out.println(g.pointer);
		while (s.hasNext())
		{
			String input = s.next();
			if(!inputs.contains(input))
			{
				System.out.println("not accepted input !");
				System.exit(0);
			}
			
			g.makeMove(input);
			System.out.println(g.pointer + " " + g.pointer.isEnd());
			if(g.pointer.isEnd())
			{
				System.out.println("isEnd");
			}
		}
	}
}
