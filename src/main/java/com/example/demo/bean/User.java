/**  
* <p>Title: User.java</p>  
* <p>Description: </p>  
* @author koko 
* @date 2018年11月2日  
* @version 1.0  
*/ 
package com.example.demo.bean;

/**  
* <p>Title: User</p>  
* <p>Description: </p>  
* @author koko  
* @date 2018年11月2日  
*/
public class User {

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	private int id;
	private String name;
	private String age;
	private String sex;
	
}
