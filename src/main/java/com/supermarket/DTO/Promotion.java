package com.supermarket.DTO;

import com.supermarket.utils.Date;

public class Promotion {
    private int id;
    private Date start_date;
    private Date end_date;
    private boolean status;
    private boolean deleted;

    public Promotion() {
    }

    public Promotion(int id, Date start_date, Date end_date, boolean status, boolean deleted) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        String status1 = status? "Ngưng áp dụng" : "Đang áp dụng";
        return id  + " | " +
            start_date + " | " +
            end_date + " | " +
            status1;
    }
}
