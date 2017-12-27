package com.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.app.dao.BatchSeqDao;

/**
 * 生成id工具
 *  
 * @author taoxiangfei
 */
public class BatchSeqUtil{
	WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
//	@Autowired
	BatchSeqDao batchSeqDao = (BatchSeqDao) ctx.getBean("batchSeqDao");
	
    public String createFuwuId() {
        StringBuffer sb = new StringBuffer();
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String date = df.format(new Date()).toString();
        String id = "";
        try {
        	id = batchSeqDao.getFuwuId();
        } catch (EmptyResultDataAccessException e) {
        	id = null;
        }
        if (id == null || "".equals(id)) {
            String batch = sb.append("FW").append(date).append("000001").toString();
            batchSeqDao.insertFuwuId(batch);
            return batch;
        } else if (date.equals(id.substring(2, 6))) {
            int num = Integer.parseInt(id.substring(6));
            num++;
            String str = String.format("%06d", num);
            String batch = sb.append("FW").append(date).append(str).toString();
            batchSeqDao.updateFuwuId(batch);
            return batch;
        } else {
            String batch = sb.append("FW").append(date).append("000001").toString();
            batchSeqDao.updateFuwuId(batch);
            return batch;
        }

    }

    public static String getFuwuId() {
    	BatchSeqUtil util = new BatchSeqUtil();
    	return util.createFuwuId();
    }

}
