package splot2HLVL;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

import utils.ParsingParameters;

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

}
