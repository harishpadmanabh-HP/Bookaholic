package com.hp.bookaholic.EndUsers.Models;

import java.util.List;

public class ReturnDetailsModel {
    /**
     * status : success
     * Buy_Details : [{"buy_id":"2","book_id":"2","user_id":"1","lend_price":"100","extra_price":"12","price":"1000","buy_date":"2020/03/20","extradate":"1","returndate":"2020/03/21","actual_pay_amount":"100"}]
     */

    private String status;
    private List<BuyDetailsBean> Buy_Details;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BuyDetailsBean> getBuy_Details() {
        return Buy_Details;
    }

    public void setBuy_Details(List<BuyDetailsBean> Buy_Details) {
        this.Buy_Details = Buy_Details;
    }

    public static class BuyDetailsBean {
        /**
         * buy_id : 2
         * book_id : 2
         * user_id : 1
         * lend_price : 100
         * extra_price : 12
         * price : 1000
         * buy_date : 2020/03/20
         * extradate : 1
         * returndate : 2020/03/21
         * actual_pay_amount : 100
         */

        private String buy_id;
        private String book_id;
        private String user_id;
        private String lend_price;
        private String extra_price;
        private String price;
        private String buy_date;
        private String extradate;
        private String returndate;
        private String actual_pay_amount;

        public String getBuy_id() {
            return buy_id;
        }

        public void setBuy_id(String buy_id) {
            this.buy_id = buy_id;
        }

        public String getBook_id() {
            return book_id;
        }

        public void setBook_id(String book_id) {
            this.book_id = book_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getLend_price() {
            return lend_price;
        }

        public void setLend_price(String lend_price) {
            this.lend_price = lend_price;
        }

        public String getExtra_price() {
            return extra_price;
        }

        public void setExtra_price(String extra_price) {
            this.extra_price = extra_price;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getBuy_date() {
            return buy_date;
        }

        public void setBuy_date(String buy_date) {
            this.buy_date = buy_date;
        }

        public String getExtradate() {
            return extradate;
        }

        public void setExtradate(String extradate) {
            this.extradate = extradate;
        }

        public String getReturndate() {
            return returndate;
        }

        public void setReturndate(String returndate) {
            this.returndate = returndate;
        }

        public String getActual_pay_amount() {
            return actual_pay_amount;
        }

        public void setActual_pay_amount(String actual_pay_amount) {
            this.actual_pay_amount = actual_pay_amount;
        }
    }
}
