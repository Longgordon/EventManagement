package com.event.java.controller.user.mservice.models;

import com.event.java.controller.user.mservice.enums.ConfirmRequestType;

public class ConfirmResponse extends Response {
    private Long amount;
    private Long transId;
    private String requestId;
    private ConfirmRequestType requestType;
}
