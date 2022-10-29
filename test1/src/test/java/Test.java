import dao.zhang;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class Test {

    @org.junit.Test
    public void testSearchById() throws IOException {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("mybatis.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂生产SqlSession对象
        SqlSession session = sqlSessionFactory.openSession();
        //4.执行Sql语句
        zhang z = session.selectOne("test.findUserById", 1);
        //5. 打印结果
        System.out.println(z);
        //6.释放资源
        session.close();
        in.close();
    }

    //根据用户名模糊查询用户列表
    @org.junit.Test
    public void testFindUserByUsername() throws IOException {
        //定义读取文件名
        String resources = "mybatis.xml";
        //创建流
        Reader reader=null;
        try {
            //读取mybatis-config.xml文件到reader对象中
            reader= Resources.getResourceAsReader(resources);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //初始化mybatis,创建SqlSessionFactory类的实例
        SqlSessionFactory sqlMapper=new SqlSessionFactoryBuilder().build(reader);
        //创建session实例
        SqlSession session=sqlMapper.openSession();
        //传入参数查询，返回结果
        zhang z=session.selectOne("findById",1);
        //输出结果
        System.out.println(z.getName());
        //关闭session
        session.close();
    }
}
