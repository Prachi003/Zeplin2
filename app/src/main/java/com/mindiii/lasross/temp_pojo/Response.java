package com.mindiii.lasross.temp_pojo;

public class Response{
	private Product product;
	private String status;

	public void setProduct(Product product){
		this.product = product;
	}

	public Product getProduct(){
		return product;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"product = '" + product + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
