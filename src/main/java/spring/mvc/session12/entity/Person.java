package spring.mvc.session12.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Person {
	//@NotEmpty(message = "姓名不可以是空值")
	//@Size(min = 2, max = 50, message = "名字範圍必須介於2~50個字之間")
	@NotEmpty(message = "{person.name.notEmpty}")
	@Size(min = 2, max = 50, message = "{person.name.size}")
	private String name; // 姓名
	
	//@NotNull(message = "年齡不可以是空值")
	//@Range(min = 18, max = 99, message = "年齡範圍必須介於18~99歲之間")
	@NotNull(message = "{person.age.notNull}")
	@Range(min = 18, max = 99, message = "{person.age.range}")
	private Integer age; // 年齡
	
	//@NotNull(message = "會員的設定不可以是空值")
	@NotNull(message = "{person.member.notNull}")
	private Boolean member; // 是否是會員
	
	//@NotNull(message = "生日不可以是空值")
	//@Past(message = "生日不可以大於現在的日期")
	@NotNull(message = "{person.birth.notNull}")
	@Past(message = "{person.birth.past}")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth; // 生日

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Boolean getMember() {
		return member;
	}

	public void setMember(Boolean member) {
		this.member = member;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", member=" + member + ", birth=" + birth + "]";
	}
	
	
	
}