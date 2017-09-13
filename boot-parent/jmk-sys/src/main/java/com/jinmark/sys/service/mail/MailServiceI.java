package com.jinmark.sys.service.mail;
/**
 * 
 * @author QC
 * @ClassName MailServiceI
 * @Description TODO(发送邮件service) 
 * @date 2017年8月25日下午5:06:25
 */
public interface MailServiceI {
	/**
	 * 
	 * @Title sendSimpleMail
	 * @Description TODO(发送简单邮件) 
	 * @param to 发给谁
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @return void  返回类型 
	 * @throws
	 */
	void sendSimpleMail(String to, String subject, String content);
	/**
	 * 
	 * @Title sendHtmlMail
	 * @Description TODO(发送html格式邮件) 
	 * @param to
	 * @param subject
	 * @param content
	 * @return void  返回类型 
	 * @throws
	 */
	void sendHtmlMail(String to, String subject, String content);
	/**
	 * 
	 * @Title sendAttachmentsMail
	 * @Description TODO(发送带附件的邮件) 
	 * @param to
	 * @param subject
	 * @param content
	 * @param filePath
	 * @return void  返回类型 
	 * @throws
	 */
	void sendAttachmentsMail(String to, String subject, String content, String filePath);
	/**
	 * 
	 * @Title sendInlineResourceMail
	 * @Description TODO(发送带静态资源的邮件) 
	 * @param to
	 * @param subject
	 * @param content
	 * @param rscPath
	 * @param rscId
	 * @return void  返回类型 
	 * @throws
	 */
	void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
}
