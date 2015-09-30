package com.sunwayland.web.controller;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunwayland.core.JsOrder.JsOrder;
import com.sunwayland.core.generic.GenericAction;
import com.sunwayland.core.utils.RandPicture;
import com.sunwayland.rest.ThingLinxRest;
import com.sunwayland.rest.eneityV2.Account;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.rest.params.SuffixParams;
import com.sunwayland.rest.params.UrlParams;
import com.sunwayland.rest.url.OrtherUrl;
import com.sunwayland.web.vo.ErrCode;
import com.sunwayland.web.vo.Global;
import com.sunwayland.web.vo.ServiceResult;

@Controller
//@SessionAttributes("user")
public class CommonController extends GenericAction {

	Logger log = Logger.getLogger(CommonController.class);
 

	// 验证码;
	@Autowired
	private RandPicture Picture;

	// @Autowired
	// private ILicenceService LicenceService ;

	/**
	 * 验证未通过! 重新登录;
	 * 
	 * @return
	 */
	@RequestMapping("err/404")
	@ResponseBody
	public Object page_404() {
		log.info("404 page");
		return RESP_ERR("404");
	}

	/* account 注册 验证码 生成 */
	@ResponseBody
	@RequestMapping("common/identify_img/{m}")
	public void identifyPic(HttpServletResponse response, HttpSession session)
			throws IOException {
		// 设置不缓存图片
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		// 指定生成的相应图片
		response.setContentType("image/jpeg");

		Object[] randPicture = Picture.getRandPicture();
		session.setAttribute(Global.session_key_identifypic, randPicture[1]);

		ImageIO.write((RenderedImage) randPicture[0], "JPEG", response.getOutputStream());
	}

	/* 登录 验证码 生成 */

	@RequestMapping("common/verifi/{m}")
	public void verifiCode(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session) throws IOException {

		// 设置不缓存图片
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		// 指定生成的相应图片
		response.setContentType("image/jpeg");

		Object[] randPicture = Picture.getRandPicture();

		// Session session = SecurityUtils.getSubject().getSession();

		session.setAttribute(Global.session_key_verifi, randPicture[1]);
		session.setAttribute(Global.session_key_login_time, 1000);

		ImageIO.write((RenderedImage) randPicture[0], "JPEG", response.getOutputStream());

	}

	// ===========================注册 Account 用户 ( 企业 注册 )
	// ===============================
	/**
	 * account 注册; 之后创建报警空间;
	 * 
	 * @param account
	 * @param session
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "common/regist", method = RequestMethod.POST)
	@ResponseBody
	public Object createAccount(@Validated @RequestBody Account account
			 ,HttpSession session
			) {

		//Session session = SecurityUtils.getSubject().getSession();

		String identfy = (String) session.getAttribute(Global.session_key_identifypic);

		if (!identfy.equalsIgnoreCase(account.getIdentifyCode())) {
			return RESP_ERR(ErrCode.indefifyErr);
			// return result ;
		}

		log.info("regist" + account);
		// 添加 User 用户!
		// ServiceResult result = UserService.createCompany(account);

		// 创建 报警空间; 还灭有 accesskey ;
		log.info(" 创建  报警空间!");

		rest.http.post(OrtherUrl.alarmSpabe,
				null,
				UrlParams.get().account_id(account.getId()),
				SuffixParams.get()
				);

		return null;
	}

	// ======================是否已经登录======================================
	@RequestMapping(value = "common/islogined")
	@ResponseBody
	public Object isLoaded( @ModelAttribute(Global.session_key_user) User user ) { 
	 
		return RESP(true);

	}

}
