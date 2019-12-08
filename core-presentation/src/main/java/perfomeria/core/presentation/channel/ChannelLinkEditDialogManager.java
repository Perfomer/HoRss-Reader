package perfomeria.core.presentation.channel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import perfomeria.core.common.KeyBoardUtil;
import perfomeria.core.presentation.R;

@SuppressLint("InflateParams")
class ChannelLinkEditDialogManager {

    private final Activity activity;

    ChannelLinkEditDialogManager(final @NonNull Activity activity) {
        this.activity = activity;
    }

    /**
     * Shows the dialog
     * Allows to edit [Channel] (RSS-feed) source link.
     * Shows the soft-keyboard after opening.
     *
     * @param currentLink the link of the current channel (to fill the field by default)
     * @param listener result-callback (provides [String] link)
     */
    void showDialog(
            final @Nullable String currentLink,
            final @NonNull OnLinkSelectedListener listener
    ) {
        final View rootView = activity.getLayoutInflater().inflate(R.layout.dialog_channellink, null, false);
        final EditText channelLinkField = rootView.findViewById(R.id.channellink_field);
        final View clearButton = rootView.findViewById(R.id.channellink_clear);


        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity)
                .setTitle(R.string.channellink_dialog_title)
                .setMessage(R.string.channellink_dialog_message)
                .setView(rootView)
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.dismiss())
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    listener.onLinkSelected(channelLinkField.getText().toString());
                });

        dialogBuilder.show();

        clearButton.setOnClickListener((v) -> channelLinkField.getText().clear());
        channelLinkField.setText(currentLink);

        focusField(channelLinkField);
    }

    private static void focusField(EditText field) {
        field.post(() -> {
            KeyBoardUtil.showKeyboard(field);
            field.setSelection(field.getText().length());
        });
    }

    @FunctionalInterface
    public interface OnLinkSelectedListener {

        void onLinkSelected(@NonNull String link);

    }

}