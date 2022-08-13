package spring.mvc.session13.repository;

import java.util.List;

import spring.mvc.session13.entity.Job;

public interface JobDao {

	// 每頁筆數
		int LIMIT = 5;

		// 新增
		int add(Job job);

		// 修改
		int update(Job job);

		// 刪除
		int delete(Integer jid);

		// 查詢所有筆數
		int getCount();

		// 單筆
		Job get(Integer jid);

		// 不分頁查詢全部
		List<Job> query();

		// 分頁查詢(判斷 HttpSession 值決定分頁狀態)
		List<Job> query(Object httpSessionValue);

		// 分頁查詢
		List<Job> queryPage(int offset);
	
}
