<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">
			
		<!-- header에 빼놓은거 불러오기 (header, nav)-->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>게시판</h2>
				<ul>
					<li><a href="">일반게시판</a></li>
					<li><a href="">댓글게시판</a></li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head">
					<h3>일반게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">일반게시판</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="board">
					<div id="list">
						<form action="${pageContext.request.contextPath}/tboard/list3" method="get">
							<div class="form-group text-right">
								<input type="text" name="keyword" value="">
								<button type="submit" id=btn_search>검색</button>
							</div>
						</form>
						<table>
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>조회수</th>
									<th>작성일</th>
									<th>관리</th>
								</tr>
							</thead>
							<tbody>
									<!-- 넘겨준이름 (requestScope) 	 5개 묶어놓은곳에 거기	  하나 담을 주소 (한바퀴마다)	-->
								<c:forEach items="${requestScope.pMap.tboardList}" var="tboardVo">
								<tr>
									<!-- vo에서 get~(필드랑 이름같게) -->
									<td>${tboardVo.no}</td>
									<td class="text-left"><a href="#">${tboardVo.title}</a></td>
									<td>${tboardVo.name}</td>
									<td>${tboardVo.hit}</td>
									<td>${tboardVo.regDate}</td>
									<td><a href="">[삭제]</a></td>
								</tr>
								</c:forEach>
							</tbody>
						</table>

						<div id="paging">
							<ul>
								<c:if test="${requestScope.pMap.prev}">
									<li><a href="${pageContext.request.contextPath}/tboard/list3?crtpage=${requestScope.pMap.startPageBtnNo-1}&keyword=${param.keyword}">◀</a></li>
								</c:if>
								
								<c:forEach begin="${requestScope.pMap.startPageBtnNo}" 
										   end="${requestScope.pMap.endPageBtnNo}" 
										   step="1" var="page">
									
									<c:choose>
										<c:when test="${param.crtpage == page}">
											<li class="active">
												<a href="${pageContext.request.contextPath}/tboard/list3?crtpage=${page}&keyword=${param.keyword}">${page}</a>
											</li>
										</c:when>
										
										<c:otherwise>
											<li class="">
												<a href="${pageContext.request.contextPath}/tboard/list3?crtpage=${page}&keyword=${param.keyword}">${page}</a>
											</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								
								<c:if test="${requestScope.pMap.next}">
									<li><a href="${pageContext.request.contextPath}/tboard/list3?crtpage=${requestScope.pMap.endPageBtnNo+1}&keyword=${param.keyword}">▶</a></li>
								</c:if>
						
							</ul>


							<div class="clear"></div>
						</div>
						<a id="btn_write" href="">글쓰기</a>

					</div>
					<!-- //list -->
				</div>
				<!-- //board -->
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
