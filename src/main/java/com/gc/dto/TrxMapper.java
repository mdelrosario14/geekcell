package com.gc.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gc.entity.TransactionEntity;
import com.gc.exception.MapperException;
import com.gc.model.TransactionResponse;
import com.gc.util.GeekCellUtil;
import com.gc.util.MessageConstants;
import com.gc.util.MessagePropertyReader;

@Component
public class TrxMapper extends EntityModelMapper {

	@Autowired
	private MessagePropertyReader messagePropertyReader;

	@Autowired
	private GeekCellUtil geekCellUtil;

	@Override
	public Object transferEntityToModel(Object o) throws MapperException {
		TransactionResponse trxRespModel = null;
		if (null != o && o instanceof TransactionEntity) {
			TransactionEntity ent = (TransactionEntity) o;
			trxRespModel = new TransactionResponse();
			trxRespModel.setRecordDateTime(this.geekCellUtil.getLocalDate(ent.getRecordDateTime()));
			trxRespModel.setIncome(ent.getIncome().getTrxIncDesc());
			trxRespModel.setExpense(ent.getExpense().getTrxExpDesc());
			trxRespModel.setChannel(ent.getChannel().getChannelDesc());
			trxRespModel.setDebit(ent.getDebit());
			trxRespModel.setCredit(ent.getCredit());
			trxRespModel.setRemarks(ent.getRemarks());

		}  else {
			throw new MapperException(this.messagePropertyReader.toLocale(
					MessageConstants.GC_DTO_TRANSFER_FAILED));
		}

		return trxRespModel;
	}

	@Override
	Object transferModelToEntity(Object o) throws MapperException {
		// TODO Auto-generated method stub
		return null;
	}

}
