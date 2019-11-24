package com.gc.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gc.dto.TrxMapper;
import com.gc.entity.TransactionEntity;
import com.gc.exception.MapperException;
import com.gc.exception.ServiceException;
import com.gc.model.Currency;
import com.gc.model.TransactionResponse;
import com.gc.repository.TransactionRepository;
import com.gc.service.TransactionService;
import com.gc.util.GeekCellUtil;

public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	GeekCellUtil geekCellUtil;

	@Autowired
	TrxMapper trxMapper;

	@Override
	public List<TransactionResponse> getTransactionByDate(LocalDateTime byDate, String curStr) throws ServiceException {
		List<TransactionEntity> transEnt = null;
		List<TransactionResponse> transOfMonth = null;

		if (byDate != null) {
			transOfMonth = new ArrayList<>();
			Currency curr = this.geekCellUtil.getCurrencyEnumVal(curStr);
			transEnt = this.transactionRepository.findByDateAndCurrency(
					this.geekCellUtil.getLocalDateWithNoTime(byDate), curr);
			int ctr = 0;
			for (TransactionEntity ent : transEnt) {
				try {
					TransactionResponse resp = (TransactionResponse) this.trxMapper.transferEntityToModel(ent);
					resp.setTrxMonthId(++ctr);
					transOfMonth.add(resp);
				} catch (MapperException e) {
					throw new ServiceException(e.getMessage());
				}

			}

		} else {
			throw new ServiceException("Unable to get date period.");
		}

		return transOfMonth;
	}

}
