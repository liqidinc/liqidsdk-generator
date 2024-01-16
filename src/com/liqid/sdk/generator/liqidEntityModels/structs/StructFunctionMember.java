//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.liqidEntityModels.structs;

import com.liqid.sdk.generator.catalog.Catalog;
import com.liqid.sdk.generator.liqidEntityModels.Description;
import com.liqid.sdk.generator.liqidEntityModels.FunctionId;
import com.liqid.sdk.generator.liqidEntityModels.ImportRequirements;
import com.liqid.sdk.generator.writers.LanguageId;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class StructFunctionMember extends StructMember {

    private final FunctionId _functionId;

    public StructFunctionMember(String baseName,
                                Description description,
                                ImportRequirements importRequirements,
                                LanguageId specificLanguage,
                                FunctionId functionId) {
        super(baseName, description, importRequirements, specificLanguage);
        _functionId = functionId;
    }

    public final FunctionId getFunctionId() { return _functionId; }

    @Override
    public Collection<String> getImportRequirementsFor(LanguageId languageId) {
        Set<String> coll = new HashSet<>(super.getImportRequirementsFor(languageId));
        var func = Catalog.getFunction(_functionId);
        coll.addAll(func.getImportRequirementsFor(languageId));
        return coll;
    }

    @Override
    public LanguageId getSpecificLanguageId() {
        return Catalog.getFunction(_functionId).getSpecificLanguageId();
    }

    public static class Builder {

        private static final String ExceptionFmt = "%s() was not invoked in Builder for class StructFunctionMember";

        private FunctionId _functionId = null;
        private final ImportRequirements _importRequirements = new ImportRequirements();
        private LanguageId _specificLanguageId = null;

        public Builder addImportRequirements(LanguageId languageId, String importString) {
            _importRequirements.addRequirement(languageId, importString);
            return this;
        }

        public Builder setFunctionId(FunctionId value) { _functionId = value; return this; }

        public Builder setSpecificLanguageId(LanguageId value) { _specificLanguageId = value; return this; }

        public StructFunctionMember build() {
            if (_functionId == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "addDescription"));
            }

            return new StructFunctionMember("<function>",
                                            new Description(new String[]{}),
                                            _importRequirements,
                                            _specificLanguageId,
                                            _functionId);
        }
    }
}
