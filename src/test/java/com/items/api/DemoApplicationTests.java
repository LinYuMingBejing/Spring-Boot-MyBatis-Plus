package com.items.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.items.api.entity.User;
import com.items.api.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private UserMapper userMapper;

	// 查詢user表中所有數據
	@Test
	public void findAll() {
		List<User> users = userMapper.selectList(null);
		System.out.println(users);
	}

	// 添加操作

	@Test
	public void addUser(){
		User user = new User();
		user.setName("情歌沒有告訴你");
		user.setId(6);
		user.setAge(43);
		user.setEmail("LoveSong@gmail.com");
		// 自動填充：不需set對象值，使用mp方式實現數據添加

		// userMapper會自動生成id
		int insert = userMapper.insert(user);
		System.out.println(insert);
	}
	// 修改操作
	@Test
	public void updateUser(){
		User user = new User();
		user.setId(2);
		user.setAge(30);
		int row = userMapper.updateById(user);
		System.out.println(row);
	}

	// 測試樂觀鎖
	@Test
	public void testOptimsticLocker(){
		// 根據id查詢數據
		User user = userMapper.selectById(6);
		user.setAge(200);
		userMapper.updateById(user);
	}

	// 多個id批量查詢
	@Test
	public void testSelectDemo1(){
		List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3, 4));
		System.out.println(users);
	}

	@Test
	public void testSelectByMap(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("name","情歌沒有告訴你");
		List<User> users = userMapper.selectByMap(map);
		users.forEach(System.out::println);
	}

	@Test
	public void testPage(){
		// 1. 創建page對象
		// 傳入兩個參數，當前頁和每頁顯示紀錄數
		Page<User> page = new Page<>(1, 3);
		// 分頁查詢的方法
		// 調用mp分頁查詢過程中，底層封裝
		// 把分頁所有數據封裝到page對象裡面
		userMapper.selectPage(page,null);

		// 通過page對象獲取分頁數據
		System.out.println(page.getCurrent()); // 當前頁
		System.out.println(page.getRecords()); // 每頁數據list集合
		System.out.println(page.getSize()); // 每頁顯示紀錄數
		System.out.println(page.getTotal()); // 總紀錄數
		System.out.println(page.getPages()); // 總頁數

		System.out.println(page.hasNext());
		System.out.println(page.hasPrevious());
	}

	// 刪除操作
	@Test
	public void testDeleteById(){
		int i = userMapper.deleteById(3);
		System.out.println(i);
	}

	// 批量刪除
	@Test
	public void testDeleteByBatchId(){
		int i = userMapper.deleteBatchIds(Arrays.asList(1, 2));
		System.out.println(i);
	}

	// mp實現複雜點操作
	@Test
	public void testSelectQuery(){
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		// 透過QueryWrapper 設置條件
		//wrapper.ge("age",1);
		wrapper.between("age",30,60);
		List<User> users = userMapper.selectList(wrapper);
		System.out.println(users);

	}


}
