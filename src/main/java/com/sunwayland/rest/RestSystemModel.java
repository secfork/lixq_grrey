package com.sunwayland.rest;

import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.sunwayland.rest.basic.HttpsRest;
import com.sunwayland.rest.eneityV2.Profile;
import com.sunwayland.rest.eneityV2.ProfileMessage;
import com.sunwayland.rest.eneityV2.SystemModel;
import com.sunwayland.rest.eneityV2.SystemModelDevice;
import com.sunwayland.rest.eneityV2.Tag;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.rest.params.GenericParams;
import com.sunwayland.rest.params.SuffixParams;
import com.sunwayland.rest.params.UrlParams;
import com.sunwayland.rest.url.SystemModelUrl;

public class RestSystemModel extends HttpsRest {

	public RestSystemModel(RestTemplate restTemplate, String base_url) {
		super(restTemplate, base_url);
	}

	// ============================ system model ============================

	public Map createSystemModel(User user, String systemModelName, SystemModel systemModel) {
		return this.post(user, SystemModelUrl.create,
				systemModel,
				UrlParams.get(),
				SuffixParams.get().name(systemModelName));

	}

	public Map updateSystemModel(User user, SystemModel systemModel) {

		return this.put(user, SystemModelUrl.update,
				systemModel,
				UrlParams.get().system_model_id(systemModel.getUuid()),
				SuffixParams.get());

	}

	public Map deleteSystemModel(User user, String systemModelId) {
		if (null == systemModelId)
			return null;

		return this.delete(user, SystemModelUrl.delete,
				null,
				UrlParams.get().system_model_id(systemModelId),
				SuffixParams.get());

	}

	public Map querySystemModel(User user, GenericParams query) {
		return this.get(user, SystemModelUrl.query, UrlParams.get(), query);

	}


	public Map getAllDeviceOfSystemModel(User user, String systemMOdelId) {
		if (null == systemMOdelId)
			return null;
		return this.get(user, SystemModelUrl.selectAllDeviceOfSM,
				UrlParams.get().system_model_id(systemMOdelId),
				SuffixParams.get());

	}



	public Map deleteDeviceOfSystemModel(User user, String systemModelId, String deviceId) {
		if (null == systemModelId || null == deviceId)
			return null;

		return this.delete(user, SystemModelUrl.deleteDeviceOfSM,
				null,
				UrlParams.get().system_model_id(systemModelId).device_id(deviceId),
				SuffixParams.get());

	}

	// ====================== system model 下 的 profile ===============

	public Map addProfile2SystemModel(User user, Profile profile) {
		return this.post(user, SystemModelUrl.addProfile2SM,
				profile,
				UrlParams.get(),
				SuffixParams.get());
	}

	public Map getALLProfileOfSystemModel(User user, String systemModelId) {
		if (null == systemModelId)
			return null;
		return this.get(user, SystemModelUrl.slelectAllProfileOfSM,
				UrlParams.get(),
				SuffixParams.get().system_model(systemModelId));

	}

	public Map updateProfleOfSystemModel(User user, Profile profile) {
		return this.put(user, SystemModelUrl.updateProfileOfSM,
				profile,
				UrlParams.get().profile_id(profile.getUuid()),
				SuffixParams.get());

	}

	// 无 删除借口???
	// ======================= system Model 下的 tag ===================

	public Map addTag2SystemModel(User user, Tag tag) {

		return this.post(user, SystemModelUrl.addTag2SM,
				tag,
				UrlParams.get().system_model_id(tag.getSystem_model()),
				SuffixParams.get());
	}

	public Map getAllTagOfSystemModel(User user, String systemModelid) {
		if (null == systemModelid)
			return null;
		return this.get(user,
				SystemModelUrl.selectAllTagOfSM,
				UrlParams.get().system_model_id(systemModelid),
				SuffixParams.get());
	}

	public Map updateTagOfSystemModel(User user, Tag tag) {
		return this.put(user, SystemModelUrl.updateTagOfSM,
				tag,
				UrlParams.get().system_model_id(tag.getSystem_model()).tag_id(tag.getId()),
				SuffixParams.get());

	}

	public Map deleteTagOfSystemModel(User user, String system_model, String tagid) {
		if (null == tagid || null == system_model)
			return null;

		return this.delete(user, SystemModelUrl.deleteTagOfSM,
				null,
				UrlParams.get().system_model_id(system_model).tag_id(tagid),
				SuffixParams.get());

	}

	public Map deleteProfile(User user, String profile_uuid) {
		return this.delete(user, SystemModelUrl.deleteProfile2System,
				null,
				UrlParams.get().profile_id(profile_uuid),
				SuffixParams.get());

	}

	// ==================== sysModel 下 的 message
	// ==========================================================

	public Map createSmMessage(User user, String profile_id,
			ProfileMessage systemModelMessage) {

		return this.post(user, SystemModelUrl.createSmMessage,
				systemModelMessage,
				UrlParams.get().profile_id(profile_id),
				SuffixParams.get());
	}

	public Map getSmMessages(User user, String profile_id) {

		if (null == profile_id)
			return null;

		return this.get(user, SystemModelUrl.getSmMessage,
				UrlParams.get().profile_id(profile_id),
				SuffixParams.get());
	}

	public Map deleteSmMessage(User user, String profile_id, String sysMessageId) {

		if (null == profile_id || null == sysMessageId)
			return null;
		return this.delete(user, SystemModelUrl.deleteSmMessage,
				null,
				UrlParams.get().profile_id(profile_id).msgprofile_id(sysMessageId),
				SuffixParams.get());
	}

	public Map updataSmMessage(User user, ProfileMessage message) {

		return this.put(user, SystemModelUrl.updataSmMessage,
				message,
				UrlParams.get()
						.profile_id(message.getProfile())
						.msgprofile_id(message.getMessage_id()),
				SuffixParams.get());
	}

}
