package com.eamon.zhuiqiu.news.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eamon.zhuiqiu.news.dao.NewsDao;
import com.eamon.zhuiqiu.news.entity.News;



@Service
public class NewsService {

	@Autowired
	private NewsDao newsDao;
	
	@Autowired
	private ThemeService themeService;

	public int createNews(int themeId, String headPic, String title, String subTitle, String author, String source,
			String keywords, String navContent, String content) {
		
		News news = new News();
		news.setThemeId(themeId);
		news.setHeadPic(headPic);
		news.setTitle(title);
		news.setSubTitle(subTitle);
		news.setAuthor(author);
		news.setSource(source);
		news.setKeywords(keywords);
		news.setNavContent(navContent);
		news.setContent(content);
		
		return newsDao.insertNews(news);
	}
	
	public int updateNews(int id, String headPic, String title, String subTitle, String author, String source,
			String keywords, String navContent, String content) {
		
		News news = new News();
		news.setId(id);
		news.setHeadPic(headPic);
		news.setTitle(title);
		news.setSubTitle(subTitle);
		news.setAuthor(author);
		news.setSource(source);
		news.setKeywords(keywords);
		news.setNavContent(navContent);
		news.setContent(content);
		return newsDao.updateNews(news);
	}
	

	public List<Map<String, Object>> getNewsByThemeId(int themeId, int page, int rows) {
		if(page<1)return null;
		int start = (page-1)*rows;
		
		List<Map<String, Object>> resultList = new ArrayList<>();
		newsDao.selectAllByThemeId(themeId, start, rows).stream().forEach((e)->{
			resultList.add(genNewsMap(e));
		});
		return resultList;
	}
	
	
	public Map<String, Object> getNewsById(int id) {
		News e = newsDao.selectById(id);
		return genNewsMap(e);
	}

	public Map<String, Object> genNewsMap(News e){
		Map<String, Object> item = new HashMap<>();
		item.put("id", e.getId());
		item.put("headPic", e.getHeadPic());
		item.put("title", e.getTitle());
		item.put("subTitle", e.getSubTitle());
		item.put("author", e.getAuthor());
		item.put("source", e.getSource());
		item.put("keywords", e.getKeywords());
		item.put("navContent", e.getNavContent());
		item.put("clickNum", e.getClickNum());
		item.put("content", e.getContent());
		item.put("updateTime", e.getUpdateTime());
		item.put("theme", themeService.getTheme(e.getThemeId()));
		return item;
	}
	
	public boolean deleteNewsById(int newsId) {
		newsDao.deleteNews(newsId);
		return true;
	}

}
