package com.newlecture.web.controller.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

@Controller("adminNoticeController")
@RequestMapping("/admin/notice/")
public class NoticeController {
	
	@Autowired
	//@Qualifier("mybatisNoticeDao")
	private NoticeDao noticeDao;
	@RequestMapping("del")																						
	public String delete(Integer id) throws ClassNotFoundException, SQLException {
		Notice notice = noticeDao.get(id);
		
		noticeDao.delete(id);
		
		return "redirect:list";
		
	}
	
	@RequestMapping("detail")
	public String detail(Integer id, Model model) {
		Notice notice = noticeDao.get(id);
		
		model.addAttribute(notice);
		return "admin/notice/detail";
	}
//	@Autowired
//	private SqlSessionTemplate sqlSession;
//	@RequestMapping("list")
//	public String list(Model model
//			,@RequestParam(name="p", defaultValue="1") Integer page) throws ClassNotFoundException, SQLException {
//		
//		model.addAttribute("list", noticeDao.getList(page));
//		return "admin/notice/list";
//	} 
	
//	@Autowired
//	public void setSqlSession(SqlSessionTemplate sqlSession) {
//		this.sqlSession = sqlSession;
//	}

	@RequestMapping("list")
	public String list(Model model) throws ClassNotFoundException, SQLException  {
		//1.���ǵ����� �����ؼ� Sqlsession sqlsession=?;
		//���ǰ�ü�� ���ؼ� ���۸� �� ���ϴ� �޼ҵ带 ȣ���Ѵ�
		
		//2. ���ǵ����� ��� �۾��� �������� ���ø����� �����ϰ� 
		//���⼭�� �׳� �� ���ǵ����� �� ȣ���ϴ� ����� ����
		
		//List<NoticeView> list = sqlSession.getMapper(NoticeDao.class).getList();
//		NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
		List<NoticeView> list = noticeDao.getList();
		
		model.addAttribute("list", list);
		//return "admin/notice/list";//jsp�������� ã�� ���� url�� ����
		return "admin.notice.list";//tiles���� ������ ������ ��Ź�ϱ� ���� �����̸�
	} 
	
//	@RequestMapping("detail")
//	public String detail(Integer id) throws ClassNotFoundException, SQLException {
//		
//		
//		return "admin/notice/detail";
//	} 
//	//test edit
	@GetMapping("edit")
	public String edit(Integer id, Model model) {
		Notice notice = noticeDao.get(id);
		
		model.addAttribute("notice", noticeDao.get(id));
		
		return "admin/notice/edit";
	}
	
	@PostMapping("edit")
	public String edit(Notice notice) throws ClassNotFoundException, SQLException {
		Notice n = noticeDao.get(notice.getId());
		
		n.setTitle(notice.getTitle());
		n.setContent(notice.getContent());
		
		noticeDao.update(n);
		return "redirect:detail?id="+notice.getId();
		
	}
	
	//4.x
	//Get
		@GetMapping("reg")
		public String reg() {
			return "admin.notice.reg";
		}
		//Post-> �Է��Ҷ� �ѱ۱���. web.xml�� ���͸���
		@PostMapping("reg")
		public String reg(Notice notice, String category, MultipartFile file, HttpServletRequest request) throws IOException, ClassNotFoundException, SQLException {
		//public String reg(String category, String title, String file, String content) {
			
			System.out.println(category);
			System.out.println(notice.getTitle());
			System.out.println(file.getOriginalFilename());
			System.out.println(notice.getContent());
			
			// 1. ���ε� ��θ� ���
		      String urlPath = "/upload";
		      String path = request
		                     .getServletContext()
		                     .getRealPath(urlPath);
		      
		      System.out.println(path);
		      
		      // 2. ���ε�� ���ϸ� ���
		      String fileName = file.getOriginalFilename();//filePart.getSubmittedFileName();
		            
		      // 3. ��� ������ �ֱ� 
		      String filePath = path + File.separator + fileName; // d:\aa + "bb.jpg" -> d:\aabb.jpg
		      
		      System.out.println(filePath);
		      
		      // 4. ��ΰ� ���ٴ� ���� ����
		      File pathFile = new File(path);
		      if(!pathFile.exists()) // �������� ������
		         pathFile.mkdirs();// �������ּ���.  
		      
		      // 5. ������ ���ϸ� ��ο� �̹� �����ϴ� ���� : �̸� ��å
		      //aa.jpg -> aa.jpg1 ==> aa1.jpg
		      //aa1.jpg -> aa(1).jpg
		      /*
		       * File ? = new File(?);
		       * 
		       * if(? �����Ѵٸ�) { ����(Ȯ����)�� �߶� �̸��� ��� �� �������� �Ұ�ȣ()�� �ִ��� Ȯ���ϰ� ������ ��ȣ�� �˾Ƴ��� 1������ ����
		       * ��.. fileName = ?; }
		       */
		       
		       File sameFile = new File(filePath);
		        System.out.println(sameFile);
		        if(sameFile.exists()) {
		           
		           int n = fileName.lastIndexOf(".");           // fileName=hello.jpg      n =?,    name=    , suffix,    
		           String name = fileName.substring(0, n);  // 
		           String suffix = fileName.substring(n);         
		           
		           //name.endsWith(")");  
		           //int parenS = name.lastIndexOf("(");
		           //int parenE = name.lastIndexOf(")");
		            
		           //String indexC = name.substring(parenS+1, parenE);
		           
		           //int indexN = Integer.parseInt(indexC);
		            
		           //if (parenS == -1)
		               fileName = name +"("+ 1 +")"+ suffix;
		           //else {
		           //   indexN++;
		           //    fileName = fileName.substring(0, parenS+1)+ indexN +")"+ suffix;
		           //}           
		       }
		      
		      InputStream fis = file.getInputStream();
		      OutputStream fos = new FileOutputStream(filePath);
		      
		      byte[] buf = new byte[1024];
		      int size = 0;
		      while((size = fis.read(buf)) != -1)
		         fos.write(buf, 0, size);
		      
		      fis.close();
		      fos.close();
		      //1.���� 2.��Ƽ���ٿ� ����
		      notice.setWriterId("jjh");
		      noticeDao.insert(notice);
		      
			return "redirect:list";
		}
		
		
	/* 3.x
	//Get
	@RequestMapping(value="reg", method=RequestMethod.GET)
	public String reg() {
		return "admin/notice/reg";
	}
	//Post
	@RequestMapping(value="reg", method=RequestMethod.POST)
	public String reg(String title) {
		//redirection: list page��
		return "redirect:list";
		//return "admin/notice/reg";//������
	}*/
}