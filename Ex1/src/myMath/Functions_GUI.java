package myMath;


import java.awt.Color;
import java.awt.Font;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import myMath.StdDraw;
import myMath.ComplexFunction;
import myMath.Range;
import myMath.function;

public class Functions_GUI extends ArrayList<function> implements functions {
	
	@Override
	public void initFromFile(String functionsFile) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(functionsFile));
		for (String line : lines) {
			this.add(new ComplexFunction(line));			
		}
	}


	@Override
	public void saveToFile(String file) throws IOException {
		try(PrintWriter pw = new PrintWriter(new FileWriter(file))){ 
			for (function f : this) {
				pw.println(f.toString());
			}
		}
	}
	

	@Override
	public void drawFunctions(int canvasWidth, int canvasHeight, Range xRange, Range yRange, int resolution) {
		StdDraw.setCanvasSize(canvasWidth, canvasHeight);
		StdDraw.setXscale(xRange.get_min(), xRange.get_max());
		StdDraw.setYscale(yRange.get_min(), yRange.get_max());
		int fixResolution = 2*resolution;
		StdDraw.setPenColor(Color.LIGHT_GRAY);
		for (double xCordinate = xRange.get_min(); xCordinate <= xRange.get_max(); xCordinate++) {
			StdDraw.line(xCordinate, yRange.get_min(), xCordinate, yRange.get_max());
		}
		for (double yCordinate = yRange.get_min(); yCordinate <= yRange.get_max(); yCordinate++) {
			StdDraw.line(xRange.get_min(), yCordinate, xRange.get_max(), yCordinate);
		}
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.005);
		StdDraw.line(xRange.get_min(), 0, xRange.get_max(), 0);
		StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 15));
		for (double xCordinate = xRange.get_min(); xCordinate <= xRange.get_max(); xCordinate++) {
			StdDraw.text(xCordinate - 0.07, -0.07, Double.toString(xCordinate));
		}
		StdDraw.line(0, yRange.get_min(), 0, yRange.get_max());
		for (double yCordinate = yRange.get_min(); yCordinate <= yRange.get_max(); yCordinate++) {
			StdDraw.text(0 - 0.07, yCordinate + 0.07, Double.toString(yCordinate));
		}		
		Color[] Colors = { Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN,
				Color.PINK };
		int functionIndex =0;
		for (function currentFunc: this) {
			double[] xVals = new double[fixResolution + 1];
			double[] yVals = new double[fixResolution + 1];
			for (int i = 0; i <= fixResolution; i++) {
				xVals[i] = xRange.get_min() + (((double) (xRange.get_max() - xRange.get_min())) * ((double)i) / ((double) fixResolution));
				yVals[i] = currentFunc.f(xVals[i]);
			}
			// pick color
			StdDraw.setPenColor(Colors[functionIndex % Colors.length]);
			for (int i = 0; i < fixResolution; i++) {
				StdDraw.line(xVals[i], yVals[i], xVals[i + 1], yVals[i + 1]);
			}			

			System.out.println(functionIndex + ") " + Colors[functionIndex % Colors.length] + " f(x)= " + currentFunc.toString());
			
			functionIndex++;
		}
	}
	
	public void drawFunctions() {
		drawFunctions(1000, 600, new Range(-10,10), new Range(-5,15), 200);	
	}

	@SuppressWarnings("unchecked")
	@Override
	public void drawFunctions(String json_file) {
		try {
			// start parsing the json file
			Reader fileReader = new FileReader(json_file);
			JSONParser jsonParser = new JSONParser();
			JSONObject mainJsonObject = (JSONObject) jsonParser.parse(fileReader);
			JSONArray xRangeObject = (JSONArray) mainJsonObject.get("Range_X");
			JSONArray yRangeObject = (JSONArray) mainJsonObject.get("Range_Y");
			Iterator<Long> xRangeObjectIterator = xRangeObject.iterator();
			Iterator<Long> yRangeObjectIterator = yRangeObject.iterator();
			Range xRange = new Range(xRangeObjectIterator.next(), xRangeObjectIterator.next());
			Range yRange = new Range(yRangeObjectIterator.next(), yRangeObjectIterator.next());	
			int resolution = ((Long) mainJsonObject.get("Resolution")).intValue();
			int width = ((Long) mainJsonObject.get("Width")).intValue();
			int height = ((Long) mainJsonObject.get("Height")).intValue();		
			drawFunctions(width, height, xRange, yRange, resolution);
		} catch(Exception exception) {
			drawFunctions();
		}
	}
}
