//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.liqidEntityModels.parameters;

import com.liqid.sdk.generator.discreteDataModels.DataDescriptor;
import com.liqid.sdk.generator.liqidEntityModels.Description;
import com.liqid.sdk.generator.liqidEntityModels.ImportRequirements;
import com.liqid.sdk.generator.liqidEntityModels.structs.StructMemberTarget;
import com.liqid.sdk.generator.writers.LanguageId;

import java.util.*;

/**
 * Describes a parameter which is taken from the SDK Client and wired into a struct which is then passed through to the REST API.
 * This value may be used in more than one area; thus we have the capability of indicating multiple targets within the api struct.
 * It should also be noted that we may need to populate values in a struct which is a member of the api struct, or even 3rd or
 * 4th level member (although, at this point, that requirement does not exit).
 * <p>
 * So, each target is represented as a possibly-empty array of strings, indicating baseName values leading up to the
 * target struct, and a singular member baseName for the actual location to be populated.
 * <p>
 * Ex 1: The simplest case: we need to populate the target struct value with the baseName of "GroupName".
 *  There is one parameter (baseName recommended to be "GroupName" with one Target value:
 *      _parentMemberNames is empty
 *      _memberName contains "GroupName"
 * Ex 2: We need to populate the "Shelf" member of coordinates structs names "Location" and "Owner" in the base struct.
 *  There is one parameter (baseName recommended to be "Shelf") with two Target values:
 *      value 1:
 *          _parentMemberNames contains ["Location"]
 *          _memberName contains "Shelf"
 *      value 2:
 *          _parentMemberNames contains ["Owner"]
 *          _memberName contains "Shelf"
 * Ex 3: (hypothetical) We need to populate the "Foo" member of a struct with baseName "Bar",
 * and the "Fee" member of a struct with baseName "Baz" which is a member of "Bar" (referenced above)
 * with a string value which we have decided has the baseName of "Xyzzy".
 *  The parameter "Xyzzy" has two Target values:
 *      value 1:
 *          _parentMemberNames contains ["Bar"]
 *          _baseName contains "Foo"
 *      value 2:
 *          _parentMemberNames contains ["Bar", "Baz"]
 *          _baseName contains "Fee"
 * <p>
 * The 'root' struct is never named here, as the writer creates code to build the struct, and names it according to its
 * own preferences - e.g., the Java writer might use 'obj' for the base name. In any case, only the names of subordinate
 * structs need be explicitly defined, which is why we need baseNames of subordinate structs, but not of the root struct.
 */
public class WiredParameter extends Parameter {

    private final Collection<StructMemberTarget> _targets = new LinkedList<>();

    private WiredParameter(String baseName,
                           Description description,
                           ImportRequirements importRequirements,
                           LanguageId specificLanguageId,
                           DataDescriptor dataDescriptor,
                           Collection<StructMemberTarget> targets,
                           boolean isOptional) {
        super(baseName, description, importRequirements, specificLanguageId, dataDescriptor, isOptional);
        _targets.addAll(targets);
    }

    public Collection<StructMemberTarget> getTargets() { return new LinkedList<>(_targets); }

    public static class Builder {

        private static final String ExceptionFmt = "%s() was not invoked in Builder for class WiredParameter";

        private final List<StructMemberTarget> _targets = new LinkedList<>();
        private String _baseName = null;
        private DataDescriptor _dataDescriptor = null;
        private final List<String> _description = new LinkedList<>();
        private final ImportRequirements _importRequirements = new ImportRequirements();
        private boolean _isOptional = false;
        private LanguageId _specificLanguageId = null;

        public Builder addDescription(String value) { _description.add(value); return this; }

        public Builder addImportRequirements(LanguageId languageId, String importString) {
            _importRequirements.addRequirement(languageId, importString);
            return this;
        }

        public Builder addTarget(StructMemberTarget value) { _targets.add(value); return this; }
        public Builder setBaseName(String value) { _baseName = value; return this; }
        public Builder setDataDescriptor(DataDescriptor value) { _dataDescriptor = value; return this; }
        public Builder setIsOptional(boolean value) { _isOptional = value; return this; }
        public Builder setSpecificLanguageId(LanguageId value) { _specificLanguageId = value; return this; }

        public WiredParameter build() {
            if (_baseName == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setBaseName"));
            }

            if (_dataDescriptor == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setDataDescriptor"));
            }

            if (_description.isEmpty()) {
                throw new RuntimeException("addDescription");
            }

            if (_targets.isEmpty()) {
                throw new RuntimeException(String.format(ExceptionFmt, "addTarget"));
            }

            return new WiredParameter(_baseName,
                                      new Description(_description),
                                      _importRequirements,
                                      _specificLanguageId,
                                      _dataDescriptor,
                                      _targets,
                                      _isOptional);
        }
    }
}
