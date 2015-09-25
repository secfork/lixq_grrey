package com.sunwayland.web.controller;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;
import com.sunwayland.core.generic.GenericAction;
import com.sunwayland.core.vo.WebPage;
import com.sunwayland.rest.ThingLinxRest;
import com.sunwayland.rest.eneityV2.Region;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.rest.params.SuffixParams;
import com.sunwayland.rest.params.UrlParams;
import com.sunwayland.rest.url.ReginUrl;
import com.sunwayland.web.vo.Global;

@Controller
@RequestMapping("region")
@ResponseBody
//@SessionAttributes("user")
public class RegionController extends GenericAction {

	Logger log = Logger.getLogger(RegionController.class);

	@Autowired
	private ThingLinxRest rest;

	private Gson gson = new Gson();

	/**
	 * 创建 project ;
	 * 
	 * @throws Exception
	 */
	
	@RequestMapping(method = RequestMethod.POST)
	public Object createRegion(@RequestBody Region region,
			@ModelAttribute(Global.session_key_user) User user
			) throws Exception {

		return rest.https.post(user, ReginUrl.create, region, null, null);

	}
	
	
	@RequestMapping(  method = RequestMethod.PUT)
	public Object updateRegion(
			@ModelAttribute(Global.session_key_user) User User,
			@RequestBody Region region,
			@PathVariable Long region_id) throws Exception {

		return rest.https.get(User, ReginUrl.get_update_del_ById, UrlParams.get().region_id(region_id), null);

	}
 
	
	@RequestMapping(method = RequestMethod.GET)
	public Object queryRegions(
			@ModelAttribute(Global.session_key_user) User user,
			HttpServletRequest requset ,
			WebPage page) {
 
		return rest.https.query(user,ReginUrl.query, null,
							SuffixParams.get().calc_sum(true),
							SuffixParams.get().limit(page.getLimit()).offset(page.getOffset())
							); 
	}
	
	
	
	
	@RequestMapping(value = "/{region_id:[0-9]+}", method = RequestMethod.DELETE)
	public Object deleteRegionById(
			@ModelAttribute(Global.session_key_user) User User,
			@PathVariable Long region_id) throws Exception {

		return rest.https.delete(User, ReginUrl.get_update_del_ById, null,
				UrlParams.get().region_id(region_id), null);
	}

	
	@RequestMapping(value = "/{region_id:[0-9]+}", method = RequestMethod.GET)
	public Object getRegionById(
			@ModelAttribute(Global.session_key_user) User User,
			@PathVariable Long region_id) throws Exception {

		return rest.https.get(User, ReginUrl.get_update_del_ById, UrlParams.get().region_id(region_id), null);

	}



	

}
