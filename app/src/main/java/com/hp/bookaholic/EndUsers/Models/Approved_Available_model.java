package com.hp.bookaholic.EndUsers.Models;

import java.util.List;

public class Approved_Available_model {
    /**
     * status : success
     * Available_Details : [{"book_id":"1","book_name":"two states","author":"peter","user_id":"1","photo":"http://srishti-systems.info/projects/BookLenging_App/uploads/166793-twilight.jpg","lend_days":"100","extra_days":"10","postal_address":"SICS","account_no":"7098654321546778","ifsc_code":"3456","branch":"Athikayam","status":"1","price":"500"},{"book_id":"2","book_name":"twilight2","author":"peter","user_id":"1","photo":"http://srishti-systems.info/projects/BookLenging_App/uploads/671709-twilight.jpg","lend_days":"100","extra_days":"10","postal_address":"SICS","account_no":"7098654321546778","ifsc_code":"3456","branch":"Athikayam","status":"1","price":"500"},{"book_id":"3","book_name":"Nun","author":"Nun","user_id":"1","photo":"http://srishti-systems.info/projects/BookLenging_App/uploads/368683-images-(2).jpeg","lend_days":"100","extra_days":"2","postal_address":"sics","account_no":"1235698074","ifsc_code":"ASD1234","branch":"Qwer","status":"1","price":"5000"},{"book_id":"6","book_name":"Two States III","author":"peter","user_id":"1","photo":"http://srishti-systems.info/projects/BookLenging_App/uploads/793251-2states.jpg","lend_days":"100","extra_days":"10","postal_address":"SICS","account_no":"7098654321546778","ifsc_code":"3456","branch":"Athikayam","status":"1","price":"500"},{"book_id":"7","book_name":"Two States IV","author":"peter","user_id":"1","photo":"http://srishti-systems.info/projects/BookLenging_App/uploads/866991-2states.jpg","lend_days":"100","extra_days":"10","postal_address":"SICS","account_no":"7098654321546778","ifsc_code":"3456","branch":"Athikayam","status":"1","price":"500"}]
     */

    private String status;
    private List<AvailableDetailsBean> Available_Details;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AvailableDetailsBean> getAvailable_Details() {
        return Available_Details;
    }

    public void setAvailable_Details(List<AvailableDetailsBean> Available_Details) {
        this.Available_Details = Available_Details;
    }

    public static class AvailableDetailsBean {
        /**
         * book_id : 1
         * book_name : two states
         * author : peter
         * user_id : 1
         * photo : http://srishti-systems.info/projects/BookLenging_App/uploads/166793-twilight.jpg
         * lend_days : 100
         * extra_days : 10
         * postal_address : SICS
         * account_no : 7098654321546778
         * ifsc_code : 3456
         * branch : Athikayam
         * status : 1
         * price : 500
         */

        private String book_id;
        private String book_name;
        private String author;
        private String user_id;
        private String photo;
        private String lend_days;
        private String extra_days;
        private String postal_address;
        private String account_no;
        private String ifsc_code;
        private String branch;
        private String status;
        private String price;

        public String getBook_id() {
            return book_id;
        }

        public void setBook_id(String book_id) {
            this.book_id = book_id;
        }

        public String getBook_name() {
            return book_name;
        }

        public void setBook_name(String book_name) {
            this.book_name = book_name;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getLend_days() {
            return lend_days;
        }

        public void setLend_days(String lend_days) {
            this.lend_days = lend_days;
        }

        public String getExtra_days() {
            return extra_days;
        }

        public void setExtra_days(String extra_days) {
            this.extra_days = extra_days;
        }

        public String getPostal_address() {
            return postal_address;
        }

        public void setPostal_address(String postal_address) {
            this.postal_address = postal_address;
        }

        public String getAccount_no() {
            return account_no;
        }

        public void setAccount_no(String account_no) {
            this.account_no = account_no;
        }

        public String getIfsc_code() {
            return ifsc_code;
        }

        public void setIfsc_code(String ifsc_code) {
            this.ifsc_code = ifsc_code;
        }

        public String getBranch() {
            return branch;
        }

        public void setBranch(String branch) {
            this.branch = branch;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
