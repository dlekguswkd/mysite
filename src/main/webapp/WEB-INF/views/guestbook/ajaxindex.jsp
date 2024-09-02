<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
			<!-- /mysite -->
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<!-- Axios 라이브러리 포함 -->
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

<!-- ///////////////////////////////////////////////////////////////// -->
<style>
/* 모달창 배경 회색부분 */
.modal {
   width: 100%; /* 가로전체 */
   height: 100%; /* 세로전체 */
   display: none; /* 시작할때 숨김처리 none(원래 폼밑에 있는거임-리스트시작하는위치)  -> block 으로 하면 작은창으로 뜸*/
   position: fixed; /* 화면에 고정 */
   left: 0; /* 왼쪽에서 0에서 시작 */
   top: 0; /* 위쪽에서 0에서 시작 */
   z-index: 999; /* 제일위에 */
   overflow: auto; /* 내용이 많으면 스크롤 생김 */
   background-color: rgba(0, 0, 0, 0.4); /* 배경이 검정색에 반투명 */
}

/* 모달창 내용 흰색부분 */
.modal .modal-content {
   width: 400px;
   margin: 100px auto; /* 상하 100px, 좌우 가운데 */
   padding: 0px 20px 20px 20px; /* 안쪽여백 */
   background-color: #ffffff; /* 배경색 흰색 */
   border: 1px solid #888888; /* 테두리 모양 색 */
}

/* 닫기버튼 */
.modal .modal-content .closeBtn {
   text-align: right;
   color: #aaaaaa;
   font-size: 28px;
   font-weight: bold;
   cursor: pointer;
   border: 0px solid #000000; /* none */
   background-color: #ffffff; 
}
</style>
<!-- ///////////////////////////////////////////////////////////////// -->

</head>

<body>
	<div id="wrap">
	
		<!-- header에 빼놓은거 불러오기 (header, nav)-->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		
	
		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">
				
				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form id="guestbookForm" action="${pageContext.request.contextPath}/api/guestbook/write" method="">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label></th>
									<td><input id="input-uname" type="text" name="name" value=""></td>
									<th><label class="form-text" for="input-pass">패스워드</label></th>
									<td><input id="input-pass"type="password" name="pass" value=""></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button type="submit">등록</button></td>
								</tr>
							</tbody>
							
						</table>
						<!-- //guestWrite -->
						<input type="hidden" name="action" value="add">
						
					</form>	
					
					
					
	               <!-- 모달 창 컨텐츠 -->
	               <div id="myModal" class="modal">
	
	                  <div id="guestbook" class="modal-content">
	                     <button class="closeBtn" type="button">×</button>
	                     <div class="m-header">패스워드를 입력하세요</div>
	                     <div class="m-body">
	                        <input id="modalPw" class="m-password" type="password" name="password" value=""><br> 
	                        <input id="modalNo" class="m-no" type="text" name="no" value="">
	                     </div>
	                     <div class="m-footer">
	                        <button id="btnDelete" class="btnDelete" type="button">삭제</button>
	                     </div>
	                  </div>
	               </div>
	               <!-- /모달 창 컨텐츠 -->
					
					
					<!-- 리스트자리 -->
					<div id="guestbookListArea">
						<!-- afterbegin 시작후 -->
					</div>
					<!-- 리스트자리 -->
					
					<!-- 
					<table class="guestRead">
						<colgroup>
							<col style="width: 10%;">
							<col style="width: 40%;">
							<col style="width: 40%;">
							<col style="width: 10%;">
						</colgroup>
						<tr>
							<td>1234555</td>
							<td>이정재</td>
							<td>2020-03-03 12:12:12</td>
							<td><a href="">[삭제]</a></td>
						</tr>
						<tr>
							<td colspan=4 class="text-left">방명록 글입니다. 방명록 글입니다.</td>
						</tr>
					</table>
					 -->
					
					
			
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<!-- footer에 빼놓은거 불러오기 (footer)-->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->
		
	</div>
	<!-- //wrap -->
	
<script>
// 로딩될때
document.addEventListener('DOMContentLoaded', function(){
	console.log('DOM tree 완료');
	
	// 리스트 가져와서 그리기//////////////////////////////////////////
	getListRender();
	

	// 글 저장하고 그리기 (글저장버튼클릭(전송)할떄) 이벤트 등록//////////////////////////////////////////
	// 전송버튼(form, submit) 클릭했을때 (데이터만 받을거야)
	let guestbookForm = document.querySelector('#guestbookForm');
	guestbookForm.addEventListener('submit', addRender);
	
	
	// 모달창 띄우기 버튼 클릭 이벤트 등록(삭제버튼) .callModal가 아닌 얘의 부모한테 잡아야함///////////////////////////////////
	let listArea = document.querySelector('#guestbookListArea');
	listArea.addEventListener('click', callModal); 

	
	// 모달창 띄운거 닫기 (닫기버튼 클릭할때) 이벤트 등록
	let closeBtn = document.querySelector('.closeBtn');
	closeBtn.addEventListener('click', closeModal);
	
	
	// 모달창의 삭제버튼 클릭할때 이벤트 등록
	let btnDelete = document.querySelector('#btnDelete');
	btnDelete.addEventListener('click', deleteRemove);
	
	

		
});	// DOM tree 끝



/////////////////////---------------------메소드 정리--------------------//////////////////////////////////

// 삭제 (삭제폼 아님) 메소드//////////////////////////////////////////
function deleteRemove() {
	console.log('삭제');
	
	// 데이터 수집
	let modalPwTag = document.querySelector('#modalPw');
	let modalNoTag = document.querySelector('#modalNo');
	
	let password = modalPwTag.value;
	let no = modalNoTag.value;
	
	let guestbookVo = {
		no: no,
		password: password
	};
	
	console.log(guestbookVo);
	
	// db삭제 (서버에 전송해서 삭제)
	axios({
		method: 'get', 				// put, post, delete
		url: '${pageContext.request.contextPath}/api/guestbook/remove',
		headers: {"Content-Type" : "application/json; charset=utf-8"},		 //전송타입
		params: guestbookVo, 			//get방식 파라미터로 값이 전달
		//data: guestbookVo, 			//put, post, delete 방식 자동으로 JSON으로 변환 전달
		
		responseType: 'json' 			//수신타입
	
		}).then(function (response) {
			console.log(response); 		//수신데이타
			console.log(response.data);
			
			if(response.data != null ) {	// 뭐가 넘어오면 (비밀번호가 맞으면)
				// 화면에서 지우기
				let delId = '#t-'+ no;
				let removeTable = document.querySelector(delId);
				console.log(removeTable);
				removeTable.remove();
				closeModal();
				
			}else if(response.data == null) {							// 비밀번호가 틀리면
				alert('비밀번호를 확인하세요');
				// 모달창 닫기
			}

		}).catch(function (error) {
			console.log(error);

		});
	
}


// 리스트 가져와서 그리기 메소드//////////////////////////////////////////
function getListRender(){
	
	// 서버로 데이터 요청 (데이터만 받는)
	axios({
		// 보낼때 데이터
		method: 'get', 					// put, post, delete 방식
		url: '${pageContext.request.contextPath}/api/guestbook/list',		// action 어디에다 데이터 내놓으라고할건지
		headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
		// params, data는 주소뒤에 ?no=~이런거 붙는걸 말함
		//params: guestbookVo, 			//get방식 파라미터로 값이 전달
		//data: guestbookVo, 				//put, post, delete 방식 자동으로 JSON으로 변환 전달
		
		// 받을때 데이터
		responseType: 'json' 			//수신타입
		
	}).then(function (response) {
		console.log(response); //수신데이타

        /* 	// 태그 가져오기  (밑으로 이동 render함수에 넣어둠(그리기))
		let listArea = document.querySelector('#guestbookListArea');
		console.log(listArea); */
		
		
		for(let i=0; i<response.data.length; i++) {
			//console.log(response.data[i].name);		// 이름만 뽑아내기
			
			/* let str = response.data[i].name + ", " + response.data[i].content + "<br>"; */
			
			/* listArea.insertAdjacentHTML('afterbegin', str);  (밑으로 이동)*/
			
			let guestbookVo = response.data[i]  // (no, name, content, regDate 다 포함)
			
			render(guestbookVo, 'down');		// 얘한테 시켜  아래에다가 넣어줘
			
		}
	
	}).catch(function (error) {
		console.log(error);
		
	});
}
// 리스트 가져오기 끝//////////////////////////////////////////
	


//글 저장하고 그리기 메소드//////////////////////////////////////////
function addRender(event) {
	
	event.preventDefault();
	console.log('전송');
	
	// 이름 패스워드 본문 가져오기
	let nameTag = document.querySelector('#input-uname');		// 공부차원에서 id로 가져옴
	let passwordTag = document.querySelector('[name="pass"]');	// 일관성있게 속성값으로 찾기
	let contentTag = document.querySelector('[name="content"]');
	
	let name = nameTag.value;
	let password = passwordTag.value;
	let content = contentTag.value;
	
	let guestbookVo = {
		name: name,
		password: password,
		content: content
	};
	
	console.log(guestbookVo);

	// 전송
	axios({
		method: 'get', 				// put, post, delete
		url: '${pageContext.request.contextPath}/api/guestbook/write',		// action
		headers: {"Content-Type" : "application/json; charset=utf-8"}, 		//전송타입
		// params가 주소뒤에를 완성시켜줌 위에 만든 guestbookVo를 쓰겠다는 뜻 (그냥 파라미터임 ?no=~&name=~ 이런거)(action 뒤에 대신)
		params: guestbookVo, 		//get방식 파라미터로 값이 전달
		//data: guestbookVo, 		//put, post, delete 방식 자동으로 JSON으로 변환 전달

		responseType: 'json' 		//수신타입

	}).then(function (response) {
		console.log(response); 		//수신데이타	내가보낸 전체
		console.log(response.data);		// 내가 보낸거
		
		// 리스트에 추가(그리기)
		render(response.data, 'up');		// 위에다가 넣어줘
		
		// 폼 비우기 (등록하고나면 등록한내용 사라지게하는) 둘중하나 선택해서 사용가능
		guestbookForm.reset();		// nameTag.value='';
									// passwordTag.value='';
									// contentTag.value='';
		
	}).catch(function (error) {
		console.log(error);

	});
	
}
//글 저장하고 그리기 메소드 끝//////////////////////////////////////////


// 모달창 보이기 메소드//////////////////////////////////////////
function callModal(event){
	/* console.log(this);		// 이벤트 걸린놈 guestbookListArea
	console.log(event.target);		// 내가 클릭한놈 버튼 */
	 
	console.log(event.target.tagName);
	if(event.target.tagName == 'BUTTON') {
		console.log('클릭');
		
		// 버튼안에 태그에서 data-no값 가져오기
		let no = event.target.dataset.no;
		console.log(no);
		
		// 모달창의 input의 value = no  (띄우는 창에 no 숨겨두기)
		let txtNoTag = document.querySelector('#modalNo');
		txtNoTag.value = no;
					
		//모달창 보이게 처리 (display: block; 추가)
		let modalTag = document.querySelector('#myModal');
		modalTag.style.display = 'block';		// 화면 띄우기	

	}
	
}
//모달창 보이기 메소드 끝//////////////////////////////////////////


//모달창 닫기 메소드//////////////////////////////////////////
function closeModal(){
	console.log('모달창닫기');
	let modalTag = document.querySelector('#myModal');
	modalTag.style.display = 'none';
}
//모달창 닫기 메소드 끝//////////////////////////////////////////



// 그림그리는 함수 (1개 그리기)
function render(guestbookVo, dir) {		// dir 방향넣어줄거임
	
	// 태그 가져오기
	let listArea = document.querySelector('#guestbookListArea');
	
	let str = '';
	str += '<table id="t-'+guestbookVo.no+'" class="guestRead">';
	str += '	<colgroup>';
	str += '		<col style="width: 10%;">';
	str += '		<col style="width: 40%;">';
	str += '		<col style="width: 40%;">';
	str += '		<col style="width: 10%;">';
	str += '	</colgroup>';
	str += '	<tr>';
	str += '		<td>'+guestbookVo.no+'</td>';
	str += '		<td>'+guestbookVo.name+'</td>';
	str += '		<td>'+guestbookVo.regDate+'</td>';
	str += '		<td><button class="callModal" type="button" data-no="'+guestbookVo.no+'">[삭제]</button></td>';
	str += '	</tr>';
	str += '	<tr>';
	str += '		<td colspan=4 class="text-left">'+guestbookVo.content+'</td>';
	str += '	</tr>';
	str += '</table>	';
	
	if(dir == 'down') {	
		listArea.insertAdjacentHTML('beforeend', str);		// 아래에 위치	(끝전)
		
	}else if(dir == 'up') {
		listArea.insertAdjacentHTML('afterbegin', str);		// 위에 위치 (시작후)
		
	}else {
		console.log('방향체크');								// 방향을 안넣었을때
	}

};


</script>


</body>

</html>