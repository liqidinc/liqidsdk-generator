//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.liqidEntityModels;

import com.liqid.sdk.generator.writers.LanguageId;

import java.util.Collection;

public class LiqidEntity implements Comparable<LiqidEntity> {

    private final String _baseName;
    private final Description _description;
    private final ImportRequirements _importRequirements;
    private final LanguageId _specificLanguageId;

    protected LiqidEntity(String baseName,
                          Description description) {
        _baseName = baseName;
        _description = description;
        _importRequirements = new ImportRequirements();
        _specificLanguageId = null;
    }

    protected LiqidEntity(String baseName,
                          Description description,
                          ImportRequirements importRequirements,
                          LanguageId specificLanguageId) {
        _baseName = baseName;
        _description = description;
        _importRequirements = importRequirements;
        _specificLanguageId = specificLanguageId;
    }

    public String getBaseName() { return _baseName; }
    public Description getDescription() { return _description; }
    public ImportRequirements getImportRequirements() { return _importRequirements; }

    public Collection<String> getImportRequirementsFor(LanguageId languageId) {
        return _importRequirements.getImportStringsFor(languageId);
    }

    public LanguageId getSpecificLanguageId() { return _specificLanguageId; }
    public final boolean hasSpecificLanguageId() { return getSpecificLanguageId() != null; }

    public final boolean matches(LanguageId languageId) {
        return (!hasSpecificLanguageId() || getSpecificLanguageId() == languageId);
    }

    public static Collection<?> sort(Collection<?> source) {
        return source.stream()
                     .sorted((a, b) -> ((LiqidEntity)a).getBaseName().compareToIgnoreCase(((LiqidEntity)b).getBaseName()))
                     .toList();
    }

    @Override
    public int compareTo(LiqidEntity entity) {
        return this._baseName.compareTo(entity._baseName);
    }
}
