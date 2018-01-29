package com.app.util.pay.bean.outbuilder;

import com.app.util.pay.bean.MsgType;
import com.app.util.pay.bean.PayOutMessage;

/**
 * @author egan
 * <pre>
 *     email egzosn@gmail.com
 *     date 2016-6-1 13:53:3
 *  </pre>
 */
public class PayXmlOutMessage extends PayOutMessage{

    private String code;

    public PayXmlOutMessage() {
        this.msgType = MsgType.xml.name();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toMessage() {
       return "<xml><return_code><![CDATA[" + code + "]]></return_code><return_msg><![CDATA[" + content
                + "]]></return_msg></xml>";
    }
}
