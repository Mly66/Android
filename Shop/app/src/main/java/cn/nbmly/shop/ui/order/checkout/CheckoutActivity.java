package cn.nbmly.shop.ui.order.checkout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import cn.nbmly.shop.data.model.Address;
import cn.nbmly.shop.data.model.CartItem;
import cn.nbmly.shop.databinding.ActivityCheckoutBinding;
import cn.nbmly.shop.ui.user.address.AddressActivity;
import cn.nbmly.shop.ui.order.checkout.adapter.CheckoutAdapter;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    private ActivityCheckoutBinding binding;
    private CheckoutViewModel viewModel;
    private CheckoutAdapter adapter;
    private static final int REQUEST_SELECT_ADDRESS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar();
        setupViewModel();
        setupRecyclerView();
        setupClickListeners();
        observeData();
    }

    private void setupToolbar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(CheckoutViewModel.class);
        List<CartItem> cartItems = getIntent().getParcelableArrayListExtra("cart_items");
        viewModel.setCartItems(cartItems);
    }

    private void setupRecyclerView() {
        adapter = new CheckoutAdapter();
        binding.productRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.productRecyclerView.setAdapter(adapter);
    }

    private void setupClickListeners() {
        binding.addressLayout.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddressActivity.class);
            intent.putExtra("select_mode", true);
            startActivityForResult(intent, REQUEST_SELECT_ADDRESS);
        });

        binding.submitButton.setOnClickListener(v -> {
            if (viewModel.getSelectedAddress() == null) {
                Toast.makeText(this, "请选择收货地址", Toast.LENGTH_SHORT).show();
                return;
            }
            viewModel.submitOrder();
            finish();
        });
    }

    private void observeData() {
        viewModel.getCartItems().observe(this, cartItems -> {
            adapter.submitList(cartItems);
            updatePrice();
        });

        viewModel.getSelectedAddress().observe(this, this::updateAddress);
    }

    private void updateAddress(Address address) {
        if (address != null) {
            binding.addressText.setText(address.getFullAddress());
            binding.namePhoneText.setText(String.format("%s %s", address.getName(), address.getPhone()));
        } else {
            binding.addressText.setText("请选择收货地址");
            binding.namePhoneText.setText("");
        }
    }

    private void updatePrice() {
        double totalPrice = viewModel.calculateTotalPrice();
        double freight = viewModel.calculateFreight();
        double actualPrice = totalPrice + freight;

        binding.totalPriceText.setText(String.format("¥%.2f", totalPrice));
        binding.freightText.setText(String.format("¥%.2f", freight));
        binding.actualPriceText.setText(String.format("¥%.2f", actualPrice));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SELECT_ADDRESS && resultCode == RESULT_OK && data != null) {
            Address address = data.getParcelableExtra("address");
            viewModel.setSelectedAddress(address);
        }
    }
}