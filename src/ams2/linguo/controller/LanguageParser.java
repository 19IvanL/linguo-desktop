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

	private List<Language> languageList;
	
	public LanguageParser() {
		parseLanguageFile();
	}

	public List<Language> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<Language> languageList) {
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
            languageList = new ArrayList<Language>();
            
            // Loop through every binding and save values
            for (int i = 0; i < bindings.size(); i++) {
            	JSONObject objects = (JSONObject)bindings.get(i);
            	
            	Language language = new Language();
            	
            	JSONObject languageName = (JSONObject)objects.get("languageName");
            	String languageNameValue = (String)languageName.get("value");
            	language.setName(languageNameValue);
            	
            	JSONObject isoCode = (JSONObject)objects.get("isoCode");
            	String isoCodeValue = (String)isoCode.get("value");
            	language.setIsoCode(isoCodeValue);
            	
            	languageList.add(language);
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
