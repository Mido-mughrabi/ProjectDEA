import java.util.*;
import java.io.*;
import java.util.regex.*;
public class Main
{
	static String dea;
	static HashSet<String> segma, states, endStates;
	static String startState;
	static Hashtable<String, String> function;
	static ExecGraph g = new ExecGraph();
	
	public static void main(String[] args)
	{	
		try
		{
			dea = readFile(args[0]);
		}
		catch(FileNotFoundException e)
		{
			System.out.print("File not found!");
			return ;
		}
		
		initSymbolTable();
		compile();
		execute();
	}
	
	public static String readFile(String path) throws FileNotFoundException
	{
		Scanner s = new Scanner(new File(path));
		String dea = "";
		while(s.hasNextLine())
		{
			dea = dea + s.nextLine() + "\n";
		}
		return dea ;
	}
	
	public static void initSymbolTable()
	{
		Tokeniser t = new Tokeniser(dea);
		segma = t.getSet("segma");
		states = t.getSet("states");
		endStates = t.getSet("endStates");
		startState = t.getVariable("start");
		function = t.getFunction();
	}
	
	public static void compile()
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
	
	public static void execute()
	{
		g.reset();
		Scanner s = new Scanner(System.in);
		System.out.println(g.pointer);
		while (s.hasNext())
		{
			String input = s.next();
			if(!segma.contains(input))
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
