## ISBN 4 JOSS
한 줄 요약: 안드로이드 앱으로 도서관 전산 시스템을 만들자

1. 개요: 드림 오브 펜팅사리 도서관의 도서 전산 자동화
2. 개발목적: 약 250권으로 예상하던 도서가 약 500권으로 생각보다 많아졌음. 고로 JOSS 팀원들의 상당한 노동력이 투자될 것으로 예상됨. 이를 기술을 통해 해결해보자.
2. 결과물: 안드로이드 앱
3. 동작원리
  1) 바코드를 스캔하여 isbn을 알아낸다.
  2) isbn으로 책 정보(저자, 책 제목)을 알아낸다.
  3) 위에서 알아낸 정보와 청구기호(저자 대문자 3글자+책제목 소문자 1글자 조합)를 DB(google spreadsheet)에 저장한다.
=> 바코드를 찍고 버튼을 누르면 도서 정보와 청구기호가 DB(구글 시트)에 자동으로 올라간다. 고로 타이핑할 시간을 줄일 수 있다.
4. 개발 소요시간: 약 3~4시간 

## reference
[Insert Data In Google Sheet by Android Studio| Google Sheet As Database | Android Studio](https://www.youtube.com/watch?v=-NG588pWT-o)
