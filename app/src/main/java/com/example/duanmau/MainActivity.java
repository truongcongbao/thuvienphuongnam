package com.example.duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.duanmau.dao.PhieuMuonDAO;
import com.example.duanmau.dao.ThuThuDAO;
import com.example.duanmau.fragment.ChangePassFragment;
import com.example.duanmau.fragment.DoanhThuFragment;
import com.example.duanmau.fragment.PhieuMuonFragment;
import com.example.duanmau.fragment.SachFragment;
import com.example.duanmau.fragment.ThanhVienFragment;
import com.example.duanmau.fragment.TopFragment;
import com.example.duanmau.model.ThuThu;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DrawerLayout drawer;
    Toolbar toolbar;
    View mHeaderView;
    TextView edUser;
    PhieuMuonDAO dao;
    ThuThuDAO thuThuDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = findViewById(R.id.drawer_layout);

        toolbar = findViewById(R.id.toolbar1);

        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

        ab.setHomeAsUpIndicator(R.drawable.menu);
        ab.setDisplayHomeAsUpEnabled(true);

        FragmentManager manager = getSupportFragmentManager();
        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
        manager.beginTransaction().replace(R.id.flContent, phieuMuonFragment).commit();

        NavigationView nv = findViewById(R.id.nvView);
        //show user in header
        mHeaderView = nv.getHeaderView(0);
        edUser = mHeaderView.findViewById(R.id.txtUser);
        Intent i = getIntent();
        //
        String user = i.getStringExtra("user");
        thuThuDAO = new ThuThuDAO(this);
        ThuThu thuThu = thuThuDAO.getID(user);
        String username = thuThu.getHoTen();
        //
        edUser.setText("Welcome! " +username+"!");
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                switch (item.getItemId()){
                    case R.id.nav_PhieuMuon:
                        setTitle("Quản lý phiếu mượn");
                        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
                        manager.beginTransaction().replace(R.id.flContent, phieuMuonFragment).commit();
                        break;
                    case R.id.nav_LoaiSach:
                        setTitle("Quản lý loại sách");
                        break;
                    case R.id.nav_Sach:
                        setTitle("Quản lý sách");
                        SachFragment sachFragment = new SachFragment();
                        manager.beginTransaction().replace(R.id.flContent, sachFragment).commit();
                        break;
                    case R.id.nav_ThanhVien:
                        setTitle("Quản lý thành viên");
                        ThanhVienFragment thanhVienFragment = new ThanhVienFragment();
                        manager.beginTransaction().replace(R.id.flContent, thanhVienFragment).commit();
                        break;
                    case R.id.sub_Top:
                        setTitle("Top 10 sách cho thuê nhiều nhất");
                        TopFragment topFragment = new TopFragment();
                        manager.beginTransaction().replace(R.id.flContent, topFragment).commit();
                        break;
                    case R.id.sub_DoanhThu:
                        setTitle("Thống kê doanh thu");
                        DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                        manager.beginTransaction().replace(R.id.flContent, doanhThuFragment).commit();
                        break;
                    case R.id.sub_AddUser:
                        setTitle("Thêm người dùng");
                        break;
                    case R.id.sub_Pass:
                        setTitle("Đổi mật khẩu");
                        ChangePassFragment changePassFragment = new ChangePassFragment();
                        manager.beginTransaction().replace(R.id.flContent, changePassFragment).commit();
                        break;
                    case R.id.sub_Logout:
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                        break;
                }
                drawer.closeDrawers();
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home)
            drawer.openDrawer(GravityCompat.START);

        return super.onOptionsItemSelected(item);
    }
}