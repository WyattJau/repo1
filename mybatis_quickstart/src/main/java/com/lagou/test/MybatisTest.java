package com.lagou.test;

import com.lagou.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    /*
    *   快速入门测试方法
    * */
    public void mybatisQuickStart() throws IOException {

        // 1、加载核心配置文件
        // 把核心文件加载成字节输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        // 2、获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        // 3、获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 4、执行sql 参数：statement.id : namespace.id
        List<User> users = sqlSession.selectList("user.findAll");

        // 5、遍历打印结果
        for (User user : users) {
            System.out.println(user);
        }

        // 6、关闭资源
        sqlSession.close();
    }
}
