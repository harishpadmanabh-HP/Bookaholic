package com.hp.bookaholic.admin.modelsAdmin;

import java.util.List;

public class AllBookModel {
    /**
     * status : success
     * Book_Details : [{"book_id":"3","book_name":"Davinci Code","author":"peter","user_id":"1","photo":"http://srishti-systems.info/projects/BookLenging_App/uploads/463772-bookimage.jpg","lend_days":"30","extra_days":"2","postal_address":"Naranammoozhy","account_no":"7098654321546778","ifsc_code":"3456","branch":"Athikayam","status":"1"},{"book_id":"2","book_name":"Twilight","author":"peter","user_id":"1","photo":"http://srishti-systems.info/projects/BookLenging_App/uploads/305832-twilight.jpg","lend_days":"30","extra_days":"2","postal_address":"Naranammoozhy","account_no":"7098654321546778","ifsc_code":"3456","branch":"Athikayam","status":"1"},{"book_id":"4","book_name":"Harry Potter","author":"J K ROWLING","user_id":"1","photo":"http://srishti-systems.info/projects/BookLenging_App/uploads/583990-bookimage.jpg","lend_days":"30","extra_days":"2","postal_address":"Naranammoozhy","account_no":"7098654321546778","ifsc_code":"3456","branch":"Athikayam","status":"1"},{"book_id":"5","book_name":"Two states","author":"Chetan bagat","user_id":"1","photo":"http://srishti-systems.info/projects/BookLenging_App/uploads/950208-2states.jpg","lend_days":"30","extra_days":"2","postal_address":"Naranammoozhy","account_no":"7098654321546778","ifsc_code":"3456","branch":"Athikayam","status":"1"},{"book_id":"6","book_name":"van helsin","author":"peter","user_id":"1","photo":"http://srishti-systems.info/projects/BookLenging_App/uploads/475302-bookaholic202003032241084381874537362387243.jpg","lend_days":"12","extra_days":"2","postal_address":"pattom","account_no":"1234567890","ifsc_code":"AHQJQ123","branch":"pattom","status":"0"},{"book_id":"7","book_name":"rtt","author":"ttyy","user_id":"1","photo":"http://srishti-systems.info/projects/BookLenging_App/uploads/200099-screenshot_20200303-094932_one-ui-home.jpg","lend_days":"999","extra_days":"55","postal_address":"fffg","account_no":"889","ifsc_code":"FFGGG","branch":"gggg\n","status":"0"}]
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
         * book_id : 3
         * book_name : Davinci Code
         * author : peter
         * user_id : 1
         * photo : http://srishti-systems.info/projects/BookLenging_App/uploads/463772-bookimage.jpg
         * lend_days : 30
         * extra_days : 2
         * postal_address : Naranammoozhy
         * account_no : 7098654321546778
         * ifsc_code : 3456
         * branch : Athikayam
         * status : 1
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
    }
}
