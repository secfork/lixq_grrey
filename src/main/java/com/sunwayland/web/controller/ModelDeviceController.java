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

import com.sunwayland.core.generic.GenericAction;
import com.sunwayland.core.utils.Utils;
import com.sunwayland.core.validate.type.Update;
import com.sunwayland.rest.ThingLinxRest;
import com.sunwayland.rest.eneityV2.DeviceModel;
import com.sunwayland.rest.eneityV2.Point;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.rest.params.QueryParams;
import com.sunwayland.rest.params.SuffixParams;
import com.sunwayland.rest.params.UrlParams;
import com.sunwayland.rest.url.DeviceModelUrl;
import com.sunwayland.web.vo.Global;


/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("devmodel") 
@ResponseBody
//@SessionAttributes("user")
public class ModelDeviceController extends GenericAction {
 

    private Logger log = Logger.getLogger(ModelDeviceController.class);
	
    @Autowired
	public  ThingLinxRest  rest ; 
	
    
	@RequestMapping(  method=RequestMethod.POST)
	public Object  createDeviceModel  (	
										@ModelAttribute(Global.session_key_user) User user ,
										@RequestBody Map deviceModel 
										){ 
		
		deviceModel.put("account_id", user.getAccount_id());
		 
		
		return rest.https.post(
				user,
				DeviceModelUrl.create,
				deviceModel,
				UrlParams.get(),
				SuffixParams.get()
		);
		 
		
	}
	
	// 分页  device model ;
	
	@RequestMapping(   method=RequestMethod.GET )
	public  Object  getAllDeviceModel (  @ModelAttribute(Global.session_key_user) User user 
//										 , WebPage page  
					   ){
		
//		page.setCurrentPage(1); 
//		page.setItemsPerPage(200); 
		log.info(" 直支持 1000 个 device Model ");  
		return  rest.deviceModel.queryDeviceModel(user,
												 QueryParams.get().offset(0).limit(Global.max_ItemsPerPage));
		
//		return   rest.deviceModel.query(user.getAccessKey(), DeviceModelUrl.query, page);
		
	}
	 
	
	@RequestMapping( value="/{pk}" , method=RequestMethod.GET )
	public Object getDeviceModelByPK (  @ModelAttribute(Global.session_key_user) User user,
										@PathVariable String pk 
									  ){  
		   return   rest.deviceModel.getByPk ( user , pk );
		  
	}
	
	
	 
	
	@RequestMapping(  method=RequestMethod.PUT)
	public Object updateDevoceModel (  @ModelAttribute(Global.session_key_user) User user,
										@Validated( Update.class) @RequestBody DeviceModel  deviceModel ,
										BindingResult result
											){
		Utils.handlerBindngResult(result); 
		return rest.deviceModel.updateDeviceModel(user,  deviceModel);
	}
	
	
	@RequestMapping(   method=RequestMethod.DELETE)
	public Object deleteDeviceModel ( @ModelAttribute(Global.session_key_user) User user,  
											String  uuid 
											){
		 
		return rest.deviceModel.deleteDeviceModel(user, uuid);
	}

    //============================= device model  下 的 point ===================================================

    
	@RequestMapping( value="/points",  method=RequestMethod.POST)
	public Object  createPointOfDeviceModel  ( @ModelAttribute(Global.session_key_user) User user ,
										   @RequestBody Map point 
										){ 
		
		return rest.https.post(user, DeviceModelUrl.addPoint2DeviceModel,
				point, 
				UrlParams.get().device_model_id(point.get("device_model")),
				null);
		
		  
		
	}
	
	
	
	@RequestMapping(  value="/points", method=RequestMethod.GET )
	public  Object  getAllPointsOfDeviceModel ( 
										 @ModelAttribute(Global.session_key_user) User user , 
										 String  device_model
									 ){ 
		
		return   rest.deviceModel.getAllPointOfDeviceModel(user , device_model );
	}
	
	 
	
	@RequestMapping( value="/points", method=RequestMethod.PUT)
	public Object updatePointOfDeviceModel (  @ModelAttribute(Global.session_key_user) User user,
											   @RequestBody Map  point   
											){ 
		 
	     return rest.https.put( user ,
							DeviceModelUrl.updatePointOfDeviceModel, 
							point,
							UrlParams.get()
							      .device_model_id(point.get("device_model"))
							      .point_id(point.get("id")), 
							SuffixParams.get());
	  
	}
	
	
	@RequestMapping( value="/points",  method=RequestMethod.DELETE)
	public Object deletePointFromDeviceModel ( @ModelAttribute(Global.session_key_user) User user,  
											@Validated( Update.class) Point  point ,
											BindingResult result
											){
		 Utils.handlerBindngResult(result);
		return rest.deviceModel.deletePointOfDeviceModel(user, point.getDevice_model(), point.getId());
	}

    
}
