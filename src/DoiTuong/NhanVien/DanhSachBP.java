package DoiTuong.NhanVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DanhSachBP{
	static ArrayList <BoPhan> DSachBP = new ArrayList<BoPhan>();
    public static void layDSBP(){
        Connection ketNoi = DieuKhien.KetNoiDB.ketNoi();
        String sql = "SELECT * FROM BoPhan";
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                BoPhan bp = new BoPhan();
                bp.setMaBP(rs.getString("MaBP"));
                bp.setTenBP(rs.getString("TenBP"));
                DSachBP.add(bp);
                
            }
            ps.close();
            rs.close();
            ketNoi.close();
            
        }catch(Exception ex) {
            ex.printStackTrace();
            
        }
    }

    public static ArrayList<BoPhan> getDSachBP() {
        return DSachBP;
    }
}
