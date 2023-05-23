package doctintuc.com.websitedoctintuc.application.constants;

public class UrlConstant {
	private static final String ADMIN = "/admin";
	private static final String USER = "/user";
	private static final String OPERATOR = "/operator";
	private static final String ALL = "/all";

	public static class Category {
		public static final String CREATE_CATEGORY = ADMIN + "/new_category";
		public static final String UPDATE_CATEGORY = ADMIN + "/update_category/{id}";
		public static final String DELETE_CATEGORY = ADMIN + "/delete/{id}";
		public static final String UPLOAD_CATEGORY_DATA_FROM_EXCEL = OPERATOR + "/upload_category_data";
		private static final String PRE_FIX = "/category";
		public static final String DATA_CATEGORY = PRE_FIX;
		public static final String GET_CATEGORY = PRE_FIX + "/get_category/{id}";

		public Category() {
		}
	}
}
