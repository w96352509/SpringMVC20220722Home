package spring.mvc.session13.repository;

import java.sql.ResultSet;
import java.util.List;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.mvc.session13.entity.Employee;
import spring.mvc.session13.entity.Job;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int add(Employee employee) {
		String sql = "insert into employee(ename , salary) value(?,?)";
		return jdbcTemplate.update(sql, employee.getEname(), employee.getSalary());
	}

	@Override
	public int update(Employee employee) {
		String sql = "update employee set ename=? , salary=? where eid=?";
		return jdbcTemplate.update(sql, employee.getEname(), employee.getSalary(), employee.getEid());
	}

	@Override
	public int delete(Integer eid) {
		String sql = "delete from employee where eid=?";
		return jdbcTemplate.update(sql, eid);
	}

	@Override
	public int getCount() {
		String sql = "select count(*) from employee";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public Employee get(Integer eid) {
		String sql = "select eid , ename , salary , createtime from employee where eid=?";
		Object[] args = { eid };
		return jdbcTemplate.queryForObject(sql, args, new BeanPropertyRowMapper<Employee>(Employee.class));
	}

	@Override
	public List<Employee> query() {
		String sql = "select e.eid , e.ename, e.salary, e.createtime , "
				+ "j.jid as job_jid , j.jname as job_jname, j.eid as job_eid "
				+ "from employee e left join job j on e.eid = j.eid ";
		ResultSetExtractor<List<Employee>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance().addKeys("eid")
				.newResultSetExtractor(Employee.class);
		return jdbcTemplate.query(sql, resultSetExtractor);
	}

	@Override
	public List<Employee> query(Object httpSessionValue) {
		if(httpSessionValue == null) {
			return query();
		}
		return queryPage(Integer.parseInt(httpSessionValue + ""));
	}

	@Override
	public List<Employee> queryPage(int offset) {
		String sql = "select e.eid, e.ename, e.salary, e.createtime from employee e order by e.eid ";

		RowMapper<Employee> rm = (ResultSet rs, int rowNum) -> {
			Employee employee = new Employee();
			employee.setEid(rs.getInt("eid"));
			employee.setEname(rs.getString("ename"));
			employee.setSalary(rs.getInt("salary"));
			employee.setCreatetime(rs.getDate("createtime"));
			String sql2 = "select j.jid, j.jname, j.eid from job j where j.eid=?";
			List<Job> jobs = jdbcTemplate.query(sql2, new Object[] { employee.getEid() },
					new BeanPropertyRowMapper<Job>(Job.class));

			employee.setJobs(jobs);
			return employee;
		};
		// 分頁查詢
		if (offset >= 0) {
			sql += String.format(" limit %d offset %d ", LIMIT, offset);
		}
		List<Employee> employees = jdbcTemplate.query(sql, rm);
		return employees;
	}

}
