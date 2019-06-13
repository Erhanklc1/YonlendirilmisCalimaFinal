package spark.template.freemarker.erhankilic.finalproje.birim;

public class User {
	
	
	private String userName;
    private String userType;
    private Integer age;
    private String gender;
    private String password;

    public User(String userName, String userType, Integer old, String gender, String password) {
        this.userName = userName;
        this.userType = userType;
        this.age = age;
        this.gender = gender;
        this.password = password;
    }
    
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}

