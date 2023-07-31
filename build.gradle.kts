plugins {
    java
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id("org.asciidoctor.jvm.convert") version "3.3.2" // gradle 7 이후
}

group = "com.blog"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation ("org.springframework.boot:spring-boot-starter-security")
    implementation ("org.springframework.security:spring-security-test")
    implementation ("org.springframework.session:spring-session-jdbc")

    implementation("org.springframework.security:spring-security-crypto")
    implementation("org.bouncycastle:bcprov-jdk15on:1.70")


    implementation("com.querydsl:querydsl-core:5.0.0")
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")


    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation ("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly ("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly ("io.jsonwebtoken:jjwt-jackson:0.11.5")

    annotationProcessor("com.querydsl:querydsl-apt:5.0.0:jakarta") // querydsl JPAAnnotationProcessor 사용 지정
    annotationProcessor("jakarta.persistence:jakarta.persistence-api") // java.lang.NoClassDefFoundError(javax.annotation.Entity) 발생 대응
    annotationProcessor("jakarta.annotation:jakarta.annotation-api") // java.lang.NoClassDefFoundError (javax.annotation.Generated) 발생 대응

    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc") // MockMvc Test 를 사용하는 Spring REST Docs

    compileOnly("org.projectlombok:lombok")
    runtimeOnly("com.h2database:h2")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Ascii Doc Snippet Directory
val snippetsDir by extra { file("build/generated-snippets") }

// Ascii Doc Create Tasks
tasks {
    // Test 결과를 snippet Directory에 출력
    test {
        outputs.dir(snippetsDir)
    }

    asciidoctor {
        // test 가 성공해야만, 아래 Task 실행
        dependsOn(test)

        // 기존에 존재하는 Docs 삭제(문서 최신화를 위해)
        doFirst {
            delete(file("src/main/resources/static/docs"))
        }

        // snippet Directory 설정
        inputs.dir(snippetsDir)

        // Ascii Doc 파일 생성
        doLast {
            copy {
                from("build/docs/asciidoc")
                into("src/main/resources/static/docs")
            }
        }
    }

    build {
        // Ascii Doc 파일 생성이 성공해야만, Build 진행
        dependsOn(asciidoctor)
    }

    bootJar {
        enabled = true
        dependsOn(asciidoctor)
    }

    jar {
        enabled = false
    }
}
