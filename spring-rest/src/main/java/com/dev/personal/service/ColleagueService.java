package com.dev.personal.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dev.personal.mapper.ColleagueMapper;
import com.dev.personal.model.Colleague;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class ColleagueService {

	private final ColleagueMapper mapper;

	public Page<Colleague> selectAll(Pageable pageable) {
		// RowBounds:MyBatis側のページ情報
		// RowBoundsの引数にoffset(ページ位置)とlimit(１ページの表示件数）を指定する。
		RowBounds rowBounds = new RowBounds((int) pageable.getOffset(), pageable.getPageSize());
		List<Colleague> colleagues = mapper.selectAll(rowBounds);

		Long total = mapper.count();
		// 引数に内容、ページング情報、合計を指定する。
		return new PageImpl<>(colleagues, pageable, total);

	}

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
