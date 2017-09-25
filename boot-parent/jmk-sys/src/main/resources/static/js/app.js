$(function() {
   
    autoLeftNav();
    $(window).resize(function() {
        autoLeftNav();
        //console.log($(window).width())
    });

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