package com.luxoft.converter.service.code;

/**
 * Created by pgolovenkov on 09.01.2017.
 */
public class QuestionCodeProvider {

	private static final String NUMBER_FORMAT = "%03d";

	private boolean isConfigured = false;

	private String prefix;
	private int number;

	public boolean configure(String prefix){
		if(prefix != null && !prefix.isEmpty()) {
			this.prefix = prefix;
			this.number = 0;
			this.isConfigured = true;
		}
		return this.isConfigured;
	}

	public String getCode(){
		if(isConfigured) {
			this.number++;
			return this.prefix + String.format(NUMBER_FORMAT, this.number);
		}
		return "";
	}

	public boolean isConfigured(){
		return this.isConfigured;
	}
}
