//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.writers.go;

import com.liqid.sdk.generator.catalog.Catalog;
import com.liqid.sdk.generator.discreteDataModels.*;
import com.liqid.sdk.generator.liqidEntityModels.Category;
import com.liqid.sdk.generator.liqidEntityModels.Description;
import com.liqid.sdk.generator.liqidEntityModels.LiqidEntity;
import com.liqid.sdk.generator.liqidEntityModels.StructId;
import com.liqid.sdk.generator.liqidEntityModels.functions.Function;
import com.liqid.sdk.generator.liqidEntityModels.structs.Struct;
import com.liqid.sdk.generator.liqidEntityModels.types.Enumerator;
import com.liqid.sdk.generator.liqidEntityModels.types.Typedef;
import com.liqid.sdk.generator.writers.DocWriter;
import com.liqid.sdk.generator.writers.LanguageId;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

public class GoDocWriter extends DocWriter {

    private static final String GO_DOCS_BASE_DIRECTORY = "../go/liqidsdk/docs/";

    private static final String CATEGORY_COLOR = "fuchsia";
    private static final String CLASS_NAME_COLOR = "orangered";
    private static final String ENUM_NAME_COLOR = "brown";
    private static final String ENUM_COMPONENT_NAME_COLOR = "purple";
    private static final String FUNCTION_NAME_COLOR = "green";
    private static final String MEMBER_NAME_COLOR = "blue";
    private static final String PARAMETER_NAME_COLOR = "olive";
    private static final String TYPEDEF_NAME_COLOR = "orangered";

    private static final String DECLARATION_HEADING = "<h3>Declaration:</h3>\n";
    private static final String DESCRIPTION_HEADING = "<h3>Description:</h3>\n";
    private static final String REFERENCES_HEADING = "<h3>See Also:</h3>\n";

    @Override
    public LanguageId getLanguageId() {
        return LanguageId.GO;
    }

    @Override
    public void writeDocs() throws IOException {
        writeIndex();
        writeCategories();
        writeEnumerators();
        writeStructs();
        writeTypedefs();
    }

    @Override
    public String convertCategoryName(String baseName) {
        return embedInSpan(initialCharacterToUpperCase(baseName), null, null, CATEGORY_COLOR);
    }

    @Override
    public String convertEnumName(String baseName) {
        return embedInSpan(initialCharacterToUpperCase(baseName),null, null, ENUM_NAME_COLOR);
    }

    @Override
    public String convertEnumComponentName(String baseName) {
        //  do not use this one
        throw new RuntimeException("Should not be here");
    }

    @Override
    public String convertEnumComponentName(String enumBaseName, String componentBaseName) {
        var composite = initialCharacterToUpperCase(enumBaseName) + initialCharacterToUpperCase(componentBaseName);
        return embedInSpan(composite, null, null, ENUM_COMPONENT_NAME_COLOR);
    }

    @Override
    public String convertFunctionName(String baseName) {
        return embedInSpan(initialCharacterToUpperCase(baseName), null, null, FUNCTION_NAME_COLOR);
    }

    @Override
    public String convertParameterName(String baseName) {
        return embedInSpan(initialTokenToLowerCase(baseName), null, null, PARAMETER_NAME_COLOR);
    }

    @Override
    public String convertStructName(String baseName) {
        return embedInSpan(initialCharacterToUpperCase(baseName), null, null, CLASS_NAME_COLOR);
    }

    @Override
    public String convertStructDataMemberName(String baseName) {
        return embedInSpan(initialCharacterToUpperCase(baseName), null, null, MEMBER_NAME_COLOR);
    }

    @Override
    public String convertTypedefName(String baseName) {
        return embedInSpan(convertStructName(baseName), null, null, TYPEDEF_NAME_COLOR);
    }

    @Override
    public String getCategoryFileName(Category category) {
        return "cat_" + initialCharacterToUpperCase(category.getBaseName()) + ".html";
    }

    @Override
    public String getEnumeratorFileName(Enumerator enumerator) {
        return "enum_" + initialCharacterToUpperCase(enumerator.getBaseName()) + ".html";
    }

    @Override
    public String getStructFileName(Struct struct) {
        return "struct_" + initialCharacterToUpperCase(struct.getBaseName()) + ".html";
    }

    @Override
    public String getTypedefFileName(Typedef typedef) {
        return "type_" + initialCharacterToUpperCase(typedef.getBaseName()) + ".html";
    }

    @Override
    public String getCategoryLink(Category category) {
        return "<a href=\"" + getCategoryFileName(category) + "\">" + convertCategoryName(category) + "</a>";
    }

    @Override
    public String getEnumeratorLink(Enumerator enumerator) {
        return "<a href=\"" + getEnumeratorFileName(enumerator) + "\">" + convertEnumName(enumerator) + "</a>";
    }

    //  A function link is a link to the containing struct with the function baseName as the index
    @Override
    public String getFunctionLink(Function function) {
        //  If we ever support standalone functions (not part of a class), we'll need to do some small work here
        var struct = Catalog.getStructFor(function.getFunctionId());
        var structFile = getStructFileName(struct);
        var index = function.getBaseName();
        return "<a href=\"" + structFile + "#" + index + "\">" + function.getBaseName() + "()</a>";
    }

    @Override
    public String getStructLink(Struct struct) {
        return "<a href=\"" + getStructFileName(struct) + "\">" + convertStructName(struct) + "</a>";
    }

    @Override
    public String getTypedefLink(Typedef typedef) {
        return "<a href=\"" + getTypedefFileName(typedef) + "\">" + convertTypedefName(typedef) + "</a>";
    }

    //  ------------------------------------------------------------------------
    //  Index
    //  ------------------------------------------------------------------------

    private void writeIndex() throws IOException {
        var fileName = "index.html";
        var fullName = GO_DOCS_BASE_DIRECTORY + fileName;
        System.out.println("Writing " + fullName + "...");
        var w = new BufferedWriter(new FileWriter(fullName));

        writeFileHeader(w);

        writeIndexCategories(w);
        writeIndexEnums(w);
        writeIndexStructsAndTypedefs(w);

        writeFileFooter(w);
        w.close();
    }

    private void writeIndexCategories(BufferedWriter writer) throws IOException {
        writer.write("<h2><b>Categories:</b></h2>\n");
        writer.write(TABLE_TAG);
        writer.write(TABLE_ROW_TAG + TABLE_HEADER_TAG + "Category</th>" + TABLE_HEADER_TAG + "Description</th></tr>\n");

        for (var c : Category.sort(Catalog.getCategories())) {
            var category = (Category) c;
            writer.write(TABLE_ROW_TAG
                             + TABLE_CELL_TAG
                             + getLinkTo(category)
                             + "</td>");

            var sb = new StringBuilder();
            sb.append(TABLE_CELL_TAG);
            for (var s : translate(category.getDescription())) {
                sb.append(s).append("<br>\n");
            }
            sb.append("</td></tr>");
            writer.write(sb.toString());
        }
        writer.write("</table><br>\n");
    }

    private void writeIndexEnums(BufferedWriter writer) throws IOException {
        writer.write("<h2><b>Enumerators:</b></h2>\n");
        writer.write(TABLE_TAG);
        writer.write(TABLE_ROW_TAG + TABLE_HEADER_TAG + "Enumerator</th>" + TABLE_HEADER_TAG + "Description</th></tr>\n");

        for (var e : Enumerator.sort(Catalog.getEnumeratorsFor(LanguageId.GO))) {
            var enumerator = (Enumerator) e;
            writer.write(TABLE_ROW_TAG
                             + TABLE_CELL_TAG
                             + getLinkTo(enumerator)
                             + "</td>");

            var sb = new StringBuilder();
            sb.append(TABLE_CELL_TAG);
            for (var s : translate(enumerator.getDescription())) {
                sb.append(s).append("<br>\n");
            }
            sb.append("</td></tr>");
            writer.write(sb.toString());
        }
        writer.write("</table><br>\n");
    }

    private void writeIndexStructsAndTypedefs(BufferedWriter writer) throws IOException {
        writer.write("<h2><b>Classes:</b></h2>\n");
        writer.write(TABLE_TAG);
        writer.write(TABLE_ROW_TAG + TABLE_HEADER_TAG + "Class</th>" + TABLE_HEADER_TAG + "Description</th></tr>\n");

        for (var s : Struct.sort(Catalog.getStructsFor(LanguageId.GO))) {
            var struct = (Struct) s;
            writer.write(TABLE_ROW_TAG
                             + TABLE_CELL_TAG
                             + getLinkTo(struct)
                             + "</td>");

            var sb = new StringBuilder();
            sb.append(TABLE_CELL_TAG);
            for (var str : translate(struct.getDescription())) {
                sb.append(str).append("<br>\n");
            }
            sb.append("</td></tr>");
            writer.write(sb.toString());
        }

        for (var t : Typedef.sort(Catalog.getTypedefsFor(LanguageId.GO))) {
            var typedef = (Typedef) t;
            writer.write(TABLE_ROW_TAG
                             + TABLE_CELL_TAG
                             + getLinkTo(typedef)
                             + "</td>");

            var sb = new StringBuilder();
            sb.append(TABLE_CELL_TAG);
            for (var s : translate(typedef.getDescription())) {
                sb.append(s).append("<br>\n");
            }
            sb.append("</td></tr>");
            writer.write(sb.toString());
        }

        writer.write("</table><br>\n");
    }

    //  ------------------------------------------------------------------------
    //  Categories
    //  ------------------------------------------------------------------------

    private void writeCategories() throws IOException {
        for (var category : Catalog.getCategories()) {
            writeCategoryDocs(category);
        }
    }

    private void writeCategoryDocs(Category category) throws IOException {
        var fileName = getCategoryFileName(category);
        var fullName = GO_DOCS_BASE_DIRECTORY + fileName;
        System.out.println("Writing " + fullName + "...");
        var w = new BufferedWriter(new FileWriter(fullName));

        writeFileHeader(w);
        w.write("<h2>Category " + convertCategoryName(category) + "</h2>\n");
        writeTopLevelDescription(w, category.getDescription());

        writeCategoryContent(w, category);
        writeCategoryReferences(w, category);
        writeFileFooter(w);
        w.close();
    }

    private void writeCategoryContent(BufferedWriter writer, Category category) throws IOException {
        writer.write("<br><b>Related Functions:</b><br>\n");
        writer.write(TABLE_TAG + "\n");
        for (var fid : category.getFunctionIds()) {
            var func = Catalog.getFunction(fid);
            writer.write(TABLE_ROW_TAG
                             + TABLE_CELL_TAG
                             + getFunctionLink(func)
                             + "</td>\n");

            var sb = new StringBuilder();
            sb.append(TABLE_CELL_TAG);
            for (var s : translate(func.getDescription())) {
                sb.append(s).append("<br>");
            }
            sb.append("</td>");
            sb.append("</tr>\n");
            writer.write(sb.toString());
        }
        writer.write("</table>\n");
    }

    private void writeCategoryReferences(BufferedWriter writer, Category category) throws IOException {
        writer.write(REFERENCES_HEADING);
        var refList = LiqidEntity.sort(Catalog.getReferrers(category.getCategoryId(), LanguageId.GO));
        for (var ref : refList) {
            writer.write(embedInSpan(getLinkTo((LiqidEntity) ref), LEFT_MARGIN_EM, null, null) + "<br>\n");
        }
        writer.write(embedInSpan("<a href=\"index.html\">Index</a>",
                                 LEFT_MARGIN_EM, null, null) + "<br>\n");
    }

    //  ------------------------------------------------------------------------
    //  Enumerators
    //  ------------------------------------------------------------------------

    private void writeEnumerators() throws IOException {
        for (var enumerator : Catalog.getEnumeratorsFor(LanguageId.GO)) {
            writeEnumeratorDocs(enumerator);
        }
    }

    private void writeEnumeratorDocs(Enumerator enumerator) throws IOException {
        var fileName = getEnumeratorFileName(enumerator);
        var fullName = GO_DOCS_BASE_DIRECTORY + fileName;
        System.out.println("Writing " + fullName + "...");
        var w = new BufferedWriter(new FileWriter(fullName));

        var enumName = convertEnumName(enumerator);
        writeFileHeader(w);
        w.write("<h2>Enumerator " + enumName + "</h2>\n");

        writeTopLevelDescription(w, enumerator.getDescription());

        w.write(DECLARATION_HEADING);
        String declStr = "type "
            + embedInSpan(enumName, null, null, ENUM_NAME_COLOR)
            + " "
            + getDataDescriptorString(new IntrinsicDataDescriptor(enumerator.getIntrinsicTypeId()));
        w.write(embedInSpan(declStr, LEFT_MARGIN_EM, "monospace", null));

        writeEnumeratorContent(w, enumerator);
        writeEnumeratorReferences(w, enumerator);
        writeFileFooter(w);
        w.close();
    }

    private void writeEnumeratorContent(BufferedWriter writer, Enumerator enumerator) throws IOException {
        writer.write("<h3>Constants:</h3>\n");
        writer.write(TABLE_TAG + "\n");
        writer.write(TABLE_ROW_TAG
                         + TABLE_HEADER_TAG + "Identifier</th>"
                         + TABLE_HEADER_TAG + "Value</th>"
                         + TABLE_HEADER_TAG + "Description</th>"
                         + "</tr>\n");
        for (var comp : enumerator.getComponents()) {
            writer.write(TABLE_ROW_TAG
                             + TABLE_CELL_TAG + convertEnumComponentName(enumerator, comp) + "</td>"
                             + TABLE_CELL_TAG + comp.getLiteralString() + "</td>");
            var sb = new StringBuilder();
            sb.append(TABLE_CELL_TAG);
            for (var s : translate(comp.getDescription())) {
                sb.append(s).append("<br>");
            }
            sb.append("</td>");
            sb.append("</tr>\n");
            writer.write(sb.toString());
        }
        writer.write("</table>\n");
    }

    private void writeEnumeratorReferences(BufferedWriter writer, Enumerator enumerator) throws IOException {
        writer.write(REFERENCES_HEADING);
        var refList = LiqidEntity.sort(Catalog.getReferrers(enumerator.getEnumeratorId(), LanguageId.GO));
        for (var ref : refList) {
            writer.write(embedInSpan(getLinkTo((LiqidEntity) ref), LEFT_MARGIN_EM, null, null) + "<br>\n");
        }
        writer.write(embedInSpan("<a href=\"index.html\">Index</a>",
                                 LEFT_MARGIN_EM, null, null) + "<br>\n");
    }

    //  ------------------------------------------------------------------------
    //  Structs
    //  ------------------------------------------------------------------------

    private void writeStructs() throws IOException {
        for (var struct : Catalog.getStructsFor(LanguageId.GO)) {
            writeStructDocs(struct);
        }
    }

    private void writeStructDocs(Struct struct) throws IOException {
        var fileName = getStructFileName(struct);
        var fullName = GO_DOCS_BASE_DIRECTORY + fileName;
        System.out.println("Writing " + fullName + "...");
        var w = new BufferedWriter(new FileWriter(fullName));

        writeFileHeader(w);
        w.write("<h2>Class " + convertStructName(struct) + "</h2>\n");

        w.write(TABLE_TAG_NB + "\n");
        if (struct.hasBaseStruct()) {
            w.write(TABLE_ROW_TAG
                        + TABLE_CELL_TAG_NB + "<b>Base type:</b></td>"
                        + TABLE_CELL_TAG_NB + getLinkTo(struct.getBaseStruct()) + "</td></tr>");
        }
        w.write("</table>\n");

        writeTopLevelDescription(w, struct.getDescription());

        writeStructMembers(w, struct);
        writeStructMethods(w, struct);
        writeStructReferences(w, struct);
        writeFileFooter(w);
        w.close();
    }

    private void writeStructMembers(BufferedWriter writer, Struct struct) throws IOException {
        var members = struct.getDataMembersFor(LanguageId.GO);
        if (members.size() > 0) {
            writer.write("<h3>Members:</h3>\n");
            writer.write(embedInSpan("All members are private, and should be accessed via the indicated get and set methods.<br>\n",
                                     LEFT_MARGIN_EM, null, null));
            writer.write("<br>" + TABLE_TAG + "\n");
            writer.write(TABLE_ROW_TAG);
            writer.write(TABLE_HEADER_TAG + "Name</th>");
            writer.write(TABLE_HEADER_TAG + "Type</th>");
            writer.write(TABLE_HEADER_TAG + "<center>Optional</center></th>");
            writer.write(TABLE_HEADER_TAG + "Default Value</th>");
            writer.write(TABLE_HEADER_TAG + "Description</th>");
            writer.write("<tr>\n");
            for (var m : members) {
                if (m.matches(LanguageId.GO)) {
                    writer.write(TABLE_CELL_TAG + convertStructDataMemberName(m) + "</td>");
                    writer.write(TABLE_CELL_TAG + getDataDescriptorString(m.getDataDescriptor()) + "</td>");

                    writer.write(TABLE_CELL_TAG
                                     + "<center>"
                                     + (m.isOptional() ? "yes" : "no")
                                     + "</center"
                                     + "</td>");

                    writer.write(TABLE_CELL_TAG + (m.hasDefaultValue() ? m.getDefaultValue().toString() : "") + "</td>");

                    writer.write(translate(m.getDescription())
                                     .stream()
                                     .collect(Collectors.joining("<br>", TABLE_CELL_TAG, "</td>")));

                    writer.write(TABLE_ROW_TAG);
                    writer.write("<tr>\n");
                }
            }
            writer.write("</table>\n");
        }
    }

    private void writeStructMethods(BufferedWriter writer, Struct struct) throws IOException {
        var methStr = "<h3>Methods:</h3>\n";
        var methStrSent = false;

        var thisStruct = struct;
        while (thisStruct != null) {
            //  Do methods local to this struct
            var methods = thisStruct.getFunctionMembersFor(LanguageId.GO);
            if (methods.size() > 0) {
                if (!methStrSent) {
                    writer.write(methStr);
                    methStrSent = true;
                }

                for (var m : methods) {
                    if (m.matches(LanguageId.GO)) {
                        var f = Catalog.getFunction(m.getFunctionId());
                        writeStructMethod(writer, struct, f);
                    }
                }
            }

            thisStruct = thisStruct.hasBaseStruct() ? thisStruct.getBaseStruct() : null;
        }
    }

    private void writeStructMethod(BufferedWriter writer,
                                   Struct containingStruct,
                                   Function function) throws IOException {
        var exceptionLink = getLinkTo(Catalog.getStruct(StructId.LIQID_EXCEPTION));

        //  header
        writer.write(String.format("<h4 id=%s style=\"margin-left:%dem;\">%s</h4>\n",
                                   function.getBaseName(),
                                   LEFT_MARGIN_EM,
                                   embedInSpan(function.getBaseName(), null, null, FUNCTION_NAME_COLOR)));

        //  div
        writer.write(String.format("<div style=\"margin-left:%dem;\">", LEFT_MARGIN_EM + 2));

        //  description
        writeInternalDescription(writer, function.getDescription());

        //  category
        writer.write("<br><b>Category:</b> "
                         + getCategoryLink(Catalog.getCategoryFor(function.getFunctionId()))
                         + "<br>\n");

        //  deprecated
        if (function.isDeprecated()) {
            writer.write("<br><b>Deprecated:</b><br>\n");
            for (var s : translate(function.getDeprecatedMessage())) {
                writer.write(s + "<br>\n");
            }
        }

        //  signature
        writer.write("<br><b>Signature:</b><br>\n");

        var sb = new StringBuilder();
        sb.append("(s *")
          .append(convertStructName(containingStruct.getBaseName()))
          .append(") ")
          .append(convertFunctionName(function))
          .append("(");

        var pre = "";
        for (var param : function.getParameters()) {
            sb.append(pre)
              .append(convertParameterName(param.getBaseName()))
              .append(" ")
              .append(getDataDescriptorString(param.getDataDescriptor()));
            pre = ", ";
        }

        sb.append(") ");

        if (function.hasResult()) {
            var resDesc = function.getResultDataDescriptor();
            sb.append("(");
//            if (resDesc.isArray()) {
//                sb.append("[]");
//            } else if (GoCommon.weShouldReturnAPointerFor(resDesc)) {
//                sb.append("*");
//            }
            sb.append(getDataDescriptorString(resDesc))
              .append(", error)");
        } else {
            sb.append("error");
        }
        sb.append("<br>");

        writer.write(embedInSpan(sb.toString(), null, "monospace", null));

        //  parameters
        var parameters = function.getParameters();
        if (parameters.size() > 0) {
            writer.write("<br><b>Parameters:</b><br>\n");

            writer.write(TABLE_TAG_NM
                             + TABLE_ROW_TAG
                             + TABLE_HEADER_TAG + "Type</th>"
                             + TABLE_HEADER_TAG + "Name</th>"
                             + TABLE_HEADER_TAG + "Optional*</th>"
                             + TABLE_HEADER_TAG + "Description</th></tr>\n");

            for (var p : parameters) {
                sb.setLength(0);
                sb.append(TABLE_ROW_TAG)
                  .append(TABLE_CELL_TAG).append(getDataDescriptorString(p.getDataDescriptor())).append("</td>")
                  .append(TABLE_CELL_TAG).append(convertParameterName(p.getBaseName())).append("</td>")
                  .append(TABLE_CELL_TAG).append("<center>").append(p.isOptional() ? "yes" : "no").append("</center></td>");
                sb.append(TABLE_CELL_TAG);
                for (var s : translate(p.getDescription())) {
                    sb.append(s).append("<br>");
                }
                sb.append("</td>\n");
                writer.write(sb.toString());
            }

            writer.write("</table>\n");
            writer.write("* optional values may be nil if the value is not to be specified.<br>\n");
        }

        //  return value
        if (function.hasResult()) {
            writer.write("<br><b>Returns:</b><br>\n");
            for (var s : translate(function.getResultDescription())) {
                writer.write(s + "<br>\n");
            }
        }

        writer.write("</div>");
    }

    private void writeStructReferences(BufferedWriter writer, Struct struct) throws IOException {
        writer.write(REFERENCES_HEADING);
        var refList = LiqidEntity.sort(Catalog.getReferrers(struct.getStructId(), LanguageId.GO));
        for (var r : refList) {
            writer.write(embedInSpan(getLinkTo((LiqidEntity) r), LEFT_MARGIN_EM, null, null) + "<br>\n");
        }
        writer.write(embedInSpan("<a href=\"index.html\">Index</a>",
                                 LEFT_MARGIN_EM, null, null) + "<br>\n");
    }

    //  ------------------------------------------------------------------------
    //  Typedefs
    //  ------------------------------------------------------------------------

    private void writeTypedefs() throws IOException {
        for (var typedef : Catalog.getTypedefsFor(LanguageId.GO)) {
            writeTypedefDocs(typedef);
        }
    }

    private void writeTypedefDocs(Typedef typedef) throws IOException {
        var fileName = getTypedefFileName(typedef);
        var fullName = GO_DOCS_BASE_DIRECTORY + fileName;
        System.out.println("Writing " + fullName + "...");
        var w = new BufferedWriter(new FileWriter(fullName));

        writeFileHeader(w);
        w.write("<h2>Class " + convertTypedefName(typedef) + "</h2>\n");

        w.write(TABLE_TAG_NB + "\n");
        w.write(TABLE_ROW_TAG
                    + TABLE_CELL_TAG_NB + "<b>Base type:</b></td>"
                    + TABLE_CELL_TAG_NB + getDataDescriptorString(typedef.getSourceDataType()) + "</td></tr>");
        w.write("</table>\n");

        writeTopLevelDescription(w, typedef.getDescription());

        writeTypedefReferences(w, typedef);
        writeFileFooter(w);
        w.close();
    }

    private void writeTypedefReferences(BufferedWriter writer, Typedef typedef) throws IOException {
        writer.write(REFERENCES_HEADING);
        var refList = LiqidEntity.sort(Catalog.getReferrers(typedef.getTypedefId(), LanguageId.GO));
        for (var r : refList) {
            writer.write(embedInSpan(getLinkTo((LiqidEntity) r), LEFT_MARGIN_EM, null, null) + "<br>\n");
        }
        writer.write(embedInSpan("<a href=\"index.html\">Index</a>",
                                 LEFT_MARGIN_EM, null, null) + "<br>\n");
    }

    //  ------------------------------------------------------------------------
    //  Helper functions
    //  ------------------------------------------------------------------------

    private String getDataDescriptorString(DataDescriptor descriptor) {
        switch (descriptor.getDataTypeId()) {
            case LANGUAGE_INTRINSIC, INTRINSIC, OCTET_STREAM, VOID:
                return GoCommon.getTypeString(descriptor);

            case ENUMERATOR:
                var enumerator = Catalog.getEnumerator(((EnumeratorDataDescriptor)descriptor).getEnumeratorId());
                var enumStr = getLinkTo(enumerator);
                if (descriptor.isArray()) {
                    enumStr = "[]" + enumStr;
                }
                return enumStr;

            case STRUCT:
                var struct = Catalog.getStruct(((StructDataDescriptor)descriptor).getStructId());
                var structStr = getLinkTo(struct);
                if (descriptor.isArray()) {
                    structStr = "[]" + structStr;
                }
                return structStr;

            case TYPEDEF:
                var typedef = Catalog.getTypedef(((TypedefDataDescriptor)descriptor).getTypedefId());
                var typedefStr = getLinkTo(typedef);
                if (descriptor.isArray()) {
                    typedefStr = "[]" + typedefStr;
                }
                return typedefStr;

            default:
                throw new RuntimeException("Should not be here");
        }
    }

    private String getLinkTo(LiqidEntity entity) {
        if (entity instanceof Category c) {
            return getCategoryLink(c);
        } else if (entity instanceof Enumerator e) {
            return getEnumeratorLink(e);
        } else if (entity instanceof Function f) {
            return getFunctionLink(f);
        } else if (entity instanceof Struct s) {
            return getStructLink(s);
        } else if (entity instanceof Typedef t) {
            return getTypedefLink(t);
        }

        throw new RuntimeException("Should not be here");
    }

    private void writeFileHeader(BufferedWriter writer) throws IOException {
        writeFileHeader(writer, "Go");
    }

    private void writeInternalDescription(BufferedWriter writer, Description description) throws IOException {
        writer.write("<b>Description:</b><br>\n");
        for (var s : translate(description)) {
            writer.write(s + "<br>\n");
        }
    }

    private void writeTopLevelDescription(BufferedWriter writer, Description description) throws IOException {
        writer.write(DESCRIPTION_HEADING);
        for (var s : translate(description)) {
            writer.write(embedInSpan(s, LEFT_MARGIN_EM, null, null) + "<br>\n");
        }
    }
}
