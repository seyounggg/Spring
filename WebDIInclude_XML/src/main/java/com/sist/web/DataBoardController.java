package com.sist.web;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.sist.dao.DataBoardDAO;
import com.sist.vo.DataBoardVO;

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
				File file=new File("/Users/seyeong/download"+mf.getOriginalFilename());
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
}
