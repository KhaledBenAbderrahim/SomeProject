package de.smartcrew.eatforfitserver.constants;

public enum UserStatus {
    GUEST("GUEST"), BASIC("BASIC"), PREMIUM("PREMIUM");

    private String userStatus;

    private UserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getStatus(){
        return userStatus;
    }
}