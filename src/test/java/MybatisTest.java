import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mappers.UserMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

public class MybatisTest {
    InputStream resourceAsStream;
    SqlSessionFactoryBuilder sqlSessionFactoryBuilder;
    SqlSessionFactory build;
    SqlSession sqlSession;

    @Before
    public void before() throws Exception {
        resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        build = sqlSessionFactoryBuilder.build(resourceAsStream);
        sqlSession = build.openSession();
    }

//    @Test
    public void testMybatis01() throws Exception {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int res = mapper.insertUser();
        sqlSession.commit();
        System.out.println("Inert: " + res);
    }

    @Test
    public void testInnodbReadWriteIOThread_1() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            mapper.insertUser();
            sqlSession.commit();
        }
        long stopTime = System.currentTimeMillis();

        System.out.println("10W Time: " + (stopTime - startTime));
    }

    @Test
    public void testInnodbReadWriteIOThread_16() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            mapper.insertUser();
            sqlSession.commit();
        }
        long stopTime = System.currentTimeMillis();

        System.out.println("10W Time: " + (stopTime - startTime));
    }
}
