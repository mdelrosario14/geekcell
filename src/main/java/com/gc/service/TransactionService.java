package com.gc.service;

import java.time.LocalDateTime;
import java.util.List;

import com.gc.exception.ServiceException;
import com.gc.model.TransactionResponse;

/**
 * Interface for monthly based transactions.
 * @author Mardolfh Del Rosario
 *
 */
public interface TransactionService {
	List<TransactionResponse> getTransactionByDate(LocalDateTime byDate, String curStr) throws ServiceException;

}
