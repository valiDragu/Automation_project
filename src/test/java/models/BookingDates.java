package models;

public class BookingDates {
    private String checkin;
    private String checkout;

    // 1. No-Args Constructor
    public BookingDates() {}

    // 2. All-Args Constructor
    public BookingDates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    // 3. Explicit Getters & Setters
    public String getCheckin() {
        return checkin;
    }
    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }
    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }
}