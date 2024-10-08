<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/gallery.css" rel="stylesheet" type="text/css">
</head>


<body>
	<div id="wrap">
	
		<!-- header에 빼놓은거 불러오기 (header, nav)-->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>갤러리</h2>
				<ul>
					<li><a href="">일반갤러리</a></li>
					<li><a href="${pageContext.request.contextPath}/attach/form">파일첨부연습</a></li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">
				<div id="content-head">
					<h3>일반갤러리</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>갤러리</li>
							<li class="last">일반갤러리</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->


				<div id="gallery">
					<div id="list">


						<!-- 로그인했을때  -->
						<c:if test="${sessionScope.authUser != null}">
							<button id="btnImgUpload">이미지올리기</button>
							<div class="clear"></div>
						</c:if>


						<ul id="viewArea">

							<c:forEach items="${requestScope.galleryList}" var="GalleryVo">
							<!-- 이미지반복영역 -->
							<li>
								<div class="view">
									<img class="imgItem" src="${pageContext.request.contextPath}/upload/${GalleryVo.saveName}">
									<div class="imgWriter">
										작성자: <strong>${GalleryVo.name}</strong>
									</div>
								</div>
							</li>
							<!-- 이미지반복영역 -->
							</c:forEach>


						</ul>
					</div>
					<!-- //list -->
				</div>
				<!-- //	gallery -->

			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->


		<!-- footer에 빼놓은거 불러오기 (footer)-->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		
	</div>
	<!-- //wrap -->



</body>





</html>

