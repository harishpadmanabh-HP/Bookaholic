package com.hp.bookaholic.Models;

public class LoginModel {
    /**
     * status : Success
     * User_data : {"user_id":"2","name":"Athira","email":"athirasurendran.sics@gmail.com","phone":"8157073329","password":"qwerty","postal_address":"Kesavadasapuram"}
     */

    private String status;
    private UserDataBean User_data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserDataBean getUser_data() {
        return User_data;
    }

    public void setUser_data(UserDataBean User_data) {
        this.User_data = User_data;
    }

    public static class UserDataBean {
        /**
         * user_id : 2
         * name : Athira
         * email : athirasurendran.sics@gmail.com
         * phone : 8157073329
         * password : qwerty
         * postal_address : Kesavadasapuram
         */

        private String user_id;
        private String name;
        private String email;
        private String phone;
        private String password;
        private String postal_address;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPostal_address() {
            return postal_address;
        }

        public void setPostal_address(String postal_address) {
            this.postal_address = postal_address;
        }
    }
}
