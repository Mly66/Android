package cn.nbmly.shop.ui.user.address;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import cn.nbmly.shop.databinding.ActivityAddressBinding;
import cn.nbmly.shop.ui.user.address.adapter.AddressAdapter;
import cn.nbmly.shop.ui.user.address.edit.AddressEditActivity;

public class AddressActivity extends AppCompatActivity implements AddressAdapter.OnAddressActionListener {

    private ActivityAddressBinding binding;
    private AddressViewModel viewModel;
    private AddressAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar();
        setupViewModel();
        setupRecyclerView();
        setupClickListeners();
        observeAddresses();
    }

    private void setupToolbar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(AddressViewModel.class);
    }

    private void setupRecyclerView() {
        adapter = new AddressAdapter(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }

    private void setupClickListeners() {
        binding.addAddressFab.setOnClickListener(v -> {
            startActivity(new Intent(this, AddressEditActivity.class));
        });
    }

    private void observeAddresses() {
        viewModel.getAddresses().observe(this, addresses -> {
            adapter.submitList(addresses);
            binding.emptyView.setVisibility(addresses.isEmpty() ? View.VISIBLE : View.GONE);
        });
    }

    @Override
    public void onSetDefault(Address address, boolean isDefault) {
        viewModel.setDefaultAddress(address, isDefault);
    }

    @Override
    public void onEdit(Address address) {
        Intent intent = new Intent(this, AddressEditActivity.class);
        intent.putExtra("address", address);
        startActivity(intent);
    }

    @Override
    public void onDelete(Address address) {
        viewModel.deleteAddress(address);
    }
}