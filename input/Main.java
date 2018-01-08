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
			String[] inputs = splitInput(input);
			if(!belongToLanguage(inputs))
			{
				System.out.println("not accepted input !");
				System.exit(0);
			}

			g.makeMove(inputs);
			System.out.println(g.pointer + " " + g.pointer.isEnd());
			if(g.pointer.isEnd())
			{
				System.out.println("isEnd");
			}
		}
	}

	public static String[] splitInput(String input)
	{ // assume that all words in alphabet have the same length
		Iterator<String> it = segma.iterator();
		int wordLength = it.next().length();
		String[] inputs = new String[input.length()/wordLength];

		for(int i=0, j=wordLength, k=0; j<=input.length(); i+= wordLength, j+= wordLength, k++)
		{
			inputs[k] =  input.substring(i,j);
		}
		return inputs;
	}

	public static boolean belongToLanguage(String[] inputs)
	{
		for(String s : inputs)
		{
			if(!segma.contains(s))
			{
				return false;
			}
		}
		return true;
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
