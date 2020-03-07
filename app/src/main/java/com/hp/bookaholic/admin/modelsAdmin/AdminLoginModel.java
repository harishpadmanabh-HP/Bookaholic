package com.hp.bookaholic.admin.modelsAdmin;

import java.util.List;

public class AdminLoginModel {
    /**
     * status : success
     * Book_Details : [{"id":"1","username":"admin@gmail.com","password":"admin"}]
     */

    private String status;
    private List<BookDetailsBean> Book_Details;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BookDetailsBean> getBook_Details() {
        return Book_Details;
    }

    public void setBook_Details(List<BookDetailsBean> Book_Details) {
        this.Book_Details = Book_Details;
    }

    public static class BookDetailsBean {
        /**
         * id : 1
         * username : admin@gmail.com
         * password : admin
         */

        private String id;
        private String username;
        private String password;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
