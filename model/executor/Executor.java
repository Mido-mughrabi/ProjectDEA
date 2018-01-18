package model.executor;

import java.util.HashSet;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import model.execGraph.ExecGraph;

public class Executor
{
	public static ExecGraph g;
	public static HashSet<String> segma;
	
	public Executor(ExecGraph g, HashSet<String> segma)
	{
		super();
		this.g = g;
		this.segma = segma;
	}

	public static void execute()
	{
		g.reset();
		Scanner s = new Scanner(System.in);
		System.out.println(g.pointer);
		while (s.hasNext())
		{
			String input = s.next();
			List<String> inputs = splitInput(input);

			if(!belongToLanguage(inputs))
			{
				System.out.println("not accepted input !");
				System.exit(0);
			}

			g.makeMoves(inputs);
			System.out.println(g.pointer + " ");
			if(g.pointer.isEnd())
			{
				System.out.println("isEnd");
			}
		}
	}

	public static List splitInput(String input)
	{
		Iterator<String> iter = segma.iterator();
		int longestWord = 0;
		while(iter.hasNext()) //get the longestWord
		{
			String s = iter.next();
			if(s.length() > longestWord)
			{
				longestWord = s.length();
			}
		}
		List<String> inputs = new ArrayList<String>();
		System.out.println(longestWord);
		for(int i=0, j=0, k=0; i<input.length(); j++)
		{ 
			if(input.length() < longestWord
			|| longestWord - j > input.length() - i)
			{
				if(segma.contains(input.substring(i,input.length())))
				{
					inputs.add(input.substring(i,input.length()));
					k++;
				}
				break;
			}
			if(longestWord == j)
			{
				break;
			}
			if(segma.contains(input.substring(i,longestWord+i-j)))
			{
				inputs.add(input.substring(i,longestWord+i-j));
				i += inputs.get(k).length();
				j=-1;
				k++;
			}
		}
		return inputs;
	}

	public static boolean belongToLanguage(List inputs)
	{
		Iterator<String> iter = inputs.iterator();
		if(!iter.hasNext())
		{
			return false;
		}
		while(iter.hasNext())
		{
			String s = iter.next();
			//System.out.println("\"" +s+ "\" is accepted");
			if(!segma.contains(s))
			{
				return false;
			}
		}
		return true;
	}
}
