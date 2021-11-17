package DoiTuong.KhachSan;

public class DichVu {
	private String maDV, tenDV;
	private int donGia;
	public String getMaDV() {
		return maDV;
	}
	public void setMaDV(String maDV) {
		this.maDV = maDV;
	}
	public String getTenDV() {
		return tenDV;
	}
	public void setTenDV(String tenDV) {
		this.tenDV = tenDV;
	}
	public int getDonGia() {
		return donGia;
	}
	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}
	public DichVu(String maDV, String tenDV, int donGia) {
		super();
		this.maDV = maDV;
		this.tenDV = tenDV;
		this.donGia = donGia;
	}
	public DichVu() {
		super();
	}
	
	

}
