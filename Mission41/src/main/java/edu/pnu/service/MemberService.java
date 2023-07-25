package edu.pnu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.pnu.dao.LogDao;
import edu.pnu.dao.MemberDaoH2Impl;
import edu.pnu.dao.MemberDaoListImpl;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

public class MemberService {

	private MemberInterface memberDao;
	private LogDao logDao;

	public MemberService() {
		//memberDao = new MemberDaoListImpl();
		memberDao = new MemberDaoH2Impl();
		logDao = new LogDao();
	}

	public List<MemberVO> getMembers() {
		//return memberDao.getMembers();
		Map<String, Object> maps = memberDao.getMembers();
		logDao.addLog(maps);
		return (List<MemberVO>)maps.get("result");
	}
	
	public MemberVO getMember(Integer id) {
		Map<String, Object> maps = memberDao.getMember(id);
		logDao.addLog(maps);
		return (MemberVO)maps.get("result");
	}
	
	public MemberVO addMember(MemberVO member) {
		Map<String, Object> maps = memberDao.addMember(member);
		logDao.addLog(maps);
		return (MemberVO)maps.get("result");
	}
	
	public MemberVO updateMember(MemberVO member) {
		Map<String, Object> maps = new HashMap<>();
		logDao.addLog(maps);
		return (MemberVO)maps.get("result");
	}
	
	public int deleteMember(Integer id) {
		Map<String, Object> maps = new HashMap<>();
		logDao.addLog(maps);
		return (int)maps.get("result");
	}
}
