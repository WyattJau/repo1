import com.lagou.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    /*
    *   快速入门测试方法
    * */
    @Test
    public void mybatisQuickStart() throws IOException {

        // 1、加载核心配置文件
        // 把核心文件加载成字节输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        // 2、获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        // 3、获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 4、执行sql 参数：statement.id : namespace.id
        List<User> users = sqlSession.selectList("userMapper.findAll");

        // 5、遍历打印结果
        for (User user : users) {
            System.out.println(user);
        }

        // 6、关闭资源
        sqlSession.close();
    }


    /*
    *   测试新增用户
    * */
    @Test
    public void testSave() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession(true); // true:自动提交事务

        User user = new User();
        user.setUsername("王五");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("北京海淀");

        sqlSession.insert("userMapper.saveUser", user);

        // 手动提交事务
//        sqlSession.commit();

        sqlSession.close();
        System.out.println("新增用户成功！");
    }

    /*
    *   测试更新用户信息
    * */
    @Test
    public void testUpdate() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setId(3);
        user.setBirthday(new Date());
        user.setUsername("Lucy");
        user.setSex("女");
        user.setAddress("北京朝阳");

        sqlSession.update("userMapper.updateUser", user);

        sqlSession.commit();

        sqlSession.close();
        System.out.println("更新用户信息成功！");
    }

    /*
    *   测试删除用户
    * */
    @Test
    public void testDelete() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        sqlSession.delete("userMapper.deleteUser", 3);

        sqlSession.commit();

        sqlSession.close();

        System.out.println("删除用户成功！");

    }
}
