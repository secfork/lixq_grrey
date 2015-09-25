package com.sunwayland.rest.params;

import com.sunwayland.rest.basic.FieldEnum;
import com.sunwayland.rest.basic.VarFieldEnum;

public abstract class Params extends GenericParams {

	// public Params accessKey(String value) {
	// this.getParams().put("accesskey", value);
	// return this;
	// }

	// ---------------

	public Params system_model_id(Object value) {
		this.put(VarFieldEnum.system_model_id.toString(), value);
		return this;
	}

	public Params system_model(String value) {
		this.put(VarFieldEnum.system_model.toString(), value);
		return this;
	}

	public Params device_id(Object value) {
		this.put(VarFieldEnum.device_id.toString(), value);
		return this;
	}
	
	//user_id
	
	public Params user_id(String value) {
		this.put("user_id", value);
		return this;
	}

	public Params profile_id(String value) {
		this.put(VarFieldEnum.profile_id.toString(), value);
		return this;
	}

	public Params msgprofile_id(String value) {
		this.put(VarFieldEnum.msgprofile_id.toString(), value);
		return this;
	}

	public Params tag_id(String value) {
		this.put(VarFieldEnum.tag_id.toString(), value);
		return this;
	}

	public Params tag(String value) {
		this.put(VarFieldEnum.tag.toString(), value);
		return this;
	}

	public Params region_id(Object region_id) {
		this.put("region_id", region_id );
		return this;
	}
	public Params role_id(Object region_id) {
		this.put("role_id", region_id );
		return this;
	}

	public Params point_id(Object value) {
		this.put(VarFieldEnum.point_id.toString(), value);
		return this;
	}

	public Params trigger_id(String value) {
		this.put(VarFieldEnum.trigger_id.toString(), value);
		return this;
	}

	public Params device_model_id(Object value) {
		this.put(VarFieldEnum.device_model_id.toString(), value);
		return this;
	}

	public Params driver_uuid(String value) {
		this.put(VarFieldEnum.driver_uuid.toString(), value);
		return this;
	}

	// =============
	public Params system_id(String value) {
		this.put(FieldEnum.system_id.toString(), value);
		return this;
	}

	public Params id(String value) {
		this.put(FieldEnum.id.toString(), value);
		return this;
	}

	public Params uuid(String value) {
		this.put(FieldEnum.uuid.toString(), value);
		return this;
	}

	public Params name(String value) {
		this.put(FieldEnum.name.toString(), value);
		return this;
	}

	public Params profile(String value) {
		this.put(FieldEnum.profile.toString(), value);
		return this;
	}

	public Params comm_type(String value) {
		this.put(FieldEnum.comm_type.toString(), value);
		return this;
	}

	public Params network(String value) {
		this.put(FieldEnum.network.toString(), value);
		return this;
	}

	public Params account_id(Object value) {
		this.put(FieldEnum.account_id.toString(), value);
		return this;
	}

	public Params daserver_id(String value) {
		this.put(FieldEnum.daserver_id.toString(), value);
		return this;
	}

	public Params sn(String value) {
		this.put(FieldEnum.sn.toString(), value);
		return this;
	}

	public Params longitude(String value) {
		this.put(FieldEnum.longitude.toString(), value);
		return this;
	}

	public Params latitude(String value) {
		this.put(FieldEnum.latitude.toString(), value);
		return this;
	}

	public Params version(String value) {
		this.put(FieldEnum.version.toString(), value);
		return this;
	}

	public Params craete_time(String value) {
		this.put(FieldEnum.create_time.toString(), value);
		return this;
	}

	public Params last_modify_time(String value) {
		this.put(FieldEnum.last_modify_time.toString(), value);
		return this;
	}

	public Params last_sync_time(String value) {
		this.put(FieldEnum.last_sync_time.toString(), value);
		return this;
	}

	public Params mode(String value) {
		this.put(FieldEnum.mode.toString(), value);
		return this;
	}

	public Params unit(String value) {
		this.put(FieldEnum.unit.toString(), value);
		return this;
	}

	public Params group(String value) {
		this.put(FieldEnum.group.toString(), value);
		return this;
	}

	public Params scale(String value) {
		this.put(FieldEnum.scale.toString(), value);
		return this;
	}

	public Params deviation(String value) {
		this.put(FieldEnum.deviation.toString(), value);
		return this;
	}

	public Params save_log(String value) {
		this.put(FieldEnum.save_log.toString(), value);
		return this;
	}

	public Params log_sycle(String value) {
		this.put(FieldEnum.log_sycle.toString(), value);
		return this;
	}

	public Params log_type(String value) {
		this.put(FieldEnum.log_type.toString(), value);
		return this;
	}

	public Params connect(String value) {
		this.put(FieldEnum.connect.toString(), value);
		return this;
	}

	public Params type(String value) {
		this.put(FieldEnum.type.toString(), value);
		return this;
	}

	public Params conditions(String value) {
		this.put(FieldEnum.conditions.toString(), value);
		return this;
	}

	public Params action(String value) {
		this.put(FieldEnum.action.toString(), value);
		return this;
	}

	public Params params(String value) {
		this.put(FieldEnum.params.toString(), value);
		return this;
	}

	public Params origin(String value) {
		this.put(FieldEnum.origin.toString(), value);
		return this;
	}

	public Params driver_id(String value) {
		this.put(FieldEnum.driver_id.toString(), value);
		return this;
	}

	public Params driver_var(String value) {
		this.put(FieldEnum.driver_var.toString(), value);
		return this;
	}

	public Params readwrite(String value) {
		this.put(FieldEnum.readwrite.toString(), value);
		return this;
	}

	public Params path(String value) {
		this.put(FieldEnum.path.toString(), value);
		return this;
	}

	public Params comm_mode_support(String value) {
		this.put(FieldEnum.comm_mode_support.toString(), value);
		return this;
	}

	public Params device_params(String value) {
		this.put(FieldEnum.device_params.toString(), value);
		return this;
	}

	public Params point_params(String value) {
		this.put(FieldEnum.point_params.toString(), value);
		return this;
	}

	public Params io_params(String value) {
		this.put(FieldEnum.io_params.toString(), value);
		return this;
	}

	public Params hostname(String value) {
		this.put(FieldEnum.hostname.toString(), value);
		return this;
	}

	public Params ip(String value) {
		this.put(FieldEnum.ip.toString(), value);
		return this;
	}

	public Params port(String value) {
		this.put(FieldEnum.port.toString(), value);
		return this;
	}

	public Params max_stations(String value) {
		this.put(FieldEnum.max_stations.toString(), value);
		return this;
	}

	public Params total_stations(String value) {
		this.put(FieldEnum.total_stations.toString(), value);
		return this;
	}

	public Params active_stations(String value) {
		this.put(FieldEnum.active_stations.toString(), value);
		return this;
	}

	public Params xmpp_id(String value) {
		this.put(FieldEnum.xmpp_id.toString(), value);
		return this;
	}

	public Params xmpp_domain(String value) {
		this.put(FieldEnum.xmpp_domain.toString(), value);
		return this;
	}

	public Params xmpp_user(String value) {
		this.put(FieldEnum.xmpp_user.toString(), value);
		return this;
	}

	public Params xmpp_passwd(String value) {
		this.put(FieldEnum.xmpp_passwd.toString(), value);
		return this;
	}

}
