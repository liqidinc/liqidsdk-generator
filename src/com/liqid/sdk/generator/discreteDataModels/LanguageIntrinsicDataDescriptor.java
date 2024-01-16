//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.discreteDataModels;

import com.liqid.sdk.generator.writers.LanguageId;

public class LanguageIntrinsicDataDescriptor extends DataDescriptor {

    private final LanguageId _languageId;
    private final String _languageTypeName;

    public LanguageIntrinsicDataDescriptor(LanguageId languageId,
                                           String languageTypeName) {
        this(languageId, languageTypeName, false);
    }

    public LanguageIntrinsicDataDescriptor(LanguageId languageId,
                                           String languageTypeName,
                                           boolean isArray) {
        super(isArray);
        _languageId = languageId;
        _languageTypeName = languageTypeName;
    }

    public final DataTypeId getDataTypeId() { return DataTypeId.LANGUAGE_INTRINSIC; }
    public final LanguageId getLanguageId() { return _languageId; }
    public final String getLanguageTypeName() { return _languageTypeName; }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof LanguageIntrinsicDataDescriptor lidd)
            && (lidd._languageId == _languageId)
            && (lidd._languageTypeName.equals(_languageTypeName));
    }
}
