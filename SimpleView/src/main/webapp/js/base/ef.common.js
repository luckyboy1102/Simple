/// <reference path="../JQuery/jquery-1.4.2-vsdoc.js" />

//以下扩展了一些共同的常用的jQuery客户端方法
$.extend({
    callMethod: function (url, inputArgs, handler, errorHandler, isAsyn) {
        var jsonData;
        if (isAsyn == undefined || isAsyn == null) isAsyn = true;
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            url: url,
            async: isAsyn,
            data: (inputArgs == null || inputArgs == undefined) ? "{}" : inputArgs,
            success: function (data) {
                //将JSON序列转行为js对象数组，如果data.d返回的是一个对象，需要进行如下的包装，否则会报“缺少分号”的异常
                jsonData = eval('(' + data.d + ')');
                handler(jsonData);
            },
            error: function (result, status) {
                try {
                    //未经过身份验证，或者操作超过身份验证时间，则任何对服务器端的访问都会收到401(Unauthorized)错误。
                    if (result.status == 401 || result.status == 302) {
                        alert("等待操作超时，您需要重新登录");
                        window.top.location = top.location.href;
                    }
                    else {
                        if (errorHandler) {
                            if (result.responseText) {
                                var e1 = eval('(' + result.responseText + ')');
                                errorHandler(e1);
                            }
                            else {
                                var e2 = { Message: result.status };
                                errorHandler(e2);
                            }
                        }
                    }
                }
                catch (e) {
                }
            }
        });
    },

    callMethod2: function (url, inputArgs, handler, errorHandler, isAsyn) {
        var jsonData;
        if (isAsyn == undefined || isAsyn == null) isAsyn = true;
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            url: url,
            async: isAsyn,
            data: (inputArgs == null || inputArgs == undefined) ? "{}" : inputArgs,
            success: function (data) {
                //将JSON序列转行为js对象数组，如果data.d返回的是一个对象，需要进行如下的包装，否则会报“缺少分号”的异常
                jsonData = eval(data.d);
                handler(jsonData);
            },
            error: function (result, status) {
                try {
                    //未经过身份验证，或者操作超过身份验证时间，则任何对服务器端的访问都会收到401(Unauthorized)错误。
                    if (result.status == 401 || result.status == 302) {
                        alert("等待操作超时，您需要重新登录");
                        window.top.location = top.location.href;
                    }
                    else {
                        if (errorHandler) {
                            if (result.responseText) {
                                var e1 = eval('(' + result.responseText + ')');
                                errorHandler(e1);
                            }
                            else {
                                var e2 = { Message: result.status };
                                errorHandler(e2);
                            }
                        }
                    }
                }
                catch (e) {
                }
            }
        });
    },

    //调用服务器端Web服务方法，返回简单字符串以及与字符串兼容的的类型值
    callMethod3: function (url, inputArgs, handler, errorHandler, isAsyn) {
        var jsonData;
        if (isAsyn == undefined || isAsyn == null) isAsyn = true;
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            url: url,
            async: isAsyn,
            data: (inputArgs == null || inputArgs == undefined) ? "{}" : inputArgs,
            success: function (data) {
                //将JSON序列转行为js对象数组，如果data.d返回的是一个对象，需要进行如下的包装，否则会报“缺少分号”的异常
                jsonData = data.d;
                handler(jsonData);
            },
            error: function (result, status) {
                try {
                    //未经过身份验证，或者操作超过身份验证时间，则任何对服务器端的访问都会收到401(Unauthorized)错误。
                    if (result.status == 401 || result.status == 302) {
                        alert("等待操作超时，您需要重新登录");
                        window.top.location = top.location.href;
                    }
                    else {
                        if (errorHandler) {
                            if (result.responseText) {
                                var e1 = eval('(' + result.responseText + ')');
                                errorHandler(e1);
                            }
                            else {
                                var e2 = { Message: result.status };
                                errorHandler(e2);
                            }
                        }
                    }
                }
                catch (e) {
                }
            }
        });
    },

    getIframeWindow: function (iframeId) {
        return document.getElementById(iframeId).contentWindow;
    },

    getIframeDocument: function (iframeId) {
        return $.getIframeWindow(iframeId).document;
    },

    //查找在iframe页面内的HTML元素，返回值是jquery对象
    getElementInIframe: function (iframeId, eleId) {
        var iframePage = $.getIframeDocument(iframeId);
        if (iframePage) {
            return $("#" + eleId, $(iframePage));
        }
    },

    callFunctionInIframe: function (iframeId, funName, args) {
        var iframeWin = $.getIframeWindow(iframeId);
        if (iframeWin) {
            var r;
            var f = iframeWin[funName];
            if (f) {
                if (args) {
                    r = f(args);
                }
                else {
                    r = f();
                }
                return r;
            }
        }
    },

    callFunctionInHost: function (funName, args) {
        var f = parent[funName];
        if (f) {
            if (args) {
                return f(args);
            }
            else {
                return f();
            }
        }
    },
    showDialog: function (url, args, width, height) {
        if (!width) width = 800;
        if (!height) height = 600;
        var rValue = window.showModalDialog(url, args, "scroll:0;status:0;help:0;resizable:1;dialogWidth:" + width + "px;dialogHeight:" + height + "px;");
        return rValue;
    },
    hasFunction: function (funcCode) {
        var ps = $("#__CYAUTHRESULT").val(), permit = false;
        if (ps == undefined || ps == null || ps == "") return false;

        var pj = eval('(' + ps + ')');
        if (pj[funcCode] == true) {
            permit = true;
        }
        return permit;
    },
    newGuid: function () {
        function S4() {
            return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
        }
        return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
    },
    emptyGuid: "00000000-0000-0000-0000-000000000000"
});

//获取url参数
function getArgs() {
    var argsArr = new Object();
    //获取URL中的查询字符串参数
    var query = window.location.search;
    //去掉查询字符串中的？号。
    query = query.substring(1);

    var pairs = query.split("&");
    for (var i = 0; i < pairs.length; i++) {
        var sign = pairs[i].indexOf("=");
        //如果没有找到=号，那么就跳过，跳到下一个字符串（下一个循环）。
        if (sign == -1) {
            continue;
        }
        var aKey = pairs[i].substring(0, sign);
        var aValue = pairs[i].substring(sign + 1);

        argsArr[aKey] = aValue;
    }
    return argsArr;
}

function numtochinese(Num) {
    Num = String(Num);

    part = String(Num).split(".");
    newchar = "";
    //小数点前进行转化 
    for (i = part[0].length - 1; i >= 0; i--) {
        if (part[0].length > 10) { alert("位数过大，无法计算"); return ""; }
        tmpnewchar = ""
        perchar = part[0].charAt(i);
        switch (perchar) {
            case "0": tmpnewchar = "零" + tmpnewchar; break;
            case "1": tmpnewchar = "壹" + tmpnewchar; break;
            case "2": tmpnewchar = "贰" + tmpnewchar; break;
            case "3": tmpnewchar = "叁" + tmpnewchar; break;
            case "4": tmpnewchar = "肆" + tmpnewchar; break;
            case "5": tmpnewchar = "伍" + tmpnewchar; break;
            case "6": tmpnewchar = "陆" + tmpnewchar; break;
            case "7": tmpnewchar = "柒" + tmpnewchar; break;
            case "8": tmpnewchar = "捌" + tmpnewchar; break;
            case "9": tmpnewchar = "玖" + tmpnewchar; break;
        }
        switch (part[0].length - i - 1) {
            case 0: tmpnewchar = tmpnewchar + "元"; break;
            case 1: if (perchar != 0) tmpnewchar = tmpnewchar + "拾"; break;
            case 2: if (perchar != 0) tmpnewchar = tmpnewchar + "佰"; break;
            case 3: if (perchar != 0) tmpnewchar = tmpnewchar + "仟"; break;
            case 4: tmpnewchar = tmpnewchar + "万"; break;
            case 5: if (perchar != 0) tmpnewchar = tmpnewchar + "拾"; break;
            case 6: if (perchar != 0) tmpnewchar = tmpnewchar + "佰"; break;
            case 7: if (perchar != 0) tmpnewchar = tmpnewchar + "仟"; break;
            case 8: tmpnewchar = tmpnewchar + "亿"; break;
            case 9: tmpnewchar = tmpnewchar + "拾"; break;
        }
        newchar = tmpnewchar + newchar;
    }
    if (Num.indexOf(".") != -1) {
        if (part[1].length > 2) {
            alert("小数点之后只能保留两位,系统将自动截段");
            part[1] = part[1].substr(0, 2)
        }
        for (i = 0; i < part[1].length; i++) {
            tmpnewchar = ""
            perchar = part[1].charAt(i)
            switch (perchar) {
                case "0": tmpnewchar = "零" + tmpnewchar; break;
                case "1": tmpnewchar = "壹" + tmpnewchar; break;
                case "2": tmpnewchar = "贰" + tmpnewchar; break;
                case "3": tmpnewchar = "叁" + tmpnewchar; break;
                case "4": tmpnewchar = "肆" + tmpnewchar; break;
                case "5": tmpnewchar = "伍" + tmpnewchar; break;
                case "6": tmpnewchar = "陆" + tmpnewchar; break;
                case "7": tmpnewchar = "柒" + tmpnewchar; break;
                case "8": tmpnewchar = "捌" + tmpnewchar; break;
                case "9": tmpnewchar = "玖" + tmpnewchar; break;
            }
            if (i == 0) tmpnewchar = tmpnewchar + "角";
            if (i == 1) tmpnewchar = tmpnewchar + "分";
            newchar = newchar + tmpnewchar;
        }
    }
    while (newchar.search("零零") != -1)
        newchar = newchar.replace("零零", "零");
    newchar = newchar.replace("零亿", "亿");
    newchar = newchar.replace("亿万", "亿");
    newchar = newchar.replace("零万", "万");
    newchar = newchar.replace("零元", "元");
    newchar = newchar.replace("零角", "");
    newchar = newchar.replace("零分", "");

    if (newchar.charAt(newchar.length - 1) == "元" || newchar.charAt(newchar.length - 1) == "角")
        newchar = newchar + "整"
    return newchar;
}
