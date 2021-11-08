package es.unex.giiis.koreku.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import es.unex.giiis.koreku.R;
import es.unex.giiis.koreku.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private es.unex.giiis.koreku.ui.profile.ProfileViewModel serviceViewModel;
    private FragmentProfileBinding binding;
    private static final int MENU_DELETE = Menu.FIRST;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        serviceViewModel =
                new ViewModelProvider(this).get(es.unex.giiis.koreku.ui.profile.ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textProfile;
        serviceViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.add(Menu.NONE, MENU_DELETE, Menu.NONE, R.string.delete_profile);
    }
}