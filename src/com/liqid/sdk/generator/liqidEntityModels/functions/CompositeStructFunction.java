//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.liqidEntityModels.functions;

import com.liqid.sdk.generator.catalog.Catalog;
import com.liqid.sdk.generator.discreteDataModels.DataDescriptor;
import com.liqid.sdk.generator.discreteDataModels.HttpMethod;
import com.liqid.sdk.generator.discreteDataModels.PresetId;
import com.liqid.sdk.generator.liqidEntityModels.*;
import com.liqid.sdk.generator.liqidEntityModels.parameters.Parameter;
import com.liqid.sdk.generator.liqidEntityModels.structs.Struct;
import com.liqid.sdk.generator.writers.LanguageId;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Describes a function which works with a composite struct - that is, a struct which is composed of
 * one or more sub-structs (and no other data members).
 * <p>
 * Regardless of whatever else happens, we start out by building an empty composite struct,
 * then populating it by performing a GET operation for each sub-struct using a defined getter function.
 * To do this, we'll need the identifier parameters for each of the getter functions.
 * </p>
 * The HttpMethod determines precisely what happens after this point.
 * For GET, we do nothing else, simply returning the composite struct to the client.
 * FOr DELETE, we present the composite struct as the body parameter, to the indicated partial path.
 * For POST (these are the weirdest ones) we simply POST the composite struct as a body parameter to the indicated
 *      partial path.
 * <p>
 * The identifier parameters for the sub getters are automatically detected, and are not to be specified in the
 * function specification. However, the parameter list *may* contain path parameters which would be used in
 * composing extensions to the partial path. No other parameters must be specified.
 */
public class CompositeStructFunction extends Function {

    /**
     * Links getter function IDs with the composite base name for the implied struct's
     * data member which is to be populated by the getter.
     */
    private static class GetterLinks extends LinkedHashMap<FunctionId, String>{}
    private final GetterLinks _getterLinks;

    /**
     * Indicates the struct which is to be passed to the DELETE or POST, or returned to the client.
     */
    private final StructId _compositeStructId;

    /**
     * Do not use the constructor - use the builder
     */
    private CompositeStructFunction(FunctionId functionId,
                                    String baseName,
                                    boolean isAsynchronous,
                                    Description description,
                                    Description deprecatedMessage,
                                    ImportRequirements importRequirements,
                                    LanguageId specificLanguageId,
                                    HttpMethod restMethod,
                                    String partialPath,
                                    DataDescriptor resultDataDescriptor,
                                    Description resultDescription,
                                    Collection<Parameter> parameters,
                                    Collection<PresetId> pathPresets,
                                    Map<String, PresetId> queryPresets,
                                    StructId compositeStructId,
                                    GetterLinks getterLinks) {
        super(functionId,
              baseName,
              isAsynchronous,
              description,
              deprecatedMessage,
              importRequirements,
              specificLanguageId,
              restMethod,
              partialPath,
              resultDataDescriptor,
              resultDescription,
              parameters,
              pathPresets,
              queryPresets);

        _getterLinks = getterLinks;
        _compositeStructId = compositeStructId;
    }

    public Struct getCompositeStruct() {
        return Catalog.getStruct(_compositeStructId);
    }

    public Collection<Function> getGetterFunctions() {
        return _getterLinks.keySet()
                           .stream()
                           .map(Catalog::getFunction)
                           .collect(Collectors.toCollection(LinkedList::new));
    }

    public String getStructMemberBaseNameFor(FunctionId functionId) {
        if (!_getterLinks.containsKey(functionId)) {
            throw new RuntimeException("There is not basename associated with the indicated function id");
        }
        return _getterLinks.get(functionId);
    }

    public static class Builder {

        private static final String ExceptionFmt = "%s() was not invoked in Builder for class CompositeStructFunction";

        private String _baseName = null;
        private StructId _compositeStructId = null;
        private final List<String> _description = new LinkedList<>();
        private final List<String> _deprecatedMessage = new LinkedList<>();
        private FunctionId _functionId = null;
        private final GetterLinks _getterLinks = new GetterLinks();
        private final ImportRequirements _importRequirements = new ImportRequirements();
        private boolean _isAsynchronous = false;
        private final List<Parameter> _parameters = new LinkedList<>();
        private String _partialPath = null;
        private final List<PresetId> _pathPresets = new LinkedList<>();
        private final Map<String, PresetId> _queryPresets = new LinkedHashMap<>();
        private HttpMethod _restMethod = null;
        private DataDescriptor _resultDataDescriptor = null;
        private final List<String> _resultDescription = new LinkedList<>();
        private LanguageId _specificLanguageId = null;

        public Builder addDeprecatedMessage(String value) { _deprecatedMessage.add(value); return this; }
        public Builder addDescription(String value) { _description.add(value); return this; }

        public Builder addGetterLink(FunctionId functionId, String baseName) {
            _getterLinks.put(functionId, baseName);
            return this;
        }

        public Builder addImportRequirements(LanguageId languageId, String importString) {
            _importRequirements.addRequirement(languageId, importString);
            return this;
        }

        public Builder addParameter(Parameter value) { _parameters.add(value); return this; }
        public Builder addPathPreset(PresetId value) { _pathPresets.add(value); return this; }
        public Builder addQueryPreset(String queryTag, PresetId value) { _queryPresets.put(queryTag, value); return this; }
        public Builder addResultDescription(String value) { _resultDescription.add(value); return this; }
        public Builder setBaseName(String value) { _baseName = value; return this; }
        public Builder setCompositeStructId(StructId value) { _compositeStructId = value; return this; }
        public Builder setFunctionId(FunctionId value) { _functionId = value; return this; }
        public Builder setIsAsynchronous(boolean value) { _isAsynchronous = value; return this; }
        public Builder setPartialPath(String value) { _partialPath = value; return this; }
        public Builder setRestMethod(HttpMethod value) { _restMethod = value; return this; }
        public Builder setResultDataDescriptor(DataDescriptor value) { _resultDataDescriptor = value; return this; }
        public Builder setSpecificLanguageId(LanguageId value) { _specificLanguageId = value; return this; }

        public Function build() {
            if (_functionId == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setFunctionId"));
            }

            if (_baseName == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setBaseName"));
            }

            if (_description.isEmpty()) {
                throw new RuntimeException(String.format(ExceptionFmt, "addDescription"));
            }

            if (_resultDataDescriptor != null && _resultDescription.isEmpty()) {
                throw new RuntimeException("Function result was specified, but no description was provided");
            }

            if (_compositeStructId == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setCompositeStructId"));
            }

            if (_restMethod != null) {
                if (_restMethod != HttpMethod.DELETE && _restMethod != HttpMethod.POST) {
                    throw new RuntimeException("REST method for ReadWriteCompositeFunctions must be DELETE or POST if specified");
                }
                if (_partialPath == null) {
                    throw new RuntimeException(String.format(ExceptionFmt, "setPartialPath"));
                }
            }


            var counters = countParameters(_parameters);
            if (counters._bodyParameterCount > 0) {
                throw new RuntimeException("BodyParameter specifications are not allowed in a GetPostCompositeFunction");
            }
            if (counters._wiredParameterCount > 0) {
                throw new RuntimeException("WiredParameter specifications are not allowed in a GetPostCompositeFunction");
            }

            return new CompositeStructFunction(_functionId,
                                               _baseName,
                                               _isAsynchronous,
                                               new Description(_description),
                                               new Description(_deprecatedMessage),
                                               _importRequirements,
                                               _specificLanguageId,
                                               _restMethod,
                                               _partialPath,
                                               _resultDataDescriptor,
                                               new Description(_resultDescription),
                                               _parameters,
                                               _pathPresets,
                                               _queryPresets,
                                               _compositeStructId,
                                               _getterLinks);
        }
    }
}
