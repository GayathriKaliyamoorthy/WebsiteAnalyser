package com.hackathon.WebsiteAnalyser;


import java.util.HashMap;
import java.util.LinkedList;


public class WebsiteAnalyser {
	HashMap<String, PageCount> pages = new HashMap<String, PageCount>();
	LinkedList<PageCount> list = new LinkedList<PageCount>();
	public void reportPageAccess(String url){
		if( ! pages.containsKey(url)){
			PageCount pc = new PageCount(url, 1);
			pages.put(url, pc);
			list.addLast(pc);
			pc.setListIndex(list.indexOf(pc));
		}
		else{
			PageCount pc= pages.get(url);
			pc.incrCount(1);
			if (list.get(0).getCount() > pc.getCount()){
				int index = pc.getListIndex();
				list.remove(index);
				list.addFirst(pc);
			}
			pages.put(url, pc);
		}
	}
	public String[] getTopNpages(int n){
		int length = n;
		if ( n > list.size()){
			// intimate the user.
			System.out.println("Required top N is more than total number of Pages");
			length = list.size();
		}
		
		String[] result = new String[n];
		for(int i = 0; i< length; i++){
			result[i] = list.get(i).getURL();
		}
		return result;
	}

	public class PageCount {
		private int count;
		private int listIndex;
		private String url;
		
		public PageCount(String url, int count){
			this.count= count;
			this.url=url;
		}
		
		public void incrCount(int incr){
			this.count += incr;
		}
		
		public int getCount(){
			return count;
		}
		
		public int getListIndex(){
			return listIndex;
		}
		public String getURL(){
			return url;
		}
		
		public void setURL(String url){
			this.url = url;
		}
		public void setListIndex(int listIndex){
			this.listIndex = listIndex;
		}
		
	}
	
	public static void main(String[] args){
		WebsiteAnalyser wa = new WebsiteAnalyser();
		wa.reportPageAccess("google.com");
		wa.reportPageAccess("gmail.com");
		wa.reportPageAccess("google.com");
		wa.reportPageAccess("gmail.com");
		wa.reportPageAccess("facebook.com");
		wa.reportPageAccess("gmail.com");
		wa.reportPageAccess("google.com");
		String[] result = wa.getTopNpages(4);
		for(int i = 0; i < 3; i++){
			System.out.println(result[i]);
		}
	}
}
