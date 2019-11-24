package com.gc.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gc.exception.ServiceException;
import com.gc.model.Currency;
import com.gc.model.IncomeGroup;
import com.gc.repository.TransactionRepository;
import com.gc.service.WorksheetService;
import com.gc.util.GeekCellUtil;

@Service
public class WorkSheetServiceImpl implements WorksheetService {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	GeekCellUtil geekCellUtil;


	@Override
	public List<IncomeGroup> getAnnualIncomeSummary(LocalDateTime ldt, Currency curr)
			throws ServiceException {
		return this.transactionRepository.findByAnnualIncomeByCurr(ldt.getYear(), curr);
	}

	@Override
	public double getTotalIncome(List<IncomeGroup> incGrp) {
		if (incGrp != null && !incGrp.isEmpty()) {
			double totalVal = 0;
			for (IncomeGroup income : incGrp) {
				totalVal += income.getTotalCnt();
			}

			return totalVal;
		}

		return 0;
	}


}
