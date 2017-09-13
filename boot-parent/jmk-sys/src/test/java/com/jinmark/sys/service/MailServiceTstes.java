package com.jinmark.sys.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jinmark.sys.service.mail.MailServiceI;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTstes {
	
	@Autowired
	private MailServiceI mailService;
	
	@Test
	public void testSimpleMail() throws Exception {
		mailService.sendSimpleMail("ityouknow@126.com","test simple mail"," hello this is simple mail");
	}
	
	@Test
	public void testHtmlMail() throws Exception {
	    String content="<html>\n" +
	            "<body>\n" +
	            "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
	            "</body>\n" +
	            "</html>";
	    mailService.sendHtmlMail("ityouknow@126.com","test simple mail",content);
	}
	
	@Test
	public void sendAttachmentsMail() {
	    String filePath="e:\\tmp\\application.log";
	    mailService.sendAttachmentsMail("ityouknow@126.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
	}
	
	@Test
	public void sendInlineResourceMail() {
	    String rscId = "neo006";
	    String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
	    String imgPath = "C:\\Users\\summer\\Pictures\\favicon.png";
	 
	    mailService.sendInlineResourceMail("ityouknow@126.com", "主题：这是有图片的邮件", content, imgPath, rscId);
	}
	
	@Test
	public void sendTemplateMail() {
	    //创建邮件正文
	    /*Context context = new Context();
	    context.setVariable("id", "006");
	    String emailContent = templateEngine.process("mail/emailTemplate", context);
	 
	    mailService.sendHtmlMail("ityouknow@126.com","主题：这是模板邮件",emailContent);*/
	}
	/**
	发送失败

	因为各种原因，总会有邮件发送失败的情况，比如：邮件发送过于频繁、网络异常等。在出现这种情况的时候，我们一般会考虑重新重试发送邮件，会分为以下几个步骤来实现：

	1、接收到发送邮件请求，首先记录请求并且入库。
	2、调用邮件发送接口发送邮件，并且将发送结果记录入库。
	3、启动定时系统扫描时间段内，未发送成功并且重试次数小于3次的邮件，进行再次发送

	异步发送

	很多时候邮件发送并不是我们主业务必须关注的结果，比如通知类、提醒类的业务可以允许延时或者失败。这个时候可以采用异步的方式来发送邮件，加快主交易执行速度，在实际项目中可以采用MQ发送邮件相关参数，监听到消息队列之后启动发送邮件。
	**/
}
