package spring.mvc.session06.template;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import spring.mvc.session06.entity.Emp;
import spring.mvc.session06.entity.Job;

@Repository
public class EmpDao {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private ComboPooledDataSource dataSource;
	
	// 多筆查詢
	public List<Map<String, Object>> queryAll() {
		String sql = "select eid , ename , age , createtime from emp";
		return jdbcTemplate.queryForList(sql);
	}
	
	public List<Emp> queryAll2() {
		String sql = "select eid , ename , age , createtime from emp";
		RowMapper<Emp> rowMapper = (ResultSet rs, int rowNum)->{
			Emp emp = new Emp();
			emp.setEid(rs.getInt("eid"));
			emp.setAge(rs.getInt("age"));
			emp.setEname(rs.getString("ename"));
			emp.setCreatetime(rs.getDate("createtime"));
			
			String sql2 = "select jid , jname , eid  from job where eid = ?";
			List<Job> job  = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<Job>(Job.class) , emp.getEid());
			emp.setJob(job);
			
			return emp;
		};
		return jdbcTemplate.query(sql, rowMapper);
	}
	
	
	public List<Emp> queryAll3(){
		String sql = "select eid , ename , age , createtime from emp";	
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class));
	}
	
	// 單筆
	public Emp getById(Integer eid) {
		String sql = "select eid , ename , age , createtime from emp where eid=?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Emp>(Emp.class) , eid );
	}
	
	// 單筆新增
	public Integer add(String ename , Integer age) {
		String sql = "insert into emp(ename , age) values(?,?)";
		int rocount = jdbcTemplate.update(sql,ename ,age);
		return rocount;
	}
	
	// 單筆新增 2 Map sql(namedParameterJdbcTemplate)
	public int add2(String ename , Integer age) {
		String sql = "insert into emp(ename, age) values(:ename, :age)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("ename", ename);
		params.addValue("age",   age);
		int rowcount = namedParameterJdbcTemplate.update(sql, params);
		return rowcount; 
	}
	
	// 多筆新增
	public int[] multiAdd(List<Object[]> rows) {
		String sql = "insert into emp(ename, age) values(?, ?)";
		return jdbcTemplate.batchUpdate(sql, rows);
	}
	
	// 多筆新增
	public int [] multiAdd2(List<Emp> emps) {
		String sql = "insert into emp(ename, age) values(?, ?)";
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				// i = index 
				ps.setString(1, emps.get(i).getEname());
				ps.setInt(2, emps.get(i).getAge());
			}
			
			@Override
			public int getBatchSize() {
				
				return emps.size();
			}
		}; 
		return jdbcTemplate.batchUpdate(sql , setter);
	}
	
	public int update(String ename , Integer age , Integer eid) {
		String sql = "update emp set ename=? , age =? where eid=?";
		return jdbcTemplate.update(sql , ename ,age ,eid);
	}
	
	public int delete(Integer eid) {
		String sql = "delete from emp where eid =?";
		return jdbcTemplate.update(sql , eid);
	}
	
	
	public List<Emp> queryEmpAndJob(){
		String sql = "select e.eid , e.ename , e.age , e.createtime , "
				+    "j.jid as job_jid , j.jname as job_jname , j.eid as job_eid "
				+    "from emp e left join job j on j.eid = e.eid";
	    ResultSetExtractor<List<Emp>> resultSetExtractor =
	    		JdbcTemplateMapperFactory
	    		.newInstance()
	    		.addKeys("eid")
	    		.newResultSetExtractor(Emp.class);
	    return jdbcTemplate.query(sql, resultSetExtractor);
	}
	
	public List<Job> queryJobsAndEmp(){
		String sql ="select  j.jid ,j.jname ,j.eid, "
				   +"e.eid as emp_eid , e.ename as emp_ename , e.age as emp_age , e.createtime as emp_createtime "
				   +"from job j left join emp e on e.eid = j.eid ";
		ResultSetExtractor<List<Job>> resultSetExtractor = 
				JdbcTemplateMapperFactory
				.newInstance()
				.addKeys("jid") // 主鍵(primary key)
				.newResultSetExtractor(Job.class);
		return jdbcTemplate.query(sql, resultSetExtractor);
	}
	
}
