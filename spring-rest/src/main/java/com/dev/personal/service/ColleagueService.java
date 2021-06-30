package com.dev.personal.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.dev.personal.mapper.ColleagueMapper;
import com.dev.personal.model.Colleague;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class ColleagueService {

	private final ColleagueMapper mapper;

	public List<Colleague> selectAll() {
		return mapper.selectAll();
	}

	public Colleague selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	public void save(Colleague colleague) {
		if (colleague.getId() == null) {
			mapper.insert(colleague);
		} else {
			mapper.updateByPrimaryKey(colleague);
		}
	}

	public void deleteByPrimaryKey(Long id) {
		mapper.deleteByPrimaryKey(id);
	}
}
