package doctintuc.com.websitedoctintuc.application.constants;

public class UrlConstant {
	private static final String ADMIN = "/admin";
	private static final String USER = "/user";
	private static final String OPERATOR = "/operator";
	private static final String ALL = "/all";

	public static class Category {
		public static final String CREATE_CATEGORY = ADMIN + "/new_category";
		public static final String UPDATE_CATEGORY = ADMIN + "/update_category/{id}";
		public static final String DELETE_CATEGORY = ADMIN + "/delete_category/{id}";
		public static final String UPLOAD_CATEGORY_DATA_FROM_EXCEL = OPERATOR + "/upload_category_data";
		private static final String PRE_FIX = "/category";
		public static final String DATA_CATEGORY = PRE_FIX;
		public static final String GET_CATEGORY = PRE_FIX + "/get_category/{id}";

		public Category() {
		}
	}

	public static class Type {
		public static final String CREATE_TYPE = ADMIN + "/new_type";
		public static final String UPDATE_TYPE = ADMIN + "/update_type/{id}";
		public static final String DELETE_TYPE = ADMIN + "/delete_type/{id}";
		public static final String UPLOAD_TYPE_DATA_FROM_EXCEL = OPERATOR + "/upload_type_data";
		private static final String PRE_FIX = "/type";
		public static final String DATA_TYPE = PRE_FIX;
		public static final String GET_TYPE = PRE_FIX + "/get_type/{id}";

		public Type() {
		}
	}

	public static class News {
		public static final String CREATE_NEWS = ADMIN + "/new_news";
		public static final String UPDATE_NEWS = ADMIN + "/update_news/{id}";
		public static final String DELETE_NEWS = ADMIN + "/delete_news/{id}";
		public static final String UPLOAD_NEWS_DATA_FROM_EXCEL = OPERATOR + "/upload_news_data";
		private static final String PRE_FIX = "/news";
		public static final String DATA_NEWS = PRE_FIX;
		public static final String GET_NEWS = PRE_FIX + "/get_news/{id}";

		public News() {
		}
	}
}
