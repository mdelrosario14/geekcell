package com.gc.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gc.exception.ServiceException;
import com.gc.model.Currency;
import com.gc.model.IncomeGroup;
import com.gc.service.WorksheetService;

@RestController
@RequestMapping("/web/geekcell-user/")
public class WorksheetController {

	private static final Logger LOG = LoggerFactory.getLogger(WorksheetController.class);

	@Autowired
	WorksheetService worksheetService;

	/**
     * Get the annual income summary.
     *
     * @param req              WorkSheetRequest model.
     * @return                 ResponseEntity - bad request or success.
     */
    @PostMapping("getAnnualTransaction")
    public ResponseEntity<?> getAnnualTransaction(@RequestParam LocalDateTime ldt,
    		@RequestParam Currency curr) {
        try {
        	LOG.warn("getAnnualTransaction()-start");
        	List<IncomeGroup> incGroup = null;
        	//Income
        	incGroup = this.worksheetService.getAnnualIncomeSummary(ldt, curr);
        	double totalIncome = this.worksheetService.getTotalIncome(incGroup);

        	Map<String, Object> responseMap = new HashMap<>();
        	responseMap.put("Total Income", totalIncome);
        	responseMap.put("Income Group", incGroup);
            return ResponseEntity.ok().body(responseMap);
        } catch (ServiceException e) {
        	return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
