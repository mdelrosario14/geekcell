package com.gc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gc.exception.ServiceException;
import com.gc.model.MonthlyTrxRequest;
import com.gc.model.TransactionResponse;
import com.gc.service.TransactionService;

@RestController
@RequestMapping("/web/geekcell-user/")
public class TransactionController {
	private static final Logger LOG = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	TransactionService transactionService;

	/**
     * Get the current month's income summary.
     *
     * @param req            WorkSheetRequest model.
     * @return                 ResponseEntity - bad request or success.
     */
    @PostMapping("getCurrentMonthTransaction")
    public ResponseEntity<?> getCurrentMonthTransaction(@RequestBody MonthlyTrxRequest req) {
        try {
        	LOG.warn("getCurrentMonthTransaction()-start");
        	List<TransactionResponse> allTransList =
        			this.transactionService.getTransactionByDate(req.getLocalDateTime(), req.getCurrency());
        	Map<String, Object> responseMap = new HashMap<>();
        	responseMap.put("Transaction", allTransList);
            return ResponseEntity.ok().body(responseMap);
        } catch (ServiceException e) {
        	return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

}
