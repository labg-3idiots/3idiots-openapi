# 1. build
# gradle:7.3.1-jdk17 이미지를 기반으로 함
FROM gradle:8.9.0-jdk21

# 작업 디렉토리 설정
WORKDIR /home/gradle/project
# Spring 소스 코드를 이미지에 복사
COPY . .

WORKDIR /home/gradle/project/3idiots-openapi

# gradlew를 이용한 프로젝트 필드
RUN ./gradlew clean build -x test

# 빌드 결과 jar 파일을 실행
CMD ["java", "-jar", "-Dspring.profiles.active=prod", "/home/gradle/project/3idiots-openapi/build/libs/openapi.jar"]