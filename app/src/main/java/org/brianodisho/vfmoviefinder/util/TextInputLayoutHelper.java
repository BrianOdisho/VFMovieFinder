package org.brianodisho.vfmoviefinder.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.ViewParent;
import android.widget.EditText;

/**
 * Quick tool for working with TextInput and TextInputEditText.
 */

public class TextInputLayoutHelper {

    private TextInputLayoutHelper() {
        throw new RuntimeException("No Instantiation");
    }

    @Nullable
    public static TextInputLayout getTextInputLayout(@NonNull EditText editText) {
        ViewParent parent = editText.getParent();

        if (parent instanceof TextInputLayout) {
            return (TextInputLayout) parent;
        } else if (parent != null) {
            parent = parent.getParent();
            if (parent instanceof TextInputLayout) {
                return (TextInputLayout) parent;
            }
        }
        return null;
    }


    public static void setError(@NonNull EditText editText, CharSequence errorMessage) {
        TextInputLayout textInputLayout = getTextInputLayout(editText);
        if (textInputLayout != null) {
            textInputLayout.setError(errorMessage);
        }
    }


    public static void setVisibility(@NonNull EditText editText, int visibility) {
        TextInputLayout textInputLayout = getTextInputLayout(editText);
        if (textInputLayout == null) {
            editText.setVisibility(visibility);
        } else {
            textInputLayout.setVisibility(visibility);
        }
    }
}
