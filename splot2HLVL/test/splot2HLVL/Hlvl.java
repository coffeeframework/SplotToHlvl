package splot2HLVL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;

public class Hlvl {
	private String hlvl;

	public Hlvl(String hlvl, boolean isUrl) {
		if (!isUrl) {
			this.hlvl = hlvl;

		} else {
			readUrl(hlvl);
		}
	}

	private void readUrl(String path) {
		try {
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			StringBuilder sb = new StringBuilder();
			while(line != null) {
				sb.append(line+"\n");
				line = br.readLine();
			}
			hlvl = sb.toString();
			br.close();
		} catch (java.io.IOException ioex) {
			System.out.println("se presento el error: " + ioex.toString());
		}
	}

	public HashSet<String> getElements() {
		HashSet<String> set = new HashSet<String>();
		String[] splittedElements = new String[0];
		String newHlvl = hlvl;
		if (newHlvl.contains("relations:")) {
			newHlvl = newHlvl.split("relations:")[0];
		}
		if (newHlvl.contains("elements:")) {
			newHlvl = newHlvl.split("elements:")[1];
			splittedElements = newHlvl.replaceAll("\\t", "").split("\\n");
		}

		for (String el : splittedElements) {
			el = el.trim();
			if (!el.equals("")) {
				set.add(el);
			}
		}

		return set;
	}

	public HashSet<String> getRelations() {
		HashSet<String> set = new HashSet<String>();
		String[] splittedElements = new String[0];
		String newHlvl = hlvl;
		if (newHlvl.contains("operations:")) {
			newHlvl = newHlvl.split("operations:")[0];
		}
		if (newHlvl.contains("relations:")) {
			newHlvl = newHlvl.split("relations:")[1];
			splittedElements = newHlvl.replaceAll("\\t", "").split("\\n");
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

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Hlvl second = (Hlvl) obj;
		HashSet<String> relations1 = getRelations();
		HashSet<String> relations2 = second.getRelations();
		
		HashSet<String> elements1 = getElements();
		HashSet<String> elements2 = second.getElements();
		
		
		return elements1.equals(elements2) && relations1.equals(relations2);
	}

}
