package com.sunwayland.rest.url;

public class UserGroupUrl {
	public static String query = "/query/groups";
	public static String create = "/groups";
	public static String r_u_d_ById = "/groups/{group_id}";

	public static String query_usersOfgorup = "/groups/{group_id}/users";
	public static String delOrAdd_usersOfgorup_byId = "/groups/{group_id}/users/{user_id}";
	
}
