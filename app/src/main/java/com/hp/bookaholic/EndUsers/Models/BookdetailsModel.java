package com.hp.bookaholic.EndUsers.Models;

import java.util.List;

public class BookdetailsModel {


    /**
     * status : success
     * Book_Details : [{"book_id":"2","book_name":"Twilight","author":"peter","user_id":"1","photo":"http://srishti-systems.info/projects/BookLenging_App/uploads/305832-twilight.jpg","lend_days":"30","extra_days":"2","postal_address":"Naranammoozhy","account_no":"7098654321546778","ifsc_code":"3456","branch":"Athikayam","status":"1","price":"200"}]
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
         * book_id : 2
         * book_name : Twilight
         * author : peter
         * user_id : 1
         * photo : http://srishti-systems.info/projects/BookLenging_App/uploads/305832-twilight.jpg
         * lend_days : 30
         * extra_days : 2
         * postal_address : Naranammoozhy
         * account_no : 7098654321546778
         * ifsc_code : 3456
         * branch : Athikayam
         * status : 1
         * price : 200
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
