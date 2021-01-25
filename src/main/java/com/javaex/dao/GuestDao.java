package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestDao {

	//오토와이어
	@Autowired
	private SqlSession sqlSession;
	
	//리스트 가져오기
	public List<GuestVo> getGuestList(){
		System.out.println("getGuestList()");
		
		List<GuestVo> guestList = sqlSession.selectList("guestbook.selectList");
		System.out.println(guestList.toString());
		
		return guestList;
	}
	
	//등록하기
	public void guestInsert(GuestVo guestVo) {
		System.out.println("guestInsert()");
		
		int count = sqlSession.insert("guestbook.insert", guestVo);
		System.out.println(count);
		
	}
	
	//삭제
	public int guestDelete(GuestVo guestVo) {
		System.out.println("guestDelete()");
		
		return sqlSession.delete("guestbook.delete", guestVo);
	}
	
}
