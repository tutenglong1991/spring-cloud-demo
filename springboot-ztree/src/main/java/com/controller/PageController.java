package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.WebSocketServer;

import lombok.Data;

/**
 * .页面控制转发
 * 
 * @description:
 * @author: zhangsike
 * @date: 2018/11/17 22:18:55
 */
@Controller
public class PageController {

	private ThreadLocal<Integer> index = new ThreadLocal<>();
	@Value("${self-static-dir}")
	private String staticDir;

	@Autowired
	private WebSocketServer ws;

	@PostConstruct
	public void Init() {
//		ScheduledExecutorService executorService=Executors.newSingleThreadScheduledExecutor();
//		Runnable runnable=()->{
//			ws.sendtoAll(System.currentTimeMillis()+"");
//		};
//		executorService.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
	}

	@GetMapping("/")
	public String index(Model model) {
		return "index";
	}

	@GetMapping("/test")
	@ResponseBody
	public String test() throws IOException {
		ws.sendtoAll("hello");
		return "index";
	}

	@GetMapping("/web/{path}")
	public String index(@PathVariable String path, Model model) {

		return path;
	}

	@GetMapping("/api/excute")
	@ResponseBody
	public void index(@RequestParam List<String> id) {
		ws.sendtoAll("正在执行：" + id);
	}

	@GetMapping("/api/files")
	@ResponseBody
	private List<Node> getFileList() {
		index.set(0);
		String dir = "E:\\eclipse-workspace\\3d-cloud\\src";
		Node root = new Node(index.get() + "", "src");
		root.setChildren(new ArrayList<>());
		listFiles(dir, root, index);
		return root.getChildren();
	}

	/**
	 * 获取文件列表，树结构
	 * 
	 * @param dir
	 * @param fileList
	 * @author: zhangsike
	 * @date: 2018/11/28 10:51:56
	 */
	public static void listFiles(String dir, Node node, ThreadLocal<Integer> index) {

		File file = new File(dir);
		File[] array = file.listFiles();

		for (int i = 0; i < array.length; i++) {
			if (array[i].isFile()) {
				index.set(index.get() + 1);
				Node tmpNode = new Node(index.get() + "", array[i].getName());
				node.getChildren().add(tmpNode);
			} else if (array[i].isDirectory()) {
				index.set(index.get() + 1);
				Node tmpNode = new Node(index.get() + "", array[i].getName());
				tmpNode.setParent(true);
				tmpNode.setChildren(new ArrayList<>());
				node.getChildren().add(tmpNode);
				listFiles(array[i].getPath(), tmpNode, index);
			}
		}
	}

	@Data
	static class Node {
		private String id;
		private String name;
		private String url;
		private boolean isParent = false;
		private List<Node> children;

		public Node(String id, String name) {
			super();
			this.id = id;
			this.name = name;
		}

	}
}
