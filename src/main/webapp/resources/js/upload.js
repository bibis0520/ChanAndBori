const $fileDrop = $('div.fileDrop');

//????????????????????????????????????????????????????????
let gUri = window.location.pathname,
	gIsRegister = gUri.indexOf('/register') !== -1,
	gIsUpdate	= gUri.indexOf('/update') !== -1,
	gIsEditing	= gIsRegister || gIsUpdate;

$fileDrop.on('dragover dragenter', (event) => {
	event.preventDefault();
	$fileDrop.css("border", "5px dotted black");
});

$fileDrop.on('dragleave', (event) => {
	event.preventDefault();
	$fileDrop.css("border", "3px dotted whitesmoke");
});

$fileDrop.on('drop', (event) => {
	event.preventDefault();
	let files = event.originalEvent.dataTransfer.files;
	console.log("droped File>>>>>>>>>", files);

	$fileDrop.css("border", "none");
});

function checkImageType(fileName){
	var pattern = /jpg|gif|png|jpeg/i;

	return fileName.match(pattern);
}

function getFileInfo(fullName){

	var fileName, imgsrc, getLink;

	var fileLink;

	if ( checkImageType(fullName) ){
		//이미지 파일일 경우!
		imgsrc 		= "/displayFile?fileName=" + fullName;
		fileLink 	= fullName.substr(14);

		var front 	= fullName.substr(0,12);
		var end 	= fullName.substr(14);

		getLink 	= "/displayFile?fileName=" + front + end;

	} else {
		//이미지 파일이 아닐 경우!
		imgsrc 		= "/resources/images/file.png";
		fileLink 	= fullName.substr(12);
		getLink 	= "/displayFile?fileName=" + fullName;

	}

	fileName = fileLink.substr(fileLink.indexOf("_")+1);  // UUID와 '_'를 제외하고 그 다음부터가 원본 파일 이름.

	return {fileName:fileName, imgsrc:imgsrc, getLink:getLink, fullName:fullName};
}