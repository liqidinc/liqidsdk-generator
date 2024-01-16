//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.writers;

import com.liqid.sdk.generator.Main;
import com.liqid.sdk.generator.catalog.Catalog;
import com.liqid.sdk.generator.liqidEntityModels.*;
import com.liqid.sdk.generator.liqidEntityModels.functions.Function;
import com.liqid.sdk.generator.liqidEntityModels.structs.Struct;
import com.liqid.sdk.generator.liqidEntityModels.types.Enumerator;
import com.liqid.sdk.generator.liqidEntityModels.types.Typedef;

import java.io.BufferedWriter;
import java.io.IOException;

public abstract class DocWriter extends Writer {

    protected static final String[] COPYRIGHT_TEXT = {
        "Copyright (c) 2022 Liqid, Inc. All rights reserved.",
        "Redistribution and use in source and binary forms, with or without modification, are not permitted without prior consent."
    };

    protected static final int LEFT_MARGIN_EM = 4;

    protected static final String TABLE_HEADER_TAG = "<th style=\"padding:3px;border:1px solid black;border-collapse:collapse;\">";
    protected static final String TABLE_HEADER_TAG_NB = "<th style=\"padding:3px;\">";
    protected static final String TABLE_CELL_TAG = "<td style=\"padding:3px;border:1px solid black;border-collapse:collapse;\">";
    protected static final String TABLE_CELL_TAG_NB = "<td style=\"padding:3px\">";
    protected static final String TABLE_ROW_TAG = "<tr>";
    protected static final String TABLE_TAG =
        String.format("<table style=\"margin-left:%dem;border:1px solid black;border-collapse:collapse;\">", LEFT_MARGIN_EM);
    protected static final String TABLE_TAG_NB = String.format("<table style=\"margin-left:%dem;\">", LEFT_MARGIN_EM);
    protected static final String TABLE_TAG_NM = "<table style=\"border:1px solid black;border-collapse:collapse;\">";

    public abstract String getCategoryFileName(Category category);
    public abstract String getEnumeratorFileName(Enumerator enumerator);
    public abstract String getStructFileName(Struct struct);
    public abstract String getTypedefFileName(Typedef typedef);
    public abstract void writeDocs() throws IOException;

    //  The following produce html links for each entity.
    public abstract String getCategoryLink(Category category);
    public abstract String getEnumeratorLink(Enumerator enumerator);
    public abstract String getFunctionLink(Function function);
    public abstract String getStructLink(Struct struct);
    public abstract String getTypedefLink(Typedef typedef);

    protected static String embedInSpan(String text,
                                        Integer margin,
                                        String fontFamily,
                                        String color) {
        return getSpanTag(margin, fontFamily, color) + text + "</span>";
    }

    protected static String getSpanTag(Integer margin,
                                       String fontFamily,
                                       String color) {
        var sb = new StringBuilder();
        sb.append("<span style=\"");

        if (margin != null) {
            sb.append("margin-left:").append(margin).append("em;");
        }

        if (fontFamily != null) {
            sb.append("font-family:").append(fontFamily).append(";");
        }

        if (color != null) {
            sb.append("color:").append(color).append(";");
        }

        sb.append("\">");
        return sb.toString();
    }

    /**
     * Translates a Description line of text, replacing tokens such as {struct Foo} with
     * HTML name of the entity, wrapped in an appropriate color with a hyperlink.
     */
    @Override
    String translate(String text) {
        var tokens = tokenizeDescription(text);
        for (var tx = 0; tx < tokens.length; ++tx) {
            var token = tokens[tx];
            if (token.charAt(0) == '{' && token.charAt(token.length() - 1) == '}') {
                var subTokes = token.split(" ");
                if (subTokes.length == 2) {
                    var type = subTokes[0].substring(1);
                    var name = subTokes[1].substring(0, subTokes[1].length() - 1);

                    switch (type) {
                        case "category" -> {
                            var category = Catalog.getCategory(name);
                            tokens[tx] = getCategoryLink(category);
                        }
                        case "enum" -> {
                            var enumerator = Catalog.getEnumerator(name);
                            tokens[tx] = getEnumeratorLink(enumerator);
                        }
                        case "function" -> {
                            var function = Catalog.getFunction(name);
                            tokens[tx] = getFunctionLink(function);
                        }
                        case "struct" -> {
                            var struct = Catalog.getStruct(name);
                            tokens[tx] = getStructLink(struct);
                        }
                        case "typedef" -> {
                            var typedef = Catalog.getTypedef(name);
                            tokens[tx] = getTypedefLink(typedef);
                        }
                    }
                }
            }
        }

        return String.join("", tokens);
    }

    protected static void writeFileHeader(BufferedWriter writer, String languageString) throws IOException {
        writer.write("<!DOCTYPE html>\n");
        writer.write("<html lang=\"en\">\n");
        writer.write("<head>\n");
        writer.write("    <meta charset=\"UTF-8\">\n");
        writer.write(String.format("    <title>Liqid SDK Programming Reference for %s</title>\n",
                                   languageString));
        writer.write("</head>\n");
        writer.write("<body>\n");
        writer.write("<h1>\n");
        writer.write(String.format("Liqid&trade; SDK Programming Reference for %s - Version %s<br>\n",
                                   languageString, Main.VERSION));
        writer.write("</h1>\n");
        writer.write("<hr>\n");

    }

    protected static void writeFileFooter(BufferedWriter writer) throws IOException {
        writer.write("<hr>\n");
        for (var s : COPYRIGHT_TEXT) {
            writer.write(String.format("%s<br>\n", s));
        }
        writer.write("Oracle and Java are registered trademarks of Oracle and/or its affiliates.<br>\n");
        writer.write("\"Python\" and the Python logos are trademarks or registered trademarks of the Python Software Foundation.<br>\n");
        writer.write("Other names may be trademarks of their respective owners.\n");
        writer.write("</body>\n");
        writer.write("</html>\n");
    }
}
