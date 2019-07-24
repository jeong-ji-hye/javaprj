package com.newlecture.web.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.entity.Notice;

public interface NoticeDao {
	
	int getCount() throws ClassNotFoundException, SQLException;
	int getCount(String field, String query) throws ClassNotFoundException, SQLException;
	
	List<NoticeView> getList() throws ClassNotFoundException, SQLException;
	List<NoticeView> getList(Integer page) throws ClassNotFoundException, SQLException;
	List<NoticeView> getList(Integer page, String field, String query) throws ClassNotFoundException, SQLException;
	//���� �������̽��� mybatis�� anotation�� �̿��ؼ� �Ʒ��� ���� �ٲ� �� �ִ�. ������ ��õ�����ʴ� ����̴�
	/*	@Select("SELECT * FROM NOTICE_VIEW\r\n" + 
			"      WHERE ${param2} LIKE '%${param3}%'  \r\n" + 
			"      AND NUM BETWEEN 1+(#{param1}-1)*10 and #{param1} * 10")
	List<NoticeView> getList(@Param("page") int page
			, @Param("field") String field
			, @Param("query") String query) throws ClassNotFoundException, SQLException;
	*/
	
	
	//@Select("SELECT * FROM NOTICE WHERE ID = #{id}")
	Notice get(int id);
	Notice getPrev(int id);
	Notice getNext(int id);
	
	int insert(Notice notice) throws ClassNotFoundException, SQLException;
	int delete(int id) throws ClassNotFoundException, SQLException;
	int update(Notice notice) throws ClassNotFoundException, SQLException;
	int getLastId() throws ClassNotFoundException, SQLException;
}
