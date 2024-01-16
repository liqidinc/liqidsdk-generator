//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.discreteDataModels;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class IntrinsicDataDescriptor extends DataDescriptor {

    public static class Translation {

        public final String _apiConstant;
        public final String _sdkConstant;

        public Translation(
            final String apiConstant,
            final String sdkConstant
        ) {
            _apiConstant = apiConstant;
            _sdkConstant = sdkConstant;
        }
    }

    private final IntrinsicTypeId _intrinsicTypeId;
    private final List<Translation> _translations = new LinkedList<>();

    public IntrinsicDataDescriptor(IntrinsicTypeId intrinsicTypeId) {
        super(false);
        _intrinsicTypeId = intrinsicTypeId;
    }

    public IntrinsicDataDescriptor(IntrinsicTypeId intrinsicTypeId, boolean isArray) {
        super(isArray);
        _intrinsicTypeId = intrinsicTypeId;
    }

    public IntrinsicDataDescriptor appendTranslation(
        final String apiConstant,
        final String sdkConstant
    ) {
        _translations.add(new Translation(apiConstant, sdkConstant));
        return this;
    }

    public final DataTypeId getDataTypeId() { return DataTypeId.INTRINSIC; }
    public final IntrinsicTypeId getIntrinsicTypeId() { return _intrinsicTypeId; }
    public final Collection<Translation> getTranslations() { return new LinkedList<>(_translations); }
    public final boolean hasTranslations() { return !_translations.isEmpty(); }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof IntrinsicDataDescriptor idd) && (idd._intrinsicTypeId == _intrinsicTypeId);
    }
}
