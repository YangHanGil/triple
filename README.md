# triple
triple 과제

현 과제물은 eclipse 21년9월 버전으로 작성되었으며, java 버전은 jdk1.8입니다.

springboot 실행하시면 포트번호 8080을 기본으로 작성해놨습니다. src/main/resource/application.properties 설정.

db는 요청하신대로 mysql이며 3306번 사용하고, 기본적인 db아이디나 비밀번호는 임의로 설정하신값으로 변경하셔도 됩니다.

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/auth-controller/insertTreview 로 들어가시면 REST API ui 확인하실 수 있습니다.

처음 접속하게 되시면 index.html로 경로가 잡혀있습니다. src/main/resource/static/index.html
여기에서 예시로 주신 값을 입력해 놓았고 javascript fetch로 백엔드에 전송 -> 백엔드에선 처리 후 결과를 json값으로 다시 리턴해줍니다.

index.html -> authcontroller -> reviewservice -> com.triple.repository 내의 java -> mapper 경로로 이동하오니 참고하시기 바랍니다.

db sql의 경우 src/main/resource/static/db 경로 안에 DB_SQL.sql 파일이 저장되어있습니다. 

감사합니다.
