//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.catalog;

import com.liqid.sdk.generator.discreteDataModels.EnumeratorDataDescriptor;
import com.liqid.sdk.generator.discreteDataModels.StructDataDescriptor;
import com.liqid.sdk.generator.discreteDataModels.TypedefDataDescriptor;
import com.liqid.sdk.generator.liqidEntityModels.*;
import com.liqid.sdk.generator.liqidEntityModels.functions.Function;
import com.liqid.sdk.generator.liqidEntityModels.structs.Struct;
import com.liqid.sdk.generator.liqidEntityModels.structs.StructDataMember;
import com.liqid.sdk.generator.liqidEntityModels.structs.StructFunctionMember;
import com.liqid.sdk.generator.liqidEntityModels.types.Enumerator;
import com.liqid.sdk.generator.liqidEntityModels.types.Typedef;
import com.liqid.sdk.generator.writers.LanguageId;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Catalog {

    public static Category getCategory(CategoryId categoryId) {
        if (!Categories.CONTENT.containsKey(categoryId)) {
            throw new RuntimeException("Cannot find category " + categoryId);
        }
        return Categories.CONTENT.get(categoryId);
    }

    public static Category getCategory(String baseName) {
        var cat = Categories.CONTENT.values()
                                    .stream()
                                    .filter(c -> c.getBaseName().equalsIgnoreCase(baseName))
                                    .findFirst()
                                    .orElse(null);
        if (cat == null) {
            throw new RuntimeException("Cannot find category " + baseName);
        }
        return cat;
    }

    public static Category getCategoryFor(FunctionId functionId) {
        for (var cat : Categories.CONTENT.values()) {
            for (var fid : cat.getFunctionIds()) {
                if (fid == functionId) {
                    return cat;
                }
            }
        }

        throw new RuntimeException("Category not found for function " + functionId);
    }

    public static Collection<Category> getCategories() {
        return Categories.CONTENT.values();
    }

    public static Enumerator getEnumerator(EnumeratorId id) {
        if (!Enumerators.CONTENT.containsKey(id)) {
            throw new RuntimeException("Cannot find enumerator " + id);
        }
        return Enumerators.CONTENT.get(id);
    }

    public static Enumerator getEnumerator(String baseName) {
        var enumerator = Enumerators.CONTENT.values()
                                            .stream()
                                            .filter(s -> s.getBaseName().equalsIgnoreCase(baseName))
                                            .findFirst()
                                            .orElse(null);
        if (enumerator == null) {
            throw new RuntimeException("Cannot find enumerator " + baseName);
        }

        return enumerator;
    }

    public static Collection<Enumerator> getEnumerators() {
        return Enumerators.CONTENT.values();
    }

    public static Collection<Enumerator> getEnumeratorsFor(LanguageId specificLanguageId) {
        return Enumerators.CONTENT.values()
                                  .stream()
                                  .filter(e -> e.matches(specificLanguageId))
                                  .collect(Collectors.toCollection(LinkedList::new));
    }

    public static Function getFunction(FunctionId functionId) {
        if (!Functions.CONTENT.containsKey(functionId)) {
            throw new RuntimeException("Cannot find function " + functionId);
        }
        return Functions.CONTENT.get(functionId);
    }

    public static Function getFunction(String baseName) {
        var func = Functions.CONTENT.values()
                                    .stream()
                                    .filter(f -> f.getBaseName().equalsIgnoreCase(baseName))
                                    .findFirst()
                                    .orElse(null);
        if (func == null) {
            throw new RuntimeException("cannot find function " + baseName);
        }

        return func;
    }

    public static Collection<Function> getFunctions() {
        return Functions.CONTENT.values();
    }

    public static Collection<LiqidEntity> getReferrers(CategoryId categoryId, LanguageId specificLanguage) {
        //  The only entities which would refer to this category are functions,
        //  and the category already has a table of these functions internally...
        //  and if all went well in the world, these two lists of functions would be identical.
        //  But... here we are.
        var cat = Catalog.getCategory(categoryId);
        return cat.getFunctionIds()
                  .stream()
                  .map(Catalog::getFunction)
                  .filter(f -> f.matches(specificLanguage))
                  .collect(Collectors.toCollection(LinkedList::new));
    }

    public static Collection<LiqidEntity> getReferrers(EnumeratorId enumeratorId, LanguageId specificLanguage) {
        var list = new LinkedList<LiqidEntity>();

        for (var s : Structs.CONTENT.values()) {
            if (s.matches(specificLanguage)) {
                for (var m : s.getMembers()) {
                    if (m.matches(specificLanguage)
                        && m instanceof StructDataMember sdm
                        && sdm.getDataDescriptor() instanceof EnumeratorDataDescriptor edd
                        && edd.getEnumeratorId() == enumeratorId) {
                        list.add(s);
                    }
                }
            }
        }

        for (var f : Functions.CONTENT.values()) {
            if (f.matches(specificLanguage)) {
                var found = false;
                if (f.hasResult()
                    && f.getResultDataDescriptor() instanceof EnumeratorDataDescriptor edd
                    && edd.getEnumeratorId() == enumeratorId) {
                    found = true;
                } else {
                    for (var p : f.getParameters()) {
                        if (p.matches(specificLanguage)
                            && p.getDataDescriptor() instanceof EnumeratorDataDescriptor edd
                            && edd.getEnumeratorId() == enumeratorId) {
                            found = true;
                            break;
                        }
                    }
                }

                if (found) {
                    list.add(f);
                }
            }
        }

        return list;
    }

    public static Collection<LiqidEntity> getReferrers(FunctionId functionId, LanguageId specificLanguage) {
        var list = new LinkedList<LiqidEntity>();

        //  is this function in a struct? (if so, it will be in only one struct).
        var done = false;
        for (var s : Structs.CONTENT.values()) {
            if (s.matches(specificLanguage)) {
                for (var m : s.getMembers()) {
                    if (m.matches(specificLanguage)
                        && m instanceof StructFunctionMember sfm
                        && sfm.getFunctionId() == functionId) {
                        list.add(s);
                        done = true;
                        break;
                    }
                }
            }

            if (done) {
                break;
            }
        }

        //  Now add the category
        list.add(Catalog.getCategoryFor(functionId));
        return list;
    }

    public static Collection<LiqidEntity> getReferrers(StructId structId, LanguageId specificLanguage) {
        var list = new LinkedList<LiqidEntity>();

        for (var t : Typedefs.CONTENT.values()) {
            if (t.matches(specificLanguage)
                && t.getSourceDataType() instanceof StructDataDescriptor sdd
                && sdd.getStructId() == structId) {
                list.add(t);
            }
        }

        for (var f : Functions.CONTENT.values()) {
            var found = false;
            if (f.matches(specificLanguage)) {
                if (f.hasResult()
                    && f.getResultDataDescriptor() instanceof StructDataDescriptor tdd
                    && tdd.getStructId() == structId) {
                    found = true;
                } else {
                    for (var p : f.getParameters()) {
                        if (p.getDataDescriptor() instanceof StructDataDescriptor tdd
                            && tdd.getStructId() == structId) {
                            found = true;
                            break;
                        }
                    }
                }
            }

            if (found) {
                list.add(f);
            }
        }

        for (var s : Structs.CONTENT.values()) {
            if (s.matches(specificLanguage)) {
                var found = false;

                if (s.hasBaseStruct() && s.getBaseStruct().getStructId() == structId)
                    found = true;
                else {
                    for (var m : s.getMembers()) {
                        if (m instanceof StructDataMember sdm
                            && sdm.getDataDescriptor() instanceof StructDataDescriptor tdd
                            && tdd.getStructId() == structId) {
                            found = true;
                            break;
                        }
                    }
                }

                if (found) {
                    list.add(s);
                }
            }
        }

        return list;
    }

    public static Collection<LiqidEntity> getReferrers(TypedefId typedefId, LanguageId specificLanguage) {
        var list = new LinkedList<LiqidEntity>();

        for (var f : Functions.CONTENT.values()) {
            var found = false;
            if (f.matches(specificLanguage)) {
                if (f.hasResult()
                    && f.getResultDataDescriptor() instanceof TypedefDataDescriptor tdd
                    && tdd.getTypedefId() == typedefId) {
                    found = true;
                } else {
                    for (var p : f.getParameters()) {
                        if (p.getDataDescriptor() instanceof TypedefDataDescriptor tdd
                            && tdd.getTypedefId() == typedefId) {
                            found = true;
                            break;
                        }
                    }
                }
            }

            if (found) {
                list.add(f);
            }
        }

        for (var s : Structs.CONTENT.values()) {
            if (s.matches(specificLanguage)) {
                for (var m : s.getMembers()) {
                    if (m instanceof StructDataMember sdm
                        && sdm.getDataDescriptor() instanceof TypedefDataDescriptor tdd
                        && tdd.getTypedefId() == typedefId) {
                        list.add(s);
                        break;
                    }
                }
            }
        }

        return list;
    }

    public static Struct getStruct(StructId id) {
        if (!Structs.CONTENT.containsKey(id)) {
            throw new RuntimeException("Cannot find struct " + id);
        }
        return Structs.CONTENT.get(id);
    }

    public static Struct getStruct(String baseName) {
        var struct = Structs.CONTENT.values()
                                    .stream()
                                    .filter(s -> s.getBaseName().equalsIgnoreCase(baseName))
                                    .findFirst()
                                    .orElse(null);

        if (struct == null) {
            throw new RuntimeException("Cannot find struct " + baseName);
        }

        return struct;
    }

    public static Struct getStructFor(FunctionId functionId) {
        for (var s : Structs.CONTENT.values()) {
            for (var m : s.getMembers()) {
                if (m instanceof StructFunctionMember sfm && sfm.getFunctionId() == functionId) {
                    return s;
                }
            }
        }

        throw new RuntimeException("Containing struct not found for function " + functionId);
    }

    public static Collection<Struct> getStructs() {
        return Structs.CONTENT.values();
    }

    public static Collection<Struct> getStructsFor(LanguageId specificLanguageId) {
        return Structs.CONTENT.values()
                              .stream()
                              .filter(e -> e.matches(specificLanguageId))
                              .collect(Collectors.toCollection(LinkedList::new));
    }

    public static Typedef getTypedef(TypedefId id) {
        if (!Typedefs.CONTENT.containsKey(id)) {
            throw new RuntimeException("Cannot find typedef " + id);
        }
        return Typedefs.CONTENT.get(id);
    }

    public static Typedef getTypedef(String baseName) {
        var typedef = Typedefs.CONTENT.values()
                                      .stream()
                                      .filter(s -> s.getBaseName().equalsIgnoreCase(baseName))
                                      .findFirst()
                                      .orElse(null);

        if (typedef == null) {
            throw new RuntimeException("Cannot find typedef " + baseName);
        }

        return typedef;
    }

    public static Collection<Typedef> getTypedefs() {
        return Typedefs.CONTENT.values();
    }

    public static Collection<Typedef> getTypedefsFor(LanguageId specificLanguageId) {
        return Typedefs.CONTENT.values()
                               .stream()
                               .filter(e -> e.matches(specificLanguageId))
                               .collect(Collectors.toCollection(LinkedList::new));
    }
}
