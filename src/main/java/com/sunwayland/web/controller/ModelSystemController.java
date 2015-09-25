package com.sunwayland.web.controller;

import java.util.Map;

import org.apache.log4j.Logger;
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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sunwayland.core.generic.GenericAction;
import com.sunwayland.core.utils.Utils;
import com.sunwayland.core.validate.type.Create;
import com.sunwayland.core.validate.type.Update;
import com.sunwayland.core.vo.WebPage;
import com.sunwayland.rest.ThingLinxRest;
import com.sunwayland.rest.eneityV2.Profile;
import com.sunwayland.rest.eneityV2.ProfileMessage;
import com.sunwayland.rest.eneityV2.SystemModel;
import com.sunwayland.rest.eneityV2.SystemModelDevice;
import com.sunwayland.rest.eneityV2.Tag;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.rest.params.QueryParams;
import com.sunwayland.rest.params.SuffixParams;
import com.sunwayland.rest.params.UrlParams;
import com.sunwayland.rest.url.SystemModelUrl;
import com.sunwayland.web.vo.Global;

@Controller
@RequestMapping("sysmodel")
@ResponseBody
//@SessionAttributes("user")
public class ModelSystemController extends GenericAction {

	@Autowired
	private ThingLinxRest rest;

	private Logger log = Logger.getLogger(ModelSystemController.class);

	// =============================== system Model
	// =================================

	/**
	 * 分页 显示 systemModel ;
	 * 
	 * @param user
	 * @param page
	 * @return
	 */
	
	@RequestMapping(method = RequestMethod.GET)
	public Object querySystemModel(@ModelAttribute(Global.session_key_user) User user, WebPage page) {

		page.setItemsPerPage(Global.max_ItemsPerPage);
		page.setCurrentPage(1);

		return rest.SystemModel.querySystemModel(user, QueryParams.get().limit(Global.max_ItemsPerPage).offset(0));

	}

	
	@RequestMapping(value = "/{pk}", method = RequestMethod.GET)
	public Object getSysModelByPk(@ModelAttribute(Global.session_key_user) User user,
			@PathVariable String pk) {

		return rest.SystemModel.get(user,
				SystemModelUrl.select,
				UrlParams.get().system_model_id(pk),
				SuffixParams.get()
				);

	}

	/**
	 * 
	 * 创建 system Model ; 创建默认 defaule profile
	 */
	
	@RequestMapping(method = RequestMethod.POST)
	public Object createSystemModel(@ModelAttribute(Global.session_key_user) User user,
			@Validated(Create.class) @RequestBody SystemModel systemModel,
			BindingResult result) {
		Utils.handlerBindngResult(result);

		// 创建 system Model ;
		Map s = rest.SystemModel.createSystemModel(user, systemModel.getName(), systemModel);
		// 创建默认 profile ;
		Profile p = new Profile();
		p.setDesc("Default Profile ");
		p.setName("Default Profile");
		p.setSystem_model(s.get(Global.ret_key).toString());

		Map s1 = rest.SystemModel.addProfile2SystemModel(user, p);
		return s;

	}

	/**
	 * 删除 systemModel ;
	 * 
	 * @param user
	 * @param model_id
	 * @return
	 */
	
	@RequestMapping(method = RequestMethod.DELETE)
	public Object deleteSystemModel(@ModelAttribute(Global.session_key_user) User user, String uuid) {

		return rest.SystemModel.deleteSystemModel(user , uuid);
	}

	/**
	 * update systemModel ;
	 * 
	 * @param user
	 * @param page
	 * @return
	 */

	
	@RequestMapping(method = RequestMethod.PUT)
	public Object updateSystemModel(@ModelAttribute(Global.session_key_user) User user,
			@Validated(Update.class) @RequestBody SystemModel systemModel,
			BindingResult result) {
		Utils.handlerBindngResult(result);

		return rest.SystemModel.updateSystemModel(user,	systemModel);
	}

	// =============================== system Model 下 device
	// =========================================================

	/**
	 * 得到 systemModel 下的 所有 device ;
	 */
	
	@RequestMapping(value = "/devices", method = RequestMethod.GET)
	public Object getAllDeviceOfSM(@ModelAttribute(Global.session_key_user) User user, String system_model) {

		return rest.SystemModel.getAllDeviceOfSystemModel(user, system_model);

	}

	/**
	 * system Model 下 添加 device ;
	 * 
	 * @return
	 */
	
	@RequestMapping(value = "/devices", method = RequestMethod.POST)
	public Object addDevice2SM(@ModelAttribute(Global.session_key_user) User user,
			  @RequestBody Map device
			  ) {
 
		return  rest.https.post(user, SystemModelUrl.addDevice2SM,
					device,
					UrlParams.get().system_model_id(device.get("system_model")),
					SuffixParams.get());
	 
 
		
		
	}

	/**
	 * 删除 systemmodel 下的 某一个 device
	 */
	
	@RequestMapping(value = "/devices", method = RequestMethod.DELETE)
	public Object deleteDeviceOfSM(@ModelAttribute(Global.session_key_user) User user, String system_model, // url
			// 参数;
			String id // device id url 参数;
	) {
		return rest.SystemModel.deleteDeviceOfSystemModel(user,system_model, id);
	}

	/**
	 * 更新 systemModel 下的 device ;
	 */
	
	@RequestMapping(value = "/devices", method = RequestMethod.PUT)
	public Object updateDeviceOfSM(@ModelAttribute(Global.session_key_user) User user,
			@RequestBody Map device
			) { 

		 
	     return rest.https.put(user, SystemModelUrl.updateDeviceOfSM,
					device,
					UrlParams.get()
							.system_model_id(device.get("system_model") )
							.device_id(device.get("id")),
					SuffixParams.get());

	}

	// =========================== systemModel 下 tag
	// ================================

	//
	
	@RequestMapping(value = "/tags", method = RequestMethod.POST)
	public Object addTag2SystemModel(@ModelAttribute(Global.session_key_user) User user,
			@Validated(Create.class) @RequestBody Tag tag, BindingResult result) {
		Utils.handlerBindngResult(result);
		return rest.SystemModel.addTag2SystemModel(user, tag);
	}

	
	@RequestMapping(value = "/tags", method = RequestMethod.GET)
	public Object getAllTagOfSystemModel(@ModelAttribute(Global.session_key_user) User user,
			String system_model) {
		return rest.SystemModel.getAllTagOfSystemModel(user,system_model);

	}

	
	@RequestMapping(value = "/tags", method = RequestMethod.PUT)
	public Object updateTagOfSystemModel(@ModelAttribute(Global.session_key_user) User user,
			@Validated(Update.class) @RequestBody Tag tag, BindingResult result) {

		Utils.handlerBindngResult(result);
		return rest.SystemModel.updateTagOfSystemModel(user , tag);
	}

	
	@RequestMapping(value = "/tags", method = RequestMethod.DELETE)
	public Object deleteTagOfSystemModel(@ModelAttribute(Global.session_key_user) User user, String id,
			String system_model) {

		return rest.SystemModel.deleteTagOfSystemModel(user,system_model, id);
	}

	// ======================== syustem Model 下的 profMesaage Center
	// ====================================================

	
	@RequestMapping(value = "/messages", method = RequestMethod.POST)
	public Object createSmMessage(@ModelAttribute(Global.session_key_user) User user,
			@Validated(Create.class) @RequestBody ProfileMessage smMessage,
			BindingResult result) {
		Utils.handlerBindngResult(result);

		return rest.SystemModel.createSmMessage(user,smMessage.getProfile(), smMessage);

	}

	
	@RequestMapping(value = "/messages", method = RequestMethod.GET)
	public Object getSmMessages(@ModelAttribute(Global.session_key_user) User user, String profile_id) {
		return rest.SystemModel.getSmMessages(user , profile_id);

	}

	
	@RequestMapping(value = "/messages", method = RequestMethod.DELETE)
	public Object deleteSmMessage(@ModelAttribute(Global.session_key_user) User user, String profile_id,
			String message_id) {
		return rest.SystemModel.deleteSmMessage(user,profile_id, message_id);
	}

	
	@RequestMapping(value = "/messages", method = RequestMethod.PUT)
	public Object updataSmMessage(@ModelAttribute(Global.session_key_user) User user,
			@Validated(Update.class) @RequestBody ProfileMessage message,
			BindingResult result) {
		Utils.handlerBindngResult(result);
		return rest.SystemModel.updataSmMessage(user, message);
	}

	// ===========================================================

}
