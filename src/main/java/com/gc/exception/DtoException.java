/**
 * DtoException.java - dto exception class.
 * 2019 All rights reserved.
 *
 */
package com.gc.exception;

/**
 * Data Transfer object class for GeekCell.
 *
 * @author Mardolfh Del Rosario
 *
 */
public class DtoException extends Exception {

	private static final long serialVersionUID = -3219418399348453620L;

	/**
     * This is called for dto type of exceptions.
     *
     * @param errorMessage                 specific error message.
     */
	public DtoException(String errorMessage) {
		super(errorMessage);
	}

	/**
     * This is called for dto type of exceptions with exception object.
     *
     * @param errorMessage                 specific error message.
     * @param e                            exception ref.
     */
    public DtoException(String errorMessage, Exception e) {
        super(errorMessage, e);
    }

}