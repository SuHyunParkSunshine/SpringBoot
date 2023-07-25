package edu.pnu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.LogDaoInterface;
import edu.pnu.dao.MemberDaoH2Impl;
import edu.pnu.dao.MemberDaoListImpl;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

//@Service //=>Configuration-Bean 사용시 Service할 필요 없어서 주석 달아둠
public class MemberService {

	@Autowired
	private MemberInterface memberDao;
	@Autowired
	private LogDaoInterface logDao;

	public List<MemberVO> getMembers() {
		// return memberDao.getMembers();
		Map<String, Object> maps = memberDao.getMembers();
		List<MemberVO> list = (List<MemberVO>) maps.get("result");
		if (list != null)
			logDao.addLog("get", (String) maps.get("sqlstring"), true);
		else
			logDao.addLog("get", (String) maps.get("sqlstring"), false);

		return list;
	}

	public MemberVO getMember(Integer id) {
		Map<String, Object> maps = memberDao.getMember(id);
		MemberVO m = (MemberVO) maps.get("result");
		if (m != null)
			logDao.addLog("get", (String) maps.get("sqlstring"), true);
		else
			logDao.addLog("get", (String) maps.get("sqlstring"), false);

		return m;
	}

	public MemberVO addMember(MemberVO member) {
		Map<String, Object> maps = memberDao.addMember(member);
		MemberVO m = (MemberVO) maps.get("result");
		if (m != null)
			logDao.addLog("post", (String) maps.get("sqlstring"), true);
		else
			logDao.addLog("post", (String) maps.get("sqlstring"), false);
		return m;
	}

	public MemberVO updateMember(MemberVO member) {
		Map<String, Object> maps = memberDao.updateMember(member);
		MemberVO m = (MemberVO) maps.get("result");
		if (m != null)
			logDao.addLog("put", (String) maps.get("sqlstring"), true);
		else
			logDao.addLog("put", (String) maps.get("sqlstring"), false);
		return m;
	}

	public int deleteMember(Integer id) {
		Map<String, Object> maps = memberDao.deleteMember(id);
		int m = (int) maps.get("result");
		if (m != 0)
			logDao.addLog("delete", (String) maps.get("sqlstring"), true);
		else
			logDao.addLog("delete", (String) maps.get("sqlstring"), false);
		return id;
	}
}
