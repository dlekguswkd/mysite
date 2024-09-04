package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.TboardDao;
import com.javaex.vo.TboardVo;

@Service
public class TboardService {
	
	@Autowired
	private TboardDao tboardDao;
	
	/* 리스트만 */
	public List<TboardVo> exeList() {
		System.out.println("TboardService.exeList()");
		
		List<TboardVo> tboardList = tboardDao.selectList();
		
		return tboardList;
		
	}
	
	
	/* 리스트2 + 페이징 */
	public Map<String, Object> exeList2(int crtPage) {
		System.out.println("TboardService.exeList2()");
		//System.out.println(crtPage);
		
		//////////////////////////////////////////////////////////
		// 리스트 가져오기
		//////////////////////////////////////////////////////////
		int listCnt = 10; // 한페이지에 몇개씩 출력할건지 (한페이지의 출력갯수)
		
		// 현재페이지 음수일때 계산
		// (조건식) ? 값 : 값 		3항연산자
		crtPage = (crtPage > 0) ? crtPage : (crtPage=1);
		/* if문으로 표현한거
		if(crtPage <= 0) {
			crtPage = 1;
		}else {
			crtPage = 1;
		}
		*/
		
		// startRowNo 구하기
		// 1 -> (1부터  10개)		2 -> (11부터  10개)	3 -> (21부터  10개)	사람
		// 1 -> (0부터  10개)		2 -> (10부터  10개)	3 -> (20부터  10개)	mysql
		// (1-1)10  0			(2-1)10 10 			(3-1)10  20
		// startRowNo = (crtPage-1)listCnt
		int startRowNo = (crtPage-1)*listCnt;			// 시작번호
		//System.out.println(startRowNo);
		
		// 두개의 데이터를 1개로 묶어준다
		Map<String, Integer> limitMap = new HashMap<String, Integer>();
		limitMap.put("startRowNo", startRowNo);			// 시작번호
		limitMap.put("listCnt", listCnt);				// 몇개
		//System.out.println(limitMap);
		
		List<TboardVo> tboardList = tboardDao.selectList2(limitMap);
		
		//////////////////////////////////////////////////////////
		// 페이징 계산 (하단 버튼)- 페이징번호
		//////////////////////////////////////////////////////////
		
		// 페이징당 버튼 갯수
		int pageBtncount = 5;			// 한번에 나오는 버튼 갯수
		
		int totalCnt = tboardDao.selectTotalCnt();				// 총 글갯수 (db에서 조회할 예정)
		
		// endPageBtnNo 마지막 버튼 번호
		// 1  2  3  4  5  >
		// 1 --> (1, 5)
		// 2 --> (1, 5)
		// 3 --> (1, 5)
		// 4 --> (1, 5)
		// 5 --> (1, 5)
		// <  6  7  8  9  10  >
		// 6 --> (6, 10)
		// 7 --> (6, 10)
		// ...
		// 10 --> (6, 10)
		// 11 --> (11, 15)
		// 1일때 5구하기  현재페이지			나누면  올림하면
		// (1  5)  =>  올림(1/5)5			(0.2)->1*5   --> 5  
		// (2  5)  =>  올림(2/5)5			(0.4)->1*5   --> 5
		// ...
		// (5  5)  =>  올림(5/5)5			(1.0)->1*5   --> 5
		// (6  10)  =>  올림(6/5)5		(1.2)->2*5   --> 10
		// (11  15)  =>  올림(11/5)5		(2.2)->3*5   --> 15
		// 올림(crtPage/pageBtncount)*pageBtncount
		
		// 마지막 버튼 번호
			// 마지막에 다시 int 붙여주기	 올림			// 둘다 정수라서 실수로 바꿔줘야함 (아무리 나눠도 정수로 나와서 올림이안됨)
		int endPageBtnNo = (int)Math.ceil( crtPage / (double)pageBtncount )* pageBtncount;
		//System.out.println(endPageBtnNo);		// 마지막 버튼 번호
		
		// 시작 버튼 번호
		int startPageBtnNo = (endPageBtnNo - pageBtncount) + 1;
		 				 // 현재페이지			버튼시작번호				버튼 마지막번호
		System.out.println(crtPage + ", " + startPageBtnNo + ", " + endPageBtnNo);
		
		// 다음 화살표 유무
		boolean next = false;	// 안보이는게 디폴트
		
		// 한페이지당 글갯수10개 * 마지막버튼번호   <  총 전체글갯수 187개
		if( listCnt * endPageBtnNo  < totalCnt ) {
			next = true;
		}else {
			// 다음 화살표가 false일때 마지막 숫자버튼이 갯수를 정확히 계산 -> 원래는 무조건 5개가 나왔음
			// 187  -- 19page		187/10 ->  18.7 올림  -> 19 로 사용
			endPageBtnNo = (int)Math.ceil (totalCnt/ (double)listCnt);
			// 마지막번호 교체   정수  올림       총글갯수	 실수	  페이지당 출력되는글갯수
			
		}
		
		// 이전 화살표 유무
		boolean prev = false; 
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		// 화면에 표현할 모든 데이터를 묶는다 -> map 	총 5가지
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("tboardList", tboardList);
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		
		return pMap;
		
	}
	
	
	/* 리스트3 + 페이징 + 검색 */
	public Map<String, Object> exeList3(int crtPage, String keyword) {
		System.out.println("TboardService.exeList3()");
		//System.out.println(crtPage);
		
		//////////////////////////////////////////////////////////
		// 리스트 가져오기
		//////////////////////////////////////////////////////////
		int listCnt = 10; // 한페이지에 몇개씩 출력할건지 (한페이지의 출력갯수)
		
		// 현재페이지 음수일때 계산
		// (조건식) ? 값 : 값 		3항연산자
		crtPage = (crtPage > 0) ? crtPage : (crtPage=1);
		/* if문으로 표현한거
		if(crtPage <= 0) {
			crtPage = 1;
		}else {
			crtPage = 1;
		}
		*/
		
		// startRowNo 구하기
		// 1 -> (1부터  10개)		2 -> (11부터  10개)	3 -> (21부터  10개)	사람
		// 1 -> (0부터  10개)		2 -> (10부터  10개)	3 -> (20부터  10개)	mysql
		// (1-1)10  0			(2-1)10 10 			(3-1)10  20
		// startRowNo = (crtPage-1)listCnt
		int startRowNo = (crtPage-1)*listCnt;			// 시작번호
		//System.out.println(startRowNo);
		
		// 두개의 데이터를 1개로 묶어준다 + keyword (object으로 변경)
		Map<String, Object> limitMap = new HashMap<String, Object>();
		limitMap.put("startRowNo", startRowNo);			// 시작번호
		limitMap.put("listCnt", listCnt);				// 몇개
		limitMap.put("keyword", keyword);
		
		List<TboardVo> tboardList = tboardDao.selectList3(limitMap);
		
		//////////////////////////////////////////////////////////
		// 페이징 계산 (하단 버튼)- 페이징번호
		//////////////////////////////////////////////////////////
		
		// 페이징당 버튼 갯수
		int pageBtncount = 5;			// 한번에 나오는 버튼 갯수
		
		int totalCnt = tboardDao.selectTotalCntKeyword(keyword);				// 총 글갯수 (db에서 조회할 예정)
		
		// endPageBtnNo 마지막 버튼 번호
		// 1  2  3  4  5  >
		// 1 --> (1, 5)
		// 2 --> (1, 5)
		// 3 --> (1, 5)
		// 4 --> (1, 5)
		// 5 --> (1, 5)
		// <  6  7  8  9  10  >
		// 6 --> (6, 10)
		// 7 --> (6, 10)
		// ...
		// 10 --> (6, 10)
		// 11 --> (11, 15)
		// 1일때 5구하기  현재페이지			나누면  올림하면
		// (1  5)  =>  올림(1/5)5			(0.2)->1*5   --> 5  
		// (2  5)  =>  올림(2/5)5			(0.4)->1*5   --> 5
		// ...
		// (5  5)  =>  올림(5/5)5			(1.0)->1*5   --> 5
		// (6  10)  =>  올림(6/5)5		(1.2)->2*5   --> 10
		// (11  15)  =>  올림(11/5)5		(2.2)->3*5   --> 15
		// 올림(crtPage/pageBtncount)*pageBtncount
		
		// 마지막 버튼 번호
			// 마지막에 다시 int 붙여주기	 올림			// 둘다 정수라서 실수로 바꿔줘야함 (아무리 나눠도 정수로 나와서 올림이안됨)
		int endPageBtnNo = (int)Math.ceil( crtPage / (double)pageBtncount )* pageBtncount;
		//System.out.println(endPageBtnNo);		// 마지막 버튼 번호
		
		// 시작 버튼 번호
		int startPageBtnNo = (endPageBtnNo - pageBtncount) + 1;
		 				 // 현재페이지			버튼시작번호				버튼 마지막번호
		//System.out.println(crtPage + ", " + startPageBtnNo + ", " + endPageBtnNo);
		
		// 다음 화살표 유무
		boolean next = false;	// 안보이는게 디폴트
		
		// 한페이지당 글갯수10개 * 마지막버튼번호   <  총 전체글갯수 187개
		if( listCnt * endPageBtnNo  < totalCnt ) {
			next = true;
		}else {
			// 다음 화살표가 false일때 마지막 숫자버튼이 갯수를 정확히 계산 -> 원래는 무조건 5개가 나왔음
			// 187  -- 19page		187/10 ->  18.7 올림  -> 19 로 사용
			endPageBtnNo = (int)Math.ceil (totalCnt/ (double)listCnt);
			// 마지막번호 교체   정수  올림       총글갯수	 실수	  페이지당 출력되는글갯수
			
		}
		
		// 이전 화살표 유무
		boolean prev = false; 
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		// 화면에 표현할 모든 데이터를 묶는다 -> map 	총 5가지
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("tboardList", tboardList);
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		
		return pMap;
		
	}
	

}
