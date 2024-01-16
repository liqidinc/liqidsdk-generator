//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.writers.go;

import com.liqid.sdk.generator.catalog.Catalog;
import com.liqid.sdk.generator.discreteDataModels.*;
import com.liqid.sdk.generator.writers.CodeWriter;
import com.liqid.sdk.generator.writers.Writer;

public class GoCommon {

    static final String GO_PACKAGE_NAME = "liqidsdk";

    private GoCommon() {}

    /**
     * Returns the Go type name corresponding to the given data descriptor,
     * ignore whether the descriptor is an array.
     */
    static String getDataTypeString(DataDescriptor descriptor) {
        return
            switch (descriptor.getDataTypeId()) {
                case INTRINSIC -> getIntrinsicTypeString(((IntrinsicDataDescriptor)descriptor).getIntrinsicTypeId());
                case LANGUAGE_INTRINSIC -> ((LanguageIntrinsicDataDescriptor)descriptor).getLanguageTypeName();
                case OCTET_STREAM -> "[]byte";
                case ENUMERATOR -> toTypeName(Catalog.getEnumerator(((EnumeratorDataDescriptor) descriptor).getEnumeratorId()).getBaseName());
                case STRUCT -> toStructName(Catalog.getStruct(((StructDataDescriptor) descriptor).getStructId()).getBaseName());
                case TYPEDEF -> toTypeName(Catalog.getTypedef(((TypedefDataDescriptor) descriptor).getTypedefId()).getBaseName());
                case VOID -> "";
            };
    }

    /**
     * Returns the API type of an intrinsic data descriptor of the indicated type
     */
    static String getIntrinsicTypeString(IntrinsicTypeId id) {
        return switch (id) {
            case BOOLEAN -> "bool";
            case FLOAT -> "float64";
            case INT32 -> "int32";
            case INT64 -> "int64";
            case STRING,
                INT16_AS_STRING,
                INT24_AS_STRING,
                INT32_AS_STRING,
                INT64_AS_STRING,
                INT32_AS_DECIMAL_STRING -> "string";
            case STRING_ARRAY -> "[]string";
            case STRING_ARRAY_MAP -> "map[string][]string";
        };
    }

    static String getExposedTypeString(DataDescriptor descriptor) {
        var csz = CodeWriter.getIntAsStringContainerSize(descriptor);
        if (csz == 32) {
            return "int32";
        } else if (csz == 64) {
            return "int64";
        } else {
            return getTypeString(descriptor);
        }
    }

    /**
     * Returns the Go type name corresponding to the given data descriptor,
     * wrapped in LinkedList<> if the descriptor is an array.
     */
    static String getTypeString(DataDescriptor descriptor) {
        return (descriptor.isArray() ? "[]" : "") + getDataTypeString(descriptor);
    }

    /**
     * Produces the function name for an accessor getter function for a member with the given baseName
     * e.g., if the baseName is "IPMIStatus" we would return "GetIPMIStatus"
     */
    static String toGetterName(String baseName) {
        return "Get" + Writer.initialCharacterToUpperCase(baseName);
    }

    /**
     * Produces the function name for an accessor setter function for a member with the given baseName
     * e.g., if the baseName is "IPMIStatus" we would return "SetIPMIStatus"
     */
    static String toSetterName(String baseName) {
        return "Set" + Writer.initialCharacterToUpperCase(baseName);
    }

    static String toStructName(String baseName) {
        return Writer.initialCharacterToUpperCase(baseName);
    }

    static String toTypeName(String baseName) {
        return Writer.initialCharacterToUpperCase(baseName);
    }

    static boolean weShouldReturnAPointerFor(DataDescriptor descriptor) {
        return switch (descriptor.getDataTypeId()) {
            case ENUMERATOR, STRUCT, TYPEDEF -> !descriptor.isArray();
            case INTRINSIC, LANGUAGE_INTRINSIC, OCTET_STREAM, VOID -> false;
        };
    }
}
