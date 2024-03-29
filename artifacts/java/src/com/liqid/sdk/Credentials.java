// File: Credentials.java
//
// Copyright (c) 2022-2023 Liqid, Inc. All rights reserved.
// 
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//
// Liqid SDK - Version 3.4
// This file was automatically generated - do not modify it directly.
//

package com.liqid.sdk;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.PropertyAccessor;

/**
 * Credentials
 * Contains credentials necessary for managing some managed entity within the configuration (such as via IPMI)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Credentials {

    public static class CredentialsWrapper extends Wrapper<Credentials>{}

    /**
     * The password associated with the given principal
     */
    @JsonProperty("password")
    private String _password = null;

    public String getPassword() {
        return _password;
    }

    public Credentials setPassword(String value) {
        _password = value;
        return this;
    }

    /**
     * Also known as username, user-id, etc
     */
    @JsonProperty("principal")
    private String _principal = null;

    public String getPrincipal() {
        return _principal;
    }

    public Credentials setPrincipal(String value) {
        _principal = value;
        return this;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public Credentials() {
    }

    /**
     * Parameterized Constructor
     */
    protected Credentials(String principal,
                          String password) {
        _principal = principal;
        _password = password;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_principal:").append(getPrincipal());
        sb.append(", ").append("_password:").append(getPassword());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private String _principal = null;
        private String _password = null;

        public Builder setPrincipal(String value) { _principal = value; return this; }
        public Builder setPassword(String value) { _password = value; return this; }

        public Credentials build() {
            if (_principal == null) {
                throw new RuntimeException("setPrincipal() was not invoked in Builder for class Credentials");
            }
            if (_password == null) {
                throw new RuntimeException("setPassword() was not invoked in Builder for class Credentials");
            }
            return new Credentials(_principal,
                                   _password);
        }
    }
}
