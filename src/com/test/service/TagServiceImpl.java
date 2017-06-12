package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.test.mapper.TagMapper;
import com.test.po.Tag;

public class TagServiceImpl implements TagService {

	@Autowired
	TagMapper tagMapper;
	
	@Override
	public List<Tag> findService() {
		// TODO Auto-generated method stub
		return tagMapper.findTag();
	}

}
