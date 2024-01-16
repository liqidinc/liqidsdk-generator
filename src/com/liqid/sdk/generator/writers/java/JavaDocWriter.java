//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.writers.java;

import com.liqid.sdk.generator.catalog.Catalog;
import com.liqid.sdk.generator.discreteDataModels.*;
import com.liqid.sdk.generator.liqidEntityModels.*;
import com.liqid.sdk.generator.liqidEntityModels.functions.Function;
import com.liqid.sdk.generator.liqidEntityModels.structs.Struct;
import com.liqid.sdk.generator.liqidEntityModels.structs.StructDataMember;
import com.liqid.sdk.generator.liqidEntityModels.types.Enumerator;
import com.liqid.sdk.generator.liqidEntityModels.types.Typedef;
import com.liqid.sdk.generator.writers.DocWriter;
import com.liqid.sdk.generator.writers.LanguageId;
import com.liqid.sdk.generator.writers.Writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

public class JavaDocWriter extends DocWriter {

    private static final String JAVA_DOCS_BASE_DIRECTORY = ARTIFACTS_DIRECTORY + "/java/docs/";

    private static final String CATEGORY_COLOR = "fuchsia";
    private static final String CLASS_NAME_COLOR = "orangered";
    private static final String ENUM_NAME_COLOR = "brown";
    private static final String ENUM_COMPONENT_NAME_COLOR = "purple";
    private static final String FUNCTION_NAME_COLOR = "green";
    private static final String MEMBER_NAME_COLOR = "blue";
    private static final String PARAMETER_NAME_COLOR = "olive";
    private static final String TYPEDEF_NAME_COLOR = "orangered";

    private static final String DESCRIPTION_HEADING = "<h3>Description:</h3>\n";
    private static final String REFERENCES_HEADING = "<h3>See Also:</h3>\n";

    @Override
    public LanguageId getLanguageId() {
        return LanguageId.JAVA;
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
        return embedInSpan(JavaCommon.toCategoryName(baseName), null, null, CATEGORY_COLOR);
    }

    @Override
    public String convertEnumName(String baseName) {
        return embedInSpan(JavaCommon.toClassName(baseName),null, null, ENUM_NAME_COLOR);
    }

    @Override
    public String convertEnumComponentName(String baseName) {
        return embedInSpan(JavaCommon.toClassName(baseName),null, null, ENUM_COMPONENT_NAME_COLOR);
    }

    @Override
    public String convertEnumComponentName(String enumBaseName, String componentBaseName) {
        var composite = convertEnumName(enumBaseName) + "." + convertEnumComponentName(componentBaseName);
        return embedInSpan(composite, null, "monospace", null);
    }

    @Override
    public String convertFunctionName(String baseName) {
        return embedInSpan(initialTokenToLowerCase(baseName), null, null, FUNCTION_NAME_COLOR);
    }

    @Override
    public String convertParameterName(String baseName) {
        return embedInSpan(initialTokenToLowerCase(baseName), null, null, PARAMETER_NAME_COLOR);
    }

    @Override
    public String convertStructName(String baseName) {
        return embedInSpan(JavaCommon.toClassName(baseName), null, null, CLASS_NAME_COLOR);
    }

    @Override
    public String convertStructDataMemberName(String baseName) {
        return embedInSpan("_" + Writer.initialTokenToLowerCase(baseName), null, null, MEMBER_NAME_COLOR);
    }

    @Override
    public String convertTypedefName(String baseName) {
        return embedInSpan(JavaCommon.toClassName(baseName), null, null, TYPEDEF_NAME_COLOR);
    }

    @Override
    public String getCategoryFileName(Category category) {
        return "cat_" + JavaCommon.toClassName(category.getBaseName()) + ".html";
    }

    @Override
    public String getEnumeratorFileName(Enumerator enumerator) {
        return "enum_" + JavaCommon.toClassName(enumerator.getBaseName()) + ".html";
    }

    @Override
    public String getStructFileName(Struct struct) {
        return "class_" + JavaCommon.toClassName(struct.getBaseName()) + ".html";
    }

    @Override
    public String getTypedefFileName(Typedef typedef) {
        return "class_" + JavaCommon.toClassName(typedef.getBaseName()) + ".html";
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
        var fullName = JAVA_DOCS_BASE_DIRECTORY + fileName;
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

        for (var e : Enumerator.sort(Catalog.getEnumeratorsFor(LanguageId.JAVA))) {
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

        for (var s : Struct.sort(Catalog.getStructsFor(LanguageId.JAVA))) {
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

        for (var t : Typedef.sort(Catalog.getTypedefsFor(LanguageId.JAVA))) {
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
        var fullName = JAVA_DOCS_BASE_DIRECTORY + fileName;
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
            if (func.matches(LanguageId.JAVA)) {
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
        }
        writer.write("</table>\n");
    }

    private void writeCategoryReferences(BufferedWriter writer, Category category) throws IOException {
        writer.write(REFERENCES_HEADING);
        var refList = LiqidEntity.sort(Catalog.getReferrers(category.getCategoryId(), LanguageId.JAVA));
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
        for (var enumerator : Catalog.getEnumeratorsFor(LanguageId.JAVA)) {
            writeEnumeratorDocs(enumerator);
        }
    }

    private void writeEnumeratorDocs(Enumerator enumerator) throws IOException {
        var fileName = getEnumeratorFileName(enumerator);
        var fullName = JAVA_DOCS_BASE_DIRECTORY + fileName;
        System.out.println("Writing " + fullName + "...");
        var w = new BufferedWriter(new FileWriter(fullName));

        writeFileHeader(w);
        w.write("<h2>Enumerator " + convertEnumName(enumerator) + "</h2>\n");

        w.write(TABLE_TAG_NB + "\n");
        w.write(TABLE_ROW_TAG
                    + TABLE_CELL_TAG_NB + "<b>Package:</b></td>"
                    + TABLE_CELL_TAG_NB + JavaCommon.JAVA_PACKAGE_NAME + "</td></tr>\n");
        w.write("</table>\n");

        writeTopLevelDescription(w, enumerator.getDescription());

        writeEnumeratorContent(w, enumerator);
        writeEnumeratorReferences(w, enumerator);
        writeFileFooter(w);
        w.close();
    }

    private void writeEnumeratorContent(BufferedWriter writer, Enumerator enumerator) throws IOException {
        writer.write("<h3>Values:</h3>\n");
        writer.write(TABLE_TAG + "\n");
        for (var comp : enumerator.getComponents()) {
            writer.write(TABLE_ROW_TAG + TABLE_CELL_TAG + convertEnumComponentName(comp) + "</td>\n");
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
        var refList = LiqidEntity.sort(Catalog.getReferrers(enumerator.getEnumeratorId(), LanguageId.JAVA));
        for (var r : refList) {
            writer.write(embedInSpan(getLinkTo((LiqidEntity) r), LEFT_MARGIN_EM, null, null) + "<br>\n");
        }
        writer.write(embedInSpan("<a href=\"index.html\">Index</a>",
                                 LEFT_MARGIN_EM, null, null) + "<br>\n");
    }

    //  ------------------------------------------------------------------------
    //  Structs
    //  ------------------------------------------------------------------------

    private void writeStructs() throws IOException {
        for (var struct : Catalog.getStructsFor(LanguageId.JAVA)) {
            writeStructDocs(struct);
        }
    }

    private void writeStructDocs(Struct struct) throws IOException {
        var fileName = getStructFileName(struct);
        var fullName = JAVA_DOCS_BASE_DIRECTORY + fileName;
        System.out.println("Writing " + fullName + "...");
        var w = new BufferedWriter(new FileWriter(fullName));

        writeFileHeader(w);
        w.write("<h2>Class " + convertStructName(struct) + "</h2>\n");

        w.write(TABLE_TAG_NB + "\n");
        if (struct.hasBaseStruct()) {
            w.write(TABLE_ROW_TAG
                        + TABLE_CELL_TAG_NB + "<b>Extends:</b></td>"
                        + TABLE_CELL_TAG_NB + getLinkTo(struct.getBaseStruct()) + "</td></tr>");
        }

        w.write(TABLE_ROW_TAG
                    + TABLE_CELL_TAG_NB + "<b>Package:</b></td>"
                    + TABLE_CELL_TAG_NB + JavaCommon.JAVA_PACKAGE_NAME + "</td></tr>\n");
        w.write("</table>\n");

        writeTopLevelDescription(w, struct.getDescription());

        writeStructMembers(w, struct);
        writeStructConstructors(w, struct);
        if (struct.isBuildable()) {
            writeStructBuilder(w, struct);
        }
        writeStructMethods(w, struct);
        writeStructReferences(w, struct);
        writeFileFooter(w);
        w.close();
    }


    private void writeStructBuilder(BufferedWriter writer, Struct struct) throws IOException {
        var className = JavaCommon.toClassName(struct.getBaseName());
        var colorClassName = embedInSpan(className, null, null, CLASS_NAME_COLOR);
        var classStr = embedInSpan(colorClassName, null, "monospace", null);
        var colorBuilderName = embedInSpan(className + ".Builder", null, null, CLASS_NAME_COLOR);
        var builderStr = embedInSpan(colorBuilderName + "()", null, "monospace", null);

        writer.write("<h3>Builder:</h3>\n");
        writer.write(String.format("<div style=\"margin-left:%dem;\">\n", LEFT_MARGIN_EM));
        writer.write("<b>Class " + colorBuilderName + ":</b><br>\n");
        writer.write("This is a static class inside the containing class which provides a fluent interface for creating an instance of the class.<br>\n");
        writer.write("The usage of each of the methods in this class follows standard conventions.<br>\n");

        writer.write("<br>" + TABLE_TAG_NM + "\n");
        writer.write(TABLE_ROW_TAG
                         + TABLE_HEADER_TAG + "Method</th>"
                         + TABLE_HEADER_TAG + "Description</th></tr>\n");
        writer.write(TABLE_ROW_TAG
                         + TABLE_CELL_TAG + builderStr + "</td>"
                         + TABLE_CELL_TAG + "Builder constructor which creates a builder object for the " + colorClassName + " class"
                         + "</td></tr>\n");

        for (var m : struct.getBaseDataMembersFor(LanguageId.JAVA)) {
            writeStructBuilderMember(writer, m, true);
        }

        for (var m : struct.getDataMembersFor(LanguageId.JAVA)) {
            writeStructBuilderMember(writer, m, false);
        }

        var colorBuild = embedInSpan("build", null, null, FUNCTION_NAME_COLOR);
        var buildStr = embedInSpan(colorClassName + " " + colorBuild + "()",
                                   null, "monospace", null);
        writer.write(TABLE_ROW_TAG
                         + TABLE_CELL_TAG + buildStr + "</td>"
                         + TABLE_CELL_TAG + "Creates an instance of the " + colorClassName + " class using the specified attribute values"
                         + "</td></tr>\n");

        writer.write("</table>\n");
        writer.write("</div>\n");
    }

    private void writeStructBuilderMember(BufferedWriter writer,
                                          StructDataMember member,
                                          boolean isBaseMember) throws IOException {
        var setter = embedInSpan("Builder", null, null, CLASS_NAME_COLOR)
            + " "
            + embedInSpan(JavaCommon.toSetterName(member.getBaseName()), null, null, FUNCTION_NAME_COLOR)
            + "("
            + getDataDescriptorString(member.getDataDescriptor())
            + " "
            + embedInSpan("value", null, null, PARAMETER_NAME_COLOR)
            + ")";

        writer.write(TABLE_ROW_TAG
                         + TABLE_CELL_TAG
                         + embedInSpan(setter, null, "monospace", null)
                         + "</td>"
                         + TABLE_CELL_TAG
                         + (member.isOptional() ? "(Optional)<br>" : "")
                         + "Specifies the value for the "
                         + (isBaseMember ? "ancestor class's " : "")
                         + convertStructDataMemberName(member.getBaseName())
                         + " attribute.</td></tr>");
    }

    private void writeStructConstructors(BufferedWriter writer, Struct struct) throws IOException {
        writer.write("<h3>Constructors:</h3>\n");
        if (struct.isBuildable()) {
            writer.write(embedInSpan("All constructors are private, and should be accessed via the indicated Builder class.",
                                     LEFT_MARGIN_EM, null, null)
                             + "<br><br>\n");
        }

        writer.write(TABLE_TAG + "\n");
        if (struct.isSerializable()) {
            writer.write(TABLE_ROW_TAG + TABLE_CELL_TAG + "Default Constructor</td>");
            writer.write(TABLE_CELL_TAG
                             + embedInSpan(JavaCommon.toClassName(struct.getBaseName()),
                                           null, "monospace", FUNCTION_NAME_COLOR)
                             + "()</td></tr>");
        }

        writer.write(TABLE_ROW_TAG + TABLE_CELL_TAG + "Parameterizing Constructor</td>");
        var sb = new StringBuilder();
        sb.append(TABLE_CELL_TAG)
          .append(getSpanTag(null, "monospace", null))
          .append(embedInSpan(JavaCommon.toClassName(struct.getBaseName()),
                              null, null, FUNCTION_NAME_COLOR))
          .append("(");

        var first = true;
        for (var m : struct.getAggregateDataMembersFor(LanguageId.JAVA)) {
            if (!first) {
                sb.append(", ");
            }
            sb.append(getDataDescriptorString(m.getDataDescriptor()))
                .append(" ")
                .append(convertParameterName(m.getBaseName()));
            first = false;
        }
        sb.append(")</span></td></tr>\n");
        writer.write(sb.toString());

        writer.write("</table>\n");
    }

    private void writeStructMembers(BufferedWriter writer, Struct struct) throws IOException {
        var members = struct.getDataMembersFor(LanguageId.JAVA);
        if (members.size() > 0) {
            writer.write("<h3>Members:</h3>\n");
            writer.write(embedInSpan("All members are private, and should be accessed via the indicated get and set methods.<br>\n",
                                     LEFT_MARGIN_EM, null, null));
            writer.write("<br>" + TABLE_TAG + "\n");
            writer.write(TABLE_ROW_TAG);
            writer.write(TABLE_HEADER_TAG + "Name</th>");
            writer.write(TABLE_HEADER_TAG + "Type</th>");
            writer.write(TABLE_HEADER_TAG + "Accessors</th>");
            writer.write(TABLE_HEADER_TAG + "<center>Optional</center></th>");
            writer.write(TABLE_HEADER_TAG + "Default Value</th>");
            writer.write(TABLE_HEADER_TAG + "Description</th>");
            writer.write("<tr>\n");
            for (var m : members) {
                if (m.matches(LanguageId.JAVA)) {
                    writer.write(TABLE_CELL_TAG + convertStructDataMemberName(m) + "</td>");
                    writer.write(TABLE_CELL_TAG + getDataDescriptorString(m.getDataDescriptor()) + "</td>");

                    var getter = getDataDescriptorString(m.getDataDescriptor())
                        + " "
                        + embedInSpan(JavaCommon.toGetterName(m.getBaseName()), null, null, FUNCTION_NAME_COLOR)
                        + "()";
                    var setter = "void "
                        + embedInSpan(JavaCommon.toSetterName(m.getBaseName()), null, null, FUNCTION_NAME_COLOR)
                        + "("
                        + getDataDescriptorString(m.getDataDescriptor())
                        + " "
                        + convertParameterName("value")
                        + ")";
                    writer.write(TABLE_CELL_TAG
                                     +
                                     embedInSpan(getter, null, "monospace", null)
                                     + "<BR>"
                                     + embedInSpan(setter, null, "monospace", null) + "</td>");

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
        var isBase = false;
        while (thisStruct != null) {
            //  Do methods local to this struct
            var methods = thisStruct.getFunctionMembersFor(LanguageId.JAVA);
            if (methods.size() > 0) {
                if (!methStrSent) {
                    writer.write(methStr);
                    methStrSent = true;
                }

                for (var m : methods) {
                    var f = Catalog.getFunction(m.getFunctionId());
                    writeStructMethod(writer, f, isBase ? thisStruct : null);
                }
            }

            isBase = true;
            thisStruct = thisStruct.hasBaseStruct() ? thisStruct.getBaseStruct() : null;
        }
    }

    private void writeStructMethod(BufferedWriter writer, Function function, Struct baseStruct) throws IOException {
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
        if (baseStruct != null) {
            writer.write("Inherited from " + getLinkTo(baseStruct) + "<br>\n");
        }

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
        sb.append(getDataDescriptorString(function.getResultDataDescriptor()))
          .append(" ")
          .append(convertFunctionName(function))
          .append("(");
        var pre = "";
        for (var param : function.getParameters()) {
            sb.append(pre)
              .append(getDataDescriptorString(param.getDataDescriptor()))
              .append(" ")
              .append(convertParameterName(param.getBaseName()));
            pre = ", ";
        }
        sb.append(")").append(" throws ").append(exceptionLink).append("<br>\n");

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
            writer.write("* optional values may be null if the value is not to be specified.<br>\n");
        }

        //  return value
        if (function.hasResult()) {
            writer.write("<br><b>Returns:</b><br>\n");
            for (var s : translate(function.getResultDescription())) {
                writer.write(s + "<br>\n");
            }
        }

        //  throws
        writer.write("<br><b>Throws:</b> " + exceptionLink + "<br>");

        writer.write("</div>");
    }

    private void writeStructReferences(BufferedWriter writer, Struct struct) throws IOException {
        writer.write(REFERENCES_HEADING);
        var refList = LiqidEntity.sort(Catalog.getReferrers(struct.getStructId(), LanguageId.JAVA));
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
        for (var typedef : Catalog.getTypedefsFor(LanguageId.JAVA)) {
            writeTypedefDocs(typedef);
        }
    }

    private void writeTypedefDocs(Typedef typedef) throws IOException {
        var fileName = getTypedefFileName(typedef);
        var fullName = JAVA_DOCS_BASE_DIRECTORY + fileName;
        System.out.println("Writing " + fullName + "...");
        var w = new BufferedWriter(new FileWriter(fullName));

        writeFileHeader(w);
        w.write("<h2>Class " + convertTypedefName(typedef) + "</h2>\n");

        w.write(TABLE_TAG_NB + "\n");
        w.write(TABLE_ROW_TAG
                    + TABLE_CELL_TAG_NB + "<b>Extends:</b></td>"
                    + TABLE_CELL_TAG_NB + getDataDescriptorString(typedef.getSourceDataType()) + "</td></tr>");
        w.write(TABLE_ROW_TAG
                    + TABLE_CELL_TAG_NB + "<b>Package:</b></td>"
                    + TABLE_CELL_TAG_NB + JavaCommon.JAVA_PACKAGE_NAME + "</td></tr>\n");
        w.write("</table>\n");

        writeTopLevelDescription(w, typedef.getDescription());

        writeTypedefReferences(w, typedef);
        writeFileFooter(w);
        w.close();
    }

    private void writeTypedefReferences(BufferedWriter writer, Typedef typedef) throws IOException {
        writer.write(REFERENCES_HEADING);
        var refList = LiqidEntity.sort(Catalog.getReferrers(typedef.getTypedefId(), LanguageId.JAVA));
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
                return JavaCommon.getTypeString(descriptor);

            case ENUMERATOR:
                var enumerator = Catalog.getEnumerator(((EnumeratorDataDescriptor)descriptor).getEnumeratorId());
                var enumStr = getLinkTo(enumerator);
                if (descriptor.isArray()) {
                    enumStr = "LinkedList<" + enumStr + ">";
                }
                return enumStr;

            case STRUCT:
                var struct = Catalog.getStruct(((StructDataDescriptor)descriptor).getStructId());
                var structStr = getLinkTo(struct);
                if (descriptor.isArray()) {
                    structStr = "LinkedList<" + structStr + ">";
                }
                return structStr;

            case TYPEDEF:
                var typedef = Catalog.getTypedef(((TypedefDataDescriptor)descriptor).getTypedefId());
                var typedefStr = getLinkTo(typedef);
                if (descriptor.isArray()) {
                    typedefStr = "LinkedList<" + typedefStr + ">";
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
        writeFileHeader(writer, "Java&trade;");
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
