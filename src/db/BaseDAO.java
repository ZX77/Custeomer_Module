package db;

import bean.*;
import com.sun.xml.internal.org.jvnet.fastinfoset.FastInfosetException;
import common.ContextUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by 林longqi on 2018/3/20.
 */
public class BaseDAO {
    //根据部门号删除部门资料
    public void del1(String depid){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "delete dep where depid="+depid;
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //查询部门表
    public List<Dep> finddep1(){
        List<Dep> list=new ArrayList<>();
        Connection connection=DBConn.openDB();
        String sql="select*from Dep";
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                Dep dep=new Dep();
                dep.setDepid(resultSet.getInt("depid"));
                dep.setDepname(resultSet.getString("depname"));
                dep.setChairman(resultSet.getString("chairman"));
                dep.setDescription(resultSet.getString("description"));
                list.add(dep);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    //新增部门资料
    public void save1(Dep d){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "insert into dep values('";
            sql += d.getDepname() +"','";
            sql += d.getChairman() +"','";
            sql += d.getDescription()+"')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //通过部门号查询一个部门的信息
    public Dep findByStudId1(String depid){
        Connection conn = DBConn.openDB();
        Dep dep = new Dep();

        try{
            Statement stmt = conn.createStatement();
            String sql = "select * from dep where depid="+depid;
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                dep.setDepid(rs.getInt("depid"));
                dep.setDepname(rs.getString("depname").trim());
                dep.setChairman(rs.getString("chairman").trim());
                dep.setDescription(rs.getString("description").trim());
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return dep;

    }
    //修改部门资料
    public void update1(Dep d){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "update dep set depname='";
            sql += d.getDepname() +"',chairman='";
            sql += d.getChairman() +"',description='";
            sql += d.getDescription()+"' where depid="+d.getDepid();
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //统计记录总数
    public int findDepCount1(){
        int cnt=0;
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();

        try{
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from dep";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                cnt = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return cnt;
    }
    //分页查询部门资料
    public List<Dep> findByPage1(int pageSize,int curPage){

        List<Dep> list = new ArrayList<Dep>();
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();
        //计算起始位置
        int startPos = (curPage-1)*pageSize;
        try{
            Statement stmt = conn.createStatement();
            String sql = "select top "+pageSize+" * from dep where depid not in(";
            sql += "select top "+startPos+" depid from dep";
            sql +=")";
            System.out.println("sql="+sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Dep s = new Dep();
                s.setDepid(rs.getInt("depid"));
                s.setDepname(rs.getString("depname").trim());
                s.setChairman(rs.getString("chairman").trim());
                s.setDescription(rs.getString("description").trim());
                list.add(s);
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    //岗位----------------------------------

    //根据岗位号删除岗位资料
    public void del2(int degreeid) {
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "delete degrees where degreeid=" + degreeid;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询岗位表
    public List<degrees> finddegrees2() {
        List<degrees> list = new ArrayList<>();
        Connection connection = DBConn.openDB();
        String sql = "select*from degrees";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                degrees degrees = new degrees();
                degrees.setDegreeid(resultSet.getInt("degreeid"));
                degrees.setDegreename(resultSet.getString("degreename"));
                list.add(degrees);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //新增岗位资料
    public void save2(degrees d) {
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "insert into degrees values('";
            sql += d.getDegreename() + "')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //通过岗位号查询一个岗位的信息
    public degrees findByStudId2(int degreeid) {
        Connection conn = DBConn.openDB();
        degrees deg = new degrees();

        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from degrees where degreeid=" + degreeid;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                deg.setDegreeid(rs.getInt("degreeid"));
                deg.setDegreename(rs.getString("degreename").trim());
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deg;

    }

    //修改岗位资料
    public void update2(degrees d) {
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "update degrees set degreename='";
            sql += d.getDegreename() + "' where degreeid=" + d.getDegreeid();
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //统计记录总数
    public int findCount2() {
        int cnt = 0;
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();

        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from degrees";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                cnt = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnt;
    }


    //分页岗位表
    public List<degrees> findByPage2(int pageSize, int curPage) {
        List<degrees> list = new ArrayList<degrees>();
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();
        //计算起始位置
        int startPos = (curPage - 1) * pageSize;
        try {
            Statement stmt = conn.createStatement();
            String sql = "select top " + pageSize + " * from degrees where degreeid not in(";
            sql += "select top " + startPos + " degreeid from degrees";
            sql += ")";
            System.out.println("sql=" + sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                degrees s = new degrees();
                s.setDegreeid(rs.getInt("degreeid"));
                s.setDegreename(rs.getString("degreename"));
                list.add(s);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //根据用户号删除用户资料
    public void del3(int userid){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "delete Users where userid="+userid;
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //新增用户的下拉框中显示
    public List<Dep> findDepAll3(){
        List<Dep> list = new ArrayList<Dep>();
        //创建Connection对象
        Connection conn = DBConn.openDB();
        //计算起始位置
        try{
            Statement stmt = conn.createStatement();
            String sql = "select * from dep";
            System.out.println("sql="+sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Dep s = new Dep();
                s.setDepid(rs.getInt("depid"));
                s.setDepname(rs.getString("depName").trim());
                s.setChairman(rs.getString("chairman").trim());
                s.setDescription(rs.getString("description").trim());
                list.add(s);
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    //新增用户资料
    public void add3(Users d){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "insert into Users values('";
            sql += d.getUsername() +"','";
            sql += d.getPassword() +"',";
            sql += d.getDepid() +",";
            sql += d.getDegreeid() +",'";
            sql += d.getJobname() +"',";
            sql += d.getManagerType() +",'";
            sql += d.getMobile() +"','";
            sql += d.getEmail() +"','";
            sql += d.getQqcode() +"','";
            sql += d.getWeixin() +"','";
            sql += d.getCardno() +"','";
            sql += d.getBankName() +"','";
            sql += d.getBankCardNo() +"','";
            sql += d.getJoinDate() +"','";
            sql += d.getWorkDate() +"','";
            sql += d.getLevelDate() +"',";
            sql += d.getBaseSalary() +",";
            sql += d.getDegreeSalary() +",'";
            sql += d.getAddr() +"',";
            sql += d.getStatus() +",'";
            sql += d.getRemark()+"')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //通过用户号查询一个用户的信息
    public Users findByUserid3(String userid){
        Connection conn = DBConn.openDB();
        Users users = new Users();

        try{
            Statement stmt = conn.createStatement();
            String sql = "select * from Users where userid="+userid;
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                users.setUserid(rs.getInt("userid"));
                users.setUsername(rs.getString("username"));
                users.setPassword(rs.getString("password"));
                users.setDepid(rs.getInt("depid"));
                users.setDegreeid(rs.getInt("degreeid"));
                users.setJobname(rs.getString("jobname"));
                users.setManagerType(rs.getInt("managerType"));
                users.setMobile(rs.getString("mobile"));
                users.setEmail(rs.getString("email"));
                users.setQqcode(rs.getString("qqcode"));
                users.setWeixin(rs.getString("weixin"));
                users.setCardno(rs.getString("cardno"));
                users.setBankName(rs.getString("bankname"));
                users.setBankCardNo(rs.getString("bankCardNo"));
                users.setJoinDate(rs.getString("joinDate"));
                users.setWorkDate(rs.getString("workDate"));
                users.setLevelDate(rs.getString("levelDate"));
                users.setBaseSalary(rs.getFloat("basesalary"));
                users.setDegreeSalary(rs.getFloat("degreesalary"));
                users.setAddr(rs.getString("addr"));
                users.setStatus(rs.getInt("status"));
                users.setRemark(rs.getString("remark"));
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return users;

    }

    //修改密码
    public void changePwd(int userid,String newpassword){
        Connection conn = DBConn.openDB();
        String sql = "update users set password = "+newpassword+" where userid = "+userid+"";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //修改用户资料
    public void update3(Users d){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "update Users set ";
            sql += " depid=";
            sql += d.getDepid() +",degreeid=";
            sql += d.getDegreeid() +",jobname='";
            sql += d.getJobname() +"',managerType=";
            sql += d.getManagerType() +",mobile='";
            sql += d.getMobile() +"',email='";
            sql += d.getEmail() +"',qqcode='";
            sql += d.getQqcode() +"',weixin='";
            sql += d.getWeixin() +"',cardno='";
            sql += d.getCardno() +"',bankName='";
            sql += d.getBankName() +"',bankCardNo='";
            sql += d.getBankCardNo() +"',joinDate='";
            sql += d.getJoinDate() +"',workDate='";
            sql += d.getWorkDate() +"',levelDate='";
            sql += d.getLevelDate() +"',baseSalary=";
            sql += d.getBaseSalary() +",degreeSalary=";
            sql += d.getDegreeSalary() +",addr='";
            sql += d.getAddr() +"',remark='";
            sql += d.getRemark()+"' where userid="+d.getUserid();
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    //查询所有用户
    public List<Users> findUserAll3(){
        List<Users> list = new ArrayList<Users>();
        //创建Connection对象
        Connection conn = DBConn.openDB();
        //计算起始位置
        try{
            Statement stmt = conn.createStatement();
            String sql = "select * from users";
            System.out.println("sql="+sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
               Users users = new Users();
                users.setUserid(rs.getInt("userid"));
                users.setUsername(rs.getString("username"));
                users.setPassword(rs.getString("password"));
                users.setDepid(rs.getInt("depid"));
                users.setDegreeid(rs.getInt("degreeid"));
                users.setJobname(rs.getString("jobname"));
                users.setManagerType(rs.getInt("managerType"));
                users.setMobile(rs.getString("mobile"));
                users.setEmail(rs.getString("email"));
                users.setQqcode(rs.getString("qqcode"));
                users.setWeixin(rs.getString("weixin"));
                users.setCardno(rs.getString("cardno"));
                users.setBankName(rs.getString("bankname"));
                users.setBankCardNo(rs.getString("bankCardNo"));
                users.setJoinDate(rs.getString("joinDate"));
                users.setWorkDate(rs.getString("workDate"));
                users.setLevelDate(rs.getString("levelDate"));
                users.setBaseSalary(rs.getFloat("basesalary"));
                users.setDegreeSalary(rs.getFloat("degreesalary"));
                users.setAddr(rs.getString("addr"));
                users.setStatus(rs.getInt("status"));
                users.setRemark(rs.getString("remark"));
                list.add(users);
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }


    //users禁用/启用状态
    public void updateUserStatus3(Users u){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "update users set status="+ u.getStatus()+" where userid="+u.getUserid();
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }



    //统计记录总数
    public int findUsersCount3(){
        int cnt=0;
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();

        try{
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from Users";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                cnt = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return cnt;
    }

    public boolean checkUser(String username){
        boolean b=false;
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            //登录成功的条件：用户名称存在，密码准确，并且状态为启用状态
            String sql = "select * from users where username='"+username+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                b=true;
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return b;
    }
    //分页查询用户资料
    public List<Users> findByPageUsers3(int pageSize,int curPage){

        List<Users> list = new ArrayList<Users>();
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();
        //计算起始位置
        int startPos = (curPage-1)*pageSize;
        try{
            Statement stmt = conn.createStatement();
            String sql = "select top "+pageSize+" * from Users u inner join dep d on u.depid=d.depid where userid not in(";
            sql += "select top "+startPos+" userid from Users";
            sql +=")"+"order by userid";
            System.out.println("sql="+sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Users s = new Users();
                s.setUserid(rs.getInt("userid"));
                s.setUsername(rs.getString("username").trim());
                s.setPassword(rs.getString("password").trim());
                s.setDepid(rs.getInt("depid"));
                s.setDegreeid(rs.getInt("degreeid"));
                s.setJobname(rs.getString("jobname").trim());
                s.setManagerType(rs.getInt("managerType"));
                s.setMobile(rs.getString("mobile").trim());
                s.setEmail(rs.getString("email").trim());
                s.setQqcode(rs.getString("qqcode").trim());
                s.setWeixin(rs.getString("weixin").trim());
                s.setCardno(rs.getString("cardno").trim());
                s.setBankName(rs.getString("bankName").trim());
                s.setBankCardNo(rs.getString("bankCardNo").trim());
                String date = rs.getString("JoinDate");
                if(date ==null)
                    s.setJoinDate("");
                else{
                    s.setJoinDate(date);
                }

                date = rs.getString("WorkDate");
                if(date ==null)
                    s.setWorkDate("");
                else{
                    s.setWorkDate(date);
                }
                date = rs.getString("LevelDate");
                if(date ==null)
                    s.setLevelDate("");
                else{
                    s.setLevelDate(date);
                }
                s.setBaseSalary(rs.getFloat("baseSalary"));
                s.setDegreeSalary(rs.getFloat("degreeSalary"));
                s.setAddr(rs.getString("addr").trim());
                s.setStatus(rs.getInt("status"));
                s.setRemark(rs.getString("remark").trim());
                s.setDepname(rs.getString("depname").trim());
                list.add(s);
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    //判断用户登录成功
    public Users login(Users user){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            //登录成功的条件：用户名称存在，密码准确，并且状态为启用状态
            String sql = "select * from users where username = '"+user.getUsername()+"'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                user.setUserid(rs.getInt("userid"));
                user.setUsername(rs.getString("userName").trim());
                user.setPassword(rs.getString("password").trim());
                user.setDepid(rs.getInt("depid"));
                user.setJobname(rs.getString("jobname").trim());
                user.setManagerType(rs.getInt("ManagerType"));
                user.setMobile(rs.getString("mobile").trim());
                user.setEmail(rs.getString("email").trim());
                user.setQqcode(rs.getString("qqcode").trim());
                user.setWeixin(rs.getString("Weixin").trim());
                user.setCardno(rs.getString("Cardno").trim());
                user.setBankName(rs.getString("BankName").trim());
                user.setBankCardNo(rs.getString("BankCardNo").trim());
                Date date = rs.getDate("JoinDate");
                if(date ==null)
                    user.setJoinDate("");
                else{
                    user.setJoinDate(ContextUtils.dateToStrShort(date));
                }

                date = rs.getDate("WorkDate");
                if(date ==null)
                    user.setWorkDate("");
                else{
                    user.setWorkDate(ContextUtils.dateToStrShort(date));
                }
                date = rs.getDate("LevelDate");
                if(date ==null)
                    user.setLevelDate("");
                else{
                    user.setLevelDate(ContextUtils.dateToStrShort(date));
                }
                user.setBaseSalary(rs.getFloat("BaseSalary"));
                user.setDegreeSalary(rs.getFloat("DegreeSalary"));
                user.setAddr(rs.getString("addr").trim());
                user.setStatus(rs.getInt("status"));
                user.setRemark(rs.getString("remark").trim());
            }else{
                user=null;
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;

    }
    //--------------------------单位表

    //分页单位表
    public List<Unit> findByPage4(int pageSize, int curPage) {
        List<Unit> list = new ArrayList<Unit>();
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();
        //计算起始位置
        int startPos = (curPage - 1) * pageSize;
        try {
            Statement stmt = conn.createStatement();
            String sql = "select top " + pageSize + " * from Unit where unitId not in(";
            sql += "select top " + startPos + " unitId from Unit ";
            sql += ") order by unitId";
            System.out.println("sql=" + sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Unit s = new Unit();
                s.setUnitId(rs.getInt("unitId"));
                s.setUnitName(rs.getString("unitName"));
                list.add(s);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //根据单位号删除单位资料
    public void del4(int unitId){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "delete degrees where unitId="+unitId;
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //查询单位表
    public List<Unit> findunit4(){
        List<Unit> list=new ArrayList<>();
        Connection connection=DBConn.openDB();
        String sql="select * from Unit";
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                Unit unit=new Unit();
                unit.setUnitId(resultSet.getInt("unitId"));
                unit.setUnitName(resultSet.getString("unitName"));
                list.add(unit);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    //新增单位资料
    public void save4(Unit d){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "insert into Unit values('";
            sql += d.getUnitName() +"')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //通过单位号查询一个单位的信息
    public Unit findByStudId4(int unitId){
        Connection conn = DBConn.openDB();
        Unit unit = new Unit();

        try{
            Statement stmt = conn.createStatement();
            String sql = "select * from Unit where unitId="+unitId;
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                unit.setUnitId(rs.getInt("unitId"));
                unit.setUnitName(rs.getString("unitName").trim());
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return unit;

    }
    //修改单位资料
    public void update4(Unit d){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "update Unit set unitName='";
            sql += d.getUnitName() +"'";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

        //根据供应商删除供应商资料
        public void supplier5(String supplierId){
            Connection conn = DBConn.openDB();
            try{
                Statement stmt = conn.createStatement();
                String sql = "delete supplier where supplierId="+supplierId;
                stmt.executeUpdate(sql);
                stmt.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        //查询供应商表
        public List<supplier> findsupplier5(){
            List<supplier> list=new ArrayList<>();
            Connection connection=DBConn.openDB();
            String sql="select*from supplier";
            try {
                Statement statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery(sql);
                while (resultSet.next()){
                    supplier supplier=new supplier();
                    supplier.setSupplierId(resultSet.getInt("supplierId"));
                    supplier.setSupplierName(resultSet.getString("supplierName"));
                    supplier.setBankAccount(resultSet.getString("bankAccount"));
                    supplier.setBankName(resultSet.getString("bankName"));
                    supplier.setContact(resultSet.getString("contact"));
                    supplier.setPhone(resultSet.getString("phone"));
                    supplier.setAddr(resultSet.getString("addr"));
                    supplier.setRemark(resultSet.getString("remark"));
                    list.add(supplier);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            return list;
        }
        //新增供应商资料
        public void save5(supplier d){
            Connection conn = DBConn.openDB();
            try{
                Statement stmt = conn.createStatement();
                String sql = "insert into supplier values('";
                sql += d.getSupplierName() +"','";
                sql += d.getBankAccount() +"','";
                sql += d.getBankName() +"','";
                sql += d.getContact() +"','";
                sql += d.getPhone() +"','";
                sql += d.getAddr() +"','";
                sql += d.getRemark()+"')";
                System.out.println(sql);
                stmt.executeUpdate(sql);
                stmt.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        //通过供应商号查询一个部门的信息
        public supplier findByStudId5(String supplierId){
            Connection conn = DBConn.openDB();
            supplier supplier = new supplier();

            try{
                Statement stmt = conn.createStatement();
                String sql = "select * from supplier where supplierId="+supplierId;
                ResultSet rs = stmt.executeQuery(sql);
                if(rs.next()){
                    supplier.setSupplierId(rs.getInt("supplierId"));
                    supplier.setSupplierName(rs.getString("supplierName").trim());
                    supplier.setBankAccount(rs.getString("bankAccount").trim());
                    supplier.setBankName(rs.getString("bankName").trim());
                    supplier.setContact(rs.getString("contact").trim());
                    supplier.setPhone(rs.getString("phone").trim());
                    supplier.setAddr(rs.getString("addr").trim());
                    supplier.setRemark(rs.getString("remark").trim());
                }
                rs.close();
                stmt.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            return supplier;

        }
        //修改供应商资料
        public void update5(supplier d){
            Connection conn = DBConn.openDB();
            try{
                Statement stmt = conn.createStatement();
                String sql = "update supplier set supplierName='";
                sql += d.getSupplierName() +"',bankAccount='";
                sql += d.getBankAccount() +"',bankName='";
                sql += d.getBankName() +"',contact='";
                sql += d.getContact() +"',phone='";
                sql += d.getPhone() +"',addr='";
                sql += d.getAddr() +"',remark='";
                sql += d.getRemark()+"' where supplierId="+d.getSupplierId();
                System.out.println(sql);
                stmt.executeUpdate(sql);
                stmt.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        //统计记录总数
        public int findCount5(){
            int cnt=0;
            //创建Connection对象
//		Connection conn = DBPool.openDB();
            Connection conn = DBConn.openDB();

            try{
                Statement stmt = conn.createStatement();
                String sql = "select count(*) cnt from supplier";
                ResultSet rs = stmt.executeQuery(sql);
                if(rs.next()){
                    cnt = rs.getInt("cnt");
                }
                rs.close();
                stmt.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            return cnt;
        }
        //分页查询供应商资料
        public List<supplier> findByPage5(int pageSize,int curPage){

            List<supplier> list = new ArrayList<supplier>();
            //创建Connection对象
//		Connection conn = DBPool.openDB();
            Connection conn = DBConn.openDB();
            //计算起始位置
            int startPos = (curPage-1)*pageSize;
            try{
                Statement stmt = conn.createStatement();
                String sql = "select top "+pageSize+" * from supplier where supplierId not in(";
                sql += "select top "+startPos+" supplierId from supplier";
                sql +=")";
                System.out.println("sql="+sql);
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    supplier s = new supplier();
                    s.setSupplierId(rs.getInt("supplierId"));
                    s.setSupplierName(rs.getString("supplierName").trim());
                    s.setBankAccount(rs.getString("bankAccount").trim());
                    s.setBankName(rs.getString("bankName").trim());
                    s.setContact(rs.getString("contact").trim());
                    s.setPhone(rs.getString("phone").trim());
                    s.setAddr(rs.getString("addr").trim());
                    s.setRemark(rs.getString("remark").trim());
                    list.add(s);
                }
                rs.close();
                stmt.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            return list;
        }


        //商品表

    //根据商品表删除供应商资料
    public void del6(String prodid){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "delete Product where prodid='"+prodid+"'";
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //查询商品表
    public List<Product> findproduct6(){
        List<Product> list=new ArrayList<>();
        Connection connection=DBConn.openDB();
        String sql="select * from Product";
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                Product product=new Product();
                product.setProdid(resultSet.getString("prodid"));
                product.setProdname(resultSet.getString("prodname"));
                product.setProdModel(resultSet.getString("prodModel"));
                product.setProdUnit(resultSet.getInt("prodUnit"));
                product.setProdStyle(resultSet.getString("prodStyle"));
                product.setProdCount(resultSet.getFloat("prodCount"));
                product.setInPrice(resultSet.getFloat("inPrice"));
                product.setSalePrice(resultSet.getFloat("salePrice"));
                product.setLowSalePrice(resultSet.getFloat("lowSalePrice"));
                product.setProdSerial(resultSet.getString("prodModel"));
                product.setProdModel(resultSet.getString("prodSerial"));
                product.setCdKey(resultSet.getString("cdKey"));
                product.setSaleStatus(resultSet.getString("saleStatus"));
                product.setSupplierId(resultSet.getInt("supplierId"));
                product.setRemark(resultSet.getString("remark"));
                list.add(product);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    //新增商品表
    public void save6(Product d){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "insert into Product values('";
            sql+=d.getProdid()+"','";
            sql += d.getProdname() +"','";
            sql += d.getProdModel()+"',";
            sql += d.getProdUnit() +",'";
            sql += d.getProdStyle() +"',";
            sql += d.getProdCount() +",";
            sql += d.getInPrice() +",";
            sql += d.getSalePrice() +",";
            sql += d.getLowSalePrice() +",'";
            sql += d.getProdSerial() +"','";
            sql += d.getCdKey() +"','";
            sql += d.getSaleStatus() +"',";
            sql += d.getSupplierId() +",'";
            sql += d.getRemark()+"')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //通过商品表查询一个商品的信息
    public Product findByStudId6(String prodid){
        Connection conn = DBConn.openDB();
        Product product = new Product();

        try{
            Statement stmt = conn.createStatement();
            String sql = "select * from Product where prodid='"+prodid+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                product.setProdid(rs.getString("prodid"));
                product.setProdname(rs.getString("prodname").trim());
                product.setProdModel(rs.getString("prodModel").trim());
                product.setProdUnit(rs.getInt("prodUnit"));
                product.setProdStyle(rs.getString("prodStyle").trim());
                product.setProdCount(rs.getFloat("prodCount"));
                product.setInPrice(rs.getFloat("inPrice"));
                product.setSalePrice(rs.getFloat("salePrice"));
                product.setLowSalePrice(rs.getFloat("lowSalePrice"));
                product.setProdSerial(rs.getString("prodSerial").trim());
                product.setCdKey(rs.getString("cdKey").trim());
                product.setSaleStatus(rs.getString("saleStatus").trim());
                product.setSupplierId(rs.getInt("supplierId"));
                product.setRemark(rs.getString("remark").trim());
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
       return product;

    }
    //修改商品表资料
    public void update6(Product d){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "update Product set prodname='";
            sql += d.getProdname() +"',prodModel='";
            sql += d.getProdModel() +"',prodUnit=";
            sql += d.getProdUnit() +",prodStyle='";
            sql += d.getProdStyle() +"',prodCount=";
            sql += d.getProdCount() +",inPrice=";
            sql += d.getInPrice() +",salePrice=";
            sql += d.getSalePrice() +",lowSalePrice=";
            sql += d.getLowSalePrice() +",prodSerial='";
            sql += d.getProdSerial() +"',cdKey='";
            sql += d.getCdKey() +"',saleStatus='";
            sql += d.getSaleStatus() +"',supplierId=";
            sql += d.getSupplierId() +",remark='";
            sql += d.getRemark()+"' where prodid="+d.getProdid();
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //统计记录总数
    public int findCount6(){
        int cnt=0;
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();

        try{
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from Product";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                cnt = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return cnt;
    }
    //分页查询商品表资料
    public List<Product> findByPage6(int pageSize,int curPage){

        List<Product> list = new ArrayList<Product>();
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();
        //计算起始位置
        int startPos = (curPage-1)*pageSize;
        try{
            Statement stmt = conn.createStatement();
            String sql = "select top "+pageSize+" * from product p inner join Unit u on p.prodUnit = u.unitid inner join supplier s on s.supplierId = p.supplierId where prodid not in(";
            sql += "select top "+startPos+" prodid from Product";
            sql +=") order by prodid";
            System.out.println("sql="+sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Product s = new Product();
                s.setProdid(rs.getString("prodid"));
                s.setProdname(rs.getString("prodname").trim());
                s.setProdModel(rs.getString("prodModel").trim());
                s.setProdUnit(rs.getInt("prodUnit"));
                s.setProdStyle(rs.getString("prodStyle").trim());
                s.setProdCount(rs.getFloat("prodCount"));
                s.setInPrice(rs.getFloat("inPrice"));
                s.setSalePrice(rs.getFloat("salePrice"));
                s.setLowSalePrice(rs.getFloat("lowSalePrice"));
                s.setProdSerial(rs.getString("prodserial"));
                s.setCdKey(rs.getString("cdKey").trim());
                s.setSaleStatus(rs.getString("saleStatus").trim());
                s.setSupplierId(rs.getInt("supplierId"));
                s.setRemark(rs.getString("remark").trim());
                s.setUnit(rs.getString("unitname"));
                s.setSupplier(rs.getString("suppliername"));
                list.add(s);
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    //客户资料
    //根据客户id删除客户表资料
    public void del7(String custId){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "delete CustomerInfo where custId="+custId;
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //查询客户表
    public List<CustomerInfo> findcustomer7(){
        List<CustomerInfo> list=new ArrayList<>();
        Connection connection=DBConn.openDB();
        String sql="select*from CustomerInfo";
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                CustomerInfo c=new CustomerInfo();
                c.setCustId(resultSet.getInt("custId"));
                c.setCustname(resultSet.getString("custname"));
                c.setCusttype(resultSet.getString("custtype"));
                c.setBankAccount(resultSet.getString("bankAccount"));
                c.setBankName(resultSet.getString("bankName"));
                c.setContact(resultSet.getString("contact"));
                c.setPhone(resultSet.getString("phone"));
                c.setTicketName(resultSet.getString("ticketName"));
                c.setTicketAddr(resultSet.getString("ticketAddr"));
                c.setTicketTel(resultSet.getString("ticketTel"));
                c.setTaxNo(resultSet.getString("taxNo"));
                c.setCustState(resultSet.getString("custState"));
                c.setUsername(resultSet.getString("userid"));
                c.setSource(resultSet.getString("source"));
                list.add(c);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    //新增客户表
    public void save7(CustomerInfo d){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "insert into customerinfo values('";
            sql += d.getCustname() +"','";
            sql += d.getCusttype() +"','";
            sql += d.getBankAccount() +"','";
            sql += d.getBankName() +"','";
            sql += d.getContact() +"','";
            sql += d.getPhone() +"','";
            sql += d.getTicketName() +"','";
            sql += d.getTicketAddr() +"','";
            sql += d.getTicketTel() +"','";
            sql += d.getTaxNo() +"','";
            sql += d.getCustState() +"','";
            sql += d.getUsername() +"','";
            sql += d.getSource()+"')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //通过客户表查询一个客户的信息
    public CustomerInfo findByStudId7(String custId){
        Connection conn = DBConn.openDB();
        CustomerInfo  c= new CustomerInfo();

        try{
            Statement stmt = conn.createStatement();
            String sql = "select * from CustomerInfo where custId="+custId;
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                c.setCustId(rs.getInt("custId"));
                c.setCustname(rs.getString("custname").trim());
                c.setCusttype(rs.getString("custtype").trim());
                c.setBankAccount(rs.getString("bankaccount").trim());
                c.setBankName(rs.getString("bankName").trim());
                c.setContact(rs.getString("contact").trim());
                c.setPhone(rs.getString("phone").trim());
                c.setTicketName(rs.getString("ticketName").trim());
                c.setTicketAddr(rs.getString("ticketAddr").trim());
                c.setTicketTel(rs.getString("tickettel"));
                c.setTaxNo(rs.getString("taxNo").trim());
                c.setCustState(rs.getString("custState").trim());
                c.setUsername(rs.getString("userid").trim());
                c.setSource(rs.getString("source").trim());
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return c;

    }
    //修改客户表资料
    public void update7(CustomerInfo d){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "update customerinfo set custname='";
            sql += d.getCustname() +"',custtype='";
            sql += d.getCusttype() +"',bankAccount='";
            sql += d.getBankAccount() +"',bankName='";
            sql += d.getBankName() +"',contact='";
            sql += d.getContact() +"',phone='";
            sql += d.getPhone() +"',ticketName='";
            sql += d.getTicketName() +"',ticketAddr='";
            sql += d.getTicketAddr() +"',ticketTel='";
            sql += d.getTicketTel() +"',taxNo='";
            sql += d.getTaxNo() +"',custState='";
            sql += d.getCustState() +"',userid='";
            sql += d.getUsername() +"',source='";
            sql += d.getSource()+"' where custId="+d.getCustId();
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //统计记录总数
    public int findCount7(){
        int cnt=0;
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();

        try{
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from CustomerInfo";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                cnt = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return cnt;
    }


    //分页查询客户表资料
    public List<CustomerInfo> findByPage7(int pageSize,int curPage){

        List<CustomerInfo> list = new ArrayList<CustomerInfo>();
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();
        //计算起始位置
        int startPos = (curPage-1)*pageSize;
        try{
            Statement stmt = conn.createStatement();
            String sql = "select top "+pageSize+" * from customerinfo c inner join users u on u.userid = c.userid  where custId not in(";
            sql += "select top "+startPos+" custId from CustomerInfo";
            sql +=")";
            System.out.println("sql="+sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                CustomerInfo s = new CustomerInfo();
                s.setCustId(rs.getInt("custId"));
                s.setCustname(rs.getString("custname").trim());
                s.setCusttype(rs.getString("custtype").trim());
                s.setBankAccount(rs.getString("bankAccount").trim());
                s.setBankName(rs.getString("bankName").trim());
                s.setContact(rs.getString("contact").trim());
                s.setPhone(rs.getString("phone").trim());
                s.setTicketName(rs.getString("ticketName").trim());
                s.setTicketAddr(rs.getString("ticketAddr").trim());
                s.setTicketTel(rs.getString("ticketTel").trim());
                s.setTaxNo(rs.getString("taxNo").trim());
                s.setCustState(rs.getString("custState").trim());
                s.setUsername(rs.getString("userid").trim());
                s.setSource(rs.getString("source").trim());
                s.setUsers(rs.getString("username"));
                list.add(s);
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }


    //-----------------------------------合同表-------------------


    //根据合同id删除合同表资料
    public void del8(String contract_id){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "delete contract where contract_id="+contract_id;
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //查询合同表
    public List<contract> findcontract8(){
        List<contract> list=new ArrayList<>();
        Connection connection=DBConn.openDB();
        String sql="select*from contract";
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                contract c=new contract();
                c.setContract_id(resultSet.getInt("contract_id"));
                c.setContract_no(resultSet.getString("contract_no"));
                c.setCustId(resultSet.getInt("custId"));
                c.setContract_time(resultSet.getString("contract_time"));
                c.setDue_time(resultSet.getString("due_time"));
                c.setTotal_money(resultSet.getString("total_money"));
                c.setDeposit(resultSet.getString("deposit"));
                c.setTotal_money(resultSet.getString("total_money"));
                c.setPay_type(resultSet.getString("pay_type"));
                c.setStatus(resultSet.getString("status"));
                c.setEmpid(resultSet.getInt("empid"));
                c.setOperator(resultSet.getString("operator"));
                c.setOprtime(resultSet.getString("oprtime"));
                list.add(c);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    //新增合同表
    public void save8(contract d){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "insert into contract values('";
            sql += d.getContract_no() +"',";
            sql += d.getCustId() +",'";
            sql += d.getContract_time() +"','";
            sql += d.getDue_time() +"','";
            sql += d.getTotal_money() +"','";
            sql += d.getDeposit() +"','";
            sql+=d.getPay_type()+"','";
            sql += d.getStatus() +"',";
            sql += d.getEmpid() +",'";
            sql += d.getOperator() +"','";
            sql += d.getOprtime()+"')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //通过订单表查询一个订单的信息
    public contract findByStudId8(String contract_id){
        Connection conn = DBConn.openDB();
        contract  c= new contract();
        try{
            Statement stmt = conn.createStatement();
            String sql = "select * from contract where contract_id="+contract_id;
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                c.setContract_id(rs.getInt("contract_id"));
                c.setContract_no(rs.getString("contract_no").trim());
                c.setCustId(rs.getInt("custId"));
                c.setContract_time(rs.getString("contract_time").trim());
                c.setDue_time(rs.getString("due_time").trim());
                c.setTotal_money(rs.getString("total_money").trim());
                c.setDeposit(rs.getString("deposit").trim());
                c.setPay_type(rs.getString("pay_type").trim());
                c.setStatus(rs.getString("status").trim());
                c.setEmpid(rs.getInt("empid"));
                c.setOperator(rs.getString("operator").trim());
                c.setOprtime(rs.getString("oprtime").trim());
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return c;

    }
    //修改合同表资料
    public void update8(contract d){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "update contract set contract_no='";
            sql += d.getContract_no() +"',custId=";
            sql += d.getCustId() +",contract_time='";
            sql += d.getContract_time() +"',due_time='";
            sql += d.getDue_time() +"',total_money='";
            sql += d.getTotal_money() +"',deposit='";
            sql += d.getDeposit() +"',pay_type='";
            sql += d.getPay_type() +"',status='";
            sql += d.getStatus() +"',empid='";
            sql += d.getEmpid() +"' where contract_id="+d.getContract_id();
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //统计记录总数
    public int findCount8(){
        int cnt=0;
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();

        try{
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from contract";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                cnt = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return cnt;
    }


    //分页查询合同表资料
    public List<contract> findByPage8(int pageSize,int curPage){

        List<contract> list = new ArrayList<contract>();
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();
        //计算起始位置
        int startPos = (curPage-1)*pageSize;
        try{
            Statement stmt = conn.createStatement();
            String sql = "select top "+pageSize+" * from contract c inner join customerinfo t on c.custId = t.custId inner join users u on u.userid = c.empid  where contract_id not in(";
            sql += "select top "+startPos+" contract_id from contract";
            sql +=") order by contract_id";
            System.out.println("sql="+sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                contract s = new contract();
                s.setContract_id(rs.getInt("contract_id"));
                s.setContract_no(rs.getString("contract_no"));
                s.setCustId(rs.getInt("custId"));
                s.setContract_time(rs.getString("contract_time").trim());
                s.setDue_time(rs.getString("due_time").trim());
                s.setTotal_money(rs.getString("total_money").trim());
                s.setPay_type(rs.getString("pay_type").trim());
                s.setDeposit(rs.getString("deposit"));
                s.setStatus(rs.getString("status").trim());
                s.setEmpid(rs.getInt("empid"));
                s.setOperator(rs.getString("operator").trim());
                s.setOprtime(rs.getString("oprtime").trim());
                s.setCustomer(rs.getString("custname"));
                s.setUsername(rs.getString("username"));
                list.add(s);
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public List<contract> findByPages8(int pageSize,int curPage,int custid){

        List<contract> list = new ArrayList<contract>();
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();
        //计算起始位置
        int startPos = (curPage-1)*pageSize;
        try{
            Statement stmt = conn.createStatement();
            String sql = "select top "+pageSize+" * from contract c inner join customerinfo t on c.custId = t.custId inner join users u on u.userid = c.empid  where contract_id not in(";
            sql += "select top "+startPos+" contract_id from contract";
            sql +=") and t.custid = "+custid;
            System.out.println("sql="+sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                contract s = new contract();
                s.setContract_id(rs.getInt("contract_id"));
                s.setContract_no(rs.getString("contract_no"));
                s.setCustId(rs.getInt("custId"));
                s.setContract_time(rs.getString("contract_time").trim());
                s.setDue_time(rs.getString("due_time").trim());
                s.setTotal_money(rs.getString("total_money").trim());
                s.setPay_type(rs.getString("pay_type").trim());
                s.setDeposit(rs.getString("deposit"));
                s.setStatus(rs.getString("status").trim());
                s.setEmpid(rs.getInt("empid"));
                s.setOperator(rs.getString("operator").trim());
                s.setOprtime(rs.getString("oprtime").trim());
                s.setCustomer(rs.getString("custname"));
                s.setUsername(rs.getString("username"));
                list.add(s);
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    //---------------------------------合同附件表------------------------
    //删除合同附件表
    public void del9(String contractAttach_id) {
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "delete contractAttach where contractAttach_id=" + contractAttach_id;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询合同附件表
    public List<contractAttach> findcontract9(String id) {
        List<contractAttach> list = new ArrayList<>();
        Connection connection = DBConn.openDB();
        String sql = "select * from contractAttach where contract_id = "+id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                contractAttach c = new contractAttach();
                c.setContractAttach_id(resultSet.getInt("contractAttach_id"));
                c.setContract_id(resultSet.getInt("contract_id"));
                c.setSeq(resultSet.getInt("Seq"));
                c.setAttachFile(resultSet.getString("AttachFile"));
                c.setUploadTime(resultSet.getString("UploadTime"));
                c.setUser_name(resultSet.getString("user_name"));
                c.setAttachURL(resultSet.getString("attachURL"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //新增合同附件表
    public void save9(contractAttach d) {
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "insert into contractAttach values(";
            sql += d.getContract_id() + ",";
            sql += d.getSeq() + ",'";
            sql += d.getAttachFile() + "','";
            sql += d.getUploadTime() + "','";
            sql += d.getUser_name() + "','";
            sql += d.getAttachURL() + "')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //通过合同附件表id查询一个合同附件表
    public contractAttach findByStudId9(String contractAttach_id) {
        Connection conn = DBConn.openDB();
        contractAttach c = new contractAttach();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from contractAttach where contractAttach_id=" + contractAttach_id;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                c.setContractAttach_id(rs.getInt("contractAttach_id"));
                c.setContract_id(rs.getInt("contract_id"));
                c.setSeq(rs.getInt("Seq"));
                c.setAttachFile(rs.getString("AttachFile"));
                c.setUploadTime(rs.getString("UploadTime"));
                c.setUser_name(rs.getString("user_name"));
                c.setAttachURL(rs.getString("attachURL"));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;

    }

    //修改合同附件表
    public void update9(contractAttach d) {
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "update contractAttach set contract_id=";
            sql += d.getContract_id() + ",Seq=";
            sql += d.getSeq() + ",AttachFile='";
            sql += d.getAttachFile() + "',user_name='";
            sql += d.getUser_name() + "',attachURL='";
            sql += d.getAttachURL() + "' where contractAttach_id=" + d.getContractAttach_id();
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //统计记录总数
    public int findCount9() {
        int cnt = 0;
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();

        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from contractAttach";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                cnt = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnt;
    }


    //分页合同附件表
    public List<contractAttach> findByPage9(int pageSize, int curPage,int contract_id) {
        List<contractAttach> list = new ArrayList<contractAttach>();
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();
        //计算起始位置
        int startPos = (curPage - 1) * pageSize;
        try {
            Statement stmt = conn.createStatement();
            String sql = "select top " + pageSize + " * from contractAttach where contractAttach_id not in(";
            sql += "select top " + startPos + " contractAttach_id from contractAttach";
            sql += ") and contract_id = "+contract_id;
            System.out.println("sql=" + sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                contractAttach s = new contractAttach();
                s.setContractAttach_id(rs.getInt("contractAttach_id"));
                s.setContract_id(rs.getInt("contract_id"));
                s.setSeq(rs.getInt("Seq"));
                s.setAttachFile(rs.getString("AttachFile"));
                s.setUploadTime(rs.getString("UploadTime"));
                s.setUser_name(rs.getString("user_name"));
                s.setAttachURL(rs.getString("attachURL"));
                list.add(s);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    //------------------------

    //根据订单id删除订单表资料
    public void del10(String orderId){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "delete Orders where orderId="+orderId;
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //查询订单表
    public List<Orders> findorders10(){
        List<Orders> list=new ArrayList<>();
        Connection connection=DBConn.openDB();
        String sql="select*from Orders";
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                Orders c=new Orders();
                c.setOrderId(resultSet.getInt("orderId"));
                c.setCustid(resultSet.getInt("custid"));
                c.setUserid(resultSet.getInt("userid"));
                c.setOrderType(resultSet.getString("orderType"));
                c.setOrderStatus(resultSet.getString("orderStatus"));
                c.setProcess(resultSet.getString("process"));
                c.setTotalMoney(resultSet.getFloat("totalMoney"));
                c.setOprtime(resultSet.getString("oprtime"));
                c.setOperator(resultSet.getString("operator"));
                c.setRemark(resultSet.getString("remark"));
                list.add(c);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    //新增订单表
    public void save10(Orders d){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "insert into Orders values('";
            sql+=d.getOrderId()+"',";
            sql += d.getCustid() +",";
            sql += d.getUserid() +",'";
            sql += d.getOrderType() +"','";
            sql += d.getOrderStatus() +"','";
            sql += d.getProcess() +"',";
            sql += d.getTotalMoney() +",'";
            sql += d.getOprtime() +"','";
            sql += d.getOperator() +"','";
            sql += d.getRemark()+"')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //通过订单表查询一个订单的信息
    public Orders findByStudId10(String orderId){
        Connection conn = DBConn.openDB();
        Orders  c= new Orders();

        try{
            Statement stmt = conn.createStatement();
            String sql = "select * from orders o inner join customerinfo c on o.custid = c.custId where orderId="+orderId;
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                c.setOrderId(rs.getInt("orderId"));
                c.setCustid(rs.getInt("custid"));
                c.setUserid(rs.getInt("userid"));
                c.setOrderType(rs.getString("orderType").trim());
                c.setOrderStatus(rs.getString("orderStatus").trim());
                c.setProcess(rs.getString("process").trim());
                c.setTotalMoney(rs.getFloat("totalMoney"));
                c.setOprtime(rs.getString("oprtime").trim());
                c.setOperator(rs.getString("operator").trim());
                c.setRemark(rs.getString("remark").trim());
                c.setCustomername(rs.getString("custname"));
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return c;

    }
    //修改订单表资料
    public void update10(Orders d){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "update Orders set custid=";
            sql += d.getCustid() +",userid=";
            sql += d.getUserid() +",orderType='";
            sql += d.getOrderType() +"',orderStatus='";
            sql += d.getOrderStatus() +"',process='";
            sql += d.getProcess() +"',totalMoney=";
            sql += d.getTotalMoney() +",oprtime='";
            sql += d.getOprtime() +"',operator='";
            sql += d.getOperator() +"',remark='";
            sql += d.getRemark()+"' where orderId="+d.getOrderId();
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //统计记录总数
    public int findCount10(){
        int cnt=0;
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();

        try{
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from Orders";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                cnt = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return cnt;
    }


    //分页查询订单表资料
    public List<Orders> findByPage10(int pageSize,int curPage){

        List<Orders> list = new ArrayList<Orders>();
        //创建Connection对象
        Connection conn = DBConn.openDB();
        //计算起始位置
        int startPos = (curPage-1)*pageSize;
        try{
            Statement stmt = conn.createStatement();
            String sql = "select top "+pageSize+" * from orders o inner join customerinfo c on o.custid = c.custId inner join users u on u.userid = o.userid where orderId not in(";
            sql += "select top "+startPos+" orderId from Orders";
            sql +=")";
            System.out.println("sql="+sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Orders s = new Orders();
                s.setOrderId(rs.getInt("orderId"));
                s.setCustid(rs.getInt("custid"));
                s.setUserid(rs.getInt("userid"));
                s.setOrderType(rs.getString("orderType").trim());
                s.setOrderStatus(rs.getString("orderStatus").trim());
                s.setProcess(rs.getString("process").trim());
                s.setTotalMoney(rs.getFloat("totalMoney"));
                s.setOprtime(rs.getString("oprtime").trim());
                s.setOperator(rs.getString("operator").trim());
                s.setRemark(rs.getString("remark").trim());
                s.setCustomername(rs.getString("custname"));
                s.setUsername(rs.getString("username"));
                list.add(s);
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<Orders> findByPage10(int pageSize,int curPage,int custid){

        List<Orders> list = new ArrayList<Orders>();
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();
        //计算起始位置
        int startPos = (curPage-1)*pageSize;
        try{
            Statement stmt = conn.createStatement();
            String sql = "select top "+pageSize+" * from orders o inner join customerinfo c on o.custid = c.custId inner join users u on u.userid = o.userid where orderId not in(";
            sql += "select top "+startPos+" orderId from Orders";
            sql +=")";
            if(custid > 0){
                sql+= " and c.custid = "+custid;
            }
            sql+=" order by orderId desc";
            System.out.println("sql="+sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Orders s = new Orders();
                s.setOrderId(rs.getInt("orderId"));
                s.setCustid(rs.getInt("custid"));
                s.setUserid(rs.getInt("userid"));
                s.setOrderType(rs.getString("orderType").trim());
                s.setOrderStatus(rs.getString("orderStatus").trim());
                s.setProcess(rs.getString("process").trim());
                s.setTotalMoney(rs.getFloat("totalMoney"));
                s.setOprtime(rs.getString("oprtime").trim());
                s.setOperator(rs.getString("operator").trim());
                s.setRemark(rs.getString("remark").trim());
                s.setCustomername(rs.getString("custname"));
                s.setUsername(rs.getString("username"));
                list.add(s);
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    //----------------------------------------订单明细表


    //根据订单id删除订单明细表资料
    public void del11(String DetailId) {
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "delete OrderDetail where DetailId=" + DetailId;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询订单表
    public List<OrderDetail> findorderDetail11(String orderid) {
        List<OrderDetail> list = new ArrayList<>();
        Connection connection = DBConn.openDB();
        String sql = "select*from OrderDetail where orderid = "+orderid;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                OrderDetail c = new OrderDetail();
                c.setDetailId(resultSet.getInt("DetailId"));
                c.setOrderId(resultSet.getString("orderId"));
                c.setProdid(resultSet.getString("prodid"));
                c.setStatus(resultSet.getString("status"));
                c.setSaleMoney(resultSet.getFloat("saleMoney"));
                c.setUnitId(resultSet.getInt("UnitId"));
                c.setRegPerson(resultSet.getString("regPerson"));
                c.setRegPassword(resultSet.getString("regPassword"));
                c.setServicePeriod(resultSet.getString("servicePeriod"));
                c.setExpireDate(resultSet.getString("expireDate"));
                c.setProdCount(resultSet.getInt("prodCount"));
                c.setTotalMoney(resultSet.getFloat("totalMoney"));
                c.setOprtime(resultSet.getString("oprtime"));
                c.setOperator(resultSet.getString("operator"));
                c.setRemark(resultSet.getString("remark"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //新增订单明细表
    public void save11(OrderDetail d) {
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "insert into OrderDetail values('";
            sql += d.getOrderId() + "','";
            sql += d.getProdid() + "','";
            sql += d.getStatus() + "',";
            sql += d.getSaleMoney() + ",";
            sql += d.getUnitId() + ",'";
            sql += d.getRegPerson() + "','";
            sql += d.getRegPassword() + "','";
            sql += d.getServicePeriod() + "','";
            sql += d.getExpireDate() + "',";
            sql += d.getProdCount() + ",";
            sql += d.getTotalMoney() + ",'";
            sql += d.getOprtime() + "','";
            sql += d.getOperator() + "','";
            sql += d.getRemark() + "')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //通过明细订单表查询一个明细订单的信息
    public OrderDetail findByStudId11(String DetailId) {
        Connection conn = DBConn.openDB();
        OrderDetail c = new OrderDetail();

        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from OrderDetail where DetailId=" + DetailId;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                c.setDetailId(rs.getInt("detailId"));
                c.setOrderId(rs.getString("orderId"));
                c.setProdid(rs.getString("prodid"));
                c.setStatus(rs.getString("status").trim());
                c.setSaleMoney(rs.getFloat("saleMoney"));
                c.setUnitId(rs.getInt("UnitId"));
                c.setRegPerson(rs.getString("regPerson"));
                c.setRegPassword(rs.getString("regPassword"));
                c.setServicePeriod(rs.getString("servicePeriod"));
                c.setExpireDate(rs.getString("expireDate"));
                c.setProdCount(rs.getInt("prodCount"));
                c.setTotalMoney(rs.getFloat("totalMoney"));
                c.setOprtime(rs.getString("oprtime").trim());
                c.setOperator(rs.getString("operator").trim());
                c.setRemark(rs.getString("remark").trim());
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;

    }

    //修改明细订单表资料
    public void update11(OrderDetail d) {
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "update OrderDetail set orderId='";
            sql += d.getOrderId() + "',prodid='";
            sql += d.getProdid() + "',status='";
            sql += d.getStatus() + "',saleMoney=";
            sql += d.getSaleMoney() + ",UnitId=";
            sql += d.getUnitId() + ",regPerson='";
            sql += d.getRegPerson() + "',regPassword='";
            sql += d.getRegPassword() + "',servicePeriod='";
            sql += d.getServicePeriod() + "',expireDate='";
            sql += d.getExpireDate() + "',prodCount='";
            sql += d.getProdCount() + "',totalMoney=";
            sql += d.getTotalMoney() + ",oprtime='";
            sql += d.getOprtime() + "',operator='";
            sql += d.getOperator() + "',remark='";
            sql += d.getRemark() + "' where DetailId=" + d.getDetailId();
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //统计记录总数
    public int findCount11() {
        int cnt = 0;
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();

        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from OrderDetail";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                cnt = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnt;
    }


    //分页查询明细订单表资料
    public List<OrderDetail> findByPage11(int pageSize, int curPage ,int orderid) {

        List<OrderDetail> list = new ArrayList<OrderDetail>();
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();
        //计算起始位置
        int startPos = (curPage - 1) * pageSize;
        try {
            Statement stmt = conn.createStatement();
            String sql = "select top " + pageSize + " * from OrderDetail o inner join product p on o.prodid = p.prodid where DetailId not in(";
            sql += "select top " + startPos + " DetailId from OrderDetail";
            sql += ") and orderid = "+orderid;
            System.out.println("sql=" + sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                OrderDetail s = new OrderDetail();
                s.setDetailId(rs.getInt("DetailId"));
                s.setOrderId(rs.getString("orderId"));
                s.setProdid(rs.getString("prodid"));
                s.setStatus(rs.getString("status").trim());
                s.setSaleMoney(rs.getFloat("saleMoney"));
                s.setUnitId(rs.getInt("UnitId"));
                s.setRegPerson(rs.getString("regPerson").trim());
                s.setRegPassword(rs.getString("regPassword").trim());
                s.setServicePeriod(rs.getString("servicePeriod").trim());
                s.setExpireDate(rs.getString("expireDate").trim());
                s.setProdCount(rs.getInt("prodCount"));
                s.setTotalMoney(rs.getFloat("totalMoney"));
                s.setOprtime(rs.getString("oprtime").trim());
                s.setOperator(rs.getString("operator").trim());
                s.setRemark(rs.getString("remark").trim());
                s.setProdname(rs.getString("prodname"));
                list.add(s);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //----------------------------------派工单------------------------
    //根据派工单删除订配工单资料
    public void del12(String jobId){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "delete jobRecord where jobId="+jobId;
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //查询派工单表
    public List<jobRecord> findjobrecord12(){
        List<jobRecord> list=new ArrayList<>();
        Connection connection=DBConn.openDB();
        String sql="select*from jobRecord";
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                jobRecord c=new jobRecord();
                c.setJobId(resultSet.getInt("jobId"));
                c.setOrderId(resultSet.getString("orderId"));
                c.setJobDate(resultSet.getString("jobDate"));
                c.setProdName(resultSet.getString("prodName"));
                c.setCustid(resultSet.getInt("custid"));
                c.setJobContent(resultSet.getString("jobContent"));
                c.setCallback(resultSet.getString("callback"));
                c.setUserid(resultSet.getString("userid"));
                c.setCustEval(resultSet.getString("custEval"));
                c.setCustSign(resultSet.getString("custSign"));
                c.setStartTime(resultSet.getString("startTime"));
                c.setEndTime(resultSet.getString("endTime"));
                c.setWorkDay(resultSet.getInt("workDay"));
                c.setBusMoney(resultSet.getFloat("busMoney"));
                c.setAttachMoney(resultSet.getFloat("attachMoney"));
                list.add(c);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    //新增派工单
    public void save12(jobRecord d){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "insert into jobRecord values(";
            sql += d.getOrderId() +",'";
            sql += d.getJobDate() +"','";
            sql += d.getProdName() +"',";
            sql += d.getCustid() +",'";
            sql += d.getJobContent() +"','";
            sql += d.getCallback() +"',";
            sql += d.getUserid() +",'";
            sql += d.getCustEval() +"','";
            sql += d.getCustSign() +"','";
            sql += d.getStartTime() +"','";
            sql += d.getEndTime() +"','";
            sql += d.getWorkDay() +"',";
            sql += d.getBusMoney() +",";
            sql += d.getAttachMoney()+")";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //通过派工单查询一个派工单的信息
    public jobRecord findByStudId12(String jobId){
        Connection conn = DBConn.openDB();
        jobRecord  c= new jobRecord();

        try{
            Statement stmt = conn.createStatement();
            String sql = "select * from jobRecord where jobId="+jobId;
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                c.setJobId(rs.getInt("jobId"));
                c.setOrderId(rs.getString("orderId"));
                c.setJobDate(rs.getString("jobDate"));
                c.setProdName(rs.getString("prodName").trim());
                c.setCustid(rs.getInt("custid"));
                c.setJobContent(rs.getString("jobContent"));
                c.setCallback(rs.getString("callback"));
                c.setUserid(rs.getString("userid"));
                c.setCustEval(rs.getString("custEval"));
                c.setCustSign(rs.getString("custSign"));
                c.setStartTime(rs.getString("startTime").trim());
                c.setEndTime(rs.getString("endTime").trim());
                c.setWorkDay(rs.getInt("workDay"));
                c.setBusMoney(rs.getFloat("busMoney"));
                c.setAttachMoney(rs.getFloat("attachMoney"));
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return c;

    }
    //修改派工单表资料
    public void update12(jobRecord d){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "update jobRecord set orderId='";
            sql += d.getOrderId() +"',jobDate='";
            sql += d.getJobDate() +"',prodName='";
            sql += d.getProdName() +"',custid=";
            sql += d.getCustid() +",jobContent='";
            sql += d.getJobContent() +"',callback='";
            sql += d.getCallback() +"',userid=";
            sql += d.getUserid() +",custEval='";
            sql += d.getCustEval() +"',custSign='";
            sql += d.getCustSign() +"',startTime='";
            sql += d.getStartTime() +"',endTime='";
            sql += d.getEndTime() +"',workDay=";
            sql += d.getWorkDay() +",busMoney='";
            sql += d.getBusMoney() +"',attachMoney='";
            sql += d.getAttachMoney()+"' where jobId="+d.getJobId();
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //统计记录总数
    public int findCount12(){
        int cnt=0;
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();

        try{
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from jobRecord";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                cnt = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return cnt;
    }


    //分页查询派工单单表资料
    public List<jobRecord> findByPage12(int pageSize,int curPage){

        List<jobRecord> list = new ArrayList<jobRecord>();
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();
        //计算起始位置
        int startPos = (curPage-1)*pageSize;
        try{
            Statement stmt = conn.createStatement();
            String sql = "select top "+pageSize+" * from JobRecord j inner join product p on j.prodName = p.prodid inner join customerinfo c on j.custid = c.custId inner join users u on u.userid = j.userid where jobId not in(";
            sql += "select top "+startPos+" jobId from jobRecord";
            sql +=") order by jobid";
            System.out.println("sql="+sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                jobRecord s = new jobRecord();
                s.setJobId(rs.getInt("jobId"));
                s.setOrderId(rs.getString("orderId"));
                s.setJobDate(rs.getString("jobDate"));
                s.setProdName(rs.getString("prodName").trim());
                s.setCustid(rs.getInt("custid"));
                s.setJobContent(rs.getString("jobContent").trim());
                s.setCallback(rs.getString("callback").trim());
                s.setUserid(rs.getString("userid"));
                s.setCustEval(rs.getString("custEval").trim());
                s.setCustSign(rs.getString("custSign"));
                s.setStartTime(rs.getString("startTime").trim());
                s.setEndTime(rs.getString("endTime").trim());
                s.setWorkDay(rs.getInt("workDay"));
                s.setBusMoney(rs.getFloat("busMoney"));
                s.setAttachMoney(rs.getFloat("attachMoney"));
                s.setProdinfor(rs.getString("prodname"));
                s.setCustname(rs.getString("custname"));
                s.setUsername(rs.getString("username"));
                list.add(s);
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }


    //----------------------------------商务洽谈表------------------------
    //删除订配工单资料
    public void del13(String businessId) {
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "delete business where businessId=" + businessId;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询商务洽谈表表
    public List<business> findbusiness13() {
        List<business> list = new ArrayList<>();
        Connection connection = DBConn.openDB();
        String sql = "select*from business";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                business c = new business();
                c.setBusinessId(resultSet.getInt("businessId"));
                c.setBusDate(resultSet.getString("busDate"));
                c.setProdName(resultSet.getString("prodName"));
                c.setChatContent(resultSet.getString("chatContent"));
                c.setChatResult(resultSet.getString("chatResult"));
                c.setCustid(resultSet.getInt("custid"));
                c.setCustContact(resultSet.getString("custContact"));
                c.setPhone(resultSet.getString("phone"));
                c.setUserid(resultSet.getInt("userid"));
                c.setModule(resultSet.getString("module"));
                c.setModuleState(resultSet.getString("moduleState"));
                c.setModuleMoney(resultSet.getFloat("moduleMoney"));
                c.setRemark(resultSet.getString("remark"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //新增商务洽谈表
    public void save13(business d) {
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "insert into business values('";
            sql += d.getBusDate() + "','";
            sql += d.getProdName() + "','";
            sql += d.getChatContent() + "','";
            sql += d.getChatResult() + "',";
            sql += d.getCustid() + ",'";
            sql += d.getCustContact() + "','";
            sql += d.getPhone() + "','";
            sql += d.getUserid() + "','";
            sql += d.getModule() + "','";
            sql += d.getModuleState() + "','";
            sql += d.getModuleMoney() + "','";
            sql += d.getRemark() + "')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //通过商业洽谈查询一个商业洽谈单的信息
    public business findByStudId13(String businessId) {
        Connection conn = DBConn.openDB();
        business c = new business();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from business where businessId=" + businessId;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                c.setBusinessId(rs.getInt("businessId"));
                c.setBusDate(rs.getString("busDate"));
                c.setProdName(rs.getString("prodName"));
                c.setChatContent(rs.getString("chatContent").trim());
                c.setChatResult(rs.getString("chatResult"));
                c.setCustid(rs.getInt("custid"));
                c.setCustContact(rs.getString("custContact"));
                c.setPhone(rs.getString("phone"));
                c.setUserid(rs.getInt("userid"));
                c.setModule(rs.getString("module"));
                c.setModuleState(rs.getString("moduleState").trim());
                c.setModuleMoney(rs.getFloat("moduleMoney"));
                c.setRemark(rs.getString("remark"));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;

    }

    //修改商业洽谈表资料
    public void update13(business d) {
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "update business set busDate='";
            sql += d.getBusDate() + "',prodName='";
            sql += d.getProdName() + "',chatContent='";
            sql += d.getChatContent() + "',chatResult='";
            sql += d.getChatResult() + "',custid=";
            sql += d.getCustid() + ",custContact='";
            sql += d.getCustContact() + "',phone='";
            sql += d.getPhone() + "',userid=";
            sql += d.getUserid() + ",module='";
            sql += d.getModule() + "',moduleState='";
            sql += d.getModuleState() + "',moduleMoney=";
            sql += d.getModuleMoney() + ",remark='";
            sql += d.getRemark() + "' where businessId=" + d.getBusinessId();
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //统计记录总数
    public int findCount13() {
        int cnt = 0;
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();

        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from business";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                cnt = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnt;
    }


    //分页商务洽谈资料
    public List<business> findByPage13(int pageSize, int curPage) {

        List<business> list = new ArrayList<business>();
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();
        //计算起始位置
        int startPos = (curPage - 1) * pageSize;
        try {
            Statement stmt = conn.createStatement();
            String sql = "select top " + pageSize + " * from Business b inner join customerinfo c on b.custid = c.custId inner join users u on u.userid = b.userid where businessId not in(";
            sql += "select top " + startPos + " businessId from business";
            sql += ") order by businessId";
            System.out.println("sql=" + sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                business s = new business();
                s.setBusinessId(rs.getInt("businessId"));
                s.setBusDate(rs.getString("busDate"));
                s.setProdName(rs.getString("prodName"));
                s.setChatContent(rs.getString("chatContent").trim());
                s.setChatResult(rs.getString("chatResult"));
                s.setCustid(rs.getInt("custid"));
                s.setCustContact(rs.getString("custContact").trim());
                s.setPhone(rs.getString("phone"));
                s.setUserid(rs.getInt("userid"));
                s.setModule(rs.getString("module"));
                s.setModuleState(rs.getString("moduleState").trim());
                s.setModuleMoney(rs.getFloat("moduleMoney"));
                s.setRemark(rs.getString("remark"));
                s.setCustname(rs.getString("custname"));
                s.setUsername(rs.getString("username"));
                list.add(s);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }







    //---------------------------客户联系表

    //根据订单id删除订单表资料
    public void del14(String contactId){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "delete contact where contactId="+contactId;
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //新增订单表
    public void save14(Contact d){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "insert into Contact values('"+d.getTalkDate()+"','"+d.getCustContact()+"','"+d.getPhone1()+"','"+d.getPhone2()+"','"+d.getCustid()+"','"+d.getQqCode()+"','"+d.getEmail()+"','"+d.getWeixin()+"','"+d.getUserid()+"','"+d.getBirthday()+"','"+d.getHobbit()+"','"+d.getJobName()+"','"+d.getRemark()+"')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //通过订单表查询一个订单的信息
    public Contact findById14(String contactid){
        Connection conn = DBConn.openDB();
        Contact  c= new Contact();

        try{
            Statement stmt = conn.createStatement();
            String sql = "select * from Contact where contactid="+contactid;
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                c.setContactId(rs.getInt("contactid"));
                c.setTalkDate(rs.getString("talkdate"));
                c.setCustContact(rs.getString("custcontact"));
                c.setPhone1(rs.getString("phone1").trim());
                c.setPhone2(rs.getString("phone2").trim());
                c.setCustid(rs.getInt("custid"));
                c.setQqCode(rs.getString("qqcode"));
                c.setEmail(rs.getString("email").trim());
                c.setWeixin(rs.getString("weixin").trim());
                c.setUserid(rs.getInt("userid"));
                c.setBirthday(rs.getString("birthday"));
                c.setHobbit(rs.getString("hobbit"));
                c.setJobName(rs.getString("jobname"));
                c.setRemark(rs.getString("remark"));
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return c;

    }
    //修改订单表资料
    public void update14(Contact d){
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
           String sql = "update Contact set talkDate = '"+d.getTalkDate()+"',custContact = '"+d.getCustContact()+"',phone1 = '"+d.getPhone1()+"',phone2 = '"+d.getPhone2()+"',custid = '"+d.getCustid()+"',qqcode = '"+d.getQqCode()+"', email = '"+d.getEmail()+"',weixin = '"+d.getWeixin()+"',userid = '"+d.getUserid()+"',birthday = '"+d.getBirthday()+"',hobbit = '"+d.getHobbit()+"',jobName = '"+d.getJobName()+"',remark = '"+d.getRemark()+"' where contactId = '"+d.getContactId()+"'";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //统计记录总数
    public int findCount14(){
        int cnt=0;
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();

        try{
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from contact";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                cnt = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return cnt;
    }


    //分页查询订单表资料
    public List<Contact> findByPage14(int pageSize,int curPage){

        List<Contact> list = new ArrayList<Contact>();
        //创建Connection对象
        Connection conn = DBConn.openDB();
        //计算起始位置
        int startPos = (curPage-1)*pageSize;
        try{
            Statement stmt = conn.createStatement();
            String sql = "select top "+pageSize+" * from Contact c inner join customerinfo u on c.custid = u.custId inner join users s on s.userid = c.userid  where  contactId not in(select top "+startPos+" contactId from Contact) order by contactId";
            System.out.println("sql="+sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Contact c = new Contact();
                c.setContactId(rs.getInt("contactid"));
                c.setTalkDate(rs.getString("talkdate"));
                c.setCustContact(rs.getString("custcontact"));
                c.setPhone1(rs.getString("phone1").trim());
                c.setPhone2(rs.getString("phone2").trim());
                c.setCustid(rs.getInt("custid"));
                c.setQqCode(rs.getString("qqcode"));
                c.setEmail(rs.getString("email").trim());
                c.setWeixin(rs.getString("weixin").trim());
                c.setUserid(rs.getInt("userid"));
                c.setBirthday(rs.getString("birthday"));
                c.setHobbit(rs.getString("hobbit"));
                c.setJobName(rs.getString("jobName"));
                c.setRemark(rs.getString("remark"));
                c.setUsersname(rs.getString("username"));
                c.setCustomer(rs.getString("custname"));
                list.add(c);
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }


    //-----------------------------------收款方式表-------------------------------
    //根据收款号删除收款方式
    public void del15(int paidtypeid) {
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "delete paidtype where paidtypeid=" + paidtypeid;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询收款方式
    public List<PaidType> findpaidtype15() {
        List<PaidType> list = new ArrayList<>();
        Connection connection = DBConn.openDB();
        String sql = "select*from paidtype";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                PaidType paidType = new PaidType();
                paidType.setPaidtypeid(resultSet.getInt("paidtypeid"));
                paidType.setPaidtypename(resultSet.getString("paidtypename"));
                list.add(paidType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //新增收款方式
    public void save15(PaidType d) {
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "insert into paidtype values('";
            sql += d.getPaidtypename() + "')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //通过收款id查询一个收款的信息
    public PaidType findByStudId15(int paidtypeid) {
        Connection conn = DBConn.openDB();
        PaidType paidType = new PaidType();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from paidtype where paidtypeid=" + paidtypeid;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                paidType.setPaidtypeid(rs.getInt("paidtypeid"));
                paidType.setPaidtypename(rs.getString("paidtypename").trim());
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paidType;

    }

    //修改收款方式
    public void update15(PaidType d) {
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "update paidtype set paidtypename='";
            sql += d.getPaidtypename() + "' where paidtypeid=" + d.getPaidtypeid();
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //统计记录总数
    public int findCount15() {
        int cnt = 0;
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();

        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from paidtype";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                cnt = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnt;
    }


    //分页收款方式
    public List<PaidType> findByPage15(int pageSize, int curPage) {
        List<PaidType> list = new ArrayList<PaidType>();
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();
        //计算起始位置
        int startPos = (curPage - 1) * pageSize;
        try {
            Statement stmt = conn.createStatement();
            String sql = "select top " + pageSize + " * from paidtype where paidtypeid not in(";
            sql += "select top " + startPos + " paidtypeid from paidtype";
            sql += ")";
            System.out.println("sql=" + sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                PaidType s = new PaidType();
                s.setPaidtypeid(rs.getInt("paidtypeid"));
                s.setPaidtypename(rs.getString("paidtypename"));
                list.add(s);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    //----------------------------------收款管理------------------------
    //删除收款管理
    public void del16(String financeId) {
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "delete finance where financeId=" + financeId;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询收款管理
    public List<finance> findfinance16() {
        List<finance> list = new ArrayList<>();
        Connection connection = DBConn.openDB();
        String sql = "select*from finance";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                finance c = new finance();
                c.setFinanceId(resultSet.getInt("financeId"));
                c.setOrderId(resultSet.getString("orderId"));
                c.setProdid(resultSet.getInt("prodid"));
                c.setPaidtypeid(resultSet.getString("paidtypeid"));
                c.setRemainMoney(resultSet.getFloat("remainMoney"));
                c.setPaidMoney(resultSet.getFloat("paidMoney"));
                c.setOrderMoney(resultSet.getFloat("orderMoney"));
                c.setPaidPerson(resultSet.getString("paidPerson"));
                c.setInbank(resultSet.getString("inbank"));
                c.setBankAccount(resultSet.getString("bankAccount"));
                c.setOutbank(resultSet.getString("outbank"));
                c.setWarrant(resultSet.getString("warrant"));
                c.setPaidTime(resultSet.getString("paidTime"));
                c.setPaidinTime(resultSet.getString("paidinTime"));
                c.setInvalid(resultSet.getString("invalid"));
                c.setUsername(resultSet.getString("username"));
                c.setOprtime(resultSet.getString("oprtime"));
                c.setOprType(resultSet.getString("oprType"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //新增收款管理
    public void save16(finance d) {
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "insert into finance values('";
            sql += d.getOrderId() + "',";
            sql += d.getProdid() + ",'";
            sql += d.getPaidtypeid() + "',";
            sql += d.getRemainMoney() + ",";
            sql += d.getPaidMoney() + ",";
            sql += d.getOrderMoney() + ",'";
            sql += d.getPaidPerson() + "','";
            sql += d.getInbank() + "','";
            sql += d.getBankAccount() + "','";
            sql += d.getOutbank() + "','";
            sql += d.getWarrant() + "','";
            sql += d.getPaidTime() + "','";
            sql += d.getPaidinTime() + "','";
            sql += d.getInvalid() + "','";
            sql += d.getUsername() + "','";
            sql += d.getOprtime() + "','";
            sql += d.getOprType() + "')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //通过收款管理查询一个收款管理
    public finance findByStudId16(String financeId) {
        Connection conn = DBConn.openDB();
        finance c = new finance();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from finance where financeId=" + financeId;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                c.setFinanceId(rs.getInt("financeId"));
                c.setOrderId(rs.getString("orderId"));
                c.setProdid(rs.getInt("prodid"));
                c.setPaidtypeid(rs.getString("paidtypeid"));
                c.setRemainMoney(rs.getFloat("remainMoney"));
                c.setPaidMoney(rs.getFloat("paidMoney"));
                c.setOrderMoney(rs.getFloat("orderMoney"));
                c.setPaidPerson(rs.getString("paidPerson"));
                c.setInbank(rs.getString("inbank"));
                c.setBankAccount(rs.getString("bankAccount"));
                c.setOutbank(rs.getString("outbank"));
                c.setWarrant(rs.getString("warrant"));
                c.setPaidTime(rs.getString("paidTime"));
                c.setPaidinTime(rs.getString("paidinTime"));
                c.setInvalid(rs.getString("invalid"));
                c.setUsername(rs.getString("username"));
                c.setOprtime(rs.getString("oprtime"));
                c.setOprType(rs.getString("oprType"));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;

    }

    //修改收款管理
    public void update16(finance d) {
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "update finance set orderId='";
            sql += d.getOrderId() + "',prodid=";
            sql += d.getProdid() + ",paidtypeid='";
            sql += d.getPaidtypeid() + "',remainMoney=";
            sql += d.getRemainMoney() + ",paidMoney=";
            sql += d.getPaidMoney() + ",orderMoney=";
            sql += d.getOrderMoney() + ",paidPerson='";
            sql += d.getPaidPerson() + "',inbank='";
            sql += d.getInbank() + "',bankAccount='";
            sql += d.getBankAccount() + "',outbank='";
            sql += d.getOutbank() + "',warrant='";
            sql += d.getWarrant() + "',paidTime='";
            sql += d.getPaidTime() + "',paidinTime='";
            sql += d.getPaidinTime() + "',invalid='";
            sql += d.getInvalid() + "',username='";
            sql += d.getUsername() + "',oprtime='";
            sql += d.getOprtime() + "',oprType='";
            sql += d.getOprType() + "' where financeId=" + d.getFinanceId();
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //统计记录总数
    public int findCount16() {
        int cnt = 0;
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();

        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from finance";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                cnt = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnt;
    }


    //分页收款管理
    public List<finance> findByPage16(int pageSize, int curPage,String OrderId) {

        List<finance> list = new ArrayList<finance>();
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();
        //计算起始位置
        int startPos = (curPage - 1) * pageSize;
        try {
            Statement stmt = conn.createStatement();
            String sql = null;
            if(OrderId==null || OrderId.equals("")){
                sql = "select top " + pageSize + " * from Finance f inner join PaidType p on f.paidtypeid = p.paidtypeid inner join product r on r.prodid = f.prodid where financeId not in(";
                sql += "select top " + startPos + " financeId from finance";
                sql += ") order by financeid";
            }else{
                sql = "select top " + pageSize + " * from Finance f inner join PaidType p on f.paidtypeid = p.paidtypeid inner join product r on r.prodid = f.prodid where financeId not in(";
                sql += "select top " + startPos + " financeId from finance";
                sql += ") and orderid = "+OrderId+" order by financeid";
            }
            System.out.println("sql=" + sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                finance s = new finance();
                s.setFinanceId(rs.getInt("financeId"));
                s.setOrderId(rs.getString("orderId"));
                s.setProdid(rs.getInt("prodid"));
                s.setPaidtypeid(rs.getString("paidtypeid"));
                s.setRemainMoney(rs.getFloat("remainMoney"));
                s.setPaidMoney(rs.getFloat("paidMoney"));
                s.setOrderMoney(rs.getFloat("orderMoney"));
                s.setPaidPerson(rs.getString("paidPerson"));
                s.setInbank(rs.getString("inbank"));
                s.setBankAccount(rs.getString("bankAccount"));
                s.setOutbank(rs.getString("outbank"));
                s.setWarrant(rs.getString("warrant"));
                s.setPaidTime(rs.getString("paidTime"));
                s.setPaidinTime(rs.getString("paidinTime"));
                s.setInvalid(rs.getString("invalid"));
                s.setUsername(rs.getString("username"));
                s.setOprtime(rs.getString("oprtime"));
                s.setOprType(rs.getString("oprType"));
                s.setPaidtypename(rs.getString("paidtypename"));
                s.setProdname(rs.getString("prodname"));
                list.add(s);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }




    //----------------------------------开票信息表------------------------
    //删除开票信息资料
    public void del17(String id) {
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "delete Ticket where id=" + id;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询开票信息表
    public List<Ticket> findticket17() {
        List<Ticket> list = new ArrayList<>();
        Connection connection = DBConn.openDB();
        String sql = "select*from Ticket";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Ticket c = new Ticket();
                c.setId(resultSet.getInt("id"));
                c.setTicketDate(resultSet.getString("ticketDate"));
                c.setOrderid(resultSet.getString("orderid"));
                c.setCustid(resultSet.getInt("custid"));
                c.setTicketMoney(resultSet.getFloat("ticketMoney"));
                c.setTicketComp(resultSet.getString("ticketComp"));
                c.setUsername(resultSet.getString("username"));
                c.setOprtime(resultSet.getString("oprtime"));
                c.setRemark(resultSet.getString("remark"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //新增开票信息表
    public void save17(Ticket d) {
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "insert into Ticket values('";
            sql += d.getTicketDate() + "','";
            sql += d.getOrderid() + "',";
            sql += d.getCustid() + ",";
            sql += d.getTicketMoney() + ",'";
            sql += d.getTicketComp() + "','";
            sql += d.getUsername() + "','";
            sql += d.getOprtime() + "','";
            sql += d.getRemark() + "')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //通过开票信息id查询一个开票信息的信息
    public Ticket findByStudId17(String id) {
        Connection conn = DBConn.openDB();
        Ticket c = new Ticket();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from Ticket where id=" + id;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                c.setId(rs.getInt("id"));
                c.setTicketDate(rs.getString("ticketDate"));
                c.setOrderid(rs.getString("orderid"));
                c.setCustid(rs.getInt("custid"));
                c.setTicketMoney(rs.getFloat("ticketMoney"));
                c.setTicketComp(rs.getString("ticketComp"));
                c.setUsername(rs.getString("username"));
                c.setOprtime(rs.getString("oprtime"));
                c.setRemark(rs.getString("remark"));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;

    }

    //修改开票信息资料
    public void update17(Ticket d) {
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "update Ticket set ticketDate='";
            sql += d.getTicketDate() + "',orderid='";
            sql += d.getOrderid() + "',custid=";
            sql += d.getCustid() + ",ticketMoney=";
            sql += d.getTicketMoney() + ",ticketComp='";
            sql += d.getTicketComp() + "',username='";
            sql += d.getUsername() + "',oprtime='";
            sql += d.getOprtime() + "',remark='";
            sql += d.getRemark() + "' where id=" + d.getId();
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //统计记录总数
    public int findCount17() {
        int cnt = 0;
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();

        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from Ticket";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                cnt = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnt;
    }


    //分页开票信息资料
    public List<Ticket> findByPage17(int pageSize, int curPage,String OrderId) {

        List<Ticket> list = new ArrayList<Ticket>();
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();
        //计算起始位置
        int startPos = (curPage - 1) * pageSize;
        try {
            Statement stmt = conn.createStatement();
            String sql = null;
            if(OrderId == null || OrderId.equals("")){
                sql = "select top " + pageSize + " * from ticket t inner join customerinfo c on t.custid  = c.custId where id not in(";
                sql += "select top " + startPos + " id from Ticket";
                sql += ") order by id";
            }else{
                sql = "select top " + pageSize + " * from ticket t inner join customerinfo c on t.custid  = c.custId where id not in(";
                sql += "select top " + startPos + " id from Ticket";
                sql += ") and orderid = "+OrderId+" order by id";
            }
            System.out.println("sql=" + sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Ticket s = new Ticket();
                s.setId(rs.getInt("id"));
                s.setTicketDate(rs.getString("ticketDate"));
                s.setOrderid(rs.getString("orderid"));
                s.setCustid(rs.getInt("custid"));
                s.setTicketMoney(rs.getFloat("ticketMoney"));
                s.setTicketComp(rs.getString("ticketComp"));
                s.setUsername(rs.getString("username"));
                s.setOprtime(rs.getString("oprtime"));
                s.setRemark(rs.getString("remark"));
                s.setCustname(rs.getString("custname"));
                list.add(s);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }




    //----------------------------------工作周报表------------------------
    //删除工作周报表资料
    public void del18(String id) {
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "delete workday where id=" + id;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询工作周报表表
    public List<workday> findworkday18() {
        List<workday> list = new ArrayList<>();
        Connection connection = DBConn.openDB();
        String sql = "select*from workday";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                workday c = new workday();
                c.setId(resultSet.getInt("id"));
                c.setWeekDate(resultSet.getString("weekDate"));
                c.setWorkContent(resultSet.getString("workContent"));
                c.setWorkReview(resultSet.getString("workReview"));
                c.setQuestion(resultSet.getString("question"));
                c.setWarning(resultSet.getString("warning"));
                c.setWeekPlan(resultSet.getString("weekPlan"));
                c.setUsername(resultSet.getString("username"));
                c.setOprtime(resultSet.getString("oprtime"));
                c.setRemark(resultSet.getString("remark"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //新增工作周报表
    public void save18(workday d) {
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "insert into workday values('";
            sql += d.getWeekDate() + "','";
            sql += d.getWorkContent() + "','";
            sql += d.getWorkReview() + "','";
            sql += d.getQuestion() + "','";
            sql += d.getWarning() + "','";
            sql += d.getWeekPlan() + "','";
            sql += d.getUsername() + "','";
            sql += d.getOprtime() + "','";
            sql += d.getRemark() + "')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //通过工作周报表id查询一个开工作周报表
    public workday findByStudId18(String id) {
        Connection conn = DBConn.openDB();
        workday c = new workday();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from workday where id=" + id;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                c.setId(rs.getInt("id"));
                c.setWeekDate(rs.getString("weekDate"));
                c.setWorkContent(rs.getString("workContent"));
                c.setWorkReview(rs.getString("workReview"));
                c.setQuestion(rs.getString("question"));
                c.setWarning(rs.getString("warning"));
                c.setWeekPlan(rs.getString("weekPlan"));
                c.setUsername(rs.getString("username"));
                c.setOprtime(rs.getString("oprtime"));
                c.setRemark(rs.getString("remark"));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;

    }

    //修改工作周报表
    public void update18(workday d) {
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "update workday set weekDate='";
            sql += d.getWeekDate() + "',workContent='";
            sql += d.getWorkContent() + "',workReview='";
            sql += d.getWorkReview() + "',question='";
            sql += d.getQuestion() + "',warning='";
            sql += d.getWarning() + "',weekPlan='";
            sql += d.getWeekPlan() + "',username='";
            sql += d.getUsername() + "',oprtime='";
            sql += d.getOprtime() + "',remark='";
            sql += d.getRemark() + "' where id=" + d.getId();
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //统计记录总数
    public int findCount18() {
        int cnt = 0;
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();

        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from workday";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                cnt = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnt;
    }


    //分页工作周报表
    public List<workday> findByPage18(int pageSize, int curPage) {

        List<workday> list = new ArrayList<workday>();
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();
        int startPos = (curPage - 1) * pageSize;
        try {
            Statement stmt = conn.createStatement();
            String sql = "select top " + pageSize + " * from workday where id not in(";
            sql += "select top " + startPos + " id from workday";
            sql += ")";
            System.out.println("sql=" + sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                workday s = new workday();
                s.setId(rs.getInt("id"));
                s.setWeekDate(rs.getString("weekDate"));
                s.setWorkContent(rs.getString("workContent"));
                s.setWorkReview(rs.getString("workReview"));
                s.setQuestion(rs.getString("question"));
                //计算起始位置
                s.setWarning(rs.getString("warning"));
                s.setWeekPlan(rs.getString("weekPlan"));
                s.setUsername(rs.getString("username"));
                s.setOprtime(rs.getString("oprtime"));
                s.setRemark(rs.getString("remark"));
                list.add(s);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //订单总金额
    public int ordertotalmoney(int orderId){
        int cnt = 0;
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select sum(totalMoney) as money from OrderDetail where orderId = "+orderId+"";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                cnt = rs.getInt("money");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnt;
    }

    //生成订单随机数
    public String ordersrand() {
        Connection conn = DBConn.openDB();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = simpleDateFormat.format(new Date());
        System.out.println("date的值"+date);
        try {
            Statement stmt = conn.createStatement();
            String sql = "select isnull(max(orderid),0)+1 as math from orders where orderid like '"+date+"%'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                int value = rs.getInt("math");
                if(value==1){
                    date = date+"1";
                }else{
                    date = value+"";
                }
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

        //判断订单是否存在
        public Boolean orderexit(int orderid){
            boolean b = false;
            Connection conn =DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from orders where orderId = "+orderid+"";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                b = true;
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
        }

        //修改订单金额
        public void updateOrder(int money,String orderId){
            Connection conn = DBConn.openDB();
            try {
                Statement stmt = conn.createStatement();
                String sql = "update orders set totalMoney = "+money+" where orderId = "+orderId+"";
                stmt.executeUpdate(sql);
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //修改库存数量
    public void updateprodcount(Product p,int orderscount){
        Connection conn = DBConn.openDB();
        float count = 0;
        if(orderscount > 0){
             count = p.getProdCount() - orderscount;
        }else{
             count = p.getProdCount() - orderscount;
        }
        try {
            Statement stmt = conn.createStatement();
            String sql = "update product set prodCount = "+count+" where prodid = "+p.getProdid()+"";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据订单号删除订单明细
    public void updatedetail(String orderid){
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "delete from OrderDetail where orderId = "+orderid+"";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取订单中订单明细的总数量
    public int detailcount(String orderid,int DetailId){
        int cnt = 0;
        System.out.println(orderid);
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select sum(prodCount) as count from OrderDetail where orderId = "+orderid+" and  DetailId = "+DetailId+"";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                cnt = rs.getInt("count");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnt;
    }

    //删除订单修改库存数量
    public void prodcountup(Product p,int orderscount,String status){
        Connection conn = DBConn.openDB();
        float count = 0;
        System.out.println(status);
        if(status.equals("采购入库")){
            count = p.getProdCount() - (orderscount);
        }else{
            count = p.getProdCount() + (orderscount);
        }
        try {
            Statement stmt = conn.createStatement();
            String sql = "update product set prodCount = "+count+" where prodid = "+p.getProdid()+"";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //修改订单状态
    public void updatestatus(String orderId,String statu){
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "update orders set orderStatus = '"+statu+"' where orderId = "+orderId+"";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //通过订单编号获取所有订单明细的集合
   public List<OrderDetail> finddetaList(String orderId){
        List<OrderDetail> list = new ArrayList<OrderDetail>();
        //创建Connection对象
          Connection conn = DBConn.openDB();
        //计算起始位置
        try {
        Statement stmt = conn.createStatement();
        String sql = "select * from OrderDetail where orderId = "+orderId+"";
        System.out.println("sql=" + sql);
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            OrderDetail s = new OrderDetail();
            s.setDetailId(rs.getInt("DetailId"));
            s.setOrderId(rs.getString("orderId"));
            s.setProdid(rs.getString("prodid"));
            s.setStatus(rs.getString("status").trim());
            s.setSaleMoney(rs.getFloat("saleMoney"));
            s.setUnitId(rs.getInt("UnitId"));
            s.setRegPerson(rs.getString("regPerson").trim());
            s.setRegPassword(rs.getString("regPassword").trim());
            s.setServicePeriod(rs.getString("servicePeriod").trim());
            s.setExpireDate(rs.getString("expireDate").trim());
            s.setProdCount(rs.getInt("prodCount"));
            s.setTotalMoney(rs.getFloat("totalMoney"));
            s.setOprtime(rs.getString("oprtime").trim());
            s.setOperator(rs.getString("operator").trim());
            s.setRemark(rs.getString("remark").trim());
            list.add(s);
        }
        rs.close();
        stmt.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
        return list;
    }

    //根据条件查询派工信息
    //分页查询派工单单表资料
    public List<jobRecord> findByPage12(int pageSize,int curPage,jobRecord jobRecord){

        List<jobRecord> list = new ArrayList<jobRecord>();
        //创建Connection对象
//		Connection conn = DBPool.openDB();
        Connection conn = DBConn.openDB();
        //计算起始位置
        int startPos = (curPage-1)*pageSize;
        try{
            Statement stmt = conn.createStatement();
            String sql = "select top "+pageSize+" * from JobRecord j inner join product p on j.prodName = p.prodid inner join customerinfo c on j.custid = c.custId inner join users u on u.userid = j.userid where jobId not in(";
            sql += "select top "+startPos+" jobId from jobRecord";
            sql +=")";
            if(jobRecord.getCustid() > 0 ){
                sql+=" and j.custid = "+jobRecord.getCustid();
            }
            if(Integer.parseInt(jobRecord.getUserid()) > 0 ){
                sql+=" and j.userid = "+jobRecord.getUserid();
            }
            System.out.println("sql="+sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                jobRecord s = new jobRecord();
                s.setJobId(rs.getInt("jobId"));
                s.setOrderId(rs.getString("orderId"));
                s.setJobDate(rs.getString("jobDate"));
                s.setProdName(rs.getString("prodName").trim());
                s.setCustid(rs.getInt("custid"));
                s.setJobContent(rs.getString("jobContent").trim());
                s.setCallback(rs.getString("callback").trim());
                s.setUserid(rs.getString("userid"));
                s.setCustEval(rs.getString("custEval").trim());
                s.setCustSign(rs.getString("custSign"));
                s.setStartTime(rs.getString("startTime").trim());
                s.setEndTime(rs.getString("endTime").trim());
                s.setWorkDay(rs.getInt("workDay"));
                s.setBusMoney(rs.getFloat("busMoney"));
                s.setAttachMoney(rs.getFloat("attachMoney"));
                s.setProdinfor(rs.getString("prodname"));
                s.setCustname(rs.getString("custname"));
                s.setUsername(rs.getString("username"));
                list.add(s);
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    //根据客户名称查出所有信息
    public CustomerInfo custinfor(String custname){
        Connection conn = DBConn.openDB();
        CustomerInfo  c = new CustomerInfo();
        try{
            Statement stmt = conn.createStatement();
            String sql = "select * from CustomerInfo where custname='"+custname+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                c.setCustId(rs.getInt("custId"));
                c.setCustname(rs.getString("custname").trim());
                c.setCusttype(rs.getString("custtype").trim());
                c.setBankAccount(rs.getString("bankaccount").trim());
                c.setBankName(rs.getString("bankName").trim());
                c.setContact(rs.getString("contact").trim());
                c.setPhone(rs.getString("phone").trim());
                c.setTicketName(rs.getString("ticketName").trim());
                c.setTicketAddr(rs.getString("ticketAddr").trim());
                c.setTicketTel(rs.getString("tickettel"));
                c.setTaxNo(rs.getString("taxNo").trim());
                c.setCustState(rs.getString("custState").trim());
                c.setUsername(rs.getString("userid").trim());
                c.setSource(rs.getString("source").trim());
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return c;
    }

    //根据客户名称查询所有订单
    public List<Orders> findByPage10(int custid){

        List<Orders> list = new ArrayList<Orders>();
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "select * from orders o inner join customerinfo c on o.custid = c.custId inner join users u on u.userid = o.userid where o.custid = "+custid;
            System.out.println("sql="+sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Orders s = new Orders();
                s.setOrderId(rs.getInt("orderId"));
                s.setCustid(rs.getInt("custid"));
                s.setUserid(rs.getInt("userid"));
                s.setOrderType(rs.getString("orderType").trim());
                s.setOrderStatus(rs.getString("orderStatus").trim());
                s.setProcess(rs.getString("process").trim());
                s.setTotalMoney(rs.getFloat("totalMoney"));
                s.setOprtime(rs.getString("oprtime").trim());
                s.setOperator(rs.getString("operator").trim());
                s.setRemark(rs.getString("remark").trim());
                s.setCustomername(rs.getString("custname"));
                s.setUsername(rs.getString("username"));
                list.add(s);
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    //获取客户的合同信息
    public List<contract> findByPage8(int custid){

        List<contract> list = new ArrayList<contract>();
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "select * from contract c inner join customerinfo t on c.custId = t.custId inner join users u on u.userid = c.empid where c.custId = "+custid;
            System.out.println("sql="+sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                contract s = new contract();
                s.setContract_id(rs.getInt("contract_id"));
                s.setContract_no(rs.getString("contract_no"));
                s.setCustId(rs.getInt("custId"));
                s.setContract_time(rs.getString("contract_time").trim());
                s.setDue_time(rs.getString("due_time").trim());
                s.setTotal_money(rs.getString("total_money").trim());
                s.setPay_type(rs.getString("pay_type").trim());
                s.setDeposit(rs.getString("deposit"));
                s.setStatus(rs.getString("status").trim());
                s.setEmpid(rs.getInt("empid"));
                s.setOperator(rs.getString("operator").trim());
                s.setOprtime(rs.getString("oprtime").trim());
                s.setCustomer(rs.getString("custname"));
                s.setUsername(rs.getString("username"));
                list.add(s);
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    //获取客户的派工信息
    public List<jobRecord> findjobrecord(int custid){
        List<jobRecord> list = new ArrayList<jobRecord>();
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "select * from JobRecord j inner join product p on j.prodName = p.prodid inner join customerinfo c on j.custid = c.custId inner join users u on u.userid = j.userid where j.custid = "+custid;
            System.out.println("sql="+sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                jobRecord s = new jobRecord();
                s.setJobId(rs.getInt("jobId"));
                s.setOrderId(rs.getString("orderId"));
                s.setJobDate(rs.getString("jobDate"));
                s.setProdName(rs.getString("prodName").trim());
                s.setCustid(rs.getInt("custid"));
                s.setJobContent(rs.getString("jobContent").trim());
                s.setCallback(rs.getString("callback").trim());
                s.setUserid(rs.getString("userid"));
                s.setCustEval(rs.getString("custEval").trim());
                s.setCustSign(rs.getString("custSign"));
                s.setStartTime(rs.getString("startTime").trim());
                s.setEndTime(rs.getString("endTime").trim());
                s.setWorkDay(rs.getInt("workDay"));
                s.setBusMoney(rs.getFloat("busMoney"));
                s.setAttachMoney(rs.getFloat("attachMoney"));
                s.setProdinfor(rs.getString("prodname"));
                s.setCustname(rs.getString("custname"));
                s.setUsername(rs.getString("username"));
                list.add(s);
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    //获取客户的洽谈信息
    public List<business> findbusiness(int custid) {

        List<business> list = new ArrayList<business>();
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from Business b inner join customerinfo c on b.custid = c.custId inner join users u on u.userid = b.userid where b.custid = "+custid+"";
            System.out.println("sql=" + sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                business s = new business();
                s.setBusinessId(rs.getInt("businessId"));
                s.setBusDate(rs.getString("busDate"));
                s.setProdName(rs.getString("prodName"));
                s.setChatContent(rs.getString("chatContent").trim());
                s.setChatResult(rs.getString("chatResult"));
                s.setCustid(rs.getInt("custid"));
                s.setCustContact(rs.getString("custContact").trim());
                s.setPhone(rs.getString("phone"));
                s.setUserid(rs.getInt("userid"));
                s.setModule(rs.getString("module"));
                s.setModuleState(rs.getString("moduleState").trim());
                s.setModuleMoney(rs.getFloat("moduleMoney"));
                s.setRemark(rs.getString("remark"));
                s.setCustname(rs.getString("custname"));
                s.setUsername(rs.getString("username"));
                list.add(s);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //统计图（获取用户的订单金额）
    public int moneycount(int custid){
        int cnt = 0;
        Connection conn = DBConn.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select sum(totalmoney) as count from orders  where custid = "+custid+"";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                cnt = rs.getInt("count");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnt;
    }

    //根据用户名称获取用户信息
    public Users finduserinfor(String username){
        Connection conn = DBConn.openDB();
        Users users = new Users();
        try{
            Statement stmt = conn.createStatement();
            String sql = "select * from users where username = '"+username+"'";
            System.out.println("sql="+sql);
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                users.setUserid(rs.getInt("userid"));
                users.setUsername(rs.getString("username"));
                users.setPassword(rs.getString("password"));
                users.setDepid(rs.getInt("depid"));
                users.setDegreeid(rs.getInt("degreeid"));
                users.setJobname(rs.getString("jobname"));
                users.setManagerType(rs.getInt("managerType"));
                users.setMobile(rs.getString("mobile"));
                users.setEmail(rs.getString("email"));
                users.setQqcode(rs.getString("qqcode"));
                users.setWeixin(rs.getString("weixin"));
                users.setCardno(rs.getString("cardno"));
                users.setBankName(rs.getString("bankname"));
                users.setBankCardNo(rs.getString("bankCardNo"));
                users.setJoinDate(rs.getString("joinDate"));
                users.setWorkDate(rs.getString("workDate"));
                users.setLevelDate(rs.getString("levelDate"));
                users.setBaseSalary(rs.getFloat("basesalary"));
                users.setDegreeSalary(rs.getFloat("degreesalary"));
                users.setAddr(rs.getString("addr"));
                users.setStatus(rs.getInt("status"));
                users.setRemark(rs.getString("remark"));
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return users;
    }
    public List<Orders> userstatus(String status,int userid){

        List<Orders> list = new ArrayList<Orders>();
        Connection conn = DBConn.openDB();
        try{
            Statement stmt = conn.createStatement();
            String sql = "select * from orders o inner join customerinfo c on o.custid = c.custId inner join users u on u.userid = o.userid where o.orderStatus = '"+status+"' and o.userid = "+userid+"";
            System.out.println("sql="+sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Orders s = new Orders();
                s.setOrderId(rs.getInt("orderId"));
                s.setCustid(rs.getInt("custid"));
                s.setUserid(rs.getInt("userid"));
                s.setOrderType(rs.getString("orderType").trim());
                s.setOrderStatus(rs.getString("orderStatus").trim());
                s.setProcess(rs.getString("process").trim());
                s.setTotalMoney(rs.getFloat("totalMoney"));
                s.setOprtime(rs.getString("oprtime").trim());
                s.setOperator(rs.getString("operator").trim());
                s.setRemark(rs.getString("remark").trim());
                s.setCustomername(rs.getString("custname"));
                s.setUsername(rs.getString("username"));
                list.add(s);
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

}

