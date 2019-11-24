package com.gc.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gc.entity.ChannelEntity;
import com.gc.entity.TransactionEntity;
import com.gc.model.Currency;
import com.gc.model.IncomeGroup;

/**
 * This repository deals with TransactionEntity transactions.
 * @author Mardolfh Del Rosario
 *
 */
@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
	List<TransactionEntity> findByChannel(ChannelEntity ch);

	@Query(value = "select tx from TransactionEntity tx join tx.income ti join tx.expense te "
			+ "where tx.trxDateTime = :trxDate1 and (ti.currCd = :curr or te.currCd = :curr)", nativeQuery = false)
	List<TransactionEntity> findByDateAndCurrency(
			@Param("trxDate1")LocalDateTime trxDate1, @Param("curr") Currency curr);

	@Query(value = "select new com.gc.model.IncomeGroup( " +
			"tx.trxDateTime, ti.trxIncId, ti.trxIncDesc, ti.currCd, sum(tx.debit) as totalCnt) " +
			"from TransactionEntity tx join tx.income ti " +
			"group by tx.trxDateTime, ti.trxIncId, ti.trxIncDesc, ti.currCd " +
	        "having YEAR(tx.trx_dt)= :trxYear and ti.currCd = :curr" , nativeQuery = false)
    List<IncomeGroup> getAnnualIncomeByCurr(@Param("trxYear") int year, @Param("curr") Currency curr);

}
