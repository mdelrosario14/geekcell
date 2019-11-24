package com.gc.service;

import java.time.LocalDateTime;
import java.util.List;

import com.gc.exception.ServiceException;
import com.gc.model.Currency;
import com.gc.model.IncomeGroup;

public interface WorksheetService {
	List<IncomeGroup> getAnnualIncomeSummary(LocalDateTime ldt, Currency curr)
			throws ServiceException;
	double getTotalIncome(List<IncomeGroup> incGrp);

}
