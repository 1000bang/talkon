# talkon 소켓통신 구현하기 - 개인 프로젝트

## 프로젝트 기한 :
22-10-25 ~ 22-10-31

## 프로젝트 목표 :

서버프로그램 생성

클라이언트 프로그램 생성

프로토콜 정의

양방향 통신 구현

다중 접속 구현

필수기능 - 브로드캐스트 (유저목록, 방개념, 전체 메세지, 귓속말)

유저리스트 띄우기

방만들기 → 방을 알려줌 

방 리스트 띄우기 


## 보완할 점 :

1. 방 만들기 인원 제한 기능 추가하기
2. 방 나가기 버튼 클릭 시 방 사용자에게 알림 기능 & 참여자에서 제외
3. 리스트 클릭시 대화방 정보 나타내기

## 느낀점 :

클라이언트와 서버간 프로토콜을 정해 양방향 소통을 구현할 수 있음에 프로젝트 기간 중 흥미를 많이 느꼈고 생각했던 만큼 진행히 원활히 되지않아 좌절감도 많이 경험했다. 여러 프레임이 생성되다 보니 객체를 사용하는데 nullpoint 때문에 고생했고  naming이 헷갈려 고생했다. 프로젝트를 통해 싱글톤 패턴의 필요성, 유용성도 깨달았고 naming의 중요성도 깨달을 수 있었다. 빨리 spring을 배우고싶었다….


## 프로젝트 이미지 :

1. **유저 /  관리자 페이지** 

<img src = 'https://user-images.githubusercontent.com/113667600/214863391-3d96e2e8-e38a-4dde-a21b-23af7695c1a0.png' width = '2000' height = '500'/>

2. **관리자가 서버를 열고 유저가 로그인시 화면** 


<img src = 'https://user-images.githubusercontent.com/113667600/214863485-1604d228-9e91-4e9d-a5c7-577e4102835a.png'/>


3. **대화방 생성 ( 현재 인원 선택 지원 안함 …)** 


<img src = 'https://user-images.githubusercontent.com/113667600/214863597-ae815a92-cfc4-4ed9-8283-8f5f64d7d363.png' width = '2000' height = '950'/>

3.1 validation 처리 


<img src = 'https://user-images.githubusercontent.com/113667600/214863585-8273bde7-df18-4c0f-b0a6-7f4e0f834329.png'/>

<img src = 'https://user-images.githubusercontent.com/113667600/214863591-bb5772b8-f6ac-4ba6-be7c-aabd8708c61e.png'/>


3.2 방 생성시 관리자에게 생성과 입장을 알림 


<img src = 'https://user-images.githubusercontent.com/113667600/214863582-972f1791-cb09-4cf2-b75e-fa748c3b9b22.png'/>

**4.  대화방 전체 리스트 표시**


<img src = 'https://user-images.githubusercontent.com/113667600/214863877-b3c3281c-bfa2-49dc-a81c-05fd8c01d510.png'/>

5.  **전체 인원 표시**


<img src = 'https://user-images.githubusercontent.com/113667600/214863937-33f9f79f-2ee3-4d83-b72c-1d80e927620a.png' width = '2000' height = '950'/>

**6. 개인 메세지 보내기** 


<img src = 'https://user-images.githubusercontent.com/113667600/214864145-a6983d8e-142f-4b01-be70-c24eb4f68115.png'/>


<img src = 'https://user-images.githubusercontent.com/113667600/214864168-22edeccc-db0d-414c-bbb3-03819321a68b.png'/>


7. **대화방 입장과 양뱡향 채팅** 

입장시 방에 접속한 유저에게 메세지  


<img src = 'https://user-images.githubusercontent.com/113667600/214864318-39e936da-2cb7-4215-bb24-d74c9cfb8ee4.png'/>


양방향 대화 기능


<img src = 'https://user-images.githubusercontent.com/113667600/214864348-63384262-f8e8-441d-ad1a-87566bb5b1cc.png'/>

채팅방 나갔을 때 기능 아직 구현 안됨 ..


<img src = 'https://user-images.githubusercontent.com/113667600/214864345-cfef2c8d-d8a3-450f-907a-3b2fb71e7618.png'/>
