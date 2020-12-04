package ams2.linguo.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LanguageParser {

	private List<String> languageList;

	public LanguageParser() {
		parseLanguageFile();
	}

	public List<String> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<String> languageList) {
		this.languageList = languageList;
	}

	public void parseLanguageFile() {
		try {
			// JSON parsing objects
			FileReader file = new FileReader("languages.json");
			JSONParser parser = new JSONParser();
            Object object = parser.parse(file);
            JSONObject jsonObject = (JSONObject)object;
            
	        // Key objects
            JSONObject results = (JSONObject)jsonObject.get("results");
            JSONArray bindings = (JSONArray)results.get("bindings");
            
            // Initialize languageList
            languageList = new ArrayList<String>();
            
            // Loop through every binding
            for (int i = 0; i < bindings.size(); i++) {
            	JSONObject objects = (JSONObject) bindings.get(i);
            	JSONObject idioma = (JSONObject)objects.get("idioma");
            	String value = (String)idioma.get("value");
            	value = value.substring(7).substring(0, 1).toUpperCase() + value.substring(8);
            	languageList.add(value);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
