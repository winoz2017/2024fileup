# 베이스 이미지 설정
FROM tomcat:10.0-jdk17

# 애플리케이션 WAR 파일 복사
COPY target/view.war /usr/local/tomcat/webapps/

# 톰캣 실행
CMD ["catalina.sh", "run"]