// File: DigestInfo.java
//
// Copyright (c) 2022-2023 Liqid, Inc. All rights reserved.
// 
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//
// Liqid SDK - Version 3.3.0
// This file was automatically generated - do not modify it directly.
//

package com.liqid.sdk;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.PropertyAccessor;

/**
 * DigestInfo
 * Describes information related to a newly-created digest message
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DigestInfo {

    public static class DigestInfoWrapper extends Wrapper<DigestInfo>{}

    /**
     * A unique (internal) identifier for this digest message
     */
    @JsonProperty("digest_id")
    private Integer _digestId = null;

    public Integer getDigestId() {
        return _digestId;
    }

    public void setDigestId(Integer value) {
        _digestId = value;
    }

    /**
     * The digest key generated by the director to be associated with the given label
     * This is the value which should be used for authenticating subsequent REST API invocations.
     */
    @JsonProperty("digest_key")
    private String _digestKey = null;

    public String getDigestKey() {
        return _digestKey;
    }

    public void setDigestKey(String value) {
        _digestKey = value;
    }

    /**
     * The digest label which is associated with the generated digest key.
     * This value should be used for deleting the digest message at the end of a client REST session.
     */
    @JsonProperty("digest_label")
    private String _digestLabel = null;

    public String getDigestLabel() {
        return _digestLabel;
    }

    public void setDigestLabel(String value) {
        _digestLabel = value;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public DigestInfo() {
    }

    /**
     * Parameterized Constructor
     */
    protected DigestInfo(Integer digestId,
                         String digestKey,
                         String digestLabel) {
        _digestId = digestId;
        _digestKey = digestKey;
        _digestLabel = digestLabel;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_digestId:").append(getDigestId());
        sb.append(", ").append("_digestKey:").append(getDigestKey());
        sb.append(", ").append("_digestLabel:").append(getDigestLabel());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private Integer _digestId = null;
        private String _digestKey = null;
        private String _digestLabel = null;

        public Builder setDigestId(Integer value) { _digestId = value; return this; }
        public Builder setDigestKey(String value) { _digestKey = value; return this; }
        public Builder setDigestLabel(String value) { _digestLabel = value; return this; }

        public DigestInfo build() {
            if (_digestId == null) {
                throw new RuntimeException("setDigestId() was not invoked in Builder for class DigestInfo");
            }
            if (_digestKey == null) {
                throw new RuntimeException("setDigestKey() was not invoked in Builder for class DigestInfo");
            }
            if (_digestLabel == null) {
                throw new RuntimeException("setDigestLabel() was not invoked in Builder for class DigestInfo");
            }
            return new DigestInfo(_digestId,
                                  _digestKey,
                                  _digestLabel);
        }
    }
}
