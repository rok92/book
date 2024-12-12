# book
### 1. 개발환경
- OpenJDK 17
- Typescript
- h2-database
- IntelliJ
- VsCode

### 2. 라이브러리, 프레임워크
- React
- Bootstrap
- Spring boot
- Lombok
- Gradle
- Security

### 3. 실행방법
- 실행전 필요사항
	- Java 17버전, node.js 18버전 이상 필요
- 폴더:
	- book
		- book-station-front(프론트엔드)
		- book_station(백엔드)
- 절차
	- `git clone https://github.com/rok92/book.git` 으로 프로젝트 받기
	- book_station폴더로 이동 후 백엔드 코드 실행
		- IntelliJ사용시 src/main/java/me.kyeongrok/sprginbootdeveloper에 있는 SpringBootDeveloperApplication파일 실행
		- 터미널 실행시: 터미널에 `./gradlew bootRun &` 입력후 엔터
	- 프론트 실행방법(2가지 중 한 개만 선택)
		1. `https://rokybookstation.netlify.app`로 접속
		2. book-station-front폴더로 이동 후 프론트엔드 코드 실행
		- `npm i`로 필요 패키지 설치
		- `npm run dev`로 실행 