package edu.pnu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import edu.pnu.domain.MemberVO;

public class MemberDaoListImpl implements MemberInterface {

	private List<MemberVO> list;
	private Map<String, Object> maps;

	public MemberDaoListImpl() {
		list = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			list.add(new MemberVO(i, "123" + i, "이름" + i, new Date()));
		}
	}

	@Override
	public Map<String, Object> getMembers() {
		return maps;
	}

	@Override
	public Map<String, Object> getMember(Integer id) {
		for(MemberVO m : list) {
			if(m.getId() == id)
				return maps;
		}
		return null;
	}

	@Override
	public Map<String, Object> addMember(MemberVO member) {
		member.setId(list.size() + 1);
		member.setRegidate(new Date());
		list.add(member);
		return maps;
	}

	@Override
	public Map<String, Object> updateMember(MemberVO member) {
		for(MemberVO m : list) {
			if(m.getId() == member.getId()) {
				m.setName(member.getName());
				m.setPass(member.getPass());
				return maps;
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> deleteMember(Integer id) {
		for (MemberVO m :  list) {
			if(m.getId() == id) {
				list.remove(m);
				return maps;
			}
		}
		return null;
	}
}
