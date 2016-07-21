package com.example.lovehometown.model;

/**
 * Created by Administrator on 2016/7/21.
 */
public class ForgetPassword {


    /**
     * code : 1
     * msg : 密码获取成功
     */

    private ResultsBean Results;
    /**
     * password : 123456
     */

    private UserBean User;

    public ResultsBean getResults() {
        return Results;
    }

    public void setResults(ResultsBean Results) {
        this.Results = Results;
    }

    public UserBean getUser() {
        return User;
    }

    public void setUser(UserBean User) {
        this.User = User;
    }

    public static class ResultsBean {
        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public static class UserBean {
        private String password;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
