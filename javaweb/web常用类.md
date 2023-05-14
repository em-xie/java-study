### dao

```java
package com.xie.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

//操作数据库的公共类
public class BaseDao {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    //静态代码块，类加载的时候就初始化了
    static {
        Properties params = new Properties();
        //通过类加载器读取对应的资源
        InputStream is=BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            params.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver=params.getProperty("driver");
        url=params.getProperty("url");
        username=params.getProperty("username");
        password=params.getProperty("password");


    }
    //获取数据库的链接
    public static Connection getConnection(){
        Connection connection=null;
        try {
            Class.forName(driver);
            connection= DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    //编写查询公共类
    public static ResultSet execute(Connection connection, PreparedStatement pstm,ResultSet resultSet,String sql ,Object params[]) throws SQLException {
        //预编译的sql，在后面直接执行就可以了
        pstm=connection.prepareStatement(sql);

        for (int i = 0; i <params.length ; i++) {
            //setObject,占位符从1开始，但是我们的数组是从0开始
            pstm.setObject(i+1,params[i]);
        }
        resultSet=pstm.executeQuery();
        return resultSet;
    }
    //编写增删改公共方法
    public static int execute(Connection connection,PreparedStatement preparedStatement, String sql,Object[] params) throws SQLException {
        for (int i = 1; i <params.length ; i++) {
            //setObject,占位符从1开始，但是我们的数组是从0开始
            preparedStatement.setObject(i+1,params[i]);
        }
        int updateRows = preparedStatement.executeUpdate();
        return updateRows;
    }
    //释放资源
    public static boolean closeResource(Connection connection,PreparedStatement pstm, ResultSet resultSet){
        boolean flag=true;
        if(resultSet!=null){
            try {
                resultSet.close();
                resultSet=null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag=false;
            }
        }
        if(pstm!=null){
            try {
                pstm.close();
                pstm=null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag=false;
            }
        }
        if(connection!=null){
            try {
                    connection.close();
                //GC回收
                connection=null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag=false;
            }
        }
        return flag;
    }
}
```





```java
package com.xie.dao.user;

import com.xie.pojo.Role;
import com.xie.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    //得到要登录的用户
    public User getLoginUser(Connection connection, String userCode) throws SQLException;

    //修改当前用户密码
    public int updatePwd(Connection connection,int id,String password) throws SQLException;

    //查询用户总数
    public int getUserCount(Connection connection,String username,int userRole) throws SQLException;

    //通过条件查询-userList
    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize)throws Exception;




}
```





```java
package com.xie.dao.user;

import com.mysql.jdbc.StringUtils;
import com.xie.dao.BaseDao;
import com.xie.pojo.Role;
import com.xie.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImp1 implements UserDao {
    public User getLoginUser(Connection connection, String userCode) throws SQLException {
        PreparedStatement pstm=null;
        ResultSet rs=null;
        User user=null;

        if(connection!=null){
            String sql="select *from smbms_user where userCode=?";
            Object[] params={userCode};
            rs= BaseDao.execute(connection,pstm,rs,sql,params);
            if(rs.next()){
                user=new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserRole(rs.getInt("userRole"));
                user.setCreatedBy(rs.getInt("createdBy"));
                user.setCreationDate(rs.getTimestamp("creationDate"));
                user.setModifyBy(rs.getInt("modifyBy"));
                user.setModifyDate(rs.getTimestamp("modifyDate"));
            }
            BaseDao.closeResource(null,pstm,rs);
        }
        return user;
    }



    public int updatePwd(Connection connection, int id, String password) throws SQLException {
        System.out.println("UserServlet:"+password);
        PreparedStatement pstm=null;
        int execute =0;


        if(connection!=null){
            Object params[]={password,id};
            String sql="update smbms_user set userPassword=? where id=?";
            execute = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null,pstm,null);
        }
        return execute;


    }

    public int getUserCount(Connection connection, String username, int userRole) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as count from smbms_user u,smbms_role r where u.userRole = r.id");
            List<Object> list = new ArrayList<Object>();
            if(!StringUtils.isNullOrEmpty(username)){
                sql.append(" and u.userName like ?");
                list.add("%"+username+"%");
            }
            if(userRole > 0){
                sql.append(" and u.userRole = ?");
                list.add(userRole);
            }
            Object[] params = list.toArray();
            System.out.println("sql ----> " + sql.toString());
            rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);
            if(rs.next()){
                count = rs.getInt("count");
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return count;
    }

    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<User>();
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select u.*,r.roleName as userRoleName from smbms_user u,smbms_role r where u.userRole = r.id");
            List<Object> list = new ArrayList<Object>();
            if(!StringUtils.isNullOrEmpty(userName)){
                sql.append(" and u.userName like ?");
                list.add("%"+userName+"%");
            }
            if(userRole > 0){
                sql.append(" and u.userRole = ?");
                list.add(userRole);
            }
            sql.append(" order by creationDate DESC limit ?,?");
            currentPageNo = (currentPageNo-1)*pageSize;
            list.add(currentPageNo);
            list.add(pageSize);

            Object[] params = list.toArray();
            System.out.println("sql ----> " + sql.toString());
            rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);
            while(rs.next()){
                User _user = new User();
                _user.setId(rs.getInt("id"));
                _user.setUserCode(rs.getString("userCode"));
                _user.setUserName(rs.getString("userName"));
                _user.setGender(rs.getInt("gender"));
                _user.setBirthday(rs.getDate("birthday"));
                _user.setPhone(rs.getString("phone"));
                _user.setUserRole(rs.getInt("userRole"));
                _user.setUserRoleName(rs.getString("userRoleName"));
                userList.add(_user);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return userList;

    }



}
```

### service

```java
package com.xie.service.user;

import com.xie.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserService {
    //用户登录
    public User login(String userCode,String password);
    //根据用户ID修改密码
    public boolean updatePwd(int id,String password);
    //根据条件查询用户表记录数
    public int getUserCount(String queryUserName, int queryUserRole);
    //根据条件查询用户列表
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize);
    //

}
```





```java
package com.xie.service.user;

import com.xie.dao.BaseDao;
import com.xie.dao.user.UserDao;
import com.xie.dao.user.UserDaoImp1;
import com.xie.pojo.User;
import com.xie.service.user.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImp1 implements UserService {

    //业务层都会调用dao层，所有我们要引入dao层
    private UserDao userDao;
    public UserServiceImp1(){
        userDao=new UserDaoImp1();

    }






    public User login(String userCode, String password) {
        Connection connection=null;
        User user=null;


        try {
            //通过业务层调用对应的具体的数据库操作
            connection= BaseDao.getConnection();
            user=userDao.getLoginUser(connection,userCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);

        }
        return user;
    }


    public boolean updatePwd( int id, String password) {

        System.out.println("UserServlet:"+password);
        Connection connection = null;
        boolean flag=false;
        //修改密码

        try {
            connection = BaseDao.getConnection();
            if(userDao.updatePwd(connection,id,password)>0){
                flag=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }

    public int getUserCount(String queryUserName, int queryUserRole) {
        Connection connection = null;
        int count = 0;
        System.out.println("queryUserName ---- > " + queryUserName);
        System.out.println("queryUserRole ---- > " + queryUserRole);
        try {
            connection = BaseDao.getConnection();
            count = userDao.getUserCount(connection, queryUserName,queryUserRole);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            BaseDao.closeResource(connection, null, null);
        }
        return count;
    }

    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize) {
        Connection connection = null;
        List<User> userList = null;
        System.out.println("queryUserName ---- > " + queryUserName);
        System.out.println("queryUserRole ---- > " + queryUserRole);
        System.out.println("currentPageNo ---- > " + currentPageNo);
        System.out.println("pageSize ---- > " + pageSize);
        try {
            connection = BaseDao.getConnection();
            userList = userDao.getUserList(connection, queryUserName,queryUserRole,currentPageNo,pageSize);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            BaseDao.closeResource(connection, null, null);
        }
        return userList;

    }

}

```





### servlet



```java
package com.xie.servlet.user;

import com.xie.pojo.User;
import com.xie.service.user.UserServiceImp1;
import com.xie.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    //Servlet:业务层，调用业务层代码

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet--start....");

        //获取用户名和密码
        String userCode=req.getParameter("userCode");
        String userPassword=req.getParameter("userPassword");

        //和数据库中的密码进行对比，调用业务层
        UserServiceImp1 userService = new UserServiceImp1();
        User user = userService.login(userCode, userPassword);//这里已经把登录的人给查出来了

        if(user!=null){//查有此人，可以登录
            //将用户的信息放到Session中；
            req.getSession().setAttribute(Constants.USER_SESSION,user);
            //跳转到主页
            resp.sendRedirect("jsp/frame.jsp");

        }else {//查无此人，无法登录
            //转发回登录页面，顺带提示他，用户名密码错误
            req.setAttribute("error","用户名或者密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
```

```java
package com.xie.servlet.user;

import com.xie.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //移除用户的Constants.USER_SESSION
        req.getSession().removeAttribute(Constants.USER_SESSION);
        resp.sendRedirect(req.getContextPath()+"/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
```

```java
package com.xie.servlet.user;


import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;
import com.xie.pojo.Role;
import com.xie.pojo.User;
import com.xie.service.role.RoleServiceImpl;
import com.xie.service.user.UserService;
import com.xie.service.user.UserServiceImp1;
import com.xie.util.Constants;
import com.xie.util.PageSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method != null && method.equals("savapwd")) {
            this.updatePwd(req, resp);
        } else if (method.equals("pwdmodify") && method != null) {
            this.pwdModify(req, resp);
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    public void updatePwd(HttpServletRequest req, HttpServletResponse resp) {
        //从Session里面拿ID
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = req.getParameter("newpassword");
        System.out.println("UserServlet:" + newpassword);
        boolean flag = false;
        if (o != null && newpassword != null) {
            UserService userService = new UserServiceImp1();

            flag = userService.updatePwd(((User) o).getId(), newpassword);

            if (flag) {
                req.setAttribute("message", "修改密码成功，请退出，使用新密码登录");
                req.getSession().removeAttribute(Constants.USER_SESSION);
                //密码修改成功，释放当前session
            } else {
                req.setAttribute("message", "密码修改失败");
            }
        } else {
            req.setAttribute("message", "新密码有问题");

        }

        try {
            req.getRequestDispatcher("pwdmodify.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //验证旧密码，session用户中的密码
    public void pwdModify(HttpServletRequest req, HttpServletResponse resp) {
        //从Session里面拿ID
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");
        //万能的Map
        Map<String, String> resultMap = new HashMap<String, String>();

        if (o == null) {//Session失效了，过期
            resultMap.put("result", "sessionerror");

        } else if (StringUtils.isNullOrEmpty(oldpassword)) {
            resultMap.put("result", "error");

        } else {
            String userPassword = ((User) o).getUserPassword(); //Session用户中的密码
            if (oldpassword.equals(userPassword)) {
                resultMap.put("result", "true");
            } else {
                resultMap.put("result", "false");
            }
        }

        try {
            resp.setContentType("application/Json");
            PrintWriter writer = resp.getWriter();
            //JSONArray 转换格式
            writer.write(JSONArray.toJSONString(resultMap));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //重点，难点
    public void query(HttpServletRequest req, HttpServletResponse resp) {
        //查询用户列表
        String queryUserName = req.getParameter("queryname");
        String temp = req.getParameter("queryUserRole");
        String pageIndex = req.getParameter("pageIndex");
        int queryUserRole = 0;

        UserServiceImp1 userService = new UserServiceImp1();

        int pageSize = 5;
        int currentPageNo = 1;


        if (queryUserName == null) {
            queryUserName = "";

        }
        if (temp != null && temp.equals("")) {
            queryUserRole = Integer.parseInt(temp);
        }
        if (pageIndex != null) {
            currentPageNo = Integer.parseInt(pageIndex);

        }
        //获取用户的总数(分页，上一页，下一页)
        int totalCount = userService.getUserCount(queryUserName, queryUserRole);
        //总页数支持
        PageSupport pageSupport = new PageSupport();
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setPageSize(pageSize);
        pageSupport.setTotalCount(totalCount);

        int totalPageCount = pageSupport.getTotalPageCount();

        //控制首页和尾页
        if (currentPageNo < 1) {
            currentPageNo = 1;
        } else if (currentPageNo > totalPageCount) {
            currentPageNo = totalPageCount;
        }

        //获取用户列表
        List<User> userList = userService.getUserList(queryUserName, queryUserRole, currentPageNo, pageSize);
        req.setAttribute("userList", userList);

        RoleServiceImpl roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.getRoleList();
        req.setAttribute("roleList", roleList);
        req.setAttribute("totalCount",totalCount);
        req.setAttribute("currentPageNo",currentPageNo);
        req.setAttribute("totalPageCount",totalPageCount);
        req.setAttribute("queryUserName",queryUserName);
        req.setAttribute("queryUserRole",queryUserRole);

        try {
            req.getRequestDispatcher("userlist.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
```