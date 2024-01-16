//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.liqidEntityModels.structs;

import java.util.Collection;
import java.util.LinkedList;

public class StructMemberTarget {
    private final Collection<String> _parentMemberNames = new LinkedList<>();
    private final String _memberName;

    public StructMemberTarget(Collection<String> parentMemberNames,
                              String memberName) {
        _parentMemberNames.addAll(parentMemberNames);
        _memberName = memberName;
    }

    public StructMemberTarget(String parentMemberName,
                              String memberName) {
        _parentMemberNames.add(parentMemberName);
        _memberName = memberName;
    }

    public StructMemberTarget(String memberName) {
        _memberName = memberName;
    }

    public Collection<String> getParentMemberNames() {
        return new LinkedList<>(_parentMemberNames);
    }

    public String getMemberName() {
        return _memberName;
    }

    @Override
    public String toString() {
        var s = String.join(".", _parentMemberNames);
        s += "." + _memberName;
        return s;
    }
}
