package cn.nbmly.shop.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import cn.nbmly.shop.databinding.FragmentUserCenterBinding;
import cn.nbmly.shop.ui.login.LoginActivity;
import cn.nbmly.shop.ui.user.address.AddressActivity;
import cn.nbmly.shop.ui.user.settings.SettingsActivity;
import cn.nbmly.shop.ui.user.about.AboutActivity;

public class UserCenterFragment extends Fragment {

    private FragmentUserCenterBinding binding;
    private UserCenterViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        binding = FragmentUserCenterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewModel();
        setupClickListeners();
        observeUserInfo();
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(UserCenterViewModel.class);
    }

    private void setupClickListeners() {
        binding.addressItem.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), AddressActivity.class));
        });

        binding.settingsItem.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), SettingsActivity.class));
        });

        binding.aboutItem.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), AboutActivity.class));
        });

        binding.logoutButton.setOnClickListener(v -> {
            viewModel.logout();
            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
        });
    }

    private void observeUserInfo() {
        viewModel.getUserInfo().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                binding.usernameText.setText(user.getUsername());
                binding.emailText.setText(user.getEmail());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}