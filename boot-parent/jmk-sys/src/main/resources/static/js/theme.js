var saveSelectColor = {
    'Name': 'SelcetColor',
    'Color': 'theme-black'
}
//iframe下的body标签
var if_body = document.getElementById('content_').contentWindow.document.getElementsByTagName('body');


// 判断用户是否已有自己选择的模板风格
if (storageLoad('SelcetColor')) {
    $('body').attr('class', storageLoad('SelcetColor').Color);
	$(if_body).attr('class', storageLoad('SelcetColor').Color);
} else {
    storageSave(saveSelectColor);
    $('body').attr('class', 'theme-black');
	$(if_body).attr('class', 'theme-black');
}


// 本地缓存
function storageSave(objectData) {
    localStorage.setItem(objectData.Name, JSON.stringify(objectData));
}

function storageLoad(objectName) {
    if (localStorage.getItem(objectName)) {
        return JSON.parse(localStorage.getItem(objectName))
    } else {
        return false
    }
}