package com.example.duanmau.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.R;
import com.example.duanmau.adapter.PhieuMuonAdapter;
import com.example.duanmau.adapter.SachSpinnerAdapter;
import com.example.duanmau.adapter.ThanhVienSpinnerAdapter;
import com.example.duanmau.dao.PhieuMuonDAO;
import com.example.duanmau.dao.SachDAO;
import com.example.duanmau.dao.ThanhVienDAO;
import com.example.duanmau.model.PhieuMuon;
import com.example.duanmau.model.Sach;
import com.example.duanmau.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PhieuMuonFragment extends Fragment {

    ListView lvPhieuMuon;
    ArrayList<PhieuMuon> list;
    static PhieuMuonDAO dao;
    PhieuMuonAdapter adapter;
    PhieuMuon item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaPM;
    Spinner spnTV, spnSach;
    TextView tvNgay, tvTienThue;
    CheckBox chkTraSach;
    Button btnSave, btnCancel;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    ArrayList<ThanhVien> listThanhVien;
    ThanhVienDAO thanhVienDAO;
    ThanhVien thanhVien;
    int maThanhVien;

    SachSpinnerAdapter sachSpinnerAdapter;
    ArrayList<Sach> listSach;
    SachDAO sachDAO;
    Sach sach;
    int maSach, tienThue;
    int positionTV, positionSach;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        lvPhieuMuon = v.findViewById(R.id.lvPhieuMuon);
        fab = v.findViewById(R.id.fab);
        dao = new PhieuMuonDAO(getActivity());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);

            }
        });
        lvPhieuMuon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);//update
                return false;
            }
        });
        capNhatLv();
        return v;
    }
    void capNhatLv(){
        list = (ArrayList<PhieuMuon>) dao.getAll();
        adapter = new PhieuMuonAdapter(getActivity(),this, list);
        lvPhieuMuon.setAdapter(adapter);
    }
    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.phieu_muon_dialog);
        edMaPM = dialog.findViewById(R.id.edMaPM);
        spnTV = dialog.findViewById(R.id.spnMaTV);
        spnSach = dialog.findViewById(R.id.spnMaSach);
        tvNgay = dialog.findViewById(R.id.tvNgay);
        tvTienThue = dialog.findViewById(R.id.tvTienThue);
        chkTraSach = dialog.findViewById(R.id.chkTraSach);
        btnCancel = dialog.findViewById(R.id.btnCancelPM);
        btnSave = dialog.findViewById(R.id.btnSavePM);

        //set ngay thue mac dinh hien hanh
        tvNgay.setText("Ngày thuê: "+sdf.format(new Date()));
        edMaPM.setEnabled(false);


        thanhVienDAO = new ThanhVienDAO(context);
        listThanhVien = new ArrayList<ThanhVien>();
        listThanhVien = (ArrayList<ThanhVien>)thanhVienDAO.getAll();
        thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(context, listThanhVien);
        spnTV.setAdapter(thanhVienSpinnerAdapter);
        spnTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maThanhVien = listThanhVien.get(position).getMaTV();
                //Toast.makeText(context, "Chọn"+listThanhVien.get(position).getHoTen(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sachDAO = new SachDAO(context);
        listSach = new ArrayList<Sach>();
        listSach = (ArrayList<Sach>)sachDAO.getAll();
        sachSpinnerAdapter = new SachSpinnerAdapter(context, listSach);
        spnSach.setAdapter(sachSpinnerAdapter);
        spnSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach = listSach.get(position).getMaSach();
                tienThue = listSach.get(position).getGiaThue();
                tvTienThue.setText("Tiền thuê: "+tienThue);
                //Toast.makeText(context, "Chọn"+listSach.get(position).getTenSach(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //set data len form
        if (type != 0){
            edMaPM.setText(String.valueOf(item.getMaPM()));
            for (int i = 0; i < listThanhVien.size(); i++)
                if (item.getMaTV() == listThanhVien.get(i).getMaTV()){
                    positionTV = i;
                }
            spnTV.setSelection(positionTV);

            for (int i = 0; i < listSach.size(); i++)
                if (item.getMaSach() == listSach.get(i).getMaSach()){
                    positionTV = i;
                }
            spnSach.setSelection(positionSach);

            tvNgay.setText("Ngày thuê: "+sdf.format(item.getNgay()));
            tvTienThue.setText("Tiền thuê: "+item.getTienThue());
            if (item.getTraSach()==1){
                chkTraSach.setChecked(true);
            }else {
                chkTraSach.setChecked(false);
            }
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new PhieuMuon();
                item.setMaSach(maSach);
                item.setMaTV(maThanhVien);
                item.setNgay(new Date());
                item.setTienThue(tienThue);
                if (chkTraSach.isChecked()){
                    item.setTraSach(1);
                }else {
                    item.setTraSach(0);
                }
                if (type == 0){
                    //type = 0 insert
                    if (dao.insert(item)>0){
                        Toast.makeText(context, "Thêm thành công ", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    item.setMaPM(Integer.parseInt(edMaPM.getText().toString()));
                    if (dao.update(item)>0){
                        Toast.makeText(context, "Sửa thành công ", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                capNhatLv();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dao.delete(Id);
                capNhatLv();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }
}