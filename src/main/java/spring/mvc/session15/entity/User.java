package spring.mvc.session15.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 資料庫決定序號生成
	private Long id;
	
	@Column(length = 50 , name = "name" , unique = true) // 不可重複
	private String name;
	
	@Column(name= "password")
	private String password;
	
	@Temporal(TemporalType.DATE) // 存到資料庫的格式 (Date 日期 , Time 時間 , TimeStamp 日期 + 時間)
	@DateTimeFormat(pattern = "yyyy-MM-dd") // HTML 表單呈現格式 <input type="date" />
	@JsonFormat(pattern = "yyyy/MM/dd")     // Json 字串呈現格式
	@Column
	private Date birth;
	
	
	public User() {
		
	}
	
	public User(String name, String password, Date birth) {
		this.name = name;
		this.password = password;
		this.birth = birth;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", birth=" + birth + "]";
	}
	
}