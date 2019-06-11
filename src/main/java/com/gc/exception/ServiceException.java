/**
 * ServiceException.java - Service logic exception class.
 * 2019 All rights reserved.
 *
 */
package com.gc.exception;

/**
 * Service Exception class for GeekCell.
 *
 * @author Mardolfh Del Rosario
 *
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 7010376031657145391L;

	/**
     * This is called for Service layer type of exceptions.
     *
     * @param errorMessage                 specific error message.
     */
	public ServiceException(String errorMessage) {
		super(errorMessage);
	}

	/**
     * This is called for Service layer type of exceptions with exception object.
     *
     * @param errorMessage                 specific error message.
     * @param e                            exception ref.
     */
    public ServiceException(String errorMessage, Exception e) {
        super(errorMessage, e);
    }

}
