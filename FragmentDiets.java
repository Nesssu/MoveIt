import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class FragmentDiets extends Fragment {
    private EditText editTextDiets;
    private ActiveUserData activeUserData = ActiveUserData.getInstance();

    // This page is a diet page that allows the user to read and update their diets.

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_diets, container, false);
        editTextDiets = (EditText) v.findViewById(R.id.editTextDiets);
        // Put's all the diets to the editText for the user to read.
        SharedPreferences sharedPreferences = Objects.requireNonNull(getContext()).getSharedPreferences(
                "DietList", Context.MODE_PRIVATE);
        String work = sharedPreferences.getString(activeUserData.getUsername(), "Remember to drink a lot of water\nRemember to sleep 8 hours a night\nExcersice at least 5 times a week\n");
        editTextDiets.setText(work);
        // Saves the changes made by the user automatically to the file after the change have been made.
        editTextDiets.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String workouts = editTextDiets.getText().toString();
                SharedPreferences preferences = Objects.requireNonNull(getContext()).getSharedPreferences(
                        "DietList", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(activeUserData.getUsername(), workouts);
                editor.apply();
            }
        });
        return v;
    }
}
