package cn.nbmly.shop.ui.user.address.edit;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import cn.nbmly.shop.data.model.Address;
import cn.nbmly.shop.databinding.ActivityAddressEditBinding;
import cn.nbmly.shop.ui.user.address.picker.AddressPickerDialog;

public class AddressEditActivity extends AppCompatActivity implements AddressPickerDialog.OnAddressSelectedListener {

    private ActivityAddressEditBinding binding;
    private AddressEditViewModel viewModel;
    private Address address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddressEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar();
        setupViewModel();
        loadAddress();
        setupClickListeners();
    }

    private void setupToolbar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(AddressEditViewModel.class);
    }

    private void loadAddress() {
        address = getIntent().getParcelableExtra("address");
        if (address != null) {
            binding.nameEdit.setText(address.getName());
            binding.phoneEdit.setText(address.getPhone());
            binding.provinceEdit.setText(address.getProvince());
            binding.cityEdit.setText(address.getCity());
            binding.districtEdit.setText(address.getDistrict());
            binding.detailEdit.setText(address.getDetail());
            binding.defaultSwitch.setChecked(address.isDefault());
        }
    }

    private void setupClickListeners() {
        binding.provinceEdit.setOnClickListener(v -> showProvincePicker());
        binding.cityEdit.setOnClickListener(v -> showCityPicker());
        binding.districtEdit.setOnClickListener(v -> showDistrictPicker());

        binding.saveButton.setOnClickListener(v -> saveAddress());
    }

    private void showProvincePicker() {
        AddressPickerDialog dialog = AddressPickerDialog.newInstance();
        dialog.setOnAddressSelectedListener(this);
        dialog.show(getSupportFragmentManager(), "province_picker");
    }

    private void showCityPicker() {
        if (TextUtils.isEmpty(binding.provinceEdit.getText())) {
            Toast.makeText(this, "请先选择省份", Toast.LENGTH_SHORT).show();
            return;
        }
        AddressPickerDialog dialog = AddressPickerDialog.newInstance();
        dialog.setOnAddressSelectedListener(this);
        dialog.show(getSupportFragmentManager(), "city_picker");
    }

    private void showDistrictPicker() {
        if (TextUtils.isEmpty(binding.cityEdit.getText())) {
            Toast.makeText(this, "请先选择城市", Toast.LENGTH_SHORT).show();
            return;
        }
        AddressPickerDialog dialog = AddressPickerDialog.newInstance();
        dialog.setOnAddressSelectedListener(this);
        dialog.show(getSupportFragmentManager(), "district_picker");
    }

    @Override
    public void onProvinceSelected(String province) {
        binding.provinceEdit.setText(province);
        binding.cityEdit.setText("");
        binding.districtEdit.setText("");
    }

    @Override
    public void onCitySelected(String city) {
        binding.cityEdit.setText(city);
        binding.districtEdit.setText("");
    }

    @Override
    public void onDistrictSelected(String district) {
        binding.districtEdit.setText(district);
    }

    private void saveAddress() {
        String name = binding.nameEdit.getText().toString().trim();
        String phone = binding.phoneEdit.getText().toString().trim();
        String province = binding.provinceEdit.getText().toString().trim();
        String city = binding.cityEdit.getText().toString().trim();
        String district = binding.districtEdit.getText().toString().trim();
        String detail = binding.detailEdit.getText().toString().trim();
        boolean isDefault = binding.defaultSwitch.isChecked();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入收货人姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(province)) {
            Toast.makeText(this, "请选择省份", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(city)) {
            Toast.makeText(this, "请选择城市", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(district)) {
            Toast.makeText(this, "请选择区县", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(detail)) {
            Toast.makeText(this, "请输入详细地址", Toast.LENGTH_SHORT).show();
            return;
        }

        if (address == null) {
            address = new Address();
        }

        address.setName(name);
        address.setPhone(phone);
        address.setProvince(province);
        address.setCity(city);
        address.setDistrict(district);
        address.setDetail(detail);
        address.setDefault(isDefault);

        viewModel.saveAddress(address);
        finish();
    }
}