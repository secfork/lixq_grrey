var SimpleOrm = require('../../utils/SimpleOrm');
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
exports.systemsOrm = new SimpleOrm("t_systems", {
	"uuid" : { "alias":"uuid", "readonly":true },
	"name" : { "alias":"name" },
	"desc" : { "alias":"desc" },
	"model" : { "alias":"model", "readonly":true },
	"profile" : { "alias":"profile" },
	"state" : { "alias":"state"},
	"comm_type" : { "alias":"comm_type"},
	"network" : { "alias":"network" },
	"account_id" : { "alias":"account_id", "readonly":true },
	"daserver_id" : { "alias":"daserver_id" },
	"sn" : { "alias":"sn", "readonly":true },
	"longitude" : { "alias":"longitude"},
	"latitude" : { "alias":"latitude"},
	"pos_type" : { "alias":"pos_type"},
	"version" : { "alias":"version", "readonly":true, "auto":true },
	"create_time" : { "alias":"create_time", "readonly":true, "auto":true },
	"last_modify_time" : { "alias":"last_modify_time", "auto":true },
	"last_sync_time" : { "alias":"last_sync_time"}
});

xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx 
exports.systemModelsOrm = new SimpleOrm("t_system_models", {
	"uuid" : { "alias":"uuid", "readonly":true },
	"name" : { "alias":"name" },
	"desc" : { "alias":"desc" },
	"mode" : { "alias":"mode", "readonly":true },
	"account_id" : { "alias":"account_id", "readonly":true },
	"version" : { "alias":"version", "readonly":true, "auto":true },
	"profile_count" : { "alias":"profile_count", "readonly":true, "auto":true },
	"device_count" : { "alias":"device_count", "readonly":true, "auto":true },
	"create_time" : { "alias":"create_time", "readonly":true, "auto":true },
	"last_modify_time" : { "alias":"last_modify_time", "auto":true }
});
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx 
exports.profilesOrm = new SimpleOrm("t_profiles", {
	"uuid" : { "alias":"uuid", "readonly":true },
	"name" : { "alias":"name" },
	"desc" : { "alias":"desc" },
	"system_model" : { "alias":"system_model", "readonly":true },
	"version" : { "alias":"version", "readonly":true, "auto":true },
	"tag_count" : { "alias":"tag_count", "readonly":true, "auto":true },
	"trigger_count" : { "alias":"trigger_count", "readonly":true, "auto":true },
	"create_time" : { "alias":"create_time", "readonly":true, "auto":true },
	"last_modify_time" : { "alias":"last_modify_time", "auto":true }	
});
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
exports.tagsOrm = new SimpleOrm("t_profile_tags", {
	"id" : { "alias":"id", "readonly":true },
	"name" : { "alias":"name" },
	"desc" : { "alias":"desc" },
	"profile" : { "alias":"profile", "readonly":true },
	"type" : { "alias":"type", "readonly":true },
	"unit" : { "alias":"unit" },
	"group" : { "alias":"group" },
	"scale" : { "alias":"scale" },
	"deviation" : { "alias":"deviation" },
	"save_log" : { "alias":"save_log" },
	"log_cycle" : { "alias":"log_cycle" },
	"log_type" : { "alias":"log_type" },
	"connect" : { "alias":"connect" }
});

exports.triggersOrm = new SimpleOrm("t_profile_triggers", {
	"id" : { "alias":"id", "readonly":true },
	"name" : { "alias":"name" },
	"desc" : { "alias":"desc" },
	"profile" : { "alias":"profile", "readonly":true },
	"type" : { "alias":"type" },
	"conditions" : { "alias":"conditions" },
	"action" : { "alias":"action" },
	"params" : { "alias":"params" },
	"origin" : { "alias":"origin" }
});
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
exports.deviceModelsOrm = new SimpleOrm("t_device_models", {
	"uuid" : { "alias":"uuid", "readonly":true },
	"name" : { "alias":"name" },
	"desc" : { "alias":"desc" },
	"account_id" : { "alias":"account_id", "readonly":true },
	"driver_id" : { "alias":"driver_id" },
	"driver_ver" : { "alias":"driver_ver" },
	"version" : { "alias":"version", "readonly":true, "auto":true },
	"point_count" : { "alias":"point_count", "readonly":true, "auto":true },
	"create_time" : { "alias":"create_time", "readonly":true, "auto":true },
	"last_modify_time" : { "alias":"last_modify_time", "auto":true }
});

exports.systemModelDevicesOrm = new SimpleOrm("t_system_model_devices", {
	"id" : { "alias":"id", "readonly":true },
	"name" : { "alias":"name" },
	"desc" : { "alias":"desc" },
	"system_model" : { "alias":"system_model", "readonly":true },
	"device_model" : { "alias":"device_model", "readonly":true },
	"params" : { "alias":"params" },
	"network" : { "alias":"network" }
});
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
exports.pointsOrm = new SimpleOrm("t_device_model_points", {
	"id" : { "alias":"id", "readonly":true },
	"name" : { "alias":"name" },
	"desc" : { "alias":"desc" },
	"device_model" : { "alias":"device_model", "readonly":true },
	"type" : { "alias":"type" },
	"readwrite" : { "alias":"readwrite" },
	"params" : { "alias":"params" }
});

xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
exports.driversOrm = new SimpleOrm("t_drivers", {
	"id" : { "alias":"driver_id", "readonly":true, "auto":true },
	"uuid" : { "alias":"uuid", "readonly":true },
	"name" : { "alias":"name" },
	"description" : { "alias":"desc" },
	"path" : { "alias":"path" },
	"version" : { "alias":"version" },		
	"comm_mode_support" : { "alias":"comm_mode_support" },
	"device_params" : { "alias":"device_params" },
	"point_params" : { "alias":"point_params" },
	"io_params" : { "alias" : "io_params"}
});


xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
exports.daserversOrm = new SimpleOrm("t_daservers", {
	"id" : { "alias":"daserver_id", "readonly":true, "auto":true },
	"uuid" : { "alias":"uuid", "readonly":true },
	"name" : { "alias":"name" },
	"description" : { "alias":"desc" },
	"hostname" : { "alias":"hostname" },
	"ip" : { "alias": "ip"},
	"port" : { "alias": "port"},
	"params" : { "alias": "params"},
	"max_stations" : { "alias": "max_stations"},
	"total_stations" : { "alias": "total_stations"},
	"active_stations" : { "alias": "active_stations"},
	"xmpp_id" : { "alias": "xmpp_id"},
	"xmpp_domain" : { "alias": "xmpp_domain"},
	"xmpp_user" : { "alias": "xmpp_user"},
	"xmpp_passwd" : { "alias": "xmpp_passwd"},
});

