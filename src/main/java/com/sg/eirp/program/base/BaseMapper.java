package com.sg.eirp.program.base;

import java.util.List;

public interface BaseMapper {
	Object mapObject(Object model);
	
	List<Object> mapObjects(List<Object> models);
}