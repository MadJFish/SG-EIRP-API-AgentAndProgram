package com.sg.eirp.program.base;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseMapper {
	public abstract Object mapObject(Object model);
	
	public List<Object> mapObjects(List<Object> models) {
		if (models == null) {
			return new ArrayList<Object>();
		}

		return models.stream()
				.map(model -> mapObject(model))
				.collect(Collectors.toList());
	}
}