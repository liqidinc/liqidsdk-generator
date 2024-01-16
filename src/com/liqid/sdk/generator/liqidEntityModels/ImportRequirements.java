//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.liqidEntityModels;

import com.liqid.sdk.generator.writers.LanguageId;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

public class ImportRequirements extends LinkedHashMap<LanguageId, LinkedList<String>> {

    public void addRequirement(LanguageId languageId,
                               String importString) {
        if (!containsKey(languageId)) {
            put(languageId, new LinkedList<>());
        }
        get(languageId).add(importString);
    }

    public Collection<String> getImportStringsFor(LanguageId languageId) {
        if (containsKey(languageId)) {
            return get(languageId);
        } else {
            return Collections.emptyList();
        }
    }

    public void dump(BufferedWriter writer) throws IOException {
        writer.write("/* FOO\n");
        for (var lid : keySet()) {
            var vs = get(lid);
            for (var s : vs) {
                writer.write("  " + lid + ":" + s + "\n");
            }
        }
        writer.write(" */\n");
    }
}
