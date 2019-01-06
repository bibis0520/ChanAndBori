/**
 * 	공통 스크립트
 *  commonJs.함수명(argements); 으로 사용
 */

function Common(){};

var commonJs = new Common();

Common.prototype.gfn_checkParam = function( param ){
	if( typeof param === "undefined" || param === null || param == "" ){
		return false;
	}else{
		return true;
	}
}

Common.prototype.gfn_isNumber = function( param ){
	if( !commonJs.gfn_checkParam( param ) ){
		return false;
	}

	return /^[0-9]*$/.test( param );
}

Common.prototype.gfn_isAlpha = function( param ){
	if( !commonJs.gfn_checkParam( param ) ){
		return false;
	}

	return /^[a-zA-z]*$/.test( param );
}

Common.prototype.gfn_isAlphaNumber = function( param ){
	if( !commonJs.gfn_checkParam( param ) ){
		return false;
	}

	return /^[0-9a-zA-z]*$/.test( param );
}

Common.prototype.gfn_isKor = function( param ){
	if( !commonJs.gfn_checkParam( param ) ){
		return false;
	}

	return /^[ㄱ-힇]*$/.test( param );
//	return /^[\uac00-\ud7a3]*$/.test( param );
}

Common.prototype.gfn_getByteUTF8 = function( param ){
	if( !commonJs.gfn_checkParam( param ) ){
		return false;
	}

	var charCode = param.charCodeAt(0);

	if (charCode <= 0x00007F) {
		return 1;
	} else if (charCode <= 0x0007FF) {
		return 2;
	} else if (charCode <= 0x00FFFF) {
		return 3;
	} else {
		return 4;
	}
}

Common.prototype.gfn_getBytesUTF8 = function( param ){
	if( !commonJs.gfn_checkParam( param ) && typeof param.length == "undefined" && param.length ){
		return false;
	}

	var rslt = 0;

	for(var i = 0; i < param.length; i++){
		console.info(param.length, i)
		rslt += commonJs.gfn_getByteUTF8(param.charAt(i));
	}

	return rslt
}