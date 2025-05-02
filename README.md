# Dự án Hospital Management System

Deployments (Frontend):
- [Frontend](https://co3017-fe.vercel.app/)

Công nghệ sử dụng:
- Framework: [Spring](https://spring.io/) (Spring Boot, Spring Cloud)
- Ngôn ngữ: [Java](https://www.java.com/en/) (JDK 21)
- Build tool: [Maven](https://maven.apache.org/)
- Công cụ và thư viện:
  - [Lombok](https://projectlombok.org/)
  - [Eureka](https://spring.io/projects/spring-cloud-netflix)
  - [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway)
  - [Springdoc OpenAPI](https://springdoc.org/)
  - [Micrometer](https://micrometer.io/)
  - [Spring Security](https://spring.io/projects/spring-security)
  - [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
  - [Jib](https://cloud.google.com/java/getting-started/jib) (Containerize Java applications)

## Thành viên tham gia project

- 2211368 - Nguyễn Phúc Hưng
- 2211368 - Trần Đại Quý 
- 2210500 - Đinh Lê Phúc Duy
- 2210509 - Nguyễn Anh Duy
- 2212651 - Trần Hồng Phúc
- 2212615 - Lê Hồng Phúc
- 2213046 - Võ Thanh Tâm
- 2213915 - Bùi Trọng Văn

## Yêu cầu hệ thống
- JDK 21: https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html
- Maven: https://maven.apache.org/download.cgi

## Hướng dẫn cài đặt
### 1. Clone repository về máy
```bash
git clone https://github.com/hongphucle1010/SoftwareArchitecture-HospitalManagement
cd SoftwareArchitecture-HospitalManagement
```

### 2. Cài đặt JDK 21 và Maven
Chắc chắn rằng bạn đã cài đặt JDK 21 và Maven trên máy tính của mình. Bạn có thể kiểm tra bằng cách chạy các lệnh sau trong terminal:
```bash
java -version
mvn -version
```

### 3. Build dự án
```bash
mvn clean install -DskipTests
```

### 4. Chạy ứng dụng
#### 4.1 Chạy Discovery Server
```bash
cd discovery-server
mvn spring-boot:run
```

#### 4.2 Chạy API Gateway
Mở một terminal mới và chạy lệnh sau:
```bash
cd api-gateway
mvn spring-boot:run
```

#### 4.3 Chạy các microservices
##### 4.3.1 Chạy Patient Service
```bash
cd patient-service
mvn spring-boot:run
```
##### 4.3.2 Chạy Staff Service
```bash
cd staff-service
mvn spring-boot:run
```

##### 4.3.3 Chạy Authentication Service
```bash
cd authentication-service
mvn spring-boot:run
```

### 5. Đọc tài liệu API
- Tài liệu API được tạo tự động bằng Springdoc OpenAPI. Bạn có thể truy cập tài liệu API tại địa chỉ sau:
  - [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)