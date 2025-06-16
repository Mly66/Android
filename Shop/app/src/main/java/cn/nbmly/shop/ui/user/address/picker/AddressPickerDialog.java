package cn.nbmly.shop.ui.user.address.picker;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.List;
import cn.nbmly.shop.R;

public class AddressPickerDialog extends BottomSheetDialogFragment {

    private static final String[] PROVINCES = { "北京市", "上海市", "广东省", "江苏省", "浙江省", "四川省", "湖北省", "湖南省", "河北省", "河南省" };
    private static final String[][] CITIES = {
            { "东城区", "西城区", "朝阳区", "海淀区", "丰台区", "石景山区" },
            { "黄浦区", "徐汇区", "长宁区", "静安区", "普陀区", "虹口区" },
            { "广州市", "深圳市", "珠海市", "汕头市", "佛山市", "韶关市" },
            { "南京市", "无锡市", "徐州市", "常州市", "苏州市", "南通市" },
            { "杭州市", "宁波市", "温州市", "嘉兴市", "湖州市", "绍兴市" },
            { "成都市", "自贡市", "攀枝花市", "泸州市", "德阳市", "绵阳市" },
            { "武汉市", "黄石市", "十堰市", "宜昌市", "襄阳市", "鄂州市" },
            { "长沙市", "株洲市", "湘潭市", "衡阳市", "邵阳市", "岳阳市" },
            { "石家庄市", "唐山市", "秦皇岛市", "邯郸市", "邢台市", "保定市" },
            { "郑州市", "开封市", "洛阳市", "平顶山市", "安阳市", "鹤壁市" }
    };
    private static final String[][][] DISTRICTS = {
            { { "东华门街道", "景山街道", "交道口街道" }, { "德胜街道", "金融街街道", "什刹海街道" }, { "三里屯街道", "朝外街道", "建外街道" },
                    { "中关村街道", "学院路街道", "北太平庄街道" }, { "丰台街道", "东高地街道", "南苑街道" }, { "八宝山街道", "老山街道", "古城街道" } },
            { { "外滩街道", "南京东路街道", "豫园街道" }, { "徐家汇街道", "天平路街道", "湖南路街道" }, { "华阳路街道", "周家桥街道", "天山路街道" },
                    { "静安寺街道", "曹家渡街道", "江宁路街道" }, { "长寿路街道", "曹杨新村街道", "长风新村街道" }, { "四川北路街道", "欧阳路街道", "曲阳路街道" } },
            { { "越秀区", "海珠区", "荔湾区" }, { "福田区", "罗湖区", "南山区" }, { "香洲区", "斗门区", "金湾区" }, { "金平区", "龙湖区", "澄海区" },
                    { "禅城区", "南海区", "顺德区" }, { "浈江区", "武江区", "曲江区" } },
            { { "玄武区", "秦淮区", "建邺区" }, { "梁溪区", "锡山区", "新吴区" }, { "鼓楼区", "云龙区", "贾汪区" }, { "天宁区", "钟楼区", "新北区" },
                    { "姑苏区", "虎丘区", "吴中区" }, { "崇川区", "港闸区", "通州区" } },
            { { "上城区", "下城区", "江干区" }, { "海曙区", "江北区", "北仑区" }, { "鹿城区", "龙湾区", "瓯海区" }, { "南湖区", "秀洲区", "嘉善县" },
                    { "吴兴区", "南浔区", "德清县" }, { "越城区", "柯桥区", "上虞区" } },
            { { "锦江区", "青羊区", "金牛区" }, { "自流井区", "贡井区", "大安区" }, { "东区", "西区", "仁和区" }, { "江阳区", "纳溪区", "龙马潭区" },
                    { "旌阳区", "中江县", "罗江县" }, { "涪城区", "游仙区", "安州区" } },
            { { "江岸区", "江汉区", "硚口区" }, { "黄石港区", "西塞山区", "下陆区" }, { "茅箭区", "张湾区", "郧阳区" }, { "西陵区", "伍家岗区", "点军区" },
                    { "襄城区", "樊城区", "襄州区" }, { "梁子湖区", "华容区", "鄂城区" } },
            { { "芙蓉区", "天心区", "岳麓区" }, { "荷塘区", "芦淞区", "石峰区" }, { "雨湖区", "岳塘区", "湘潭县" }, { "珠晖区", "雁峰区", "石鼓区" },
                    { "双清区", "大祥区", "北塔区" }, { "岳阳楼区", "云溪区", "君山区" } },
            { { "长安区", "桥西区", "新华区" }, { "路南区", "路北区", "古冶区" }, { "海港区", "山海关区", "北戴河区" }, { "邯山区", "丛台区", "复兴区" },
                    { "桥东区", "桥西区", "邢台县" }, { "竞秀区", "莲池区", "满城区" } },
            { { "中原区", "二七区", "金水区" }, { "鼓楼区", "龙亭区", "顺河区" }, { "老城区", "西工区", "瀍河区" }, { "新华区", "卫东区", "石龙区" },
                    { "文峰区", "北关区", "殷都区" }, { "鹤山区", "山城区", "淇滨区" } }
    };

    private OnAddressSelectedListener listener;
    private int currentLevel = 0; // 0: 省份, 1: 城市, 2: 区县
    private int selectedProvince = -1;
    private int selectedCity = -1;

    public interface OnAddressSelectedListener {
        void onProvinceSelected(String province);

        void onCitySelected(String city);

        void onDistrictSelected(String district);
    }

    public static AddressPickerDialog newInstance() {
        return new AddressPickerDialog();
    }

    public void setOnAddressSelectedListener(OnAddressSelectedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_address_picker, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        TextView titleText = view.findViewById(R.id.titleText);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        AddressAdapter adapter = new AddressAdapter();
        recyclerView.setAdapter(adapter);

        updateTitle(titleText);
        updateData(adapter);

        return view;
    }

    private void updateTitle(TextView titleText) {
        switch (currentLevel) {
            case 0:
                titleText.setText("选择省份");
                break;
            case 1:
                titleText.setText("选择城市");
                break;
            case 2:
                titleText.setText("选择区县");
                break;
        }
    }

    private void updateData(AddressAdapter adapter) {
        switch (currentLevel) {
            case 0:
                adapter.setData(PROVINCES);
                break;
            case 1:
                adapter.setData(CITIES[selectedProvince]);
                break;
            case 2:
                adapter.setData(DISTRICTS[selectedProvince][selectedCity]);
                break;
        }
    }

    private class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
        private String[] data;

        public void setData(String[] data) {
            this.data = data;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.textView.setText(data[position]);
            holder.itemView.setOnClickListener(v -> {
                if (listener != null) {
                    switch (currentLevel) {
                        case 0:
                            selectedProvince = position;
                            listener.onProvinceSelected(data[position]);
                            currentLevel = 1;
                            break;
                        case 1:
                            selectedCity = position;
                            listener.onCitySelected(data[position]);
                            currentLevel = 2;
                            break;
                        case 2:
                            listener.onDistrictSelected(data[position]);
                            dismiss();
                            break;
                    }
                    updateTitle(requireView().findViewById(R.id.titleText));
                    updateData(this);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data != null ? data.length : 0;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            ViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(android.R.id.text1);
            }
        }
    }
}