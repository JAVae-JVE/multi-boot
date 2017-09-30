$(function() {
   
    autoLeftNav();
    $(window).resize(function() {
        autoLeftNav();
        //console.log($(window).width())
    });

    /*$('#all_selected_').on('ifChecked', function(event){
		$('#data_table_ tbody input[type="checkbox"]').iCheck('check');
	});
	$('#all_selected_').on('ifUnchecked', function(event){
		$('#data_table_ tbody input[type="checkbox"]').iCheck('uncheck');
	});*/
})
//document.getElementById('iframe的ID').contentWindow.document.getElementById('元素的ID')

// 风格切换

/*$('.tpl-skiner-toggle').on('click', function() {
    $('.tpl-skiner').toggleClass('active');
})

$('.tpl-skiner-content-bar').find('span').on('click', function() {
	$(if_body).attr('class', $(this).attr('data-color'));
    $('body').attr('class', $(this).attr('data-color'));
    saveSelectColor.Color = $(this).attr('data-color');
    // 保存选择项
    storageSave(saveSelectColor);

})*/




// 侧边菜单开关


function autoLeftNav() {
    $('.tpl-header-switch-button').on('click', function() {
        if ($('.left-sidebar').is('.active')) {
            if ($(window).width() > 1024) {
                $('.iframe_content').removeClass('active').css({ width: "83%" });
            }
            $('.left-sidebar').removeClass('active');
        } else {

            $('.left-sidebar').addClass('active');
            if ($(window).width() > 1024) {
                $('.iframe_content').addClass('active').css({ width: "100%" });
            }
        }
    })

    if ($(window).width() < 1024) {
        $('.left-sidebar').addClass('active');
    } else {
        $('.left-sidebar').removeClass('active');
    }
}


// 侧边菜单
$('.sidebar-nav-sub-title').on('click', function() {
    $(this).siblings('.sidebar-nav-sub').slideToggle(80)
        .end()
        .find('.sidebar-nav-sub-ico').toggleClass('sidebar-nav-sub-ico-rotate');
    var plus = $(this).find('.sidebar-nav-sub-ico-plus');
    if($(plus).hasClass("am-icon-plus-square-o")) {
    	$(plus).removeClass("am-icon-plus-square-o");
    	$(plus).addClass("am-icon-minus-square-o");
    } else {
    	$(plus).removeClass("am-icon-minus-square-o");
    	$(plus).addClass("am-icon-plus-square-o");
    } 
});

//返回上个页面
$('.go_history').on('click', function() {
	window.history.go(-1);
});
/**
 * 点击左侧菜单加载content内容
 * @param url
 * @returns
 */
function loadContent(menu) {
	var url = $(menu).attr("url");
	if(url == null || url == '') {
		return ;
	}
	$("#content_").attr("src", url);
	$(".left-sidebar").find("a").removeClass("active").removeClass("sub-active");
	if($(menu).parent().parent().hasClass("sidebar-nav-sub")) {//二级菜单
		$(menu).parent().parent().prev("a").addClass("active");
		$(menu).addClass("sub-active");
	}else {//一级菜单
		$(menu).addClass("active");
	}
}

/**
 * iframe内部的页面跳转
 * @param url
 * @returns
 */
function jumpUrl(url) {
	$('#content_', parent.document).attr("src", url);
}

/**
 * 全局后台返回成功或失败的alert
 * @param success
 * @param msg
 * @returns
 */
function SweetAlert(success, msg) {
	if(success) {
		swal({
			text: msg,
			icon: "success",
			button: false,
			timer: 2000
		});
	} else {
		swal({
			text: msg,
			icon: "error",
			closeOnClickOutside: false,
			button: {
			    text: "确定"
			}
			
		});
	}
	
}

/**
 * 警告提示
 * @param msg
 * @returns
 */
function SweetWarning(msg) {
	swal({
		text: msg,
		icon: "warning",
		closeOnClickOutside: false,
		button: {
		    text: "确定"
		}
		
	});
}


/**
 * 
 * @param msg
 * @param nextMethod
 * @returns
 */
function SweetComfirm(msg, nextMethod) {
	swal({
		text: msg,
		icon: "warning",
		dangerMode: true,
		closeOnClickOutside: false,
		buttons: {
			cancel: {
			    text: "取消",
			    value: false,
			    visible: true,
			    className: "",
			    closeModal: true,
			 },
			 confirm: {
			    text: "确定",
			    value: true,
			    visible: true,
			    className: "",
			    closeModal: true
			  }
		}
	}).then((value) => {
		  if (value) {
			  nextMethod();
		  }
	});
}

/**
 * 表单验证公共方法
 * @param formId 表单id
 * @param async 是否异步
 * @param exeFunction 回调方法，同步时回调无效
 * @returns
 */
function formValidate(formId, async, exeFunction) {
	$('#' + formId).validator({
	    onValid: function(validity) {
	      $(validity.field).closest('.am-form-group').find('small').hide();
	    },

	    onInValid: function(validity) {
	      var $field = $(validity.field);
	      var $alert = $field.next('small');
	      // 使用自定义的提示信息 或 插件内置的提示信息
	      var msg = $field.data('validationMessage') || this.getValidationMessage(validity);

	      if (!$alert.length) {
	        $alert = $('<small class="error-tip"></small>').hide().
	          appendTo($field.parent());
	      }

	      $alert.html(msg).show();
	    },
	    submit: function() {
	    	if(async) {
	    		//不管验证成功还是失败都阻止表单提交，采用ajax异步提交
	    		if(this.isFormValid()) {
		    		exeFunction();
		    	}
		    	return false;
	    	}else {
	    		if(!this.isFormValid()) {
	    			return false;
	    		}
	    	}
	    }
	});
}

var CommonUtil={
	    post:function(url,async,data,callBak){
	         $.ajax({
			     type:"POST",
			     url:url,
			     cache:false,
			     async:async,
			     data:data,
			     success:callBak
	         });
	    },postComplex:function(url,async,contentType,data,callBak){
		    $.ajax({
			     type:"POST",
			     url:url,
			     cache:false,
			     async:async,
			     contentType:contentType,
			     data:data,
			     success:callBak
		    });
		},get:function(url,async,data,callBak){
	    	 $.ajax({
			     type:"GET",
			     url:url,
			     cache:false,
			     async:async,
			     data:data,
			     success:callBak
			});
	    },parseJSON:function(data){//类型转化
	    	if(typeof data != "object"){
	    		data = $.parseJSON(data);
	    	}
	    	return data;
	    },setValue:function(id,value){
	    	document.getElementById(id).value=value;
	    },getValue:function(id){
	    	 return document.getElementById(id).value;
	    },isEmpty:function(value){
	    	  if(value!=null&&value!=''){
	    		  return false;
	    	  }else{
	    		  return true;
	    	  }
	    },
	    //大于零的数，可有小数点
	    moreThanZeroNumber:function(obj) {
	    	var value = $(obj).val();
	    	if(isNaN(value) || !/^[1-9]/.test(value)) {
	    		$(obj).val(value.substr(0,value.length-1));
	    	}
	    },
	    trim:function(str){
	        return str.replace(/(^\s*)|(\s*$)/g, "");
	    },email:function(str){ 
	       var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
           return reg.test(str);
	    },phone:function(str){
	      var reg = /^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;
	      return reg.test(str);
	    },tel:function(str){
	      var reg = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
	      return reg.test(str);
	    },
	    //加    
	    floatAdd:function(arg1,arg2){    
	         var r1,r2,m;    
	         try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;}    
	         try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;}    
	         m=Math.pow(10,Math.max(r1,r2));    
	         return (arg1*m+arg2*m)/m;    
	    },    
	    //减    
	    floatSub:function(arg1,arg2){    
	        var r1,r2,m,n;    
	        try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;}    
	        try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;}    
	        m=Math.pow(10,Math.max(r1,r2));    
	        //动态控制精度长度    
	        n=(r1>=r2)?r1:r2;    
	        return ((arg1*m-arg2*m)/m).toFixed(n);    
	    },    
	    //乘    
	    floatMul:function(arg1,arg2)   {     
	        var m=0,s1=arg1.toString(),s2=arg2.toString();     
	        try{m+=s1.split(".")[1].length;}catch(e){}     
	        try{m+=s2.split(".")[1].length;}catch(e){}     
	        return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);     
	    },    
	    //除   
	    floatDiv:function(arg1,arg2){     
	          var t1=0,t2=0,r1,r2;     
	          try{t1=arg1.toString().split(".")[1].length;}catch(e){}     
	          try{t2=arg2.toString().split(".")[1].length;}catch(e){}     
	            
	          r1=Number(arg1.toString().replace(".",""));  
	       
	          r2=Number(arg2.toString().replace(".",""));     
	          return (r1/r2)*Math.pow(10,t2-t1);     
	    }, 
	    checked:function(flag){
	    	var checked = $('#data_table_ tbody input[type="checkbox"]');
	    	var ids = '';
	    	$.each(checked, function(i, n){
    			if($(n).is(':checked') && $(n).val() != 'on'){
	    			ids += $(n).val() + ',';
	    		}
			});
	    	//编辑
	    	if(flag == 'E'){
	    		if(ids == '') {
		    		this.alert(200,null,'消息','请选择需要编辑的行记录');
		    		return '';
		    	}else{
		    		ids = ids.substring(0,ids.length-1);
			    	if(ids.split(",").length > 1) {
			    		this.alert(200,null,'消息','只能选择唯一的行记录');
			    		return '';
			    	}else if(ids.split(",").length == 1){
			    		return ids;
			    	}
		    	}
	    	//删除
	    	}else if(flag == 'D'){
	    		if(ids == '') {
		    		this.alert(200,null,'消息','请至少选择一条行记录');
		    		return '';
		    	}else{
		    		ids = ids.substring(0,ids.length-1);
			    	return ids;
		    	}
	    	}
	    }
};


//倒计时
function timer(intDiff, box, otherHtml){
    window.setInterval(function(){
    var day=0,
        hour=0,
        minute=0,
        second=0;      
    if(intDiff > 0){
        day = Math.floor(intDiff / (60 * 60 * 24));
        hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
        minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
        second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
    }
    if (minute <= 9) minute = '0' + minute;
    if (second <= 9) second = '0' + second;
    $(box).html((day == 0 ? '' : day + '天') + (hour == 0 ? '' : hour+'时') + minute+'分' + second+'秒' + otherHtml);
    intDiff--;
    }, 1000);
}


//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function (fmt) { //author: meizz 
 var o = {
     "M+": this.getMonth() + 1, //月份 
     "d+": this.getDate(), //日 
     "h+": this.getHours(), //小时 
     "m+": this.getMinutes(), //分 
     "s+": this.getSeconds(), //秒 
     "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
     "S": this.getMilliseconds() //毫秒 
 };
 if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
 for (var k in o)
 if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
 return fmt;
}
