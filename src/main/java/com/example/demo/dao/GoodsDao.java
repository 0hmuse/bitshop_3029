package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.GoodsManager;
import com.example.demo.vo.GoodsVo;

@Repository
public class GoodsDao {

	public List<GoodsVo> listGoods(){
		return GoodsManager.listGoods();
	}
	public int insertGoods(GoodsVo g) {
		return GoodsManager.insertGoods(g);
	}
}
