package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping(value="/guest")
public class GuestController {

	//필드
	@Autowired
	private GuestDao guestDao;
	
	
	//리스트
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("리스트");
		
		//Dao -> 리스트 가져오기
		List<GuestVo> guestList = guestDao.getGuestList();
		
		//Model - 데이터 담기
		model.addAttribute("gList", guestList);
		
		return "list";
	}
	
	
	//등록
	@RequestMapping(value="/insert", method= {RequestMethod.GET, RequestMethod.POST})
	public String insert(@ModelAttribute GuestVo guestVo) {
		System.out.println("등록");
		
		//Dao -> 등록
		guestDao.guestInsert(guestVo);
		
		return "redirect:/guest/list";
	}
	
	
	//삭제폼
	@RequestMapping(value="/deleteForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(@RequestParam("no") int no) {
		System.out.println("삭제폼");
		System.out.println(no);
		
		return "deleteForm";
	}
	
	
	//삭제
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestVo guestVo) {
		System.out.println("삭제");

		//Dao -> 삭제
		int count = guestDao.guestDelete(guestVo);
		
		//model담기 -> 포워드할때 이용 -> 내부 jsp
		//param -> 리다이렉트할때 이용 -> 요청 url
		//model.addAttribute("no", guestVo.getNo());
		
		//리턴값이 1이면 삭제, 0이면 다시입력
		if(count == 1) {
			return "redirect:/guest/list";
		} else {
			return "redirect:/guest/deleteForm?no=" + guestVo.getNo();
		}

	}
	
}
