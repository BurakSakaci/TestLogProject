package com.example.testlogproject.core.utilities.mapping;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
	ModelMapper forDto();
	ModelMapper forRequest();

}
