package com.liqid.sdk.generator.liqidEntityModels;

import java.util.Collection;

/**
 * Contains a (possibly) multi-line textual description of some Liqid entity, or of a discrete data item there-in.
 * This text will be extracted and written into the generated code as commentary in some suitable language-appropriate fashion.
 * Additionally, the text serves as important information in the generated documentation.
 * <p>
 * Occasionally, it is advantageous for such text to reference some other entity. In this case, such references should be
 * encoded as in the following example:
 *      "... for more information, see {type name}"
 * where the delimiting brackets serve to signify a contained link,
 * the type is selected from "category", "enum", "function", "struct", or "typedef",
 * and the name is the baseName of the indicated category, enum, etc.
 * <p>
 * When a description with such an encoding is encountered, the given name is converted to a language-appropriate name
 * and written into the code commentary; further, it is converted into a hyper-link to the documentation for the referenced
 * entity when written into the documentation.
 */
public class Description {

    private final String[] _rawText;

    public Description(String singleLine) {
        _rawText = new String[]{ singleLine };
    }

    public Description(String[] multiLines) {
        _rawText = multiLines;
    }

    public Description(Collection<String> collection) {
        _rawText = collection.toArray(new String[]{});
    }

    public String[] getStrings() { return _rawText; }

    public boolean isEmpty() {
        return _rawText.length == 0;
    }
}
