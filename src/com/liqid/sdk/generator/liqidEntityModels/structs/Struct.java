//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.liqidEntityModels.structs;

import com.liqid.sdk.generator.catalog.Catalog;
import com.liqid.sdk.generator.discreteDataModels.DataTypeId;
import com.liqid.sdk.generator.discreteDataModels.StructDataDescriptor;
import com.liqid.sdk.generator.liqidEntityModels.*;
import com.liqid.sdk.generator.writers.LanguageId;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Models a data struct as defined by the REST API.
 */
public final class Struct extends LiqidEntity {

    /**
     * If this struct extends a base struct, that base struct is indicated here
     */
    private final StructId _baseStructId;

    /**
     * If this struct has a Builder
     */
    private final Boolean _isBuildable;

    /**
     * If this struct is hard-coded within the language-specific SDK (i.e., is not auto-generated)
     */
    private final Boolean _isHardCoded;

    /**
     * If this struct is serializable, this is true.
     */
    private final Boolean _isSerializable;

    /**
     * Describes the various members of this struct
     */
    private final Collection<StructMember> _members = new LinkedList<>();

    /**
     * Internal identifier for this struct
     */
    private final StructId _structId;

    public Struct(StructId structId,
                  String baseName,
                  Description description,
                  ImportRequirements importRequirements,
                  LanguageId specificLanguageId,
                  StructId baseStructId,
                  Collection<StructMember> members,
                  boolean isBuildable,
                  boolean isSerializable,
                  boolean isHardCoded) {
        super(baseName, description, importRequirements, specificLanguageId);
        _structId = structId;
        _baseStructId = baseStructId;
        _members.addAll(members);
        _isBuildable = isBuildable;
        _isHardCoded = isHardCoded;
        _isSerializable = isSerializable;
    }

    public StructDataMember findAggregateDataMember(String baseName) {
        for (var m : getAggregateDataMembers()) {
            if (m.getBaseName().equals(baseName)) {
                return m;
            }
        }
        throw new RuntimeException("Cannot find member '" + baseName + "' in struct '" + getBaseName() + "'");
    }

    public Collection<StructMember> getAggregateMembersFor(LanguageId languageId) {
        var result = getBaseMembersFor(languageId);
        result.addAll(getMembersFor(languageId));
        return result;
    }

    public Collection<StructDataMember> getAggregateDataMembers() {
        var result = getBaseDataMembers();
        result.addAll(getDataMembers());
        return result;
    }

    public Collection<StructDataMember> getAggregateDataMembersFor(LanguageId languageId) {
        var result = getBaseDataMembersFor(languageId);
        result.addAll(getDataMembersFor(languageId));
        return result;
    }

    public Collection<StructFunctionMember> getAggregateFunctionMembers() {
        var result = getBaseFunctionMembers();
        result.addAll(getFunctionMembers());
        return result;
    }

    public Collection<StructFunctionMember> getAggregateFunctionMembersFor(LanguageId languageId) {
        var result = getBaseFunctionMembersFor(languageId);
        result.addAll(getFunctionMembersFor(languageId));
        return result;
    }

    public Collection<StructMember> getAggregateMembers() {
        var result = getBaseMembers();
        result.addAll(_members);
        return result;
    }

    public Collection<StructMember> getBaseMembers() {
        var result = new LinkedList<StructMember>();
        if (hasBaseStruct()) {
            result.addAll(getBaseStruct().getAggregateMembers());
        }
        return result;
    }

    public Collection<StructMember> getBaseMembersFor(LanguageId languageId) {
        var result = new LinkedList<StructMember>();
        if (hasBaseStruct()) {
            result.addAll(getBaseStruct().getAggregateMembersFor(languageId));
        }
        return result;
    }

    public Collection<StructDataMember> getBaseDataMembers() {
        var result = new LinkedList<StructDataMember>();
        if (hasBaseStruct()) {
            result.addAll(getBaseStruct().getAggregateDataMembers());
        }
        return result;
    }

    public Collection<StructDataMember> getBaseDataMembersFor(LanguageId languageId) {
        return getBaseMembers().stream()
                               .filter(m -> m instanceof StructDataMember && m.matches(languageId))
                               .map(m -> (StructDataMember) m)
                               .collect(Collectors.toCollection(LinkedList::new));
    }

    public Collection<StructFunctionMember> getBaseFunctionMembers() {
        var result = new LinkedList<StructFunctionMember>();
        if (hasBaseStruct()) {
            result.addAll(getBaseStruct().getAggregateFunctionMembers());
        }
        return result;
    }

    public Collection<StructFunctionMember> getBaseFunctionMembersFor(LanguageId languageId) {
        return getBaseMembers().stream()
                               .filter(m -> m instanceof StructFunctionMember && m.matches(languageId))
                               .map(m -> (StructFunctionMember) m)
                               .collect(Collectors.toCollection(LinkedList::new));
    }

    public Struct getBaseStruct() { return Catalog.getStruct(_baseStructId); }

    public Collection<StructDataMember> getDataMembers() {
        return _members.stream()
                       .filter(m -> m instanceof StructDataMember)
                       .map(m -> (StructDataMember) m)
                       .collect(Collectors.toCollection(LinkedList::new));
    }

    public Collection<StructDataMember> getDataMembersFor(LanguageId languageId) {
        return _members.stream()
                       .filter(m -> m instanceof StructDataMember && m.matches(languageId))
                       .map(m -> (StructDataMember) m)
                       .collect(Collectors.toCollection(LinkedList::new));
    }

    public Collection<StructFunctionMember> getFunctionMembers() {
        return _members.stream()
                       .filter(m -> m instanceof StructFunctionMember)
                       .map(m -> (StructFunctionMember) m)
                       .collect(Collectors.toCollection(LinkedList::new));
    }

    public Collection<StructFunctionMember> getFunctionMembersFor(LanguageId languageId) {
        return _members.stream()
                       .filter(m -> m instanceof StructFunctionMember && m.matches(languageId))
                       .map(m -> (StructFunctionMember) m)
                       .collect(Collectors.toCollection(LinkedList::new));
    }

    public Collection<StructMember> getMembers() {
        return new LinkedList<>(_members);
    }

    public Collection<StructMember> getMembersFor(LanguageId languageId) {
        return _members.stream()
                       .filter(m -> m.matches(languageId))
                       .collect(Collectors.toCollection(LinkedList::new));
    }

    public StructId getStructId() { return _structId; }

    public boolean hasBaseStruct() { return _baseStructId != null; }
    public boolean isBuildable() { return _isBuildable; }
    public boolean isHardCoded() { return _isHardCoded; }
    public boolean isSerializable() { return _isSerializable; }

    public static class Builder {

        private static final String ExceptionFmt = "%s() was not invoked in Builder for class Struct";

        private String _baseName = null;
        private StructId _baseStructId = null;
        private final List<String> _description = new LinkedList<>();
        private final ImportRequirements _importRequirements = new ImportRequirements();
        private Boolean _isBuildable = true;
        private Boolean _isHardCoded = false;
        private Boolean _isSerializable = true;
        private final List<StructMember> _members = new LinkedList<>();
        private LanguageId _specificLanguageId = null;
        private StructId _structId = null;

        public Builder addDescription(String value) { _description.add(value); return this; }

        public Builder addImportRequirement(LanguageId languageId, String importString) {
            _importRequirements.addRequirement(languageId, importString);
            return this;
        }

        public Builder addMember(StructMember value) { _members.add(value); return this; }
        public Builder setBaseName(String value) { _baseName = value; return this; }
        public Builder setBaseStructId(StructId value) { _baseStructId = value; return this; }
        public Builder setIsBuildable(Boolean value) { _isBuildable = value; return this; }
        public Builder setIsHardCoded(Boolean value) { _isHardCoded = value; return this; }
        public Builder setIsSerializable(Boolean value) { _isSerializable = value; return this; }
        public Builder setSpecificLanguageId(LanguageId value) { _specificLanguageId = value; return this; }
        public Builder setStructId(StructId value) { _structId = value; return this; }

        public Struct build() {
            if (_structId == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setStructId"));
            }

            if (_baseName == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setBaseName"));
            }

            if (_description.isEmpty()) {
                throw new RuntimeException(String.format(ExceptionFmt, "addDescription"));
            }

            _members.stream()
                    .filter(m -> m instanceof StructDataMember sdm && sdm.getDataDescriptor().isArray() && sdm.getDefaultValue() != null)
                    .forEach(m -> {throw new RuntimeException("Member cannot be an array and have a default value");});

            return new Struct(_structId,
                              _baseName,
                              new Description(_description),
                              _importRequirements,
                              _specificLanguageId,
                              _baseStructId,
                              _members,
                              _isBuildable,
                              _isSerializable,
                              _isHardCoded);
        }
    }

    public static class DependencySorter {

        private final Collection<Struct> _content = new LinkedList<>();
        private final LinkedList<Struct> _result = new LinkedList<>();
        private final HashSet<String> _lookup = new HashSet<>();

        public DependencySorter(final Collection<Struct> source) {
            _content.addAll(source);
        }

        public boolean checkSource(final Struct source) {
            if (source.hasBaseStruct()) {
                var baseStruct = source.getBaseStruct();
                if (!_lookup.contains(baseStruct.getBaseName())) {
                    return false;
                }
            }

            for (var member : source.getMembers()) {
                if (member instanceof StructDataMember structDataMember) {
                    var memberDataDesc = structDataMember.getDataDescriptor();
                    if (memberDataDesc.getDataTypeId() == DataTypeId.STRUCT) {
                        var memberStructDataDescriptor = (StructDataDescriptor) memberDataDesc;
                        var memberStruct = Catalog.getStruct(memberStructDataDescriptor.getStructId());
                        if (!_lookup.contains(memberStruct.getBaseName())) {
                            return false;
                        }
                    }
                }
            }

            _result.add(source);
            _lookup.add(source.getBaseName());
            return true;
        }

        public void scanContent() {
            _content.removeIf(this::checkSource);
        }

        public Collection<Struct> sort() {
            _result.clear();
            _lookup.clear();

            var prevLen = _content.size();
            while (!_content.isEmpty()) {
                scanContent();
                var newLen = _content.size();
                if (prevLen == newLen) {
                    throw new RuntimeException("Illegal dependency cycle in struct definitions");
                }
            }

            return _result;
        }
    }
}
