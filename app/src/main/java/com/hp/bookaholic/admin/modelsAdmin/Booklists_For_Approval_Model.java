package com.hp.bookaholic.admin.modelsAdmin;

import java.util.List;

public class Booklists_For_Approval_Model {
    /**
     * status : success
     * Book_Details : [{"book_id":"2","book_name":"twilight2","author":"peter","user_id":"1","photo":"http://srishti-systems.info/projects/BookLenging_App/uploads/671709-twilight.jpg","lend_days":"100","extra_days":"10","postal_address":"SICS","account_no":"7098654321546778","ifsc_code":"3456","branch":"Athikayam","status":"0","price":"500","user_details":{"user_id":"1","name":"Harish","email":"hp.sics@gmail.com","phone":"7012069385","password":"qwerty","address":"Kesavadasapuram"}}]
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
         * book_name : twilight2
         * author : peter
         * user_id : 1
         * photo : http://srishti-systems.info/projects/BookLenging_App/uploads/671709-twilight.jpg
         * lend_days : 100
         * extra_days : 10
         * postal_address : SICS
         * account_no : 7098654321546778
         * ifsc_code : 3456
         * branch : Athikayam
         * status : 0
         * price : 500
         * user_details : {"user_id":"1","name":"Harish","email":"hp.sics@gmail.com","phone":"7012069385","password":"qwerty","address":"Kesavadasapuram"}
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
        private UserDetailsBean user_details;

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

        public UserDetailsBean getUser_details() {
            return user_details;
        }

        public void setUser_details(UserDetailsBean user_details) {
            this.user_details = user_details;
        }

        public static class UserDetailsBean {
            /**
             * user_id : 1
             * name : Harish
             * email : hp.sics@gmail.com
             * phone : 7012069385
             * password : qwerty
             * address : Kesavadasapuram
             */

            private String user_id;
            private String name;
            private String email;
            private String phone;
            private String password;
            private String address;

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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }
    }
}
