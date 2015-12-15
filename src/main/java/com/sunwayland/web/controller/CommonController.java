package com.sunwayland.web.controller;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sun.mail.util.UUDecoderStream;
import com.sunwayland.core.generic.GenericAction;
import com.sunwayland.core.utils.RandPicture;
import com.sunwayland.core.utils.SimpleMail;
import com.sunwayland.core.utils.Utils;
import com.sunwayland.rest.ThingLinxRest;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.rest.params.UrlParams;
import com.sunwayland.web.vo.ErrCode;
import com.sunwayland.web.vo.Global;

@Controller
@ResponseBody
public class CommonController extends GenericAction {

	Logger log = Logger.getLogger(CommonController.class);
	
	@Autowired
	public  ThingLinxRest  rest ; 
	

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
	public Object page_404() {
		log.info("404 page");
		return RESP_ERR("404");
	}

	/*  验证码 生成 */ 
	
	@RequestMapping(value = "common/identify/{to}", method = RequestMethod.GET)
	public void identify(
			HttpServletResponse response, HttpSession session,
			@PathVariable String to
			)
					throws IOException {
		// 设置不缓存图片
		// response.setHeader("Pragma", "No-cache");
		// response.setHeader("Cache-Control", "No-cache");
		// response.setDateHeader("Expires", 0);
		// 指定生成的相应图片
		
		
		
		response.setContentType("image/jpeg");

		Object[] randPicture = Picture.getRandPicture();

		if (StringUtils.equalsIgnoreCase(to, "login")) { // 登录
			
			session.setAttribute(Global.identifypic_login, randPicture[1]);
			
		}
		if (StringUtils.equalsIgnoreCase(to, "admin")) { // 修改 accoutn
															// admin用户密码;
			session.setAttribute(Global.identifypic_update_admin, randPicture[1]);
			
		}
		
		if( StringUtils.equalsIgnoreCase(to, "account")){ // account 注册;  
			session.setAttribute(Global.identifypic_create_account, randPicture[1] ); 
		}
		 
		
		
		ImageIO.write((RenderedImage) randPicture[0], "JPEG", response.getOutputStream());
	}

	/* 登录 验证码 生成 */

	// ======================是否已经登录======================================
	@RequestMapping(value = "common/islogined")
	public Object isLoaded(
			// @ModelAttribute(Global.session_key_user) User user,
			HttpServletRequest request ,
			SessionStatus  sessionStatus
			) { 
		
		HttpSession session = request.getSession();
		
		Object attribute = session.getAttribute(Global.session_key_usermap);
		
		if( null == attribute ){
			// sessionStatus.setComplete();
			return RESP(false);
		}else{
			log.info("是否已经登录 " + attribute );
			  
			return RESP( attribute );
		}
		 

	}

	// 验证  图片验证码; 
	@RequestMapping(value = "common/verify", method = RequestMethod.GET)
	public Object Identify_test(
			String code,
			HttpSession session) {

		Object attribute = session.getAttribute(Global.identifypic_login);
		if (null == attribute) {
			return RESP(false);
		}
		String sk = (String) attribute;

		return RESP(StringUtils.equalsIgnoreCase(sk, code));
	}

	@RequestMapping(value = "common/admin", params = { "account", "identify" },
			method = RequestMethod.GET)
	public Object getAdminUserByAccountName(
			HttpServletRequest request ,
			String account,
			String identify
			) throws UnsupportedEncodingException {

		HttpSession session = request.getSession();
		
		Object _identify = session.getAttribute(Global.identifypic_update_admin);
		
		
		if (null == _identify || !StringUtils.equalsIgnoreCase(identify, (CharSequence) _identify)) {
			return RESP_ERR(ErrCode.identify_err);
		}

		account =  new String( account.getBytes("iso-8859-1") , "utf-8");
		
		Map map = rest.http.get("/accounts/{account_name}/super_user",
				
				UrlParams.get().put("account_name", account),
				null);

		Object map1 = map.get("ret");
		if (null == map1) {
			return RESP_ERR(ErrCode.no_account);
		}

		String  uuid =Utils.randomStr(32);
		String  sid  = session.getId();
		
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		Object web_site = context.getBean("web_site"); 
	
		
		 
		String email;
		try{
			email = (String) ((Map) map1).get("email");
			
		}catch( Exception e ){
			// 无预留 email ; 
			return RESP_ERR(ErrCode.no_account_email);
		}
		
		
		String  url  =  web_site + "/#/access/forgotpwd?v=" + uuid +"&s=" + sid ;
		
		try { 
			SimpleMail.sendAccountForgetPassEmail ( email , url );
		} catch (Exception e) {
			// 预留email 无效; 
			return RESP_ERR( ErrCode.send_maill_err	);
		} 
		  
		// 移除 session 验证码;
		session.removeAttribute(Global.identifypic_update_admin);
		// 
		session.setAttribute(Global.hold_account_name, account);
		
		session.setAttribute(uuid, true ); 
		
		return RESP(true);
	}
	 
	// =========== 更改 account的admin 用户 ==========
	@RequestMapping(value = "common/admin", params={"uuid"}, method = RequestMethod.POST)
	public Object updateAdminUser(
			HttpServletRequest request ,
			@Validated @RequestBody User newAdmin,
			BindingResult result,
			String uuid 
			) {
		Utils.handlerBindngResult(result);
		
		HttpSession session = request.getSession();
		
		Object attribute = session.getAttribute( uuid );
		if( null == attribute){
			return RESP_ERR(ErrCode.page_expire);
		}
		 
		// 短信验证码; 
		// Object verify = session.getAttribute(Global.verify_update_admin);  
		// 内部接口;
		Object account_name = session.getAttribute(Global.hold_account_name);
		if( null == account_name){
			return  RESP_ERR(ErrCode.page_expire);
		}
			
		
//		newAdmin.setVerifi(null);
	 
			newAdmin.setIs_super_user("ture");
			Map map = rest.http.put("/accounts/{account}/super_user", newAdmin,
										UrlParams.get().put("account", account_name),
										null);
		   
			session.removeAttribute(uuid);
			session.removeAttribute(Global.hold_account_name);
			return  RESP(true);
	 

	}

	@ResponseBody
	@RequestMapping(value = "common/logintimes", method = RequestMethod.GET)
	public Object getLoginTiems(HttpSession session) {

		Object attribute = session.getAttribute(Global.session_key_login_time);
		return RESP(attribute);
	}

}