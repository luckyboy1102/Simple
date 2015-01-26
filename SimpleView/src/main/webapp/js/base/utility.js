;(function ($) {
    $.extend({
        //对象克隆
        objectClone: {
            //浅克隆对象
            clone: function (result, source) {
                for (var key in source) {
                    result[key] = source[key];
                }
                return result;
            },

            //判断对象的类型
            is: function (obj, type) {
                var toString = Object.prototype.toString, undefined;
                return (type === "Null" && obj === null) ||
                   (type === "Undefined" && obj === undefined) ||
                   (type === "Function" && typeof document.getElementById === "object" ?
                        /^\s*\bfunction\b/.test(obj + "") :
                        toString.call(obj).slice(8, -1) === type
                   )

            },

            //深克隆对象
            deepClone: function (result, source) {
                var copy = null;
                for (var key in source) {
                    copy = source[key];
                    if (result === copy)
                        continue;

                    if ($.objectClone.is(copy, "Object")) {
                        result[key] = arguments.callee(result[key] || {}, copy);
                    } else if ($.objectClone.is(copy, "Array")) {
                        result[key] = arguments.callee(result[key] || [], copy);
                    } else {
                        result[key] = copy;
                    }
                }
                return result;
            }
        },
        
        ajaxCall: function(url, data, success, error, async) {
        	$.ajax({
        		type: "POST",
                //contentType: "application/json; charset=utf-8",
                dataType: "json",
        		url: url,
        		data: (data == undefined || data == null) ? "{}" : data,
        		async: (async === false) ? false : true,
        		success: function(data) {
        			if($.isFunction(success))
        				success(data);
        		},
        		error: function(result, status) {
        			if($.isFunction(error))
        				error(result,status);
        		}
        	});
        },

        //url处理工具
        urlHelper: {
            //获取url的各种信息
            urlInfo: function (url) {
                var a = document.createElement('a');
                a.href = url;
                return {
                    source: url,
                    protocol: a.protocol.replace(':', ''),
                    host: a.hostname,
                    port: a.port,
                    search: a.search,
                    params: (function () {
                        var ret = {},
                    seg = a.search.replace(/^\?/, '').split('&'),
                    len = seg.length, i = 0, s;
                        for (; i < len; i++) {
                            if (!seg[i]) { continue; }
                            s = seg[i].split('=');
                            ret[s[0]] = s[1];
                        }
                        return ret;
                    })(),
                    file: (a.pathname.match(/\/([^\/?#]+)$/i) || [, ''])[1],
                    hash: a.hash.replace('#', ''),
                    path: a.pathname.replace(/^([^\/])/, '/$1'),
                    relative: (a.href.match(/tps?:\/\/[^\/]+(.+)/) || [, ''])[1],
                    segments: a.pathname.replace(/^\//, '').split('/')
                };
            },

            //取得URL的参数，以对象形式返回！
            getParam: function (url) {
                var result = {}
                var param = /([^?=&]+)=([^&]+)/ig;
                var match;
                while ((match = param.exec(url)) != null) {
                    result[match[1]] = match[2];
                }
                return result;
            },

            //获取地址栏参数值
            getLocationParam: function (name) {
                var sUrl = window.location.search.substr(1);
                var r = sUrl.match(new RegExp("(^|&)" + name + "=([^&]*)(&|$)"));
                return (r == null ? null : unescape(r[2]));
            }
        },

        //文本长和宽
        textSizeCalculator: {
            textSize: function(text) {
                var span = $("#textSpan");
                if(span.length == 0) {
                    span = $("<span id=\"textSpan\" />");
                    span.css({"visibility": "hidden", "position": "absolute"});
                    $(document.body).append(span);
                }
                var size = {
                    width: 0,
                    height: 0
                };
                span.text("");
                size.width = span[0].offsetWidth;
                size.height = span[0].offsetHeight;

                span.text(text);
                size.width = span[0].offsetWidth - size.width;
                size.height = span[0].offsetHeight - size.height;

                return size;
            },

            close: function() {
                var span = $("#textSpan");
                if(span.length > 0) {
                    span.remove();
                } 
            }
        },

        format: {
            StrformatReg: /\\?\{([^{}]+)\}/gm,
            //格式化字符串，Format("He{0}{1}o", "l", "l") 返回 Hello
            stringFormat: function(str, params) {
                var Arr_slice = Array.prototype.slice;
                var array = Arr_slice.call(arguments, 1);
                return str.replace(this.StrformatReg, function (match, name) {
                    if (match.charAt(0) == '\\')
                        return match.slice(1);
                    var index = Number(name);
                    if (index >= 0)
                        return array[index];
                    if (params && params[name])
                        return params[name];
                    return '';
                });
            },

            //格式化日期: y|Y 年; m 月; d|D 日; H|h 小时; M 分; S|s 秒; ms|MS 毫秒; wk|WK 星期;
            dateFormat: function(format, date) {
                if (!date) date = new Date();

                var year = date.getFullYear();
                var month = date.getMonth() + 1;
                var day = date.getDate();
                var hour = date.getHours();
                var minute = date.getMinutes();
                var second = date.getSeconds();
                var millisecond = date.getMilliseconds();
                var weekday = date.getDay();

                var monthFormat = 0;
                var dayFormat = 0;
                var hourFormat = 0;
                var minuteFormat = 0;
                var secondFormat = 0;
                for (var i = 0; i < format.length; i++) {
                    var ch = format[i];
                    if ((ch == 'm' || ch == 'M') && (format[i + 1] == 's' || format[i + 1] == 'S'))
                        continue;
                    if (ch == 'm')
                        monthFormat++;
                    else if (ch == 'd' || ch == 'D')
                        dayFormat++;
                    else if (ch == "H" || ch == 'h')
                        hourFormat++;
                    else if (ch == 'M')
                        minuteFormat++;
                    else if (ch == 'S' || ch == 's')
                        secondFormat++;
                }
                if (monthFormat == 2 && parseInt(month, 10) < 10)
                    month = "0" + month;
                if (dayFormat == 2 && parseInt(day, 10) < 10)
                    day = "0" + day;
                if (hourFormat == 2 && parseInt(hour, 10) < 10)
                    hour = "0" + hour;
                if (minuteFormat == 2 && parseInt(minute, 10) < 10)
                    minute = "0" + minute;
                if (secondFormat == 2 && parseInt(second, 10) < 10)
                    second = "0" + second;

                var result = format;
                result = result.replace(/y+/i, year);
                result = result.replace(/m+/, month);
                result = result.replace(/d+/i, day);
                result = result.replace(/H+/i, hour);
                result = result.replace(/M+/, minute);
                result = result.replace(/S+/i, second);
                result = result.replace(/ms/i, millisecond);
                result = result.replace(/wk/i, weekday);
                return result;
            }
        },

        //获取html元素的绝对位置
        getGoords: function(el) {
            var box = el.getBoundingClientRect();
            var doc = el.ownerDocument;
            var body = doc.body;
            var html = doc.documentElement;
            var clientTop = html.clientTop || body.clientTop || 0;
            var clientLeft = html.clientLeft || body.clientLeft || 0;
            var top  = box.top  + (self.pageYOffset || html.scrollTop  ||  body.scrollTop ) - clientTop;
            var left = box.left + (self.pageXOffset || html.scrollLeft ||  body.scrollLeft) - clientLeft;
            return { 'top': top, 'left': left };
        },
        
        //GUID获取
        guid : {
        	newGuid: function () {
    	        function S4() {
    	            return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
    	        }
    	        return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
    	    },
    	    newGuidForOracle : function(){
    	    	function S4() {
    	            return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
    	        }
    	        return (S4() + S4() + S4()  + S4()  + S4() + S4() + S4() + S4());
    	    },
        	emptyGuid: "00000000-0000-0000-0000-000000000000"
        },

        //java的写法
        getHashCode: function(str) {
            if(typeof str !== "string")
                str = str.toString();
            var h = 0;
            var len = str.length;
            for(var i = 0; i < len; i++) {
                h = 31 * h + str.charCodeAt(i);
            }
            return h;
        },

        controlDisabled: function(el, isDisabled) {
            if (arguments.length > 2) {
                if (el.jquery === undefined)
                    el = $(el);
                for (var i = 2; i < arguments.length; i++) {
                    arguments[i] += "";
                    if (arguments[i].toLowerCase() === "select"||arguments[i].toLowerCase() === "button") {
                        el.find(arguments[i]).attr("disabled", isDisabled);
                    } else {
                        el.find(arguments[i]).attr("readonly", isDisabled);
                    }
                }
            }
        }
    });
})(jQuery);

