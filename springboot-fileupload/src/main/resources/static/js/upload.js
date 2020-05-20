$(document).ready(function() {
	
	
	
	
	// 获取文件上传相关组件
	var fileInput=$("#file");
	var processBar = $("#progressBar");
	var uploadBtn = $("button[name=upload]");
	var cancelUploadBtn = $("button[name=cancelUpload]");
	var speedLab = $("#showSpeed")
	var fileNameLab = $("label[name=upfileName]")
	var fileSizeLab = $("label[name=upfileSize]")
	var fileTypeLab = $("label[name=upfileType]")

	
	var url = '/upload'
	var upload = UploadCommon(url,
			processBar, 
			speedLab, 
			uploadBtn, 
			cancelUploadBtn)
	
	// 模型文件选择框变更事件
	fileInput.change(function() {
				var fileObj = fileInput.get(0).files[0]// js获取文件对象
				if (fileObj) {
					var fileSize = getSize(fileObj.size)
					fileNameLab.text('文件名：' + fileObj.name)
					fileSizeLab.text('文件大小：' + fileSize);
					fileTypeLab.text('文件类型：' + fileObj.type)
					uploadBtn.attr('disabled', false)
					upload.setProgressValue(0)
				}
			});
	// 上传文件按钮点击的时候
	uploadBtn.click(function() {
		upload.setProgressValue(0)
		var file = fileInput.get(0).files[0]
		if (file == null) {
			alert('请添加文件')
			return
		}
		var size = file.size
		if (size > 100 * 1024 * 1024) {
			alert("模型切图文件不能大于100M");
			return;
		}
		
		// 上传按钮禁用
		uploadBtn.attr('disabled', true);
		// FormData 对象
		var formData = new FormData();
		formData.append('file', file); 
		// 上传文件
		upload.uploadFile(formData)
	});
	
	
	function getSize(size) {
		var fileSize = '0KB';
		if (size > 1024 * 1024) {
			fileSize = (Math.round(size / (1024 * 1024))).toString() + 'MB';
		} else {
			fileSize = (Math.round(size / 1024)).toString() + 'KB';
		}
		return fileSize;
	}
});
