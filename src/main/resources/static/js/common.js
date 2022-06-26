var Common = {
	emlPattern : /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/,  //이메일정규식
	numPattern : /[^0-9]/gi,   // 숫자만정규식	
	dotPattern : /^([0-9]*)(.[0-9]{1,2})?$/,		//소수점2자리만 가능
	bthDayPattern : /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/,	//생년월일 정규식
	telPattern : /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/,	//전화번호 정규식
	mobPattern : /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/, //핸드폼번호 정규식
	//emlPattern : /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i, //이메일정규식
	rowPattern : /\n/g,		//줄바꿈정규식
	enterPattern : /\r/g,	//엔터정규식
	smEngNumPattern : /^[a-z0-9]$/, 	// 숫자,영문소문자만 입력가능 정규식
	upEngNumPattern : /^[A-Z0-9]$/, 	// 숫자,영문대문자만 입력가능 정규식
	idPattern : /^[0-9a-z]([-_.]?[0-9a-z])*$/,						// 아이디 정규식
	engPattern : /^[a-zA-Z]$/, 			// 영문만 입력가능 정규식
	smEngPattern : /^[a-z]$/, 		// 영문소문자만 입력가능 정규식
	upEngPattern : /^[A-Z]$/, 		// 영문대문자만 입력가능 정규식
	//숫자이외는 모두 삭제
	getReplaceNumber : function(param) {
		return $.trim(param).replace(this.numPattern, "");
	},
	//소수점2자리만 가능
	isDotNumber : function(param) {
		return this.dotPattern.test($.trim(param));
	},
	//숫자만 체크
	isNumber : function(param) {
		return this.numPattern.test(param) ? false : true;
	},
	//아이디 체크
	isUserId : function (param) {
		var flag = this.idPattern.test($.trim(param));
		if (!flag) return false;
		
		if ($.trim(param).length < 4 || $.trim(param).length > 20) return false;
		return true;
	}, 
	//생년월일 체크
	isBirthDay : function (param) {
		var birthday = this.getReplaceNumber(param);
		return this.bthDayPattern.test(birthday);
	},
	//이메일 체크
	isEmail : function (param) {
		return this.emlPattern.test(param);
	}, 
	//전화번호 체크
	isTelNumber : function (param) {
		return this.telPattern.test($.trim(param));
	}, 
	//핸드폰번호 체크
	isMobNumber : function (param) {
		return this.mobPattern.test($.trim(param));
	},
	//줄바꿈 체크
	isAddRow : function(param) {
		return this.rowPattern.test(param);
	},
	//엔터 체크
	isEnter : function(param) {
		return this.enterPattern.test(param);
	},
	//즐바꿈 삭제
	removeAddRow : function(param) {
		return param.replace(this.rowPattern, '');
	},
	//엔터 삭제
	removeEnter : function(param) {
		return param.replace(this.enterPattern, '');
	},
	//개행문자 삭제
	removeNewLine : function(param) {
		return this.removeEnter(this.removeAddRow(param));
	},
	//데이타 타입체크
	getType : function(param) {
	    if (param == null)  return 'null';
	    
	    var t = typeof param;
	    if (t != "object") return t;
	    
	    var c = Object.prototype.toString.apply(param);
	    c = c.substring(8, c.length - 1);
	 
	    if (c != "Object") return c;
	    
	    if (c.constructor == "Object")  return c;
	    else {
	        var s = param.constructor.toString();
	        var i = s.indexOf("(");
	        return s.substring(9, i);
	    }
	 
	    return "unknown type";
	},
	//Ajax호출(POST)
	call : function(url, param, callback, sync) {
		if ($.trim(url) == '') return alert('호출 URL정보가 전송되지 않았습니다.');
		$.ajax({  
			type: "POST", 
			dataType: 'json',
			url: url,  
			data: JSON.stringify(param),  
			cache: 'false',
			async : sync || true,
		    success: function(msg){
				if (callback) callback(msg);
		    },
		    error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				console.log(request.status);
				console.log(request.responseText);
				console.log(error);
		    }
		});
	},
	//Ajax호출(GET)
	callget : function(url, param, callback, sync) {
		if ($.trim(url) == '') return alert('호출 URL정보가 전송되지 않았습니다.');
		$.ajax({  
		     type: "json", 
		     url: url,  
		     data: param,  
		     method: "get",
		     async : sync || true,
		     success: function(msg){
		    	 if (callback) callback(msg);
		     },
		     error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		     }
		});
	},
	
	//ajax 업로드
	upload : function(url, formData, callback, sync) {
		if ($.trim(url) == '') return alert('호출 URL정보가 전송되지 않았습니다.');
		$.ajax({  
			url : url,
			type : 'POST',
			data : formData,
			enctype : "multipart/form-data",
			processData: false,
			contentType: false,
			cache: false,
			async : false,
			success : function(msg) {
		    	 if (callback) callback(msg);
		    }
		});
	},
	
	// 페이지 이동
	pageMove : function(url) {
		location.href = url;
	},
	searchMove : function(url) {
		location.href = url+"?search="+$(".searche-bar").val();
	},
	
	//라디오 및 체크박스 선택값
	radioName : function(name){
		return $("input[name="+name+"]:radio:checked").val(); 
	},
	CheckBoxId : function(id){
		return $("#"+id+":checkbox:checked").val(); 
	},
	CheckBoxName : function(name){
		var cnt = 0;
		var CheckBoxVal = "";
		for(var i=0; i<$("input[name="+name+"]").length; i++){
			if($("input[name="+name+"]").eq(i).is(":checked")){
				if(cnt!=0) CheckBoxVal = CheckBoxVal + ","; 
				CheckBoxVal = CheckBoxVal + ($("input[name="+name+"]").eq(i).val());
				cnt ++;
			}
		}
		return CheckBoxVal
	},
	/*동적태그 날짜 추가시*/
	addDataPicker : function(id, dateInfo){/* dateInfo는 월까지 표시인지, 일까지 표시인지*/
		if(dateInfo == "month"){
			$("#"+id).monthpicker({
				monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
				monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
				changeYear: false,
				yearRange: "c-2:c+2",
				dateFormat: "yy-mm"
			});
		} else {
			$("#"+id).datepicker({
				dateFormat: 'yy-mm-dd',
				prevText: '이전 달',
				nextText: '다음 달',
				monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
				monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
				dayNames: ['일','월','화','수','목','금','토'],
				dayNamesShort: ['일','월','화','수','목','금','토'],
				dayNamesMin: ['일','월','화','수','목','금','토'],
				showMonthAfterYear: true,
				yearSuffix: '년'
			});
		}
	},
	
	/*엔터 효과*/
	enterkey : function(key){
		if (window.event.keyCode == 13) {
			Common.searchMove("/mento");
		}
	},
	
	//
	
}

function fn_prev(page, range, rangeSize, url){
	var board_selec = $("#board_selec").val();
	var boardSearch = $("#boardSearch").val();

	location.href = url+"?page="+(page-1)+"&range="+(range-1)+"&listSize="+rangeSize+"&board_selec="+board_selec+"&boardSearch="+boardSearch;
}

function fn_next(page, range, rangeSize, url){
	var board_selec = $("#board_selec").val();
	var boardSearch = $("#boardSearch").val();

	location.href = url+"?page="+(page+1)+"&range="+(range+1)+"&listSize="+rangeSize+"&board_selec="+board_selec+"&boardSearch="+boardSearch;
}