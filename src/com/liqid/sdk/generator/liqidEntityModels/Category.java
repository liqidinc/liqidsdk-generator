//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.liqidEntityModels;

import com.liqid.sdk.generator.catalog.Catalog;
import com.liqid.sdk.generator.liqidEntityModels.functions.HardCodedFunction;

import java.util.*;

/**
 * Models a Category - such entities are not directly indicated by any Liqid architecture;
 * However, they do derive from the REST API swagger documentation, which is itself (possibly)
 * directed by the REST API architecture.
 */
public final class Category extends LiqidEntity {

    private final CategoryId _categoryId;
    private final Collection<FunctionId> _functionIds = new LinkedList<>();

    private Category(CategoryId categoryId,
                     Description description,
                     Collection<FunctionId> functionIds) {
        super(categoryId.getValue(), description);
        _categoryId = categoryId;
        _functionIds.addAll(functionIds);
    }

    public CategoryId getCategoryId() { return _categoryId; }
    public Collection<FunctionId> getFunctionIds() {
        return _functionIds;
    }

    public boolean hasGeneratedCode() {
        return _functionIds.stream().anyMatch(fid -> !(Catalog.getFunction(fid) instanceof HardCodedFunction));
    }

    public static class Builder {

        private static final String ExceptionFmt = "%s() was not invoked in Builder for class Category";

        private CategoryId _categoryId = null;
        private final List<String> _description = new LinkedList<>();
        private final List<FunctionId> _functionIds = new LinkedList<>();

        public Builder addDescription(String value) { _description.add(value); return this; }
        public Builder addFunction(FunctionId value) { _functionIds.add(value); return this; }
        public Builder setCategoryId(CategoryId value) { _categoryId = value; return this; }

        public Category build() {
            if (_categoryId == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setCategoryId"));
            }

            if (_description.isEmpty()) {
                throw new RuntimeException(String.format(ExceptionFmt, "addDescription"));
            }

            return new Category(_categoryId, new Description(_description), _functionIds);
        }
    }
}
