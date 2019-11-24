/**
 * EntityModelDto.java - Abstract class that deals with data transfer objects.
 * 2019 All rights reserved.
 *
 */
package com.gc.dto;

import com.gc.exception.MapperException;

/**
 * Abstract class for Entity and Model transfer.
 * @author Mardolfh Del Rosario
 *
 */
public abstract class EntityModelMapper {
	static String DTO_ERROR = "Dto transfer failed. Cause: Message Property Reader";
	abstract Object transferEntityToModel(Object o) throws MapperException;
	abstract Object transferModelToEntity(Object o) throws MapperException;
}
