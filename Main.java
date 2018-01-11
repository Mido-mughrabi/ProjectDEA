import java.util.*;
import java.io.*;
import model.Model;

public class Main
{
	static Model model;
	
	public static void main(String[] args)
	{	
		if(args.length != 0)
		{
			try
			{
				model = new Model(readFile(args[0]));
			}
			catch(FileNotFoundException e)
			{
				System.out.print("File not found!");
				return ;
			}
			model.executor.execute();
		}

	}
	
	//toDo: move to class that wil read and write from files(r\w execGraphs, r DEAs)
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
}
