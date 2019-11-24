/**
 * DtoException.java - dto exception class.
 * 2019 All rights reserved.
 *
 */
package com.gc.exception;

/**
 * Mapper exception for GeekCell.
 *
 * @author Mardolfh Del Rosario
 *
 */
public class MapperException extends Exception {

	private static final long serialVersionUID = -3219418399348453620L;

	/**
     * This is called for mapper type of exceptions.
     *
     * @param errorMessage                 specific error message.
     */
	public MapperException(String errorMessage) {
		super(errorMessage);
	}

	/**
     * This is called for mapper type of exceptions with exception object.
     *
     * @param errorMessage                 specific error message.
     * @param e                            exception ref.
     */
    public MapperException(String errorMessage, Exception e) {
        super(errorMessage, e);
    }

}