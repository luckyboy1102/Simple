var AjaxClass = function(url, callBackPoint) {
	// 共有变量
	this.url = url;
	this.callBackPoint = callBackPoint;
	// 私有变量
	// 定义XMLHttpRequest对象
	this.http_request = false;
	
	/**
	 * 设置请求地址 类型：私有函数
	 */
	this.setRequestUrl = function(argUrl) {
		this.url = argUrl;
	};
	
	/**
	 * 设置回调函数 类型：私有函数
	 */
	this.setCallBackPoint = function(argPoint) {
		this.callBackPoint = argPoint;
	};
	
	/**
	 * 描述：创建ajax并发送请求地址 类型：公开函数
	 */
	this.sendRequest = function() {
		var self = this;
		// 开始初始化XMLHttpRequest对象
		if (window.XMLHttpRequest) {
			// Mozilla等浏览器初始化XMLHttpRequest过程
			this.http_request = new XMLHttpRequest();
			// 有些版本的Mozilla浏览器处理服务器返回的未包含XML mime-type头部信息的内容时会出错.
			// 因此,要确保返回的内容包含text/xml信息.
			if (this.http_request.overrideMimeType) {
				this.http_request.overrideMimeType("text/xml");
			}
		} else if (window.ActiveXObject) {
			// IE浏览器初始化XMLHttpRequest过程
			try {
				this.http_request = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					this.http_request = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
				window.alert("不能创建XMLHttpRequest对象实例!");
				}
			}
		}
		// 异常,创建对象失败
		if (!this.http_request) {
			window.alert("不能创建XMLHttpRequest对象实例!");
			return false;
		}
		// 指定响应处理函数
		this.http_request.onreadystatechange = function() {
			// 判断对象状态
			if (self.http_request.readyState == 4) {
				// 判断HTTP状态码
				if (self.http_request.status == 200) {
					// 信息已经成功返回
					// window.document.write(http_request.responseText);
					// alert(self.http_request.responseText);
					if (typeof(self.callBackPoint) == "function") {
						// 存在
						self.callBackPoint(self.http_request.responseText);
					} else {
						// 不存在
						self.defaultStatus(self.http_request.responseText);
					}
				} else {
					// 请求页面有问题
					alert("您所请求的页面有异常!错误状态:" + self.http_request.status);
				}
			}
		};
		// 发送HTTP请求信息
		this.http_request.open("GET", this.url, true);
		this.http_request.send(null);
	};

	/**
	 * 如果没有提供自定义回调函数使用此默认的回调函数 类型：私有函数
	 */
	this.defaultCallBackPoint = function(responseText) {
		alert("缺少重写方法：function callBackPoint(resultData){...}");
	};
};

function setInnerHTML(el, htmlCode) { 
	var ua = navigator.userAgent.toLowerCase(); 
	if (ua.indexOf('msie') >= 0 && ua.indexOf('opera') < 0) { 
		htmlCode = '<div style="display:none">for IE</div>' + htmlCode; 
		htmlCode = htmlCode.replace(/<script([^>]*)>/gi, '<script$1 defer>'); 
		el.innerHTML = htmlCode; 
		el.removeChild(el.firstChild); 
	} else { 
		var el_next = el.nextSibling; 
		var el_parent = el.parentNode; 
		el_parent.removeChild(el); 
		el.innerHTML = htmlCode; 
		if (el_next) { 
			el_parent.insertBefore(el, el_next);
		} else { 
			el_parent.appendChild(el); 
		} 
	} 
}