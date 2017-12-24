package com.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import com.app.dao.BatchSeqDao;

/**
 * 生成id工具
 *  
 * @author taoxiangfei
 */
public class BatchSeqUtil{
	
	@Autowired
	BatchSeqDao batchSeqDao;
	
    public String createId(String column) {
        StringBuffer sb = new StringBuffer();
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String date = df.format(new Date()).toString();
        String id = "";
        try {
        	id = batchSeqDao.getId(column);
        } catch (EmptyResultDataAccessException e) {
        	id = null;
        }
        if (id == null || "".equals(id)) {
            String batch = sb.append("FW").append(date).append("000001").toString();
            batchSeqDao.insertId(column, batch);
            return batch;
        } else if (date.equals(id.substring(1, 5))) {
            int num = Integer.parseInt(id.substring(5));
            num++;
            String str = String.format("%06d", num);
            String batch = sb.append("FW").append(date).append(str).toString();
            batchSeqDao.updateId(column, batch);
            return batch;
        } else {
            String batch = sb.append("FW").append(date).append("000001").toString();
            batchSeqDao.updateId(column, batch);
            return batch;
        }

    }

    public static String getFuwuId() {
    	BatchSeqUtil util = new BatchSeqUtil();
    	return util.createId("fuwu_id");
    }
    public static String getErjifuwuId() {
    	BatchSeqUtil util = new BatchSeqUtil();
    	return util.createId("erjifuwu_id");
    }

}
