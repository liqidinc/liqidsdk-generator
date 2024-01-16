//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator;

import com.liqid.sdk.generator.writers.*;
import com.liqid.sdk.generator.writers.go.GoCodeWriter;
import com.liqid.sdk.generator.writers.go.GoDocWriter;
import com.liqid.sdk.generator.writers.java.JavaCodeWriter;
import com.liqid.sdk.generator.writers.java.JavaDocWriter;
import com.liqid.sdk.generator.writers.python.PythonCodeWriter;
import com.liqid.sdk.generator.writers.python.PythonDocWriter;

import java.io.IOException;

public class Main {

    public static final String VERSION = "3.3.0";

    private static final CodeWriter[] CODE_WRITERS = {
        new GoCodeWriter(),
        new JavaCodeWriter(),
        new PythonCodeWriter(),
    };

    private static final DocWriter[] DOC_WRITERS = {
        new GoDocWriter(),
        new JavaDocWriter(),
        new PythonDocWriter(),
    };

    public static void main(String[] args) throws IOException {
        System.out.println("Writing code...");
        for (var w : CODE_WRITERS) {
            w.writeCode();
        }

        System.out.println("Writing docs...");
        for (var w : DOC_WRITERS) {
            w.writeDocs();
        }
    }
}
