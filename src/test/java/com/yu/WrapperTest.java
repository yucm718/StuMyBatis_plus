package com.yu;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yu.mapper.UserMapper;
import com.yu.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @Author: ycm
 * @Date: 2021/7/20 20:16
 */
@SpringBootTest
public class WrapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        //查询name不为空，并且邮箱不为空，年龄大于12岁的
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .isNotNull("name")
                .isNotNull("email")
                .ge("age",12);
        userMapper.selectList(wrapper).forEach(System.out :: println);

    }

    @Test
    void test2(){
        //查询名字等于zhang
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","zhang");
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    void test3(){
        //查询年龄在20~30岁之间的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age",20,30);
        Integer count = userMapper.selectCount(wrapper);
        System.out.println(count);
    }

    //模糊查询
    @Test
    void test4(){
        //查询年龄在20~30岁之间的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .notLike("name","e")
                .likeLeft("email","t");
        List<Map<String,Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out :: println);
    }

    //模糊查询
    @Test
    void test5(){
        //id在子查询中查出来
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .inSql("id","select id from user where id < 3");
        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out :: println);
    }

    @Test
    void test6(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //通过id进行排序 orderByDesc：降序 ---- orderByASC：升序
        wrapper.orderByDesc("id");
        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out :: println);
    }

}
