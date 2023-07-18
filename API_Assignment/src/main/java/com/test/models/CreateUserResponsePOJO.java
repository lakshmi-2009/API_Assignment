package com.test.models;

public class CreateUserResponsePOJO {

	class Data{
		private String name;
		private String salary;
		private String age;
		private int id;
	 }
	
	class Response{
		
		private String status;
		private Data data;
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Data getData() {
			return data;
		}
		public void setData(Data data) {
			this.data = data;
		}
	}
	
}
