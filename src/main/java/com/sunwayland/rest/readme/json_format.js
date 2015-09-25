/*
 * 数据下置接口函数 int OnControl(std::string content); control命令格式 {
 * uuid:"bd958060-829f-11e4-b903-e1b8383cac90" data:{ dev_1:{ tag_1:”12.5”,
 * tag_2:”6.8” } dev_2:{ tag_1:”hello”, tag_2:”9.8” } } }
 * 
 * 各种命令发送接口函数 name=start/stop/quit/get/update content命令内容格式json串
 * OnCommand(std::string name,std::string content);
 * 
 * start stop quit get 命令格式 { "uuid":"bd958060-829f-11e4-b903-e1b8383cac90" }
 * 
 * 
 * update命令格式 { station:{ uuid:”1245877dffsd” state:”1”, network:{
 * tcpclient:{ip:“127.0.0.1”}, tcpserver:{ip:"172.18.16.15",port:502},
 * gprs:{dtu:”Dtu_HongDian”,simid:”12345678978”} } } devices:{ dev_1:{
 * name:"dev1", index:1 , cycle:10, retry:2, timeout:5, driver:”FCS_MODBUS”
 * network:{ tcpclient:{ip:“127.0.0.1”}, tcpserver:{ip:"172.18.16.15",port:502},
 * gprs:{dtu:”Dtu_HongDian”,simid:”12345678978”} },
 * params:{ip:”127.0.0.1”,opcserver:”PCAuto.OPCServer”}, points:{
 * var_1:{name:"var1",index:1,params:{area:”hr”, offset:1, type:”int”}},
 * var_2:{name:"var2",index:2,params::{area:”hr”, offset:1, type:”int”}} } },
 * dev_2:{} } }
 */