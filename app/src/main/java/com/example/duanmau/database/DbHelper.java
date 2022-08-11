package com.example.duanmau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "DuAnMau", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Bảng Thủ Thư
        String sql = "create table ThuThu ( maTT TEXT primary key, "+
                "hoTen TEXT not null," +
                "matKhau TEXT not null )";
        db.execSQL(sql);

        sql = "INSERT INTO ThuThu VALUES('admin','công bảo', '123456')";
        db.execSQL(sql);
        sql = "INSERT INTO ThuThu VALUES('admin1','công bảo' , '123456')";
        db.execSQL(sql);
        sql = "INSERT INTO ThuThu VALUES('admin2','Minh Kiên', '123456')";
        db.execSQL(sql);

        //Bảng Thành Viên
        sql = "create table ThanhVien ( maTV INTEGER primary key autoincrement, "+
                "hoTen TEXT not null, " +
                "namSinh TEXT not null)";
        db.execSQL(sql);

        sql = "INSERT INTO ThanhVien VALUES(null,'Đại Nghĩa', '2002')";
        db.execSQL(sql);
        sql = "INSERT INTO ThanhVien VALUES(null, 'Quốc Trung', '2003')";
        db.execSQL(sql);
        sql = "INSERT INTO ThanhVien VALUES(null, 'Nhật Huy', '2002')";
        db.execSQL(sql);
        sql = "INSERT INTO ThanhVien VALUES(null,'Ngọc Sang', '1989')";
        db.execSQL(sql);
        sql = "INSERT INTO ThanhVien VALUES(null, 'Quốc Thông', '2002')";
        db.execSQL(sql);
        sql = "INSERT INTO ThanhVien VALUES(null, 'Ngọc Trà', '2005')";
        db.execSQL(sql);
        sql = "INSERT INTO ThanhVien VALUES(null, 'Thái Luật', '2002')";
        db.execSQL(sql);
        sql = "INSERT INTO ThanhVien VALUES(null,'Thanh Trúc', '2010')";
        db.execSQL(sql);
        sql = "INSERT INTO ThanhVien VALUES(null, 'Minh Hiếu', '1999')";
        db.execSQL(sql);
        sql = "INSERT INTO ThanhVien VALUES(null, 'Hoàng Vĩ', '1998')";
        db.execSQL(sql);


        //Bảng Loại Sách
        sql = "create table LoaiSach ( maLoai INTEGER primary key autoincrement, "+
                "tenLoai TEXT not null )";
        db.execSQL(sql);

        sql = "INSERT INTO LoaiSach VALUES(null, 'Tiếng Anh')";
        db.execSQL(sql);
        sql = "INSERT INTO LoaiSach VALUES(null, 'Photoshop')";
        db.execSQL(sql);
        sql = "INSERT INTO LoaiSach VALUES(null, 'Lập trình andoird')";
        db.execSQL(sql);
        sql = "INSERT INTO LoaiSach VALUES(null, 'Lập trình java')";
        db.execSQL(sql);
        sql = "INSERT INTO LoaiSach VALUES(null, 'Lập trình web')";
        db.execSQL(sql);
        sql = "INSERT INTO LoaiSach VALUES(null, 'Dự án mẫu')";
        db.execSQL(sql);

        //Bảng Sách
        sql = "create table Sach ( maSach INTEGER primary key autoincrement, "+
                "tenSach TEXT not null, " +
                "giaThue INTEGER not null, "+
                "maLoai INTEGER references LoaiSach(maLoai))";
        db.execSQL(sql);

        sql = "INSERT INTO Sach VALUES(null, 'Tiếng Anh cơ bản', '2000', '5')";
        db.execSQL(sql);
        sql = "INSERT INTO Sach VALUES(null, 'Tiếng Anh nâng cao', '2500', '5')";
        db.execSQL(sql);
        sql = "INSERT INTO Sach VALUES(null, 'Photoshop cơ bản', '3000', '2')";
        db.execSQL(sql);
        sql = "INSERT INTO Sach VALUES(null, 'Photoshop nâng cao', '3200', '2')";
        db.execSQL(sql);
        sql = "INSERT INTO Sach VALUES(null, 'Thiết kế web với HTML và CSS', '2000', '6')";
        db.execSQL(sql);
        sql = "INSERT INTO Sach VALUES(null, 'Thiết kế web với Bootstrap', '2000', '6')";
        db.execSQL(sql);
        sql = "INSERT INTO Sach VALUES(null, 'Dự án với công nghệ MS.NET MVC', '2000', '1')";
        db.execSQL(sql);
        sql = "INSERT INTO Sach VALUES(null, 'Dự án với công nghệ Spring MVC', '2000', '1')";
        db.execSQL(sql);
        sql = "INSERT INTO Sach VALUES(null, 'Lập trình JDBC', '4000', '4')";
        db.execSQL(sql);
        sql = "INSERT INTO Sach VALUES(null, 'Lập trình cơ sở dữ liệu', '3500', '4')";
        db.execSQL(sql);
        sql = "INSERT INTO Sach VALUES(null, 'Lập trình java cơ bản', '1000', '3')";
        db.execSQL(sql);
        sql = "INSERT INTO Sach VALUES(null, 'Lập trình java nâng cao', '1500', '3')";
        db.execSQL(sql);

        //Bảng Phiếu Mượn
        sql = "create table PhieuMuon ( maPM INTEGER primary key autoincrement, "+
                "maTT TEXT references ThuTHu(maTT)," +
                "maTV INTEGER references ThanhVien(maTV), " +
                "maSach INTEGER references Sach(maSach)," +
                "tienThue INTEGER not null, " +
                "ngay DATE not null, " +
                "traSach INTEGER not null)";
        db.execSQL(sql);

        sql = "INSERT INTO PhieuMuon VALUES(null, 'admin', '2', '2', '2000', '2021-01-12', '0')";
        db.execSQL(sql);
        sql = "INSERT INTO PhieuMuon VALUES(null, 'admin', '3', '3', '4000', '2021-10-01', '1')";
        db.execSQL(sql);
        sql = "INSERT INTO PhieuMuon VALUES(null, 'admin', '4', '4', '3500', '2021-10-21', '1')";
        db.execSQL(sql);
        sql = "INSERT INTO PhieuMuon VALUES(null, 'admin', '5', '5', '2300', '2021-08-21', '0')";
        db.execSQL(sql);
        sql = "INSERT INTO PhieuMuon VALUES(null, 'admin', '6', '6', '1500', '2021-03-26', '1')";
        db.execSQL(sql);
        sql = "INSERT INTO PhieuMuon VALUES(null, 'admin', '7', '7', '2000', '2021-05-01', '0')";
        db.execSQL(sql);
        sql = "INSERT INTO PhieuMuon VALUES(null, 'admin', '8', '8', '2000', '2021-05-01', '0')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ThuThu");
        db.execSQL("DROP TABLE IF EXISTS ThanhVien");
        db.execSQL("DROP TABLE IF EXISTS LoaiSach");
        db.execSQL("DROP TABLE IF EXISTS Sach");
        db.execSQL("DROP TABLE IF EXISTS PhieuMuon");
        onCreate(db);
    }
}
