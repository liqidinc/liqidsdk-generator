//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.writers.python;

import com.liqid.sdk.generator.catalog.Catalog;
import com.liqid.sdk.generator.discreteDataModels.*;
import com.liqid.sdk.generator.writers.Writer;

import static com.liqid.sdk.generator.discreteDataModels.DataTypeId.INTRINSIC;

public class PythonCommon {

    private PythonCommon(){}

    /**
     * Returns the Python type name corresponding to the given data descriptor,
     * ignore whether the descriptor is an array.
     */
    static String getDataTypeString(DataDescriptor descriptor) {
        return
            switch (descriptor.getDataTypeId()) {
                case LANGUAGE_INTRINSIC -> ((LanguageIntrinsicDataDescriptor)descriptor).getLanguageTypeName();
                case INTRINSIC -> getIntrinsicTypeString(((IntrinsicDataDescriptor)descriptor).getIntrinsicTypeId());
                case OCTET_STREAM -> "bytestring";
                case ENUMERATOR -> getIntrinsicTypeString(Catalog.getEnumerator(((EnumeratorDataDescriptor) descriptor).getEnumeratorId()).getIntrinsicTypeId());
                case STRUCT -> toClassName(Catalog.getStruct(((StructDataDescriptor) descriptor).getStructId()).getBaseName());
                case TYPEDEF -> toTypeDefName(Catalog.getTypedef(((TypedefDataDescriptor) descriptor).getTypedefId()).getBaseName());
                case VOID -> "void";
            };
    }

    static String getExposedDataTypeString(DataDescriptor descriptor) {
        if (descriptor.isArray()) {
            return "list";
        } else {
            if (descriptor.getDataTypeId() == INTRINSIC) {
                var idd = (IntrinsicDataDescriptor) descriptor;
                switch (idd.getIntrinsicTypeId()) {
                    case INT16_AS_STRING,
                        INT24_AS_STRING,
                        INT32_AS_STRING,
                        INT64_AS_STRING,
                        INT32_AS_DECIMAL_STRING-> {
                        return "int";
                    }
                    default -> {
                    }
                }
            }
        }

        var temp = getDataTypeString(descriptor);
        if (temp.endsWith("()")) {
            temp = temp.substring(0, temp.length() - 2);
        }
        return temp;
    }

    /**
     * Returns the language-specific type for the entity, insofar as the API is concerned
     */
    static String getIntrinsicTypeString(IntrinsicTypeId id) {
        return switch (id) {
            case BOOLEAN -> "bool";
            case FLOAT -> "float";
            case INT32,
                INT64 -> "int";
            case STRING,
                INT16_AS_STRING,
                INT24_AS_STRING,
                INT32_AS_STRING,
                INT64_AS_STRING,
                INT32_AS_DECIMAL_STRING -> "str";
            case STRING_ARRAY -> "list()";
            case STRING_ARRAY_MAP -> "dict()";
        };
    }

    /**
     * Returns the Python type name corresponding to the given data descriptor
     */
    static String getTypeString(DataDescriptor descriptor) {
        if (descriptor.isArray()) {
            return "list";
        } else {
            return getDataTypeString(descriptor);
        }
    }

    private static String toTypeDefName(String baseName) {
        return Writer.initialCharacterToUpperCase(baseName);
    }

    private static String toClassName(String baseName) {
        return Writer.initialCharacterToUpperCase(baseName);
    }
}
