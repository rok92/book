# 1. 베이스 이미지 설정 (자바 17 필요)
FROM openjdk:17-jdk-slim

# 2. 앱 디렉터리 생성
WORKDIR /app

# 3. Gradle 빌드 후 jar 파일 복사
COPY build/libs/*.jar app.jar

# 4. 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]