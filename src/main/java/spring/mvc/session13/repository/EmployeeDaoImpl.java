package spring.mvc.session13.repository;

import java.util.List;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import spring.mvc.session13.entity.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int add(Employee employee) {
		String sql = "insert into employee(ename , salary) value(?,?)";
		return jdbcTemplate.update(sql, employee.getEname(),employee.getSalary());
	}

	@Override
	public int update(Employee employee) {
		String sql = "update employee set ename=? , salary=? where eid=?";
		return jdbcTemplate.update(sql, employee.getEname(),employee.getSalary() , employee.getEid());
	}

	@Override
	public int delete(Integer eid) {
		String sql = "delete form employee where eid=?";
		return jdbcTemplate.update(sql , eid);
	}

	@Override
	public int getCount() {
		String sql = "select count(*) from employee";
		return jdbcTemplate.queryForObject(sql,Integer.class);
	}

	@Override
	public Employee get(Integer eid) {
		String sql = "select ename , salary , createtime form employee where eid=?";
		Object[] args = {eid};
		return jdbcTemplate.queryForObject(sql, args , new BeanPropertyRowMapper<Employee>(Employee.class));
	}

	@Override
	public List<Employee> query() {
		String sql ="select e.eid , e.ename, e.salary, e.createtime , "
				  + "j.jid as job_jid , j.jname as job_jname, j.eid as job_eid "
				  + "from employee e left join job j on e.eid = j.eid ";
		ResultSetExtractor<List<Employee>> resultSetExtractor = 
				JdbcTemplateMapperFactory.newInstance()
				                         .addKeys("eid")
				                         .newResultSetExtractor(Employee.class);
		return jdbcTemplate.query(sql, resultSetExtractor) ;
	}

	@Override
	public List<Employee> query(Object httpSessionValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> queryPage(int offset) {
		// TODO Auto-generated method stub
		return null;
	}

}
