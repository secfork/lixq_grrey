package com.sunwayland.rest;

import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.sunwayland.rest.basic.HttpsRest;
import com.sunwayland.rest.eneityV2.Tag;
import com.sunwayland.rest.eneityV2.Trigger;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.rest.params.SuffixParams;
import com.sunwayland.rest.params.UrlParams;
import com.sunwayland.rest.url.SystemModelProfileTagUrl;
import com.sunwayland.rest.url.SystemModelProfileTriggerUrl;

public class RestSystemModelProfile extends HttpsRest {

	public RestSystemModelProfile(RestTemplate restTemplate, String base_url) {
		super(restTemplate, base_url);

	}

	// ==================== systemModel tag 在 profile 下 设置 log , ==============

	public Map setSysModelTag2ProfleLog(User user, String profile_id, String tag_id, Tag logTag) {
		if (null == profile_id || null == tag_id)
			return null;
		return this.put(user, 
				SystemModelProfileTagUrl.addLogTag2Profile, 
				logTag,
				UrlParams.get().profile_id(profile_id).tag_id(tag_id), 
				SuffixParams.get());
	}

	public Map getAllLogPointOfProfile(User user, String profile_id) {
		if (null == profile_id)
			return null;
		return this.get(user, SystemModelProfileTagUrl.selectAllTagOfSysProfile, 
				UrlParams.get().profile_id(profile_id), 
				SuffixParams.get());

	}

	public Map updateProfLogTag(User user, Tag tag) {

		return this.put(user, SystemModelProfileTagUrl.updateLogTagOfSysProfile, tag,
				UrlParams.get().profile_id(tag.getProfile()).tag_id(tag.getId()),
				SuffixParams.get());
	}

	public Map deleteTagOfSystemModelProfile(User user, String profile_id, String tag_id) {

		return this.delete(user, SystemModelProfileTagUrl.deleteLogTagOfProfile, null,
				UrlParams.get().profile_id(profile_id).tag_id(tag_id),
				SuffixParams.get());

	}

	// ============== systemModel profle 下的 trigger ==========================

	public Map addTrigger2SystemModelProfle(User user, Trigger trigger) {
		return this.post(user, SystemModelProfileTriggerUrl.addTrigger2SysProflie, trigger,
				UrlParams.get().profile_id(trigger.getProfile()),
				SuffixParams.get());
	}

	public Map getAllTriggerOfSMProfile(User user, String profile_id) {
		if (null == profile_id)
			return null;
		return this.get(user, SystemModelProfileTriggerUrl.selectAllTriggerOfSysProfile,
				UrlParams.get().profile_id(profile_id), 
				SuffixParams.get());
	}

	public Map updateTriggerOfSMProfile(User user, Trigger trigger) {

		return this.put(user, SystemModelProfileTriggerUrl.updateTrigerOfSysProfile, trigger, 
				UrlParams.get().profile_id(trigger.getProfile()).trigger_id(trigger.getId()),

				SuffixParams.get());
	}

	public Map deleteTriggerOfSMProfile(User user, String profileId, String triggerid) {

		return this.delete(user, 
				SystemModelProfileTriggerUrl.deleteTrigerOfSysProfile,
				null,
				UrlParams.get().profile_id(profileId).trigger_id(triggerid),
				SuffixParams.get());
	}

}
