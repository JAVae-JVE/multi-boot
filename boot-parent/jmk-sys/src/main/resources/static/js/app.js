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
})

/**
 * 点击左侧菜单加载content内容
 * @param url
 * @returns
 */
function loadContent(menu) {
	$("#content_").attr("src", $(menu).attr("url"));
	$(".left-sidebar").find("a").removeClass("active").removeClass("sub-active");
	if($(menu).parent().parent().hasClass("sidebar-nav-sub")) {//二级菜单
		$(menu).parent().parent().prev("a").addClass("active");
		$(menu).addClass("sub-active");
	}else {//一级菜单
		$(menu).addClass("active");
	}
}