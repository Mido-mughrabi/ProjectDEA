package model.compiler;
import java.util.*;
import java.util.regex.*;

public class Tokeniser
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