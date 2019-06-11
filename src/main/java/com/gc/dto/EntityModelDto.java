package com.gc.dto;

import com.gc.exception.DtoException;

public abstract class EntityModelDto {
	abstract Object transferDataToModel(Object o) throws DtoException;
}
