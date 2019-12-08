package perfomeria.core.common;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;

public class KeyBoardUtil {

    public static void showKeyboard(final @NonNull View focus) {
        final InputMethodManager manager = getInputMethodManager(focus.getContext());

        focus.requestFocus();
        manager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    public static void hideKeyboard(final @NonNull Activity activity) {
        final View currentFocus = activity.getCurrentFocus();
        if (currentFocus == null) return;

        hideKeyboard(currentFocus);
    }

    public static void hideKeyboard(final @NonNull View focusedView) {
        final InputMethodManager manager = getInputMethodManager(focusedView.getContext());
        manager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
    }

    @NonNull
    private static InputMethodManager getInputMethodManager(final @NonNull Context context) {
        return (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
    }

}