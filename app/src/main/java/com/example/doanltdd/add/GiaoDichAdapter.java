package com.example.doanltdd.add;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.doanltdd.R;

import java.util.List;

public class GiaoDichAdapter extends BaseAdapter {


    private Context context;
    private int layout;
    private List<GiaoDich> list;

    public GiaoDichAdapter(Context context, int layout, List<GiaoDich> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);
        //
        TextView txtSoTien = (TextView) convertView.findViewById(R.id.txt_sotien);
        TextView txtNoiDung = (TextView) convertView.findViewById(R.id.txt_noidung);
        TextView txtLoaiGD = (TextView) convertView.findViewById(R.id.txt_loaigiaodich);
        TextView txtNgay = (TextView) convertView.findViewById(R.id.txt_ngaythang);

        GiaoDich giaoDich = list.get(position);

        txtSoTien.setText(giaoDich.getSoTien());
        txtNoiDung.setText(giaoDich.getNoiDung());
        txtLoaiGD.setText(giaoDich.getLoaiGiaoDich());
        txtNgay.setText(giaoDich.getNgayThang());

        return convertView;
    }
}
