package avh.nusoft.api.security.common;

public interface SecurityConstants {
	public static final String PublicServletPath = "/avh/nusoft/api/public";
	public static final String PrivateServletPath = "/avh/nusoft/api/authorized";
	public static final String LoginServletPath = PublicServletPath + "/login";
	public static final String RegisterServletPath = PublicServletPath + "/register";
	public static final String RoleUser = "ROLE_User";
}
