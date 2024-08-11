# ISBN 4 JOSS
한 줄 요약: 안드로이드 앱으로 도서관 전산 시스템을 만들자

1. 개요: [드림 오브 펜팅사리 도서관](https://www.google.com/maps/place/Dream+of+Pentingsari+Library/@-7.6383083,110.4271891,17z/data=!4m6!3m5!1s0x2e689f003e33b96d:0xbd317a2ad1a7779a!8m2!3d-7.6383136!4d110.429764!16s%2Fg%2F11w9h4cr32?entry=ttu)의 도서 전산 자동화
2. 개발목적: 약 250권으로 예상하던 도서가 약 500권으로 생각보다 많아졌음. 고로 JOSS 팀원들의 상당한 노동력이 투자될 것으로 예상됨. 이를 기술을 통해 해결해보자.
3. 결과물: 안드로이드 앱
4. 동작원리
    1) 바코드를 스캔하여 isbn을 알아낸다.
    2) isbn으로 책 정보(저자, 책 제목)을 알아낸다.
    3) 위에서 알아낸 정보와 청구기호(저자 대문자 3글자+책제목 소문자 1글자 조합)를 DB(google spreadsheet)에 저장한다.  
    => 바코드를 찍고 버튼을 누르면 도서 정보와 청구기호가 DB(구글 시트)에 자동으로 올라간다. 고로 타이핑할 시간을 줄일 수 있다.
5. 개발 소요시간: 약 3~4시간


<details>
<summary>소감문 [도전과 성찰을 안겨준 도서 전산 자동화 앱 개발 프로젝트]</summary>
<div markdown="1">  

  
    
초기에는 250권의 도서를 예상했으나, 다양한 기증을 통해 381권의 도서를 추가 확보하면서 도서 관리의 필요성이 커졌다. 특히 UGM 교수님들, 학생들, 지역 주민들로부터의 기증이 크게 기여했다. 이로 인해 JOSS 팀원들의 노동력이 크게 필요할 것으로 예상되었고, 이에 기술을 활용해 효율적으로 도서를 정리하고 관리하고자 했다. 미니 도서관의 도서 전산 시스템을 자동화하기 위해 유진 단원이 안드로이드 앱을 개발했다. 앱 개발의 소요 시간은 약 4시간으로, 도서 관리의 어려움을 해결할 방법과 팀원들의 노고를 덜어주고자 기획하고 개발하였다.

앱의 동작 원리는 바코드를 스캔해 ISBN을 확인하고, ISBN을 이용해 구글 Books API로 저자, 책 제목 등 책 정보를 얻은 후, 해당 정보를 임시 청구기호와 함께 구글 스프레드시트에 자동으로 저장하는 것이다. 바코드 스캔 후 버튼을 누르면 도서 정보가 DB에 자동으로 올라가므로, 타이핑 시간을 절약할 수 있다. 이는 도서 수량이 예상보다 많아진 상황에서 업무를 크게 간소화해 줄 것으로 기대되었다.

하지만 인도네시아의 미흡한 ISBN 전산화로 인해, 앱의 본래 목적이었던 도서 목록 전산화는 완벽히 이루어지지 않았다. 주류 출판사가 아닌 도서, 특히 아동 도서는 ISBN 바코드 스캔으로 책의 정보를 얻기 어려웠다. 그 결과, 초기 목적을 달성하지는 못했으나, 문헌정보학과 학생이 청구기호를 만드는 동안 ISBN 바코드를 스캔하고, 앱으로 조회가 가능한 도서의 장르나 내용을 정리하여 작업 환경을 개선하는 데 크게 기여했다.

시간상의 제약으로 기증 도서의 라벨링 및 청구기호 작업은 완료하지 못했지만, 구매한 도서에 대해서는 모든 작업을 마칠 수 있었다. 이 과정에서 유진 단원은 모든 도서가 전산화되어 있을 것이라는 안일한 생각과 기술만으로 모든 문제를 해결할 수 있다는 자신의 오만함을 깨닫게 되었다. 이번 경험을 통해 현실적 제약을 인식하고, 앞으로의 프로젝트에 더 나은 접근 방식을 고민하게 되었다.

이 프로젝트는 지역사회와 기술의 협력으로 미니 도서관의 발전에 기여하였다. 누군가는 책을 기부하고, 누군가는 기부한 책을 잘 관리하기 위해 라벨링을 하고 앱을 만들었다. Dream of Pentingsari 도서관을 관리하는 데에 지속 가능한 발전을 목표로 도전한 프로젝트였으며, 펜팅사리 마을을 항한 족자카르타 지역 주민들의 관심과 죠스 팀원들이 함께 만들어간 의미 있는 도전이자 성찰의 기회가 되었다.

</div>
</details>


## english
Project Summary: Create a Library Catalog System Using an Android App

1. Overview: Automate the book cataloging process for [Dream of Pentingsari Library](https://www.google.com/maps/place/Dream+of+Pentingsari+Library/@-7.6383083,110.4271891,17z/data=!4m6!3m5!1s0x2e689f003e33b96d:0xbd317a2ad1a7779a!8m2!3d-7.6383136!4d110.429764!16s%2Fg%2F11w9h4cr32?entry=ttu).
2. Development Purpose: The number of books has increased from the estimated 250 to approximately 500. This is expected to require significant manual effort from the JOSS team. We aim to solve this issue through technology.
3. Deliverable: An Android app.
4. Functionality:
    1) Scan the barcode to retrieve the ISBN.
    2) Obtain book information (author, title) using the ISBN.
    3) Save the retrieved information and the call number (a combination of the author’s initials and the first letter of the book title) to a database (Google Sheets).  
    => By scanning the barcode and pressing a button, the book information and call number are automatically uploaded to the database (Google Sheets), reducing the time spent typing.  
5.Estimated Development Time: Approximately 3–4 hours.

## using
- zxing (for scanner)
- google book api
- google spread sheet api (for db)

## reference
- [Google Books API using REST](https://developers.google.com/books/docs/v1/getting_started?hl=ko#REST)
- [Insert Data In Google Sheet by Android Studio| Google Sheet As Database | Android Studio](https://www.youtube.com/watch?v=-NG588pWT-o)

## result
https://github.com/user-attachments/assets/9952ee51-9122-41bf-a0a8-a39229bc0f06

<img width="1440" alt="스크린샷 2024-07-30 오후 4 08 58" src="https://github.com/user-attachments/assets/99820f82-ff31-4c08-97bf-789e0c5dda2a">
