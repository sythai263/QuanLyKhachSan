package DoiTuong.NhanVien;

public class TaiKhoan {
	private String MaNV ="", MatKhau ="";
	
	public String getMaNV() {
		return MaNV;
	}

	public void setMaNV(String maNV) {
		MaNV = maNV;
	}

	public String getMatKhau() {
		return MatKhau;
	}

	public void setMatKhau(String matKhau) {
		MatKhau = matKhau;
	}

	public TaiKhoan(String maNV, String matKhau) {
		super();
		MaNV = maNV;
		MatKhau = matKhau;
	}

	public TaiKhoan() {
		super();
	}
	
	public boolean checkMaNV(String username) {
		boolean kq = false;
		if (getMaNV().equals(username)) {
			kq= true;
		} else kq = false;
		
		return kq;
	}
	public boolean checkMatKhau(String password) {
		boolean kq = false;
		if (password.equals(getMatKhau())) {
			kq= true;
		}else kq = false;
		
		return kq;
	}
	public boolean login(String username, String password) {
		boolean kq = false;
		if ((checkMaNV(username)) && (checkMatKhau(password))) {
			kq = true;
		}
		return kq;
	}
	
}
