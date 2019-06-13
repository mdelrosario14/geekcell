/**
 * EntityModelDto.java - Abstract class that deals with data transfer objects.
 * 2019 All rights reserved.
 *
 */
package com.gc.dto;

import com.gc.exception.DtoException;

/**
 * Abstract class for Entity and Model transfer.
 * @author Mardolfh Del Rosario
 *
 */
public abstract class EntityModelDto {
	static String DTO_ERROR = "Dto transfer failed. Cause: Message Property Reader";
	abstract Object transferDataToModel(Object o) throws DtoException;
}
