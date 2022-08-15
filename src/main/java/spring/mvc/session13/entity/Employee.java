package spring.mvc.session13.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class Employee {

	private Integer eid;

	@Size(min = 2 , max = 10 , message = "{employee.ename.size}")
	private String ename;
	
	@NotNull(message = "{employee.salary.empty}")
	@Range(min = 38000 , max = 300000,message = "{employee.salary.range}")
	private Integer salary;
	
	private Date createtime;
	
	private List<Job> jobs ;

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + ", salary=" + salary + ", createtime=" + createtime
				+ ", jobs=" + jobs + "]";
	}

	
	
	
	
}
