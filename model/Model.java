package model;

import java.util.Scanner;

import model.compiler.Compiler;
import model.compiler.Tokeniser;
import model.execGraph.ExecGraph;
import model.executor.Executor;

public class Model
{
	String dea;
	public Compiler compiler;
	public Executor executor;
	
	public Model(String dea)
	{
		this.dea = dea;
		initSymbolTable();
		executor = new Executor(compiler.compile(), compiler.getSegma());
	}

	public void initSymbolTable()
	{
		Tokeniser t = new Tokeniser(dea);
		compiler = new Compiler(t.getSet("segma"),t.getSet("states"),t.getSet("endStates"), t.getVariable("start"), t.getFunction());
	}
	

}
