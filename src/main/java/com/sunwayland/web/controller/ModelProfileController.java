package com.sunwayland.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunwayland.core.utils.Utils;
import com.sunwayland.core.validate.type.Create;
import com.sunwayland.core.validate.type.Update;
import com.sunwayland.rest.ThingLinxRest;
import com.sunwayland.rest.eneityV2.Profile;
import com.sunwayland.rest.eneityV2.Tag;
import com.sunwayland.rest.eneityV2.Tag.profileLog;
import com.sunwayland.rest.eneityV2.Trigger;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.web.exceptionhandler.ExceptionHandl;
import com.sunwayland.web.vo.Global;

@Controller
@RequestMapping("profile")
@ResponseBody
//@SessionAttributes("user")
public class ModelProfileController extends ExceptionHandl {

	@Autowired
	private ThingLinxRest rest;

	private Logger log = Logger.getLogger(ModelProfileController.class);

	// ========================= profile
	// ===============================================

	
	@RequestMapping(method = RequestMethod.POST)
	public Object addProfile2SystemModel(@ModelAttribute(Global.session_key_user) User user,
			@Validated(Create.class) @RequestBody Profile profile,
			BindingResult result) {
		
		Utils.handlerBindngResult(result);
		return rest.SystemModel.addProfile2SystemModel(user, profile);
		
	}

	
	@RequestMapping(method = RequestMethod.GET)
	public Object getAllProfileOfSystemModel(@ModelAttribute(Global.session_key_user) User user, String system_model) {

		return rest.SystemModel.getALLProfileOfSystemModel(user, system_model);
	}

	
	@RequestMapping(method = RequestMethod.PUT)
	public Object updateProfileOfSystemModel(
			@ModelAttribute(Global.session_key_user) User user,
			@Validated(Update.class) @RequestBody Profile profile,
			BindingResult result) {

		Utils.handlerBindngResult(result);
		return rest.SystemModel.updateProfleOfSystemModel(user, profile);
	}

	
	@RequestMapping(method = RequestMethod.DELETE)
	public Object deleteProfile(@ModelAttribute(Global.session_key_user) User user,
			@Validated(Update.class) Profile profile, BindingResult result) {
		
		Utils.handlerBindngResult(result);
		return rest.SystemModel.deleteProfile(user, profile.getUuid());
		// return "rest 未 实现!";
	}

	// ========================= profile 下 的 log tag
	// ===============================================
	/**
	 * profile 下 log 点;
	 */
	
	@RequestMapping(value = "/tags", method = RequestMethod.POST)
	public Object addTagLog2Profile(@ModelAttribute(Global.session_key_user) User user,
			@Validated(profileLog.class) @RequestBody Tag tag,
			BindingResult result) {
		Utils.handlerBindngResult(result);
		return rest.profile.setSysModelTag2ProfleLog(user , tag.getProfile(), tag.getId(), tag);
	}

	
	@RequestMapping(value = "/tags", method = RequestMethod.PUT)
	public Object updateLogTag(@ModelAttribute(Global.session_key_user) User user,
			@Validated(profileLog.class) @RequestBody Tag tag,
			BindingResult result) {
		Utils.handlerBindngResult(result);
		return rest.profile.updateProfLogTag(user , tag);

	}

	
	@RequestMapping(value = "/tags", method = RequestMethod.GET)
	public Object getAllTagsOfProfLog(@ModelAttribute(Global.session_key_user) User user, String profile) {
		 
		return rest.profile.getAllLogPointOfProfile( user , profile);

	}

	
	@RequestMapping(value = "/tags", method = RequestMethod.DELETE)
	public Object deleteLogTagOfProfile(@ModelAttribute(Global.session_key_user) User user,
			@Validated(profileLog.class) Tag tag, BindingResult result) {
		Utils.handlerBindngResult(result);
		return rest.profile.deleteTagOfSystemModelProfile(user,	tag.getProfile(), tag.getId());

	}

	// ========================= profile 下 的 trigger
	// =======================================

	
	@RequestMapping(value = "/triggers", method = RequestMethod.POST)
	public Object addTrigger2Profile(@ModelAttribute(Global.session_key_user) User user,
			@Validated(Create.class) @RequestBody Trigger trigger,
			BindingResult result) {
		Utils.handlerBindngResult(result);

		return rest.profile.addTrigger2SystemModelProfle(user, trigger);
	}

	
	@RequestMapping(value = "/triggers", method = RequestMethod.GET)
	public Object getAllTriggersOfProfile(@ModelAttribute(Global.session_key_user) User user, String profile) {
		return rest.profile.getAllTriggerOfSMProfile(user , profile);

	}

	
	@RequestMapping(value = "/triggers", method = RequestMethod.PUT)
	public Object updateTrigerOfProfile(@ModelAttribute(Global.session_key_user) User user,
			@Validated(Update.class) @RequestBody Trigger trigger,
			BindingResult result) {
		Utils.handlerBindngResult(result);
		return rest.profile.updateTriggerOfSMProfile(user, trigger);
	}

	
	@RequestMapping(value = "/triggers", method = RequestMethod.DELETE)
	public Object deleteTrigerOfProfile(@ModelAttribute(Global.session_key_user) User user,
			@Validated(Update.class) Trigger trigger, BindingResult result) {
		Utils.handlerBindngResult(result);

		return rest.profile.deleteTriggerOfSMProfile(user,trigger.getProfile(), trigger.getId());
	}

}
