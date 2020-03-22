package com.hp.bookaholic.EndUsers.Models;

public class ReturnBookModel {

    /**
     * status : Updated  Successfully
     * pay_amount : 100
     */

    private String status;
    private String pay_amount;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPay_amount() {
        return pay_amount;
    }

    public void setPay_amount(String pay_amount) {
        this.pay_amount = pay_amount;
    }
}
