package spring.mvc.session13.repository;

import java.util.List;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import spring.mvc.session13.entity.Job;
@Repository
public class JobDaoImpl implements JobDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int add(Job job) {
		String sql ="insert into Job(jname , eid) value(?,?)";
		return jdbcTemplate.update(sql , job.getJname() , job.getEid());
	}

	@Override
	public int update(Job job) {
		String sql = "update job set jname=?, eid=? where jid = ?";
		return jdbcTemplate.update(sql , job.getJname() , job.getEid() , job.getJid());
	}

	@Override
	public int delete(Integer jid) {
		String sql = "delete form job where jid=?";
		return jdbcTemplate.update(sql,jid);
	}

	@Override
	public int getCount() {
		String sql ="select count(*) from job";
		return jdbcTemplate.queryForObject(sql,Integer.class);
	}

	@Override
	public Job get(Integer jid) {
		String sql ="select jid , jname , eid from job where jid=?";
		Object[] args = {jid};
		return jdbcTemplate.queryForObject(sql , args, new BeanPropertyRowMapper<Job>(Job.class));
	}

	@Override
	public List<Job> query() {
		String sql ="select "
				+ "j.jid , j.jname , j.eid , "
				+ "e.eid as employee_eid, e.ename as employee_ename, e.salary as employee_salary, "
				+ "e.createtime as employee_createtime "
				+ "from job j left join employee e on j.eid = e.eid ";
		ResultSetExtractor<List<Job>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance().addKeys("jid").newResultSetExtractor(Job.class);
		return jdbcTemplate.query(sql, resultSetExtractor);
	}

	@Override
	public List<Job> query(Object httpSessionValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Job> queryPage(int offset) {
		// TODO Auto-generated method stub
		return null;
	}

}
