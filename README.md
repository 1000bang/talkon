# talkon

## 소켓통신 구현하기 - 개인 프로젝트

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

1. **유저 /  관리자 페이지** 

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e43203ea-e052-4b6e-8d5f-c7c14442620b/Untitled.png)

1. **관리자가 서버를 열고 유저가 로그인시 화면** 

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/3fffa6b8-5488-4e74-936c-37a068f430d0/Untitled.png)

1. **대화방 생성 ( 현재 인원 선택 지원 안함 …)** 

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/965643be-93f0-48de-9dc3-2ddc0dd7edc3/Untitled.png)

3.1 validation 처리 

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ab839cdb-2f9b-441f-a16e-00e8c1f99ced/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/8b12c712-c510-4610-a5e4-a88e72e57a86/Untitled.png)

3.2 방 생성시 관리자에게 생성과 입장을 알림 

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5de9b8bf-02ae-4dfd-9d2e-87ae375573d9/Untitled.png)

**4.  대화방 전체 리스트 표시**

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/7a30081c-bbed-4248-aa4f-df2d71746b46/Untitled.png)

1.  **전체 인원 표시**

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/7d401007-9c5a-4b90-842f-c92834bfbf85/Untitled.png)

**6. 개인 메세지 보내기** 

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/66d8e4cc-2de9-4b5c-9bf2-5ac7d20460dd/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/67127919-5431-4603-9f57-6fcd888e0e5a/Untitled.png)

1. **대화방 입장과 양뱡향 채팅** 

입장시 방에 접속한 유저에게 메세지  

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2bf97ecb-1463-4fe3-b7ea-b0949a3d0c15/Untitled.png)

양방향 대화 기능

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d422fdfc-3e2e-4709-8c66-7a325c3c65b9/Untitled.png)

채팅방 나갔을 때 기능 아직 구현 안됨 ..

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9f353a5c-480f-497c-811e-a44e4148864c/Untitled.png)

## 보완할 점 :

1. 방 만들기 인원 제한 기능 추가하기
2. 방 나가기 버튼 클릭 시 방 사용자에게 알림 기능 & 참여자에서 제외
3. 리스트 클릭시 대화방 정보 나타내기

## 느낀점 :

클라이언트와 서버간 프로토콜을 정해 양방향 소통을 구현할 수 있음에 프로젝트 기간 중 흥미를 많이 느꼈고 생각했던 만큼 진행히 원활히 되지않아 좌절감도 많이 경험했다. 여러 프레임이 생성되다 보니 객체를 사용하는데 nullpoint 때문에 고생했고  naming이 헷갈려 고생했다. 프로젝트를 통해 싱글톤 패턴의 필요성, 유용성도 깨달았고 naming의 중요성도 깨달을 수 있었다. 빨리 spring을 배우고싶었다….
