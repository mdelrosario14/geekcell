/**
 * UtilityException.java - util exception class.
 * 2019 All rights reserved.
 *
 */
package com.gc.exception;

/**
 * Utility Exception class for GeekCell.
 *
 * @author Mardolfh Del Rosario
 *
 */
public class UtilityException extends Exception {

	private static final long serialVersionUID = 2073436967315838741L;

	/**
     * This is called for util type of exceptions.
     *
     * @param errorMessage                 specific error message.
     */
	public UtilityException(String errorMessage) {
		super(errorMessage);
	}

	/**
     * This is called for util type of exceptions with exception object.
     *
     * @param errorMessage                 specific error message.
     * @param e                            exception ref.
     */
    public UtilityException(String errorMessage, Exception e) {
        super(errorMessage, e);
    }

}
