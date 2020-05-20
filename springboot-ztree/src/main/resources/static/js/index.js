/**
 * 
 */

$(function() {
	var lfPanel = $(".left-div")
	var rgPanel = $(".right-div")
	var openOrCloseBtn = $("#openOrCloseBtn")
	var runBtn = $("#runBtn")
	var text = $("#textConsole")
	var clearLogBtn = $("#clearLogBtn")

	var lfHide = false

	openOrCloseBtn.click(function() {
		let _this = $(this)
		if (lfHide) {
			lfHide = !lfHide
			lfPanel.show()
			rgPanel.removeClass("col-md-12")
			rgPanel.addClass("col-md-8")
			_this.text("隐藏")
		} else {
			lfPanel.hide()
			lfHide = !lfHide
			rgPanel.removeClass("col-md-8")
			rgPanel.addClass("col-md-12")
			_this.text("展开")
		}
	})

	// 选中节点checkBox事件
	function onCheck(e, treeId, treeNode) {
		log(treeNode.name + "==" + treeNode.id + "\n");
	}
	// 选中节点事件
	function onClick(event, treeId, treeNode, clickFlag) {
		log("[ " + getTime() + " onClick ]&nbsp;&nbsp;clickFlag = " + clickFlag + " (" + (clickFlag === 1 ? "普通选中" : (clickFlag === 0 ? "<b>取消选中</b>" : "<b>追加选中</b>")) + ")");
	}
	function getTime() {
		var now = new Date(), h = now.getHours(), m = now.getMinutes(), s = now.getSeconds();
		return (h + ":" + m + ":" + s);
	}

	function log(str) {
		text.append(str+"\n")
		text.scrollTop(text[0].scrollHeight);
	}
	var setting = {
		check : {
			enable : true
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onCheck : onCheck,
			onClick : onClick
		}
	};

	var zTree
	// 初始化tree
	$.get("/api/files", function(nodes) {
		$.fn.zTree.init($("#treeDemo"), setting, nodes);
		zTree = $.fn.zTree.getZTreeObj("treeDemo")
		zTree.setting.check.chkboxType = {
			"Y" : "ps",
			"N" : "ps"
		};
		setting.check.enable = true
		zTree.expandAll(true);
	});

	// 运行按钮事件
	runBtn.click(function() {
		var nodes = zTree.getCheckedNodes(true);
		let list = []
		for (index in nodes) {
			let node = nodes[index]
			if (!node.children)
				list.push(node.name + " " + node.id)
		}
		$.ajax({
			url: "/api/excute",
			type:"GET",
			data: {"id":list},
			traditional: true,//使用参数序列化的传统样式，否则传递数组有问题
			success: function(result,status,xhr) {
				
			},
			error: function(xhr,status,error){
				
			}
		})
	})
	// 清除日志按钮事件
	clearLogBtn.click(function() {
		text.text("")
	})

	// *******************************websocket*********************
	if (!('WebSocket' in window)) {
		alert('当前浏览器不支持webSocket');
	}
	
	function randomStr(len) {
		　　len = len || 32;
			// 默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1
		　　var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';    
		　　var maxPos = $chars.length;
		　　var str = '';
		　　for (i = 0; i < len; i++)
		　　　　str += $chars.charAt(Math.floor(Math.random() * maxPos));
		　　return str;
		}
	
	var websocket = null;

	// 打开websocket
	function openConnect() {
		// websocket = new WebSocket("ws://localhost:80/websocket/log");
		// 断线自动重连机制，来自reconnecting-websocket.min.js库
		websocket = new ReconnectingWebSocket("ws://localhost:80/websocket/"+randomStr(10));
		websocket.onerror = function(event) {
			log("连接异常"+event.dat);
		};

		websocket.onopen = function(event) {
			log("开始连接");
		}
		websocket.onmessage = function(event) {
			log(event.data)
		}
		websocket.onclose = function() {
			log("连接关闭");
		}
		window.onbeforeunload = function() {
			websocket.close();
		}
	}
	function closeWebSocket() {
		websocket.close();
	}
	function send() {
		var msg="test msg"
		websocket.send(msg);
	}
	
	openConnect()
})