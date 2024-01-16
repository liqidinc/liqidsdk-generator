//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.liqidEntityModels.types;

import com.liqid.sdk.generator.liqidEntityModels.Description;
import com.liqid.sdk.generator.liqidEntityModels.LiqidEntity;

import java.util.LinkedList;

/**
 * Describes an instance of an API-defined enumerator.
 * Languages which do not support enumerators should implement this as a constant.
 */
public class EnumComponent extends LiqidEntity {

    private final Object _value;

    public EnumComponent(String baseName,
                         Description description,
                         Object value) {
        super(baseName, description);
        _value = value;
    }

    public final String getLiteralString() {
        if (_value instanceof String) {
            return "\"" + _value + "\"";
        } else {
            return _value.toString();
        }
    }

    public final Object getValue() { return _value; }

    public static class Builder {

        private static final String ExceptionFmt = "%s() was not invoked in Builder for class Enumerator";

        private String _baseName = null;
        private final LinkedList<String> _description = new LinkedList<>();
        private Object _value = null;

        public Builder addDescription(String value) { _description.add(value); return this; }
        public Builder setBaseName(String value) { _baseName = value; return this; }
        public Builder setValue(Object value) { _value = value; return this; }

        public EnumComponent build() {
            if (_baseName == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setBaseName"));
            }

            if (_description.isEmpty()) {
                throw new RuntimeException(String.format(ExceptionFmt, "addDescription"));
            }

            if (_value == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setValue"));
            }

            return new EnumComponent(_baseName, new Description(_description), _value);
        }
    }
}
