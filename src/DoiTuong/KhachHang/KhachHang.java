package DoiTuong.KhachHang;

import java.sql.Connection;
import java.sql.PreparedStatement;


import DieuKhien.KetNoiDB;

public class KhachHang {
	String soCCCD="", ho ="", ten="", diachi = "", 
			sdt = "", mst ="", email ="";
	String loi ="";

	public String getSoCCCD() {
		return soCCCD;
	}

	public void setSoCCCD(String soCCCD) {
		this.soCCCD = soCCCD;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getMst() {
		return mst;
	}

	public void setMst(String mst) {
		this.mst = mst;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public KhachHang(String soCCCD, String ho, String ten, String diachi, String sdt, String email, String mst) {
		super();
		this.soCCCD = soCCCD;
		this.ho = ho;
		this.ten = ten;
		this.diachi = diachi;
		this.sdt = sdt;
		this.mst = mst;
		this.email = email;
	}

	public KhachHang() {
		super();
	}
	
	public boolean luuTTKH() {
		boolean kq = false;
		Connection ketNoi = KetNoiDB.ketNoi();
		String layDSKH = "INSERT INTO KhachHang VALUES (?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(layDSKH);
			ps.setString(1, getSoCCCD());
			ps.setString(2, getHo());
			ps.setString(3, getTen());
			ps.setString(4, getDiachi());
			ps.setString(5, getSdt());
			ps.setString(6, getEmail());
			ps.setString(7, getMst());
			ps.executeUpdate();
			kq= true;
			ps.close();
			ketNoi.close();
		}catch (Exception e) {
			System.out.println(e.getMessage());
			loi = e.getMessage();
		}
		
		return kq;
	}

	public String getLoi() {
		return loi;
	}
	public void capNhatTTKH() {
		Connection ketNoi = KetNoiDB.ketNoi();
		String layDSKH = "UPDATE KhachHang "+
						"SET Ho = '"+getHo()+
							"', Ten ='"+getTen()+ "', DC ='"+getDiachi()+
							"', SDT ='"+getSdt()+"', Email='"+getEmail()+
							"', MSThue='"+ getMst()+"'"+
						"WHERE SoCMND ='"+getSoCCCD()+"'";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(layDSKH);
			ps.executeUpdate();
			ps.close();
			ketNoi.close();
			System.out.println("Cập nhật thông tin khách hàng - Close !");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
