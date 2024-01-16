//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.writers.java;

import com.liqid.sdk.generator.catalog.Catalog;
import com.liqid.sdk.generator.discreteDataModels.*;
import com.liqid.sdk.generator.liqidEntityModels.structs.Struct;
import com.liqid.sdk.generator.writers.Writer;

import static com.liqid.sdk.generator.discreteDataModels.DataTypeId.*;
import static com.liqid.sdk.generator.discreteDataModels.IntrinsicTypeId.*;

public class JavaCommon {

    static final String JAVA_PACKAGE_NAME = "com.liqid.sdk";

    private JavaCommon() {}

    /**
     * Returns the Java class name corresponding to the given data descriptor,
     * ignore whether the descriptor is an array.
     */
    static String getDataTypeString(DataDescriptor descriptor) {
        return
            switch (descriptor.getDataTypeId()) {
                case INTRINSIC -> getIntrinsicTypeString(((IntrinsicDataDescriptor)descriptor).getIntrinsicTypeId());
                case LANGUAGE_INTRINSIC -> ((LanguageIntrinsicDataDescriptor)descriptor).getLanguageTypeName();
                case OCTET_STREAM -> "Byte[]";
                case ENUMERATOR -> toClassName(Catalog.getEnumerator(((EnumeratorDataDescriptor) descriptor).getEnumeratorId()).getBaseName());
                case STRUCT -> toClassName(Catalog.getStruct(((StructDataDescriptor) descriptor).getStructId()).getBaseName());
                case TYPEDEF -> toCategoryName(Catalog.getTypedef(((TypedefDataDescriptor) descriptor).getTypedefId()).getBaseName());
                case VOID -> "void";
            };
    }

    static String getExposedDataTypeString(DataDescriptor descriptor) {
        if (descriptor.getDataTypeId() == INTRINSIC) {
            var idd = (IntrinsicDataDescriptor) descriptor;
            switch (idd.getIntrinsicTypeId()) {
                case INT16_AS_STRING, INT24_AS_STRING, INT32_AS_STRING, INT32_AS_DECIMAL_STRING -> { return "Integer"; }
                case INT64_AS_STRING -> { return "Long"; }
                default -> {}
            }
        }

        return getDataTypeString(descriptor);
    }

    /**
     * Returns the API type of an intrinsic data descriptor of the indicated type
     */
    static String getIntrinsicTypeString(IntrinsicTypeId id) {
        return switch (id) {
            case BOOLEAN -> "Boolean";
            case FLOAT -> "Double";
            case INT32 -> "Integer";
            case INT64 -> "Long";
            case STRING,
                INT16_AS_STRING,
                INT24_AS_STRING,
                INT32_AS_STRING,
                INT64_AS_STRING,
                INT32_AS_DECIMAL_STRING -> "String";
            case STRING_ARRAY -> "String[]";
            case STRING_ARRAY_MAP -> "HashMap<String, String[]>";
        };
    }

    /**
     * Returns the Java class name corresponding to the given data descriptor,
     * wrapped in LinkedList<> if the descriptor is an array.
     */
    static String getTypeString(DataDescriptor descriptor) {
        if (descriptor.isArray()) {
            return "LinkedList<" + getDataTypeString(descriptor) + ">";
        } else {
            return getDataTypeString(descriptor);
        }
    }

    static String getTypeString(Struct struct, boolean isArray) {
        if (isArray) {
            return "LinkedList<" + toClassName(struct.getBaseName()) + ">";
        } else {
            return toClassName(struct.getBaseName());
        }
    }

    /**
     * Produces the function name for a Builder add function for a member with the given baseName
     * e.g., if the baseName is "IPMIStatus" we would return "addIPMIStatus"
     */
    static String toAdderName(String baseName) {
        return "add" + Writer.initialCharacterToUpperCase(baseName);
    }

    static String toCategoryName(String baseName) {
        return Writer.initialCharacterToUpperCase(baseName);
    }

    static String toClassName(String baseName) {
        return Writer.initialCharacterToUpperCase(baseName);
    }

    /**
     * Produces the function name for an accessor getter function for a member with the given baseName
     * e.g., if the baseName is "IPMIStatus" we would return "getIPMIStatus"
     */
    static String toGetterName(String baseName) {
        return "get" + Writer.initialCharacterToUpperCase(baseName);
    }

    /**
     * Produces a string containing the fully-qualified name of the Wrapper class for the given data type.
     * e.g., the wrapper for class 'Foo', fully qualified, is 'Foo.FooWrapper',
     *  but for 'Integer' is simply 'IntegerWrapper'.
     */
    static String toQualifiedWrapperName(DataDescriptor descriptor) {
        var dtStr = getDataTypeString(descriptor);

        if (descriptor.getDataTypeId() == INTRINSIC) {
            var idd = (IntrinsicDataDescriptor) descriptor;
            if (idd.getIntrinsicTypeId() == STRING_ARRAY) {
                return "StringArrayWrapper";
            } else if (idd.getIntrinsicTypeId() == STRING_ARRAY_MAP) {
                    return "StringArrayMapWrapper";
            } else {
                return dtStr + "Wrapper";
            }
        } else {
            return dtStr + "." + dtStr + "Wrapper";
        }
    }

    /**
     * Produces the function name for an accessor or builder setter function for a member with the given baseName
     * e.g., if the baseName is "IPMIStatus" we would return "setIPMIStatus"
     */
    static String toSetterName(String baseName) {
        return "set" + Writer.initialCharacterToUpperCase(baseName);
    }
}
