package com.yu;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.mapper.UserMapper;
import com.yu.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class YuApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
        //查询所有用户
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out :: println);
    }

    //测试插入
    @Test
    public void testInsert(){
        User user = new User();
        user.setId(9L);
        user.setName("ge321");
        user.setAge(4);
        user.setEmail("ge321@qq.com");

        int result = userMapper.insert(user);
        System.out.println(result);
        System.out.println(user);

    }

    //测试更新
    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(1L);
        user.setName("zh");
        user.setAge(16);
        user.setEmail("zh@qq.com");

        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    //测试乐观锁成功
    @Test
    public void testOptimisticLocker(){
        //1.查询用户信息
        User user = userMapper.selectById(1L);
        //2.修改用户信息
        user.setName("yuyuyuyu");
        user.setEmail("yuyuyuyuyuyu@qq.com");
        //3.执行更新操作
        userMapper.updateById(user);
    }

    //测试乐观锁失败
    @Test
    public void testOptimisticLocker2(){
        //1.查询用户信息
        User user = userMapper.selectById(1L);
        //2.修改用户信息
        user.setName("yuyuyuyu");
        user.setEmail("yuyuyuyuyuyu@qq.com");

        //多线程下
        User user2 = userMapper.selectById(1L);
        user.setName("yuyuyuyu2222");
        user.setEmail("yuyuyuyuyuyu2222@qq.com");
        userMapper.updateById(user2);

        //3.执行更新操作
        userMapper.updateById(user);
    }

    //测试批量查询
    @Test
    public void  testSelectByBatchId(){
        List<User> user = userMapper.selectBatchIds(Arrays.asList(1,2,3));
        user.forEach(System.out :: println);
    }

    //测试条件查询之一：使用map操作
    @Test
    public void testSelectByBatchIds(){
        HashMap<String,Object> map = new HashMap<>();
        //自定义查询
        map.put("name","yu");
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out :: println);
    }

    //测试分页查询
    @Test
    public void testPage(){
        //参数一：当前页；参数二：页面大小
        Page<User> page = new Page<>(1,3);
        userMapper.selectPage(page,null);
        page.getRecords().forEach(System.out :: println);
        System.out.println(page.getTotal());

    }

    /**
     * 物理删除：
     * 逻辑删除：在数据库中没有被移除，而是通过一个变量让它消失
     * 走的是更新操作，并不是删除操作。deleted 的值变为 1
     * 查询时，会过滤。
     */
    //测试通过id删除
    @Test
    public void testDeleteById(){
        userMapper.deleteById(3L);
    }

    //测试通过id批量删除
    @Test
    public void testDeleteBatchIds(){
        userMapper.deleteBatchIds(Arrays.asList(2L,2L));
    }

    //测试通过条件 map 批量删除
    @Test
    public void testDeleteMap(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("name","zhang111");
        userMapper.deleteByMap(map);
    }


}
