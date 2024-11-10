package cn.bdqn.util;
/**
 * Created by bdqn on 2016/4/21.
 */
public class Constants {
    /**
     * json 格式返回数据的 status 标示说明
     */
    public static interface ReturnResult{
        public static int SUCCESS=1;
        public static int FAIL=-1;
    }
    /**
     * 前后台用户
     */
    public static interface  UserType{
        public static int PRE=0;
        public static int BACKEND=1;
    }
}
