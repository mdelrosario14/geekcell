package com.gc.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.stereotype.Component;

import com.gc.model.Currency;

@Component
public class GeekCellUtil {

	public LocalDateTime getLocalDateWithNoTime(LocalDateTime origDt) {
		LocalDateTime locDateOnly = null;
		if (origDt != null) {
			LocalDate localDate = LocalDate.of(origDt.getYear(), origDt.getMonth(),
					origDt.getDayOfMonth());
			LocalTime localTime = LocalTime.of(0, 0, 0);
			locDateOnly = LocalDateTime.of(localDate, localTime);
		} else {
			locDateOnly = LocalDateTime.now();
		}

		return locDateOnly;
	}

	public LocalDate getLocalDate(LocalDateTime origDt) {
		LocalDate locDateOnly = null;
		if (origDt != null) {
			locDateOnly = LocalDate.of(origDt.getYear(), origDt.getMonth(),
					origDt.getDayOfMonth());
		} else {
			locDateOnly = LocalDate.now();
		}

		return locDateOnly;
	}

	public Currency getCurrencyEnumVal(String currStr) {
		Currency curr;
		switch(currStr.toUpperCase()) {
			case "USD" : curr = Enum.valueOf(Currency.class, "USD"); break;
			default : curr = Enum.valueOf(Currency.class, "NCV"); break;
		}

		return curr;

	}

}
