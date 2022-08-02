package spring.mvc.session06.entity;

public class Job {
	private Integer jid;
	private String jname;
	private Integer eid;

	// 關聯關系
	private Emp emp; // 一個 job 對應 0 或 1 個 emp

	public Job() {

	}

	public Job(String jname) {
		this.jname = jname;
	}

	public Integer getJid() {
		return jid;
	}

	public void setJid(Integer jid) {
		this.jid = jid;
	}

	public String getJname() {
		return jname;
	}

	public void setJname(String jname) {
		this.jname = jname;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	@Override
	public String toString() {
		return "Job [jid=" + jid + ", jname=" + jname + ", eid=" + eid + ", emp=" + emp + "]";
	}
}
