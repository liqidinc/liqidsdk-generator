//
// Copyright (c) 2022,2023 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.writers;

import com.liqid.sdk.generator.liqidEntityModels.*;
import com.liqid.sdk.generator.liqidEntityModels.functions.Function;
import com.liqid.sdk.generator.liqidEntityModels.parameters.Parameter;
import com.liqid.sdk.generator.liqidEntityModels.structs.Struct;
import com.liqid.sdk.generator.liqidEntityModels.structs.StructDataMember;
import com.liqid.sdk.generator.liqidEntityModels.types.EnumComponent;
import com.liqid.sdk.generator.liqidEntityModels.types.Enumerator;
import com.liqid.sdk.generator.liqidEntityModels.types.Typedef;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Writer {

    protected static final String ARTIFACTS_DIRECTORY = "artifacts";

    //  The following conversions are to be implemented by the subclass writers.
    //  They convert the basename used in the configuration structs to language-correct names.
    //  DocWriters *may* include links and color.
    public abstract String convertCategoryName(String baseName);
    public abstract String convertEnumName(String baseName);
    public abstract String convertEnumComponentName(String baseName);
    public abstract String convertEnumComponentName(String enumBaseName, String componentBaseName);
    public abstract String convertFunctionName(String baseName);
    public abstract String convertParameterName(String baseName);
    public abstract String convertStructName(String baseName);
    public abstract String convertStructDataMemberName(String baseName);
    public abstract String convertTypedefName(String baseName);

    //  And these non-abstract methods are wrappers around the abstract methods
    public final String convertCategoryName(Category category) {
        return convertCategoryName(category.getBaseName());
    }

    public final String convertEnumName(Enumerator enumerator) {
        return convertEnumName(enumerator.getBaseName());
    }

    public final String convertEnumComponentName(EnumComponent component) {
        return convertEnumComponentName(component.getBaseName());
    }

    public final String convertEnumComponentName(Enumerator enumerator, EnumComponent component) {
        return convertEnumComponentName(enumerator.getBaseName(), component.getBaseName());
    }

    public final String convertFunctionName(Function function) {
        return convertFunctionName(function.getBaseName());
    }

    public final String convertParameterName(Parameter parameter) {
        return convertParameterName(parameter.getBaseName());
    }

    public final String convertStructName(Struct struct) {
        return convertStructName(struct.getBaseName());
    }

    public final String convertStructDataMemberName(StructDataMember member) {
        return convertStructDataMemberName(member.getBaseName());
    }

    public final String convertTypedefName(Typedef typedef) {
        return convertTypedefName(typedef.getBaseName());
    }

    public abstract LanguageId getLanguageId();

    /**
     * Ensures the first character of a string is lower-cased.
     */
    public static String initialCharacterToUpperCase(String input) {
        return Character.toUpperCase(input.charAt(0)) + input.substring(1);
    }

    /**
     * Sets the initial token of a string to all lower-case.
     */
    public static String initialTokenToLowerCase(String input) {
        if (input.isEmpty()) {
            return input;
        }

        var tokens = splitTextByToken(input);
        tokens[0] = tokens[0].toLowerCase();
        return String.join("", tokens);
    }

    /**
     * Splits apart a compound string into tokens - roughly equivalent to words.
     * Individual tokens are signified in the input text by upper-cased letters.
     * Thus,
     *      ThisIsAString
     * would contain the tokens
     *      This, Is, A, and String
     * There is one challenge for us: the input string may contain acronyms, signified by
     * being composed entirely of consecutive upper-cased characters.
     * Hence,
     *      ThisIsAnIPMIInterface
     * should be interpreted as
     *      This, Is, An, IPMI, Interface
     * Note the challenge we have with the first letter of "Interface" appearing to be part of
     * the "IPMI" acronym.
     */
    protected static String[] splitTextByToken(String input) {
        List<String> result = new LinkedList<>();
        if (!input.isEmpty()) {
            var pending = new StringBuilder();
            var ch = input.charAt(0);
            pending.append(ch);

            var ix = 1;
            while (ix < input.length()) {
                var prev = ch;
                ch = input.charAt(ix);

                //  If we have found an upper-cased character, we need to perform some shenanigans
                var newToken = false;
                if (Character.isUpperCase(ch)) {
                    //  If the previous character was not upper-cased, then this is the start of a new token.
                    //  Note that checking for lower-case is wrong, as digits and such should also meet this test.
                    if (!Character.isUpperCase(prev)) {
                        newToken = true;
                    } else {
                        //  If there is a next char, and if the next char is not upper-cased,
                        //  then again this is the start of a new token.
                        var ixNext = ix + 1;
                        if (ixNext < input.length()) {
                            var chNext = input.charAt(ixNext);
                            if (!Character.isUpperCase(chNext)) {
                                newToken = true;
                            }
                        }
                    }

                    if (newToken) {
                        result.add(pending.toString());
                        pending.setLength(0);
                    }
                }
                pending.append(ch);
                ix++;
            }

            //  There will almost certainly be a pending token
            if (!pending.isEmpty()) {
                result.add(pending.toString());
            }
        }

        return result.toArray(new String[]{});
    }

    /**
     * Breaks up the input string into tokens of either general text or potential reference.
     * e.g., if the input text looks like this:
     *      "This is some {struct random} string of {enum goofy} text with } and { characters"
     * would produce the following tokens:
     *      "This is some "
     *      "{struct random}"
     *      " string of "
     *      "{enum goofy}"
     *      " text with } and { characters"
     * Note that the unmatched brackets do NOT signify token boundaries.
     * This is for use by the translation routines.
     */
    protected static String[] tokenizeDescription(String text) {
        var tokens = new LinkedList<String>();

        var pending = new StringBuilder();
        var inBrackets = false;
        for (var tx = 0; tx < text.length(); ++tx) {
            var ch = text.charAt(tx);
            if (inBrackets) {
                pending.append(ch);
                if (ch == '}') {
                    tokens.add(pending.toString());
                    pending.setLength(0);
                    inBrackets = false;
                }
            } else {
                if (ch == '{') {
                    if (pending.length() > 0) {
                        tokens.add(pending.toString());
                        pending.setLength(0);
                    }
                    inBrackets = true;
                }
                pending.append(ch);
            }
        }

        if (!pending.isEmpty()) {
            tokens.add(pending.toString());
        }

        return tokens.toArray(new String[]{});
    }

    protected final String toSnakeCase(String baseName) {
        var tokens = splitTextByToken(baseName);
        return String.join("_", tokens).toLowerCase();
    }

    /**
     * Translates the strings from a Description object according to the language writer preferences.
     * This does a smart conversion from tokens such as "{struct Foo}" into a language-appropriate name,
     * and in the case of docs, to an appropriate color and hyperlink.
     */
    protected final Collection<String> translate(Description description) {
        return Arrays.stream(description.getStrings())
                     .map(this::translate)
                     .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * This is the function which does the real work in translations - it must be implemented by a subclass.
     */
    abstract String translate(String text);

    /**
     * writes a block of code consisting of a prefix and zero or more suffixes.
     * used when you want the output to look like:
     *    prefix1prefix2suffix
     *    prefix1       suffix
     *          ...
     * and prefix2 must be written, and the suffixes must all be vertically aligned.
     */
    protected static void writeBlock(BufferedWriter writer,
                                     String prefix1,
                                     String prefix2,
                                     Collection<String> suffixes) throws IOException {
        if (suffixes.size() == 0) {
            writer.write(String.format("%s%s\n", prefix1, prefix2));
        } else {
            var iter = suffixes.iterator();
            var suffix = iter.next();
            writer.write(String.format("%s%s%s\n", prefix1, prefix2, suffix));
            if (iter.hasNext()) {
                var indent = new String(new char[prefix2.length()]).replace('\0', ' ');
                while (iter.hasNext()) {
                    suffix = iter.next();
                    writer.write(String.format("%s%s%s\n", prefix1, indent, suffix));
                }
            }
        }
    }

    /**
     * As above, with prefix1 set to the empty string
     */
    protected static void writeBlock(BufferedWriter writer,
                                     String firstLinePrefix,
                                     Collection<String> suffixes) throws IOException {
        writeBlock(writer, "", firstLinePrefix, suffixes);
    }
}
