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
		//1.세션도구를 생성해서 Sqlsession sqlsession=?;
		//세션객체를 통해서 매퍼를 얻어서 원하는 메소드를 호출한다
		
		//2. 세션도구를 얻는 작업을 설정에서 템플릿으로 설정하고 
		//여기서는 그냥 그 세션도구를 얻어서 호출하는 방법을 하자
		
		//List<NoticeView> list = sqlSession.getMapper(NoticeDao.class).getList();
//		NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
		List<NoticeView> list = noticeDao.getList();
		
		model.addAttribute("list", list);
		//return "admin/notice/list";//jsp페이지를 찾기 위한 url의 정보
		return "admin.notice.list";//tiles에게 페이지 조립을 부탁하기 위한 매핑이름
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
		//Post-> 입력할때 한글깨짐. web.xml에 필터맵핑
		@PostMapping("reg")
		public String reg(Notice notice, String category, MultipartFile file, HttpServletRequest request) throws IOException, ClassNotFoundException, SQLException {
		//public String reg(String category, String title, String file, String content) {
			
			System.out.println(category);
			System.out.println(notice.getTitle());
			System.out.println(file.getOriginalFilename());
			System.out.println(notice.getContent());
			
			// 1. 업로드 경로를 얻기
		      String urlPath = "/upload";
		      String path = request
		                     .getServletContext()
		                     .getRealPath(urlPath);
		      
		      System.out.println(path);
		      
		      // 2. 업로드된 파일명 얻기
		      String fileName = file.getOriginalFilename();//filePart.getSubmittedFileName();
		            
		      // 3. 경로 구분자 넣기 
		      String filePath = path + File.separator + fileName; // d:\aa + "bb.jpg" -> d:\aabb.jpg
		      
		      System.out.println(filePath);
		      
		      // 4. 경로가 없다는 오류 문제
		      File pathFile = new File(path);
		      if(!pathFile.exists()) // 존재하지 않으면
		         pathFile.mkdirs();// 생성해주세요.  
		      
		      // 5. 동일한 파일명에 경로에 이미 존재하는 문제 : 이름 정책
		      //aa.jpg -> aa.jpg1 ==> aa1.jpg
		      //aa1.jpg -> aa(1).jpg
		      /*
		       * File ? = new File(?);
		       * 
		       * if(? 존재한다면) { 꼬리(확장자)를 잘라낸 이름을 얻고 그 마지막에 소괄호()가 있는지 확인하고 있으면 번호를 알아내고 1증가된 값을
		       * 얻어서.. fileName = ?; }
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
		      //1.매퍼 2.노티스다오 구현
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
		//redirection: list page로
		return "redirect:list";
		//return "admin/notice/reg";//포워딩
	}*/
}