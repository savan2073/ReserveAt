package com.example.ReserveAt.Dto;

public class AddFriendRequest {
    private String friendFirstName;
    private String friendLastName;

    public AddFriendRequest(String friendFirstName, String friendLastName) {
        this.friendFirstName = friendFirstName;
        this.friendLastName = friendLastName;
    }

    public AddFriendRequest() {
    }

    public String getFriendFirstName() {
        return friendFirstName;
    }

    public void setFriendFirstName(String friendFirstName) {
        this.friendFirstName = friendFirstName;
    }

    public String getFriendLastName() {
        return friendLastName;
    }

    public void setFriendLastName(String friendLastName) {
        this.friendLastName = friendLastName;
    }
}
