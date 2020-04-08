package splot2HLVL;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.Test;

import com.github.coffeeframework.utils.ParsingParameters;

public class TestSplot2HLVL {

	@Test
	public void testParseGraph() {
		testParse("test-data/SplotFiles/Splot_GLP.xml", "test-data/HLVLFiles/graph.hlvl", 0);
	}

	@Test
	public void testParsePhone() {
		testParse("test-data/SplotFiles/MobilePhone.xml", "test-data/HLVLFiles/mobile_phone.hlvl", 1);
	}
	
	@Test
	public void testParseComputador() {
		testParse("test-data/SplotFiles/Computador.xml", "test-data/HLVLFiles/computador.hlvl", 2);
	}
	
	@Test
	public void testParseSmartHome() {
		testParse("test-data/SplotFiles/SmartHome.xml", "test-data/HLVLFiles/smart_home.hlvl", 3);
	}

	private void testParse(String splotPath, String originalHLVLPath, int i) {

		String HLVLPath = "test-data/HLVLFiles/";
		ParsingParameters params = new ParsingParameters();

		params.setInputPath(splotPath);
		params.setOutputPath(HLVLPath);
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

		Hlvl originalHlvl = new Hlvl(originalHLVLPath, true);
		Hlvl parsedHlvl = new Hlvl(parsedProgram, false);

		assertEquals(originalHlvl, parsedHlvl);

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
