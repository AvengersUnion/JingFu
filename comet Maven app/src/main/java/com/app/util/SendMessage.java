package com.app.util;

import javax.servlet.http.HttpSession;

import com.montnets.mwgate.common.GlobalParams;
import com.montnets.mwgate.common.Message;
import com.montnets.mwgate.smsutil.ConfigManager;
import com.montnets.mwgate.smsutil.SmsSendConn;

/**
 * 获取短信验证码
 * @author taoxiangfei
 *
 */
public class SendMessage {
	/**
	 * 获取登陆短信验证码
	 * @param phone
	 * @return
	 */
	public static String sendLoginCode(String phone) {
		// 用户账号
		String userid = Application.mwyUserId;
		// 创建全局参数
		GlobalParams globalParams = new GlobalParams();
		// 设置请求路径
		globalParams.setRequestPath(Application.mwyRequestPath);
		// 设置是否需要日志 1:需要日志;0:不需要日志
		globalParams.setNeedLog(1);
		// 设置全局参数
		ConfigManager.setGlobalParams(globalParams);
		// 设置用户账号信息
		boolean isSet = setAccountInfo();
		if(!isSet) {
			return null;
		}
		String code = createCode();
		// 是否保持长连接
		boolean isKeepAlive = true;
		// 实例化短信处理对象
		SmsSendConn smsSendConn = new SmsSendConn(isKeepAlive);
		// 单条发送
		boolean isSend = singleSend(smsSendConn, userid,phone,code);
		if(!isSend) {
			return null;
		}
		// 移除用户账号
		removeAccount(userid);
		return code;
	}
	private static String createCode() {
        String vcode = "";
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int)(Math.random() * 9);
        }
        return vcode;
	}
	/**
	 * @description 设置用户账号信息
	 */
	public static boolean setAccountInfo() {
		// 用户账号
		String userid = Application.mwyUserId;
		// 密码
		String password = Application.mwyPassword;
		// 发送优先级
		int priority = 1;
		// 主IP信息
		String ipAddress1 = Application.mwyIpAddress1;
		// 备用IP1信息
		String ipAddress2 = Application.mwyIpAddress2;
		// 备用IP2信息
		String ipAddress3 = Application.mwyIpAddress3;
		// 备用IP3信息
		String ipAddress4 = Application.mwyIpAddress4;
		// 返回值
		int result = -310007;
		try {
			// 设置用户账号信息
			result = ConfigManager.setAccountInfo(userid, password, priority,
					ipAddress1, ipAddress2, ipAddress3, ipAddress4);
			// 判断返回结果，0设置成功，否则失败
			if (result == 0) {
				System.out.println("设置用户账号信息成功！");
				return true;
			} else {
				System.out.println("设置用户账号信息失败，错误码：" + result);
				return false;
			}
		} catch (Exception e) {
			// 异常处理
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 
	 * @description 单条发送
	 * @param smsSendConn
	 *            短信处理对象,在这个方法中调用发送短信功能
	 * @param userid
	 *            用户账号
	 */
	public static boolean singleSend(SmsSendConn smsSendConn, String userid,String phone,String code) {
		try {
			// 参数类
			Message message = new Message();
			// 设置用户账号 指定用户账号发送，需要填写用户账号，不指定用户账号发送，无需填写用户账号
			message.setUserid(userid);
			// 设置手机号码 此处只能设置一个手机号码
			message.setMobile(phone);
			// 设置内容
			String mes = "您的验证码是"+code+"，在10分钟内有效。如非本人操作请忽略本短信。";
			message.setContent(mes);
//			// 设置扩展号
//			message.setExno("11");
//			// 用户自定义流水编号
//			message.setCustid("20160929194950100001");
//			// 自定义扩展数据
//			message.setExdata("abcdef");
//			// 业务类型
//			message.setSvrtype("SMS001");

			// 返回的流水号
			StringBuffer returnValue = new StringBuffer();
			// 返回值
			int result = -310099;
			// 发送短信
			result = smsSendConn.singleSend(message, returnValue);
			// result为0:成功
			if (result == 0) {
				System.out.println("单条发送提交成功！");
				System.out.println(returnValue.toString());
				return true;
			}
			// result为非0：失败
			else {
				System.out.println("单条发送提交失败,错误码：" + result);
				return false;
			}
		} catch (Exception e) {
			// 异常处理
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 移除用户账号
	 * 
	 * @param userid
	 *            用户账号
	 */
	public static boolean removeAccount(String userid) {
		try {
			// 调用移除用户账号的方法
			int result = ConfigManager.removeAccount(userid);

			// 返回0，代表移除成功
			if (result == 0) {
				System.out.println("移除用户账号[" + userid + "]成功！");
				return true;
			}
			// 返回非0，代表移除失败
			else {
				System.out.println("移除用户账号[" + userid + "]失败！错误码：" + result);
				return false;
			}
		} catch (Exception e) {
			// 异常处理
			e.printStackTrace();
			return false;
		}
	}
}
