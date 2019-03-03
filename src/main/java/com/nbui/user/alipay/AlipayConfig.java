package com.nbui.user.alipay;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092500593617";

	// 商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCBp6+"
			+ "IdlYxRuOZZCwaqI+aYISaBYigp+fjS1njELPJPPs8VIYwM9KJOHVnZ/8bl5rfBAPH82O5dsZBabGjS0w1Pa/PfhIimKWP0M"
			+ "VLQ1eI9vRHMQoYfZkgzftwnkM+ibFrjdKsAei7qQPhIJbOJWXbYwigFvjbHiOWualWGdDheraSzc4MZmBSTD5/FbrLbZOn"
			+ "ITlUMJEMLy3NsHXwcG7vTIxB7g7N3vadQVzS2Iel6HfihwMVfRD3gDyyG8IyqEcfOCv5StosBhRQZl0Q+DY3vvNWdXD4GT"
			+ "anFdimZmeVVK1+R0S6nM+gk/wH24h+bdjEan7sanHdf6bB4nlYzLNLAgMBAAECggEATkgBSoIwXkYvywBZRKs7MPlPdnMa"
			+ "3+0gq/JlzhvagQkpo25hKp9PYXxOHmYC+BTb9ATHh6UjM+pLn1kCYNZop8ib6spyhqLla2SgimWZZ1+7c3RfZRQILW9Qfd"
			+ "mAPjUCgy644ROyAgP1DKsRglvAGOq9XXMhQ6L519o3LH4fyNLg1l5yFxnuL+NTrzdaNcixfvcjuv9T+qZ8G7mjssVG7jdu"
			+ "/axog6J2eTLkX77Q9XI+ErQcFFg7lXhL+VMIxiQgnngpmK6jEVsubIgbzWH4XACBTes718XCu1JYOaiviabd0glHzF7DA5"
			+ "8JEcXGJvYsagpZ41mJ4fiXWZT91xOtYQKBgQDU5jGueK1R8JGVJ4cjXEJa+uBy5jeY4I9Ip5G7T/GYWtoCyCDN+3hX5fzI"
			+ "vgv35A8RB0iLNtj8U3J+u2PCi/Zr2C1hah5quLjFfgErHQu27hvwOxeJEJDcDN2a1lPl4Eea+UcjedUDKYDwaBQptak71N"
			+ "k8fuSsTJ8rRkB6Y3i+ewKBgQCb5z42Z7WUgAKysZbZwR8Mz3hTdkDeEl0kZuPTdiUXoS8VHGIOK5Raik64IFilSWa8WPf+"
			+ "vaZdagBxpJyRAUjG7SjtPpDhFZ5z0vEYEHg7USlzZHiYUGIiUmYgqQWNk7QcWqfVFLfV3p8ubQtZFg2WxM+EmhpBrxz90h"
			+ "QwqH8tcQKBgDdhihrwmWVvRu+d0LGKFljEWg6JhHzoLV2g+W/YY+hV+dAvbxkmCTAUS6WMimB5mtG7K0vLgLZinq4I4PKC"
			+ "ph93pJZqnG72jloRZQxqCdoUJoBfXsfw3z3euubESh7KMNZHchHwZ1DjQv9Gk7gl4VgHDluGRsXrSJxhMfSkW4xxAoGADP"
			+ "Pwekqxjm34S2pv0xW1g7X1FFId35V9AZo+0YHeYRE2PTXHe8/pGoClO60MUGdBzuJBcHvHR7mX4dlAXNfTeRuZK+Tn2puW"
			+ "N59OAqbTpnH+1KSDVHxROvLHo20HlhSwgS6TBSQchftiFFRg/fzUEjO13hmDlvN6oCgFH2nQbzECgYAe6xiaMNonONr9PH"
			+ "H+3fHtITUzA0fvkpGK5fy44TXp9d3nmEnynu9HYv7o9OMD8U1Ek2SP+7VgK4K73g9f0p25"
			+ "dxgYR4OvBdeHeoHc6z4a2/hgpsLczhnzkLD1K8f+fcgg/NH/HstAbqI9AQCmNgOmoCQVQJoX+B6WYv9yciuMvg==";

	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1qeAFVeBPELG9UOVCgL"
			+ "/RYSvL+999SF2mYZki8cq2AtUCxl4b3TMdNUd53YfOMZALQtYZ7fnFYXW2z9bav5bT4kTp584osiTxcGBnE5YHQJfMDhfp"
			+ "4REIJb8ZyUW7ZbRoOF1FMwJ8nxk3r6VoceFomggUU9D1VHHCgxYwCWVglLHciPUv0uLlsx6JYNzkWS8v+B/cvUhoQS5594"
			+ "15Ut12CGSVKjnyze7R1nu62MX72V4p8X0EjNn6BAVMSHPczVfI/ai99+ym83seer5eeXz7pjOZTvyWnTkvCW0I6zdoFI3g"
			+ "ie3tVqdyk0zJ8avlPXTc9Rek+FRUrRe5YwqEMhm0wIDAQAB";

	// 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	//public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";
	public static String notify_url = "http://localhost:8080/alipay/notifyUrl";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/alipay/returnUrl";

	// 签名方式
	public static String sign_type = "RSA2";

	// 字符编码格式
	public static String charset = "utf-8";
	
	public static String format = "json";

	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

	//  日志地址
	public static String log_path = "D:\\logs\\";

//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	/**
	 * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
	 * 
	 * @param sWord 要写入日志里的文本内容
	 */
	public static void logResult(String sWord) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
			writer.write(sWord);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
