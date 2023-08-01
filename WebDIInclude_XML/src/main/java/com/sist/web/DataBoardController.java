package com.sist.web;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sist.dao.DataBoardDAO;
import com.sist.vo.DataBoardVO;

import oracle.jdbc.proxy.annotation.Post;

@Controller
public class DataBoardController {
	@Autowired
	private DataBoardDAO dao;
	
	// 목록 출력
	@GetMapping("databoard/list.do")
	public String databoard_list(String page,Model model) {
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end",end);
		List<DataBoardVO> list=dao.databoardListData(map);
		int totalpage=dao.databoardTotalPage();
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("list", list);
		model.addAttribute("main_jsp", "../databoard/list.jsp");
		return "main/main";
	}
	// 데이터 추가
	@GetMapping("databoard/insert.do")
	public String databoard_insert(Model model) {
		
		model.addAttribute("main_jsp", "../databoard/insert.jsp");
		return "main/main";
	}
	
	@PostMapping("databoard/insert_ok.do")
	public String databoard_insert_ok(DataBoardVO vo) {
		List<MultipartFile> list=vo.getFiles();
		if(list==null) { // 파일 업로드가 안된 상태
			vo.setFilename("");
			vo.setFilesize("");
			vo.setFilecount(0);
		} else { // 업로드가 된 상태
			String filenames="";
			String filesizes="";
			for(MultipartFile mf:list) {
				File file=new File("/Users/seyeong/download/"+mf.getOriginalFilename());
				try {
					mf.transferTo(file); // 파일 업로드할때 사용하는 것
				}catch(Exception e) {}
				filenames+=file.getName()+",";
				long len=file.length();
				filesizes+=len+",";
			}
			filenames=filenames.substring(0,filenames.lastIndexOf(","));
			filesizes=filesizes.substring(0,filesizes.lastIndexOf(","));
			vo.setFilename(filenames);
			vo.setFilesize(filesizes);
			vo.setFilecount(list.size()); // list.size를 통해서 파일 갯수 확인
		}
		dao.databoardInsert(vo);
		return "redirect:../databoard/list.do";
	}
	
	// "../databoard/detail.do?no=${vo.no }"
	// int no는 dispatcherServlet 받아온다
	@GetMapping("databoard/detail.do")
	public String databoard_detail(int no,Model model) { 
		DataBoardVO vo=dao.databoardDetailData(no);
		if(vo.getFilecount()>0) {
			String filenames=vo.getFilename();
			StringTokenizer st=new StringTokenizer(filenames,",");
			List<String> nList=new ArrayList<String>();
			while(st.hasMoreTokens()) {
				nList.add(st.nextToken());
			}
			String filesizes=vo.getFilesize();
			st=new StringTokenizer(filesizes,",");
			List<String> sList=new ArrayList<String>();
			while(st.hasMoreTokens()) {
				sList.add(st.nextToken());
			}
			model.addAttribute("nList", nList);
			model.addAttribute("sList", sList);
		}
		model.addAttribute("vo", vo);
		model.addAttribute("main_jsp", "../databoard/detail.jsp");
		return "main/main";
	}
	
	@GetMapping("databoard/download.do")
	public void databoard_download(String fn,HttpServletResponse response) {
		try {
			File file=new File("/Users/seyeong/download/"+fn);
			response.setHeader("Content-Disponsition", "attachement;filename="
					+URLEncoder.encode(fn,"UTF-8"));
			//download
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
			// 서버에서 파일 읽기
			BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
			// 사용자에게 전송
			int i=0;
			byte[] buffer=new byte[1024]; //TCP(1024) / UDP(512)
			while((i=bis.read(buffer,0,1024))!=-1) { //-1 :EOF
				bos.write(buffer, 0, i);
			}
			bis.close();
			bos.close();
		} catch (Exception e) {}
	}
	
	@PostMapping("databoard/find.do")
	public String databoard_find(String fs,String ss,Model model) {
		Map map=new HashMap();
		map.put("fs", fs);
		map.put("ss", ss);
		List<DataBoardVO> list=dao.databoardFindData(map);
		
		model.addAttribute("list", list);
		model.addAttribute("count", list.size());
		model.addAttribute("main_jsp", "../databoard/find.jsp");
		return "main/main";
	}
	
	@GetMapping("databoard/update.do")
	public String databoard_update(int no,Model model) {
		DataBoardVO vo=dao.databoardUpdateData(no); // update 전 데이터 가져오기
		
		model.addAttribute("vo", vo);
		model.addAttribute("main_jsp", "../databoard/update.jsp");
		return "main/main";
	}
	
	@PostMapping("databoard/update_ok.do")
	@ResponseBody // 승격 -> (@restController) body 에 응답을 해주겠다?
	public String databoard_update_ok(DataBoardVO vo) {
		String result="";
		boolean bCheck=dao.databoardUpdate(vo);
		if(bCheck==true) {
			result="<script>"
				+ "location.href=\"../databoard/detail.do?no="+vo.getNo()+"\";"
				+ "</script>";
		} else {
			result="<script>"
				+ "alert(\"비밀번호가 틀립니다!\");"
				+ "history.back()"
				+ "</script>";
		}
		return result;
	}
	
	// delete 창으로 이동
	@GetMapping("databoard/delete.do")
	public String databoard_delete(int no,Model model) {
		
		model.addAttribute("no", no);
		model.addAttribute("main_jsp", "../databoard/delete.jsp");
		return"main/main";
	}
	// 실제 delete => @ResponseBody
	@PostMapping("databoard/delete_ok.do")
	@ResponseBody // 취소를 하거나, hisotry.back() 하거나 둘중 하나로 보내야 해서 responsebody 이용
	public String databoard_delete_ok(int no,String pwd) {
		String result="";
		boolean bCheck=dao.databoardDelete(no, pwd);
		if(bCheck==true) {
			result="<script>"
				+ "location.href=\"../databoard/list.do\";"
				+ "</script>";
		} else {
			result="<script>"
				+ "alert(\"비밀번호가 틀립니다!\");"
				+ "history.back()"
				+ "</script>";
		}
		return result;
	}
}
