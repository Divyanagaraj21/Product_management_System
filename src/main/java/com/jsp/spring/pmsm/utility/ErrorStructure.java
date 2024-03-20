	package com.jsp.spring.pmsm.utility;
	
	import org.springframework.stereotype.Component;
	
	@Component
	public class ErrorStructure<T> {
		private int errorStatuscode;
		private String errorMessage;
		private T errorData;
		public int getErrorStatuscode() {
			return errorStatuscode;
		}
		public ErrorStructure<T> setErrorStatuscode(int errorStatuscode) {
			this.errorStatuscode = errorStatuscode;
			return this;
		}
		public String getErrorMessage() {
			return errorMessage;
		}
		public ErrorStructure<T> setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
			return this;
		}
		public T getErrorData() {
			return errorData;
		}
		public ErrorStructure<T> setErrorData(T errorData) {
			this.errorData = errorData;
			return this;
		} 
	
	
	}
