package splot2HLVL;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.Test;

import com.github.coffeeframework.utils.ParsingParameters;

public class TestSplot2HLVL {

	private HashSet<String> getElements(String hlvl) {
		HashSet<String> set = new HashSet<String>();
		String[] splittedElements = new String[0];
		if (hlvl.contains("relations:")) {
			hlvl = hlvl.split("relations:")[0];
		}
		if (hlvl.contains("elements:")) {
			hlvl = hlvl.split("elements:")[1];
			splittedElements = hlvl.replaceAll("\\t", "").split("\\n");
		}

		for (String el : splittedElements) {
			el = el.trim();
			if (!el.equals("")) {
				set.add(el);
			}
		}

		return set;
	}

	private HashSet<String> getRelations(String hlvl) {
		HashSet<String> set = new HashSet<String>();
		String[] splittedElements = new String[0];
		if (hlvl.contains("operations:")) {
			hlvl = hlvl.split("operations:")[0];
		}
		if (hlvl.contains("relations:")) {
			hlvl = hlvl.split("relations:")[1];
			splittedElements = hlvl.replaceAll("\\t", "").split("\\n");
		}

		for (String el : splittedElements) {
			el = el.trim();
			if (!el.equals("")) {
				el = el.split(":")[1];
				el = el.trim();
				set.add(el);
			}
		}

		return set;
	}

	@Test
	public void testParse() {

		String program = "model  test0_generated\n" + "elements: \n" + "\tboolean GPL\n" + "\tboolean Gtp\n"
				+ "\tboolean directed\n" + "\tboolean undirected\n" + "\tboolean Weight\n" + "\tboolean weighted\n"
				+ "\tboolean unweighted\n" + "\tboolean Search\n" + "\tboolean BFS\n" + "\tboolean DFS\n"
				+ "\tboolean Algorithms\n" + "\tboolean connected\n" + "\tboolean stronglyc\n" + "\tboolean cycle\n"
				+ "\tboolean mstprim\n" + "\tboolean mstkruskal\n" + "\tboolean shortest\n" + "relations:\n"
				+ "\tr0: common(GPL)\n" + 
				"\tr1:decomposition(GPL,[Gtp],[1,1])\n" + 
				"\tr2:group(Gtp,[directed, undirected],[1,1])\n" + 
				"\tr3:decomposition(GPL,[Weight],[0,1])\n" + 
				"\tr4:group(Weight,[weighted, unweighted],[1,1])\n" + 
				"\tr5:decomposition(GPL,[Search],[0,1])\n" + 
				"\tr6:group(Search,[BFS, DFS],[1,1])\n" + 
				"\tr7:decomposition(GPL,[Algorithms],[1,1])\n" + 
				"\tr8:group(Algorithms,[connected, stronglyc, cycle, mstprim, mstkruskal, shortest],[1,*])\n" + 
				"\tr9:expression(~mstprim OR unweighted )\n" + 
				"\tr10:expression(~stronglyc OR ~shortest )\n" + 
				"\tr11:expression(~mstprim OR undirected )\n" + 
				"\tr12:expression(~shortest OR directed )\n" + 
				"\tr13:expression(~mstprim OR ~mstkruskal )\n" + 
				"\tr14:expression(~mstkruskal OR unweighted )\n" + 
				"\tr15:expression(~stronglyc OR undirected )\n" + 
				"\tr16:expression(~connected OR ~stronglyc )\n" + 
				"\tr17:expression(~connected OR Search )\n" + 
				"\tr18:expression(~mstkruskal OR undirected )\n" + 
				"\tr19:expression(~mstkruskal OR ~shortest )\n" + 
				"\tr20:expression(~mstprim OR ~shortest )\n" + 
				"\tr21:expression(~cycle OR DFS )";

		HashSet<String> elementsA = getElements(program);
		HashSet<String> relationsA = getRelations(program);

		// for all files in the folder
		// First we create a parameters object
		int i = 0;
		ParsingParameters params = new ParsingParameters();

		params.setInputPath("test-data/SplotFiles/Splot_GLP.xml");
		params.setOutputPath("test-data/HLVLFiles/");
		params.setTargetName("test" + i);

		// Now we create the parser object
		Splot2HlvlParser parser = new Splot2HlvlParser(params);

		try {
			parser.parse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String parsedProgram = parser.getProgram();

		HashSet<String> elementsParsed = getElements(parsedProgram);
		HashSet<String> relationsParsed = getRelations(parsedProgram);

		assertEquals(elementsA, elementsParsed);
		assertEquals(relationsA, relationsParsed);

	}
	
	@Test
	public void testParseString() {
		String data = "<feature_model name=\"Graph Product Line\">\r\n" + 
				"<meta>\r\n" + 
				"<data name=\"description\">A simple graph product line</data>\r\n" + 
				"<data name=\"creator\">Don Batory</data>\r\n" + 
				"<data name=\"address\"></data>\r\n" + 
				"<data name=\"email\">batory@cs.utexas.edu</data>\r\n" + 
				"<data name=\"phone\"></data>\r\n" + 
				"<data name=\"website\">http://www.cs.utexas.edu/~dsb/</data>\r\n" + 
				"<data name=\"organization\">University of Texas at Austin</data>\r\n" + 
				"<data name=\"department\"></data>\r\n" + 
				"<data name=\"date\"></data>\r\n" + 
				"<data name=\"reference\">Don S. Batory. Feature models, grammars, and propositional formulas. In Software Product Lines, 9th International Conference, SPLC 2005, Rennes, France, September 26-29, 2005, Proceedings, volume 3714 of Lecture Notes in Computer Science, pages 7-20. Springer, 2005</data>\r\n" + 
				"</meta>\r\n" + 
				"<feature_tree>\r\n" + 
				":r GPL(_r)\r\n" + 
				"	:m Gtp(_r_3)\r\n" + 
				"		:g (_r_3_4) [1,1] \r\n" + 
				"			: directed(_r_3_4_5)\r\n" + 
				"			: undirected(_r_3_4_6)\r\n" + 
				"	:o Weight(_r_10)\r\n" + 
				"		:g (_r_10_11) [1,1] \r\n" + 
				"			: weighted(_r_10_11_12)\r\n" + 
				"			: unweighted(_r_10_11_13)\r\n" + 
				"	:o Search(_r_15)\r\n" + 
				"		:g (_r_15_16) [1,1] \r\n" + 
				"			: BFS(_r_15_16_17)\r\n" + 
				"			: DFS(_r_15_16_18)\r\n" + 
				"	:m Algorithms(_r_19)\r\n" + 
				"		:g (_r_19_20) [1,*] \r\n" + 
				"			: connected(_r_19_20_22)\r\n" + 
				"			: stronglyc(_r_19_20_23)\r\n" + 
				"			: cycle(_r_19_20_24)\r\n" + 
				"			: mstprim(_r_19_20_25)\r\n" + 
				"			: mstkruskal(_r_19_20_26)\r\n" + 
				"			: shortest(_r_19_20_27)\r\n" + 
				"</feature_tree>\r\n" + 
				"<constraints>\r\n" + 
				"constraint11:_r_10_11_13 or ~_r_19_20_25\r\n" + 
				"constraint_17:~_r_19_20_23 or ~_r_19_20_27\r\n" + 
				"constraint10:_r_3_4_6 or ~_r_19_20_25\r\n" + 
				"constraint13:_r_3_4_5 or ~_r_19_20_27\r\n" + 
				"constraint12:~_r_19_20_25 or ~_r_19_20_26\r\n" + 
				"constraint9:_r_10_11_13 or ~_r_19_20_26\r\n" + 
				"constraint6:_r_3_4_6 or ~_r_19_20_23\r\n" + 
				"constraint_13:~_r_19_20_22 or ~_r_19_20_23\r\n" + 
				"constraint5:_r_15 or ~_r_19_20_22\r\n" + 
				"constraint8:_r_3_4_6 or ~_r_19_20_26\r\n" + 
				"constraint_15:~_r_19_20_26 or ~_r_19_20_27\r\n" + 
				"constraint_16:~_r_19_20_25 or ~_r_19_20_27\r\n" + 
				"constraint7:_r_15_16_18 or ~_r_19_20_24\r\n" + 
				"</constraints>\r\n" + 
				"</feature_model>\r\n" + 
				"";
		Splot2HlvlParser parser = new Splot2HlvlParser();
		try {
			System.out.println(parser.parse(data));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
