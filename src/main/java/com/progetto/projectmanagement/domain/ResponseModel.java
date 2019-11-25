package com.progetto.projectmanagement.domain;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class ResponseModel<T> {

	private int status;
	private int recordCount;
	private T  content;
	private List<?> contents;
	private String error;
	private String result;
	private String systemError;

	public ResponseModel() {
	}



	public ResponseModel(String result,int status, T content, List<?> contents, String error, String systemError) {
		this.result =result;
		this.status = status;
		this.content = content;
		this.contents = contents;
		this.error = error;
		this.systemError = systemError;
	}

	public ResponseModel(String result,int status,T content,String error, String systemError) {
		this.result = result;
		this.status = status;
		this.content = content;
		this.error = error;
		this.systemError = systemError;
	}

	public ResponseModel(String result,int status,List<T> contents,int recordCount,String error, String systemError) {
		this.result = result;
		this.status = status;
		this.contents = contents;
		this.error = error;
		this.systemError = systemError;
		this.recordCount = recordCount;
	}



	public String getResult() {
		return result;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public void setResult(String result) {
		this.result = result;
	}
	public int getStatus() {
		return this.status;
	}

	/**
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	public T  getContent() {
		return this.content;
	}

	/**
	 * 
	 * @param content
	 */
	public void setContent(T content) {
		this.content = content;
	}

	public List<?> getContents() {
		return this.contents;
	}

	/**
	 * @param contents
	 */
	public void setContents(List<?> contents) {
		this.contents = contents;
	}

	public String getError() {
		return this.error;
	}

	/**
	 * 
	 * @param error
	 */
	public void setError(String error) {
		this.error = error;
	}

	public String getSystemError() {
		return this.systemError;
	}

	/**
	 * 
	 * @param systemError
	 */
	public void setSystemError(String systemError) {
		this.systemError = systemError;
	}


	public String getStringUTF8(String value)  {

		try {
			return new String(value.getBytes("UTF-8"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}



}