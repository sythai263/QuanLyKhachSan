package DoiTuong.KhachSan;

public class TrangThai {
	String maTT, tenTT;

	public String getMaTT() {
		return maTT;
	}

	public void setMaTT(String maTT) {
		this.maTT = maTT;
	}

	public String getTenTT() {
		return tenTT;
	}

	public void setTenTT(String tenTT) {
		this.tenTT = tenTT;
	}

	public TrangThai(String maTT, String tenTT) {
		super();
		this.maTT = maTT;
		this.tenTT = tenTT;
	}

	public TrangThai() {
		super();
		this.maTT = "";
		this.tenTT = "";
	}
	

}
